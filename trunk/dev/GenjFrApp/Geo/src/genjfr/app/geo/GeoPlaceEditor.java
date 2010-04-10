/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GeoPlaceEditor.java
 *
 * Created on 2 avr. 2010, 22:28:09
 */
package genjfr.app.geo;

import genj.gedcom.Gedcom;
import genj.gedcom.GedcomException;
import genj.gedcom.Property;
import genj.gedcom.PropertyPlace;
import genj.gedcom.UnitOfWork;
import genjfr.app.App;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;
import javax.swing.JOptionPane;
import org.geonames.InsufficientStyleException;
import org.geonames.Toponym;
import org.netbeans.api.options.OptionsDisplayer;
import org.openide.awt.StatusDisplayer;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;
import org.openide.windows.WindowManager;

/**
 *
 * @author frederic
 */
public class GeoPlaceEditor extends javax.swing.JPanel implements PreferenceChangeListener {

    private GeoNodeObject geoObj = null;
    private List<Toponym> list = new ArrayList<Toponym>();
    private boolean isBusy = false;

    /** Creates new form GeoPlaceEditor */
    public GeoPlaceEditor(GeoNodeObject geoObj) {
        this.geoObj = geoObj;
        initComponents();
        enableAddressFields();
        jTextField1.setText(geoObj.getCity());
        fillPlaceFromGeoNode(geoObj);
        jLabel14.setText("");
        jLabel15.setText("");
        jLabel16.setText("");
        jLabel18.setText("");
        jLabel19.setText("");
        jLabel20.setText("");
        jLabel21.setText("");
        jLabel34.setText("");
        WindowManager.getDefault().invokeWhenUIReady(new Runnable() {
            public void run() {
                jButton1.doClick();
            }
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton5 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();

        jButton5.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jButton5.text")); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel27.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jLabel27.text")); // NOI18N

        jLabel11.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jLabel11.text")); // NOI18N

        jLabel28.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jLabel28.text")); // NOI18N

        jLabel25.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jLabel25.text")); // NOI18N

        jLabel9.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jLabel9.text")); // NOI18N

        jLabel26.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jLabel26.text")); // NOI18N

        jLabel10.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jLabel10.text")); // NOI18N

        jLabel30.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jLabel30.text")); // NOI18N

        jLabel7.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jLabel7.text")); // NOI18N

        jLabel8.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jLabel8.text")); // NOI18N

        jLabel29.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jLabel29.text")); // NOI18N

        jLabel6.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jLabel6.text")); // NOI18N

        jLabel32.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jLabel32.text")); // NOI18N

        jLabel31.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jLabel31.text")); // NOI18N

        jLabel14.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jLabel14.text")); // NOI18N

        jLabel15.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jLabel15.text")); // NOI18N

        jLabel16.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jLabel16.text")); // NOI18N

        jLabel18.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jLabel18.text")); // NOI18N

        jLabel19.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jLabel19.text")); // NOI18N

        jLabel20.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jLabel20.text")); // NOI18N

        jLabel21.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jLabel21.text")); // NOI18N

        jLabel1.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jLabel1.text")); // NOI18N

        jLabel4.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jLabel4.text")); // NOI18N

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        jLabel2.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jLabel2.text")); // NOI18N

        jLabel24.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        jLabel24.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jLabel24.text")); // NOI18N

        jButton1.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jTextField1.text")); // NOI18N

        jButton2.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jButton2.text")); // NOI18N
        jButton2.setMaximumSize(new java.awt.Dimension(100, 29));
        jButton2.setMinimumSize(new java.awt.Dimension(100, 29));
        jButton2.setPreferredSize(new java.awt.Dimension(100, 29));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        jLabel3.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jLabel3.text")); // NOI18N

        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jTextField2.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jTextField2.text")); // NOI18N

        jTextField3.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jTextField3.text")); // NOI18N

        jTextField4.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jTextField4.text")); // NOI18N

        jTextField6.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jTextField6.text")); // NOI18N

        jTextField7.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jTextField7.text")); // NOI18N

        jTextField8.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jTextField8.text")); // NOI18N

        jTextField9.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jTextField9.text")); // NOI18N

        jLabel33.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jLabel33.text")); // NOI18N

        jLabel34.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jLabel34.text")); // NOI18N

        jLabel35.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jLabel35.text")); // NOI18N

        jTextField5.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jTextField5.text")); // NOI18N

        jTextField10.setText(org.openide.util.NbBundle.getMessage(GeoPlaceEditor.class, "GeoPlaceEditor.jTextField10.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel11))
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel33)
                                .addGap(57, 57, 57)
                                .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31)
                            .addComponent(jLabel32)
                            .addComponent(jLabel25)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel26))
                            .addComponent(jLabel27)
                            .addComponent(jLabel28)
                            .addComponent(jLabel35))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField10, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField9, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel25)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel14)
                            .addComponent(jLabel26)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel15)
                            .addComponent(jLabel27)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel16)
                            .addComponent(jLabel28)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel18)
                            .addComponent(jLabel29)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel19)
                            .addComponent(jLabel30)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel20)
                            .addComponent(jLabel31)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel21)
                            .addComponent(jLabel32)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(jLabel34)
                            .addComponent(jLabel35)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        isBusy = true;
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        list = geoObj.getToponymsFromPlace(jTextField1.getText(), 30);
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        isBusy = false;
        if (list == null) {
            String msg = NbBundle.getMessage(GeoInternetSearch.class, "ERROR_cannotFindPlace");
            JOptionPane.showMessageDialog(WindowManager.getDefault().getMainWindow(), msg, NbBundle.getMessage(GeoInternetSearch.class, "ERROR_Title"), JOptionPane.ERROR_MESSAGE);
            StatusDisplayer.getDefault().setStatusText(msg, StatusDisplayer.IMPORTANCE_ANNOTATION * 10);
            System.out.println(msg);
            return;
        }
        jList1.setListData(list.toArray());
        if (!list.isEmpty()) {
            jList1.setSelectedIndex(0);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        if (isBusy) {
            return;
        }
        try {
            Toponym topo = (Toponym) jList1.getSelectedValue();
            jLabel14.setText(topo.getName());
            jLabel15.setText(topo.getPostcode());
            jLabel16.setText(topo.getAdminCode4());
            jLabel18.setText(topo.getAdminName2());
            jLabel19.setText(topo.getAdminName1());
            jLabel20.setText(topo.getCountryName());
            jLabel21.setText(Double.toString(topo.getLatitude()));
            jLabel34.setText(Double.toString(topo.getLongitude()));
        } catch (InsufficientStyleException ex) {
        }

    }//GEN-LAST:event_jList1ValueChanged

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //jTextField6.setText("");
        jTextField7.setText(jLabel14.getText());
        jTextField8.setText(jLabel15.getText());
        jTextField9.setText(jLabel16.getText());
        jTextField2.setText(jLabel18.getText());
        jTextField3.setText(jLabel19.getText());
        jTextField4.setText(jLabel20.getText());
        jTextField5.setText(jLabel21.getText());
        jTextField10.setText(jLabel34.getText());
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        OptionsDisplayer.getDefault().open("OptionsData");
    }//GEN-LAST:event_jButton2ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables

    private void fillPlaceFromGeoNode(GeoNodeObject geoObj) {
        int cityIndex = geoObj.getPlace().getCityIndex();
        int max = geoObj.getPlace().getJurisdictions().length - 1;

        // fill in most logical ones
        jTextField7.setText(NbPreferences.forModule(App.class).get("fmt_address2", "").equals("none") ? "" : geoObj.getPlace().getCity());
        if ((cityIndex != max) && (max > 0)) {
            jTextField4.setText(NbPreferences.forModule(App.class).get("fmt_address7", "").equals("none") ? "" : geoObj.getPlace().getJurisdiction(max));
        }
        if ((cityIndex != (max - 1)) && ((max - 1) > 0)) {
            jTextField3.setText(NbPreferences.forModule(App.class).get("fmt_address6", "").equals("none") ? "" : geoObj.getPlace().getJurisdiction(max - 1));
        }
        jTextField5.setText(Double.toString(geoObj.getLatitude()));
        jTextField10.setText(Double.toString(geoObj.getLongitude()));

        // fill in the remaining others until we reach max-1 which is already used
        int i = 0;
        if (!geoObj.getPlace().getJurisdiction(0).equals(geoObj.getPlace().getCity())) {
            jTextField6.setText(NbPreferences.forModule(App.class).get("fmt_address1", "").equals("none") ? "" : geoObj.getPlace().getJurisdiction(i++));
        } else {
            i++;
        }
        if (i == cityIndex || cityIndex == -1) {
            i++;
        }
        if (i == (max - 1) || (i == max)) {
            return;
        }
        jTextField8.setText(NbPreferences.forModule(App.class).get("fmt_address3", "").equals("none") ? "" : geoObj.getPlace().getJurisdiction(i++));
        if (i == cityIndex) {
            i++;
        }
        if (i == (max - 1) || (i == max)) {
            return;
        }
        jTextField9.setText(NbPreferences.forModule(App.class).get("fmt_address4", "").equals("none") ? "" : geoObj.getPlace().getJurisdiction(i++));
        if (i == cityIndex) {
            i++;
        }
        if (i == (max - 1) || (i == max)) {
            return;
        }
        jTextField2.setText(NbPreferences.forModule(App.class).get("fmt_address5", "").equals("none") ? "" : geoObj.getPlace().getJurisdiction(i));
    }

    private void enableAddressFields() {
        jLabel25.setEnabled(!NbPreferences.forModule(App.class).get("fmt_address1", "").equals("none"));
        jLabel26.setEnabled(!NbPreferences.forModule(App.class).get("fmt_address2", "").equals("none"));
        jLabel27.setEnabled(!NbPreferences.forModule(App.class).get("fmt_address3", "").equals("none"));
        jLabel28.setEnabled(!NbPreferences.forModule(App.class).get("fmt_address4", "").equals("none"));
        jLabel29.setEnabled(!NbPreferences.forModule(App.class).get("fmt_address5", "").equals("none"));
        jLabel30.setEnabled(!NbPreferences.forModule(App.class).get("fmt_address6", "").equals("none"));
        jLabel31.setEnabled(!NbPreferences.forModule(App.class).get("fmt_address7", "").equals("none"));

        jTextField6.setEnabled(!NbPreferences.forModule(App.class).get("fmt_address1", "").equals("none"));
        jTextField7.setEnabled(!NbPreferences.forModule(App.class).get("fmt_address2", "").equals("none"));
        jTextField8.setEnabled(!NbPreferences.forModule(App.class).get("fmt_address3", "").equals("none"));
        jTextField9.setEnabled(!NbPreferences.forModule(App.class).get("fmt_address4", "").equals("none"));
        jTextField2.setEnabled(!NbPreferences.forModule(App.class).get("fmt_address5", "").equals("none"));
        jTextField3.setEnabled(!NbPreferences.forModule(App.class).get("fmt_address6", "").equals("none"));
        jTextField4.setEnabled(!NbPreferences.forModule(App.class).get("fmt_address7", "").equals("none"));

        NbPreferences.forModule(App.class).addPreferenceChangeListener(this);
    }

    public void preferenceChange(PreferenceChangeEvent pce) {
        enableAddressFields();
    }

    public void updateGedcom(final PropertyPlace pp) {
        if (pp == null) {
            return;
        }
        String str = "";
        boolean endsComma = false;
        if (!NbPreferences.forModule(App.class).get("fmt_address1", "").equals("none")) {
            str += jTextField6.getText() + ",";
            endsComma = true;
        }
        if (!NbPreferences.forModule(App.class).get("fmt_address2", "").equals("none")) {
            str += jTextField7.getText() + ",";
            endsComma = true;
        }
        if (!NbPreferences.forModule(App.class).get("fmt_address3", "").equals("none")) {
            str += jTextField8.getText() + ",";
            endsComma = true;
        }
        if (!NbPreferences.forModule(App.class).get("fmt_address4", "").equals("none")) {
            str += jTextField9.getText() + ",";
            endsComma = true;
        }
        if (!NbPreferences.forModule(App.class).get("fmt_address5", "").equals("none")) {
            str += jTextField2.getText() + ",";
            endsComma = true;
        }
        if (!NbPreferences.forModule(App.class).get("fmt_address6", "").equals("none")) {
            str += jTextField3.getText() + ",";
            endsComma = true;
        }
        if (!NbPreferences.forModule(App.class).get("fmt_address7", "").equals("none")) {
            str += jTextField4.getText();
            endsComma = false;
        }
        final String place = (endsComma ? str.substring(0, str.lastIndexOf(",")) : str);

        // update gedcom
        try {
            Gedcom gedcom = pp.getGedcom();
            if (gedcom == null) {
                return;
            }
            gedcom.doUnitOfWork(new UnitOfWork() {

                public void perform(Gedcom gedcom) throws GedcomException {
                    pp.setValue(place);
                    // remember for next time
                    Toponym topo = geoObj.getToponym();
                    updateTopoFromDialog(topo);
                    geoObj.setToponym(topo);
                    NbPreferences.forModule(GeoPlacesList.class).put(pp.getValueStartingWithCity(), geoObj.Toponym2Code(topo));
                }
            });
        } catch (Exception e) {
            String msg = NbBundle.getMessage(GeoPlaceEditor.class, "ERROR_cannotUpdatePlace");
            JOptionPane.showMessageDialog(WindowManager.getDefault().getMainWindow(), msg, NbBundle.getMessage(GeoPlaceEditor.class, "ERROR_Title"), JOptionPane.ERROR_MESSAGE);
            System.out.println(msg + "\n" + e);
        }

    }

    private void updateTopoFromDialog(Toponym topo) {

        try {
            topo.setName(jTextField7.getText());
            topo.setLatitude(Double.parseDouble(jTextField5.getText()));
            topo.setLongitude(Double.parseDouble(jTextField10.getText()));
            topo.setCountryName(jTextField4.getText());
            topo.setAdminName2(jTextField2.getText());
            topo.setAdminName3(jTextField3.getText());
            topo.setAdminCode4(jTextField9.getText());
            topo.setPostcode(jTextField8.getText());
        } catch (Throwable t) {
        }
        return;
    }
}
