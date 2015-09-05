/*
 * Ancestris - http://www.ancestris.org
 * 
 * Copyright 2015 Ancestris
 * 
 * Author: Frédéric Lapeyre (frederic@ancestris.org).
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */

package ancestris.modules.treesharing.panels;

import ancestris.core.actions.AbstractAncestrisAction;
import ancestris.modules.treesharing.TreeSharingTopComponent;
import java.awt.event.ActionEvent;
import org.openide.util.NbBundle;

/**
 *
 * @author frederic
 */
public class DisplayStatsAction extends AbstractAncestrisAction {

    private final TreeSharingTopComponent owner;
    
    /**
     * Constructor
     */
    public DisplayStatsAction(TreeSharingTopComponent tstc) {
        this.owner = tstc;
        setImage(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/treesharing/resources/connectionstats.png")));
        setTip(NbBundle.getMessage(RearrangeAction.class, "TIP_DisplayStats"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        owner.displayStats();
    }
  
}
