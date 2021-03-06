/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FeedbackPanel.java
 *
 * Created on 15 mars 2010, 23:31:41
 */
package ancestris.modules.feedback;

import ancestris.util.swing.FileChooserBuilder;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.prefs.Preferences;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;

/**
 *
 * @author daniel
 */
public class FeedbackPanel extends javax.swing.JPanel {

    private File zipFile;
    private javax.swing.ImageIcon ancestris_logo = new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/feedback/ancestris_logo.gif")); // NOI18N
    private Preferences modulePreferences = NbPreferences.forModule(FeedBackPlugin.class);

    /**
     * Creates new form FeedbackPanel
     */
    public FeedbackPanel(File zipFile) {
        this.zipFile = zipFile;
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        sendLogCheckBox = new javax.swing.JCheckBox();
        jtEmailTo = new javax.swing.JFormattedTextField();
        jtEmail = new javax.swing.JFormattedTextField();
        jtName = new javax.swing.JFormattedTextField();
        jtSubject = new javax.swing.JFormattedTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaText = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel() {
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                Image image = ancestris_logo.getImage();
                int x = (this.getWidth() - image.getWidth(null)) / 2;
                int y = (this.getHeight() - image.getHeight(null)) / 2;
                g2d.drawImage(image,0, 0, 116, 116, null);
                super.paintComponent(g);
            }
        };
        jLabel7 = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();

        setBackground(getBackground());

        jLabel1.setText(org.openide.util.NbBundle.getMessage(FeedbackPanel.class, "FeedbackPanel.jLabel1.text")); // NOI18N

        jLabel2.setText(org.openide.util.NbBundle.getMessage(FeedbackPanel.class, "FeedbackPanel.jLabel2.text")); // NOI18N

        jLabel3.setText(org.openide.util.NbBundle.getMessage(FeedbackPanel.class, "FeedbackPanel.jLabel3.text")); // NOI18N

        jLabel4.setText(org.openide.util.NbBundle.getMessage(FeedbackPanel.class, "FeedbackPanel.jLabel4.text")); // NOI18N

        jLabel5.setText(org.openide.util.NbBundle.getMessage(FeedbackPanel.class, "FeedbackPanel.jLabel5.text")); // NOI18N

        sendLogCheckBox.setSelected(true);
        sendLogCheckBox.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/feedback/Bundle").getString("FeedbackPanel.jLabel6.text"), new Object[] {zipFile.length()})); // NOI18N
        sendLogCheckBox.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        sendLogCheckBox.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jtEmail.setText(org.openide.util.NbBundle.getMessage(FeedbackPanel.class, "FeedbackPanel.jtEmail.text")); // NOI18N

        jtName.setText(org.openide.util.NbBundle.getMessage(FeedbackPanel.class, "FeedbackPanel.jtName.text")); // NOI18N

        jtSubject.setText(org.openide.util.NbBundle.getMessage(FeedbackPanel.class, "FeedbackPanel.jtSubject.text")); // NOI18N

        jtaText.setColumns(20);
        jtaText.setLineWrap(true);
        jtaText.setRows(5);
        jtaText.setWrapStyleWord(true);
        jtaText.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane2.setViewportView(jtaText);

        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(116, 116));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );

        jLabel7.setFont(jLabel7.getFont().deriveFont(jLabel7.getFont().getSize()+4f));
        jLabel7.setForeground(new java.awt.Color(0, 102, 255));
        jLabel7.setText(org.openide.util.NbBundle.getMessage(FeedbackPanel.class, "FeedbackPanel.jLabel7.text")); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(500, 67));

        saveButton.setText(org.openide.util.NbBundle.getMessage(FeedbackPanel.class, "FeedbackPanel.saveButton.text")); // NOI18N
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sendLogCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(saveButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addComponent(jtSubject)
                            .addComponent(jtEmailTo)
                            .addComponent(jtEmail)
                            .addComponent(jtName, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE))
                        .addGap(8, 8, 8)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtEmailTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sendLogCheckBox)
                    .addComponent(saveButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        
        File file = new FileChooserBuilder(FeedbackPanel.class)
                .setFilesOnly(true)
                .setDefaultBadgeProvider()
                .setTitle(NbBundle.getMessage(getClass(), "FileChooserTitle"))
                .setApproveText(NbBundle.getMessage(getClass(), "FileChooserOKButton"))
                .setDefaultExtension(FileChooserBuilder.getZipFilter().getExtensions()[0])
                .setFileFilter(FileChooserBuilder.getZipFilter())
                .setAcceptAllFileFilterUsed(false)
                .setSelectedFile(new File(zipFile.getName()))
                .setFileHiding(true)
                .showSaveDialog();
        
        if (file != null) {
            File copyFile = file;
            try {
                Files.copy(zipFile.toPath(), copyFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }//GEN-LAST:event_saveButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    javax.swing.JFormattedTextField jtEmail;
    javax.swing.JFormattedTextField jtEmailTo;
    javax.swing.JFormattedTextField jtName;
    javax.swing.JFormattedTextField jtSubject;
    javax.swing.JTextArea jtaText;
    private javax.swing.JButton saveButton;
    javax.swing.JCheckBox sendLogCheckBox;
    // End of variables declaration//GEN-END:variables
}
