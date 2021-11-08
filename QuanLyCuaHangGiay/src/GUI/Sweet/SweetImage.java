/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Sweet;
import static GUI.Sweet.SweetFileChooser.DIR;
import static GUI.Sweet.SweetFileChooser.isDirectoryExited;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Administrator
 */
public class SweetImage {
    private static BufferedImage Image;
    private static String DEFAULT_IMAGE = "default.jpg";
    public static ImageIcon resizeImage(BufferedImage image, int width, int height) {
    DEFAULT_IMAGE = null;
    if(image == null){
        return null;
    };
    Image = image;
    BufferedImage tempImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    Graphics2D gr = tempImg.createGraphics();
    gr.drawImage(image, 0, 0, width, height, null); // Draw the selected image on the tempImage from co-ordinates (0, 0) to (width, height) of the tempImage.
    gr.dispose();  // Clear resources.
    return new ImageIcon(tempImg);
}
     
public static String saveImage(Icon image,String filename) {
        
        BufferedImage bi = new BufferedImage(
                image.getIconWidth(),
                image.getIconHeight(),
                BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.createGraphics();
        image.paintIcon(null, g, 0, 0);
        g.dispose();
        return saveImage(filename);
    }    
     public static Image readImageFromFilePath(String FilePath, int width, int height) {
        try {
            Image image = ImageIO.read(new File(DIR + FilePath));
            image = image.getScaledInstance(168, 238, Image.SCALE_SMOOTH);
            return image;
        } catch (IOException ex) {
            Logger.getLogger(SweetImage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
public static ImageIcon readImageFromFile(String FilePath,int width,int height){
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(DIR+FilePath));
            return resizeImage(bufferedImage,width,height);
        } catch (IOException ex) {
            Logger.getLogger(SweetImage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     }      
     public static String saveImage(String filename){
            try {
                String path = isDirectoryExited(filename.substring(0, 2));
                if(DEFAULT_IMAGE == null){
                File ImageFile = new File(path+"/"+filename+".jpg");
                ImageIO.write(Image, "jpg", ImageFile);
                return filename+".jpg";
                }
            } catch (IOException ex) {
                Logger.getLogger(SweetImage.class.getName()).log(Level.SEVERE, null, ex);
            }
                return "default.jpg";
     }
//     public static void main(String[] args) throws IOException{
//         File z = new File(DIR+"/src/images/NV/NV9.jpg");
//         BufferedImage buff = ImageIO.read(z);
//         System.out.println(buff.getWidth());
//     }
}
