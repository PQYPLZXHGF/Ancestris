/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ancestris.trancestris.editors.resourceeditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.ancestris.trancestris.resources.ResourceFile;
import org.ancestris.trancestris.resources.ZipDirectory;
import org.openide.util.LookupEvent;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import org.openide.util.ImageUtilities;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.util.Lookup;
import org.openide.util.LookupListener;
import org.openide.util.Utilities;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//org.ancestris.trancestris.editors.resourceeditor//ResourceEditor//EN",
autostore = false)
public final class ResourceEditorTopComponent extends TopComponent implements LookupListener {

    private class Listener implements ListSelectionListener, ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionevent) {
            ResourceFile resourcefile = resourceFileView.getResourceFile();
            int i = resourceFileView.getSelectedIndex();
            if (i >= 0) {
                resourcefile.setLineTranslation(i, jTextAreaTranslation.getText());
            }
            resourceFileView.incSelection();
        }

        @Override
        public void valueChanged(ListSelectionEvent lse) {
            if (lse.getValueIsAdjusting()) {
                return;
            }
            String s = null;
            boolean flag = false;
            if (resourceFileView.getSelectedIndex() >= 0) {
                ResourceFile resourcefile = resourceFileView.getResourceFile();
                int i = resourceFileView.getSelectedIndex();
                flag = resourcefile.getLineState(i) != -1;
                s = resourcefile.getLineTranslation(i);
            }
            jTextAreaTranslation.setText(s);
            jTextAreaTranslation.setEditable(flag);
            jTextAreaTranslation.setCaretPosition(0);
            jButtonConfirm.setEnabled(flag);
        }
    }
    private static ResourceEditorTopComponent instance;
    /** path to the icon used by the component and its open action */
    static final String ICON_PATH = "org/ancestris/trancestris/editors/Advanced.png";
    private static final String PREFERRED_ID = "ResourceEditorTopComponent";
    private ResourceFileView resourceFileView;
    private Lookup.Result result = null;

    public ResourceEditorTopComponent() {
        resourceFileView = new ResourceFileView();
        Listener listener = new Listener();
        initComponents();
        setName(NbBundle.getMessage(ResourceEditorTopComponent.class, "CTL_ResourceEditorTopComponent"));
        setToolTipText(NbBundle.getMessage(ResourceEditorTopComponent.class, "HINT_ResourceEditorTopComponent"));
        setIcon(ImageUtilities.loadImage(ICON_PATH, true));
        putClientProperty(TopComponent.PROP_CLOSING_DISABLED, Boolean.TRUE);
        resourceFileView.addListSelectionListener(listener);
        jButtonConfirm.addActionListener(listener);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPaneResourceView = new javax.swing.JScrollPane(resourceFileView);
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaTranslation = new javax.swing.JTextArea();
        jButtonConfirm = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setResizeWeight(0.75);
        jSplitPane1.setLeftComponent(jScrollPaneResourceView);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jTextAreaTranslation.setColumns(20);
        jTextAreaTranslation.setRows(5);
        jScrollPane1.setViewportView(jTextAreaTranslation);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonConfirm, org.openide.util.NbBundle.getMessage(ResourceEditorTopComponent.class, "ResourceEditorTopComponent.jButtonConfirm.text")); // NOI18N
        jPanel1.add(jButtonConfirm, java.awt.BorderLayout.SOUTH);

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));
        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jSplitPane1.setRightComponent(jPanel1);

        add(jSplitPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonConfirm;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneResourceView;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTextArea jTextAreaTranslation;
    // End of variables declaration//GEN-END:variables

    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link #findInstance}.
     */
    public static synchronized ResourceEditorTopComponent getDefault() {
        if (instance == null) {
            instance = new ResourceEditorTopComponent();
        }
        return instance;
    }

    /**
     * Obtain the ResourceEditorTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized ResourceEditorTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            Logger.getLogger(ResourceEditorTopComponent.class.getName()).warning(
                    "Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof ResourceEditorTopComponent) {
            return (ResourceEditorTopComponent) win;
        }
        Logger.getLogger(ResourceEditorTopComponent.class.getName()).warning(
                "There seem to be multiple components with the '" + PREFERRED_ID
                + "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }

    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ALWAYS;
    }

    @Override
    public void componentOpened() {
        Lookup.Template tpl = new Lookup.Template(ZipDirectory.class);
        result = Utilities.actionsGlobalContext().lookup(tpl);
        result.addLookupListener(this);
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    Object readProperties(java.util.Properties p) {
        if (instance == null) {
            instance = this;
        }
        instance.readPropertiesImpl(p);
        return instance;
    }

    private void readPropertiesImpl(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }

    @Override
    public void resultChanged(LookupEvent le) {
        Lookup.Result r = (Lookup.Result) le.getSource();
        Collection c = r.allInstances();
        if (!c.isEmpty()) {
            for (Iterator i = c.iterator(); i.hasNext();) {
                ZipDirectory zipDirectory = (ZipDirectory) i.next();
                if (zipDirectory.getResourceFile() != null) {
                    resourceFileView.setResourceFile(zipDirectory.getResourceFile());
                }
            }
        }
    }
}
