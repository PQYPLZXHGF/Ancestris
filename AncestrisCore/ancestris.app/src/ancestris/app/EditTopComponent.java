/*                         
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ancestris.app;

import ancestris.view.AncestrisDockModes;
import ancestris.view.AncestrisTopComponent;
import ancestris.view.AncestrisViewInterface;
import genj.edit.EditView;
import genj.edit.EditViewFactory;
import genj.gedcom.Context;
import genj.gedcom.Entity;
import genj.gedcom.Property;
import genj.view.ViewFactory;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.RetainLocation;
import org.openide.windows.TopComponent;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//ancestris.app//Edit//EN",
autostore = false)
@RetainLocation(AncestrisDockModes.EDITOR)
@ServiceProvider(service=AncestrisViewInterface.class)
public final class EditTopComponent extends GenjViewTopComponent implements TopComponent.Cloneable{

    private static final String PREFERRED_ID = "EditTopComponent";
    private static EditTopComponent factory;
    private ViewFactory viewfactory = new EditViewFactory();  // should not be static

    public ViewFactory getViewFactory() {
        return viewfactory;
    }

    @Override
    public String getDefaultFactoryMode() {return AncestrisDockModes.EDITOR;}

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
    public static synchronized EditTopComponent getFactory() {
        if (factory == null) {
            factory = new EditTopComponent();
        }
        return factory;
    }

    public TopComponent cloneComponent() {
        if (getContext() == null)
            return null;

        AncestrisTopComponent topComponent = new EditTopComponent();
        topComponent.init(getContext());
            return topComponent;
    }

    @Override
    public void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        super.writeProperties(p);
    }


    public Object readProperties(java.util.Properties p) {
        super.readProperties(p);
        return this;
    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }

    /**
     * @deprecated use getContext().getEntity()
     * @return
     */
    @Deprecated
    public Entity getCurrentEntity() {
        return getContext().getEntity();
}

    public void setCurrentEntity(Property property) {
        Context vc = new Context(property);
        ((EditView)getView()).setContext(vc,true);
    }

}
