/*
 * Ancestris - http://www.ancestris.org
 * 
 * Copyright 2015 Ancestris
 * 
 * Author: Frédéric Lapeyre (frederic@ancestris.org).
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */
package modules.editors.gedcomproperties.utils;

import genj.gedcom.PropertyPlace;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.TransferHandler;

/**
 *
 * @author frederic
 */
public class PlaceFormatConverterPanel extends javax.swing.JPanel {

    private final String[] fromFormat;
    private final JTextField[] fromFields;
    private final StringTransferHandler sth;

    private String toPlaceFormat = null;
    private String[] toFormat = null;
    private JTextField[] toFields = null;
    private boolean isValidated = false;
    
    /**
     * Creates new form PlaceFormatConverter
     */
    public PlaceFormatConverterPanel(String fromPlaceFormat, String toPlaceFormat) {
        this.sth = new StringTransferHandler(this);
        initComponents();

        // Create mouse listener
        MouseListener listener = new DragMouseAdapter();
        
        // Init from-fields
        this.fromFormat = PropertyPlace.getFormat(fromPlaceFormat);
        fromFields = new JTextField[fromFormat.length];
        jPanel1.setLayout(new GridLayout(0,1));
        for (int i = 0; i < fromFormat.length; i++) {
            fromFields[i] = new JTextField(fromFormat[i]);
            jPanel1.add(fromFields[i]);
            fromFields[i].setTransferHandler(sth);
            fromFields[i].setDragEnabled(true);
            fromFields[i].addMouseListener(listener);
            fromFields[i].setEditable(false);
            fromFields[i].setDropTarget(null);
        }

        // Init to-fields
        initToFields(toPlaceFormat);
    }

    public final void initToFields(String toPlaceFormat) {
        // return if new place format is null
        if (toPlaceFormat == null) {
            return;
        }
        // return if new place format has already been initialised and remains the same
        if (this.toPlaceFormat != null && this.toPlaceFormat.equals(toPlaceFormat)) {
            return;
        }
        // clear panel if already been initialised and new place format has changed
        if (this.toPlaceFormat != null) {
            jPanel2.removeAll();
            for (JTextField fromField : fromFields) {
                fromField.setEnabled(true);
            }
        }
        
        this.toPlaceFormat = toPlaceFormat;
        this.toFormat = PropertyPlace.getFormat(toPlaceFormat);
        toFields = new JTextField[toFormat.length];
        jPanel2.setLayout(new GridLayout(0,2));
        for (int i = 0; i < toFormat.length; i++) {
            jPanel2.add(new JLabel(toFormat[i]));
            toFields[i] = new JTextField("");
            jPanel2.add(toFields[i]);
            toFields[i].setTransferHandler(sth);
            toFields[i].setEditable(false);
            toFields[i].setDragEnabled(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(570, 470));

        jScrollPane3.setViewportBorder(null);

        jPanel3.setPreferredSize(new java.awt.Dimension(560, 460));

        jLabel1.setFont(new java.awt.Font("Cantarell", 1, 15)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(PlaceFormatConverterPanel.class, "PlaceFormatConverterPanel.jLabel1.text")); // NOI18N

        jLabel2.setFont(new java.awt.Font("Cantarell", 1, 15)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(PlaceFormatConverterPanel.class, "PlaceFormatConverterPanel.jLabel2.text")); // NOI18N

        jLabel3.setFont(new java.awt.Font("Cantarell", 1, 15)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(PlaceFormatConverterPanel.class, "PlaceFormatConverterPanel.jLabel3.text")); // NOI18N

        jScrollPane1.setViewportBorder(null);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 292, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel1);

        jScrollPane2.setViewportBorder(null);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 393, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(jPanel2);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(PlaceFormatConverterPanel.class, "PlaceFormatConverterPanel.jLabel4.text")); // NOI18N

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/modules/editors/gedcomproperties/ressources/Erase.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(PlaceFormatConverterPanel.class, "PlaceFormatConverterPanel.jButton1.text")); // NOI18N
        jButton1.setToolTipText(org.openide.util.NbBundle.getMessage(PlaceFormatConverterPanel.class, "PlaceFormatConverterPanel.jButton1.toolTipText")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE))
                        .addGap(20, 20, 20))
                    .addComponent(jLabel4))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3))
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        for (int i = 0; i < fromFormat.length; i++) {
            fromFields[i].setEnabled(true);
        }
        for (int i = 0; i < toFormat.length; i++) {
            toFields[i].setText("");
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables

    // Make sure that is none of toFields include a fromField, that fromField should be enabled
    void updateDisplay() {
        boolean found = false;
        for (int i = 0; i < fromFields.length; i++) {
            String fromStr = fromFields[i].getText();
            found = false;
            for (JTextField toField : toFields) {
                String toStr = toField.getText();
                if (toStr.equals(fromStr)) {
                    found = true;
                    break;
                }
            }
            if (!found){
                fromFields[i].setEnabled(true);
            }
        }
    }

    public void setValidatedMap(boolean b, String displayedFormat) {
        // Only consider valid calls
        if (displayedFormat != null && displayedFormat.isEmpty()) {
            return;
        }
        if (displayedFormat == null) {
           this.isValidated = b;
           return;
        } 
        if (this.isValidated && this.toPlaceFormat.equals(displayedFormat)) {
           return;
        }
    }
    
    public boolean isValidatedMap() {
        return this.isValidated;
    }
    
    public boolean isMapComplete() {
        // Map is complete if all from-fields are used...
        boolean found = false;
        for (int i = 0; i < fromFields.length; i++) {
            String fromStr = fromFields[i].getText();
            found = false;
            for (JTextField toField : toFields) {
                String toStr = toField.getText();
                if (toStr.equals(fromStr)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }

        // ...and map is complete if all to-fields are not empty...
        for (JTextField toField : toFields) {
            String toStr = toField.getText();
            if (toStr.isEmpty()) {
                return false;
            }
        }
        
        return true;
    }

    /**
     * Sequence of numbers representing the conversionMap
     * newPlaceFormat[i] = oldJuridictions[map[i]];
     * map[new rank] = old rank ==> ex: string of : 2,,4,0,1 => Map[0] = 2, Map[1] left blank, etc
     * 
     */
    public String getConversionMapAsString() {
        String ret = "";
        String sep = PropertyPlace.JURISDICTION_SEPARATOR; // keep this as we use the PropertyPlace info to reverse read.
        int index = -1;
        
        for (int i = 0; i < toFormat.length; i++) {
            if (i == toFormat.length-1) {
                sep = "";
            }
            for (int j = 0; j < fromFormat.length; j++) {
                if (fromFormat[j].equals(toFields[i].getText())) {
                    index = j;
                }
            }
            if (index != -1) {
                ret += index + sep;
            } else {
                ret += "" + sep;
            }
            index = -1;
        }
        return ret;
    }
    
    public class DragMouseAdapter extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            JComponent c = (JComponent) e.getSource();
            TransferHandler handler = c.getTransferHandler();
            handler.exportAsDrag(c, e, TransferHandler.COPY);
        }
    }

    
}
