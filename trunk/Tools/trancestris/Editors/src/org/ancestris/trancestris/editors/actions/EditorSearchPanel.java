/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EditorSearchPanel.java
 *
 * Created on 7 mai 2012, 18:35:08
 */
package org.ancestris.trancestris.editors.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import org.ancestris.trancestris.editors.resourceeditor.ResourceEditorTopComponent;
import org.ancestris.trancestris.resources.ResourceFile;
import org.openide.util.NbBundle;
import org.openide.windows.WindowManager;

/**
 *
 * @author dominique
 */
public class EditorSearchPanel extends javax.swing.JPanel {

    /** Creates new form EditorSearchPanel */
    public EditorSearchPanel() {
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

        buttonGroup1 = new ButtonGroup();
        buttonGroup2 = new ButtonGroup();
        expressionTextField = new JTextField();
        fromLocaleCheckBox = new JCheckBox();
        caseSensitiveCheckBox = new JCheckBox();
        toLocaleCheckBox = new JCheckBox();
        forwardCheckBox = new JCheckBox();
        backwardCheckBox = new JCheckBox();
        searchButton = new JButton();

        expressionTextField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                expressionTextFieldKeyPressed(evt);
            }
        });

        buttonGroup1.add(fromLocaleCheckBox);
        fromLocaleCheckBox.setSelected(true);
        fromLocaleCheckBox.setText(NbBundle.getMessage(EditorSearchPanel.class, "EditorSearchPanel.fromLocaleCheckBox.text")); // NOI18N

        caseSensitiveCheckBox.setSelected(true);
        caseSensitiveCheckBox.setText(NbBundle.getMessage(EditorSearchPanel.class, "EditorSearchPanel.caseSensitiveCheckBox.text")); // NOI18N

        buttonGroup1.add(toLocaleCheckBox);
        toLocaleCheckBox.setText(NbBundle.getMessage(EditorSearchPanel.class, "EditorSearchPanel.toLocaleCheckBox.text")); // NOI18N

        buttonGroup2.add(forwardCheckBox);
        forwardCheckBox.setSelected(true);
        forwardCheckBox.setText(NbBundle.getMessage(EditorSearchPanel.class, "EditorSearchPanel.forwardCheckBox.text")); // NOI18N

        buttonGroup2.add(backwardCheckBox);

        backwardCheckBox.setText(NbBundle.getMessage(EditorSearchPanel.class, "EditorSearchPanel.backwardCheckBox.text")); // NOI18N
        searchButton.setText(NbBundle.getMessage(EditorSearchPanel.class, "EditorSearchPanel.searchButton.text")); // NOI18N
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                    .addGroup(Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(fromLocaleCheckBox)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(toLocaleCheckBox)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(caseSensitiveCheckBox))
                    .addComponent(expressionTextField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .addGroup(Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(forwardCheckBox)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(backwardCheckBox)
                        .addPreferredGap(ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                        .addComponent(searchButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(toLocaleCheckBox)
                        .addComponent(caseSensitiveCheckBox))
                    .addComponent(fromLocaleCheckBox))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(expressionTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(forwardCheckBox)
                    .addComponent(backwardCheckBox)
                    .addComponent(searchButton))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void expressionTextFieldKeyPressed(KeyEvent evt) {//GEN-FIRST:event_expressionTextFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            search ();
        }
}//GEN-LAST:event_expressionTextFieldKeyPressed

    private void searchButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        search();
    }//GEN-LAST:event_searchButtonActionPerformed

    void search() {
        ResourceEditorTopComponent tc = (ResourceEditorTopComponent) WindowManager.getDefault().findTopComponent("ResourceEditorTopComponent");
        JList resourceFileView = tc.getResourceFileView();
        ResourceFile resourceFile = tc.getResourceFile();
        int index = -1;
        if (resourceFile != null) {
            if (forwardCheckBox.isSelected()) {
                index = resourceFile.searchNext(resourceFileView.getSelectedIndex(), expressionTextField.getText(), fromLocaleCheckBox.isSelected(), caseSensitiveCheckBox.isSelected());
            } else {
                index = resourceFile.searchPrevious(resourceFileView.getSelectedIndex(), expressionTextField.getText(), fromLocaleCheckBox.isSelected(), caseSensitiveCheckBox.isSelected());
            }
            if (index > -1) {
                resourceFileView.setSelectedIndex(index);
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JCheckBox backwardCheckBox;
    private ButtonGroup buttonGroup1;
    private ButtonGroup buttonGroup2;
    private JCheckBox caseSensitiveCheckBox;
    private JTextField expressionTextField;
    private JCheckBox forwardCheckBox;
    private JCheckBox fromLocaleCheckBox;
    private JButton searchButton;
    private JCheckBox toLocaleCheckBox;
    // End of variables declaration//GEN-END:variables
}
