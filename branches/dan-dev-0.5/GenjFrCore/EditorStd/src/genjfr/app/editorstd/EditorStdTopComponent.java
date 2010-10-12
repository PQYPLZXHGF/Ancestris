/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genjfr.app.editorstd;

import genj.gedcom.Context;
import genj.gedcom.Entity;
import genj.gedcom.Gedcom;
import genj.gedcom.GedcomException;
import genj.gedcom.UnitOfWork;
import genjfr.app.App;
import genjfr.app.GenjViewInterface;
import genjfr.explorer.ExplorerNode;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.swing.GroupLayout;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import org.openide.util.Exceptions;
import org.openide.util.LookupEvent;
import org.openide.util.NbBundle;
import org.openide.windows.Mode;
import org.openide.windows.TopComponent;
import org.openide.util.ImageUtilities;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.Lookup;
import org.openide.util.LookupListener;
import org.openide.util.NbPreferences;
import org.openide.util.Utilities;
import org.openide.windows.WindowManager;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//genjfr.app.editorstd//EditorStd//EN",
autostore = false)
public final class EditorStdTopComponent extends TopComponent implements LookupListener,GenjViewInterface {

    // One TopComponent per Gedcom
    private static SortedMap<String, EditorStdTopComponent> instances;
    //
    // Path to the icon used by the component and its open action */
    static final String ICON_PATH = "genjfr/app/editorstd/images/editorStd.png";
    private static final String PREFERRED_ID = "EditorStdTopComponent";
    // Variables per TopComponent instance
    private Gedcom gedcom = null;
    private Lookup.Result result = null;
    private EntityPanel panelOn = null;
    private Entity previousSelectedEntity = null;

    public EditorStdTopComponent() {
        super();
    }

    public EditorStdTopComponent(Gedcom gedcom, String id) {
        super();
        init(gedcom, id);
    }

    public void init(Gedcom gedParam, String id) {
        // Init gedcom
        initGedcom(gedParam);
        if (gedcom == null) {
            close();
            return;
        }

        // TopComponent name, tooltip and Gedcom
        setName(NbBundle.getMessage(EditorStdTopComponent.class, "CTL_EditorStdTopComponent"));
        setToolTipText(NbBundle.getMessage(EditorStdTopComponent.class, "HINT_EditorStdTopComponent"));
        setIcon(ImageUtilities.loadImage(ICON_PATH, true));
        ToolTipManager.sharedInstance().setDismissDelay(10000);
        String name;
        if ((gedcom != null) && ((name = gedcom.getName()) != null)) {
            setName(name);
            setToolTipText(getToolTipText() + ": " + name);
        }

        // TopComponent window parameters
        initComponents();

        // Set Panel with entity
        if (gedcom != null && id != null && !id.isEmpty()) {
            setPanel(gedcom.getEntity(id));
        }
    }

    private void initGedcom(Gedcom gedParam) {
        if (gedcom == null) {
            if (gedParam == null) {
                Context c = App.center.getSelectedContext(true);
                if (c == null) {
                    return;
                }
                gedcom = c.getGedcom();
            } else {
                gedcom = gedParam;
            }
        }
        setGedcom(gedcom);
    }

    private void setGedcom(Gedcom selectedGedcom) {
        gedcom = selectedGedcom;
        String name;
        if ((gedcom != null) && ((name = gedcom.getName()) != null)) {
            setName(name);
            setToolTipText(getToolTipText() + ": " + name);
        }
    }

    public Gedcom getGedcom() {
        return gedcom;
    }

