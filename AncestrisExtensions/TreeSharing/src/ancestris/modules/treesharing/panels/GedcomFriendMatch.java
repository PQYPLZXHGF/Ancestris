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
import genj.gedcom.Entity;
import genj.gedcom.Fam;
import genj.gedcom.Gedcom;
import genj.gedcom.Indi;
import java.util.HashSet;

import java.util.Set;
import javax.swing.JInternalFrame;

/**
 *
 * @author frederic
 */
public class GedcomFriendMatch extends JInternalFrame {

    private final TreeSharingTopComponent owner;

    private SharedGedcom sharedGedcom = null;
    private AncestrisFriend ancestrisFriend = null;
    private Set<MatchData> matchedIndis = null; 
    private Set<MatchData> matchedFams = null; 
    
    
    /**
     * Creates new form GedcomFriendMatch
     */
    public GedcomFriendMatch(TreeSharingTopComponent tstc, SharedGedcom sharedGedcom, AncestrisFriend ancestrisFriend) {
        this.owner = tstc;
        this.sharedGedcom = sharedGedcom;
        this.ancestrisFriend = ancestrisFriend;
        matchedIndis = new HashSet<MatchData>();
        matchedFams = new HashSet<MatchData>();
        
        initComponents();
        setJMenuBar(null);
        updateStats();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setIconifiable(true);
        setToolTipText(org.openide.util.NbBundle.getMessage(GedcomFriendMatch.class, "GedcomFriendMatch.toolTipText")); // NOI18N
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/treesharing/resources/allowed.png"))); // NOI18N
        setVisible(false);

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 0, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/treesharing/resources/Indi.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(GedcomFriendMatch.class, "GedcomFriendMatch.jLabel1.text")); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(50, 14));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/treesharing/resources/Fam.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(GedcomFriendMatch.class, "GedcomFriendMatch.jLabel2.text")); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(50, 14));

        jButton1.setFont(new java.awt.Font("DejaVu Sans", 0, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(GedcomFriendMatch.class, "GedcomFriendMatch.jButton1.text")); // NOI18N
        jButton1.setPreferredSize(new java.awt.Dimension(50, 20));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("DejaVu Sans", 0, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButton2, org.openide.util.NbBundle.getMessage(GedcomFriendMatch.class, "GedcomFriendMatch.jButton2.text")); // NOI18N
        jButton2.setPreferredSize(new java.awt.Dimension(50, 20));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        showList(Gedcom.INDI);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        showList(Gedcom.FAM);
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables

    public SharedGedcom getSharedGedcom() {
        return sharedGedcom;
    }

    public AncestrisFriend getFriend() {
        return ancestrisFriend;
    }

    public void addEntity(Entity entity, FriendGedcomEntity friendGedcomEntity, int matchResult) {
        if (entity instanceof Indi) {
            matchedIndis.add(new MatchData(entity, friendGedcomEntity, matchResult));
            updateStats();
            return;
        }
        if (entity instanceof Fam) {
            matchedFams.add(new MatchData(entity, friendGedcomEntity, matchResult));
            updateStats();
            return;
        }
    }

    private void updateStats() {
        int nbCommonIndis = countIds(matchedIndis);
        int nbCommonFams = countIds(matchedFams);
        jButton1.setText("" + nbCommonIndis);
        jButton2.setText("" + nbCommonFams);
        jButton1.setEnabled(nbCommonIndis != 0);
        jButton2.setEnabled(nbCommonFams != 0);
    }

    private void showList(String type) {
        owner.displayResultsPanel(sharedGedcom.getGedcom().getName(), ancestrisFriend.getFriendName(), type);
    }


    
    private int countIds(Set<MatchData> matchedEntities) {
        Set<String> ret = new HashSet<String>();
        for (MatchData data : matchedEntities) {
            ret.add(data.myEntity.getId());
        }
        return ret.size();
    }

}
