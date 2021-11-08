/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.THONGKE;

import GUI.*;
import DTO.ChiTietDonHangDTO;
import DTO.DonHangDTO;
import DTO.KhachHangDTO;
import DTO.SanPhamDTO;
import DataThongKe.DataThongKeDonHang;
import static DataThongKe.DataThongKeDonHang.ThongKeDonHangQuyExcel;
import static DataThongKe.DataThongKeDonHang.ThongKeDonHangThangExcel;
import GUI.CHITIET.ChiTietDonHangJPanel;
import GUI.Sweet.SweetAlert;
import GUI.Sweet.SweetComboBox;
import GUI.Sweet.SweetFileChooser;
import GUI.THEMMOI.TaoDonHangJPanel;
import static GUI.TongQuanJPanel.changeColorColumn3;
import static GUI.TongQuanJPanel.changeColorColumn4;
import static GUI.TongQuanJPanel.customTable;
import static GUI.TongQuanJPanel.setJTableColumnsWidth;
import IO.Excel.ThongKeDonHangQuyExcel;
import IO.Excel.ThongKeDonHangThangExcel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import quanlycuahanggiay.DateTime;
import static quanlycuahanggiay.DateTime.TimestampToDateString;

/**
 *
 * @author admin
 */
public class ChiTietThongKeDonHangJPanel extends javax.swing.JPanel {
    Object[] columnNames ={"Tháng","Tổng đơn hàng","Đơn thành công","Đơn hủy"};
    Object[] columnNamesBot = {"Quý","Tổng đơn hàng","Đơn thành công","Đơn hủy"};
    public static KhachHangDTO currentKhachHang;
    public static DonHangDTO currentDonHang;
    public static ChiTietDonHangDTO currentChiTietDonHang;
    public static SanPhamDTO currentSanPham;
    static DefaultTableModel listTableModelThang;
    static DefaultTableModel listTableModelQuy;
    public ArrayList<DonHangDTO> arraySearch;
    public static SweetComboBox cbxNam,cbxThangQuy;
    
    /**
     * Creates new form DonHangJPanel
     */

