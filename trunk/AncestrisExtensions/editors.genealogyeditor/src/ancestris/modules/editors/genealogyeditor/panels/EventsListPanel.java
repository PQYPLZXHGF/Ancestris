package ancestris.modules.editors.genealogyeditor.panels;

import ancestris.modules.editors.genealogyeditor.models.EventsTableModel;
import ancestris.modules.gedcom.utilities.PropertyTag2Name;
import ancestris.util.swing.DialogManager;
import ancestris.util.swing.DialogManager.ADialog;
import genj.gedcom.*;
import java.awt.Component;
import java.util.Comparator;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.openide.DialogDescriptor;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;

/**
 *
 * @author dominique
 */
public class EventsListPanel extends javax.swing.JPanel {

    private class DateComparator implements Comparator<PropertyDate> {

        @Override
        public int compare(PropertyDate t, PropertyDate t1) {
            return t.compareTo(t1);
        }
    }

    public class EventsListTableCellRenderer extends DefaultTableCellRenderer {

        public EventsListTableCellRenderer() {
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (value instanceof PropertyDate) {
                setText(((PropertyDate) value).getDisplayValue());
            } else if (value instanceof PropertyPlace) {
                setText(((PropertyPlace)value).format("all"));
            }
            return this;
        }
    }
    public final static int INDIVIDUAL_EVENT_TYPE_LIST = 1;
    public final static int FAMILY_EVENT_TYPE_LIST = 2;
    private Property mRoot;
    private EventsTableModel mEventsTableModel = new EventsTableModel();
    private PropertyEvent mEvent = null;
    private int mEventTypeList = INDIVIDUAL_EVENT_TYPE_LIST;
    private String[] mIndividualEvents = {
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
        PropertyTag2Name.getTagName("EVEN"), /*
     * INDIVIDUAL_ATTRIBUTE
     */ /*
     * Unable to create / edit this events
     * PropertyTag2Name.getTagName("CAST"),
     * PropertyTag2Name.getTagName("DSCR"),
     * PropertyTag2Name.getTagName("EDUC"),
     * PropertyTag2Name.getTagName("IDNO"),
     * PropertyTag2Name.getTagName("NATI"),
     * PropertyTag2Name.getTagName("NCHI"),
     * PropertyTag2Name.getTagName("NMR"),
     * PropertyTag2Name.getTagName("OCCU"),
     * PropertyTag2Name.getTagName("PROP"),
     * PropertyTag2Name.getTagName("RELI"),
     * PropertyTag2Name.getTagName("RESI"),
     * PropertyTag2Name.getTagName("SSN"),
     * PropertyTag2Name.getTagName("TITL"),
     * PropertyTag2Name.getTagName("FACT")
     */};
    private String[] mFamilyEvents = {
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
    DefaultComboBoxModel<String> mEventsModel = new DefaultComboBoxModel<String>(new String[]{"empty"});
    private boolean updateOnGoing = false;

    /**
     * Creates new form EventsListPanel
     */
    public EventsListPanel() {
        this(INDIVIDUAL_EVENT_TYPE_LIST);
    }

    public EventsListPanel(int eventTypeList) {
        mEventTypeList = eventTypeList;
        initComponents();
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(eventsTable.getModel());
        sorter.setComparator(2, new DateComparator());
        eventsTable.setRowSorter(sorter);
        eventsTable.setDefaultRenderer(PropertyDate.class, new EventsListTableCellRenderer());
        eventsTable.setDefaultRenderer(PropertyPlace.class, new EventsListTableCellRenderer());
        eventsTable.setID(EventsListPanel.class.getName());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        eventsToolBar = new javax.swing.JToolBar();
        jLabel1 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(3, 0), new java.awt.Dimension(3, 0), new java.awt.Dimension(3, 32767));
        eventTypeComboBox = new javax.swing.JComboBox<String>();
        editEventButton = new javax.swing.JButton();
        deleteEventButton = new javax.swing.JButton();
        eventsScrollPane = new javax.swing.JScrollPane();
        eventsTable = new ancestris.modules.editors.genealogyeditor.table.EditorTable();

        eventsToolBar.setFloatable(false);
        eventsToolBar.setRollover(true);

        jLabel1.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("EventsListPanel.jLabel1.text"), new Object[] {})); // NOI18N
        eventsToolBar.add(jLabel1);
        eventsToolBar.add(filler1);

        eventTypeComboBox.setModel(mEventsModel);
        eventTypeComboBox.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("EventsListPanel.eventTypeComboBox.toolTipText"), new Object[] {})); // NOI18N
        eventTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eventTypeComboBoxActionPerformed(evt);
            }
        });
        eventsToolBar.add(eventTypeComboBox);

        editEventButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit.png"))); // NOI18N
        editEventButton.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("EventsListPanel.editEventButton.toolTipText"), new Object[] {})); // NOI18N
        editEventButton.setFocusable(false);
        editEventButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editEventButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editEventButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editEventButtonActionPerformed(evt);
            }
        });
        eventsToolBar.add(editEventButton);

        deleteEventButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/edit_delete.png"))); // NOI18N
        deleteEventButton.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("EventsListPanel.deleteEventButton.toolTipText"), new Object[] {})); // NOI18N
        deleteEventButton.setFocusable(false);
        deleteEventButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteEventButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        deleteEventButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteEventButtonActionPerformed(evt);
            }
        });
        eventsToolBar.add(deleteEventButton);

        eventsTable.setModel(mEventsTableModel);
        eventsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eventsTableMouseClicked(evt);
            }
        });
        eventsScrollPane.setViewportView(eventsTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(eventsToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(eventsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(eventsToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eventsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void editEventButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editEventButtonActionPerformed
        int selectedRow = eventsTable.getSelectedRow();
        Gedcom gedcom = mRoot.getGedcom();
        int undoNb = gedcom.getUndoNb();
        if (selectedRow != -1) {
            int rowIndex = eventsTable.convertRowIndexToModel(selectedRow);
            PropertyEvent event = mEventsTableModel.getValueAt(rowIndex);
            EventEditorPanel eventEditorPanel = new EventEditorPanel(mEventTypeList);
            eventEditorPanel.set(mRoot, event);

            ADialog eventEditorDialog = new ADialog(
                    NbBundle.getMessage(
                            EventEditorPanel.class, "EventEditorPanel.edit.title",
                            new Object[]{PropertyTag2Name.getTagName(event.getTag()), mRoot}),
                    eventEditorPanel);
            eventEditorDialog.setDialogId(EventEditorPanel.class.getName());

            if (eventEditorDialog.show() == DialogDescriptor.OK_OPTION) {
                eventEditorPanel.commit();
            } else {
                while (gedcom.getUndoNb() > undoNb && gedcom.canUndo()) {
                    gedcom.undoUnitOfWork(false);
                }
            }
        }
    }//GEN-LAST:event_editEventButtonActionPerformed

    private void deleteEventButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteEventButtonActionPerformed
        final int selectedRow = eventsTable.getSelectedRow();
        Gedcom gedcom = mRoot.getGedcom();

        if (selectedRow != -1) {
            int rowIndex = eventsTable.convertRowIndexToModel(selectedRow);
            PropertyEvent event = mEventsTableModel.getValueAt(rowIndex);

            DialogManager createYesNo = DialogManager.createYesNo(
                    NbBundle.getMessage(
                            EventEditorPanel.class, "EventsListPanel.deleteEventConfirmation.title",
                            PropertyTag2Name.getTagName(event.getTag())),
                    NbBundle.getMessage(
                            EventEditorPanel.class, "EventsListPanel.deleteEventConfirmation.text",
                            PropertyTag2Name.getTagName(event.getTag()),
                            mRoot));
            if (createYesNo.show() == DialogManager.YES_OPTION) {
                try {
                    gedcom.doUnitOfWork(new UnitOfWork() {

                        @Override
                        public void perform(Gedcom gedcom) throws GedcomException {
                            int rowIndex = eventsTable.convertRowIndexToModel(selectedRow);
                            mRoot.delProperty(mEventsTableModel.remove(rowIndex));
                        }
                    }); // end of doUnitOfWork
                    seteventTypeComboBox(mRoot.getProperties(PropertyEvent.class));

                } catch (GedcomException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
    }//GEN-LAST:event_deleteEventButtonActionPerformed

    private void eventTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eventTypeComboBoxActionPerformed
        if (!updateOnGoing) {
            Gedcom gedcom = mRoot.getGedcom();
            int undoNb = gedcom.getUndoNb();
            mEvent = null;
            final String eventType = eventTypeComboBox.getSelectedItem().toString();
            try {
                gedcom.doUnitOfWork(new UnitOfWork() {

                    @Override
                    public void perform(Gedcom gedcom) throws GedcomException {
                        Property addProperty = mRoot.addProperty(PropertyTag2Name.getPropertyTag(eventType), "");
                        if (addProperty instanceof PropertyEvent) {
                            mEvent = (PropertyEvent) addProperty;
                        } else {
                            System.out.println(addProperty.getClass());
                        }
                    }
                }); // end of doUnitOfWork

                if (mEvent != null) {
                    EventEditorPanel eventEditorPanel = new EventEditorPanel(mEventTypeList);

                    eventEditorPanel.set(mRoot, mEvent);

                    ADialog eventEditorDialog = new ADialog(
                            NbBundle.getMessage(EventEditorPanel.class,
                                    "EventEditorPanel.create.title",
                                    new Object[]{PropertyTag2Name.getTagName(mEvent.getTag()), mRoot}),
                            eventEditorPanel);

                    eventEditorDialog.setDialogId(EventEditorPanel.class.getName());
                    if (eventEditorDialog.show() == DialogDescriptor.OK_OPTION) {
                        mEventsTableModel.add(eventEditorPanel.commit());
                        seteventTypeComboBox(mRoot.getProperties(PropertyEvent.class));
                    } else {
                        while (gedcom.getUndoNb() > undoNb && gedcom.canUndo()) {
                            gedcom.undoUnitOfWork(false);
                        }
                    }
                }
            } catch (GedcomException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }//GEN-LAST:event_eventTypeComboBoxActionPerformed

    private void eventsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eventsTableMouseClicked
        if (evt.getClickCount() >= 2) {
            int selectedRow = eventsTable.getSelectedRow();
            Gedcom gedcom = mRoot.getGedcom();
            int undoNb = gedcom.getUndoNb();
            if (selectedRow != -1) {
                int rowIndex = eventsTable.convertRowIndexToModel(selectedRow);
                PropertyEvent event = mEventsTableModel.getValueAt(rowIndex);
                EventEditorPanel eventEditorPanel = new EventEditorPanel(mEventTypeList);
                eventEditorPanel.set(mRoot, event);

                ADialog eventEditorDialog = new ADialog(
                        NbBundle.getMessage(EventEditorPanel.class,
                                "EventEditorPanel.edit.title",
                                new Object[]{PropertyTag2Name.getTagName(event.getTag()), mRoot}),
                        eventEditorPanel);
                eventEditorDialog.setDialogId(EventEditorPanel.class.getName());

                if (eventEditorDialog.show() == DialogDescriptor.OK_OPTION) {
                    eventEditorPanel.commit();
                    mEventsTableModel.update();
                } else {
                    while (gedcom.getUndoNb() > undoNb && gedcom.canUndo()) {
                        gedcom.undoUnitOfWork(false);
                    }
                }
            }
        }
    }//GEN-LAST:event_eventsTableMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteEventButton;
    private javax.swing.JButton editEventButton;
    private javax.swing.JComboBox<String> eventTypeComboBox;
    private javax.swing.JScrollPane eventsScrollPane;
    private ancestris.modules.editors.genealogyeditor.table.EditorTable eventsTable;
    private javax.swing.JToolBar eventsToolBar;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

    public void setEventsList(Property root, List<PropertyEvent> eventsList) {
        this.mRoot = root;
        seteventTypeComboBox(eventsList);
        mEventsTableModel.addAll(eventsList);
    }

    public PropertyEvent getSelectedEvent() {
        int selectedRow = eventsTable.getSelectedRow();
        if (selectedRow != -1) {
            int rowIndex = eventsTable.convertRowIndexToModel(selectedRow);
            return mEventsTableModel.getValueAt(rowIndex);
        } else {
            return null;
        }
    }

    private void seteventTypeComboBox(List<PropertyEvent> eventsList) {
        updateOnGoing = true;
        if (mEventTypeList == INDIVIDUAL_EVENT_TYPE_LIST) {
            mEventsModel.removeAllElements();

            for (String element : mIndividualEvents) {
                mEventsModel.addElement(element);
            }
            for (PropertyEvent event : eventsList) {
                /*
                 * Filter by events already present
                 */
                if (!event.getTag().equals("EVEN") && !event.getTag().equals("GRAD")) {
                    mEventsModel.removeElement(PropertyTag2Name.getTagName(event.getTag()));
                }
            }
        } else if (mEventTypeList == FAMILY_EVENT_TYPE_LIST) {
            mEventsModel.removeAllElements();
            for (String element : mFamilyEvents) {
                mEventsModel.addElement(element);
            }
            for (PropertyEvent event : eventsList) {
                /*
                 * Filter by events already present
                 */
                if (!event.getTag().equals("EVEN")) {
                    mEventsModel.removeElement(PropertyTag2Name.getTagName(event.getTag()));
                }
            }
        } else {
            mEventsModel.removeAllElements();
            for (String element : mIndividualEvents) {
                mEventsModel.addElement(element);
            }
        }
        updateOnGoing = false;
    }
}
