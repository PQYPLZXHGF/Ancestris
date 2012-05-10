/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ZipExplorerDownloadActionPanel.java
 *
 * Created on 9 mai 2012, 22:08:12
 */
package org.ancestris.trancestris.explorers.zipexplorer.actions;

import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;
import org.openide.windows.WindowManager;

/**
 *
 * @author dominique
 */
public class ZipExplorerDownloadActionPanel extends javax.swing.JPanel {

    private File localBundleFile = null;
    private String bundleUrl = null;
    final FileNameExtensionFilter filter = new FileNameExtensionFilter("Zip files", "zip");
    private JFileChooser fileChooser = new JFileChooser() {

        @Override
        public void approveSelection() {
            File f = getSelectedFile();
            if (f.exists() && getDialogType() == SAVE_DIALOG) {
                int result = JOptionPane.showConfirmDialog(this, NbBundle.getMessage(ZipExplorerDownloadActionPanel.class, "ZipExplorerDownloadActionPanel.Overwrite.Text"), NbBundle.getMessage(ZipExplorerDownloadActionPanel.class, "ZipExplorerDownloadActionPanel.Overwrite.Title"), JOptionPane.OK_CANCEL_OPTION);
                switch (result) {
                    case JOptionPane.YES_OPTION:
                        super.approveSelection();
                        return;
                    case JOptionPane.NO_OPTION:
                        return;
                    case JOptionPane.CANCEL_OPTION:
                        super.cancelSelection();
                        return;
                }
            } else {
                if (filter.accept(f) == false) {
                    setSelectedFile(new File(f.getName() + ".zip"));
                }
            }
            super.approveSelection();
        }
    };

    /** Creates new form ZipExplorerDownloadActionPanel */
    public ZipExplorerDownloadActionPanel() {
        initComponents();
        localBundleTextField.setText(NbPreferences.forModule(ZipExplorerOpenActionPanel.class).get("Fichier", ""));
        String dirName = NbPreferences.forModule(ZipExplorerOpenActionPanel.class).get("Dossier", "");
        String fileName = NbPreferences.forModule(ZipExplorerOpenActionPanel.class).get("Fichier", "");
        if (dirName.length() > 0) {
            localBundleFile = new File(dirName + System.getProperty("file.separator") + fileName);
        }
        bundleUrl = UrlTextField.getText();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        UrlTextField = new javax.swing.JTextField();
        localBundleTextField = new javax.swing.JTextField();
        openFileButton = new javax.swing.JButton();

        UrlTextField.setText(org.openide.util.NbBundle.getMessage(ZipExplorerDownloadActionPanel.class, "ZipExplorerDownloadActionPanel.UrlTextField.text")); // NOI18N

        openFileButton.setText(org.openide.util.NbBundle.getMessage(ZipExplorerDownloadActionPanel.class, "ZipExplorerDownloadActionPanel.openFileButton.text")); // NOI18N
        openFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(UrlTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(localBundleTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(openFileButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(UrlTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(localBundleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(openFileButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void openFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileButtonActionPerformed
        String dirName = NbPreferences.forModule(ZipExplorerOpenActionPanel.class).get("Dossier", "");
        String fileName = NbPreferences.forModule(ZipExplorerOpenActionPanel.class).get("Fichier", "");

        fileChooser.setFileFilter(filter);
        if (dirName.length() > 0) {
            // Set the current directory
            fileChooser.setCurrentDirectory(new File(dirName));
        }

        if (fileName.length() > 0) {
            fileChooser.setSelectedFile(new File(fileName));
        }

        if (fileChooser.showSaveDialog(WindowManager.getDefault().getMainWindow()) == JFileChooser.APPROVE_OPTION) {
            localBundleFile = fileChooser.getSelectedFile();
            localBundleTextField.setText(getLocalBundleFile().getName());
            try {
                NbPreferences.forModule(ZipExplorerOpenActionPanel.class).put("Dossier", fileChooser.getCurrentDirectory().getCanonicalPath());
            } catch (IOException ex) {
                NbPreferences.forModule(ZipExplorerOpenActionPanel.class).put("Dossier", "");
            }
            NbPreferences.forModule(ZipExplorerOpenActionPanel.class).put("Fichier", getLocalBundleFile().getName());
        }

    }//GEN-LAST:event_openFileButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField UrlTextField;
    private javax.swing.JTextField localBundleTextField;
    private javax.swing.JButton openFileButton;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the localBundleFile
     */
    public File getLocalBundleFile() {
        return localBundleFile;
    }

    /**
     * @return the urlBundleFile
     */
    public String getBundleUrl() {
        return bundleUrl;
    }
}
