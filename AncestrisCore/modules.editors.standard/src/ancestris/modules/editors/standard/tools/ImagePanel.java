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
package ancestris.modules.editors.standard.tools;

import ancestris.modules.editors.standard.IndiPanel;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.openide.util.Exceptions;
import org.openide.windows.WindowManager;

/**
 *
 * @author frederic
 */
public class ImagePanel extends javax.swing.JPanel {

    private IndiPanel callingPanel = null;
    
    private int default_width = 197, default_height = 140;
    private BufferedImage IMG_NO_SOURCE_MEDIA = null;
    private BufferedImage image = null;
    private File file = null;
    
    private int x, y;
    private static int startX, startY;
    private double sourceZoom;
    private boolean ready;
    
    private final static RenderingHints textRenderHints = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    private final static RenderingHints imageRenderHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    private final static RenderingHints renderHints = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    
    
    /**
     * Creates new form imagePanel
     */
    public ImagePanel(IndiPanel callingPanel) {
        this.callingPanel = callingPanel;
        this.x = 0;
        this.y = 0;
        this.startX = 0;
        this.startY = 0;
        this.ready = false;
        try {
            IMG_NO_SOURCE_MEDIA = ImageIO.read(getClass().getResourceAsStream("/ancestris/modules/editors/standard/images/source_dummy.png"));
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        this.image = IMG_NO_SOURCE_MEDIA;
        initComponents();
        if (callingPanel == null) {
            setToolTipText(org.openide.util.NbBundle.getMessage(ImagePanel.class, "ImagePanel.toolTipText2"));
        }
    }

    public void setMedia(File file) {
        this.file = file;
        if (file != null && file.exists()) {
            try {
                image = ImageIO.read(file);
            } catch (IOException ex) {
                image = IMG_NO_SOURCE_MEDIA;
            }
        } else {
            image = IMG_NO_SOURCE_MEDIA;
        }
        final ImagePanel ip = this;
        WindowManager.getDefault().invokeWhenUIReady(new Runnable() {

            @Override
            public void run() {

                sourceZoom = (double) (ip.getWidth()) / (double) image.getWidth();
                double s2 = (double) (ip.getHeight()) / (double) image.getHeight();
                if (s2 < sourceZoom) {
                    sourceZoom = s2;
                }
                if (sourceZoom > 20) {
                    sourceZoom = 20;
                }
                if (sourceZoom < 0.1) {
                    sourceZoom = 0.1;
                }
                x = (int) ((ip.getWidth() / 2 - (int) (image.getWidth() * sourceZoom / 2)) / sourceZoom);
                y = (int) ((ip.getHeight() / 2 - (int) (image.getHeight() * sourceZoom / 2)) / sourceZoom);
                ready = true;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        if (!ready) {
            return;
        }
        
        Graphics2D g2d = (Graphics2D) grphcs;
        applyRenderHints(g2d);
        g2d.scale(sourceZoom, sourceZoom);
        g2d.drawImage(image, x, y, null);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(default_width, default_height);
    }

    public static void applyRenderHints(Graphics2D g2d) {
        g2d.setRenderingHints(textRenderHints);
        g2d.setRenderingHints(imageRenderHints);
        g2d.setRenderingHints(renderHints);
    }

        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBorder(null);
        setToolTipText(org.openide.util.NbBundle.getMessage(ImagePanel.class, "ImagePanel.toolTipText")); // NOI18N
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                formMouseWheelMoved(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        if (callingPanel != null && evt.getButton() == MouseEvent.BUTTON1) {
            callingPanel.chooseSource();
        } else if (callingPanel != null && evt.getButton() == MouseEvent.BUTTON3 && file != null && file.exists()) {
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException ex) {
                //Exceptions.printStackTrace(ex);
            }
        } else if (callingPanel == null && evt.getButton() == MouseEvent.BUTTON1 && file != null && file.exists()) {
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException ex) {
                //Exceptions.printStackTrace(ex);
            }
        }
    }//GEN-LAST:event_formMouseClicked

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        startX = (int) (evt.getX() - x * sourceZoom);
        startY = (int) (evt.getY() - y * sourceZoom);
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        x = (int) ((evt.getX() - startX) / sourceZoom);
        y = (int) ((evt.getY() - startY) / sourceZoom);
        repaint();
    }//GEN-LAST:event_formMouseDragged

    private void formMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_formMouseWheelMoved
        int notches = evt.getWheelRotation();
        double zoom = Math.pow(1.1f, -notches);
        double pointX = evt.getX();
        double pointY = evt.getY();
        double zoomafter = sourceZoom * zoom;
        if (zoomafter > 20) {
            zoomafter = 20;
        }
        if (zoomafter < 0.1) {
            zoomafter = 0.1;
        }
        x -= (double) ((pointX / sourceZoom) - (double) (pointX / zoomafter));
        y -= (double) ((pointY / sourceZoom) - (double) (pointY / zoomafter));
        sourceZoom = zoomafter;

        repaint();
    }//GEN-LAST:event_formMouseWheelMoved


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    public void redraw() {
        setMedia(this.file);
    }

    
    public File getFile() {
        return file;
    }

    public Image getImage() {
        return image;
    }

}
