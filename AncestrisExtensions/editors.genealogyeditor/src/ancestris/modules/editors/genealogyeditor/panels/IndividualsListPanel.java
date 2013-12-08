package ancestris.modules.editors.genealogyeditor.panels;

import ancestris.modules.editors.genealogyeditor.models.IndividualsTableModel;
import ancestris.util.swing.DialogManager;
import genj.gedcom.Indi;
import genj.gedcom.Property;
import java.util.List;
import org.openide.DialogDescriptor;
import org.openide.util.NbBundle;

/**
 *
 * @author dominique
 */
public class IndividualsListPanel extends javax.swing.JPanel {

    private IndividualsTableModel mIndividualsTableModel = new IndividualsTableModel();
    private Property mRoot;

    /**
     * Creates new form IndividualsListPanel
     */
    public IndividualsListPanel() {
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

        IndividualsToolBar = new javax.swing.JToolBar();
        addIndividualButton = new javax.swing.JButton();
        editIndividualButton = new javax.swing.JButton();
        deleteIndividualButton = new javax.swing.JButton();
        childrensScrollPane = new javax.swing.JScrollPane();
        individualsTable = new javax.swing.JTable();

        IndividualsToolBar.setFloatable(false);
        IndividualsToolBar.setRollover(true);

        addIndividualButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit_add.png"))); // NOI18N
        addIndividualButton.setFocusable(false);
        addIndividualButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addIndividualButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addIndividualButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addIndividualButtonActionPerformed(evt);
            }
        });
        IndividualsToolBar.add(addIndividualButton);

        editIndividualButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit.png"))); // NOI18N
        editIndividualButton.setFocusable(false);
        editIndividualButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editIndividualButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editIndividualButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editIndividualButtonActionPerformed(evt);
            }
        });
        IndividualsToolBar.add(editIndividualButton);

        deleteIndividualButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit_delete.png"))); // NOI18N
        deleteIndividualButton.setFocusable(false);
        deleteIndividualButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteIndividualButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        deleteIndividualButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteIndividualButtonActionPerformed(evt);
            }
        });
        IndividualsToolBar.add(deleteIndividualButton);

        individualsTable.setModel(mIndividualsTableModel);
        individualsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                individualsTableMouseClicked(evt);
            }
        });
        childrensScrollPane.setViewportView(individualsTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(IndividualsToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
            .addComponent(childrensScrollPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(IndividualsToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(childrensScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addIndividualButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addIndividualButtonActionPerformed
        Indi individual = new Indi();
//        individual.setName("", family.getHusband().getName());
        IndividualEditorPanel individualEditorPanel = new IndividualEditorPanel();
        individualEditorPanel.set(individual);

        DialogManager.ADialog individualEditorDialog = new DialogManager.ADialog(
                NbBundle.getMessage(IndividualEditorPanel.class, "IndividualEditorPanel.title"),
                individualEditorPanel);
        individualEditorDialog.setDialogId(IndividualEditorPanel.class.getName());

        if (individualEditorDialog.show() == DialogDescriptor.OK_OPTION) {
            mIndividualsTableModel.add(individualEditorPanel.getIndividual());
        }
    }//GEN-LAST:event_addIndividualButtonActionPerformed

    private void editIndividualButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editIndividualButtonActionPerformed
        int rowIndex = individualsTable.convertRowIndexToModel(individualsTable.getSelectedRow());
        if (rowIndex != -1) {
            IndividualEditorPanel individualEditorPanel = new IndividualEditorPanel();
            individualEditorPanel.set(mIndividualsTableModel.getValueAt(rowIndex));

            DialogManager.ADialog individualEditorDialog = new DialogManager.ADialog(
                    NbBundle.getMessage(IndividualEditorPanel.class, "IndividualEditorPanel.title"),
                    individualEditorPanel);
            individualEditorDialog.setDialogId(IndividualEditorPanel.class.getName());

            if (individualEditorDialog.show() == DialogDescriptor.OK_OPTION) {
            }
        }
    }//GEN-LAST:event_editIndividualButtonActionPerformed

    private void deleteIndividualButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteIndividualButtonActionPerformed
   }//GEN-LAST:event_deleteIndividualButtonActionPerformed

    private void individualsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_individualsTableMouseClicked
        if (evt.getClickCount() >= 2) {
            int rowIndex = individualsTable.convertRowIndexToModel(individualsTable.getSelectedRow());
            if (rowIndex != -1) {
                IndividualEditorPanel individualEditorPanel = new IndividualEditorPanel();
                individualEditorPanel.set(mIndividualsTableModel.getValueAt(rowIndex));

                DialogManager.ADialog individualEditorDialog = new DialogManager.ADialog(
                        NbBundle.getMessage(IndividualEditorPanel.class, "IndividualEditorPanel.title"),
                        individualEditorPanel);
                individualEditorDialog.setDialogId(IndividualEditorPanel.class.getName());

                if (individualEditorDialog.show() == DialogDescriptor.OK_OPTION) {
                }
            }
        }
    }//GEN-LAST:event_individualsTableMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar IndividualsToolBar;
    private javax.swing.JButton addIndividualButton;
    private javax.swing.JScrollPane childrensScrollPane;
    private javax.swing.JButton deleteIndividualButton;
    private javax.swing.JButton editIndividualButton;
    private javax.swing.JTable individualsTable;
    // End of variables declaration//GEN-END:variables

    public void setIndividualsList(Property root, List<Indi> individualsList) {
        this.mRoot = root;
        mIndividualsTableModel.update(individualsList);
    }

    public Indi getSelectedIndividual() {
        int selectedRow = individualsTable.getSelectedRow();
        if (selectedRow != -1) {
            int rowIndex = individualsTable.convertRowIndexToModel(selectedRow);
            return mIndividualsTableModel.getValueAt(rowIndex);
        } else {
            return null;
        }
    }

    public void commit() {
    }
}
