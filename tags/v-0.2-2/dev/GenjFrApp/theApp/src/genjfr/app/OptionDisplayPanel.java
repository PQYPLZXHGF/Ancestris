/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genjfr.app;

import genj.util.Registry;
import javax.swing.SpinnerNumberModel;
import javax.swing.ToolTipManager;
import org.openide.awt.StatusDisplayer;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;

final class OptionDisplayPanel extends javax.swing.JPanel {

    private final OptionDisplayOptionsPanelController controller;
    // Values
    String[] languages = new String[13];
    String[] skins = new String[9];

    OptionDisplayPanel(OptionDisplayOptionsPanelController controller) {
        this.controller = controller;
        initLanguages();
        initSkins();
        initComponents();
        ToolTipManager.sharedInstance().setDismissDelay(10000); // sets it for the other panels...
        // TODO listen to changes in form fields and call controller.changed()
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jSpinner1 = new javax.swing.JSpinner(new SpinnerNumberModel(10, 10, 300, 5));
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox(skins);
        jComboBox1 = new javax.swing.JComboBox(languages);
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();

        setPreferredSize(new java.awt.Dimension(691, 503));

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBox4, org.openide.util.NbBundle.getMessage(OptionDisplayPanel.class, "OptionDisplayPanel.jCheckBox4.text")); // NOI18N
        jCheckBox4.setToolTipText(org.openide.util.NbBundle.getMessage(OptionDisplayPanel.class, "OptionDisplayPanel.jCheckBox4.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBox2, org.openide.util.NbBundle.getMessage(OptionDisplayPanel.class, "OptionDisplayPanel.jCheckBox2.text")); // NOI18N
        jCheckBox2.setToolTipText(org.openide.util.NbBundle.getMessage(OptionDisplayPanel.class, "OptionDisplayPanel.jCheckBox2.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBox3, org.openide.util.NbBundle.getMessage(OptionDisplayPanel.class, "OptionDisplayPanel.jCheckBox3.text")); // NOI18N
        jCheckBox3.setToolTipText(org.openide.util.NbBundle.getMessage(OptionDisplayPanel.class, "OptionDisplayPanel.jCheckBox3.toolTipText")); // NOI18N

        jSpinner1.setToolTipText(org.openide.util.NbBundle.getMessage(OptionDisplayPanel.class, "OptionDisplayPanel.jSpinner1.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(OptionDisplayPanel.class, "OptionDisplayPanel.jLabel6.text")); // NOI18N
        jLabel6.setToolTipText(org.openide.util.NbBundle.getMessage(OptionDisplayPanel.class, "OptionDisplayPanel.jLabel6.toolTipText")); // NOI18N

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(OptionDisplayPanel.class, "OptionDisplayPanel.jLabel2.text")); // NOI18N

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(OptionDisplayPanel.class, "OptionDisplayPanel.jLabel1.text")); // NOI18N

        jComboBox2.setToolTipText(org.openide.util.NbBundle.getMessage(OptionDisplayPanel.class, "OptionDisplayPanel.jComboBox2.toolTipText")); // NOI18N
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jComboBox1.setToolTipText(org.openide.util.NbBundle.getMessage(OptionDisplayPanel.class, "OptionDisplayPanel.jComboBox1.toolTipText")); // NOI18N

        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(OptionDisplayPanel.class, "OptionDisplayPanel.jLabel4.text")); // NOI18N

        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(OptionDisplayPanel.class, "OptionDisplayPanel.jLabel3.text")); // NOI18N

        jPanel1.setBackground(new java.awt.Color(179, 179, 179));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setPreferredSize(new java.awt.Dimension(199, 224));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(OptionDisplayPanel.class, "OptionDisplayPanel.jLabel5.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBox1, org.openide.util.NbBundle.getMessage(OptionDisplayPanel.class, "OptionDisplayPanel.jCheckBox1.text")); // NOI18N
        jCheckBox1.setToolTipText(org.openide.util.NbBundle.getMessage(OptionDisplayPanel.class, "OptionDisplayPanel.jCheckBox1.toolTipText")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(48, 48, 48)
                                .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(79, 79, 79)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox2)
                            .addComponent(jCheckBox4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBox3)
                                .addGap(154, 154, 154))))
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jCheckBox1)))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 167, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jCheckBox1))
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox2)
                .addGap(61, 61, 61))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        int i = jComboBox2.getSelectedIndex();
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/genjfr/app/skin-" + i + ".png")));
}//GEN-LAST:event_jComboBox2ActionPerformed

    void load() {
        setLanguage(NbPreferences.forModule(App.class).get("language", ""));
        setSkin(NbPreferences.forModule(App.class).get("skin", ""));
        setRestoreWindows(NbPreferences.forModule(App.class).get("restoreWindows", ""));
        setAutoCommit(NbPreferences.forModule(App.class).get("autoCommit", ""));
        setUndos(NbPreferences.forModule(App.class).get("undos", ""));
        setSplitJurisdictions(NbPreferences.forModule(App.class).get("splitJurisdiction", ""));
        setOpenEditor(NbPreferences.forModule(App.class).get("OpenEditor", ""));
    }

    void store() {
        NbPreferences.forModule(App.class).put("language", getLanguage());
        NbPreferences.forModule(App.class).put("skin", getSkin());
        NbPreferences.forModule(App.class).put("restoreWindows", getRestoreWindows());
        NbPreferences.forModule(App.class).put("autoCommit", getAutoCommit());
        NbPreferences.forModule(App.class).put("undos", getUndos());
        NbPreferences.forModule(App.class).put("splitJurisdiction", getSplitJurisdictions());
        NbPreferences.forModule(App.class).put("OpenEditor", getOpenEditor());

        NbPreferences.forModule(App.class).put("optionswizard", "3"); // should be same as in the wizard
        
        putRegistryFromSettings();

        StatusDisplayer.getDefault().setStatusText(org.openide.util.NbBundle.getMessage(OptionDisplayPanel.class, "OptionPanel.saved.statustext"));
    }

    boolean valid() {
        // TODO check whether form is consistent and complete
        return true;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSpinner jSpinner1;
    // End of variables declaration//GEN-END:variables

    private void initLanguages() {
        languages[0] = NbBundle.getMessage(App.class, "option.language.EN");
        languages[1] = NbBundle.getMessage(App.class, "option.language.DE");
        languages[2] = NbBundle.getMessage(App.class, "option.language.FR");
        languages[3] = NbBundle.getMessage(App.class, "option.language.HU");
        languages[4] = NbBundle.getMessage(App.class, "option.language.ES");
        languages[5] = NbBundle.getMessage(App.class, "option.language.NL");
        languages[6] = NbBundle.getMessage(App.class, "option.language.RU");
        languages[7] = NbBundle.getMessage(App.class, "option.language.IT");
        languages[8] = NbBundle.getMessage(App.class, "option.language.JA");
        languages[9] = NbBundle.getMessage(App.class, "option.language.PT_BR");
        languages[10] = NbBundle.getMessage(App.class, "option.language.CS");
        languages[11] = NbBundle.getMessage(App.class, "option.language.PL");
        languages[12] = NbBundle.getMessage(App.class, "option.language.FI");

    }

    private void initSkins() {
        skins[0] = NbBundle.getMessage(App.class, "option.skin.ST");
        skins[1] = NbBundle.getMessage(App.class, "option.skin.JA");
        skins[2] = NbBundle.getMessage(App.class, "option.skin.ME");
        skins[3] = NbBundle.getMessage(App.class, "option.skin.KS");
        skins[4] = NbBundle.getMessage(App.class, "option.skin.AQ");
        skins[5] = NbBundle.getMessage(App.class, "option.skin.XP");
        skins[6] = NbBundle.getMessage(App.class, "option.skin.MO");
        skins[7] = NbBundle.getMessage(App.class, "option.skin.WH");
        skins[8] = NbBundle.getMessage(App.class, "option.skin.AR");
    }

    void setLanguage(String str) {
        if (str.equals("-1")) {
            str = "2";
        }
        Integer i = getIntFromStr(str);
        if (i == -1) {
            i = 2;
        }
        if (i > languages.length) {
            i = languages.length;
        }
        jComboBox1.setSelectedIndex(i);
    }

    String getLanguage() {
        return jComboBox1.getSelectedIndex() + "";
    }

    void setSkin(String str) {
        if (str.equals("-1")) {
            str = "1";
        }
        Integer i = getIntFromStr(str);
        if (i == -1) {
            i = 1;
        }
        if (i > skins.length) {
            i = skins.length;
        }
        jComboBox2.setSelectedIndex(i);
    }

    String getSkin() {
        return jComboBox2.getSelectedIndex() + "";
    }

    void setRestoreWindows(String str) {
        if (str.equals("")) {
            str = "true";
        }
        jCheckBox1.setSelected(str.equals("true") ? true : false);
    }

    String getRestoreWindows() {
        return jCheckBox1.isSelected() ? "true" : "false";
    }

    void setAutoCommit(String str) {
        jCheckBox3.setSelected(str.equals("true") ? true : false);
    }

    String getAutoCommit() {
        return jCheckBox3.isSelected() ? "true" : "false";
    }

    void setUndos(String str) {
        if (str.equals("-1")) {
            str = "10";
        }
        Integer i = getIntFromStr(str);
        if (i == -1) {
            i = 10;
        }
        if (i > 300) {
            i = 300;
        }
        jSpinner1.setValue(i);
    }

    String getUndos() {
        return jSpinner1.getValue().toString();
    }

    void setSplitJurisdictions(String str) {
        jCheckBox4.setSelected(str.equals("true") ? true : false);
    }

    String getSplitJurisdictions() {
        return jCheckBox4.isSelected() ? "true" : "false";
    }

    void setOpenEditor(String str) {
        jCheckBox2.setSelected(str.equals("true") ? true : false);
    }

    String getOpenEditor() {
        return jCheckBox2.isSelected() ? "true" : "false";
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

    private void putRegistryFromSettings() {
        Registry registry = Registry.lookup("genj", null);

        registry.put("options.genj.app.Options.language", NbPreferences.forModule(App.class).get("language", ""));
        registry.put("options.genj.app.Options.lookAndFeel", NbPreferences.forModule(App.class).get("skin", ""));
        registry.put("options.genj.app.Options.isRestoreViews", NbPreferences.forModule(App.class).get("restoreWindows", ""));
        registry.put("options.genj.edit.Options.isAutoCommit", NbPreferences.forModule(App.class).get("autoCommit", ""));
        registry.put("options.genj.gedcom.Options.numberOfUndos", NbPreferences.forModule(App.class).get("undos", ""));
        registry.put("options.genj.edit.Options.isSplitJurisdictions", NbPreferences.forModule(App.class).get("splitJurisdiction", ""));
        registry.put("options.genj.edit.Options.isOpenEditor", NbPreferences.forModule(App.class).get("OpenEditor", ""));

        Registry.persist();

    }
}