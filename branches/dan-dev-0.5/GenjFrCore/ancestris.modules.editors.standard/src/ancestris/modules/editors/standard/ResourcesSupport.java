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

import genj.util.swing.ImageIcon;
import org.openide.util.NbBundle;

/**
 *
 * @author daniel
 */
public class ResourcesSupport {
    static ImageIcon icon = new ImageIcon(ResourcesSupport.class,"editeur_familial");

    public static String getTitle(String bundleKey) {
        return NbBundle.getBundle(ResourcesSupport.class).getString(bundleKey+".title");
    }
}
