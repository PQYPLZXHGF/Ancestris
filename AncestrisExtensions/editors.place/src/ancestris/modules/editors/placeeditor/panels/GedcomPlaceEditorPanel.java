package ancestris.modules.editors.placeeditor.panels;

import ancestris.api.place.Place;
import ancestris.util.swing.DialogManager;
import genj.gedcom.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javax.swing.JComponent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.openide.DialogDescriptor;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;

/**
 *
 * @author dominique
 */
public class GedcomPlaceEditorPanel extends javax.swing.JPanel {

    public final static int ALL = 0;
    public final static int HAMLET = 0;
    public final static int PARISH = 1;
    public final static int CITY = 2;
    public final static int ZIP_CODE = 3;
    public final static int GEO_ID = 4;
    public final static int COUNTY = 5;
    public final static int STATE = 6;
    public final static int COUNTRY = 7;

    private final static Logger logger = Logger.getLogger(GedcomPlaceEditorPanel.class.getName(), null);
    private Property mRoot;
    private PropertyPlace mPlace;
    private String mPlaceFormat[];
    private int mPlaceOrder[] = {
        0, // hamlet
        -1, // parish
        1, // city,
        2, // zip Code
        3, // geo ID,
        4, // county,
        5, // state
        6 // country
    };
    JComponent mGedcomFields[][];
    boolean mPlaceModified = false;
    boolean updateOnGoing = false;

