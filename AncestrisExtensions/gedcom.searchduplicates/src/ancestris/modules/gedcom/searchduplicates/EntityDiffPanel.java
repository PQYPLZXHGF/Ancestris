package ancestris.modules.gedcom.searchduplicates;

import genj.gedcom.Entity;
import genj.gedcom.Property;
import genj.gedcom.TagPath;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author lemovice
 */
public class EntityDiffPanel extends javax.swing.JPanel {

    /**
     * Creates new form EntityDiffPanel
     */
    public EntityDiffPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        EntitiesIdPanel = new javax.swing.JPanel();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        LeftEntityIdLabel = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        rightEntityIdLabel = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        entityPropertiesScrollPane = new javax.swing.JScrollPane();
        entityPropertiesPanel = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        EntitiesIdPanel.setLayout(new javax.swing.BoxLayout(EntitiesIdPanel, javax.swing.BoxLayout.LINE_AXIS));
        EntitiesIdPanel.add(filler3);

        LeftEntityIdLabel.setText(org.openide.util.NbBundle.getMessage(EntityDiffPanel.class, "EntityDiffPanel.LeftEntityIdLabel.text")); // NOI18N
        EntitiesIdPanel.add(LeftEntityIdLabel);
        EntitiesIdPanel.add(filler1);

        rightEntityIdLabel.setText(org.openide.util.NbBundle.getMessage(EntityDiffPanel.class, "EntityDiffPanel.rightEntityIdLabel.text")); // NOI18N
        EntitiesIdPanel.add(rightEntityIdLabel);
        EntitiesIdPanel.add(filler2);

        add(EntitiesIdPanel, java.awt.BorderLayout.NORTH);

        entityPropertiesPanel.setLayout(new javax.swing.BoxLayout(entityPropertiesPanel, javax.swing.BoxLayout.Y_AXIS));
        entityPropertiesScrollPane.setViewportView(entityPropertiesPanel);

        add(entityPropertiesScrollPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel EntitiesIdPanel;
    private javax.swing.JLabel LeftEntityIdLabel;
    private javax.swing.JPanel entityPropertiesPanel;
    private javax.swing.JScrollPane entityPropertiesScrollPane;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.JLabel rightEntityIdLabel;
    // End of variables declaration//GEN-END:variables

    public void setEntities(Entity leftEntity, Entity rightEntity) {
        ArrayList<TagPath> entityTagArray = new ArrayList<TagPath>();

        entityTagArray.clear();

        for (Property property : leftEntity.getProperties()) {
            entityTagArray.add(property.getPath());
        }
        
        for (Property property : rightEntity.getProperties()) {
            if (!entityTagArray.contains(property.getPath())) {
                entityTagArray.add(property.getPath());
            }
        }

        LeftEntityIdLabel.setText(leftEntity.getId());
        rightEntityIdLabel.setText(rightEntity.getId());
        entityPropertiesPanel.removeAll();
        for (Iterator<TagPath> it = entityTagArray.iterator(); it.hasNext();) {
            TagPath tagPath = it.next();
            PropertiesDiffPanel propertiesDiffPanel = new PropertiesDiffPanel();
            propertiesDiffPanel.set(leftEntity.getProperty(tagPath), rightEntity.getProperty(tagPath));
            entityPropertiesPanel.add(propertiesDiffPanel);
        }
    }
}
