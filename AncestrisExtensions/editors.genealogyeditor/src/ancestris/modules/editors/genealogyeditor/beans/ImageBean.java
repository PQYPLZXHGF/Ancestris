package ancestris.modules.editors.genealogyeditor.beans;

import genj.gedcom.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;

/**
 *
 * @author dominique
 */
public class ImageBean extends javax.swing.JPanel {

    private final static Logger logger = Logger.getLogger(ImageBean.class.getName(), null);
    private boolean imageModified = false;
    private Property root;
    Property multimediaObject;
    private BufferedImage resizedImage;
    private File imageFile;

    /**
     * Creates new form ImageBean
     */
    public ImageBean() {
        super();
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

        setToolTipText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ancestris/modules/editors/genealogyeditor/beans/Bundle").getString("ImageBean.toolTipText"), new Object[] {})); // NOI18N
        setMinimumSize(new java.awt.Dimension(30, 40));
        setPreferredSize(new java.awt.Dimension(150, 200));
        setRequestFocusEnabled(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        FileNameExtensionFilter imageFileFilter = new FileNameExtensionFilter(NbBundle.getMessage(ImageBean.class, "ImageBean.fileType"), "jpeg");
        JFileChooser imageFileChooser = new JFileChooser();

        imageFileChooser.setFileFilter(imageFileFilter);
        imageFileChooser.setAcceptAllFileFilterUsed(true);
        imageFileChooser.setSelectedFile(imageFile);
        if (imageFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            imageFile = imageFileChooser.getSelectedFile();
            try {
                BufferedImage loadImage = ImageIO.read(imageFile);
                resizedImage = resizeImage(loadImage, 150, 200);
                this.repaint();
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            } finally {
                imageModified = true;
            }
        }
    }//GEN-LAST:event_formMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    public void setImage(Property root, Property multimediaObject) {
        this.root = root;
        this.multimediaObject = multimediaObject;

        if (multimediaObject != null) {
            Property file = multimediaObject.getProperty("FILE", true);
            if (file instanceof PropertyFile) {
                imageFile = ((PropertyFile) file).getFile();
                try {
                    BufferedImage loadImage = ImageIO.read(imageFile);
                    resizedImage = resizeImage(loadImage, 150, 200);
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
    }

    public BufferedImage resizeImage(BufferedImage img, int newW, int newH) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
        Graphics2D g = dimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return dimg;
    }

    public void commit() {
        logger.log(Level.INFO, "Commiting ...");
        try {
            root.getGedcom().doUnitOfWork(new UnitOfWork() {

                @Override
                public void perform(Gedcom gedcom) throws GedcomException {
                    if (multimediaObject != null && imageModified == true) {
                        Property file = multimediaObject.getProperty("FILE", true);
                        if (file instanceof PropertyFile) {
                            logger.log(Level.INFO, "Update property FILE");

                            ((PropertyFile) file).addFile(imageFile);
                        }
                    }
                }
            }); // end of doUnitOfWork
        } catch (GedcomException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        }

        logger.log(Level.INFO, "... finished");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(resizedImage, 0, 0, null);
    }
}
