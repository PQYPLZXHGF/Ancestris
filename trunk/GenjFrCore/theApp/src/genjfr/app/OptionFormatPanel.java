/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genjfr.app;

import genj.util.AncestrisPreferences;
import genj.util.Registry;
import javax.swing.SpinnerNumberModel;
import org.openide.awt.StatusDisplayer;

final class OptionFormatPanel extends javax.swing.JPanel {

    private final OptionFormatOptionsPanelController controller;
    // Values
    String[] indis = new String[]{"Charles de Gaulle", "de Gaulle, Charles"};
    String[] dates = new String[] { "25 FEB 1587 (Format Gedcom)", "25 Fév 1587 (Format local court)", "25 Février 1587 (Format local long)", "25/02/1587 (Format numérique européen)" };

    OptionFormatPanel(OptionFormatOptionsPanelController controller) {
        this.controller = controller;
        initComponents();
        // TODO listen to changes in form fields and call controller.changed()
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField3 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jSpinner3 = new javax.swing.JSpinner(new SpinnerNumberModel(248, 20, 248, 1));
        jLabel23 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner(new SpinnerNumberModel(128, 128, 16384, 128));
        jLabel22 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox(dates);
        jComboBox4 = new javax.swing.JComboBox(indis);
        jTextField1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jSpinner2 = new javax.swing.JSpinner(new SpinnerNumberModel(0, 0, 10000, 1));
        jLabel21 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(691, 503));

