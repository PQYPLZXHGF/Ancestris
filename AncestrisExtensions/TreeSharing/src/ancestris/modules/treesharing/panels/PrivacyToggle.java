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

import ancestris.modules.treesharing.TreeSharingTopComponent;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import org.openide.util.NbBundle;

/**
 *
 * @author frederic
 */
public class PrivacyToggle extends JToggleButton {

    private final TreeSharingTopComponent owner;
    
    private final ImageIcon PRIVACY_ON  = new ImageIcon(getClass().getResource("/ancestris/modules/treesharing/resources/privacyOn.png"));
    private final ImageIcon PRIVACY_OFF = new ImageIcon(getClass().getResource("/ancestris/modules/treesharing/resources/privacyOff.png"));

    
    /**
     * Creates new form PrivacyToggle
     */
    public PrivacyToggle(TreeSharingTopComponent tstc, boolean preferredPrivacy) {
        this.owner = tstc;
        initComponents();
        setPrivacy(preferredPrivacy);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setSelected(true);
        addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formActionPerformed(evt);
            }
        });
    }// </editor-fold>//GEN-END:initComponents

    private void formActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_formActionPerformed
        setPrivacy(isSelected());
    }//GEN-LAST:event_formActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    public final void setPrivacy(boolean privacy) {
        setSelected(privacy);
        if (isSelected()) {
            setIcon(PRIVACY_ON);
            setToolTipText(NbBundle.getMessage(PrivacyToggle.class, "TIP_PrivacyOn"));
        } else {
            setIcon(PRIVACY_OFF);
            setToolTipText(NbBundle.getMessage(PrivacyToggle.class, "TIP_PrivacyOff"));
        }
        owner.dispatchPrivacy(privacy);
    }
}
