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

import javax.swing.JInternalFrame;

/**
 *
 * Represents Ancestris members with entities matching
 * 
 * @author frederic
 */
public class AncestrisFriend extends JInternalFrame {

    
    private final String name;
    private final String access;

    
    /**
     * Creates new form AncestrisFriend
     */
    public AncestrisFriend(String name, String access) {
        super(name);
        this.name = name;
        this.access = access;
        initComponents();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();

        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setIconifiable(true);
        setToolTipText(org.openide.util.NbBundle.getMessage(AncestrisFriend.class, "AncestrisFriend.Form.toolTipText")); // NOI18N
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/treesharing/resources/friend.png"))); // NOI18N
        setMinimumSize(new java.awt.Dimension(150, 100));
        setPreferredSize(new java.awt.Dimension(150, 100));
        setVisible(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 60, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables

    public String getFriendName() {
        return name;
    }
    
}
