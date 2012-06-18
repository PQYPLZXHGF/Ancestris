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
package ancestris.modules.beans;

import genj.edit.beans.DateBean;
import java.io.Serializable;

/**
 *
 * @author daniel
 */
public class ADateBean extends DateBean implements Serializable {

    private static final String TAG = "DATE";

    public ADateBean() {
        super();
    }

    @Override
    public String getTag() {
        return TAG;
    }
}
