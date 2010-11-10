/* * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genjfr.app;

import genj.app.Workbench;
import genj.app.WorkbenchListener;
import genj.gedcom.Context;
import genj.gedcom.Gedcom;
import genj.util.Trackable;
import genj.util.swing.Action2;
import genj.util.swing.Action2.Group;
import genj.util.swing.MenuHelper;
import genj.view.ActionProvider;
import genj.view.ActionProvider.Purpose;
import genj.view.ContextProvider;
import genj.view.SelectionSink;
import genj.view.ToolBar;
import genj.view.View;
import genj.view.ViewContext;
import genj.view.ViewFactory;
import genjfr.app.pluginservice.GenjFrPlugin;
import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.FocusManager;
import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import javax.swing.MenuSelectionManager;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import org.openide.util.lookup.ServiceProvider;
//import org.openide.util.ImageUtilities;

/**
 * Top component which displays something.
 */
// TODO: regarder en detail cette faq: http://wiki.netbeans.org/DevFaqNonSingletonTopComponents
//TODO: delete@ConvertAsProperties(
//    dtd="-//genjfr.app//ControlCenter//EN",
//    autostore=false
//)
/*
 * on sauvegarde les modes
 * voir http://netbeans.org/bugzilla/show_bug.cgi?id=179526
 * Pour que les modes (TOUS et meme ceux crees par l'utilisateur et donc anonymous)
 * il faut mettre la persistence a ALWAYS
 * or cela conduit a un nombre de fichiers assez important dans le userdir.
 * XXX: il faut donc trouver un moyen pour que la persistence des modes fonctionne.
 * Pour le moment on laisse a ALWAYS
 * Pour qu'un mode soit persistent il doit:
 * - etre non vide
 * - etre marque comme permanent mais ce n'est possible que via le fichier de descrition du mode (donc pas pour les nouveaux modes crees)
 * - Note: il n'existe pas de possibilite de mettre un mode permanent via l'api (voire DefaultModeModel)
 *
 * Les possibilites:
 * - mettre un 'dummy' TC dans les modes pour les rendre permanent
 *   => en attendant une modif des sources
 * - modifier les sources de NB
 *   => trop lourd!
 * - voir si on peut faire comme pour le lifecycle
 *   => non car le DefautModeModel n'est pas instancie via lookup
 * - autre ???
 */
@ServiceProvider(service=WorkbenchListener.class)
public class GenjViewTopComponent extends AncestrisTopComponent implements WorkbenchListener, ActionProvider {

//    static GenjViewTopComponent factory;
    /** path to the icon used by the component and its open action */
//    static final String ICON_PATH = "SET/PATH/TO/ICON/HERE";
    private static final String PREFERRED_ID = "GenjViewTopComponent";
    private static javax.swing.JPanel panel;
    private View view=null;
    AToolBar bar = null;
    private boolean isRestored = false;
  private final static Logger LOG = Logger.getLogger("genj.app");
  private final static ContextHook HOOK = new ContextHook();

