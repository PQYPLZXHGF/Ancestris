/*
 * Ancestris - http://www.ancestris.org
 * 
 * Copyright 2011 Ancestris
 * 
 * Author: Daniel Andre (daniel@ancestris.org).
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */

package ancestris.modules.editors.standard;

import genjfr.app.pluginservice.GenjFrPlugin;
import java.util.Arrays;
import java.util.Collection;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author daniel
 */
@ServiceProvider(service=genjfr.app.pluginservice.PluginInterface.class)
public class EditorPlugin extends GenjFrPlugin{

    @Override
    public Collection<Class> getDefaultOpenedViews() {
        return Arrays.asList(new Class[]{EditTopComponent.class});
    }
}