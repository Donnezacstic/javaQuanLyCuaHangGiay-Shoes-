/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author admin
 */
public class changeColorOfCellTable extends DefaultTableCellRenderer {
  String value;
  public changeColorOfCellTable(JTable table,int row,int column){
      value = table.getValueAt(row,column).toString();
  }    
    
  @Override
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

    //Cells are by default rendered as a JLabel.
    JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
    if(value.equals("Thành công")){
        l.setForeground(Color.white);
    }
    else if(value.equals("Đã thanh toán") || value.equals("Xác nhận")){
        l.setForeground(Color.decode("#20872a"));
    }
    else if(value.equals("Chưa thanh toán") || value.equals("Hủy")){
        l.setForeground(Color.decode("#74343c"));
    }
    else if(value.equals("Sửa")){
        l.setText("");
        l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit-cell.png")));
    }
    else if(value.equals("Thêm sản phẩm")){
        l.setText("");
        l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add-product.png")));
    }
    else if(value.equals("Xóa sản phẩm đã thêm")){
        l.setText("");
        l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/remove-product.png")));
    }
    else if(value.equals("Xóa")){        
        l.setText("");
        l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png")));
    }
    else if(value.equals(0)){
        l.setForeground(Color.decode("#74343c"));
    }
    else{
        l.setForeground(Color.white);
    }
    setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

  //Return the JLabel which renders the cell.
  return l;

}
}
