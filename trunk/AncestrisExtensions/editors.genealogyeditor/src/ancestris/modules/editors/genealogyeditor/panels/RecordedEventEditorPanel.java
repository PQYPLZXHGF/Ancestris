package ancestris.modules.editors.genealogyeditor.panels;

import ancestris.modules.gedcom.utilities.PropertyTag2Name;
import genj.gedcom.*;
import org.openide.util.Exceptions;

/**
 *
 * @author dominique
 */
public class RecordedEventEditorPanel extends javax.swing.JPanel {
    
    private Property mEvent = null;
    private PropertyPlace mPlace = null;
    private String[] mEvents = {
        /*
         * INDIVIDUAL_EVENT
         */
        PropertyTag2Name.getTagName("BIRT"),
        PropertyTag2Name.getTagName("CHR"),
        PropertyTag2Name.getTagName("DEAT"),
        PropertyTag2Name.getTagName("BURI"),
        PropertyTag2Name.getTagName("CREM"),
        PropertyTag2Name.getTagName("ADOP"),
        PropertyTag2Name.getTagName("BAPM"),
        PropertyTag2Name.getTagName("BARM"),
        PropertyTag2Name.getTagName("BASM"),
        PropertyTag2Name.getTagName("BLES"),
        PropertyTag2Name.getTagName("CHRA"),
        PropertyTag2Name.getTagName("CONF"),
        PropertyTag2Name.getTagName("FCOM"),
        PropertyTag2Name.getTagName("ORDN"),
        PropertyTag2Name.getTagName("NATU"),
        PropertyTag2Name.getTagName("EMIG"),
        PropertyTag2Name.getTagName("IMMI"),
        PropertyTag2Name.getTagName("CENS"),
        PropertyTag2Name.getTagName("PROB"),
        PropertyTag2Name.getTagName("WILL"),
        PropertyTag2Name.getTagName("GRAD"),
        PropertyTag2Name.getTagName("RETI"),
        PropertyTag2Name.getTagName("EVEN"),
        /*
         * INDIVIDUAL_ATTRIBUTE
         */
        PropertyTag2Name.getTagName("CAST"),
        PropertyTag2Name.getTagName("DSCR"),
        PropertyTag2Name.getTagName("EDUC"),
        PropertyTag2Name.getTagName("IDNO"),
        PropertyTag2Name.getTagName("NATI"),
        PropertyTag2Name.getTagName("NCHI"),
        PropertyTag2Name.getTagName("NMR"),
        PropertyTag2Name.getTagName("OCCU"),
        PropertyTag2Name.getTagName("PROP"),
        PropertyTag2Name.getTagName("RELI"),
        PropertyTag2Name.getTagName("RESI"),
        PropertyTag2Name.getTagName("SSN"),
        PropertyTag2Name.getTagName("TITL"),
        PropertyTag2Name.getTagName("FACT"),
        /*
         * FAMILY_EVENT
         */
        PropertyTag2Name.getTagName("ANUL"),
        PropertyTag2Name.getTagName("CENS"),
        PropertyTag2Name.getTagName("DIV"),
        PropertyTag2Name.getTagName("DIVF"),
        PropertyTag2Name.getTagName("MARR"),
        PropertyTag2Name.getTagName("ENGA"),
        PropertyTag2Name.getTagName("MARB"),
        PropertyTag2Name.getTagName("MARC"),
        PropertyTag2Name.getTagName("MARL"),
        PropertyTag2Name.getTagName("MARS"),
        PropertyTag2Name.getTagName("RESI"),
        PropertyTag2Name.getTagName("EVEN")
    };

    /**
     * Creates new form RecordedEventEditorPanel
     */
    public RecordedEventEditorPanel() {
        initComponents();
        aDateBean.setPreferHorizontal(true);
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gedcomPlacePanel = new ancestris.modules.editors.genealogyeditor.panels.GedcomPlacePanel();
        eventTypeComboBox = new javax.swing.JComboBox(mEvents);
        recordedEventLabel = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        aDateBean = new ancestris.modules.beans.ADateBean();

        recordedEventLabel.setText(org.openide.util.NbBundle.getMessage(RecordedEventEditorPanel.class, "RecordedEventEditorPanel.recordedEventLabel.text")); // NOI18N

        dateLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("RecordedEventEditorPanel.dateLabel.text"), new Object[] {})); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(gedcomPlacePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(recordedEventLabel)
                            .addComponent(dateLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(eventTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(aDateBean, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                                .addContainerGap())))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eventTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(recordedEventLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(aDateBean, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gedcomPlacePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ancestris.modules.beans.ADateBean aDateBean;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JComboBox eventTypeComboBox;
    private ancestris.modules.editors.genealogyeditor.panels.GedcomPlacePanel gedcomPlacePanel;
    private javax.swing.JLabel recordedEventLabel;
    // End of variables declaration//GEN-END:variables

    Property commit() {
        final String eventType = eventTypeComboBox.getSelectedItem().toString();
        mEvent.setValue(PropertyTag2Name.getPropertyTag(eventType));
        try {
            aDateBean.commit();
        } catch (GedcomException ex) {
            Exceptions.printStackTrace(ex);
        }
        
        gedcomPlacePanel.commit();
        
        return mEvent;
    }
    
    void set(Property event) {
        this.mEvent = event;
        
        PropertyDate date = (PropertyDate) mEvent.getProperty("DATE", false);
        if (date == null) {
            date = (PropertyDate) mEvent.addProperty("DATE", "");
        }
        aDateBean.setContext(date);
        mPlace = (PropertyPlace) mEvent.getProperty(PropertyPlace.TAG, false);
        if (mPlace == null) {
            try {
                mEvent.getGedcom().doUnitOfWork(new UnitOfWork() {
                    
                    @Override
                    public void perform(Gedcom gedcom) throws GedcomException {
                        mPlace = (PropertyPlace) mEvent.addProperty(PropertyPlace.TAG, "");
                    }
                }); // end of doUnitOfWork
            } catch (GedcomException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        
        gedcomPlacePanel.set(mEvent, mPlace);
    }
}
