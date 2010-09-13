/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package genjfr.app;

import genj.nav.NavigatorViewFactory;
import genj.view.ViewFactory;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
    dtd="-//genjfr.app//Navigator//EN",
    autostore=false
)
public final class NavigatorTopComponent extends GenjViewTopComponent {

    private static final String PREFERRED_ID = "NavigatorTopComponent";
    private static NavigatorTopComponent factory;
    private static ViewFactory viewfactory = new NavigatorViewFactory();


    @Override
    String getDefaultFactoryMode() {return "genjfr-nav";}

    ViewFactory getViewFactory() {
        return viewfactory;
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
    public static synchronized NavigatorTopComponent getFactory() {
        if (factory == null) {
            factory = new NavigatorTopComponent();
        }
        return factory;
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        super.writeProperties(p);
    }

    Object readProperties(java.util.Properties p) {
        readPropertiesImpl(p);
        return this;
    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }
}
