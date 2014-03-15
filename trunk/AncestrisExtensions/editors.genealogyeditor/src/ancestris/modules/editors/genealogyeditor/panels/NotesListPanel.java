package ancestris.modules.editors.genealogyeditor.panels;

import ancestris.modules.editors.genealogyeditor.models.NotesTableModel;
import ancestris.modules.editors.genealogyeditor.renderer.TextPaneTableCellRenderer;
import ancestris.util.swing.DialogManager;
import ancestris.util.swing.DialogManager.ADialog;
import genj.gedcom.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.table.TableCellRenderer;
import org.openide.DialogDescriptor;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;

/**
 *
 * @author dominique
 */
public class NotesListPanel extends javax.swing.JPanel {

    private Property mRoot;
    private NotesTableModel mNotesTableModel = new NotesTableModel();
    private Note mNote;

    /**
     * Creates new form NotesListPanel
     */
    public NotesListPanel() {
        initComponents();
        notesTable.setID(NotesListPanel.class.getName());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        notesToolBar = new javax.swing.JToolBar();
        addNoteButton = new javax.swing.JButton();
        editNoteButton = new javax.swing.JButton();
        deleteNoteButton = new javax.swing.JButton();
        linkToNoteButton = new javax.swing.JButton();
        notesTableScrollPane = new javax.swing.JScrollPane();
        notesTable = new ancestris.modules.editors.genealogyeditor.table.EditorTable() {
            public TableCellRenderer getCellRenderer(int row, int column) {
                if (column == 1) {
                    return new TextPaneTableCellRenderer ();
                }
                return super.getCellRenderer(row, column);
            }
        };

        notesToolBar.setFloatable(false);
        notesToolBar.setRollover(true);

        addNoteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit_add.png"))); // NOI18N
        addNoteButton.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NotesListPanel.addNoteButton.toolTipText"), new Object[] {})); // NOI18N
        addNoteButton.setFocusable(false);
        addNoteButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addNoteButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addNoteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNoteButtonActionPerformed(evt);
            }
        });
        notesToolBar.add(addNoteButton);

        editNoteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit.png"))); // NOI18N
        editNoteButton.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NotesListPanel.editNoteButton.toolTipText"), new Object[] {})); // NOI18N
        editNoteButton.setFocusable(false);
        editNoteButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editNoteButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editNoteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editNoteButtonActionPerformed(evt);
            }
        });
        notesToolBar.add(editNoteButton);

        deleteNoteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit_delete.png"))); // NOI18N
        deleteNoteButton.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NotesListPanel.deleteNoteButton.toolTipText"), new Object[] {})); // NOI18N
        deleteNoteButton.setFocusable(false);
        deleteNoteButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteNoteButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        deleteNoteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteNoteButtonActionPerformed(evt);
            }
        });
        notesToolBar.add(deleteNoteButton);

        linkToNoteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/link_add.png"))); // NOI18N
        linkToNoteButton.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NotesListPanel.linkToNoteButton.toolTipText"), new Object[] {})); // NOI18N
        linkToNoteButton.setFocusable(false);
        linkToNoteButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        linkToNoteButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        linkToNoteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linkToNoteButtonActionPerformed(evt);
            }
        });
        notesToolBar.add(linkToNoteButton);

        notesTable.setAutoCreateRowSorter(true);
        notesTable.setModel(mNotesTableModel);
        notesTable.setSelectionBackground(new java.awt.Color(89, 142, 195));
        notesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                notesTableMouseClicked(evt);
            }
        });
        notesTableScrollPane.setViewportView(notesTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(notesToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
            .addComponent(notesTableScrollPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(notesToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(notesTableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addNoteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNoteButtonActionPerformed
        Gedcom gedcom = mRoot.getGedcom();
        int undoNb = gedcom.getUndoNb();
        try {
            gedcom.doUnitOfWork(new UnitOfWork() {

                @Override
                public void perform(Gedcom gedcom) throws GedcomException {
                    mNote = (Note) gedcom.createEntity(Gedcom.NOTE);
                }
            }); // end of doUnitOfWork

            NoteEditorPanel noteEditorPanel = new NoteEditorPanel();
            noteEditorPanel.set(mRoot.getGedcom(), mRoot, mNote);
            ADialog noteEditorDialog = new ADialog(
                    NbBundle.getMessage(NoteEditorPanel.class, "NoteEditorPanel.create.title"),
                    noteEditorPanel);
            noteEditorDialog.setDialogId(NoteEditorPanel.class.getName());
            if (noteEditorDialog.show() == DialogDescriptor.OK_OPTION) {
                final Note commitedNote = (Note) noteEditorPanel.commit();
                mNotesTableModel.add(commitedNote);
                gedcom.doUnitOfWork(new UnitOfWork() {

                    @Override
                    public void perform(Gedcom gedcom) throws GedcomException {
                        mRoot.addNote(commitedNote);
                    }
                }); // end of doUnitOfWork
            } else {
                while (gedcom.getUndoNb() > undoNb && gedcom.canUndo()) {
                    gedcom.undoUnitOfWork(false);
                }
            }
        } catch (GedcomException ex) {
            Exceptions.printStackTrace(ex);
        }
    }//GEN-LAST:event_addNoteButtonActionPerformed

    private void editNoteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editNoteButtonActionPerformed
        int selectedRow = notesTable.getSelectedRow();
        Gedcom gedcom = mRoot.getGedcom();
        int undoNb = gedcom.getUndoNb();
        if (selectedRow != -1) {
            int rowIndex = notesTable.convertRowIndexToModel(selectedRow);
            NoteEditorPanel noteEditorPanel = new NoteEditorPanel();
            noteEditorPanel.set(mRoot.getGedcom(), mRoot, mNotesTableModel.getValueAt(rowIndex));

            ADialog noteEditorDialog = new ADialog(
                    NbBundle.getMessage(NoteEditorPanel.class, "NoteEditorPanel.edit.title"),
                    noteEditorPanel);
            noteEditorDialog.setDialogId(NoteEditorPanel.class.getName());

            if (noteEditorDialog.show() == DialogDescriptor.OK_OPTION) {
                noteEditorPanel.commit();
            } else {
                while (gedcom.getUndoNb() > undoNb && gedcom.canUndo()) {
                    gedcom.undoUnitOfWork(false);
                }
            }
        }
    }//GEN-LAST:event_editNoteButtonActionPerformed

    private void deleteNoteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteNoteButtonActionPerformed
        int selectedRow = notesTable.getSelectedRow();
        if (selectedRow != -1) {
            final int rowIndex = notesTable.convertRowIndexToModel(selectedRow);
            DialogManager createYesNo = DialogManager.createYesNo(
                    NbBundle.getMessage(
                            NotesListPanel.class, "NotesListPanel.deleteNote.title"),
                    NbBundle.getMessage(
                            NotesListPanel.class, "NotesListPanel.deleteNote.text",
                            mRoot));
            if (createYesNo.show() == DialogManager.YES_OPTION) {
                try {
                    mRoot.getGedcom().doUnitOfWork(new UnitOfWork() {

                        @Override
                        public void perform(Gedcom gedcom) throws GedcomException {
                            mRoot.delProperty(mNotesTableModel.remove(rowIndex));

                        }
                    }); // end of doUnitOfWork
                } catch (GedcomException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
    }//GEN-LAST:event_deleteNoteButtonActionPerformed

    private void linkToNoteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linkToNoteButtonActionPerformed
        List<Note> notesList = new ArrayList<Note>((Collection<Note>) mRoot.getGedcom().getEntities(Gedcom.NOTE));

        NotesListPanel notesListPanel = new NotesListPanel();
        notesListPanel.setNotesList(mRoot, notesList);
        notesListPanel.setToolBarVisible(false);
        DialogManager.ADialog individualsListDialog = new DialogManager.ADialog(
                NbBundle.getMessage(NoteEditorPanel.class, "NoteEditorPanel.title"),
                notesListPanel);
        individualsListDialog.setDialogId(NoteEditorPanel.class.getName());

        if (individualsListDialog.show() == DialogDescriptor.OK_OPTION) {
            final Note selectedNote = notesListPanel.getSelectedNote();
            mNotesTableModel.add(selectedNote);
            try {
                mRoot.getGedcom().doUnitOfWork(new UnitOfWork() {

                    @Override
                    public void perform(Gedcom gedcom) throws GedcomException {
                        mRoot.addNote(selectedNote);
                    }
                }); // end of doUnitOfWork
            } catch (GedcomException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }//GEN-LAST:event_linkToNoteButtonActionPerformed

    private void notesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notesTableMouseClicked
        if (evt.getClickCount() >= 2) {
            int selectedRow = notesTable.getSelectedRow();
            Gedcom gedcom = mRoot.getGedcom();
            int undoNb = gedcom.getUndoNb();
            if (selectedRow != -1) {
                int rowIndex = notesTable.convertRowIndexToModel(selectedRow);
                NoteEditorPanel noteEditorPanel = new NoteEditorPanel();
                noteEditorPanel.set(mRoot.getGedcom(), mRoot, mNotesTableModel.getValueAt(rowIndex));

                ADialog noteEditorDialog = new ADialog(
                        NbBundle.getMessage(NoteEditorPanel.class, "NoteEditorPanel.edit.title"),
                        noteEditorPanel);
                noteEditorDialog.setDialogId(NoteEditorPanel.class.getName());

                if (noteEditorDialog.show() == DialogDescriptor.OK_OPTION) {
                    noteEditorPanel.commit();
                } else {
                    while (gedcom.getUndoNb() > undoNb && gedcom.canUndo()) {
                        gedcom.undoUnitOfWork(false);
                    }
                }
            }
        }
    }//GEN-LAST:event_notesTableMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addNoteButton;
    private javax.swing.JButton deleteNoteButton;
    private javax.swing.JButton editNoteButton;
    private javax.swing.JButton linkToNoteButton;
    private ancestris.modules.editors.genealogyeditor.table.EditorTable notesTable;
    private javax.swing.JScrollPane notesTableScrollPane;
    private javax.swing.JToolBar notesToolBar;
    // End of variables declaration//GEN-END:variables

    public void setNotesList(Property root, List<Note> notesList) {
        this.mRoot = root;
        mNotesTableModel.update(notesList);
    }

    public void setToolBarVisible(boolean visible) {
        notesToolBar.setVisible(visible);
    }

    public Note getSelectedNote() {
        int selectedRow = notesTable.getSelectedRow();
        if (selectedRow != -1) {
            int rowIndex = notesTable.convertRowIndexToModel(selectedRow);
            return mNotesTableModel.getValueAt(rowIndex);
        } else {
            return null;
        }
    }
}
