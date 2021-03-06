/*                         
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ancestris.modules.editors.gedcom;

import ancestris.view.GenjViewTopComponent;
import ancestris.view.AncestrisDockModes;
import ancestris.view.AncestrisTopComponent;
import ancestris.view.AncestrisViewInterface;
import genj.edit.EditViewFactory;
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
@ServiceProvider(service = AncestrisViewInterface.class)
public final class EditTopComponent extends GenjViewTopComponent implements TopComponent.Cloneable {

    private static final String PREFERRED_ID = "EditTopComponent";
    private static EditTopComponent factory;
    private ViewFactory viewfactory = new EditViewFactory();  // should not be static

    public ViewFactory getViewFactory() {
        return viewfactory;
    }

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
        if (getContext() == null) {
            return null;
        }

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

    public void readProperties(java.util.Properties p) {
        super.readProperties(p);
    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }
}
