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

import ancestris.modules.treesharing.communication.EntityConversion;
import ancestris.modules.treesharing.options.TreeSharingOptionsPanel;
import genj.gedcom.Entity;
import genj.gedcom.Gedcom;
import genj.gedcom.Indi;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import org.apache.commons.lang.StringEscapeUtils;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;

/**
 *
 * @author frederic
 */
public class EntitiesListPanel extends javax.swing.JPanel {

    private final Set<MatchData> list;
    private StringBuffer textToPaste;
    
    private final static int IMG_MEDIUM_WIDTH = 51;

    private final static ImageIcon nophoto = new ImageIcon(ImageUtilities.loadImage("ancestris/modules/treesharing/resources/nophoto.png"));
    private final static ImageIcon ArrowButton = new ImageIcon(ImageUtilities.loadImage("ancestris/modules/treesharing/resources/dropdownarrow.png"));
    private final static ImageIcon AllMembers = new ImageIcon(ImageUtilities.loadImage("ancestris/modules/treesharing/resources/allMembers.png"));
    private final static String allGedcoms = NbBundle.getMessage(EntitiesListPanel.class, "STR_AllGedcoms");
    private final static String allMembers = NbBundle.getMessage(EntitiesListPanel.class, "STR_AllMembers");
    
    private SortedSet<String> myGedcoms = new TreeSet<String>();
    private SortedSet<String> memberGedcoms = new TreeSet<String>();
    private TreeMap<String, ImageIcon> members = new TreeMap<String, ImageIcon>();
    
    private String[] arrayMyGedcoms;
    private String[] arrayMemberGedcoms;
    private ImageIcon[] arrayMemberIcons;
    private String[] arrayMemberStrings;
    
    private boolean busy = false;
    
