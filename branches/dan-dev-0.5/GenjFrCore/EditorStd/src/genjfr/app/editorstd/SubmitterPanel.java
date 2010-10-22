/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SubmitterPanel.java
 *
 * Created on 5 oct. 2010, 00:01:03
 */
package genjfr.app.editorstd;

import genj.gedcom.Entity;
import genj.gedcom.Submitter;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.UndoRedo.Manager;
import org.openide.util.NbBundle;

/**
 *
 * @author frederic
 */
public class SubmitterPanel extends EntityPanel {

    private Entity entity;

    /** Creates new form SubmPanel */
    public SubmitterPanel(Entity entity) {
        this.entity = entity;
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        editPane = new javax.swing.JTabbedPane();
        addressStructureBeanPanel = new genjfr.app.editorstd.beans.AddressStructureBeanPanel();
        jTextField2 = new javax.swing.JTextField();

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 14));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(org.openide.util.NbBundle.getMessage(SubmitterPanel.class, "SubmitterPanel.jLabel1.text")); // NOI18N

        jTextField1.setText(org.openide.util.NbBundle.getMessage(SubmitterPanel.class, "SubmitterPanel.jTextField1.text")); // NOI18N

        editPane.addTab(org.openide.util.NbBundle.getMessage(SubmitterPanel.class, "SubmitterPanel.addressStructureBeanPanel.TabConstraints.tabTitle"), addressStructureBeanPanel); // NOI18N

        jTextField2.setText(org.openide.util.NbBundle.getMessage(SubmitterPanel.class, "SubmitterPanel.jTextField2.text")); // NOI18N
        editPane.addTab(org.openide.util.NbBundle.getMessage(SubmitterPanel.class, "SubmitterPanel.jTextField2.TabConstraints.tabTitle"), jTextField2); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(editPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(editPane, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private genjfr.app.editorstd.beans.AddressStructureBeanPanel addressStructureBeanPanel;
    private javax.swing.JTabbedPane editPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void init() {
        addressStructureBeanPanel.init(getTabIndex(editPane, addressStructureBeanPanel));
    }

    @Override
    public void checkIfModified() {
        if (addressStructureBeanPanel.isModified) {
            NotifyDescriptor d = new NotifyDescriptor.Confirmation(NbBundle.getMessage(SubmitterPanel.class, "CTL_SaveUnsaved"),
                    NbBundle.getMessage(SubmitterPanel.class, "CTL_AskConfirmation"),
                    NotifyDescriptor.YES_NO_OPTION);
            if (DialogDisplayer.getDefault().notify(d) == NotifyDescriptor.NO_OPTION) {
                loadEntity(entity);
                displayEntity();
            } else {
                saveEntity();
            }
        }
    }

    @Override
    public boolean isModified() {
        if (addressStructureBeanPanel.isModified) {
            return true;
        }
        return false;
    }

    @Override
    public void loadEntity(Entity entity) {
        this.entity = entity;
        Submitter submitter = (Submitter) entity;
        jTextField1.setText(submitter.toString());
        addressStructureBeanPanel.setProperties(entity);
    }

    @Override
    public void displayEntity() {
        addressStructureBeanPanel.displayProperties();
    }

    @Override
    public void saveEntity() {
        addressStructureBeanPanel.saveProperties();
    }

    @Override
    public Entity getEntity() {
        return entity;
    }

    @Override
    void setManagers(Manager URmanager, EditorStdTopComponent editor) {
        addressStructureBeanPanel.setManagers(URmanager, editor);
    }
}
