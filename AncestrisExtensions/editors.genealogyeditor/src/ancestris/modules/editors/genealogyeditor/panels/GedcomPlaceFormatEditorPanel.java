package ancestris.modules.editors.genealogyeditor.panels;

import genj.gedcom.Gedcom;
import genj.gedcom.PropertyPlace;
import javax.swing.JComponent;

/**
 *
 * @author dominique
 */
public class GedcomPlaceFormatEditorPanel extends javax.swing.JPanel {

    private Gedcom gedcom;
    private String[] mPlaceFormat;

    /**
     * Creates new form GedcomPlaceFormatEditorPanel
     */
    public GedcomPlaceFormatEditorPanel(Gedcom gedcom) {
        this.gedcom = gedcom;
        mPlaceFormat = PropertyPlace.getFormat(gedcom);
        initComponents();

        JComponent gedcomFields[][] = {
            {hamletLabel, hamletSpinner, hamletComboBox},
            {parishLabel, parishSpinner, parishComboBox},
            {townLabel, townSpinner, twonComboBox},
            {postalCodeLabel, postalCodeSpinner, postalCodeComboBox},
            {jLabel5, jSpinner5, jComboBox5},
            {countyLabel, countySpinner, countyComboBox},
            {stateLabel, stateSpinner, stateComboBox},
            {countryLabel, countrySpinner, countryComboBox}
        };

        for (int index = 0; index < mPlaceFormat.length; index++) {
            gedcomFields[index][0].setVisible(true);
            gedcomFields[index][1].setVisible(true);
            ((javax.swing.JSpinner) gedcomFields[index][1]).setValue(index + 1);
            gedcomFields[index][2].setVisible(true);
            ((javax.swing.JComboBox<String>) (gedcomFields[index][2])).setSelectedIndex(index);
        }

        for (int index = mPlaceFormat.length; index < gedcomFields.length; index++) {
            gedcomFields[index][0].setVisible(false);
            gedcomFields[index][1].setVisible(false);
            ((javax.swing.JSpinner) gedcomFields[index][1]).setValue(0);
            gedcomFields[index][2].setVisible(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        hamletLabel = new javax.swing.JLabel();
        hamletComboBox = new javax.swing.JComboBox<String>(mPlaceFormat);
        parishLabel = new javax.swing.JLabel();
        parishComboBox = new javax.swing.JComboBox<String>(mPlaceFormat);
        townLabel = new javax.swing.JLabel();
        twonComboBox = new javax.swing.JComboBox<String>(mPlaceFormat);
        postalCodeLabel = new javax.swing.JLabel();
        postalCodeComboBox = new javax.swing.JComboBox<String>(mPlaceFormat);
        jLabel5 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<String>(mPlaceFormat);
        countyLabel = new javax.swing.JLabel();
        countyComboBox = new javax.swing.JComboBox<String>(mPlaceFormat);
        stateLabel = new javax.swing.JLabel();
        stateComboBox = new javax.swing.JComboBox<String>(mPlaceFormat);
        countryLabel = new javax.swing.JLabel();
        countryComboBox = new javax.swing.JComboBox<String>(mPlaceFormat);
        hamletSpinner = new javax.swing.JSpinner();
        parishSpinner = new javax.swing.JSpinner();
        townSpinner = new javax.swing.JSpinner();
        postalCodeSpinner = new javax.swing.JSpinner();
        jSpinner5 = new javax.swing.JSpinner();
        countySpinner = new javax.swing.JSpinner();
        stateSpinner = new javax.swing.JSpinner();
        countrySpinner = new javax.swing.JSpinner();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        hamletLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(hamletLabel, org.openide.util.NbBundle.getMessage(GedcomPlaceFormatEditorPanel.class, "GedcomPlaceFormatEditorPanel.hamletLabel.text")); // NOI18N

        parishLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(parishLabel, org.openide.util.NbBundle.getMessage(GedcomPlaceFormatEditorPanel.class, "GedcomPlaceFormatEditorPanel.parishLabel.text")); // NOI18N

        townLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(townLabel, org.openide.util.NbBundle.getMessage(GedcomPlaceFormatEditorPanel.class, "GedcomPlaceFormatEditorPanel.townLabel.text")); // NOI18N

        postalCodeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(postalCodeLabel, org.openide.util.NbBundle.getMessage(GedcomPlaceFormatEditorPanel.class, "GedcomPlaceFormatEditorPanel.postalCodeLabel.text")); // NOI18N

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(GedcomPlaceFormatEditorPanel.class, "GedcomPlaceFormatEditorPanel.jLabel5.text")); // NOI18N

        countyLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(countyLabel, org.openide.util.NbBundle.getMessage(GedcomPlaceFormatEditorPanel.class, "GedcomPlaceFormatEditorPanel.countyLabel.text")); // NOI18N

        stateLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(stateLabel, org.openide.util.NbBundle.getMessage(GedcomPlaceFormatEditorPanel.class, "GedcomPlaceFormatEditorPanel.stateLabel.text")); // NOI18N

        countryLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(countryLabel, org.openide.util.NbBundle.getMessage(GedcomPlaceFormatEditorPanel.class, "GedcomPlaceFormatEditorPanel.countryLabel.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hamletLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(townLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(stateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(stateSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                            .addComponent(jSpinner5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(townSpinner, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(hamletSpinner, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hamletComboBox, 0, 87, Short.MAX_VALUE)
                            .addComponent(twonComboBox, 0, 87, Short.MAX_VALUE)
                            .addComponent(jComboBox5, 0, 87, Short.MAX_VALUE)
                            .addComponent(stateComboBox, 0, 87, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(countyLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(postalCodeLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(parishLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(countryLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(countrySpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                            .addComponent(countySpinner, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(parishSpinner)
                            .addComponent(postalCodeSpinner))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(postalCodeComboBox, 0, 87, Short.MAX_VALUE)
                            .addComponent(parishComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(countyComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(countryComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hamletLabel)
                    .addComponent(parishLabel)
                    .addComponent(hamletComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(parishComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hamletSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(parishSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(townLabel)
                    .addComponent(postalCodeLabel)
                    .addComponent(twonComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(postalCodeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(townSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(postalCodeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(countyLabel)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(countyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(countySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stateLabel)
                    .addComponent(countryLabel)
                    .addComponent(countryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stateSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(countrySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> countryComboBox;
    private javax.swing.JLabel countryLabel;
    private javax.swing.JSpinner countrySpinner;
    private javax.swing.JComboBox<String> countyComboBox;
    private javax.swing.JLabel countyLabel;
    private javax.swing.JSpinner countySpinner;
    private javax.swing.JComboBox<String> hamletComboBox;
    private javax.swing.JLabel hamletLabel;
    private javax.swing.JSpinner hamletSpinner;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner5;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JComboBox<String> parishComboBox;
    private javax.swing.JLabel parishLabel;
    private javax.swing.JSpinner parishSpinner;
    private javax.swing.JComboBox<String> postalCodeComboBox;
    private javax.swing.JLabel postalCodeLabel;
    private javax.swing.JSpinner postalCodeSpinner;
    private javax.swing.JComboBox<String> stateComboBox;
    private javax.swing.JLabel stateLabel;
    private javax.swing.JSpinner stateSpinner;
    private javax.swing.JLabel townLabel;
    private javax.swing.JSpinner townSpinner;
    private javax.swing.JComboBox<String> twonComboBox;
    // End of variables declaration//GEN-END:variables

    public int[] getPlaceOrder() {
        int placeOrder[] = {
            (Integer) hamletSpinner.getValue() -1,
            (Integer) parishSpinner.getValue() -1,
            (Integer) townSpinner.getValue() -1,
            (Integer) postalCodeSpinner.getValue() -1,
            (Integer) jSpinner5.getValue() -1,
            (Integer) countySpinner.getValue() -1,
            (Integer) stateSpinner.getValue() -1,
            (Integer) countrySpinner.getValue()
        };
        return placeOrder;
    }
}