    public View getView() {
        return view;
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    /**
     * Obtain the panel for this view
     */
//    static public JPanel getPanel() {
//        return panel;
//    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }

    ViewFactory getViewFactory() {
        return null;
    }

    public Image getImageIcon(){
        return getViewFactory().getImage().getImage();
    }

    public boolean createPanel(){
        if (getViewFactory() == null)
           return false;
        // create the view
        view = getViewFactory().createView();
        setPanel(view);
        view.setContext(getContext(), true);
        setToolBar(view);
        return true;
    }

    public void setName() {
        setName(getViewFactory().getTitle());
    }
    public void setToolTipText(){
        setToolTipText(getViewFactory().getTitle());
    }

    // ToolBar support
    private void setToolBar(View view) {

        if (bar != null) {
            return;
        }
        bar = new AToolBar();

        bar.beginUpdate();
        view.populate(bar);
        bar.endUpdate();
//        if (EnvironmentChecker.getProperty(this, "genj.view.toolbarnoproblem", null, "checking for switch to not use glue in toolbar")!=null)
//          bar.add(Box.createGlue());
//        else
//            bar.addSeparator();

        //    add(bar, viewHandle.getRegistry().get("toolbar", BorderLayout.WEST));
        if ((bar != null) && (bar.getToolBar() != null)){
            add(bar.getToolBar(), genj.util.Registry.get(view).get("toolbar",BorderLayout.WEST));;
        }
    // done
    }

  /**
   * When adding components we fix a Toolbar's sub-component's orientation
   */
    @Override
  protected void addImpl(Component comp, Object constraints, int index) {
    // restore toolbar orientation?
    if ((bar!=null) && (comp==bar.getToolBar())) {
      // remember
      genj.util.Registry.get(view).put("toolbar", constraints.toString());
      // find orientation
      int orientation = SwingConstants.HORIZONTAL;
      if (BorderLayout.WEST.equals(constraints)||BorderLayout.EAST.equals(constraints))
        orientation = SwingConstants.VERTICAL;
      // fix orientation for toolbar
      bar.setOrientation(orientation);
      // toolbar o.k.
    }
    // go ahead with super
    super.addImpl(comp, constraints, index);
    // done
  }

    public void createActions(Context context, Purpose purpose, Group into) {
        // Delegate
        if (!(view instanceof ActionProvider))
            return;
        if (getContext().getGedcom().equals(context.getGedcom()))
            ((ActionProvider)view).createActions(context, purpose, into);
    }

    private class AToolBar implements ToolBar{
        AtomicBoolean notEmpty = new AtomicBoolean(false);
        JToolBar bar = new JToolBar();

     public void add(Action action) {
        bar.add(action);
        bar.setVisible(true);
        notEmpty.set(true);
     }

        public void add(JComponent component) {
        bar.add(component);
        bar.setVisible(true);
        component.setFocusable(false);
        notEmpty.set(true);
      }


      public void addSeparator() {
        bar.addSeparator();
        bar.setVisible(true);
        notEmpty.set(true);
      }

      public JToolBar getToolBar() {
          return (notEmpty.get())?bar:null;
      }

        private void setOrientation(int orientation) {
            bar.setOrientation(orientation);
        }

    public void beginUpdate() {
      notEmpty.set(false);
      bar.removeAll();
      bar.setVisible(false);
//      bar.validate();
    }
    public void endUpdate() {
    }

    }

    // Context menu support

  /**
   * Our hook into keyboard and mouse operated context changes / menu
   */
  private static class ContextHook extends Action2 implements AWTEventListener {

    /** constructor */
    private ContextHook() {
      try {
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
          public Void run() {
            Toolkit.getDefaultToolkit().addAWTEventListener(ContextHook.this, AWTEvent.MOUSE_EVENT_MASK);
            return null;
          }
        });
      } catch (Throwable t) {
        LOG.log(Level.WARNING, "Cannot install ContextHook", t);
      }
    }

    /**
     * Find workbench for given component
     * @return workbench or null
     */

    /**
     * A Key press initiation of the context menu
     */
    public void actionPerformed(ActionEvent event) {
      // only for jcomponents with focus
      Component focus = FocusManager.getCurrentManager().getFocusOwner();
      if (!(focus instanceof JComponent))
        return;
      // look for ContextProvider and show menu if appropriate
      ViewContext context = new ContextProvider.Lookup(focus).getContext();
      if (context != null) {
        JPopupMenu popup = getContextMenu(context);
        if (popup != null)
          popup.show(focus, 0, 0);
      }
      // done
    }