    /**
     * Creates new form GedcomPlaceEditorPanel
     */
    public GedcomPlaceEditorPanel() {
        initComponents();
        mGedcomFields = new JComponent[][]{
            {gedcomHamletLabel, gedcomHamletTextField}, // hamlet
            {gedcomParishLabel, gedcomParishTextField}, // parish
            {gedcomCityLabel, gedcomCityTextField}, // city,
            {gedcomZipCodeLabel, gedcomZipCodeTextField},// zip Code
            {gedcomGeoIdLabel, gedcomGeoIDTextField}, // geo ID,
            {gedcomCountyLabel, gedcomCountyTextField}, // county,
            {gedcomStateLabel, gedcomStateTextField}, // state
            {gedcomCountryLabel, gedcomCountryTextField} // country
        };
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gedcomLatitudeLabel = new javax.swing.JLabel();
        gedcomParishLabel = new javax.swing.JLabel();
        gedcomLongitudeTextField = new javax.swing.JTextField();
        gedcomCountyTextField = new javax.swing.JTextField();
        gedcomCityLabel = new javax.swing.JLabel();
        gedcomHamletLabel = new javax.swing.JLabel();
        gedcomLongitudeLabel = new javax.swing.JLabel();
        gedcomStateLabel = new javax.swing.JLabel();
        gedcomHamletTextField = new javax.swing.JTextField();
        gedcomGeoIDTextField = new javax.swing.JTextField();
        gedcomCountyLabel = new javax.swing.JLabel();
        gedcomGeoIdLabel = new javax.swing.JLabel();
        gedcomZipCodeTextField = new javax.swing.JTextField();
        gedcomLatitudeTextField = new javax.swing.JTextField();
        gedcomCityTextField = new javax.swing.JTextField();
        gedcomZipCodeLabel = new javax.swing.JLabel();
        gedcomCountryLabel = new javax.swing.JLabel();
        gedcomStateTextField = new javax.swing.JTextField();
        gedcomCountryTextField = new javax.swing.JTextField();
        gedcomParishTextField = new javax.swing.JTextField();
        jToolBar1 = new javax.swing.JToolBar();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        parametersButton = new javax.swing.JButton();

        gedcomLatitudeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gedcomLatitudeLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/placeeditor/panels/Bundle").getString("placeeditor.LatitudeLabel.text"), new Object[] {})); // NOI18N

        gedcomParishLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gedcomParishLabel.setText(org.openide.util.NbBundle.getMessage(GedcomPlaceEditorPanel.class, "placeeditor.parishLabel.text")); // NOI18N

        gedcomLongitudeTextField.setColumns(16);
        gedcomLongitudeTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                }
            }
        });

        gedcomCountyTextField.setColumns(16);
        gedcomCountyTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                    PropertyPlace[] sameChoices = PropertyPlace.getSameChoices(mRoot.getGedcom(), mPlaceOrder[5], gedcomCountyTextField.getText());
                    if (sameChoices.length > 0) {
                        updatePlace(sameChoices[0], 6);
                    } else {
                        updatePlace(null, 6);
                    }
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                    PropertyPlace[] sameChoices = PropertyPlace.getSameChoices(mRoot.getGedcom(), mPlaceOrder[5], gedcomCountyTextField.getText());
                    if (sameChoices.length > 0) {
                        updatePlace(sameChoices[0], 6);
                    } else {
                        updatePlace(null, 6);
                    }
                }
            }
        });

        gedcomCityLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gedcomCityLabel.setText(org.openide.util.NbBundle.getMessage(GedcomPlaceEditorPanel.class, "placeeditor.cityLabel.text")); // NOI18N

        gedcomHamletLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gedcomHamletLabel.setText(org.openide.util.NbBundle.getMessage(GedcomPlaceEditorPanel.class, "placeeditor.hamletLabel.text")); // NOI18N

        gedcomLongitudeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gedcomLongitudeLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/placeeditor/panels/Bundle").getString("placeeditor.LongitudeLabel.text"), new Object[] {})); // NOI18N

        gedcomStateLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gedcomStateLabel.setText(org.openide.util.NbBundle.getMessage(GedcomPlaceEditorPanel.class, "placeeditor.stateLabel.text")); // NOI18N

        gedcomHamletTextField.setColumns(16);
        gedcomHamletTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                    PropertyPlace[] sameChoices = PropertyPlace.getSameChoices(mRoot.getGedcom(), mPlaceOrder[0], gedcomHamletTextField.getText());
                    if (sameChoices.length > 0) {
                        updatePlace(sameChoices[0], 1);
                    } else {
                        updatePlace(null, 1);
                    }
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                    PropertyPlace[] sameChoices = PropertyPlace.getSameChoices(mRoot.getGedcom(), mPlaceOrder[0], gedcomHamletTextField.getText());
                    if (sameChoices.length > 0) {
                        updatePlace(sameChoices[0], 1);
                    } else {
                        updatePlace(null, 1);
                    }
                }
            }
        });

        gedcomGeoIDTextField.setColumns(16);
        gedcomGeoIDTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                }
            }
        });

        gedcomCountyLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gedcomCountyLabel.setText(org.openide.util.NbBundle.getMessage(GedcomPlaceEditorPanel.class, "placeeditor.countyLabel.text")); // NOI18N

        gedcomGeoIdLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gedcomGeoIdLabel.setText(org.openide.util.NbBundle.getMessage(GedcomPlaceEditorPanel.class, "placeeditor.geoIDLabel.text")); // NOI18N

        gedcomZipCodeTextField.setColumns(16);
        gedcomZipCodeTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                }
            }
        });

        gedcomLatitudeTextField.setColumns(16);
        gedcomLatitudeTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                }
            }
        });

        gedcomCityTextField.setColumns(16);
        gedcomCityTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                    PropertyPlace[] sameChoices = PropertyPlace.getSameChoices(mRoot.getGedcom(), mPlaceOrder[2], gedcomCityTextField.getText());
                    if (sameChoices.length > 0) {
                        updatePlace(sameChoices[0], 3);
                    } else {
                        updatePlace(null, 3);
                    }
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                    PropertyPlace[] sameChoices = PropertyPlace.getSameChoices(mRoot.getGedcom(), mPlaceOrder[2], gedcomCityTextField.getText());
                    if (sameChoices.length > 0) {
                        updatePlace(sameChoices[0], 3);
                    } else {
                        updatePlace(null, 3);
                    }
                }
            }
        });

        gedcomZipCodeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gedcomZipCodeLabel.setText(org.openide.util.NbBundle.getMessage(GedcomPlaceEditorPanel.class, "placeeditor.zipCodeLabel.text")); // NOI18N

        gedcomCountryLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gedcomCountryLabel.setText(org.openide.util.NbBundle.getMessage(GedcomPlaceEditorPanel.class, "placeeditor.countryLabel.text")); // NOI18N

        gedcomStateTextField.setColumns(16);
        gedcomStateTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                    PropertyPlace[] sameChoices = PropertyPlace.getSameChoices(mRoot.getGedcom(), mPlaceOrder[6], gedcomStateTextField.getText());
                    if (sameChoices.length > 0) {
                        updatePlace(sameChoices[0], 7);
                    } else {
                        updatePlace(null, 7);
                    }
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                    PropertyPlace[] sameChoices = PropertyPlace.getSameChoices(mRoot.getGedcom(), mPlaceOrder[6], gedcomStateTextField.getText());
                    if (sameChoices.length > 0) {
                        updatePlace(sameChoices[0], 7);
                    } else {
                        updatePlace(null, 7);
                    }
                }
            }
        });

        gedcomCountryTextField.setColumns(16);
        gedcomCountryTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                }
            }
        });

        gedcomParishTextField.setColumns(16);
        gedcomParishTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!updateOnGoing) {
                    mPlaceModified = true;
                }
            }
        });

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.add(filler1);

        parametersButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/placeeditor/resources/parameters.png"))); // NOI18N
        parametersButton.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/placeeditor/panels/Bundle").getString("GedcomPlaceEditorPanel.parametersButton.text"), new Object[] {})); // NOI18N
        parametersButton.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/placeeditor/panels/Bundle").getString("GedcomPlaceEditorPanel.parametersButton.toolTipText"), new Object[] {})); // NOI18N
        parametersButton.setFocusable(false);
        parametersButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        parametersButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        parametersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parametersButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(parametersButton);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gedcomHamletLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                    .addComponent(gedcomCityLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                    .addComponent(gedcomCountyLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                    .addComponent(gedcomStateLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                    .addComponent(gedcomLatitudeLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gedcomLatitudeTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                            .addComponent(gedcomStateTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                            .addComponent(gedcomCityTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                            .addComponent(gedcomCountyTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(gedcomHamletTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gedcomCountryLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gedcomGeoIdLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gedcomZipCodeLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gedcomParishLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gedcomLongitudeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(gedcomCountryTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(gedcomGeoIDTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(gedcomZipCodeTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(gedcomParishTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(gedcomLongitudeTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(gedcomParishLabel)
                        .addComponent(gedcomParishTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(gedcomHamletLabel)
                        .addComponent(gedcomHamletTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gedcomZipCodeLabel)
                    .addComponent(gedcomZipCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gedcomCityLabel)
                    .addComponent(gedcomCityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gedcomGeoIdLabel)
                    .addComponent(gedcomGeoIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gedcomCountyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gedcomCountyLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gedcomCountryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gedcomCountryLabel)
                    .addComponent(gedcomStateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gedcomStateLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gedcomLongitudeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gedcomLongitudeLabel)
                    .addComponent(gedcomLatitudeLabel)
                    .addComponent(gedcomLatitudeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void parametersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_parametersButtonActionPerformed
        PlaceFormatOptionsPanel gedcomPlaceFormatEditorPanel = new PlaceFormatOptionsPanel(mPlaceFormat, mPlaceOrder);

        DialogManager.ADialog gedcomPlaceFormatEditorDialog = new DialogManager.ADialog(
                NbBundle.getMessage(PlaceFormatOptionsPanel.class, "PlaceFormatOptionsPanel.title"),
                gedcomPlaceFormatEditorPanel);
        gedcomPlaceFormatEditorDialog.setDialogId(PlaceFormatOptionsPanel.class.getName());

        if (gedcomPlaceFormatEditorDialog.show() == DialogDescriptor.OK_OPTION) {
            Preferences modulePreferences = NbPreferences.forModule(PlaceEditorPanel.class);
            Preferences node;
            mPlaceOrder = gedcomPlaceFormatEditorPanel.getPlaceOrder();
            node = modulePreferences.node(mPlace.getGedcom().getName());
            node.putInt("placeOrder.index.hamlet", mPlaceOrder[0]);
            node.putInt("placeOrder.index.parish", mPlaceOrder[1]);
            node.putInt("placeOrder.index.city", mPlaceOrder[2]);
            node.putInt("placeOrder.index.zipCode", mPlaceOrder[3]);
            node.putInt("placeOrder.index.geoID", mPlaceOrder[4]);
            node.putInt("placeOrder.index.county", mPlaceOrder[5]);
            node.putInt("placeOrder.index.state", mPlaceOrder[6]);
            node.putInt("placeOrder.index.Country", mPlaceOrder[7]);

            for (int index = 0; index < mPlaceOrder.length; index++) {
                if (mPlaceOrder[index] != -1) {
                    ((javax.swing.JLabel) (mGedcomFields[index][0])).setText(mPlaceFormat[mPlaceOrder[index]]);
                    ((javax.swing.JTextField) (mGedcomFields[index][1])).setText(mPlace.getJurisdiction(mPlaceOrder[index]));
                    List<String> jurisdictions = Arrays.asList(mPlace.getAllJurisdictions(mPlaceOrder[index], true));
                    if (jurisdictions != null) {
                        AutoCompleteDecorator.decorate((javax.swing.JTextField) mGedcomFields[index][1], jurisdictions, false);
                    }
                } else {
                    ((javax.swing.JLabel) (mGedcomFields[index][0])).setText("");
                    ((javax.swing.JTextField) (mGedcomFields[index][1])).setText("");
                    mGedcomFields[index][1].setVisible(false);
                }
            }
        }
    }//GEN-LAST:event_parametersButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel gedcomCityLabel;
    private javax.swing.JTextField gedcomCityTextField;
    private javax.swing.JLabel gedcomCountryLabel;
    private javax.swing.JTextField gedcomCountryTextField;
    private javax.swing.JLabel gedcomCountyLabel;
    private javax.swing.JTextField gedcomCountyTextField;
    private javax.swing.JTextField gedcomGeoIDTextField;
    private javax.swing.JLabel gedcomGeoIdLabel;
    private javax.swing.JLabel gedcomHamletLabel;
    private javax.swing.JTextField gedcomHamletTextField;
    private javax.swing.JLabel gedcomLatitudeLabel;
    private javax.swing.JTextField gedcomLatitudeTextField;
    private javax.swing.JLabel gedcomLongitudeLabel;
    private javax.swing.JTextField gedcomLongitudeTextField;
    private javax.swing.JLabel gedcomParishLabel;
    private javax.swing.JTextField gedcomParishTextField;
    private javax.swing.JLabel gedcomStateLabel;
    private javax.swing.JTextField gedcomStateTextField;
    private javax.swing.JLabel gedcomZipCodeLabel;
    private javax.swing.JTextField gedcomZipCodeTextField;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton parametersButton;
    // End of variables declaration//GEN-END:variables

    /**
     * @param place the place to set
     */
    public void set(Property root, PropertyPlace place) {
        Preferences modulePreferences = NbPreferences.forModule(PlaceEditorPanel.class);
        Preferences node;

        this.mPlace = place;
        this.mRoot = root;
        mPlaceFormat = PropertyPlace.getFormat(mRoot.getGedcom());

        try {
            if (!modulePreferences.nodeExists(mRoot.getGedcom().getName())) {

                PlaceFormatOptionsPanel gedcomPlaceFormatEditorPanel = new PlaceFormatOptionsPanel(mPlaceFormat, mPlaceOrder);

                DialogManager.ADialog gedcomPlaceFormatEditorDialog = new DialogManager.ADialog(
                        NbBundle.getMessage(PlaceFormatOptionsPanel.class, "PlaceFormatOptionsPanel.title"),
                        gedcomPlaceFormatEditorPanel);
                gedcomPlaceFormatEditorDialog.setDialogId(PlaceFormatOptionsPanel.class.getName());
                node = modulePreferences.node(mRoot.getGedcom().getName());
                if (gedcomPlaceFormatEditorDialog.show() == DialogDescriptor.OK_OPTION) {
                    mPlaceOrder = gedcomPlaceFormatEditorPanel.getPlaceOrder();
                    node.putInt("placeOrder.index.hamlet", mPlaceOrder[0]);
                    node.putInt("placeOrder.index.parish", mPlaceOrder[1]);
                    node.putInt("placeOrder.index.city", mPlaceOrder[2]);
                    node.putInt("placeOrder.index.zipCode", mPlaceOrder[3]);
                    node.putInt("placeOrder.index.geoID", mPlaceOrder[4]);
                    node.putInt("placeOrder.index.county", mPlaceOrder[5]);
                    node.putInt("placeOrder.index.state", mPlaceOrder[6]);
                    node.putInt("placeOrder.index.Country", mPlaceOrder[7]);
                }
            } else {
                node = modulePreferences.node(mRoot.getGedcom().getName());
                mPlaceOrder[0] = node.getInt("placeOrder.index.hamlet", mPlaceOrder[0]);
                mPlaceOrder[1] = node.getInt("placeOrder.index.parish", mPlaceOrder[1]);
                mPlaceOrder[2] = node.getInt("placeOrder.index.city", mPlaceOrder[2]);
                mPlaceOrder[3] = node.getInt("placeOrder.index.zipCode", mPlaceOrder[3]);
                mPlaceOrder[4] = node.getInt("placeOrder.index.geoID", mPlaceOrder[4]);
                mPlaceOrder[5] = node.getInt("placeOrder.index.county", mPlaceOrder[5]);
                mPlaceOrder[6] = node.getInt("placeOrder.index.state", mPlaceOrder[6]);
                mPlaceOrder[7] = node.getInt("placeOrder.index.Country", mPlaceOrder[7]);
            }
        } catch (BackingStoreException ex) {
            Exceptions.printStackTrace(ex);
        }

        for (int index = 0; index < mPlaceOrder.length; index++) {
            if (mPlaceOrder[index] != -1) {
                if (mPlaceOrder[index] < mPlaceFormat.length) {
                    ((javax.swing.JLabel) (mGedcomFields[index][0])).setText(mPlaceFormat[mPlaceOrder[index]]);
                    List<String> jurisdictions = Arrays.asList(PropertyPlace.getAllJurisdictions(mRoot.getGedcom(), mPlaceOrder[index], true));
                    if (jurisdictions != null) {
                        AutoCompleteDecorator.decorate((javax.swing.JTextField) mGedcomFields[index][1], jurisdictions, false);
                    } else {
                        AutoCompleteDecorator.decorate((javax.swing.JTextField) mGedcomFields[index][1], new <String>ArrayList(), false);
                    }
                }
            } else {
                ((javax.swing.JLabel) (mGedcomFields[index][0])).setText("");
                ((javax.swing.JTextField) (mGedcomFields[index][1])).setText("");
                mGedcomFields[index][1].setVisible(false);
            }
        }

        updatePlace(mPlace, 0);
    }

    public void setPlace(Place place, boolean completePlace) {
        String[] jurisdictions = place.getJurisdictions();

        updateOnGoing = true;

        if (!completePlace || gedcomCityTextField.getText().isEmpty()) {
            gedcomCityTextField.setText(jurisdictions[0] != null ? jurisdictions[0] : ""); // City
        }
        if (!completePlace || gedcomZipCodeTextField.getText().isEmpty()) {
            gedcomZipCodeTextField.setText(jurisdictions[1] != null ? jurisdictions[1] : ""); // Postal code    
        }
        if (!completePlace || gedcomGeoIDTextField.getText().isEmpty()) {
            gedcomGeoIDTextField.setText(jurisdictions[2] != null ? jurisdictions[2] : ""); // GeoID
        }
        if (!completePlace || gedcomCountyTextField.getText().isEmpty()) {
            gedcomCountyTextField.setText(jurisdictions[3] != null ? jurisdictions[3] : ""); // County
        }
        if (!completePlace || gedcomStateTextField.getText().isEmpty()) {
            gedcomStateTextField.setText(jurisdictions[4] != null ? jurisdictions[4] : ""); // State
        }
        if (!completePlace || gedcomCountryTextField.getText().isEmpty()) {
            gedcomCountryTextField.setText(jurisdictions[5] != null ? jurisdictions[5] : ""); // Country
        }
        if (!completePlace || gedcomLatitudeTextField.getText().isEmpty()) {
            gedcomLatitudeTextField.setText(place.getLatitude().toString());
        }
        if (!completePlace || gedcomLongitudeTextField.getText().isEmpty()) {
            gedcomLongitudeTextField.setText(place.getLongitude().toString());
        }
        updateOnGoing = false;
    }

     private void updatePlace(PropertyPlace place, int startIndex) {

        updateOnGoing = true;

        if (place != null) {
            logger.log(Level.INFO, "startIndex {0}", new Object[]{startIndex});

            for (int index = startIndex; index < mPlaceOrder.length; index++) {
                if (mPlaceOrder[index] != -1) {
                    if (mPlaceOrder[index] < mPlaceFormat.length) {
                        String jurisdiction = place.getJurisdiction(mPlaceOrder[index]);
                        ((javax.swing.JTextField) (mGedcomFields[index][1])).setText(jurisdiction != null ? jurisdiction : "");
                    }
                } else {
                    ((javax.swing.JLabel) (mGedcomFields[index][0])).setText("");
                }
            }

            Property latitude = null;
            Property longitude = null;

            if (place.getGedcom().getGrammar().getVersion().equals("5.5.1")) {
                Property map = place.getProperty("MAP");
                if (map != null) {
                    latitude = map.getProperty("LATI");
                    longitude = map.getProperty("LONG");
                }
            } else {
                Property map = place.getProperty("_MAP");
                if (map != null) {
                    latitude = map.getProperty("_LATI");
                    longitude = map.getProperty("_LONG");
                }
            }

            if (latitude != null && longitude != null) {
                gedcomLatitudeTextField.setText(latitude.getValue());
                gedcomLongitudeTextField.setText(longitude.getValue());
            } else {
                gedcomLatitudeTextField.setText("");
                gedcomLongitudeTextField.setText("");
            }
        }
        updateOnGoing = false;
    }

    public String getPlaceString(int startingFrom) {

        String placeString = "";

        javax.swing.JTextField gedcomFieldsOrder[] = new javax.swing.JTextField[mPlaceFormat.length];
        for (int placeOrderindex = startingFrom; placeOrderindex < mPlaceOrder.length; placeOrderindex++) {
            if (mPlaceOrder[placeOrderindex] != -1) {
                gedcomFieldsOrder[mPlaceOrder[placeOrderindex]] = (javax.swing.JTextField) mGedcomFields[placeOrderindex][1];
            }
        }

        for (int index = 0; index < mPlaceFormat.length; index++) {
            placeString += index > 0 ? (gedcomFieldsOrder[index] != null ? gedcomFieldsOrder[index].getText() + "," : ",") : (gedcomFieldsOrder[index] != null ? gedcomFieldsOrder[index].getText() : "");
        }

        return placeString;
    }

    public boolean isModified() {
        return mPlaceModified;
    }

    public String getLatitude() {
        return gedcomLatitudeTextField.getText();
    }

    public String getLongitude() {
        return gedcomLongitudeTextField.getText();
    }
}
