/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ancestris.trancestris.application.actions;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.ancestris.trancestris.application.DownloadBundleWorker;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;

public final class DownloadBundleAction implements ActionListener {

    class DownloadBundlePanelListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }
    DownloadBundlePanel zipExplorerDownloadActionPanel = new DownloadBundlePanel();

    @Override
    public void actionPerformed(ActionEvent e) {

        DialogDescriptor downloadActionDescriptor = new DialogDescriptor(
                zipExplorerDownloadActionPanel,
                NbBundle.getMessage(OpenZipBundlePanel.class, "CTL_DownloadBundleAction"),
                true,
                new DownloadBundlePanelListener());
        Dialog dialog = DialogDisplayer.getDefault().createDialog(downloadActionDescriptor);

        dialog.setVisible(true);
        dialog.toFront();
        if (downloadActionDescriptor.getValue() == DialogDescriptor.OK_OPTION) {
            try {
                URL url = new URL(zipExplorerDownloadActionPanel.getBundleUrl());
                File bundleFile = zipExplorerDownloadActionPanel.getLocalBundleFile();
                NbPreferences.forModule(OpenZipBundlePanel.class).put("Dossier", bundleFile.getParent());
                NbPreferences.forModule(OpenZipBundlePanel.class).put("Fichier", bundleFile.getName());
                NbPreferences.forModule(OpenZipBundlePanel.class).put("Url.address", url.toString());
                if (bundleFile.exists()) {
                    int result = JOptionPane.showConfirmDialog(null, NbBundle.getMessage(DownloadBundlePanel.class, "DownloadBundlePanel.Overwrite.Text"), NbBundle.getMessage(DownloadBundlePanel.class, "DownloadBundlePanel.Overwrite.Title"), JOptionPane.OK_CANCEL_OPTION);
                    switch (result) {
                        case JOptionPane.YES_OPTION:
                            Thread t = new Thread(new DownloadBundleWorker(url, bundleFile));
                            t.start();
                            return;
                        case JOptionPane.NO_OPTION:
                        case JOptionPane.CANCEL_OPTION:
                            return;
                    }
                }

            } catch (MalformedURLException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }
}
