/*
 * HexEditor.java
 *
 * Created on November 4, 2003, 8:23 AM
 */

package org.owasp.webscarab.ui.swing.editors;

import java.awt.Image;
import javax.swing.ImageIcon;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

/**
 *
 * @author  rdawes
 */
public class ImagePanel extends javax.swing.JPanel implements ByteArrayEditor {
    
    
    private boolean _editable = false;
    private byte[] _data = new byte[0];
    
    /** Creates new form HexEditor */
    public ImagePanel() {
        initComponents();
        setName("Image");
    }
    
    public String[] getContentTypes() {
        return new String[] { "image/.*" };
    }
    
    public void setEditable(boolean editable) {
        _editable = editable;
    }
    
    public byte[] getBytes() {
        return _data;
    }
    
    public void setBytes(byte[] bytes) {
        imageLabel.setIcon(null);
        if (bytes != null && bytes.length > 0) {
            try {
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                Image image = ImageIO.read(bais);
                if (image != null) {
                    imageLabel.setIcon(new ImageIcon(image));
                }
            } catch (Exception e) {
                e.printStackTrace();
                imageLabel.setIcon(null);
            }
        }
    }
    
    public boolean isModified() {
        return false;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        imageScrollPane = new javax.swing.JScrollPane();
        imageLabel = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        imageScrollPane.setViewportView(imageLabel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(imageScrollPane, gridBagConstraints);

    }//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imageLabel;
    private javax.swing.JScrollPane imageScrollPane;
    // End of variables declaration//GEN-END:variables
    
    
    public static void main(String[] args) {
        byte[] content = new byte[0];
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            FileInputStream fis = new FileInputStream("/usr/share/xfce/backdrops/Flower.jpg");
            byte[] buff = new byte[1024];
            int got = 0;
            while ((got = fis.read(buff)) > 0) {
                baos.write(buff, 0, got);
            }
            content = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        
        javax.swing.JFrame top = new javax.swing.JFrame("Image Panel");
        top.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                System.exit(0);
            }
        });
        
        ImagePanel ip = new ImagePanel();
        top.getContentPane().add(ip);
        top.setBounds(100,100,600,400);
        try {
            ip.setBytes(content);
            ip.setEditable(false);
            top.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
