package ancestris.modules.editors.placeeditor.panels;

import ancestris.api.editor.AncestrisEditor;
import ancestris.api.place.Place;
import ancestris.modules.editors.placeeditor.models.GeonamePlacesListModel;
import ancestris.modules.editors.placeeditor.models.ReferencesTableModel;
import ancestris.modules.place.geonames.GeonamesPlacesList;
import genj.gedcom.*;
import java.util.Set;
import java.util.logging.Logger;
import org.jdesktop.swingx.JXMapKit;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.netbeans.api.progress.ProgressUtils;
import org.openide.util.*;

/**
 *
 * @author dominique
 */
public class PlaceEditorPanel extends javax.swing.JPanel {

    private final static Logger logger = Logger.getLogger(PlaceEditorPanel.class.getName(), null);
    private GeonamePlacesListModel geonamePlacesListModel = new GeonamePlacesListModel();
    private ReferencesTableModel referencesTableModel = new ReferencesTableModel();
    private GeonamesPlacesList geonamesPlacesList = new GeonamesPlacesList();
    private Gedcom mGedcom = null;
    Set<PropertyPlace> mPropertyPlaces = null;

    /**
     * Creates new form GedcomPlacesEditorPanel
     */
    public PlaceEditorPanel() {
        initComponents();
        jXMapKit1.setDataProviderCreditShown(true);
        jXMapKit1.getMainMap().setRecenterOnClickEnabled(true);
        jXMapKit1.setDefaultProvider(JXMapKit.DefaultProviders.OpenStreetMaps);
        jXMapKit1.setMiniMapVisible(false);
        jXMapKit1.getZoomSlider().setValue(5);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gedcomPlaceEditorPanel = new ancestris.modules.editors.placeeditor.panels.GedcomPlaceEditorPanel();
        placeEditorTabbedPane = new javax.swing.JTabbedPane();
        mapPanel = new javax.swing.JPanel();
        MapScrollPane = new javax.swing.JScrollPane();
        jXMapKit1 = new org.jdesktop.swingx.JXMapKit();
        searchPlacePanel = new javax.swing.JPanel();
        searchPlaceTextField = new javax.swing.JTextField();
        searchPlaceButton = new javax.swing.JButton();
        geonamesScrollPane = new javax.swing.JScrollPane();
        geonamesPlacesListResult = new javax.swing.JList<String>();
        PlaceReferencesPanel = new javax.swing.JPanel();
        placeReferenceScrollPane = new javax.swing.JScrollPane();
        placeReferencesTable = new javax.swing.JTable();
        replacePlaceButton = new javax.swing.JButton();
        completePlaceButton = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(537, 414));

        placeEditorTabbedPane.setMinimumSize(new java.awt.Dimension(513, 263));

        MapScrollPane.setViewportView(jXMapKit1);

        javax.swing.GroupLayout mapPanelLayout = new javax.swing.GroupLayout(mapPanel);
        mapPanel.setLayout(mapPanelLayout);
        mapPanelLayout.setHorizontalGroup(
            mapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MapScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
        );
        mapPanelLayout.setVerticalGroup(
            mapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MapScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
        );

