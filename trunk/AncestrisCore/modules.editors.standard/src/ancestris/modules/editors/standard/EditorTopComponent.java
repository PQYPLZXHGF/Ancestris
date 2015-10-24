/*
 * Ancestris - http://www.ancestris.org
 * 
 * Copyright 2011 Ancestris
 * Author: Daniel Andre (daniel@ancestris.org).
 * 
 * Copyright 2015 Ancestris
 * Author: Frederic Lapeyre (frederic@ancestris.org).
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */
package ancestris.modules.editors.standard;

import ancestris.api.editor.Editor;
import ancestris.core.beans.ConfirmChangeWidget;
import ancestris.view.AncestrisDockModes;
import ancestris.view.AncestrisTopComponent;
import ancestris.view.AncestrisViewInterface;
import genj.gedcom.Context;
import genj.gedcom.Gedcom;
import genj.gedcom.GedcomException;
import genj.gedcom.GedcomListenerAdapter;
import genj.gedcom.UnitOfWork;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.openide.awt.UndoRedo;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.RetainLocation;
import org.openide.windows.TopComponent;

/**
 *
 * @author daniel & frederic
 */
@ServiceProvider(service = AncestrisViewInterface.class)
@RetainLocation(AncestrisDockModes.EDITOR)
public class EditorTopComponent extends AncestrisTopComponent implements TopComponent.Cloneable, ConfirmChangeWidget.ConfirmChangeCallBack {

    private static final String PREFERRED_ID = "AncestrisEditor";  // NOI18N

    final static Logger LOG = Logger.getLogger("ancestris.editor");
    
    // Main elements
    private Gedcom gedcom = null;
    private Editor editor = null;
    private Context context = null;
    
    // Update control
    private final Callback callback = new Callback();
    private boolean isChangeSource = false;

    // Redo elements
    private int undoNb;
    private UndoRedoListener undoRedoListener;
    
    // Panel elements
    private ConfirmChangeWidget confirmPanel;


    
    
    /**
     * Initializers (#1)
     */
    @Override
    public void setName() {
        if (editor != null && editor.getName() != null) {
            setName(editor.getName());
        } else {
            super.setName();
        }
    }

    /**
     * Initializers (#2)
     */
    @Override
    public void setToolTipText() {
        if (editor != null && editor.getToolTipText() != null) {
            setToolTipText(editor.getToolTipText());
        } else {
            super.setToolTipText();
        }
    }


    /**
     * Initializers (#3)
     * 
     * - Called by AncestrisTopComponent.init
     * - Called by FireSelection after call to componentOpened (from open gedcom)
     * @param context
     */
    @Override
    public void setContextImpl(Context context) {
        // Quit if context is null or the same 
        if (context == null || context.getEntity() == null || this.context == context) {
            return;
        }
        LOG.fine("setContextImpl context = " + context.toString());
        this.context = context;
        
        // Reconnect to gedcom if different
        if (context.getGedcom() != gedcom) {
            // Disconnect if not null
            if (gedcom != null) {
                gedcom.removeGedcomListener(callback);
            }
            gedcom = context.getGedcom();
            gedcom.addGedcomListener(callback);
        }

        // Commit if necessary without asking for confirmation
        commit(false);

        // Prepare confirm panel
        if (confirmPanel == null) {
            confirmPanel = new ConfirmChangeWidget(this);
            confirmPanel.setChanged(false);
        }
        
        // Set the right editor panel to display
        if (editor == null) {
            if (context.getEntity().getTag().equals(Gedcom.INDI)) {
                LOG.fine("setContextImpl create Indi panel");
                editor = new IndiPanel();
                editor.addChangeListener(confirmPanel);
            } else {
                return;
            }
        }
        
        editor.setContext(context);

        // Save undo index for use in cancel
        undoNb = gedcom.getUndoNb();
        
        // Show
        revalidate();
        repaint();
    }

    
    
