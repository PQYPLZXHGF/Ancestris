package ancestris.modules.gedcom.searchduplicates;

import static ancestris.modules.gedcom.searchduplicates.Bundle.CheckDuplicatesVisualPanel2_title;
import ancestris.modules.gedcom.utilities.matchers.*;
import genj.gedcom.Gedcom;
import java.util.ArrayList;
import java.util.TreeMap;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;
import org.openide.util.NbBundle;

@NbBundle.Messages("CheckDuplicatesVisualPanel2.title=Entities selection")
public final class SearchDuplicatesVisualPanel2 extends JPanel {

    private ArrayList<String> selectedEntities = new ArrayList<String>() {

        {
            add(Gedcom.INDI);
        }
    };
    private TreeMap<String, ? extends Options> selectedOptions = new TreeMap() {

        {
            put(Gedcom.INDI, new IndiMatcherOptions());
            put(Gedcom.FAM, new FamMatcherOptions());
            put(Gedcom.NOTE, new NoteMatcherOptions());
            put(Gedcom.REPO, new RepositoryMatcherOptions());
            put(Gedcom.SOUR, new SourceMatcherOptions());
            put(Gedcom.SUBM, new SubmitterMatcherOptions());
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
        jPanel2 = new javax.swing.JPanel();
        individualsCheckBox = new javax.swing.JCheckBox();
        familiesCheckBox = new javax.swing.JCheckBox();
        submittersCheckBox = new javax.swing.JCheckBox();
        repositoriesCheckBox = new javax.swing.JCheckBox();
        sourcesCheckBox = new javax.swing.JCheckBox();
        jPanel7 = new javax.swing.JPanel();
        individualsOptionPanel = new javax.swing.JPanel();
        indiMaximumDateIntervalLabel = new javax.swing.JLabel();
        indiMaxDateIntervalSpinner = new javax.swing.JSpinner(new SpinnerNumberModel(365,0,3650,1));
        indiEmptyValuesValidRadioButton = new javax.swing.JRadioButton();
        indiAllFirstNamesRadioButton = new javax.swing.JRadioButton();
        indiCheckAllNamesRadioButton = new javax.swing.JRadioButton();
        indiCheckFamiliesRadioButton = new javax.swing.JRadioButton();
        familiesOptionPanel = new javax.swing.JPanel();
        famMaxDateIntervalLabel = new javax.swing.JLabel();
        famMaxDateIntervalSpinner = new javax.swing.JSpinner(new SpinnerNumberModel(365,0,3650,1));
        famEmptyValuesValidRadioButton = new javax.swing.JRadioButton();

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

        individualsCheckBox.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(individualsCheckBox, org.openide.util.NbBundle.getMessage(SearchDuplicatesVisualPanel2.class, "SearchDuplicatesVisualPanel2.individualsCheckBox.text")); // NOI18N
        individualsCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                individualsCheckBoxActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(familiesCheckBox, org.openide.util.NbBundle.getMessage(SearchDuplicatesVisualPanel2.class, "SearchDuplicatesVisualPanel2.familiesCheckBox.text")); // NOI18N
        familiesCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                familiesCheckBoxActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(submittersCheckBox, org.openide.util.NbBundle.getMessage(SearchDuplicatesVisualPanel2.class, "SearchDuplicatesVisualPanel2.submittersCheckBox.text")); // NOI18N
        submittersCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submittersCheckBoxActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(repositoriesCheckBox, org.openide.util.NbBundle.getMessage(SearchDuplicatesVisualPanel2.class, "SearchDuplicatesVisualPanel2.repositoriesCheckBox.text")); // NOI18N
        repositoriesCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repositoriesCheckBoxActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(sourcesCheckBox, org.openide.util.NbBundle.getMessage(SearchDuplicatesVisualPanel2.class, "SearchDuplicatesVisualPanel2.sourcesCheckBox.text")); // NOI18N
        sourcesCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourcesCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(individualsCheckBox)
                    .addComponent(familiesCheckBox)
                    .addComponent(submittersCheckBox)
                    .addComponent(sourcesCheckBox)
                    .addComponent(repositoriesCheckBox))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(individualsCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(familiesCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(submittersCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sourcesCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(repositoriesCheckBox))
        );

        jPanel7.setLayout(new javax.swing.BoxLayout(jPanel7, javax.swing.BoxLayout.Y_AXIS));

        individualsOptionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(SearchDuplicatesVisualPanel2.class, "SearchDuplicatesVisualPanel2.individualsOptionPanel.border.title"))); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(indiMaximumDateIntervalLabel, org.openide.util.NbBundle.getMessage(SearchDuplicatesVisualPanel2.class, "SearchDuplicatesVisualPanel2.indiMaximumDateIntervalLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(indiEmptyValuesValidRadioButton, org.openide.util.NbBundle.getMessage(SearchDuplicatesVisualPanel2.class, "SearchDuplicatesVisualPanel2.indiEmptyValuesValidRadioButton.text")); // NOI18N

        indiAllFirstNamesRadioButton.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(indiAllFirstNamesRadioButton, org.openide.util.NbBundle.getMessage(SearchDuplicatesVisualPanel2.class, "SearchDuplicatesVisualPanel2.indiAllFirstNamesRadioButton.text")); // NOI18N

        indiCheckAllNamesRadioButton.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(indiCheckAllNamesRadioButton, org.openide.util.NbBundle.getMessage(SearchDuplicatesVisualPanel2.class, "SearchDuplicatesVisualPanel2.indiCheckAllNamesRadioButton.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(indiCheckFamiliesRadioButton, org.openide.util.NbBundle.getMessage(SearchDuplicatesVisualPanel2.class, "SearchDuplicatesVisualPanel2.indiCheckFamiliesRadioButton.text")); // NOI18N

        javax.swing.GroupLayout individualsOptionPanelLayout = new javax.swing.GroupLayout(individualsOptionPanel);
        individualsOptionPanel.setLayout(individualsOptionPanelLayout);
        individualsOptionPanelLayout.setHorizontalGroup(
            individualsOptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(individualsOptionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(individualsOptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(individualsOptionPanelLayout.createSequentialGroup()
                        .addComponent(indiMaximumDateIntervalLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(indiMaxDateIntervalSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(indiEmptyValuesValidRadioButton)
                    .addComponent(indiCheckAllNamesRadioButton)
                    .addComponent(indiAllFirstNamesRadioButton)
                    .addComponent(indiCheckFamiliesRadioButton))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        individualsOptionPanelLayout.setVerticalGroup(
            individualsOptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(individualsOptionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(individualsOptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(indiMaximumDateIntervalLabel)
                    .addComponent(indiMaxDateIntervalSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(indiAllFirstNamesRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(indiCheckAllNamesRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(indiCheckFamiliesRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(indiEmptyValuesValidRadioButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.add(individualsOptionPanel);

        familiesOptionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(SearchDuplicatesVisualPanel2.class, "SearchDuplicatesVisualPanel2.familiesOptionPanel.border.border.title")), org.openide.util.NbBundle.getMessage(SearchDuplicatesVisualPanel2.class, "SearchDuplicatesVisualPanel2.familiesOptionPanel.border.title"))); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(famMaxDateIntervalLabel, org.openide.util.NbBundle.getMessage(SearchDuplicatesVisualPanel2.class, "SearchDuplicatesVisualPanel2.famMaxDateIntervalLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(famEmptyValuesValidRadioButton, org.openide.util.NbBundle.getMessage(SearchDuplicatesVisualPanel2.class, "SearchDuplicatesVisualPanel2.famEmptyValuesValidRadioButton.text")); // NOI18N

        javax.swing.GroupLayout familiesOptionPanelLayout = new javax.swing.GroupLayout(familiesOptionPanel);
        familiesOptionPanel.setLayout(familiesOptionPanelLayout);
        familiesOptionPanelLayout.setHorizontalGroup(
            familiesOptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(familiesOptionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(familiesOptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(familiesOptionPanelLayout.createSequentialGroup()
                        .addComponent(famMaxDateIntervalLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(famMaxDateIntervalSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(famEmptyValuesValidRadioButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        familiesOptionPanelLayout.setVerticalGroup(
            familiesOptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(familiesOptionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(familiesOptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(famMaxDateIntervalSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(famMaxDateIntervalLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(famEmptyValuesValidRadioButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.add(familiesOptionPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
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
    private javax.swing.JRadioButton famEmptyValuesValidRadioButton;
    private javax.swing.JLabel famMaxDateIntervalLabel;
    private javax.swing.JSpinner famMaxDateIntervalSpinner;
    private javax.swing.JCheckBox familiesCheckBox;
    private javax.swing.JPanel familiesOptionPanel;
    private javax.swing.JRadioButton indiAllFirstNamesRadioButton;
    private javax.swing.JRadioButton indiCheckAllNamesRadioButton;
    private javax.swing.JRadioButton indiCheckFamiliesRadioButton;
    private javax.swing.JRadioButton indiEmptyValuesValidRadioButton;
    private javax.swing.JSpinner indiMaxDateIntervalSpinner;
    private javax.swing.JLabel indiMaximumDateIntervalLabel;
    private javax.swing.JCheckBox individualsCheckBox;
    private javax.swing.JPanel individualsOptionPanel;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
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

    public TreeMap<String, ? extends Options> getSelectedOptions() {
        IndiMatcherOptions indiMatcherOptions = (IndiMatcherOptions) selectedOptions.get(Gedcom.INDI);
        indiMatcherOptions.setDateinterval((Integer) indiMaxDateIntervalSpinner.getValue());
        indiMatcherOptions.setCheckAllNames(indiCheckAllNamesRadioButton.isSelected());
        indiMatcherOptions.setAllFirstNames(indiAllFirstNamesRadioButton.isSelected());
        indiMatcherOptions.setCheckFamilies(indiCheckFamiliesRadioButton.isSelected());
        indiMatcherOptions.setEmptyValueValid(indiEmptyValuesValidRadioButton.isSelected());

        FamMatcherOptions famMatcherOptions = (FamMatcherOptions) selectedOptions.get(Gedcom.FAM);
        famMatcherOptions.setDateinterval((Integer) famMaxDateIntervalSpinner.getValue());
        famMatcherOptions.setEmptyValueValid(famEmptyValuesValidRadioButton.isSelected());
        return selectedOptions;
    }
}
