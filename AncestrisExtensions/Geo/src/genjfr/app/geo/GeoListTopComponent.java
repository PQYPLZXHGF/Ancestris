/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genjfr.app.geo;

import genj.gedcom.Context;
import ancestris.view.AncestrisTopComponent;
import ancestris.view.AncestrisViewInterface;
import ancestris.core.pluginservice.AncestrisPlugin;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import javax.swing.tree.TreeSelectionModel;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.view.BeanTreeView;
import org.openide.nodes.Node;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.WindowManager;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//genjfr.app.geo//GeoList//EN",
autostore = false)
@ServiceProvider(service=AncestrisViewInterface.class)
public final class GeoListTopComponent extends AncestrisTopComponent implements ExplorerManager.Provider, GeoPlacesListener, PropertyChangeListener {

    /** path to the icon used by the component and its open action */
    static final String ICON_PATH = "genjfr/app/geo/list.png";
    private static final String PREFERRED_ID = "GeoListTopComponent";
    private GeoNodeObject[] nodes = null;
    private ExplorerManager mgr = new ExplorerManager();
    //
//    private Gedcom gedcom = null;
    private GeoPlacesList gpl = null;
    private boolean isInitialised = false;

    public GeoListTopComponent() {
        super();
        //super.setDefaultMode("anonymousMode_7");
    }

    @Override
    public Image getImageIcon() {
        return ImageUtilities.loadImage(ICON_PATH, true);
    }

    @Override
    public void setName() {
        setName(NbBundle.getMessage(GeoListTopComponent.class, "CTL_GeoListTopComponent"));
    }

    @Override
    public void setToolTipText() {
        setToolTipText(NbBundle.getMessage(GeoListTopComponent.class, "HINT_GeoListTopComponent")); 
    }

    @Override
    public boolean createPanel() {
        // TopComponent window parameters
        initComponents();

        // Set mono selection and allow substring quick search (requires nodes to have a valid getName())
        ((BeanTreeView) jScrollPane1).setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        ((MyBeanTreeView) jScrollPane1).setUseSubstringInQuickSearch(true);

        // Init tree
        initTree();
        isInitialised = true;
        return true;
    }

    private void initTree() {
        // Launch search for locations and set listener
        gpl = GeoPlacesList.getInstance(getGedcom());
        if (gpl.getPlaces() == null) {
            gpl.launchPlacesSearch();
        } else {
            geoPlacesChanged(gpl, "gedcom");
        }
        gpl.addGeoPlacesListener(this);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new MyBeanTreeView();

        setLayout(new java.awt.BorderLayout());
        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        // Set lookup to listen to selection
        mgr.addPropertyChangeListener(this);
    }

    @Override
    public void componentClosed() {
        if (getGedcom() != null) {
            GeoPlacesList gpl = GeoPlacesList.getInstance(getGedcom());
            gpl.removeGeoPlacesListener(this);
        }
        mgr.removePropertyChangeListener(this);
        AncestrisPlugin.unregister(this);
    }

    public void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        if (getGedcom() != null) {
            p.setProperty("gedcom", getGedcom().getOrigin().toString());
        }
    }

    public void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        final String gedName = p.getProperty("gedcom");
        if (gedName == null) {
            return;
        }
        waitStartup(gedName);
    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }

    public ExplorerManager getExplorerManager() {
        return mgr;
    }

    public boolean isInitialised() {
        return isInitialised;
    }

    public void geoPlacesChanged(GeoPlacesList gpl, String change) {
        if (change.equals("cood")) {
        } else if (change.equals("name")) {
        } else if (change.equals("gedcom")) {
            String selectedNode = getSelectedNode();
            nodes = gpl.getPlaces();
            mgr.setRootContext(new GeoNode(gpl));
            ((BeanTreeView) jScrollPane1).setRootVisible(false);
            selectNode(selectedNode);
            jScrollPane1.repaint();
            WindowManager.getDefault().invokeWhenUIReady(new Runnable() {

                public void run() {
                    jScrollPane1.updateUI();
                }
            });

        }
    }

    /**
     * Case of node selection
     * @param pce
     */
    public void propertyChange(PropertyChangeEvent pce) {
        // actually do nothing in case of simple click
        if (true) {
            return;
        }
        // normal code now
        if (mgr != null) {
            Node[] geonodes = mgr.getSelectedNodes();
            if (geonodes.length > 0) {
                // get geonode selected
                GeoNodeObject obj = geonodes[0].getLookup().lookup(GeoNodeObject.class);
                // get map top component
                GeoMapTopComponent theMap = null;
                for (TopComponent tc : WindowManager.getDefault().getRegistry().getOpened()) {
                    if (tc instanceof GeoMapTopComponent) {
                        GeoMapTopComponent gmtc = (GeoMapTopComponent) tc;
                        if (gmtc.getGedcom() == getGedcom()) {
                            theMap = gmtc;
                            break;
                        }
                    }
                }
                // display geonode on the map
                if (theMap != null) {
                    theMap.requestActive();
                    theMap.ShowMarker(obj);
                }
            }
        }
    }

    void ShowLocation(GeoNodeObject gno) {
        if (mgr == null || gno == null) {
            return;
        }
        Node rootNode = mgr.getRootContext();
        Node[] kids = rootNode.getChildren().getNodes();
        for (int i = 0; i < kids.length; i++) {
            final Node node = kids[i];
            ((BeanTreeView) jScrollPane1).collapseNode(node);
            GeoNodeObject obj = node.getLookup().lookup(GeoNodeObject.class);
            if (obj == gno) {
                ((MyBeanTreeView) jScrollPane1).setScrollOnExpand(true);
                ((MyBeanTreeView) jScrollPane1).expandNode(node);
                try {
                    mgr.setSelectedNodes(new Node[]{node});
                } catch (PropertyVetoException ex) {
                    // nothing
                }
            }
        }
    }

    private String getSelectedNode() {

        // Get selected node
        Node[] geonodes = mgr.getSelectedNodes();
        if (geonodes.length == 0) {
            return "";
        }
        GeoNodeObject obj = geonodes[0].getLookup().lookup(GeoNodeObject.class);
        return (obj != null) ? obj.getPlaceAsLongString() : "";
    }

    private void selectNode(String selectedNode) {
        // scan nodes to find the one
        if (selectedNode.isEmpty()) {
            return;
        }
        Node[] scannedNodes = mgr.getRootContext().getChildren().getNodes();
        for (int i = 0; i < scannedNodes.length; i++) {
            Node node = scannedNodes[i];
            GeoNodeObject obj = node.getLookup().lookup(GeoNodeObject.class);
            if (obj.getPlaceAsLongString().equals(selectedNode)) {
                try {
                    mgr.setSelectedNodes(new Node[]{node});
                } catch (PropertyVetoException ex) {
                    // nothing
                }
            }
        }

    }

    /**
     * Subclass BeanTreeView to be able to use the setScrollOnExpand function (tree is protected)
     */
    private static class MyBeanTreeView extends BeanTreeView {

        public boolean getScrollOnExpand() {
            return tree.getScrollsOnExpand();
        }

        public void setScrollOnExpand(boolean scroll) {
            this.tree.setScrollsOnExpand(scroll);
        }
    }
}