    /**
     * Initializers (#4)
     * @return 
     */
    @Override
    public Image getImageIcon() {
        Image icon = null;
        if (editor != null) {
            icon = editor.getImageIcon();
        }
        if (icon == null) {
            icon = getImageIcon("ancestris/modules/editors/standard/editeur_standard.png");
        }
        if (icon == null) {
            icon = super.getImageIcon();
        }
        return icon;
    }


    
    /**
     * Initializers (#5)
     * @return 
     */
    @Override
    public boolean createPanel() {
        LOG.fine("createPanel - editor.");
        if (editor == null) {
            return false;
        }
        LOG.fine("createPanel - editor hashcdode = " + editor.hashCode());
        
        // Display editor
        JPanel panelContainer = new JPanel(new BorderLayout());
        panelContainer.add(editor, BorderLayout.PAGE_START);
editor.setBorder(BorderFactory.createLineBorder(Color.red));
        panelContainer.add(confirmPanel, BorderLayout.PAGE_END);
confirmPanel.setBorder(BorderFactory.createLineBorder(Color.red));
        setPanel(panelContainer);
        
        return true;
    }

    

    /**
     * Initializers (#6)
     * @return 
     */
    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }

    /**
     * Initializers (#7)
     */
    @Override
    public void componentOpened() {
        undoRedoListener = new UndoRedoListener();
        UndoRedo undoRedo = getUndoRedo();
        undoRedo.addChangeListener(undoRedoListener);
    }


    
    
    
    /**
     * Updates
     */
    
    
    public void commit() {
        commit(true);
    }

    
    private void commit(boolean ask) {
        // we only consider committing IF we're still in a visible top level ancestor (window) - otherwise we assume
        // that the containing window was closed and we're not going to throw a dialog out there or do a change
        // behind the covers - we really would need a about-to-close hook for contained components here :(
        if (!isOpened()) {
            return;
        }

        // Changes?
        if (confirmPanel == null || !confirmPanel.hasChanged()) {
            return;
        }

        // Do not commit for auto commit
        if (ask && !confirmPanel.isCommitChanges()) {
            cancel();
            confirmPanel.setChanged(false);
            return;
        }

        try {

            isChangeSource = true;

            if (gedcom.isWriteLocked()) {
                editor.commit();
            } else {
                gedcom.doUnitOfWork(new UnitOfWork() {

                    @Override
                    public void perform(Gedcom gedcom) throws GedcomException {
                        editor.commit();
                    }
                });
            }

        } catch (Throwable t) {
            LOG.log(Level.WARNING, "error committing editor", t);
        } finally {
            isChangeSource = false;
            confirmPanel.setChanged(false);
        }
    }

    
    private void cancel() {
        // we only consider cancelling IF we're still in a visible top level ancestor (window) - otherwise we assume
        // that the containing window was closed and we're not going to throw a dialog out there or do a change
        // behind the covers - we really would need a about-to-close hook for contained components here :(
        if (!isOpened()) {
            return;
        }

        if (!gedcom.isWriteLocked()) {
            while (gedcom.getUndoNb() > undoNb && gedcom.canUndo()) {
                gedcom.undoUnitOfWork(false);
            }
        }

    }

    public void okCallBack(ActionEvent event) {
        commit(false);
    }

    public void cancelCallBack(ActionEvent event) {
        cancel();

        // Re-set for cancel
        Context ctx = editor.getContext();
        editor.setContext(ctx);
    }

    
    public TopComponent cloneComponent() {
        if (getContext() == null) {
            return null;
        }

        AncestrisTopComponent topComponent = new EditorTopComponent();
        topComponent.init(getContext());
        return topComponent;
    }

    
    
    
    /**
     * Classes
     */
    
    private class Callback extends GedcomListenerAdapter {

        @Override
        public void gedcomWriteLockAcquired(Gedcom gedcom) {
            LOG.fine("Callback - gedcomWriteLockAcquired");

            // Changes we have to commit?
            if (!isChangeSource) {
                commit(false);
            }

        }
    }

    
    
    private class UndoRedoListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            LOG.fine("UndoRedoListener - stateChanged");
            
            Context ctx = editor.getContext();
            editor.setContext(ctx);
        }
    }
}
