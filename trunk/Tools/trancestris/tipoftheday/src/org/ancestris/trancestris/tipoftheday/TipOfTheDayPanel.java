/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ancestris.trancestris.tipoftheday;

import javax.swing.text.html.HTMLDocument;

/**
 *
 * @author lemovice
 */
public class TipOfTheDayPanel extends javax.swing.JPanel {

    /**
     * Creates new form TipOfTheDayPanel
     */
    public TipOfTheDayPanel() {
        initComponents();
    }

    /**
     * @return the jTextArea1
     */
    public String getText() {
        return jTextPane1.getText();
    }

    /**
     * @param jTextArea1 the jTextArea1 to set
     */
    public void setText(String text) {
        jTextPane1.setText(text);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane(new HTMLDocument ());
        jTextPane1.setContentType("text/html");

        setMaximumSize(new java.awt.Dimension(405, 225));
        setMinimumSize(new java.awt.Dimension(405, 225));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(405, 225));
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 0, 0};
        layout.rowHeights = new int[] {0, 0, 0};
        setLayout(layout);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ancestris/trancestris/tipoftheday/light_bulb_on.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        add(jLabel1, gridBagConstraints);

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        jTextField1.setText(org.openide.util.NbBundle.getMessage(TipOfTheDayPanel.class, "TipOfTheDayPanel.jTextField1.text")); // NOI18N
        jTextField1.setBorder(null);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.weightx = 1.0;
        add(jTextField1, gridBagConstraints);

        jTextPane1.setEditable(false);
        jTextPane1.setMaximumSize(new java.awt.Dimension(160, 90));
        jScrollPane2.setViewportView(jTextPane1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jScrollPane2, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
}
