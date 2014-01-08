package ancestris.modules.editors.genealogyeditor.panels;

import ancestris.modules.editors.genealogyeditor.models.FamiliesTreeTableModel;
import ancestris.util.swing.DialogManager;
import genj.gedcom.*;
import java.util.ArrayList;
import java.util.List;
import org.openide.DialogDescriptor;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;

/**
 *
 * @author dominique
 */
public class FamiliesTreeTablePanel extends javax.swing.JPanel {

    public static int LIST_FAM = 0;
    public static int EDIT_FAMC = 1;
    public static int EDIT_FAMS = 2;
    private int mFamilyEditingType = EDIT_FAMC;
    private Property mRoot;
    private Fam mCreateFamily = null;

    /**
     * Creates new form FamiliesTreeTablePanel
     */
    public FamiliesTreeTablePanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        familiesTreeTable = new org.jdesktop.swingx.JXTreeTable(new FamiliesTreeTableModel());
        familyNamesToolBar = new javax.swing.JToolBar();
        addFamilyNameButton = new javax.swing.JButton();
        linkToFamilyButton = new javax.swing.JButton();
        editFamilyNameButton = new javax.swing.JButton();
        deleteFamilyNameButton = new javax.swing.JButton();

        jScrollPane1.setViewportView(familiesTreeTable);

        familyNamesToolBar.setFloatable(false);
        familyNamesToolBar.setRollover(true);

        addFamilyNameButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit_add.png"))); // NOI18N
        addFamilyNameButton.setFocusable(false);
        addFamilyNameButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addFamilyNameButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addFamilyNameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFamilyNameButtonActionPerformed(evt);
            }
        });
        familyNamesToolBar.add(addFamilyNameButton);

        linkToFamilyButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/link_add.png"))); // NOI18N
        linkToFamilyButton.setFocusable(false);
        linkToFamilyButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        linkToFamilyButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        linkToFamilyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linkToFamilyButtonActionPerformed(evt);
            }
        });
        familyNamesToolBar.add(linkToFamilyButton);

        editFamilyNameButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit.png"))); // NOI18N
        editFamilyNameButton.setFocusable(false);
        editFamilyNameButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editFamilyNameButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editFamilyNameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editFamilyNameButtonActionPerformed(evt);
            }
        });
        familyNamesToolBar.add(editFamilyNameButton);

        deleteFamilyNameButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit_delete.png"))); // NOI18N
        deleteFamilyNameButton.setFocusable(false);
        deleteFamilyNameButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteFamilyNameButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        deleteFamilyNameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteFamilyNameButtonActionPerformed(evt);
            }
        });
        familyNamesToolBar.add(deleteFamilyNameButton);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
            .addComponent(familyNamesToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(familyNamesToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addFamilyNameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFamilyNameButtonActionPerformed
        Gedcom gedcom = mRoot.getGedcom();
        try {
            gedcom.doUnitOfWork(new UnitOfWork() {

                @Override
                public void perform(Gedcom gedcom) throws GedcomException {
                    mCreateFamily = (Fam) gedcom.createEntity(Gedcom.FAM);
                    if (mFamilyEditingType == EDIT_FAMC) {
                        mCreateFamily.addChild((Indi) mRoot);
                    } else if (mFamilyEditingType == EDIT_FAMS) {
                        if (((Indi) mRoot).getSex() == PropertySex.MALE) {
                            mCreateFamily.setHusband((Indi) mRoot);
                        } else {
                            mCreateFamily.setWife((Indi) mRoot);
                        }
                    }
                }
            }); // end of doUnitOfWork

            FamilyEditorPanel familyEditorPanel = new FamilyEditorPanel();
            familyEditorPanel.set(mCreateFamily);

            DialogManager.ADialog familyEditorDialog = new DialogManager.ADialog(
                    NbBundle.getMessage(FamilyEditorPanel.class, "FamilyEditorPanel.create.title"),
                    familyEditorPanel);
            familyEditorDialog.setDialogId(FamilyEditorPanel.class.getName());

            if (familyEditorDialog.show() == DialogDescriptor.OK_OPTION) {
                ((FamiliesTreeTableModel) familiesTreeTable.getTreeTableModel()).add(familyEditorPanel.commit());
            } else {
                while (gedcom.canUndo()) {
                    gedcom.undoUnitOfWork(false);
                }
            }
        } catch (GedcomException ex) {
            Exceptions.printStackTrace(ex);
        }
    }//GEN-LAST:event_addFamilyNameButtonActionPerformed

    private void linkToFamilyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linkToFamilyButtonActionPerformed
        FamiliesListPanel familiesListPanel = new FamiliesListPanel(LIST_FAM);
        familiesListPanel.setFamiliesList(mRoot, new ArrayList(mRoot.getGedcom().getFamilies()));
        DialogManager.ADialog familiesListDialog = new DialogManager.ADialog(
                NbBundle.getMessage(FamiliesListPanel.class, "FamiliesListPanel.linkto.title"),
                familiesListPanel);
        familiesListDialog.setDialogId(FamiliesListPanel.class.getName());

        if (familiesListDialog.show() == DialogDescriptor.OK_OPTION) {
            final Fam selectedFamily = familiesListPanel.getSelectedFamily();
            if (selectedFamily != null) {
                try {
                    mRoot.getGedcom().doUnitOfWork(new UnitOfWork() {

                        @Override
                        public void perform(Gedcom gedcom) throws GedcomException {
                            if (mFamilyEditingType == EDIT_FAMC) {
                                selectedFamily.addChild((Indi) mRoot);
                            } else if (mFamilyEditingType == EDIT_FAMS) {
                                if (((Indi) mRoot).getSex() == PropertySex.MALE) {
                                    selectedFamily.setHusband((Indi) mRoot);
                                } else {
                                    selectedFamily.setWife((Indi) mRoot);
                                }
                            }
                        }
                    }); // end of doUnitOfWork
                    ((FamiliesTreeTableModel) familiesTreeTable.getTreeTableModel()).add(selectedFamily);
                } catch (GedcomException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
    }//GEN-LAST:event_linkToFamilyButtonActionPerformed

    private void editFamilyNameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editFamilyNameButtonActionPerformed
        int rowIndex = familiesTreeTable.convertRowIndexToModel(familiesTreeTable.getSelectedRow());
        /*
         * if (rowIndex != -1) {
         * ((FamiliesTreeTableModel) familiesTreeTable.getTreeTableModel()).
         * Fam family = mFamiliesTableModel.getValueAt(rowIndex);
         * FamilyEditorPanel familyEditorPanel = new FamilyEditorPanel();
         * familyEditorPanel.set(family);
         *
         * DialogManager.ADialog familyEditorDialog = new DialogManager.ADialog(
         * NbBundle.getMessage(FamilyEditorPanel.class, "FamilyEditorPanel.edit.title", family),
         * familyEditorPanel);
         * familyEditorDialog.setDialogId(FamilyEditorPanel.class.getName());
         *
         * if (familyEditorDialog.show() == DialogDescriptor.OK_OPTION) {
         * familyEditorPanel.commit();
         * } else {
         * Gedcom gedcom = mRoot.getGedcom();
         * while (gedcom.canUndo()) {
         * gedcom.undoUnitOfWork(false);
         * }
         * }
         * }
         */
    }//GEN-LAST:event_editFamilyNameButtonActionPerformed

    private void deleteFamilyNameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteFamilyNameButtonActionPerformed
        final int selectedRow = familiesTreeTable.getSelectedRow();
        Gedcom gedcom = mRoot.getGedcom();

        if (selectedRow != -1) {
            try {
                gedcom.doUnitOfWork(new UnitOfWork() {

                    @Override
                    public void perform(Gedcom gedcom) throws GedcomException {
                        mRoot.delProperty(((FamiliesTreeTableModel) familiesTreeTable.getTreeTableModel()).remove(familiesTreeTable.convertRowIndexToModel(selectedRow)));
                    }
                }); // end of doUnitOfWork
            } catch (GedcomException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }//GEN-LAST:event_deleteFamilyNameButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addFamilyNameButton;
    private javax.swing.JButton deleteFamilyNameButton;
    private javax.swing.JButton editFamilyNameButton;
    private org.jdesktop.swingx.JXTreeTable familiesTreeTable;
    private javax.swing.JToolBar familyNamesToolBar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton linkToFamilyButton;
    // End of variables declaration//GEN-END:variables

    public void setFamiliesList(Property root, List<Fam> familiesList) {
        this.mRoot = root;
        ((FamiliesTreeTableModel) familiesTreeTable.getTreeTableModel()).addAll(familiesList);
    }
}
