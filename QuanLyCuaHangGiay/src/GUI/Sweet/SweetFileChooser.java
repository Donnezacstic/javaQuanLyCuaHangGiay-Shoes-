package GUI.Sweet;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class SweetFileChooser{
    private static JFileChooser sfc;
    public static String PATH_NHANVIEN = "/src/images/NV/";
    public static  String DIR = System.getProperty("user.dir");
    public static String PATH_SANPHAM = "/src/images/SP/";
    public static String ExcelFileChooser()
    {

        sfc = ConfigureExcelFormat(sfc);
        int choosen = sfc.showDialog(sfc,"Select");
        if (choosen == JFileChooser.APPROVE_OPTION){
            File f = sfc.getSelectedFile();
            if(f.getAbsolutePath().contains(".xlsx"))
            {
            return f.getAbsolutePath();
            }
            else
            {
                return f.getAbsolutePath()+".xlsx";
            }
        }
        else 
            return null;
    }
    public static File ImageFileChooser() throws IOException
    {
       
        sfc = ConfigureImageFormat(sfc);
        int choosen = sfc.showDialog(sfc,"Select");
        if (choosen == JFileChooser.APPROVE_OPTION){
            File picture = sfc.getSelectedFile();
            return picture;
        }
        else 
            return null;
    }
    public static String PDFFileChooser()
    {
  
        sfc = ConfigurePDFFormat(sfc);
        int choosen = sfc.showDialog(sfc,"Select");
        if (choosen == JFileChooser.APPROVE_OPTION){
            File f = sfc.getSelectedFile();
            if(f.getAbsolutePath().contains(".pdf"))
            {
            return f.getAbsolutePath();
            }
            else
            {
                return f.getAbsolutePath()+".pdf";
            }
        }
        else 
            return null;
    }
    private static JFileChooser ConfigureExcelFormat(JFileChooser sfc)
    {
        sfc = new JFileChooser();
        FileNameExtensionFilter excel = new FileNameExtensionFilter("Excel","xlsx");
        sfc.setAcceptAllFileFilterUsed(false);
        sfc.setDialogTitle("Chọn file excel");
        sfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        sfc.setFileFilter(excel);
        sfc.setMultiSelectionEnabled(false);    
        sfc.setCurrentDirectory(new java.io.File(System.getProperty("user.home")+"\\Desktop"));
        sfc.setSelectedFile(new java.io.File("New Sago Document"));
        return sfc;
    }
    private static JFileChooser ConfigureImageFormat(JFileChooser sfc)
    {
        sfc = new JFileChooser();
        FileNameExtensionFilter image = new FileNameExtensionFilter("Image","jpg","png");
        sfc.setAcceptAllFileFilterUsed(false);
        sfc.setDialogTitle("Chọn file ảnh");
        sfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        sfc.setFileFilter(image);
        sfc.setMultiSelectionEnabled(false);    
        sfc.setCurrentDirectory(new java.io.File(System.getProperty("user.home")+"\\Desktop"));
        return sfc;
    }
    
    // Update soon
    private static JFileChooser ConfigurePDFFormat(JFileChooser sfc){
        sfc = new JFileChooser();
        FileNameExtensionFilter image = new FileNameExtensionFilter("PDF","pdf");
        sfc.setAcceptAllFileFilterUsed(false);
        sfc.setDialogTitle("Chọn file pdf");
        sfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        sfc.setFileFilter(image);
        sfc.setMultiSelectionEnabled(false);    
        sfc.setCurrentDirectory(new java.io.File(System.getProperty("user.home")+"\\Desktop"));
        sfc.setSelectedFile(new java.io.File("New Sago Document"));
        return sfc;
    }
    // Update soon
    private static JFileChooser ConfigureWordFormat(JFileChooser sfc){
        return sfc;
    }
    public static String isDirectoryExited(String floderName)
    {
        String dir = System.getProperty("user.dir");
        dir = dir + "/src/images/" + floderName+"/";
        File file = new File(dir);
                    if (!file.exists()){
                        file.mkdir();                    
                    }
                    return dir;
    }
    public static boolean FileExists(String filepath){
        
       return new File(DIR+filepath).exists();
    }
    public static void saveToSource( String dir , File picture) throws IOException{
        dir += "/" + picture.getName();
        Path copied = Paths.get(dir);
        Path originalPath = Paths.get(picture.getAbsolutePath());
        Files.copy(originalPath,copied,StandardCopyOption.COPY_ATTRIBUTES);
    }
    
}
