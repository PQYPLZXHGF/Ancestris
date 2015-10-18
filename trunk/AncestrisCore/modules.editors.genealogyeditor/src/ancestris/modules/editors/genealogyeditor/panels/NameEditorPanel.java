package ancestris.modules.editors.genealogyeditor.panels;

import ancestris.modules.editors.genealogyeditor.models.NameTypeComboBoxModel;
import genj.gedcom.*;
import genj.util.ChangeSupport;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultEditorKit;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDocument;
import org.openide.util.Utilities;

/**
 *
 * @author dominique
 */
public class NameEditorPanel extends javax.swing.JPanel {

    private NameTypeComboBoxModel nameTypeComboBoxModelModel = new NameTypeComboBoxModel();
    private Indi mRoot;
    private PropertyName mName;
    private boolean nameTypeModified = false;
    private boolean familyNamePrefixModified = false;
    private boolean familyNameModified = false;
    private boolean firstNamePrefixModified = false;
    private boolean firstNameSuffixModified = false;
    private boolean firstNameModified = false;
    private boolean nicknameModified = false;
    private final static Logger logger = Logger.getLogger(NameEditorPanel.class.getName(), null);
    private final ChangeSupport changeSupport = new ChangeSupport((this));

    /**
     * Creates new form NameEditorPanel
     */
    public NameEditorPanel() {
        initComponents();
        jCheckBox1.setSelected(false);
        firstNamePrefixLabel.setVisible(false);
        firstNamePrefixTextField.setVisible(false);
        familyNamePrefixLabel.setVisible(false);
        familyNamePrefixTextField.setVisible(false);
        firstNameSuffixLabel.setVisible(false);
        firstNameSuffixTextField.setVisible(false);

        // add changelistener
        firstNamePrefixTextField.getDocument().addDocumentListener(changeSupport);
        familyNamePrefixTextField.getDocument().addDocumentListener(changeSupport);
        firstNameTextField.getDocument().addDocumentListener(changeSupport);
        familyNameTextField.getDocument().addDocumentListener(changeSupport);
        nicknameTextField.getDocument().addDocumentListener(changeSupport);
        firstNameSuffixTextField.getDocument().addDocumentListener(changeSupport);

        Context contextToOpen = Utilities.actionsGlobalContext().lookup(Context.class);
        if (contextToOpen != null) {
            Gedcom gedcom = contextToOpen.getGedcom();

            // FIXME : aucocompletion does not respect case sensitivity. Remove for now. Some simple ways to do autocompletion seem to exist as an alternative.
//            AutoCompleteDecorator.decorate(firstNameTextField, PropertyName.getFirstNames(gedcom, true), true);
//            InputMap map = firstNameTextField.getInputMap();
//            map.put(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_BACK_SPACE, 0), DefaultEditorKit.deletePrevCharAction);
//            AutoCompleteDecorator.decorate(familyNameTextField, PropertyName.getLastNames(gedcom, true), true);
//            map = familyNameTextField.getInputMap();
//            map.put(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_BACK_SPACE, 0), DefaultEditorKit.deletePrevCharAction);
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

        nameEditorTabbedPane = new javax.swing.JTabbedPane();
        namePanel = new javax.swing.JPanel();
        nameEditorPanel = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        nameTypeLabel = new javax.swing.JLabel();
        nameTypeComboBox = new javax.swing.JComboBox<String>();
        firstNamePrefixLabel = new javax.swing.JLabel();
        firstNamePrefixTextField = new javax.swing.JTextField();
        firstNameTextField = new javax.swing.JTextField();
        familyNamePrefixLabel = new javax.swing.JLabel();
        familyNamePrefixTextField = new javax.swing.JTextField();
        familyNameTextField = new javax.swing.JTextField();
        firstNameSuffixTextField = new javax.swing.JTextField();
        nicknameLabel = new javax.swing.JLabel();
        nicknameTextField = new javax.swing.JTextField();
        firstnameLabel = new javax.swing.JLabel();
        familyNameLabel = new javax.swing.JLabel();
        firstNameSuffixLabel = new javax.swing.JLabel();
        noteCitationsTablePanel = new ancestris.modules.editors.genealogyeditor.panels.NoteCitationsTablePanel();
        sourceCitationsTablePanel = new ancestris.modules.editors.genealogyeditor.panels.SourceCitationsTablePanel();

        jCheckBox1.setText(org.openide.util.NbBundle.getMessage(NameEditorPanel.class, "NameEditorPanel.jCheckBox1.text")); // NOI18N
        jCheckBox1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        nameTypeLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NameEditorPanel.nameTypeLabel.text"), new Object[] {})); // NOI18N

        nameTypeComboBox.setEditable(true);
        nameTypeComboBox.setModel(nameTypeComboBoxModelModel);
        nameTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTypeComboBoxActionPerformed(evt);
            }
        });

        firstNamePrefixLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        firstNamePrefixLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NameEditorPanel.firstNamePrefixLabel.text"), new Object[] {})); // NOI18N

        firstNamePrefixTextField.setColumns(8);
        firstNamePrefixTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        firstNamePrefixTextField.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NameEditorPanel.firstNamePrefixTextField.toolTipText"), new Object[] {})); // NOI18N
        firstNamePrefixTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                firstNamePrefixModified = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                firstNamePrefixModified = true;
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                firstNamePrefixModified = true;
            }
        });

        firstNameTextField.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NameEditorPanel.firstNameTextField.toolTipText"), new Object[] {})); // NOI18N
        firstNameTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                firstNameModified = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                firstNameModified = true;
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                firstNameModified = true;
            }
        });

        familyNamePrefixLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        familyNamePrefixLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NameEditorPanel.familyNamePrefixLabel.text"), new Object[] {})); // NOI18N

        familyNamePrefixTextField.setColumns(8);
        familyNamePrefixTextField.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NameEditorPanel.familyNamePrefixTextField.toolTipText"), new Object[] {})); // NOI18N
        familyNamePrefixTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                familyNamePrefixModified = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                familyNamePrefixModified = true;
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                familyNamePrefixModified = true;
            }
        });

        familyNameTextField.setColumns(16);
        familyNameTextField.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NameEditorPanel.familyNameTextField.toolTipText"), new Object[] {})); // NOI18N
        familyNameTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                familyNameModified = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                familyNameModified = true;
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                familyNameModified = true;
            }
        });

        firstNameSuffixTextField.setColumns(8);
        firstNameSuffixTextField.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NameEditorPanel.firstNameSuffixTextField.text"), new Object[] {})); // NOI18N
        firstNameSuffixTextField.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NameEditorPanel.firstNameSuffixTextField.toolTipText"), new Object[] {})); // NOI18N
        firstNameSuffixTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                firstNameSuffixModified = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                firstNameSuffixModified = true;
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                firstNameSuffixModified = true;
            }
        });

        nicknameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nicknameLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NameEditorPanel.nicknameLabel.text"), new Object[] {})); // NOI18N

        nicknameTextField.setColumns(8);
        nicknameTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        nicknameTextField.setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NameEditorPanel.nicknameTextField.toolTipText"), new Object[] {})); // NOI18N
        nicknameTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                nicknameModified = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                nicknameModified = true;
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                nicknameModified = true;
            }
        });

        firstnameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        firstnameLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NameEditorPanel.firstnameLabel.text"), new Object[] {})); // NOI18N

        familyNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        familyNameLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NameEditorPanel.familyNameLabel.text"), new Object[] {})); // NOI18N

        firstNameSuffixLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        firstNameSuffixLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/panels/Bundle").getString("NameEditorPanel.firstNameSuffixLabel.text"), new Object[] {})); // NOI18N

        javax.swing.GroupLayout nameEditorPanelLayout = new javax.swing.GroupLayout(nameEditorPanel);
        nameEditorPanel.setLayout(nameEditorPanelLayout);
        nameEditorPanelLayout.setHorizontalGroup(
            nameEditorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nameEditorPanelLayout.createSequentialGroup()
                .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameTypeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameTypeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(nameEditorPanelLayout.createSequentialGroup()
                .addGroup(nameEditorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(familyNamePrefixLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(firstNamePrefixLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(nameEditorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(firstNamePrefixTextField)
                    .addComponent(familyNamePrefixTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(nameEditorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nicknameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(familyNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(firstnameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(nameEditorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(firstNameTextField)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, nameEditorPanelLayout.createSequentialGroup()
                        .addGroup(nameEditorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(nicknameTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(familyNameTextField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(firstNameSuffixLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(firstNameSuffixTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        nameEditorPanelLayout.setVerticalGroup(
            nameEditorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nameEditorPanelLayout.createSequentialGroup()
                .addGroup(nameEditorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameTypeLabel)
                    .addComponent(jCheckBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(nameEditorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNamePrefixTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstNamePrefixLabel)
                    .addComponent(firstnameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(nameEditorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(familyNamePrefixLabel)
                    .addComponent(familyNamePrefixTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(familyNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstNameSuffixTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(familyNameLabel)
                    .addComponent(firstNameSuffixLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(nameEditorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nicknameLabel)
                    .addComponent(nicknameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout namePanelLayout = new javax.swing.GroupLayout(namePanel);
        namePanel.setLayout(namePanelLayout);
        namePanelLayout.setHorizontalGroup(
            namePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(namePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameEditorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        namePanelLayout.setVerticalGroup(
            namePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(namePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameEditorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        nameEditorTabbedPane.addTab(org.openide.util.NbBundle.getMessage(NameEditorPanel.class, "NameEditorPanel.namePanel.TabConstraints.tabTitle"), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/Name.png")), namePanel); // NOI18N
        nameEditorTabbedPane.addTab(org.openide.util.NbBundle.getMessage(NameEditorPanel.class, "NameEditorPanel.noteCitationsTablePanel.TabConstraints.tabTitle"), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/Note.png")), noteCitationsTablePanel); // NOI18N
        nameEditorTabbedPane.addTab(org.openide.util.NbBundle.getMessage(NameEditorPanel.class, "NameEditorPanel.sourceCitationsTablePanel.TabConstraints.tabTitle"), new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/editors/genealogyeditor/resources/Source.png")), sourceCitationsTablePanel); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nameEditorTabbedPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nameEditorTabbedPane)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (jCheckBox1.isSelected()) {
            firstNamePrefixLabel.setVisible(true);
            firstNamePrefixTextField.setVisible(true);
            familyNamePrefixLabel.setVisible(true);
            familyNamePrefixTextField.setVisible(true);
            firstNameSuffixLabel.setVisible(true);
            firstNameSuffixTextField.setVisible(true);
        } else {
            firstNamePrefixLabel.setVisible(false);
            firstNamePrefixTextField.setVisible(false);
            familyNamePrefixLabel.setVisible(false);
            familyNamePrefixTextField.setVisible(false);
            firstNameSuffixLabel.setVisible(false);
            firstNameSuffixTextField.setVisible(false);
        }
        revalidate();
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void nameTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTypeComboBoxActionPerformed
        nameTypeModified = true;
        changeSupport.fireChangeEvent();
//        nameModified = true;
    }//GEN-LAST:event_nameTypeComboBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel familyNameLabel;
    private javax.swing.JLabel familyNamePrefixLabel;
    private javax.swing.JTextField familyNamePrefixTextField;
    private javax.swing.JTextField familyNameTextField;
    private javax.swing.JLabel firstNamePrefixLabel;
    private javax.swing.JTextField firstNamePrefixTextField;
    private javax.swing.JLabel firstNameSuffixLabel;
    private javax.swing.JTextField firstNameSuffixTextField;
    private javax.swing.JTextField firstNameTextField;
    private javax.swing.JLabel firstnameLabel;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JPanel nameEditorPanel;
    private javax.swing.JTabbedPane nameEditorTabbedPane;
    private javax.swing.JPanel namePanel;
    private javax.swing.JComboBox<String> nameTypeComboBox;
    private javax.swing.JLabel nameTypeLabel;
    private javax.swing.JLabel nicknameLabel;
    private javax.swing.JTextField nicknameTextField;
    private ancestris.modules.editors.genealogyeditor.panels.NoteCitationsTablePanel noteCitationsTablePanel;
    private ancestris.modules.editors.genealogyeditor.panels.SourceCitationsTablePanel sourceCitationsTablePanel;
    // End of variables declaration//GEN-END:variables

    public void set(Indi mRoot, PropertyName mName) {
        this.mRoot = mRoot;
        this.mName = mName;

        changeSupport.mute();
        String version = mRoot.getGedcom().getGrammar().getVersion();
        if (version.equals("5.5")) {
            nameTypeLabel.setVisible(false);
            nameTypeComboBox.setVisible(false);
        }

        if (mName != null) {
            /*
             * Indicates the mName type, for example the mName issued or assumed
             * as an immigrant.
             */
            Property nameType = mName.getProperty("TYPE");
            if (nameType != null) {
                nameTypeComboBox.setSelectedItem(nameType.getValue());
            } else {
                nameTypeComboBox.setSelectedIndex(1);
            }
            nameTypeModified = false;

            /*
             * NPFX Non indexing mName piece that appears preceding the given
             * mName and surname parts. Different mName prefix parts are separated
             * by a comma.
             *
             */
            Property firstnamePrefix = mName.getProperty("NPFX");
            firstNamePrefixTextField.setText(firstnamePrefix != null ? firstnamePrefix.getValue() : "");
            firstNamePrefixModified = false;

            /*
             * GIVN Given mName or earned mName. Different given names are
             * separated by a comma.
             */
            Property givenName = mName.getProperty("GIVN");
            firstNameTextField.setText(givenName != null ? givenName.getValue() : mName.getFirstName());
            firstNameModified = false;

            /*
             * NSFX Non-indexing mName piece that appears after the given mName
             * and surname parts. Different mName suffix parts are separated by a
             * comma.
             */
            Property firstNameSuffix = mName.getProperty("NSFX");
            firstNameSuffixTextField.setText(firstNameSuffix != null ? firstNameSuffix.getValue() : "");
            firstNameSuffixModified = false;

            /*
             * SPFX surname prefix or article used in a family mName. Different
             * surname articles are separated by a comma, for example in the
             * mName "de la Cruz", this value would be "de, la".
             */
            Property familyNamePrefix = mName.getProperty("SPFX");
            familyNamePrefixTextField.setText(familyNamePrefix != null ? familyNamePrefix.getValue() : "");
            familyNamePrefixModified = false;

            /*
             * SURN Surname or family mName. Different surnames are separated by
             * a comma.
             */
            Property familyName = mName.getProperty("SURN");
            if (familyName != null) {
                familyNameTextField.setText(familyName.getValue());
            } else {
                familyNameTextField.setText(mName.getLastName());
            }
            familyNameModified = false;

            /*
             * NICK A descriptive or familiar mName used in connection with one's
             * proper mName.
             */
            Property nickName = mName.getProperty("NICK");
            nicknameTextField.setText(nickName != null ? nickName.getValue() : "");
            nicknameModified = false;

            if (firstnamePrefix != null || firstNameSuffix != null || familyNamePrefix != null) {
                jCheckBox1.setSelected(true);
                firstNamePrefixLabel.setVisible(true);
                firstNamePrefixTextField.setVisible(true);
                familyNamePrefixLabel.setVisible(true);
                familyNamePrefixTextField.setVisible(true);
                firstNameSuffixLabel.setVisible(true);
                firstNameSuffixTextField.setVisible(true);
            } else {
                jCheckBox1.setSelected(false);
                firstNamePrefixLabel.setVisible(false);
                firstNamePrefixTextField.setVisible(false);
                familyNamePrefixLabel.setVisible(false);
                familyNamePrefixTextField.setVisible(false);
                firstNameSuffixLabel.setVisible(false);
                firstNameSuffixTextField.setVisible(false);
            }
            /*
             * +1 <<NOTE_STRUCTURE>>
             */
            noteCitationsTablePanel.set(mName, Arrays.asList(mName.getProperties("NOTE")));

            /*
             * +1 <<SOURCE_CITATION>>
             */
            sourceCitationsTablePanel.set(mName, Arrays.asList(mName.getProperties("SOUR")));

        } else {
            nameTypeComboBox.setSelectedIndex(1);
            nameTypeModified = false;
            jCheckBox1.setSelected(false);
            firstNamePrefixLabel.setVisible(false);
            firstNamePrefixTextField.setVisible(false);
            familyNamePrefixLabel.setVisible(false);
            familyNamePrefixTextField.setVisible(false);
            firstNameSuffixLabel.setVisible(false);
            firstNameSuffixTextField.setVisible(false);
        }
        revalidate();
        changeSupport.setChanged(false);
        changeSupport.unmute();
    }

    /**
     * Whether the bean has changed since first listener was attached
     */
    public boolean hasChanged() {
        return changeSupport.hasChanged();
    }

    /**
     * Listener
     */
    public void addChangeListener(ChangeListener l) {
        changeSupport.addChangeListener(l);
    }

    /**
     * Listener
     */
    public void removeChangeListener(ChangeListener l) {
        changeSupport.removeChangeListener(l);
    }

    public void commit() {
        final String version = mRoot.getGedcom().getGrammar().getVersion();

        if (hasChanged()) {
            logger.log(Level.INFO, "Commiting ...");
            if (mName == null) {
                logger.log(Level.INFO, "Add property mName");

                mName = (PropertyName) mRoot.addProperty("NAME", "");
            }

            if (version.equals("5.5.1") && nameTypeModified == true) {

                Property nameType = mName.getProperty("TYPE");
                if (nameType == null) {
                    logger.log(Level.INFO, "Add property TYPE");

                    mName.addProperty("TYPE", nameTypeComboBox.getSelectedItem().toString());
                } else {
                    logger.log(Level.INFO, "Update property TYPE");

                    nameType.setValue(nameTypeComboBox.getSelectedItem().toString());
                }
            }

            /*
             * NPFX Non indexing mName piece that appears preceding the
             * given mName and surname parts. Different mName prefix parts
             * are separated by a comma.
             */
            if (firstNamePrefixModified == true) {
                Property firstnamePrefix = mName.getProperty("NPFX");
                if (firstnamePrefix == null) {
                    logger.log(Level.INFO, "Add property NPFX");

                    mName.addProperty("NPFX", firstNamePrefixTextField.getText().trim());
                } else {
                    logger.log(Level.INFO, "Update property NPFX");
                    firstnamePrefix.setValue(firstNamePrefixTextField.getText().trim());
                }
            }

            /*
             * GIVN Given mName or earned mName. Different given names are
             * separated by a comma.
             */
            if (firstNameModified == true) {
                Property givenName = mName.getProperty("GIVN");
                if (givenName == null) {
                    // Suppressed as an IndexOutOfBoundsException is thrown on undo
                    // logger.log(Level.INFO, "Add property GIVN");
                    // mName.addProperty("GIVN", firstNameTextField.getText().trim());
                } else {
                    logger.log(Level.INFO, "Update property GIVN");
                    givenName.setValue(firstNameTextField.getText().trim());
                }
            }

            if (firstNameSuffixModified == true) {
                Property firstNameSuffix = mName.getProperty("NSFX");
                if (firstNameSuffix == null) {
                    logger.log(Level.INFO, "Add property NSFX");
                    mName.addProperty("NSFX", firstNameSuffixTextField.getText().trim());
                } else {
                    logger.log(Level.INFO, "Update property NSFX");
                    firstNameSuffix.setValue(firstNameSuffixTextField.getText().trim());
                }
            }

            /*
             * SPFX surname prefix or article used in a family mName.
             * Different surname articles are separated by a comma, for
             * example in the mName "de la Cruz", this value would be
             * "de, la".
             */
            if (familyNamePrefixModified == true) {
                Property familyNamePrefix = mName.getProperty("SPFX");
                if (familyNamePrefix == null) {
                    logger.log(Level.INFO, "Add property SPFX");
                    mName.addProperty("SPFX", familyNamePrefixTextField.getText().trim());
                } else {
                    logger.log(Level.INFO, "Update property SPFX");
                    familyNamePrefix.setValue(familyNamePrefixTextField.getText().trim());
                }
            }

            /*
             * SURN Surname or family mName. Different surnames are
             * separated by a comma.
             */
            if (familyNameModified == true) {
                Property familyName = mName.getProperty("SURN");
                if (familyName == null) {
                    // Suppressed as an IndexOutOfBoundsException is thrown on undo
                    // logger.log(Level.INFO, "Add property SURN");
                    // mName.addProperty("SURN", familyNameTextField.getText().trim());
                } else {
                    logger.log(Level.INFO, "Update property SURN");
                    familyName.setValue(familyNameTextField.getText().trim());
                }
            }

            if (nicknameModified == true) {
                Property nickname = mName.getProperty("NICK");
                if (nickname == null) {
                    logger.log(Level.INFO, "Update property NICK");
                    mName.addProperty("NICK", nicknameTextField.getText().trim());
                } else {
                    logger.log(Level.INFO, "Update property NICK");
                    nickname.setValue(nicknameTextField.getText().trim());
                }
            }
            // ... store changed value
            mName.setName(
                    firstNamePrefixTextField.getText().trim(),
                    firstNameTextField.getText().trim(),
                    familyNamePrefixTextField.getText().trim(),
                    familyNameTextField.getText().trim(),
                    firstNameSuffixTextField.getText().trim(),
                    false);
            logger.log(Level.INFO, "... finished");
        }
        // clear changed
        changeSupport.setChanged(false);
    }

    PropertyName get() {
        return mName;
    }

    /**
     * @return the nameEditorTabbedPane
     */
    public javax.swing.JTabbedPane getNameEditorTabbedPane() {
        return nameEditorTabbedPane;
    }
}
