/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author admin
 */
import static com.sun.org.apache.xalan.internal.lib.ExsltStrings.align;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class RenderTable extends DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, 
            boolean isSelected, boolean hasFocus, int row, int column) {
            if(value instanceof JLabel){
                JLabel lbl = (JLabel)value;
                lbl.setBorder(new JLabelRounded(Color.decode("#20872a"), 13));
                lbl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            return lbl;
        }
            if(value instanceof JButton){
                JButton btn = (JButton)value;
                btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png")));
                btn.setBorder(null);
                return btn;
            }

        setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        return super.getTableCellRendererComponent(table, value, isSelected, 
                hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
    }

    
}
