package ancestris.modules.gedcom.searchduplicates;

import static ancestris.modules.gedcom.searchduplicates.Bundle.CheckDuplicatesVisualPanel2_title;
import genj.gedcom.Gedcom;
import java.util.ArrayList;
import javax.swing.JPanel;
import org.openide.util.NbBundle;

@NbBundle.Messages("CheckDuplicatesVisualPanel2.title=Entities selection")
public final class SearchDuplicatesVisualPanel2 extends JPanel {

    private ArrayList<String> selectedEntities = new ArrayList<String>() {

        {
            add(Gedcom.INDI);
        }
    };

    /**
     * Creates new form SearchDuplicatesVisualPanel2
     */
    public SearchDuplicatesVisualPanel2() {
        initComponents();
    }

    @Override
    public String getName() {
        return CheckDuplicatesVisualPanel2_title();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        emptyPanel = new javax.swing.JPanel();
        sourcesOptionPanel1 = new javax.swing.JPanel();
        submittersOptionPanel = new javax.swing.JPanel();
        repositoriesOptionPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        repositoriesCheckBox = new javax.swing.JCheckBox();
        familiesCheckBox = new javax.swing.JCheckBox();
        sourcesCheckBox = new javax.swing.JCheckBox();
        individualsCheckBox = new javax.swing.JCheckBox();
        submittersCheckBox = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();

        javax.swing.GroupLayout emptyPanelLayout = new javax.swing.GroupLayout(emptyPanel);
        emptyPanel.setLayout(emptyPanelLayout);
        emptyPanelLayout.setHorizontalGroup(
            emptyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 275, Short.MAX_VALUE)
        );
        emptyPanelLayout.setVerticalGroup(
            emptyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 166, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout sourcesOptionPanel1Layout = new javax.swing.GroupLayout(sourcesOptionPanel1);
        sourcesOptionPanel1.setLayout(sourcesOptionPanel1Layout);
        sourcesOptionPanel1Layout.setHorizontalGroup(
            sourcesOptionPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 275, Short.MAX_VALUE)
        );
        sourcesOptionPanel1Layout.setVerticalGroup(
            sourcesOptionPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 166, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout submittersOptionPanelLayout = new javax.swing.GroupLayout(submittersOptionPanel);
        submittersOptionPanel.setLayout(submittersOptionPanelLayout);
        submittersOptionPanelLayout.setHorizontalGroup(
            submittersOptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 275, Short.MAX_VALUE)
        );
        submittersOptionPanelLayout.setVerticalGroup(
            submittersOptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 166, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout repositoriesOptionPanelLayout = new javax.swing.GroupLayout(repositoriesOptionPanel);
        repositoriesOptionPanel.setLayout(repositoriesOptionPanelLayout);
        repositoriesOptionPanelLayout.setHorizontalGroup(
            repositoriesOptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 275, Short.MAX_VALUE)
        );
        repositoriesOptionPanelLayout.setVerticalGroup(
            repositoriesOptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 166, Short.MAX_VALUE)
        );

        setPreferredSize(new java.awt.Dimension(400, 312));

        org.openide.awt.Mnemonics.setLocalizedText(repositoriesCheckBox, org.openide.util.NbBundle.getMessage(SearchDuplicatesVisualPanel2.class, "SearchDuplicatesVisualPanel2.repositoriesCheckBox.text")); // NOI18N
        repositoriesCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repositoriesCheckBoxActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(familiesCheckBox, org.openide.util.NbBundle.getMessage(SearchDuplicatesVisualPanel2.class, "SearchDuplicatesVisualPanel2.familiesCheckBox.text")); // NOI18N
        familiesCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                familiesCheckBoxActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(sourcesCheckBox, org.openide.util.NbBundle.getMessage(SearchDuplicatesVisualPanel2.class, "SearchDuplicatesVisualPanel2.sourcesCheckBox.text")); // NOI18N
        sourcesCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourcesCheckBoxActionPerformed(evt);
            }
        });

        individualsCheckBox.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(individualsCheckBox, org.openide.util.NbBundle.getMessage(SearchDuplicatesVisualPanel2.class, "SearchDuplicatesVisualPanel2.individualsCheckBox.text")); // NOI18N
        individualsCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                individualsCheckBoxActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(submittersCheckBox, org.openide.util.NbBundle.getMessage(SearchDuplicatesVisualPanel2.class, "SearchDuplicatesVisualPanel2.submittersCheckBox.text")); // NOI18N
        submittersCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submittersCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(individualsCheckBox)
                    .addComponent(repositoriesCheckBox))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(submittersCheckBox)
                    .addComponent(familiesCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sourcesCheckBox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(individualsCheckBox)
                    .addComponent(familiesCheckBox)
                    .addComponent(sourcesCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submittersCheckBox)
                    .addComponent(repositoriesCheckBox))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jEditorPane1.setContentType("text/html"); // NOI18N
        jEditorPane1.setText(org.openide.util.NbBundle.getMessage(SearchDuplicatesVisualPanel2.class, "SearchDuplicatesVisualPanel2.jEditorPane1.text")); // NOI18N
        jEditorPane1.setDisabledTextColor(new java.awt.Color(32, 32, 32));
        jEditorPane1.setEnabled(false);
        jEditorPane1.setFocusable(false);
        jEditorPane1.setPreferredSize(new java.awt.Dimension(400, 312));
        jEditorPane1.setRequestFocusEnabled(false);
        jScrollPane1.setViewportView(jEditorPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 103, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void individualsCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_individualsCheckBoxActionPerformed
        if (individualsCheckBox.isSelected()) {
            getSelectedEntities().add(Gedcom.INDI);
        } else {
            getSelectedEntities().remove(Gedcom.INDI);
        }
    }//GEN-LAST:event_individualsCheckBoxActionPerformed

    private void familiesCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_familiesCheckBoxActionPerformed
        if (familiesCheckBox.isSelected()) {
            getSelectedEntities().add(Gedcom.FAM);
        } else {
            getSelectedEntities().remove(Gedcom.FAM);
        }
    }//GEN-LAST:event_familiesCheckBoxActionPerformed

    private void submittersCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submittersCheckBoxActionPerformed
        if (submittersCheckBox.isSelected()) {
            getSelectedEntities().add(Gedcom.SUBM);
        } else {
            getSelectedEntities().remove(Gedcom.SUBM);
        }
    }//GEN-LAST:event_submittersCheckBoxActionPerformed

    private void sourcesCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourcesCheckBoxActionPerformed
        if (sourcesCheckBox.isSelected()) {
            getSelectedEntities().add(Gedcom.SOUR);
        } else {
            getSelectedEntities().remove(Gedcom.SOUR);
        }
    }//GEN-LAST:event_sourcesCheckBoxActionPerformed

    private void repositoriesCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repositoriesCheckBoxActionPerformed
        if (repositoriesCheckBox.isSelected()) {
            getSelectedEntities().add(Gedcom.REPO);
        } else {
            getSelectedEntities().remove(Gedcom.REPO);
        }
    }//GEN-LAST:event_repositoriesCheckBoxActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel emptyPanel;
    private javax.swing.JCheckBox familiesCheckBox;
    private javax.swing.JCheckBox individualsCheckBox;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox repositoriesCheckBox;
    private javax.swing.JPanel repositoriesOptionPanel;
    private javax.swing.JCheckBox sourcesCheckBox;
    private javax.swing.JPanel sourcesOptionPanel1;
    private javax.swing.JCheckBox submittersCheckBox;
    private javax.swing.JPanel submittersOptionPanel;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the selectedEntities
     */
    public ArrayList<String> getSelectedEntities() {
        return selectedEntities;
    }
}
