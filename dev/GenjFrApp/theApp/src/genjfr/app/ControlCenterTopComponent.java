/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genjfr.app;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;
import javax.swing.JPanel;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
//import org.openide.util.ImageUtilities;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.windows.Mode;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//genjfr.app//ControlCenter//EN",
autostore = false)
public final class ControlCenterTopComponent extends GenjViewTopComponent {

    private static ControlCenterTopComponent instance;
    /** path to the icon used by the component and its open action */
//    static final String ICON_PATH = "SET/PATH/TO/ICON/HERE";
    private static final String PREFERRED_ID = "ControlCenterTopComponent";
//    private static JPanel panel = App.center;
    private static ControlCenter panel = App.center;
    private static boolean isLoaded = false;

    public ControlCenterTopComponent() {
        super();
        setPanel(panel);
        setName(NbBundle.getMessage(ControlCenterTopComponent.class, "CTL_" + PREFERRED_ID));
        setToolTipText(NbBundle.getMessage(ControlCenterTopComponent.class, "HINT_" + PREFERRED_ID));
        putClientProperty(TopComponent.PROP_CLOSING_DISABLED, Boolean.TRUE);
//	putClientProperty(TopComponent.PROP_DRAGGING_DISABLED, Boolean.TRUE);
//	putClientProperty(TopComponent.PROP_MAXIMIZATION_DISABLED, Boolean.TRUE);
//	putClientProperty(TopComponent.PROP_SLIDING_DISABLED, Boolean.TRUE);
//	putClientProperty(TopComponent.PROP_UNDOCKING_DISABLED, Boolean.TRUE);

    }

    public void open() {
        Mode m = WindowManager.getDefault().findMode("explorer");
        if (m != null) {
            m.dockInto(this);
        }
        super.open();
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
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link #findInstance}.
     */
    public static synchronized ControlCenterTopComponent getDefault() {
        if (instance == null) {
            instance = new ControlCenterTopComponent();
        }
        if (!isLoaded) {
            panel.load(new ArrayList());
            isLoaded = true;
        }
        return instance;
    }

    /**
     * Obtain the panel for this view
     */
    static public JPanel getPanel() {
        return panel;
    }

    /**
     * Obtain the ControlCenterTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized ControlCenterTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            Logger.getLogger(ControlCenterTopComponent.class.getName()).warning(
                    "Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof ControlCenterTopComponent) {
            return (ControlCenterTopComponent) win;
        }
        Logger.getLogger(ControlCenterTopComponent.class.getName()).warning(
                "There seem to be multiple components with the '" + PREFERRED_ID
                + "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }

    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ALWAYS;
    }

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    @Override
    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        nbRegistry.put(p, "gedcoms", panel.getOpenedGedcoms());
        // TODO store your settings
    }

    @Override
    Object readProperties(java.util.Properties p) {
        ControlCenterTopComponent singleton = ControlCenterTopComponent.getDefault();
        if (!isLoaded) {
            singleton.readPropertiesImpl(p);
            isLoaded = true;
        }
        return singleton;
    }

    @Override
    void readPropertiesImpl(java.util.Properties p) {
        String version = p.getProperty("version");
        panel.load(nbRegistry.get(p, "gedcoms", (Collection) null));
        // TODO read your settings according to their version
    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }
}
