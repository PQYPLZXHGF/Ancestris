/*
 * Ancestris - http://www.ancestris.org
 *
 * Copyright 2010 Ancestris
 *
 * Author: Daniel Andre (daniel@ancestris.org).
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */

package genj.util;

import java.util.prefs.PreferenceChangeListener;

/**
 *
 * @author daniel.andre
 */
public interface IRegistryStorage {
    public String get(String key, String def);

    public void put(String key, String value);

    public void remove(String key);

    public void persist();

    public void addPreferenceChangeListener(PreferenceChangeListener pcl);
}
