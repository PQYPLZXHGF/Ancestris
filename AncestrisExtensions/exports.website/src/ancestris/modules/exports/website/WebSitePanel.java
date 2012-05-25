/*
 * Ancestris - http://www.ancestris.org
 * 
 * Copyright 2012 Ancestris
 * 
 * Author: Daniel Andre (daniel@ancestris.org).
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */
package ancestris.modules.exports.website;

import genj.option.OptionsWidget;
import genj.report.Report;
import javax.swing.JScrollPane;

final class WebSitePanel extends javax.swing.JPanel {

    private final WebSiteOptionsPanelController controller;
    private OptionsWidget optionsPanel;

    WebSitePanel(WebSiteOptionsPanelController controller) {
        this.controller = controller;
//        initComponents();
        optionsPanel = new OptionsWidget("");
        add(new JScrollPane(optionsPanel));
        // TODO listen to changes in form fields and call controller.changed()
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 202, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 112, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    /*
     * first persists options to get them back in case of cancellation
     * then get options from report
     * XXX: we should pur this in report class
     */
    void load() {
        Report report = WebSiteExportPlugin.getReport();
        report.saveOptions();
        optionsPanel.setOptions(report.getOptions());
    }

    /*
     * stop editing to get last change done
     * persist options as this report will not be persisted in reportloader class
     */
    void store() {
        optionsPanel.stopEditing();
        Report report = WebSiteExportPlugin.getReport();
        report.saveOptions();
    }

    /*
     * if cancelled, (re)get options from file
     */
    void cancel() {
        optionsPanel.stopEditing();
        Report report = WebSiteExportPlugin.getReport();
        report.getOptions(true);
    }

    boolean valid() {
        // TODO check whether form is consistent and complete
        return true;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