        jTextField3.setText(org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jTextField3.text")); // NOI18N
        jTextField3.setToolTipText(org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jTextField3.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel24, org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jLabel24.text")); // NOI18N

        jSpinner3.setToolTipText(org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jSpinner3.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel23, org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jLabel23.text")); // NOI18N

        jSpinner1.setToolTipText(org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jSpinner1.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel22, org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jLabel22.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jLabel11.text")); // NOI18N

        jTextField5.setText(org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jTextField5.text")); // NOI18N
        jTextField5.setToolTipText(org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jTextField5.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel10, org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jLabel10.text")); // NOI18N

        jTextField4.setText(org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jTextField4.text")); // NOI18N
        jTextField4.setToolTipText(org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jTextField4.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel14, org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jLabel14.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel12, org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jLabel12.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel13, org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jLabel13.text")); // NOI18N

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jLabel6.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel15, org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jLabel15.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel16, org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jLabel16.text")); // NOI18N

        jTextField9.setText(org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jTextField9.text")); // NOI18N
        jTextField9.setToolTipText(org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jTextField9.toolTipText")); // NOI18N

        jTextField8.setText(org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jTextField8.text")); // NOI18N
        jTextField8.setToolTipText(org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jTextField8.toolTipText")); // NOI18N

        jTextField6.setText(org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jTextField6.text")); // NOI18N
        jTextField6.setToolTipText(org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jTextField6.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel19, org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jLabel19.text")); // NOI18N

        jTextField12.setText(org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jTextField12.text")); // NOI18N
        jTextField12.setToolTipText(org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jTextField12.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel17, org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jLabel17.text")); // NOI18N

        jTextField10.setText(org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jTextField10.text")); // NOI18N
        jTextField10.setToolTipText(org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jTextField10.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel18, org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jLabel18.text")); // NOI18N

        jTextField11.setText(org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jTextField11.text")); // NOI18N
        jTextField11.setToolTipText(org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jTextField11.toolTipText")); // NOI18N

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jLabel1.text")); // NOI18N

        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jLabel4.text")); // NOI18N

        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jLabel3.text")); // NOI18N

        jLabel5.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jLabel5.text")); // NOI18N

        jLabel7.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jLabel7.text")); // NOI18N

        jComboBox3.setToolTipText(org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jComboBox3.toolTipText")); // NOI18N

        jComboBox4.setToolTipText(org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jComboBox4.toolTipText")); // NOI18N

        jTextField1.setText(org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jTextField1.text")); // NOI18N
        jTextField1.setToolTipText(org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jTextField1.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jLabel8.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jLabel9.text")); // NOI18N

        jTextField2.setText(org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jTextField2.text")); // NOI18N
        jTextField2.setToolTipText(org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jTextField2.toolTipText")); // NOI18N

        jTextField13.setText(org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jTextField13.text")); // NOI18N
        jTextField13.setToolTipText(org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jTextField13.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBox1, org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jCheckBox1.text")); // NOI18N
        jCheckBox1.setToolTipText(org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jCheckBox1.toolTipText")); // NOI18N

        jSpinner2.setToolTipText(org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jSpinner2.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel21, org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jLabel21.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel20, org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionFormatPanel.jLabel20.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel16))
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel12)
                            .addComponent(jLabel9)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(160, 160, 160)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel19)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(175, 175, 175)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel20)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel24)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jCheckBox1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel21)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel22))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(160, 160, 160)
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSpinner1)
                                    .addComponent(jSpinner3, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel8)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel18)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel24)
                    .addComponent(jCheckBox1))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel23)
                    .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    void load() {
        AncestrisPreferences gedcomPrefs = Registry.get(genj.gedcom.Options.class);
        AncestrisPreferences reportPrefs = Registry.get(genj.report.Options.class);

        setSymbolBirt(reportPrefs.get("birthSymbol", ""));
        setSymbolBapm(reportPrefs.get("baptismSymbol", ""));
        setSymbolChildOf(reportPrefs.get("childOfSymbol", ""));
        setSymbolEngm(reportPrefs.get("engagingSymbol", ""));
        setSymbolMarr(reportPrefs.get("marriageSymbol", ""));
        setSymbolDivc(reportPrefs.get("divorceSymbol", ""));
        setSymbolOccu(reportPrefs.get("occuSymbol", ""));
        setSymbolResi(reportPrefs.get("resiSymbol", ""));
        setSymbolDeat(reportPrefs.get("deathSymbol", ""));
        setSymbolBuri(reportPrefs.get("burialSymbol", ""));
        setPrivDisplay(gedcomPrefs.get("maskPrivate", ""));
        setPrivFlag(reportPrefs.get("privateTag", ""));
        setPrivAlive(reportPrefs.get("deceasedIsPublic", ""));
        setPrivYears(reportPrefs.get("yearsEventsArePrivate", ""));
        setLineBreak(gedcomPrefs.get("valueLineBreak", ""));
        setImageSize(gedcomPrefs.get("maxImageFileSizeKB", ""));
        setDisplayNames(gedcomPrefs.get("nameFormat", ""));
        setDisplayDates(gedcomPrefs.get("dateFormat", ""));
    }

    void store() {
        AncestrisPreferences gedcomPrefs = Registry.get(genj.gedcom.Options.class);
        AncestrisPreferences reportPrefs = Registry.get(genj.report.Options.class);

        reportPrefs.put("birthSymbol", getSymbolBirt());
        reportPrefs.put("baptismSymbol", getSymbolBapm());
        reportPrefs.put("childOfSymbol", getSymbolChildOf());
        reportPrefs.put("engagingSymbol", getSymbolEngm());
        reportPrefs.put("marriageSymbol", getSymbolMarr());
        reportPrefs.put("divorceSymbol", getSymbolDivc());
        reportPrefs.put("occuSymbol", getSymbolOccu());
        reportPrefs.put("resiSymbol", getSymbolResi());
        reportPrefs.put("deathSymbol", getSymbolDeat());
        reportPrefs.put("burialSymbol", getSymbolBuri());
        gedcomPrefs.put("maskPrivate", getPrivDisplay());
        reportPrefs.put("privateTag", getPrivFlag());
        reportPrefs.put("deceasedIsPublic", getPrivAlive());
        reportPrefs.put("yearsEventsArePrivate", getPrivYears());
        gedcomPrefs.put("valueLineBreak", getLineBreak());
        gedcomPrefs.put("maxImageFileSizeKB", getImageSize());
        gedcomPrefs.put("nameFormat", getDisplayNames());
        gedcomPrefs.put("dateFormat", getDisplayDates());
        
        StatusDisplayer.getDefault().setStatusText(org.openide.util.NbBundle.getMessage(OptionFormatPanel.class, "OptionPanel.saved.statustext"));
    }

    boolean valid() {
        // TODO check whether form is consistent and complete
        return true;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables

    void setSymbolBirt(String str) {
        if (str.equals("")) {
            str = "o";
        }
        jTextField1.setText(str);
    }

    String getSymbolBirt() {
        return jTextField1.getText();
    }

    void setSymbolBapm(String str) {
        if (str.equals("")) {
            str = "b.";
        }
        jTextField2.setText(str);
    }

    String getSymbolBapm() {
        return jTextField2.getText();
    }

    void setSymbolChildOf(String str) {
        if (str.equals("")) {
            str = "fs.";
        }
        jTextField3.setText(str);
    }

    String getSymbolChildOf() {
        return jTextField3.getText();
    }

    void setSymbolEngm(String str) {
        if (str.equals("")) {
            str = "(x)";
        }
        jTextField4.setText(str);
    }

    String getSymbolEngm() {
        return jTextField4.getText();
    }

    void setSymbolMarr(String str) {
        if (str.equals("")) {
            str = "x";
        }
        jTextField5.setText(str);
    }

    String getSymbolMarr() {
        return jTextField5.getText();
    }

    void setSymbolDivc(String str) {
        if (str.equals("")) {
            str = ")(";
        }
        jTextField6.setText(str);
    }

    String getSymbolDivc() {
        return jTextField6.getText();
    }

    void setSymbolOccu(String str) {
        if (str.equals("")) {
            str = "=";
        }
        jTextField9.setText(str);
    }

    String getSymbolOccu() {
        return jTextField9.getText();
    }

    void setSymbolResi(String str) {
        if (str.equals("")) {
            str = "^";
        }
        jTextField8.setText(str);
    }

    String getSymbolResi() {
        return jTextField8.getText();
    }

    void setSymbolDeat(String str) {
        if (str.equals("")) {
            str = "+";
        }
        jTextField11.setText(str);
    }

    String getSymbolDeat() {
        return jTextField11.getText();
    }

    void setSymbolBuri(String str) {
        if (str.equals("")) {
            str = "(+)";
        }
        jTextField10.setText(str);
    }

    String getSymbolBuri() {
        return jTextField10.getText();
    }

    void setPrivDisplay(String str) {
        if (str.equals("")) {
            str = "...";
        }
        jTextField12.setText(str);
    }

    String getPrivDisplay() {
        return jTextField12.getText();
    }

    void setPrivFlag(String str) {
        if (str.equals("")) {
            str = "_PRIV";
        }
        jTextField13.setText(str);
    }

    String getPrivFlag() {
        return jTextField13.getText();
    }

    void setPrivAlive(String str) {
        jCheckBox1.setSelected(str.equals("true") ? true : false);
    }

    String getPrivAlive() {
        return jCheckBox1.isSelected() ? "true" : "false";
    }

    void setPrivYears(String str) {
        if (str.equals("-1")) {
            str = "0";
        }
        Integer i = getIntFromStr(str);
        if (i == -1) {
            i = 75;
        }
        if (i > 10000) {
            i = 10000;
        }
        jSpinner2.setValue(i);
    }

    String getPrivYears() {
        return jSpinner2.getValue().toString();
    }

    void setLineBreak(String str) {
        if (str.equals("-1")) {
            str = "20";
        }
        Integer i = getIntFromStr(str);
        if (i == -1) {
            i = 248;
        }
        if (i > 248) {
            i = 248;
        }
        jSpinner3.setValue(i);
    }

    String getLineBreak() {
        return jSpinner3.getValue().toString();
    }

    void setImageSize(String str) {
        if (str.equals("-1")) {
            str = "128";
        }
        Integer i = getIntFromStr(str);
        if (i == -1) {
            i = 128;
        }
        if (i > 16384) {
            i = 16384;
        }
        jSpinner1.setValue(i);
    }

    String getImageSize() {
        return jSpinner1.getValue().toString();
    }

    void setDisplayNames(String str) {
        if ((str.length() == 0) || str.equals("-1")) {
            str = "1";
        }
        Integer i = getIntFromStr(str);
        if (i == -1) {
            i = 1;
        }
        if (i > 1) {
            i = 1;
        }
        jComboBox4.setSelectedIndex(i);
    }

    String getDisplayNames() {
        return jComboBox4.getSelectedIndex() + "";
    }

    void setDisplayDates(String str) {
        if ((str.length() == 0) || str.equals("-1")) {
            str = "1";
        }
        Integer i = getIntFromStr(str);
        if (i == -1) {
            i = 1;
        }
        if (i > 3) {
            i = 3;
        }
        jComboBox3.setSelectedIndex(i);
    }

    String getDisplayDates() {
        return jComboBox3.getSelectedIndex() + "";
    }

    private Integer getIntFromStr(String str) {

        Integer i = 0;
        try {
            i = Integer.valueOf(str);
        } catch (Exception e) {
            i = -1;
        }
        return i;
    }
}
