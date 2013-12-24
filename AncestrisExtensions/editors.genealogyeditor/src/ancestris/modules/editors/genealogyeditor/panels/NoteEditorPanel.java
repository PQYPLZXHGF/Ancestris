package ancestris.modules.editors.genealogyeditor.panels;

import ancestris.modules.editors.genealogyeditor.models.ReferencesTableModel;
import genj.gedcom.*;
import java.util.ArrayList;
import java.util.List;
import org.openide.util.Exceptions;

/**
 *
 * @author dominique
 */
public class NoteEditorPanel extends javax.swing.JPanel {

    private Note mNote;
    private ReferencesTableModel mReferencesTableModel = new ReferencesTableModel();

    /**
     * Creates new form NoteEditorPanel
     */
    public NoteEditorPanel() {
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

        noteIDLabel = new javax.swing.JLabel();
        noteIDTextField = new javax.swing.JTextField();
        noteInformationTabbedPane = new javax.swing.JTabbedPane();
        noteTextPanel = new javax.swing.JPanel();
        noteTextToolBar = new javax.swing.JToolBar();
        noteTextScrollPane = new javax.swing.JScrollPane();
        noteTextTextArea = new javax.swing.JTextArea();
        noteReferencesPanel = new javax.swing.JPanel();
        referencesListPanel = new ancestris.modules.editors.genealogyeditor.panels.ReferencesListPanel();

        noteIDLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NoteEditorPanel.noteIDLabel.text"), new Object[] {})); // NOI18N

        noteIDTextField.setColumns(8);
        noteIDTextField.setEditable(false);
        noteIDTextField.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NoteEditorPanel.noteIDTextField.text"), new Object[] {})); // NOI18N

        noteTextToolBar.setFloatable(false);
        noteTextToolBar.setRollover(true);

        noteTextTextArea.setColumns(20);
        noteTextTextArea.setRows(5);
        noteTextScrollPane.setViewportView(noteTextTextArea);

        javax.swing.GroupLayout noteTextPanelLayout = new javax.swing.GroupLayout(noteTextPanel);
        noteTextPanel.setLayout(noteTextPanelLayout);
        noteTextPanelLayout.setHorizontalGroup(
            noteTextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(noteTextToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(noteTextScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        noteTextPanelLayout.setVerticalGroup(
            noteTextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(noteTextPanelLayout.createSequentialGroup()
                .addComponent(noteTextToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(noteTextScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE))
        );

        noteInformationTabbedPane.addTab(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NoteEditorPanel.noteTextPanel.TabConstraints.tabTitle"), new Object[] {}), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/Note.png")), noteTextPanel); // NOI18N

        javax.swing.GroupLayout noteReferencesPanelLayout = new javax.swing.GroupLayout(noteReferencesPanel);
        noteReferencesPanel.setLayout(noteReferencesPanelLayout);
        noteReferencesPanelLayout.setHorizontalGroup(
            noteReferencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(referencesListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        noteReferencesPanelLayout.setVerticalGroup(
            noteReferencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(referencesListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
        );

        noteInformationTabbedPane.addTab(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NoteEditorPanel.noteReferencesPanel.TabConstraints.tabTitle"), new Object[] {}), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/association.png")), noteReferencesPanel); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(noteInformationTabbedPane)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(noteIDLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(noteIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noteIDLabel)
                    .addComponent(noteIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(noteInformationTabbedPane)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel noteIDLabel;
    private javax.swing.JTextField noteIDTextField;
    private javax.swing.JTabbedPane noteInformationTabbedPane;
    private javax.swing.JPanel noteReferencesPanel;
    private javax.swing.JPanel noteTextPanel;
    private javax.swing.JScrollPane noteTextScrollPane;
    private javax.swing.JTextArea noteTextTextArea;
    private javax.swing.JToolBar noteTextToolBar;
    private ancestris.modules.editors.genealogyeditor.panels.ReferencesListPanel referencesListPanel;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the note
     */
    public Note get() {
        return mNote;
    }

    /**
     * @param note the note to set
     * 
     * @<XREF:NOTE>@ NOTE <SUBMITTER_TEXT>
     * +1 [CONC|CONT] <SUBMITTER_TEXT>
     * +1 REFN <USER_REFERENCE_NUMBER>
     * +2 TYPE <USER_REFERENCE_TYPE>
     * +1 RIN <AUTOMATED_RECORD_ID>
     * +1 <<SOURCE_CITATION>>
     * +1 <<CHANGE_DATE>>
     */
    public void set(Note note) {
        this.mNote = note;
        noteIDTextField.setText(note.getId());
        noteTextTextArea.setText(note.getValue() != null ? note.getValue() : "");
        
        List<Entity> entitiesList = new ArrayList<Entity>();
        for (PropertyXRef entityRef : note.getProperties(PropertyXRef.class)) {
            entitiesList.add(entityRef.getTargetEntity());
        }
        referencesListPanel.set(note, entitiesList);
    }

    public Note commit() {
        try {
            mNote.getGedcom().doUnitOfWork(new UnitOfWork() {

                @Override
                public void perform(Gedcom gedcom) throws GedcomException {
                    mNote.setValue(noteTextTextArea.getText());
                }
            }); // end of doUnitOfWork
        } catch (GedcomException ex) {
            Exceptions.printStackTrace(ex);
            return null;
        } finally {
            return mNote;
        }
    }
}