    /**
     * A mouse click initiation of the context menu
     */
    public void eventDispatched(AWTEvent event) {

      // a mouse popup/click event?
      if (!(event instanceof MouseEvent))
        return;
      final MouseEvent me = (MouseEvent) event;
      if (!(me.isPopupTrigger() || me.getID() == MouseEvent.MOUSE_CLICKED))
        return;

      // NM 20080130 do the component/context calculation in another event to
      // allow everyone to catch up
      // Peter reported that the context menu is the wrong one as
      // PropertyTreeWidget
      // changes the selection on mouse clicks (following right-clicks).
      // It might be that eventDispatched() is called before the mouse click is
      // propagated to the
      // component thus calculates the menu before the selection changes.
      // So I'm trying now to show the popup this in a later event to make sure
      // everyone caught up to the event

      // find workbench now (popup menu might go away after this method call)
//      final Workbench workbench = getWorkbench((Component)me.getSource());
//      if (workbench==null)
//        return;

      // find context at point
      final Component source = SwingUtilities.getDeepestComponentAt(me.getComponent(), me.getX(), me.getY());
      final ContextProvider.Lookup lookup = new ContextProvider.Lookup(source);
      if (lookup.getContext()==null)
        return;

      final Point point = SwingUtilities.convertPoint(me.getComponent(), me.getX(), me.getY(), me.getComponent());

      SwingUtilities.invokeLater(new Runnable() {
        public void run() {

          // a double-click on provider?
          if (lookup.getProvider() == source
              && me.getButton() == MouseEvent.BUTTON1
              && me.getID() == MouseEvent.MOUSE_CLICKED
              && me.getClickCount() == 2) {
            SelectionSink.Dispatcher.fireSelection(me.getComponent(), lookup.getContext(), true);
            return;
          }

          // a popup?
          if (me.isPopupTrigger()) {

            // cancel any menu
            MenuSelectionManager.defaultManager().clearSelectedPath();

            // show context menu
            JPopupMenu popup = getContextMenu(lookup.getContext());
            if (popup != null)
              popup.show(me.getComponent(), point.x, point.y);

          }
        }
      });

      // done
    }

    /**
     * Create a popup menu for given context
     */
    private JPopupMenu getContextMenu(ViewContext context) {

      // make sure context is valid
      if (context==null)
        return null;

      // make sure any existing popup is cleared
      MenuSelectionManager.defaultManager().clearSelectedPath();

      // create a popup
      MenuHelper mh = new MenuHelper();
      JPopupMenu popup = mh.createPopup();

      // popup local actions?
      mh.createItems(context.getActions());

      // get and merge all actions
      List<Action2> groups = new ArrayList<Action2>(8);
      List<Action2> singles = new ArrayList<Action2>(8);
      Map<Action2.Group,Action2.Group> lookup = new HashMap<Action2.Group,Action2.Group>();

      for (Action2 action : getProvidedActions(context)) {
        if (action instanceof Action2.Group) {
          Action2.Group group = lookup.get(action);
          if (group!=null) {
            group.add(new ActionProvider.SeparatorAction());
            group.addAll((Action2.Group)action);
          } else {
            lookup.put((Action2.Group)action, (Action2.Group)action);
            groups.add((Action2.Group)action);
          }
        } else {
          singles.add(action);
        }
      }

      // add to menu
      mh.createItems(groups);
      mh.createItems(singles);

      // done
      return popup;
    }

      private Action2.Group getProvidedActions(Context context) {
      Action2.Group group = new Action2.Group("");
      // ask the action providers
        for (ActionProvider provider : (List<ActionProvider>) GenjFrPlugin.lookupAll(ActionProvider.class) )
        provider.createActions(context, Purpose.CONTEXT, group);
      // done
      return group;
    }


  } //ContextHook

    // Workbench listener support

    public void selectionChanged(Workbench workbench, Context context, boolean isActionPerformed) {
    // appropriate?
    if (context.getGedcom()!= getContext().getGedcom()) {
      LOG.log(Level.FINER, "context selection on unknown gedcom", new Throwable());
      return;
    }

    // already known?
    if (!isActionPerformed && getContext().equals(context))
      return;

    LOG.finer("fireSelection("+context+","+isActionPerformed+")");

    // remember
    setContext(context);

    if (context.getGedcom()!=null)
      App.getRegistry(context.getGedcom()).put(context.getGedcom().getName()+".context", context.toString());

        if (view != null)
          view.setContext(context, isActionPerformed);
  }

    public void processStarted(Workbench workbench, Trackable process) {
    }

    public void processStopped(Workbench workbench, Trackable process) {
    }

    public void commitRequested(Workbench workbench, Context context) {
        if (context.getGedcom()!= getContext().getGedcom()) {
          LOG.log(Level.FINER, "context selection on unknown gedcom", new Throwable());
          return;
        }
        if (view != null)
            view.commit();
    }

    public void workbenchClosing(Workbench workbench) {
        if (view != null)
            view.closing();
    }

    public void gedcomClosed(Workbench workbench, Gedcom gedcom) {
    }

    public void gedcomOpened(Workbench workbench, Gedcom gedcom) {
    }

    public void viewRestored(Workbench workbench, View view) {
    }

    public void viewOpened(Workbench workbench, View view) {
    }

    public void viewClosed(Workbench workbench, View view) {
    }

}
