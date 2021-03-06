/*
 * MergeOptionPanel.java
 *
 * Created on 23 févr. 2014, 11:27:52
 */

package ancestris.modules.releve.dnd;

import genj.gedcom.Source;
import java.awt.Frame;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;

/**
 *
 * @author Michel
 */
public class MergeOptionPanel extends javax.swing.JPanel {

    private File currentFile; 
    private Frame parent;

    /** Creates new form MergeOptionPanel */
    public MergeOptionPanel() {
        initComponents();
    }

    public void initData(java.awt.Frame parent, File  currentFile) {
        this.parent = parent;
        this.currentFile = currentFile;

        SourceModel sourceModel = SourceModel.getModel();
        jTableSource.setModel(sourceModel);
        if ( sourceModel.getRowCount() > 0) {
            // je selectionne la première ligne
            jTableSource.getSelectionModel().setSelectionInterval(0, 0);
        }

        if( currentFile != null && sourceModel.exist(currentFile.getName())) {
            jButtonAddSource.setVisible(false);
        } else {
            jButtonAddSource.setVisible(true);
        }

    }

    public void loadPreferences() {
        SourceModel.getModel().loadPreferences();
    }
    
    public void savePreferences() {
        SourceModel.getModel().savePreferences();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanelSimilar = new javax.swing.JPanel();
        jButtonSimilarFirstNames = new javax.swing.JButton();
        jButtonSimilarLastNames = new javax.swing.JButton();
        jPanelSource = new javax.swing.JPanel();
        jLabelAssociateLabel = new javax.swing.JLabel();
        jScrollPaneSource = new javax.swing.JScrollPane();
        jTableSource = new javax.swing.JTable();
        jButtonAddSource = new javax.swing.JButton();
        jButtonModify = new javax.swing.JButton();
        jButtonRemoveSource = new javax.swing.JButton();
        jLabelFiller = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(MergeOptionPanel.class, "MergeOptionPanel.jPanel.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        setLayout(new java.awt.BorderLayout());

        jPanelSimilar.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jButtonSimilarFirstNames.setText(org.openide.util.NbBundle.getMessage(MergeOptionPanel.class, "MergeOptionPanel.jButtonSimilarFirstNames.text")); // NOI18N
        jButtonSimilarFirstNames.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSimilarFirstNamesActionPerformed(evt);
            }
        });
        jPanelSimilar.add(jButtonSimilarFirstNames);

        jButtonSimilarLastNames.setText(org.openide.util.NbBundle.getMessage(MergeOptionPanel.class, "MergeOptionPanel.jButtonSimilarLastNames.text")); // NOI18N
        jButtonSimilarLastNames.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSimilarLastNamesActionPerformed(evt);
            }
        });
        jPanelSimilar.add(jButtonSimilarLastNames);

        add(jPanelSimilar, java.awt.BorderLayout.PAGE_START);

        jPanelSource.setPreferredSize(new java.awt.Dimension(400, 200));
        jPanelSource.setLayout(new java.awt.GridBagLayout());

        jLabelAssociateLabel.setText(org.openide.util.NbBundle.getMessage(MergeOptionPanel.class, "MergeOptionPanel.jLabelAssociateLabel.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 2, 2, 2);
        jPanelSource.add(jLabelAssociateLabel, gridBagConstraints);

        jTableSource.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title1", "Title2"
            }
        ));
        jTableSource.setMaximumSize(new java.awt.Dimension(2147483647, 500));
        jTableSource.setMinimumSize(new java.awt.Dimension(60, 60));
        jTableSource.setPreferredSize(null);
        jTableSource.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPaneSource.setViewportView(jTableSource);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelSource.add(jScrollPaneSource, gridBagConstraints);

        jButtonAddSource.setText(org.openide.util.NbBundle.getMessage(MergeOptionPanel.class, "MergeOptionPanel.jButtonAddSource.text")); // NOI18N
        jButtonAddSource.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddSourceActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        jPanelSource.add(jButtonAddSource, gridBagConstraints);

        jButtonModify.setText(org.openide.util.NbBundle.getMessage(MergeOptionPanel.class, "MergeOptionPanel.jButtonModify.text")); // NOI18N
        jButtonModify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModifyActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        jPanelSource.add(jButtonModify, gridBagConstraints);

        jButtonRemoveSource.setText(org.openide.util.NbBundle.getMessage(MergeOptionPanel.class, "MergeOptionPanel.jButtonRemoveSource.text")); // NOI18N
        jButtonRemoveSource.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveSourceActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        jPanelSource.add(jButtonRemoveSource, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        jPanelSource.add(jLabelFiller, gridBagConstraints);

        add(jPanelSource, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    private void jButtonAddSourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddSourceActionPerformed
        
        if( currentFile != null && !currentFile.getName().isEmpty()) {

            // j'affiche la fenetre de selection d'une source
            Source newSource = RecordSourceConfigDialog.show(parent, currentFile.getName(), "");
            // j'enregistre la source
            if( newSource != null) {
                SourceModel.getModel().add(currentFile.getName(), newSource.getTitle());
                // je selectionne la dernière ligne du modele
                int index = jTableSource.convertRowIndexToView(SourceModel.getModel().getRowCount() - 1);
                jTableSource.getSelectionModel().setSelectionInterval(index, index);
            }
        } else {
            // j'affiche le message d'erreur
            String message = NbBundle.getMessage(MergeOptionPanel.class, "MergeOptionPanel.message.FirstSaveFile");
            String title = jLabelAssociateLabel.getText();
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
        }
}//GEN-LAST:event_jButtonAddSourceActionPerformed

    private void jButtonRemoveSourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveSourceActionPerformed
        if( jTableSource.getSelectedRow() != -1) {
            SourceModel.getModel().remove(jTableSource.convertRowIndexToModel(jTableSource.getSelectedRow()));
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_jButtonRemoveSourceActionPerformed

    private void jButtonModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModifyActionPerformed
        if (jTableSource.getSelectedRow() != -1) {
            int index = jTableSource.convertRowIndexToModel(jTableSource.getSelectedRow());
            String fileName = (String) SourceModel.getModel().getValueAt(index, 0);
            String sourceName = (String) SourceModel.getModel().getValueAt(index, 1);
            // j'affiche la fenetre de selection d'une source
            Source newSource = RecordSourceConfigDialog.show(parent, fileName, sourceName);
            // j'enregistre la source
            if (newSource != null) {
                SourceModel.getModel().modify(index, newSource.getTitle());
            }
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_jButtonModifyActionPerformed

    private void jButtonSimilarFirstNamesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSimilarFirstNamesActionPerformed
        SimilarNameDialog.showSimilarFirstNamePanel();
}//GEN-LAST:event_jButtonSimilarFirstNamesActionPerformed

    private void jButtonSimilarLastNamesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSimilarLastNamesActionPerformed
        SimilarNameDialog.showSimilarLastNamePanel();
}//GEN-LAST:event_jButtonSimilarLastNamesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddSource;
    private javax.swing.JButton jButtonModify;
    private javax.swing.JButton jButtonRemoveSource;
    private javax.swing.JButton jButtonSimilarFirstNames;
    private javax.swing.JButton jButtonSimilarLastNames;
    private javax.swing.JLabel jLabelAssociateLabel;
    private javax.swing.JLabel jLabelFiller;
    private javax.swing.JPanel jPanelSimilar;
    private javax.swing.JPanel jPanelSource;
    private javax.swing.JScrollPane jScrollPaneSource;
    private javax.swing.JTable jTableSource;
    // End of variables declaration//GEN-END:variables


    static private class SourceModelElement {
        public String fileName;
        public String sourceName;

        public SourceModelElement(String fileName, String sourceName) {
            this.fileName = fileName;
            this.sourceName = sourceName;
        }
    }

    static public class SourceModel extends AbstractTableModel {
        static final  String SOURCE_PREFERENCE = "RecordSource";
        static private  SourceModel sourceModel = null;

        final String columnName[] = {
            NbBundle.getMessage(SourceModel.class, "MergeOptionPanel.column.title.file"),
            NbBundle.getMessage(SourceModel.class, "MergeOptionPanel.column.title.source")
        };

        final Class columnClass[] = {String.class, String.class};

        // données du modèle 
        private final ArrayList<SourceModelElement> sourceList = new ArrayList<SourceModelElement>();

        /** 
         * model factory
         * @return 
         */
        static public SourceModel getModel() {

            if (sourceModel == null) {
                sourceModel = new SourceModel();
                sourceModel.loadPreferences();
            }
            return sourceModel;
        }

        /**
         * charge les repertoires
         */
        private void loadPreferences() {
            sourceList.clear();
            // je recupere la liste des valeurs similaires
            String stringData = NbPreferences.forModule(SourceModel.class).get(
                    SOURCE_PREFERENCE, "");
            String[] arrayData = stringData.split(";");
            for (String arrayData1 : arrayData) {
                if (!arrayData1.isEmpty()) {
                    String[] item = arrayData1.split("=");
                    if( item.length == 2) {
                        sourceList.add(new SourceModelElement(item[0], item[1]));
                    }
                }
            }
        }

        /**
         * enregistre les paires fileName=sourceName
         */
        public void savePreferences() {
            StringBuilder values = new StringBuilder();

            for (SourceModelElement element : sourceList) {
                values.append(element.fileName)
                        .append("=")
                        .append(element.sourceName)
                        .append(";");
            }
            NbPreferences.forModule(SourceModel.class).put(
                    SOURCE_PREFERENCE, values.toString());
        }

        
        public void add(String fileName, String sourceName) {
            int index = index(fileName);
            if (index!= -1) {
                sourceList.get(index).sourceName = sourceName;
                fireTableRowsUpdated(index, index);
            } else {
                sourceList.add(new SourceModelElement(fileName, sourceName));
                index = sourceList.size()-1;
                fireTableRowsInserted(index, index);
            }            
        }

        public void remove(int index) {
            sourceList.remove(index);
            fireTableRowsDeleted(index, index);
        }

        public void modify(int index, String sourceName) {
            sourceList.get(index).sourceName = sourceName;
            fireTableRowsUpdated(index, index);
        }

        public int index(String fileName) {
            for(int i=0; i < sourceList.size(); i++) {
                if( sourceList.get(i).fileName.equalsIgnoreCase(fileName)) {
                    return i;
                }
            }
            return -1;
        }

        public boolean exist(String fileName) {
            if( index(fileName)== -1) {
                return false;
            } else {
                return true;
            }
        }

        public String getSource(String fileName) {
            int index = index(fileName);
            if( index != -1) {
                return sourceList.get(index).sourceName;
            } else {
                return "";
            }            
        }

        @Override
        public String getColumnName(int col) {
            return columnName[col];
        }

        @Override
        public Class<?> getColumnClass(int col) {
            return columnClass[col];
        }

        @Override
        public int getRowCount() {
            return sourceList.size();
        }

        @Override
        public int getColumnCount() {
            return columnClass.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return sourceList.get(rowIndex).fileName;
                default:
                    return sourceList.get(rowIndex).sourceName;
            }

        }
    }
}