        placeEditorTabbedPane.addTab(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/placeeditor/panels/Bundle").getString("PlaceEditorPanel.mapPanel.TabConstraints.tabTitle"), new Object[] {}), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/placeeditor/resources/map.png")), mapPanel); // NOI18N

        searchPlaceTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchPlaceButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(searchPlaceButton, java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/placeeditor/panels/Bundle").getString("PlaceEditorPanel.searchPlaceButton.text"), new Object[] {})); // NOI18N
        searchPlaceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchPlaceButtonActionPerformed(evt);
            }
        });

        geonamesPlacesListResult.setModel(geonamePlacesListModel);
        geonamesPlacesListResult.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        geonamesScrollPane.setViewportView(geonamesPlacesListResult);

        javax.swing.GroupLayout searchPlacePanelLayout = new javax.swing.GroupLayout(searchPlacePanel);
        searchPlacePanel.setLayout(searchPlacePanelLayout);
        searchPlacePanelLayout.setHorizontalGroup(
            searchPlacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPlacePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchPlaceTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchPlaceButton)
                .addContainerGap())
            .addComponent(geonamesScrollPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        searchPlacePanelLayout.setVerticalGroup(
            searchPlacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPlacePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchPlacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchPlaceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchPlaceButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(geonamesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
        );

        placeEditorTabbedPane.addTab(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/placeeditor/panels/Bundle").getString("PlaceEditorPanel.searchPlacePanel.TabConstraints.tabTitle"), new Object[] {}), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/placeeditor/resources/Place.png")), searchPlacePanel); // NOI18N

        placeReferenceScrollPane.setMinimumSize(new java.awt.Dimension(503, 218));
        placeReferenceScrollPane.setPreferredSize(new java.awt.Dimension(503, 218));

        placeReferencesTable.setModel(referencesTableModel);
        placeReferencesTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        placeReferencesTable.setShowVerticalLines(false);
        placeReferencesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                placeReferencesTableMouseClicked(evt);
            }
        });
        placeReferenceScrollPane.setViewportView(placeReferencesTable);

        javax.swing.GroupLayout PlaceReferencesPanelLayout = new javax.swing.GroupLayout(PlaceReferencesPanel);
        PlaceReferencesPanel.setLayout(PlaceReferencesPanelLayout);
        PlaceReferencesPanelLayout.setHorizontalGroup(
            PlaceReferencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(placeReferenceScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
        );
        PlaceReferencesPanelLayout.setVerticalGroup(
            PlaceReferencesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(placeReferenceScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
        );

        placeEditorTabbedPane.addTab(org.openide.util.NbBundle.getMessage(PlaceEditorPanel.class, "PlacesEditorPanel.PlaceReferencesPanel.TabConstraints.tabTitle"), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/placeeditor/resources/association.png")), PlaceReferencesPanel); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(replacePlaceButton, org.openide.util.NbBundle.getMessage(PlaceEditorPanel.class, "PlaceEditorPanel.replacePlaceButton.text")); // NOI18N
        replacePlaceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                replacePlaceButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(completePlaceButton, org.openide.util.NbBundle.getMessage(PlaceEditorPanel.class, "PlaceEditorPanel.completePlaceButton.text")); // NOI18N
        completePlaceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                completePlaceButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gedcomPlaceEditorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(placeEditorTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(replacePlaceButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(completePlaceButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gedcomPlaceEditorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(placeEditorTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(replacePlaceButton)
                    .addComponent(completePlaceButton))
                .addContainerGap())
        );

        placeEditorTabbedPane.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(PlaceEditorPanel.class, "PlaceEditorPanel.searchPlacePanel.TabConstraints.tabTitle")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents

    private void searchPlaceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchPlaceButtonActionPerformed
        String searchedPlace = searchPlaceTextField.getText();

        if (searchedPlace.isEmpty() == false) {
            searchPlace();
        }
    }//GEN-LAST:event_searchPlaceButtonActionPerformed

    private void placeReferencesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_placeReferencesTableMouseClicked
        if (evt.getClickCount() >= 2) {
            int rowIndex = placeReferencesTable.convertRowIndexToModel(placeReferencesTable.getSelectedRow());
            if (rowIndex != -1) {
                Entity entity = referencesTableModel.getValueAt(rowIndex);
                AncestrisEditor editor = AncestrisEditor.findEditor(entity);
                if (editor != null) {
                    editor.edit(entity);
                }
            }
        }
    }//GEN-LAST:event_placeReferencesTableMouseClicked

    private void replacePlaceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_replacePlaceButtonActionPerformed
        Place place = geonamePlacesListModel.getPlaceAt(geonamesPlacesListResult.getSelectedIndex());
        gedcomPlaceEditorPanel.setPlace(place, false);
        jXMapKit1.setAddressLocation(new GeoPosition(place.getLatitude(), place.getLongitude()));
    }//GEN-LAST:event_replacePlaceButtonActionPerformed

    private void completePlaceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_completePlaceButtonActionPerformed
        Place place = geonamePlacesListModel.getPlaceAt(geonamesPlacesListResult.getSelectedIndex());
        gedcomPlaceEditorPanel.setPlace(place, true);
        jXMapKit1.setAddressLocation(new GeoPosition(place.getLatitude(), place.getLongitude()));
    }//GEN-LAST:event_completePlaceButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane MapScrollPane;
    private javax.swing.JPanel PlaceReferencesPanel;
    private javax.swing.JButton completePlaceButton;
    private ancestris.modules.editors.placeeditor.panels.GedcomPlaceEditorPanel gedcomPlaceEditorPanel;
    private javax.swing.JList<String> geonamesPlacesListResult;
    private javax.swing.JScrollPane geonamesScrollPane;
    private org.jdesktop.swingx.JXMapKit jXMapKit1;
    private javax.swing.JPanel mapPanel;
    private javax.swing.JTabbedPane placeEditorTabbedPane;
    private javax.swing.JScrollPane placeReferenceScrollPane;
    private javax.swing.JTable placeReferencesTable;
    private javax.swing.JButton replacePlaceButton;
    private javax.swing.JButton searchPlaceButton;
    private javax.swing.JPanel searchPlacePanel;
    private javax.swing.JTextField searchPlaceTextField;
    // End of variables declaration//GEN-END:variables

    /**
     * @param place the place to set
     */
    public void set(Gedcom gedcom, Set<PropertyPlace> propertyPlaces) {

        Object[] propertyPlaceArray = propertyPlaces.toArray();
        this.mGedcom = gedcom;
        this.mPropertyPlaces = propertyPlaces;

        gedcomPlaceEditorPanel.set(((PropertyPlace) propertyPlaceArray[0]).getParent(), (PropertyPlace) propertyPlaceArray[0]);

        Property latitude = null;
        Property longitude = null;

        if (((PropertyPlace) propertyPlaceArray[0]).getGedcom().getGrammar().getVersion().equals("5.5.1")) {
            Property map = ((PropertyPlace) propertyPlaceArray[0]).getProperty("MAP");
            if (map != null) {
                latitude = map.getProperty("LATI");
                longitude = map.getProperty("LONG");
            }
        } else {
            Property map = ((PropertyPlace) propertyPlaceArray[0]).getProperty("_MAP");
            if (map != null) {
                latitude = map.getProperty("_LATI");
                longitude = map.getProperty("_LONG");
            }
        }

        if (latitude != null && !latitude.getValue().isEmpty() && longitude != null && !longitude.getValue().isEmpty()) {
            jXMapKit1.setAddressLocation(new GeoPosition(Double.parseDouble(latitude.getValue()), Double.parseDouble(longitude.getValue())));
        } else {
            placeEditorTabbedPane.setSelectedComponent(searchPlacePanel);
            searchPlaceTextField.setText(gedcomPlaceEditorPanel.getPlaceString(GedcomPlaceEditorPanel.CITY).replaceAll(",", " ").replaceAll("\\s+", " "));
//            searchPlace();
        }

        for (PropertyPlace propertyPlace : propertyPlaces) {
            referencesTableModel.addRow(propertyPlace);
        }
    }

    private void searchPlace() {
        searchPlaceButton.setEnabled(false);
        geonamePlacesListModel.clear();
        geonamesPlacesList.searchPlace(searchPlaceTextField.getText(), geonamePlacesListModel);
        geonamesPlacesList.getTask().addTaskListener(new TaskListener() {

            @Override
            public void taskFinished(Task task) {
                logger.info("Search terminated");
                searchPlaceButton.setEnabled(true);
            }
        });
    }

    public void commit() {
        try {
            final String placeString = gedcomPlaceEditorPanel.getPlaceString(GedcomPlaceEditorPanel.ALL);
            final String mapTAG;
            final String latitudeTAG;
            final String longitudeTAG;

            if (mGedcom.getGrammar().getVersion().equals("5.5.1")) {
                mapTAG = "MAP";
                latitudeTAG = "LATI";
                longitudeTAG = "LONG";
            } else {
                mapTAG = "_MAP";
                latitudeTAG = "_LATI";
                longitudeTAG = "_LONG";
            }
            if (gedcomPlaceEditorPanel.isModified()) {
                mGedcom.doUnitOfWork(new UnitOfWork() {

                    @Override
                    public void perform(Gedcom gedcom) throws GedcomException {
                        for (PropertyPlace propertyPlace : mPropertyPlaces) {
                            propertyPlace.setValue(placeString);
                            Property map = propertyPlace.getProperty(mapTAG);
                            if (!gedcomPlaceEditorPanel.getLatitude().isEmpty() && !gedcomPlaceEditorPanel.getLongitude().isEmpty()) {
                                if (map == null) {
                                    map = propertyPlace.addProperty(mapTAG, "");
                                    map.addProperty(latitudeTAG, gedcomPlaceEditorPanel.getLatitude());
                                    map.addProperty(longitudeTAG, gedcomPlaceEditorPanel.getLongitude());
                                } else {
                                    Property latitude = map.getProperty("LATI");
                                    if (latitude == null) {
                                        map.addProperty(latitudeTAG, gedcomPlaceEditorPanel.getLatitude());
                                    } else {
                                        latitude.setValue(gedcomPlaceEditorPanel.getLatitude());
                                    }
                                    Property longitude = map.getProperty("LONG");
                                    if (longitude == null) {
                                        map.addProperty(longitudeTAG, gedcomPlaceEditorPanel.getLongitude());
                                    } else {
                                        longitude.setValue(gedcomPlaceEditorPanel.getLongitude());
                                    }
                                }
                            }
                        }
                    }
                }); // end of doUnitOfWork
            }
        } catch (GedcomException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
}