    public static EditorStdTopComponent updateInstances(EditorStdTopComponent aThis) {
        if (instances == null) {
            instances = new TreeMap<String, EditorStdTopComponent>();
        }
        if (aThis != null) {
            instances.put(aThis.getGedcom().getOrigin().toString(), aThis);
            return aThis;
        }
        Context c = App.center.getSelectedContext(true);
        if (c == null) {
            return null;
        }
        Gedcom selectedGedcom = c.getGedcom();
        Entity selectedEntity = c.getEntity();
        String selectedId = "";
        if (selectedGedcom == null) {
            return null;
        }
        if (selectedEntity != null) {
            selectedId = selectedEntity.getId();
        }
        String gedcomName = selectedGedcom.getOrigin().toString();
        EditorStdTopComponent editorTC = instances.get(gedcomName);
        if (editorTC == null) {
            editorTC = new EditorStdTopComponent(selectedGedcom, selectedId);
            instances.put(gedcomName, editorTC);
        }
        return editorTC;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(EditorStdTopComponent.class, "EditorStdTopComponent.jLabel1.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addContainerGap(445, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(352, Short.MAX_VALUE))
        );

        org.openide.awt.Mnemonics.setLocalizedText(okButton, org.openide.util.NbBundle.getMessage(EditorStdTopComponent.class, "EditorStdTopComponent.okButton.text")); // NOI18N
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(okButton))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(okButton))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        try {
            gedcom.doUnitOfWork(new UnitOfWork() {

                @Override
                public void perform(Gedcom gedcom) throws GedcomException {
                    panelOn.saveEntity();
                }
            });
        } catch (GedcomException ex) {
            NotifyDescriptor d = new NotifyDescriptor.Confirmation(NbBundle.getMessage(EditorStdTopComponent.class, "CTL_CannotSave"),
                    NbBundle.getMessage(EditorStdTopComponent.class, "CTL_Error"),
                    NotifyDescriptor.YES_NO_OPTION);
            DialogDisplayer.getDefault().notify(d);
            Exceptions.printStackTrace(ex);
        }
    }//GEN-LAST:event_okButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton okButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ALWAYS;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void componentOpened() {
        Lookup.Template tpl = new Lookup.Template(ExplorerNode.class);
        result = Utilities.actionsGlobalContext().lookup(tpl);
        result.addLookupListener(this);
    }

    @Override
    public void componentClosed() {
        result.removeLookupListener(this);
        result = null;
    }

    @Override
    public void resultChanged(LookupEvent lookupEvent) {
        Lookup.Result r = (Lookup.Result) lookupEvent.getSource();
        Collection c = r.allInstances();
        if (!c.isEmpty()) {
            ExplorerNode o = (ExplorerNode) c.iterator().next();
            Entity selectedEntity = o.getContext() != null ? o.getContext().getEntity() : null;
            if (gedcom != null && selectedEntity != null && selectedEntity.getGedcom() == gedcom) {
                setPanel(selectedEntity);
            }
        }
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        if (gedcom != null) {
            p.setProperty("gedcom", gedcom.getOrigin().toString());
            if (panelOn != null && panelOn.getEntity() != null) {
                p.setProperty("entity", panelOn.getEntity().getId());
            }
        }
    }

    Object readProperties(java.util.Properties p) {
        readPropertiesImpl(p);
        return this;
    }

    private void readPropertiesImpl(java.util.Properties p) {
        String version = p.getProperty("version");
        final String gedcomProperty = p.getProperty("gedcom");
        final String entityProperty = p.getProperty("entity");
        if (gedcomProperty == null) {
            close();
        } else {
            waitStartup(gedcomProperty, entityProperty);
        }
    }

    void waitStartup(String gedcomProperty, String entityProperty) {
        final String gedName = gedcomProperty;
        final String entId = entityProperty;
        final EditorStdTopComponent aThis = this;
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (!App.center.isReady(0));
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        Context c = App.center.getOpenedContext(gedName);
                        if (c == null) {
                            return;
                        }
                        init(c.getGedcom(), entId);
                        updateInstances(aThis);
                    }
                });
            }
        }).start();

    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }

    private void setPanel(Entity selectedEntity) {
        if (selectedEntity == null) {
            return;
        }

        // Get panel corresponding to entity
        EntityPanel jPanelEntity = EntityPanel.findInstance(selectedEntity);

        // Remove existing panel if any
        if (panelOn != null && panelOn != jPanelEntity) {
            jPanel1.remove(panelOn);
        }

        // Check if entity is different and was in modified state
        if (previousSelectedEntity != selectedEntity) {
            if (previousSelectedEntity != null) {
                panelOn.checkIfModified();
            }
            previousSelectedEntity = selectedEntity;
            jPanelEntity.loadEntity(selectedEntity);
        }

        // Set new panel on (Netbeans requires this lenghty code below apparently)
        GroupLayout mainPanelLayout = new GroupLayout(jPanel1);
        jPanel1.setLayout(mainPanelLayout);
        mainPanelLayout.setAutoCreateContainerGaps(true);
        mainPanelLayout.setAutoCreateGaps(true);
        GroupLayout.SequentialGroup hGroup = mainPanelLayout.createSequentialGroup();
        hGroup.addComponent(jPanelEntity);
        mainPanelLayout.setHorizontalGroup(hGroup);
        GroupLayout.SequentialGroup vGroup = mainPanelLayout.createSequentialGroup();
        vGroup.addComponent(jPanelEntity);
        mainPanelLayout.setVerticalGroup(vGroup);
        jPanelEntity.setVisible(true);

        // Remember displayed panel
        panelOn = jPanelEntity;
    }


    String getDefaultFactoryMode() {return "genjfr-editor";}

    String getDefaultMode(){
        return NbPreferences.forModule(this.getClass()).get(preferredID()+".dockMode",getDefaultFactoryMode());
    }

    public void setDefaultMode(String mode) {
        NbPreferences.forModule(this.getClass()).put(preferredID()+".dockMode", mode);
    }

    public void setDefaultMode(Mode mode) {
        setDefaultMode(mode.getName());
    }

    public Mode getMode() {
        return WindowManager.getDefault().findMode(this);
    }


    @Override
    public void init(Context context) {
        if (context != null){
            init(context.getGedcom(),context.getEntity().getId());
        }
    }
}
