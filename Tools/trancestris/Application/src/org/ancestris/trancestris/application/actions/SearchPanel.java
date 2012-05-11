/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SearchPanel.java
 *
 * Created on 30 avr. 2012, 16:31:25
 */
package org.ancestris.trancestris.application.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import org.ancestris.trancestris.resources.ZipArchive;
import org.openide.util.NbBundle;

/**
 *
 * @author lemovice
 */
public class SearchPanel extends javax.swing.JPanel {

    ZipArchive zipArchive = null;

    /** Creates new form SearchPanel */
    public SearchPanel(ZipArchive zipArchive) {
        this.zipArchive = zipArchive;
        initComponents();
        fromLocaleCheckBox.setText(zipArchive.getFromLocale().getDisplayLanguage());
        toLocaleCheckBox.setText(zipArchive.getToLocale().getDisplayLanguage());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        localeButtonGroup = new ButtonGroup();
        expressionTextField = new JTextField();
        resultScrollPane = new JScrollPane();
        resultTextArea = new JTextArea();
        searchButton = new JButton();
        fromLocaleCheckBox = new JCheckBox();
        toLocaleCheckBox = new JCheckBox();
        caseSensitiveCheckBox = new JCheckBox();

        expressionTextField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                expressionTextFieldKeyPressed(evt);
            }
        });

        resultTextArea.setColumns(20);
        resultTextArea.setRows(5);
        resultScrollPane.setViewportView(resultTextArea);

        searchButton.setText(NbBundle.getMessage(SearchPanel.class, "SearchPanel.searchButton.text")); // NOI18N
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        localeButtonGroup.add(fromLocaleCheckBox);
        fromLocaleCheckBox.setSelected(true);
        fromLocaleCheckBox.setText("From Locale");

        localeButtonGroup.add(toLocaleCheckBox);
        toLocaleCheckBox.setText("To Locale");

        caseSensitiveCheckBox.setSelected(true);

        caseSensitiveCheckBox.setText(NbBundle.getMessage(SearchPanel.class, "SearchPanel.caseSensitiveCheckBox.text")); // NOI18N
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                    .addComponent(resultScrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(fromLocaleCheckBox)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(toLocaleCheckBox)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(caseSensitiveCheckBox))
                            .addComponent(expressionTextField, GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE))
                        .addPreferredGap(ComponentPlacement.RELATED)
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
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(expressionTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(resultScrollPane, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        search();
    }//GEN-LAST:event_searchButtonActionPerformed

    private void expressionTextFieldKeyPressed(KeyEvent evt) {//GEN-FIRST:event_expressionTextFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            search();
        }
    }//GEN-LAST:event_expressionTextFieldKeyPressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JCheckBox caseSensitiveCheckBox;
    private JTextField expressionTextField;
    private JCheckBox fromLocaleCheckBox;
    private ButtonGroup localeButtonGroup;
    private JScrollPane resultScrollPane;
    private JTextArea resultTextArea;
    private JButton searchButton;
    private JCheckBox toLocaleCheckBox;
    // End of variables declaration//GEN-END:variables

    private void search() {
        List<String> search = null;
        boolean caseSensitive = caseSensitiveCheckBox.isSelected();
        if (fromLocaleCheckBox.isSelected()) {
            search = zipArchive.search(expressionTextField.getText(), true, caseSensitive);
        } else {
            search = zipArchive.search(expressionTextField.getText(), false, caseSensitive);
        }
        // Clear the Text Area
        resultTextArea.setText("");
        if (search.isEmpty()) {
            resultTextArea.append(NbBundle.getMessage(SearchPanel.class, "SearchPanel.searchResult.text", expressionTextField.getText()));
        } else {
            for (String dirName : search) {
                resultTextArea.append(dirName + "\n");
            }
        }
    }
}
