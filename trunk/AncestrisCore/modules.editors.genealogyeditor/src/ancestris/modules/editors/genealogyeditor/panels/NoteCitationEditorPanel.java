package ancestris.modules.editors.genealogyeditor.panels;

import genj.gedcom.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;

/**
 *
 * @author dominique
 */
public class NoteCitationEditorPanel extends javax.swing.JPanel {

    private Property mNote;
    private boolean mNoteModified = false;
    private Gedcom mGedcom;
    private Property mParent;

    /**
     * Creates new form NoteEditorPanel
     */
    public NoteCitationEditorPanel() {
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
        inlineNoteCheckBox = new javax.swing.JCheckBox();
        changeDateLabel = new javax.swing.JLabel();
        changeDateLabeldate = new javax.swing.JLabel();

        noteIDLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NoteCitationEditorPanel.noteIDLabel.text"), new Object[] {})); // NOI18N

        noteIDTextField.setEditable(false);
        noteIDTextField.setColumns(8);
        noteIDTextField.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NoteCitationEditorPanel.noteIDTextField.text"), new Object[] {})); // NOI18N

        noteTextToolBar.setFloatable(false);
        noteTextToolBar.setRollover(true);

        noteTextTextArea.setColumns(20);
        noteTextTextArea.setLineWrap(true);
        noteTextTextArea.setRows(5);
        noteTextTextArea.setWrapStyleWord(true);
        noteTextScrollPane.setViewportView(noteTextTextArea);

        javax.swing.GroupLayout noteTextPanelLayout = new javax.swing.GroupLayout(noteTextPanel);
        noteTextPanel.setLayout(noteTextPanelLayout);
        noteTextPanelLayout.setHorizontalGroup(
            noteTextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(noteTextToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(noteTextScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
        );
        noteTextPanelLayout.setVerticalGroup(
            noteTextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(noteTextPanelLayout.createSequentialGroup()
                .addComponent(noteTextToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(noteTextScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
        );

        noteInformationTabbedPane.addTab(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NoteCitationEditorPanel.noteTextPanel.TabConstraints.tabTitle"), new Object[] {}), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/Note.png")), noteTextPanel); // NOI18N

        javax.swing.GroupLayout noteReferencesPanelLayout = new javax.swing.GroupLayout(noteReferencesPanel);
        noteReferencesPanel.setLayout(noteReferencesPanelLayout);
        noteReferencesPanelLayout.setHorizontalGroup(
            noteReferencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(referencesListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
        );
        noteReferencesPanelLayout.setVerticalGroup(
            noteReferencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(referencesListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
        );

        noteInformationTabbedPane.addTab(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NoteCitationEditorPanel.noteReferencesPanel.TabConstraints.tabTitle"), new Object[] {}), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/association.png")), noteReferencesPanel); // NOI18N

        inlineNoteCheckBox.setText(org.openide.util.NbBundle.getMessage(NoteCitationEditorPanel.class, "NoteCitationEditorPanel.inlineNoteCheckBox.text")); // NOI18N
        inlineNoteCheckBox.setToolTipText(org.openide.util.NbBundle.getMessage(NoteCitationEditorPanel.class, "NoteCitationEditorPanel.inlineNoteCheckBox.toolTipText")); // NOI18N
        inlineNoteCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inlineNoteCheckBoxActionPerformed(evt);
            }
        });

        changeDateLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        changeDateLabel.setText(org.openide.util.NbBundle.getMessage(NoteCitationEditorPanel.class, "NoteCitationEditorPanel.changeDateLabel.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(noteInformationTabbedPane)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(inlineNoteCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(noteIDLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(noteIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(changeDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(changeDateLabeldate, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noteIDLabel)
                    .addComponent(noteIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inlineNoteCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(noteInformationTabbedPane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(changeDateLabel)
                    .addComponent(changeDateLabeldate))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void inlineNoteCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inlineNoteCheckBoxActionPerformed
        if (inlineNoteCheckBox.isSelected()) {
            noteIDLabel.setVisible(false);
            noteIDTextField.setVisible(false);
            changeDateLabel.setVisible(false);
            changeDateLabeldate.setVisible(false);
            noteInformationTabbedPane.removeTabAt(noteInformationTabbedPane.indexOfTab(NbBundle.getMessage(NoteCitationEditorPanel.class, "NoteCitationEditorPanel.noteReferencesPanel.TabConstraints.tabTitle")));
        } else {
            noteIDLabel.setVisible(true);
            noteIDTextField.setVisible(true);
            changeDateLabel.setVisible(true);
            changeDateLabeldate.setVisible(true);
            noteInformationTabbedPane.addTab(NbBundle.getMessage(NoteCitationEditorPanel.class, "NoteCitationEditorPanel.noteReferencesPanel.TabConstraints.tabTitle"), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/association.png")), noteReferencesPanel);
        }
    }//GEN-LAST:event_inlineNoteCheckBoxActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel changeDateLabel;
    private javax.swing.JLabel changeDateLabeldate;
    private javax.swing.JCheckBox inlineNoteCheckBox;
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
     * @param gedcom
     * @param parent
     * @param note the note to set
     *
     * @<XREF:NOTE>@ NOTE <SUBMITTER_TEXT>
     * +1 [CONC|CONT] <SUBMITTER_TEXT>
     * +1 REFN <USER_REFERENCE_NUMBER>
     * +2 TYPE <USER_REFERENCE_TYPE>
     * +1 RIN <AUTOMATED_RECORD_ID>
     * +1 <<SOURCE_CITATION>> +1 <<CHANGE_DATE>>
     */
    public void set(Gedcom gedcom, Property parent, Property note) {
        mGedcom = gedcom;
        mParent = parent;

        if (note != null) {
            inlineNoteCheckBox.setVisible(false);

            if (note instanceof PropertyNote) {
                this.mNote = ((PropertyNote) note).getTargetEntity();
            } else {
                this.mNote = note;
            }
            if (mNote instanceof Note) {
                noteIDTextField.setText(((Note) mNote).getId());
                List<Entity> entitiesList = new ArrayList<Entity>();
                for (PropertyXRef entityRef : mNote.getProperties(PropertyXRef.class)) {
                    entitiesList.add(entityRef.getTargetEntity());
                }
                referencesListPanel.set(((Note) mNote), entitiesList);
            } else {
                noteIDTextField.setVisible(false);
                noteIDLabel.setVisible(false);
                changeDateLabel.setVisible(false);
                changeDateLabeldate.setVisible(false);
                noteInformationTabbedPane.removeTabAt(noteInformationTabbedPane.indexOfTab(NbBundle.getMessage(NoteCitationEditorPanel.class, "NoteCitationEditorPanel.noteReferencesPanel.TabConstraints.tabTitle")));
            }

            noteTextTextArea.setText(mNote.getValue() != null ? mNote.getValue() : "");

            Property changeDate = mNote.getProperty("CHAN");
            if (changeDate != null) {
                changeDateLabeldate.setText(((PropertyChange) changeDate).getDisplayValue());
            }
        }

        noteTextTextArea.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                mNoteModified = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                mNoteModified = true;
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                mNoteModified = true;
            }
        });
    }

    public Property get() {
        return mNote;
    }

    public void commit() {

        if (mNoteModified) {
            if (mNote == null) {
                if (inlineNoteCheckBox.isSelected()) {
                    mNote = mParent.addProperty("NOTE", noteTextTextArea.getText());
                } else {
                    try {
                        mNote = mGedcom.createEntity(Gedcom.NOTE);
                    } catch (GedcomException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                    mNote.setValue(noteTextTextArea.getText());
                }
            } else {
                mNote.setValue(noteTextTextArea.getText());
            }
        }
    }
}