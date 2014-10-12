package ancestris.modules.editors.genealogyeditor.panels;

import ancestris.modules.editors.genealogyeditor.editors.RepositoryEditor;
import ancestris.modules.editors.genealogyeditor.models.ShelfNumberTableModel;
import ancestris.util.swing.DialogManager;
import ancestris.util.swing.DialogManager.ADialog;
import genj.gedcom.Context;
import genj.gedcom.Gedcom;
import genj.gedcom.GedcomException;
import genj.gedcom.Property;
import genj.gedcom.PropertyRepository;
import genj.gedcom.Repository;
import genj.gedcom.UnitOfWork;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.openide.DialogDescriptor;
import org.openide.util.ChangeSupport;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;

/**
 *
 * @author dominique
 */
public class RepositoryCitationEditorPanel extends javax.swing.JPanel {

    private Property mParentProperty;
    private Repository mRepository;
    private PropertyRepository mRepositoryCitation;
    private final ChangeListner changeListner = new ChangeListner();
    private final ChangeSupport changeSupport = new ChangeSupport(RepositoryCitationEditorPanel.class);
    private final ShelfNumberTableModel mShelfNumberTableModel = new ShelfNumberTableModel();

    /**
     * Creates new form RepositoryCitationPanel
     */
    public RepositoryCitationEditorPanel() {
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
        repositoryTextField = new javax.swing.JTextField();
        repositoryCitationTabbedPane = new javax.swing.JTabbedPane();
        shelfNumberPanel = new javax.swing.JPanel();
        shelfNumberScrollPane = new javax.swing.JScrollPane();
        shelfNumberTable = new javax.swing.JTable();
        shelfNumberToolBar = new javax.swing.JToolBar();
        editShelfNumberButton = new javax.swing.JButton();
        deleteShelfNumberButton = new javax.swing.JButton();
        addShelfNumberButton = new javax.swing.JButton();
        noteCitationsListPanel = new ancestris.modules.editors.genealogyeditor.panels.NoteCitationsListPanel();
        addRepositoryButton = new javax.swing.JButton();
        editRepositoryButton = new javax.swing.JButton();
        deleteRepositoryButton = new javax.swing.JButton();
        linkToRepositoryButton = new javax.swing.JButton();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(RepositoryCitationEditorPanel.class, "RepositoryCitationEditorPanel.jLabel1.text")); // NOI18N

        repositoryTextField.setEditable(false);

        shelfNumberTable.setModel(mShelfNumberTableModel);
        shelfNumberScrollPane.setViewportView(shelfNumberTable);

        shelfNumberToolBar.setFloatable(false);
        shelfNumberToolBar.setRollover(true);

        editShelfNumberButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit.png"))); // NOI18N
        editShelfNumberButton.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("RepositoryCitationEditorPanel.editShelfNumberButton.toolTipText"), new Object[] {})); // NOI18N
        editShelfNumberButton.setFocusable(false);
        editShelfNumberButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editShelfNumberButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editShelfNumberButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editShelfNumberButtonActionPerformed(evt);
            }
        });
        shelfNumberToolBar.add(editShelfNumberButton);

        deleteShelfNumberButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit_delete.png"))); // NOI18N
        deleteShelfNumberButton.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("RepositoryCitationEditorPanel.deleteShelfNumberButton.toolTipText"), new Object[] {})); // NOI18N
        deleteShelfNumberButton.setFocusable(false);
        deleteShelfNumberButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteShelfNumberButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        deleteShelfNumberButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteShelfNumberButtonActionPerformed(evt);
            }
        });
        shelfNumberToolBar.add(deleteShelfNumberButton);

        addShelfNumberButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit_add.png"))); // NOI18N
        addShelfNumberButton.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("RepositoryCitationEditorPanel.addShelfNumberButton.toolTipText"), new Object[] {})); // NOI18N
        addShelfNumberButton.setFocusable(false);
        addShelfNumberButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addShelfNumberButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addShelfNumberButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addShelfNumberButtonActionPerformed(evt);
            }
        });
        shelfNumberToolBar.add(addShelfNumberButton);

        javax.swing.GroupLayout shelfNumberPanelLayout = new javax.swing.GroupLayout(shelfNumberPanel);
        shelfNumberPanel.setLayout(shelfNumberPanelLayout);
        shelfNumberPanelLayout.setHorizontalGroup(
            shelfNumberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(shelfNumberToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(shelfNumberScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
        );
        shelfNumberPanelLayout.setVerticalGroup(
            shelfNumberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shelfNumberPanelLayout.createSequentialGroup()
                .addComponent(shelfNumberToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(shelfNumberScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        repositoryCitationTabbedPane.addTab(org.openide.util.NbBundle.getMessage(RepositoryCitationEditorPanel.class, "RepositoryCitationEditorPanel.shelfNumberPanel.TabConstraints.tabTitle"), shelfNumberPanel); // NOI18N
        repositoryCitationTabbedPane.addTab(org.openide.util.NbBundle.getMessage(RepositoryCitationEditorPanel.class, "RepositoryCitationEditorPanel.noteCitationsListPanel.TabConstraints.tabTitle"), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/Note.png")), noteCitationsListPanel); // NOI18N

        addRepositoryButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit_add.png"))); // NOI18N
        addRepositoryButton.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("RepositoryCitationEditorPanel.addRepositoryButton.toolTipText"), new Object[] {})); // NOI18N
        addRepositoryButton.setFocusable(false);
        addRepositoryButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addRepositoryButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addRepositoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRepositoryButtonActionPerformed(evt);
            }
        });

        editRepositoryButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit.png"))); // NOI18N
        editRepositoryButton.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("RepositoryCitationEditorPanel.editRepositoryButton.toolTipText"), new Object[] {})); // NOI18N
        editRepositoryButton.setFocusable(false);
        editRepositoryButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editRepositoryButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editRepositoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editRepositoryButtonActionPerformed(evt);
            }
        });

        deleteRepositoryButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit_delete.png"))); // NOI18N
        deleteRepositoryButton.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("RepositoryCitationEditorPanel.deleteRepositoryButton.toolTipText"), new Object[] {})); // NOI18N
        deleteRepositoryButton.setFocusable(false);
        deleteRepositoryButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteRepositoryButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        deleteRepositoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteRepositoryButtonActionPerformed(evt);
            }
        });

        linkToRepositoryButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/link_add.png"))); // NOI18N
        linkToRepositoryButton.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("RepositoryCitationEditorPanel.linkToRepositoryButton.toolTipText"), new Object[] {})); // NOI18N
        linkToRepositoryButton.setFocusable(false);
        linkToRepositoryButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        linkToRepositoryButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        linkToRepositoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linkToRepositoryButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(repositoryCitationTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(repositoryTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linkToRepositoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addRepositoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editRepositoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteRepositoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(repositoryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linkToRepositoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteRepositoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editRepositoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addRepositoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(repositoryCitationTabbedPane)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addRepositoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRepositoryButtonActionPerformed
        Gedcom gedcom = mParentProperty.getGedcom();
        int undoNb = gedcom.getUndoNb();

        try {
            gedcom.doUnitOfWork(new UnitOfWork() {

                @Override
                public void perform(Gedcom gedcom) throws GedcomException {
                    mRepository = (Repository) gedcom.createEntity(Gedcom.REPO);
                }
            }); // end of doUnitOfWork

            RepositoryEditor repositoryEditor = new RepositoryEditor();
            repositoryEditor.setContext(new Context(mRepository));
            if (repositoryEditor.showPanel()) {
                mParentProperty.getGedcom().doUnitOfWork(new UnitOfWork() {

                    @Override
                    public void perform(Gedcom gedcom) throws GedcomException {
                        mRepositoryCitation = (PropertyRepository) mParentProperty.addProperty("REPO", '@' + mRepository.getId() + '@');
                        mRepositoryCitation.link();
                    }
                }); // end of doUnitOfWork
                repositoryTextField.setText(mRepository.getValue());
                editRepositoryButton.setVisible(true);
                deleteRepositoryButton.setVisible(true);
                addRepositoryButton.setVisible(false);
                linkToRepositoryButton.setVisible(false);
                changeSupport.fireChange();
            } else {
                while (gedcom.getUndoNb() > undoNb && gedcom.canUndo()) {
                    gedcom.undoUnitOfWork(false);
                }
            }
        } catch (GedcomException ex) {
            Exceptions.printStackTrace(ex);
        }
    }//GEN-LAST:event_addRepositoryButtonActionPerformed

    private void editRepositoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editRepositoryButtonActionPerformed

        RepositoryEditor repositoryEditor = new RepositoryEditor();
        repositoryEditor.setContext(new Context(mRepositoryCitation.getTargetEntity()));
        repositoryEditor.addChangeListener(changeListner);
        repositoryEditor.showPanel();
        repositoryEditor.removeChangeListener(changeListner);
    }//GEN-LAST:event_editRepositoryButtonActionPerformed

    private void deleteRepositoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteRepositoryButtonActionPerformed
        DialogManager createYesNo = DialogManager.createYesNo(NbBundle.getMessage(RepositoryCitationEditorPanel.class, "RepositoriesListPanel.deleteRepository.title",
                mRepositoryCitation),
                NbBundle.getMessage(RepositoryCitationEditorPanel.class, "RepositoriesListPanel.deleteRepository.text",
                        mRepositoryCitation,
                        mParentProperty));
        if (createYesNo.show() == DialogManager.YES_OPTION) {
            try {
                mParentProperty.getGedcom().doUnitOfWork(new UnitOfWork() {

                    @Override
                    public void perform(Gedcom gedcom) throws GedcomException {
                        mParentProperty.delProperty(mRepositoryCitation);
                    }
                }); // end of doUnitOfWork
                editRepositoryButton.setVisible(false);
                deleteRepositoryButton.setVisible(false);
                addRepositoryButton.setVisible(true);
                linkToRepositoryButton.setVisible(true);
                repositoryTextField.setText("");
                changeSupport.fireChange();
            } catch (GedcomException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }//GEN-LAST:event_deleteRepositoryButtonActionPerformed

    private void linkToRepositoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linkToRepositoryButtonActionPerformed
        List<Repository> repositoriesList = new ArrayList<Repository>((Collection<Repository>) mParentProperty.getGedcom().getEntities(Gedcom.REPO));

        RepositoriesListPanel repositoriesListPanel = new RepositoriesListPanel();
        repositoriesListPanel.set(mParentProperty, repositoriesList);
        repositoriesListPanel.setToolBarVisible(false);
        DialogManager.ADialog individualsListDialog = new DialogManager.ADialog(
                NbBundle.getMessage(RepositoryCitationEditorPanel.class, "RepositoriesListPanel.linkTo.title"),
                repositoriesListPanel);
        individualsListDialog.setDialogId(RepositoryCitationEditorPanel.class
                .getName());

        if (individualsListDialog.show() == DialogDescriptor.OK_OPTION) {
            final Repository selectedRepository = repositoriesListPanel.getSelectedRepository();
            if (selectedRepository != null) {
                try {
                    mParentProperty.getGedcom().doUnitOfWork(new UnitOfWork() {

                        @Override
                        public void perform(Gedcom gedcom) throws GedcomException {
                            mRepositoryCitation = (PropertyRepository) mParentProperty.addProperty("REPO", '@' + selectedRepository.getId() + '@');
                            mRepositoryCitation.link();
                            repositoryTextField.setText(selectedRepository.toString());
                        }
                    }); // end of doUnitOfWork
                    editRepositoryButton.setVisible(true);
                    deleteRepositoryButton.setVisible(true);
                    addRepositoryButton.setVisible(false);
                    linkToRepositoryButton.setVisible(false);
                    changeSupport.fireChange();
                } catch (GedcomException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
    }//GEN-LAST:event_linkToRepositoryButtonActionPerformed

    private void editShelfNumberButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editShelfNumberButtonActionPerformed
        int selectedRow = shelfNumberTable.getSelectedRow();
        if (selectedRow != -1) {
            int rowIndex = shelfNumberTable.convertRowIndexToModel(selectedRow);
            Gedcom gedcom = mParentProperty.getGedcom();
            int undoNb = gedcom.getUndoNb();
            final ShelfNumberEditorPanel shelfNumberEditorPanel = new ShelfNumberEditorPanel();
            shelfNumberEditorPanel.set(mParentProperty, mShelfNumberTableModel.getValueAt(rowIndex));
            ADialog sourceCitationEditorDialog = new ADialog(
                    NbBundle.getMessage(SourceCitationEditorPanel.class, "SourceCitationEditorPanel.edit.title",
                            Gedcom.getName(mParentProperty.getTag()),
                            mParentProperty.getEntity()),
                    shelfNumberEditorPanel);

            if (sourceCitationEditorDialog.show() == DialogDescriptor.OK_OPTION) {
                try {
                    gedcom.doUnitOfWork(new UnitOfWork() {

                        @Override
                        public void perform(Gedcom gedcom) throws GedcomException {
                            shelfNumberEditorPanel.commit();
                        }
                    });
                    changeSupport.fireChange();
                } catch (GedcomException ex) {
                    Exceptions.printStackTrace(ex);
                }
            } else {
                while (gedcom.getUndoNb() > undoNb && gedcom.canUndo()) {
                    gedcom.undoUnitOfWork(false);
                }
            }
        }
    }//GEN-LAST:event_editShelfNumberButtonActionPerformed

    private void deleteShelfNumberButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteShelfNumberButtonActionPerformed
        final int selectedRow = shelfNumberTable.getSelectedRow();
        Gedcom gedcom = mParentProperty.getGedcom();

        if (selectedRow != -1) {
            DialogManager createYesNo = DialogManager.createYesNo(
                    NbBundle.getMessage(
                            SourceCitationsListPanel.class, "SourceCitationsListPanel.deleteSourceCitation.title"),
                    NbBundle.getMessage(
                            SourceCitationsListPanel.class, "SourceCitationsListPanel.deleteSourceCitation.text",
                            mParentProperty, mParentProperty.getEntity()));
            if (createYesNo.show() == DialogManager.YES_OPTION) {
                try {
                    mParentProperty.getGedcom().doUnitOfWork(new UnitOfWork() {

                        @Override
                        public void perform(Gedcom gedcom) throws GedcomException {
                            mParentProperty.delProperty(mShelfNumberTableModel.remove(shelfNumberTable.convertRowIndexToModel(selectedRow)));
                        }
                    }); // end of doUnitOfWork
                    changeSupport.fireChange();
                } catch (GedcomException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
    }//GEN-LAST:event_deleteShelfNumberButtonActionPerformed

    private void addShelfNumberButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addShelfNumberButtonActionPerformed

        Gedcom gedcom = mParentProperty.getGedcom();
        int undoNb = gedcom.getUndoNb();
        // create a the source link
        final ShelfNumberEditorPanel shelfNumberEditorPanel = new ShelfNumberEditorPanel();
        shelfNumberEditorPanel.set(mParentProperty, null);
        ADialog sourceCitationEditorDialog = new ADialog(
                NbBundle.getMessage(SourceCitationEditorPanel.class, "SourceCitationEditorPanel.create.title",
                        Gedcom.getName(mParentProperty.getTag()),
                        mParentProperty.getEntity()),
                shelfNumberEditorPanel);

        if (sourceCitationEditorDialog.show() == DialogDescriptor.OK_OPTION) {
            try {
                gedcom.doUnitOfWork(new UnitOfWork() {

                    @Override
                    public void perform(Gedcom gedcom) throws GedcomException {
                        mShelfNumberTableModel.add(shelfNumberEditorPanel.commit());
                    }
                });
                changeSupport.fireChange();
            } catch (GedcomException ex) {
                Exceptions.printStackTrace(ex);
            }
        } else {
            while (gedcom.getUndoNb() > undoNb && gedcom.canUndo()) {
                gedcom.undoUnitOfWork(false);
            }
        }
    }//GEN-LAST:event_addShelfNumberButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addRepositoryButton;
    private javax.swing.JButton addShelfNumberButton;
    private javax.swing.JButton deleteRepositoryButton;
    private javax.swing.JButton deleteShelfNumberButton;
    private javax.swing.JButton editRepositoryButton;
    private javax.swing.JButton editShelfNumberButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton linkToRepositoryButton;
    private ancestris.modules.editors.genealogyeditor.panels.NoteCitationsListPanel noteCitationsListPanel;
    private javax.swing.JTabbedPane repositoryCitationTabbedPane;
    private javax.swing.JTextField repositoryTextField;
    private javax.swing.JPanel shelfNumberPanel;
    private javax.swing.JScrollPane shelfNumberScrollPane;
    private javax.swing.JTable shelfNumberTable;
    private javax.swing.JToolBar shelfNumberToolBar;
    // End of variables declaration//GEN-END:variables

    public void set(Property parentProperty, PropertyRepository repositoryCitation) {
        mParentProperty = parentProperty;
        mRepositoryCitation = repositoryCitation;
        if (mRepositoryCitation != null) {
            editRepositoryButton.setVisible(true);
            deleteRepositoryButton.setVisible(true);
            addRepositoryButton.setVisible(false);
            linkToRepositoryButton.setVisible(false);
            repositoryTextField.setText(mRepositoryCitation.getTargetEntity().toString());
            mShelfNumberTableModel.addAll(Arrays.asList(mRepositoryCitation.getProperties("CALN")));
            noteCitationsListPanel.set(mRepositoryCitation, Arrays.asList(repositoryCitation.getProperties("NOTE")));
        } else {
            editRepositoryButton.setVisible(false);
            deleteRepositoryButton.setVerifyInputWhenFocusTarget(false);
            addRepositoryButton.setVisible(true);
            linkToRepositoryButton.setVisible(true);
            noteCitationsListPanel.set(parentProperty, new ArrayList<Property>());
        }
    }

    public void commit() {

    }

    private class ChangeListner implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent ce) {
            changeSupport.fireChange();
        }
    }
}