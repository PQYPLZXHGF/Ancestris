package ancestris.modules.editors.genealogyeditor.panels;

import ancestris.modules.editors.genealogyeditor.models.MultiMediaObjectsTableModel;
import ancestris.modules.editors.genealogyeditor.renderer.TextPaneTableCellRenderer;
import genj.gedcom.*;
import java.util.List;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author dominique
 */
public class MultimediaObjectTablePanel extends javax.swing.JPanel {

    private Property mRoot;
    private MultiMediaObjectsTableModel mMultiMediaObjectsTableModel = new MultiMediaObjectsTableModel();

    /**
     * Creates new form MultimediaObjectTablePanel
     */
    public MultimediaObjectTablePanel() {
        initComponents();
        MultimediaObjectsTable.setID(MultimediaObjectTablePanel.class.getName());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MultimediaObjectTableScrollPane = new javax.swing.JScrollPane();
        MultimediaObjectsTable = new ancestris.modules.editors.genealogyeditor.table.EditorTable() {
            public TableCellRenderer getCellRenderer(int row, int column) {
                if (column == 1) {
                    return new TextPaneTableCellRenderer ();
                }
                return super.getCellRenderer(row, column);
            }
        };

        MultimediaObjectsTable.setModel(mMultiMediaObjectsTableModel);
        MultimediaObjectsTable.setSelectionBackground(new java.awt.Color(89, 142, 195));
        MultimediaObjectTableScrollPane.setViewportView(MultimediaObjectsTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MultimediaObjectTableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MultimediaObjectTableScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane MultimediaObjectTableScrollPane;
    private ancestris.modules.editors.genealogyeditor.table.EditorTable MultimediaObjectsTable;
    // End of variables declaration//GEN-END:variables

    public void set(Property root, List<Media> MultimediaObjectsList) {
        this.mRoot = root;
        mMultiMediaObjectsTableModel.clear();
        mMultiMediaObjectsTableModel.addAll(MultimediaObjectsList);
    }

    public Media getSelectedMultiMediaObject() {
        int selectedRow = MultimediaObjectsTable.getSelectedRow();
        if (selectedRow != -1) {
            int rowIndex = MultimediaObjectsTable.convertRowIndexToModel(selectedRow);
            return mMultiMediaObjectsTableModel.getValueAt(rowIndex);
        } else {
            return null;
        }
    }
}