    /**
     * Creates new form ListEntitiesPanel
     */
    public EntitiesListPanel(String gedcomName, String friend, Set<MatchData> list, String typeOfEntity) {
        this.list = list;
        this.textToPaste = new StringBuffer("");

        // Initialise with lists
        initComponents();
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(20);
        jScrollPane1.getHorizontalScrollBar().setUnitIncrement(20);
        jComboBox2.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                return new JButton() {
                    @Override
                    public int getWidth() {
                        return 20;
                    }
                    @Override
                    public int getX() {
                        return IMG_MEDIUM_WIDTH+1;
                    }
                    @Override
                    public Icon getIcon() {
                        return ArrowButton;
                    }
                };
            }
        
        });
   
        // Restore window size
        restoreWindowSize();
        
        // Set my picture
        jLabel2.setText(TreeSharingOptionsPanel.getPseudo());
        jLabel1.setIcon(TreeSharingOptionsPanel.getPhoto(2, TreeSharingOptionsPanel.getProfile().photoBytes));
        
        // Set checkboxes
        busy = true;
        jCheckBox1.setSelected(typeOfEntity.equals(Gedcom.INDI));
        jCheckBox2.setSelected(typeOfEntity.equals(Gedcom.FAM));
        
        // Build filtered list from selection
        buildFilteredLists(0, null, null, null);
        
        // Update panel display
        jComboBox1.setSelectedItem(gedcomName);
        jComboBox2.setSelectedIndex(getIndexOf(friend));
        jComboBox3.setSelectedIndex(0);
        updatePanelDisplay();
        busy = false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox3 = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox1 = new javax.swing.JCheckBox();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(EntitiesListPanel.class, "EntitiesListPanel.jLabel1.text")); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel1.setOpaque(true);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(EntitiesListPanel.class, "EntitiesListPanel.jLabel2.text")); // NOI18N

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(EntitiesListPanel.class, "EntitiesListPanel.jLabel3.text")); // NOI18N

        jComboBox2.setToolTipText(org.openide.util.NbBundle.getMessage(EntitiesListPanel.class, "EntitiesListPanel.jComboBox2.toolTipText")); // NOI18N
        jComboBox2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jComboBox2.setMinimumSize(new java.awt.Dimension(51, 62));
        jComboBox2.setPreferredSize(new java.awt.Dimension(51, 62));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));
        jScrollPane1.setViewportView(jPanel1);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/treesharing/resources/Indi.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(EntitiesListPanel.class, "EntitiesListPanel.jLabel4.text")); // NOI18N
        jLabel4.setToolTipText(org.openide.util.NbBundle.getMessage(EntitiesListPanel.class, "EntitiesListPanel.jLabel4.toolTipText")); // NOI18N

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ancestris/modules/treesharing/resources/Fam.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(EntitiesListPanel.class, "EntitiesListPanel.jLabel7.text")); // NOI18N
        jLabel7.setToolTipText(org.openide.util.NbBundle.getMessage(EntitiesListPanel.class, "EntitiesListPanel.jLabel7.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBox2, org.openide.util.NbBundle.getMessage(EntitiesListPanel.class, "EntitiesListPanel.jCheckBox2.text")); // NOI18N
        jCheckBox2.setToolTipText(org.openide.util.NbBundle.getMessage(EntitiesListPanel.class, "EntitiesListPanel.jCheckBox2.toolTipText")); // NOI18N
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBox1, org.openide.util.NbBundle.getMessage(EntitiesListPanel.class, "EntitiesListPanel.jCheckBox1.text")); // NOI18N
        jCheckBox1.setToolTipText(org.openide.util.NbBundle.getMessage(EntitiesListPanel.class, "EntitiesListPanel.jCheckBox1.toolTipText")); // NOI18N
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                                .addComponent(jCheckBox1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4))
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox2)
                                .addGap(33, 33, 33)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE))
                            .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jCheckBox2)
                            .addComponent(jLabel7)
                            .addComponent(jCheckBox1)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        if (!busy) {
            updateSelections(2);
            updatePanelDisplay();
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        if (!busy) {
            updateSelections(1);
            updatePanelDisplay();
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        if (!busy) {
            updateSelections(3);
            updatePanelDisplay();
        }
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (!busy) {
            updatePanelDisplay();
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        if (!busy) {
            updatePanelDisplay();
        }
    }//GEN-LAST:event_jCheckBox2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    private void buildFilteredLists(int listNb, String gedcomName, String friend, String memberGedcomName) {

        // Clear lists
        if (listNb != 1) myGedcoms.clear();
        if (listNb != 2) members.clear();
        if (listNb != 3) memberGedcoms.clear();

        // Add generic elements
        if (listNb != 1) myGedcoms.add(allGedcoms);
        if (listNb != 2) members.put(allMembers, AllMembers);
        if (listNb != 3) memberGedcoms.add(allGedcoms);
        
        // Fill in with filtered criteria
        String iGedcomName = "";
        String iFriend = "";
        String iMemberGedcomName = "";
        for (MatchData line : list) {
            iGedcomName = line.myEntity.getGedcom().getName();
            iFriend = line.friendGedcomEntity.friend;
            iMemberGedcomName = line.friendGedcomEntity.gedcomName;
            if (match("", gedcomName, friend, memberGedcomName, "", iGedcomName, iFriend, iMemberGedcomName)) {
                if (listNb != 1) myGedcoms.add(iGedcomName);
                if (listNb != 2) { 
                    AncestrisFriend af = line.friendGedcomEntity.afriend;
                    ImageIcon icon =  (af != null ? (af.getFriendProfile() != null ? TreeSharingOptionsPanel.getPhoto(2, af.getFriendProfile().photoBytes) : null) : null);
                    if (icon == null) {
                        icon = nophoto;
                    }
                    members.put(iFriend, icon);
                }
                if (listNb != 3) memberGedcoms.add(iMemberGedcomName);
            }
        }
        
        // Overwrite arrays
        if (listNb != 1) arrayMyGedcoms = myGedcoms.toArray(new String[myGedcoms.size()]);
        if (listNb != 2) arrayMemberStrings = members.keySet().toArray(new String[members.keySet().size()]);
        if (listNb != 2) arrayMemberIcons = members.values().toArray(new ImageIcon[members.values().size()]);
        if (listNb != 3) arrayMemberGedcoms = memberGedcoms.toArray(new String[memberGedcoms.size()]);

        // update comboboxes
        if (listNb != 1) jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(arrayMyGedcoms));
        if (listNb != 2) jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(arrayMemberIcons));
        if (listNb != 3) jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(arrayMemberGedcoms));
    }

    private void updateSelections(int listNb) {
        String selection2 = arrayMemberStrings[jComboBox2.getSelectedIndex()];
        if (listNb == 2 && jComboBox2.getSelectedIndex() == 0) {   // if all members are selected, select all on all lists
            jComboBox1.setSelectedIndex(0);
            jComboBox3.setSelectedIndex(0);
        }
        String selection1 = arrayMyGedcoms[jComboBox1.getSelectedIndex()];
        String selection3 = arrayMemberGedcoms[jComboBox3.getSelectedIndex()];
        busy = true;
        buildFilteredLists(listNb, listNb == 1 ? selection1 : null, listNb == 2 ? selection2 : null, listNb == 3 ? selection3 : null);
        if (arrayMyGedcoms.length == 2) {
            jComboBox1.setSelectedIndex(1);
        } else {
            jComboBox1.setSelectedItem(selection1);
        }
        if (arrayMemberStrings.length == 2) {
            jComboBox2.setSelectedIndex(1);
        } else {
            jComboBox2.setSelectedIndex(getIndexOf(selection2));
        }
        if (arrayMemberGedcoms.length == 2) {
            jComboBox3.setSelectedIndex(1);
        } else {
            jComboBox3.setSelectedItem(selection3);
        }
        busy = false;
    }

    private boolean match(String type, String gedcomName, String friend, String memberGedcomName, String iType, String iGedcomName, String iFriend, String iMemberGedcomName) {

        String gn = gedcomName, f= friend, mgn = memberGedcomName;

        if (type == null || type.isEmpty()) {
            type = iType;
        }
        if (gn == null || gn.isEmpty() || gn.equals(allGedcoms)) {
            gn = iGedcomName;
        }
        if (f == null || f.isEmpty() || f.equals(allMembers)) {
            f = iFriend;
        }
        if (mgn == null || mgn.isEmpty() || mgn.equals(allGedcoms)) {
            mgn = iMemberGedcomName;
        }
        
        return (type.equals(iType) && gn.equals(iGedcomName) && f.equals(iFriend) && mgn.equals(iMemberGedcomName));
    }

    private int getIndexOf(String selection) {
        for (int i = 0; i < arrayMemberStrings.length; i++) {
            String item = arrayMemberStrings[i];
            if (item.equals(selection)) {
                return i;
            }
        }
        return 0;
    }



    
    private void updatePanelDisplay() {

        TreeMap<String, MatchData> sortedMatches = new TreeMap<String, MatchData>();   //  String key is mygedcomname(asc)/myEntityString(asc)/matchresult(asc)
        
        
        // Set label
        jLabel3.setText(arrayMemberStrings[jComboBox2.getSelectedIndex()]);

        // Get criteria
        String type = "";
        if (jCheckBox1.isSelected() && !jCheckBox2.isSelected()) type = Gedcom.INDI;
        if (!jCheckBox1.isSelected() && jCheckBox2.isSelected()) type = Gedcom.FAM;
        String gedcomName = arrayMyGedcoms[jComboBox1.getSelectedIndex()];
        String friend = arrayMemberStrings[jComboBox2.getSelectedIndex()];
        String memberGedcomName = arrayMemberGedcoms[jComboBox3.getSelectedIndex()];
        
        // Scan list and build sorted maps
        String key = "";
        String iType = "";
        String iGedcomName = "";
        String iFriend = "";
        String iMemberGedcomName = "";
        for (MatchData line : list) {
            iType = line.myEntity instanceof Indi ? Gedcom.INDI : Gedcom.FAM;
            iGedcomName = line.myEntity.getGedcom().getName();
            iFriend = line.friendGedcomEntity.friend;
            iMemberGedcomName = line.friendGedcomEntity.gedcomName;
            if (match(type, gedcomName, friend, memberGedcomName, iType, iGedcomName, iFriend, iMemberGedcomName)) {
                key = iGedcomName + "-" + line.myEntity.toString() + "-" + line.matchResult + line.friendGedcomEntity.indiLastName;
                sortedMatches.put(key, line);
            }
        }
        
        // Display sortedMap
        textToPaste.delete(0, textToPaste.length());
        jPanel1.removeAll();
        jPanel1.repaint();
        BoxLayout layout = new BoxLayout(jPanel1, BoxLayout.PAGE_AXIS);
        jPanel1.setLayout(layout);
        String group = "", strItem = "", str = "";
        Entity currentEntity = null;
        List<MatchData> subList = new LinkedList<MatchData>();
        int i = 0;
        for (String k : sortedMatches.keySet()) {
            MatchData line = sortedMatches.get(k);
            textToPaste.append(convertToText(line));
            strItem = EntityConversion.getStringFromEntity(line.myEntity, false);
            if (!group.equals(strItem)) { // Group break
                if (i == 0) {               // Start of list
                    group = strItem;
                    currentEntity = line.myEntity;
                } else {                    // End of previous group
                    addEntityBloc(currentEntity, subList);
                    group = strItem;
                    currentEntity = line.myEntity;
                }
                i = 0;
                subList.clear();
            }
            i++;
            subList.add(line);
        }
        if (i != 0) {
            addEntityBloc(currentEntity, subList);
        }
        jPanel1.repaint();
        jPanel1.validate();
        //jPanel1.add(Box.createVerticalGlue());
        
    }

    
    private void addEntityBloc(Entity currentEntity, List<MatchData> subList) {
        EntityBean bean = new EntityBean(currentEntity, subList);
        jPanel1.add(bean);
    }

    private void restoreWindowSize() {
        int width = Integer.valueOf(NbPreferences.forModule(EntitiesListPanel.class).get("EntitiesListPanel.width", "0"));   
        int height = Integer.valueOf(NbPreferences.forModule(EntitiesListPanel.class).get("EntitiesListPanel.height", "0"));   
        if (width * height != 0) {
            this.setPreferredSize(new Dimension(width, height));
        }
    }

    private void saveWindowSize() {
        Component c = this;
        NbPreferences.forModule(EntitiesListPanel.class).put("EntitiesListPanel.width", "" + c.getBounds().width);   
        NbPreferences.forModule(EntitiesListPanel.class).put("EntitiesListPanel.height", "" + c.getBounds().height);   
    }

    public void close() {
        saveWindowSize();
    }

    public void copy() {
        String str = textToPaste.toString();
        StringSelection stringSelection = new StringSelection(str);
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        clpbrd.setContents(stringSelection, null);
    }

    private StringBuffer convertToText(MatchData line) {
        StringBuffer sb = new StringBuffer("");
        sb.append(line.myEntity.getGedcom().getName());
        sb.append("\t");
        sb.append(StringEscapeUtils.unescapeHtml(EntityConversion.getStringFromEntity(line.myEntity, false)));
        sb.append("\t");
        sb.append(StringEscapeUtils.unescapeHtml(EntityConversion.getStringFromEntity(line.friendGedcomEntity, false)));
        sb.append("\t");
        sb.append(line.friendGedcomEntity.gedcomName);
        sb.append("\t");
        sb.append(line.friendGedcomEntity.friend);
        sb.append("\t");
        sb.append(line.matchResult);
        sb.append("\n");
        return sb;
    }
    
}
