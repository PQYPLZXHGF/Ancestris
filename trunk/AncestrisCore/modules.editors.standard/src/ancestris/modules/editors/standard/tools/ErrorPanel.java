/*
 * Ancestris - http://www.ancestris.org
 * 
 * Copyright 2016 Ancestris
 * 
 * Author: Frédéric Lapeyre (frederic@ancestris.org).
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */
package ancestris.modules.editors.standard.tools;

import genj.view.ViewContext;
import java.awt.Color;
import java.awt.Component;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import org.netbeans.api.options.OptionsDisplayer;

/**
 *
 * @author frederic
 */
public class ErrorPanel extends javax.swing.JPanel {

    List<ViewContext> errors = null;
    
    /**
     * Creates new form ErrorPanel
     */
    public ErrorPanel(List<ViewContext> errors, boolean showParameters) {
        this.errors = errors;
        initComponents();
        errorsList.setCellRenderer(new ListRenderer());
        paramLabel.setVisible(showParameters);
        paramButton.setVisible(showParameters);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane = new javax.swing.JScrollPane();
        errorsList = new javax.swing.JList();
        paramLabel = new javax.swing.JLabel();
        paramButton = new javax.swing.JButton();

        errorsList.setModel(new javax.swing.AbstractListModel() {
            ViewContext[] lines = errors.toArray(new ViewContext[errors.size()]);

            public int getSize() {
                return lines.length;
            }

            public Object getElementAt(int i) {
                return lines[i];
            }
        });
        errorsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        errorsList.setVisibleRowCount(12);
        jScrollPane.setViewportView(errorsList);

        org.openide.awt.Mnemonics.setLocalizedText(paramLabel, org.openide.util.NbBundle.getMessage(ErrorPanel.class, "ErrorPanel.paramLabel.text")); // NOI18N

        paramButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/standard/images/parameters.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(paramButton, org.openide.util.NbBundle.getMessage(ErrorPanel.class, "ErrorPanel.paramButton.text")); // NOI18N
        paramButton.setPreferredSize(new java.awt.Dimension(24, 24));
        paramButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paramButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(paramButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(paramLabel)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(paramLabel)
                    .addComponent(paramButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void paramButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paramButtonActionPerformed
        OptionsDisplayer.getDefault().open("Extensions/GedcomValidateOptions");
    }//GEN-LAST:event_paramButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList errorsList;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JButton paramButton;
    private javax.swing.JLabel paramLabel;
    // End of variables declaration//GEN-END:variables


    private class ListRenderer extends JLabel implements ListCellRenderer {

        private Color backSelectedColor = null;
        private Color foreSelectedColor = null;
        
        public ListRenderer() {
            // For some reason, selected colors have to be instantiated in order to work when item is selected.
            Color c = new JList().getSelectionBackground();
            backSelectedColor = new Color(c.getRed(), c.getGreen(), c.getBlue());
            c = new JList().getSelectionForeground();
            foreSelectedColor = new Color(c.getRed(), c.getGreen(), c.getBlue());
        }


        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            if (value != null) {
                ViewContext vc = (ViewContext) value;
                setIcon(vc.getImage());
                setText(vc.getText());
                if (isSelected) {
                    setBackground(backSelectedColor);
                    setForeground(foreSelectedColor);
                } else {
                    setBackground(list.getBackground());
                    setForeground(list.getForeground());
                }
                setEnabled(list.isEnabled());
                setFont(list.getFont());
                setOpaque(true);
            }
            return this;
        }

    }

}