    public void ShowTable(JTable table,Object[] columnName){
        listTableModelThang = new DefaultTableModel(columnName,0);
        table.setDefaultRenderer(Object.class, new RenderTable());
        fetchAllThang();
    }  
    public void ShowTableBottom(JTable table,Object[] columnName){
        listTableModelQuy = new DefaultTableModel(columnName,0);
        table.setDefaultRenderer(Object.class, new RenderTable());
        fetchAllQuy();
    }      
    public void setComboBox(){
        String[] nam = {"2020","2019","2018"};
        cbxNam = new SweetComboBox("#353746","#ffffff",0,0,130,30,nam);
        pnlNam.add(cbxNam);          
        cbxNam.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie){
                   lblNamThang.setText("Năm "+cbxNam.getSelectedItem().toString());
                   lblNamQuy.setText("Năm "+cbxNam.getSelectedItem().toString());
                   fetchAllThang();
                   fetchAllQuy();
            }
        });
        cbxNam.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent pce) {
                   lblNamThang.setText("Năm "+cbxNam.getSelectedItem().toString());
                   lblNamQuy.setText("Năm "+cbxNam.getSelectedItem().toString());
                   fetchAllThang();
                   fetchAllQuy();
            }
        });
    }
    
    public void setColor(){
        
        for(int j = 0 ; j<tblDonHang.getRowCount();j++){
            for(int i=4;i<tblDonHang.getColumnCount();i++){
                
                tblDonHang.getColumnModel().getColumn(i).setCellRenderer(new changeColorOfCellTable(tblDonHang,j,i));

            }
            
        }
        
    }
    public ChiTietThongKeDonHangJPanel() {
        initComponents();
        
        setComboBox();
        customTable(tblDonHang,scpDonHang);
        customTable(tblDonHangQuy,scpDonHang1);
        ShowTable(tblDonHang,columnNames);
        setJTableColumnsWidth(tblDonHang,tblDonHang.getWidth(),18,28,18,23,13);  
        ShowTableBottom(tblDonHangQuy,columnNamesBot);
        setJTableColumnsWidth(tblDonHangQuy,tblDonHangQuy.getWidth(),18,28,18,23,13);          
        setColor();
        pnlQuayVe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));   
            
        
        
    }
    public static void fetchAllQuy(){
        listTableModelQuy.setRowCount(0);
        ThongKeDonHangQuyExcel(cbxNam.getSelectedItem().toString()).forEach(ThongKeQuy->{
            Vector row = new Vector();
                row.add(ThongKeQuy[1]);
                row.add(ThongKeQuy[2]);
                row.add(ThongKeQuy[3]);
                row.add(ThongKeQuy[4]);
                listTableModelQuy.addRow(row);
        });
        tblDonHangQuy.setModel(listTableModelQuy);
    }    
    public static void fetchAllThang(){
        
        listTableModelThang.setRowCount(0);
            ThongKeDonHangThangExcel(cbxNam.getSelectedItem().toString()).forEach(ThongKeThang->{
            Vector row = new Vector();
                row.add(ThongKeThang[1]);
                row.add(ThongKeThang[2]);
                row.add(ThongKeThang[3]);
                row.add(ThongKeThang[4]);
                listTableModelThang.addRow(row);
            });
            tblDonHang.setModel(listTableModelThang);
    }
    public static String getTrangThai(String MaTrangThai){
        return MaTrangThai.equals("1") ? "Thành Công" : "Hủy";
    }
    public String getGiaTriTrangThai(String currentTrangThai){
        return currentTrangThai.equals("Hủy") ? "0" : "1";
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTblDonHangChuaXacNhan = new GUI.Rounded();
        scpDonHang = new javax.swing.JScrollPane();
        tblDonHang = new javax.swing.JTable(){
            public boolean isCellEditable ( int row, int col)
            {
                return false;
            }

        };
        lblTrangThai = new javax.swing.JLabel();
        pnlNam = new javax.swing.JPanel();
        pnlQuayVe = new GUI.Rounded();
        lblQuayVe = new javax.swing.JLabel();
        pnlTblDonHangChuaXacNhan1 = new GUI.Rounded();
        scpDonHang1 = new javax.swing.JScrollPane();
        tblDonHangQuy = new javax.swing.JTable(){
            public boolean isCellEditable ( int row, int col)
            {
                return false;
            }

        };
        lblNamQuy = new javax.swing.JLabel();
        lblNamThang = new javax.swing.JLabel();
        pnlXuatExcelThang = new GUI.Rounded();
        lblXuatExcelThang = new javax.swing.JLabel();
        pnlXuatExcelQuy = new GUI.Rounded();
        lblXuatExcelQuy = new javax.swing.JLabel();

        setBackground(new java.awt.Color(45, 47, 62));
        setPreferredSize(new java.awt.Dimension(920, 700));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlTblDonHangChuaXacNhan.setBackground(new java.awt.Color(45, 47, 62));
        pnlTblDonHangChuaXacNhan.setForeground(new java.awt.Color(53, 55, 70));

        scpDonHang.setBackground(new java.awt.Color(53, 55, 70));

        tblDonHang.setAutoCreateRowSorter(true);
        tblDonHang.setBackground(new java.awt.Color(53, 55, 70));
        tblDonHang.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tblDonHang.setForeground(new java.awt.Color(255, 255, 255));
        tblDonHang.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblDonHang.setFocusable(false);
        tblDonHang.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblDonHang.setRowHeight(25);
        tblDonHang.setSelectionBackground(new java.awt.Color(72, 74, 89));
        tblDonHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDonHangMouseClicked(evt);
            }
        });
        scpDonHang.setViewportView(tblDonHang);

        javax.swing.GroupLayout pnlTblDonHangChuaXacNhanLayout = new javax.swing.GroupLayout(pnlTblDonHangChuaXacNhan);
        pnlTblDonHangChuaXacNhan.setLayout(pnlTblDonHangChuaXacNhanLayout);
        pnlTblDonHangChuaXacNhanLayout.setHorizontalGroup(
            pnlTblDonHangChuaXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTblDonHangChuaXacNhanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scpDonHang)
                .addContainerGap())
        );
        pnlTblDonHangChuaXacNhanLayout.setVerticalGroup(
            pnlTblDonHangChuaXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTblDonHangChuaXacNhanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scpDonHang, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(pnlTblDonHangChuaXacNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 88, 890, 390));

        lblTrangThai.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTrangThai.setForeground(new java.awt.Color(255, 255, 255));
        lblTrangThai.setText("Năm:");
        add(lblTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 10, -1, 30));

        pnlNam.setBackground(new java.awt.Color(53, 55, 70));
        pnlNam.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pnlNamLayout = new javax.swing.GroupLayout(pnlNam);
        pnlNam.setLayout(pnlNamLayout);
        pnlNamLayout.setHorizontalGroup(
            pnlNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );
        pnlNamLayout.setVerticalGroup(
            pnlNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        add(pnlNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 10, 130, 30));

        pnlQuayVe.setBackground(new java.awt.Color(45, 47, 62));
        pnlQuayVe.setForeground(new java.awt.Color(238, 38, 63));
        pnlQuayVe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlQuayVeMouseClicked(evt);
            }
        });

        lblQuayVe.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblQuayVe.setForeground(new java.awt.Color(255, 255, 255));
        lblQuayVe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back-icon.png"))); // NOI18N
        lblQuayVe.setText("  Quay về");

        javax.swing.GroupLayout pnlQuayVeLayout = new javax.swing.GroupLayout(pnlQuayVe);
        pnlQuayVe.setLayout(pnlQuayVeLayout);
        pnlQuayVeLayout.setHorizontalGroup(
            pnlQuayVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuayVeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblQuayVe, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlQuayVeLayout.setVerticalGroup(
            pnlQuayVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblQuayVe, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        add(pnlQuayVe, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 18, -1, -1));

        pnlTblDonHangChuaXacNhan1.setBackground(new java.awt.Color(45, 47, 62));
        pnlTblDonHangChuaXacNhan1.setForeground(new java.awt.Color(53, 55, 70));

        scpDonHang1.setBackground(new java.awt.Color(53, 55, 70));

        tblDonHangQuy.setAutoCreateRowSorter(true);
        tblDonHangQuy.setBackground(new java.awt.Color(53, 55, 70));
        tblDonHangQuy.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tblDonHangQuy.setForeground(new java.awt.Color(255, 255, 255));
        tblDonHangQuy.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblDonHangQuy.setFocusable(false);
        tblDonHangQuy.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblDonHangQuy.setRowHeight(25);
        tblDonHangQuy.setSelectionBackground(new java.awt.Color(72, 74, 89));
        tblDonHangQuy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDonHangQuyMouseClicked(evt);
            }
        });
        scpDonHang1.setViewportView(tblDonHangQuy);

        javax.swing.GroupLayout pnlTblDonHangChuaXacNhan1Layout = new javax.swing.GroupLayout(pnlTblDonHangChuaXacNhan1);
        pnlTblDonHangChuaXacNhan1.setLayout(pnlTblDonHangChuaXacNhan1Layout);
        pnlTblDonHangChuaXacNhan1Layout.setHorizontalGroup(
            pnlTblDonHangChuaXacNhan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTblDonHangChuaXacNhan1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scpDonHang1, javax.swing.GroupLayout.DEFAULT_SIZE, 866, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTblDonHangChuaXacNhan1Layout.setVerticalGroup(
            pnlTblDonHangChuaXacNhan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTblDonHangChuaXacNhan1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scpDonHang1, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(pnlTblDonHangChuaXacNhan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, -1, 180));

        lblNamQuy.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblNamQuy.setForeground(new java.awt.Color(238, 38, 63));
        lblNamQuy.setText("Năm 2020");
        add(lblNamQuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, -1, -1));

        lblNamThang.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblNamThang.setForeground(new java.awt.Color(238, 38, 63));
        lblNamThang.setText("Năm 2020");
        add(lblNamThang, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        pnlXuatExcelThang.setBackground(new java.awt.Color(45, 47, 62));
        pnlXuatExcelThang.setForeground(new java.awt.Color(238, 38, 63));
        pnlXuatExcelThang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlXuatExcelThangMouseClicked(evt);
            }
        });

        lblXuatExcelThang.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblXuatExcelThang.setForeground(new java.awt.Color(255, 255, 255));
        lblXuatExcelThang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblXuatExcelThang.setText("Xuất Excel");
        lblXuatExcelThang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pnlXuatExcelThangLayout = new javax.swing.GroupLayout(pnlXuatExcelThang);
        pnlXuatExcelThang.setLayout(pnlXuatExcelThangLayout);
        pnlXuatExcelThangLayout.setHorizontalGroup(
            pnlXuatExcelThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlXuatExcelThangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblXuatExcelThang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlXuatExcelThangLayout.setVerticalGroup(
            pnlXuatExcelThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblXuatExcelThang, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        add(pnlXuatExcelThang, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 50, -1, 30));

        pnlXuatExcelQuy.setBackground(new java.awt.Color(45, 47, 62));
        pnlXuatExcelQuy.setForeground(new java.awt.Color(238, 38, 63));
        pnlXuatExcelQuy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlXuatExcelQuyMouseClicked(evt);
            }
        });

        lblXuatExcelQuy.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblXuatExcelQuy.setForeground(new java.awt.Color(255, 255, 255));
        lblXuatExcelQuy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblXuatExcelQuy.setText("Xuất Excel");
        lblXuatExcelQuy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pnlXuatExcelQuyLayout = new javax.swing.GroupLayout(pnlXuatExcelQuy);
        pnlXuatExcelQuy.setLayout(pnlXuatExcelQuyLayout);
        pnlXuatExcelQuyLayout.setHorizontalGroup(
            pnlXuatExcelQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlXuatExcelQuyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblXuatExcelQuy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlXuatExcelQuyLayout.setVerticalGroup(
            pnlXuatExcelQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblXuatExcelQuy, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        add(pnlXuatExcelQuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 490, -1, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void tblDonHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDonHangMouseClicked
        int column = tblDonHang.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/tblDonHang.getRowHeight();
        if(row < tblDonHang.getRowCount() && row >= 0 && column < tblDonHang.getColumnCount() && column >= 0){
            Object value = tblDonHang.getValueAt(row, column);
            if(value instanceof JLabel){
                JLabel label = (JLabel) value;
                for(int i=0;i<tblDonHang.getColumnCount()-2;i++){
                    System.out.println(tblDonHang.getModel().getValueAt(row,i));
                }
            }
            else if(value instanceof JButton){
                JButton label = (JButton) value;
                for(int i=0;i<tblDonHang.getColumnCount()-2;i++){
                    System.out.println(tblDonHang.getModel().getValueAt(row,i));                
                }
            }
            else {
                    currentDonHang = quanlycuahanggiay.quanlycuahanggiay.DonHangBUS.getByMaDonHang((String) tblDonHang.getModel().getValueAt(row,0));
                    currentKhachHang = quanlycuahanggiay.quanlycuahanggiay.KhachHangBUS.getByMaKhachHang(currentDonHang.getMaKhachHang());
                    currentChiTietDonHang = quanlycuahanggiay.quanlycuahanggiay.ChiTietDonHangBUS.getByMaDonHang(currentDonHang.getMaDonHang());    
                    this.removeAll();
                    this.setLayout(new BorderLayout());
                    this.add(new ChiTietDonHangJPanel());
                    this.validate();
                    this.repaint();
                }
            

        }
    }//GEN-LAST:event_tblDonHangMouseClicked

    private void pnlQuayVeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlQuayVeMouseClicked
        if (SwingUtilities.isLeftMouseButton(evt))
        this.removeAll();
        this.setLayout(new BorderLayout());
        this.add(new ThongKeDonHangJPanel());
        this.validate();
        this.repaint();        // TODO add your handling code here:
    }//GEN-LAST:event_pnlQuayVeMouseClicked

    private void tblDonHangQuyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDonHangQuyMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDonHangQuyMouseClicked

    private void pnlXuatExcelThangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlXuatExcelThangMouseClicked
        try {
            String path = SweetFileChooser.ExcelFileChooser();
            if(path==null){
                SweetAlert.showSweetAlert(new JFrame(), "Lỗi", "Vui lòng chọn file phù hợp", 1);
                return;
            }
        ThongKeDonHangThangExcel.ExportExcelFile(path, DataThongKeDonHang.ThongKeDonHangThangExcel(cbxNam.getSelectedItem().toString()));
            SweetAlert.showSweetAlert(new JFrame(), "Hoàn tất", "Xuất dữ liệu thành công", 0);
        } catch (IOException ex) {
            Logger.getLogger(ThongKeDonHangJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_pnlXuatExcelThangMouseClicked

    private void pnlXuatExcelQuyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlXuatExcelQuyMouseClicked
        try {
            String path = SweetFileChooser.ExcelFileChooser();
            if(path==null){
                SweetAlert.showSweetAlert(new JFrame(), "Lỗi", "Vui lòng chọn file phù hợp", 1);
                return;
            }
            ThongKeDonHangQuyExcel.ExportExcelFile(path, DataThongKeDonHang.ThongKeDonHangQuyExcel(cbxNam.getSelectedItem().toString()));
            SweetAlert.showSweetAlert(new JFrame(), "Hoàn tất", "Xuất dữ liệu thành công", 0);
        } catch (IOException ex) {
            Logger.getLogger(ThongKeDonHangJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_pnlXuatExcelQuyMouseClicked



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblNamQuy;
    private javax.swing.JLabel lblNamThang;
    private javax.swing.JLabel lblQuayVe;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JLabel lblXuatExcelQuy;
    private javax.swing.JLabel lblXuatExcelThang;
    private javax.swing.JPanel pnlNam;
    private GUI.Rounded pnlQuayVe;
    private GUI.Rounded pnlTblDonHangChuaXacNhan;
    private GUI.Rounded pnlTblDonHangChuaXacNhan1;
    private GUI.Rounded pnlXuatExcelQuy;
    private GUI.Rounded pnlXuatExcelThang;
    private javax.swing.JScrollPane scpDonHang;
    private javax.swing.JScrollPane scpDonHang1;
    private static javax.swing.JTable tblDonHang;
    private static javax.swing.JTable tblDonHangQuy;
    // End of variables declaration//GEN-END:variables
}
