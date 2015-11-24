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
package ancestris.modules.treesharing.panels;

import ancestris.modules.treesharing.TreeSharingTopComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JSpinner;
import javax.swing.JToolTip;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.Timer;
import org.apache.commons.lang.time.DateUtils;
import org.openide.util.NbBundle;

/**
 *
 * @author frederic
 */
public class TimerPanel extends javax.swing.JPanel {

    public static int DEFAULT_DELAY = 12;  // hours 

    private final TreeSharingTopComponent owner;
    private boolean stateBeingChanged = false;
    
    // Popup things
    private JToolTip jToolTip = null;
    private ActionListener hider = null;
    private Popup popup = null;
    private Timer timer = null;
    
    /**
     * Creates new form TimerPanel
     */
    public TimerPanel(TreeSharingTopComponent tstc) {
        this.owner = tstc;
        initComponents();
        jSpinner1.setEditor(new JSpinner.DateEditor(jSpinner1, "d-MMM-yyyy HH:mm"));
        ((JSpinner.DefaultEditor)jSpinner1.getEditor()).getTextField().setHorizontalAlignment(javax.swing.JTextField.CENTER);
        initPopup();
        setTimerDate(DEFAULT_DELAY);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinner1 = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(186, 36));

        jSpinner1.setFont(new java.awt.Font("DejaVu Sans", 0, 11)); // NOI18N
        jSpinner1.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.HOUR));
        jSpinner1.setToolTipText(org.openide.util.NbBundle.getMessage(TimerPanel.class, "TimerPanel.jSpinner1.toolTipText", DEFAULT_DELAY));
        jSpinner1.setPreferredSize(new java.awt.Dimension(150, 26));
        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner1StateChanged(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/treesharing/resources/timer.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(TimerPanel.class, "TimerPanel.jButton1.text")); // NOI18N
        jButton1.setToolTipText(org.openide.util.NbBundle.getMessage(TimerPanel.class, "TimerPanel.jButton1.toolTipText")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinner1, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton1)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jSpinner1.setValue(new java.util.Date());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
        if (!stateBeingChanged) {
            stateBeingChanged = true;
            Date date = (Date) jSpinner1.getValue();
            if (date.before(new java.util.Date())) {
                jSpinner1.setValue(new java.util.Date());
            }
            owner.resetTimer();
            stateBeingChanged = false;
        }
    }//GEN-LAST:event_jSpinner1StateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JSpinner jSpinner1;
    // End of variables declaration//GEN-END:variables


    public void setTimerEnabled(boolean b) {
        jButton1.setEnabled(b);
        jSpinner1.setEnabled(b);
    }

    public Date getTimerDate() {
        return (Date) jSpinner1.getValue();
    }

    public void setTimerDate(int hours) {
        Date date = DateUtils.addHours(new java.util.Date(), hours);
        jSpinner1.setValue(date);
    }

    public final void initPopup() {
        jToolTip = new JToolTip();
        jToolTip.setTipText(NbBundle.getMessage(TimerPanel.class, "TIP_DelayTooShort"));
        hider = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popup.hide();
            }
        };
        // Hide popup in 3 seconds
        timer = new Timer(3000, hider);
    }

    public void setFocus() {
        int x = (int)this.getLocationOnScreen().getX();
        int y = (int)this.getLocationOnScreen().getY();
        popup = PopupFactory.getSharedInstance().getPopup(this, jToolTip, x+60, y+30);
        popup.show();
        timer.start();
    }

}