/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DTO.MauDTO;
import DTO.SanPhamDTO;
import DTO.SizeDTO;
import DTO.ThuongHieuDTO;
import static DataThongKe.DataThongKeSanPham.SanPhamDaBan;
import static DataThongKe.DataThongKeSanPham.SanPhamDaBanThang;
import GUI.CHITIET.ChiTietDonHangJPanel;
import GUI.CHITIET.ChiTietSanPhamJPanel;
import GUI.POPUP.SoLuongVaDonGiaJFrame;
import GUI.Sweet.SweetComboBox;
import GUI.THEMMOI.TaoSanPhamJPanel;
import static GUI.TongQuanJPanel.customTable;
import static GUI.TongQuanJPanel.setJTableColumnsWidth;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import static quanlycuahanggiay.Currency.changeCurrency;

/**
 *
 * @author admin
 */
public class SanPhamJPanel extends javax.swing.JPanel {
    Object[] columnNames ={"Mã sản phẩm","Tên sản phẩm","Đơn giá","Số lượng","Đã bán","Đã bán trong tháng"};
    SweetComboBox cbxMau, cbxTrangThai, cbxSize, cbxThuongHieu;
    public static DefaultTableModel listTableModel;
    public static SanPhamDTO currentSanPham;
    public ArrayList<SanPhamDTO> arraySearch;
    /**
     * Creates new form SanPhamJPanel
     */
    public void setComboBox(){
        String[] trangthai = {"Tất cả","Còn hàng","Hết hàng"};
        cbxTrangThai = new SweetComboBox("#353746","#ffffff",0,0,170,30,trangthai);
        pnlTrangThai.add(cbxTrangThai);
        String[] mau = quanlycuahanggiay.quanlycuahanggiay.MauBUS.getTenStringArray();
        cbxMau = new SweetComboBox("#353746","#C0C0C0",0,0,150,30,mau);
        pnlMau.add(cbxMau);
        String[] size = quanlycuahanggiay.quanlycuahanggiay.SizeBUS.getTenStringArray();
        cbxSize = new SweetComboBox("#353746","#C0C0C0",0,0,150,30,size);
        pnlSize.add(cbxSize);
        String[] thuonghieu = quanlycuahanggiay.quanlycuahanggiay.ThuongHieuBUS.getTenStringArray();
        cbxThuongHieu = new SweetComboBox("#353746","#C0C0C0",0,0,150,30,thuonghieu);
        pnlThuongHieu.add(cbxThuongHieu);          
        cbxTrangThai.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie){
                if(ie.getStateChange() == ItemEvent.SELECTED)
                getAdvancedSearch();
            }
        });
        cbxMau.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie){
                if(ie.getStateChange() == ItemEvent.SELECTED)
                getAdvancedSearch();
            }
        });
        cbxSize.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie){
                if(ie.getStateChange() == ItemEvent.SELECTED)
                getAdvancedSearch();
            }
        });
        cbxThuongHieu.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie){
                if(ie.getStateChange() == ItemEvent.SELECTED)
                getAdvancedSearch();
            }
        });
    }
    
    public void ShowTable(JTable table,Object[] columnName){
        listTableModel = new DefaultTableModel(columnNames,0);
        table.setDefaultRenderer(Object.class, new RenderTable());
        fetchAll();  
    }
    
    
    public static void fetchAll(){
        listTableModel.setRowCount(0);
        quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.SanPhamList.forEach(SanPham ->{
            Vector row = new Vector();
            row.add(SanPham.getMaSanPham());
            row.add(SanPham.getTenSanPham());
            row.add(SanPham.getDonGia());
            row.add(SanPham.getSoLuong());
            row.add(changeCurrency(Integer.toString(SanPhamDaBan(SanPham.getMaSanPham())))); // GET TU DON HANG
            row.add(changeCurrency(Integer.toString(SanPhamDaBanThang(SanPham.getMaSanPham())[0]))); // GET TU DON HANG
            listTableModel.addRow(row);
        });
        tblSanPham.setModel(listTableModel);
    }
    
    public void setColor(){
        
        for(int j = 0 ; j<tblSanPham.getRowCount();j++){
            for(int i=3;i<tblSanPham.getColumnCount();i++){ 
                tblSanPham.getColumnModel().getColumn(i).setCellRenderer(new changeColorOfCellTable(tblSanPham,j,i));
            }
            
        }
        
    }    
    public SanPhamJPanel() {
        initComponents();
        customTable(tblSanPham,scpSanPham);
        ShowTable(tblSanPham,columnNames);
        setJTableColumnsWidth(tblSanPham,890,15,25,20,10,10,20);
        setColor();
        setComboBox();
        pnlTaoSanPham.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    }
    
    private void getAdvancedSearch(){
        HashMap<String, String> advancedSearch = new HashMap<>();
        advancedSearch.put("MaSanPham",txtTimKiemMaSanPham.getText());
        advancedSearch.put("TenSanPham",txtTimKiemTheoTenSanPham.getText());
        advancedSearch.put("TrangThai","");
        advancedSearch.put("Mau","");
        advancedSearch.put("Size","");
        advancedSearch.put("ThuongHieu", "");
        if(!cbxTrangThai.getSelectedItem().equals("Tất cả"))
            advancedSearch.replace("TrangThai", cbxTrangThai.getSelectedItem().toString());
        if(!cbxMau.getSelectedItem().equals("Màu"))
            advancedSearch.replace("Mau", quanlycuahanggiay.quanlycuahanggiay.MauBUS.getMaMau(cbxMau.getSelectedItem().toString()));
        if(!cbxSize.getSelectedItem().equals("Size"))
            advancedSearch.replace("Size", quanlycuahanggiay.quanlycuahanggiay.SizeBUS.getMaSize(cbxSize.getSelectedItem().toString()));
        if(!cbxThuongHieu.getSelectedItem().equals("Thương hiệu"))
            advancedSearch.replace("ThuongHieu", quanlycuahanggiay.quanlycuahanggiay.ThuongHieuBUS.getMaThuongHieu(cbxThuongHieu.getSelectedItem().toString()));
        arraySearch = quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.advancedSearch(advancedSearch);
        search();
    }
    
    public void search(){
        listTableModel.setRowCount(0);
        arraySearch.forEach(SanPham ->{
            Vector row = new Vector();
            row.add(SanPham.getMaSanPham());
            row.add(SanPham.getTenSanPham());
            row.add(SanPham.getDonGia());
            row.add(SanPham.getSoLuong());
            row.add(changeCurrency(Integer.toString(SanPhamDaBan(SanPham.getMaSanPham()))));//GET DON HANG
            row.add(changeCurrency(Integer.toString(SanPhamDaBanThang(SanPham.getMaSanPham())[0])));//GET DON HANG
            listTableModel.addRow(row);
        });
        tblSanPham.setModel(listTableModel);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTblSanPham = new GUI.Rounded();
        scpSanPham = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable(){
            public boolean isCellEditable ( int row, int col)
            {
                return false;
            }

        };
        pnlTaoSanPham = new GUI.Rounded();
        lblTaoSanPham = new javax.swing.JLabel();
        lblTimKiemTheoTenSanPham = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pnlTimKiemTheoTenSanPham = new GUI.Rounded();
        txtTimKiemTheoTenSanPham = new javax.swing.JTextField();
        pnlTimKiemMaSanPham = new GUI.Rounded();
        txtTimKiemMaSanPham = new javax.swing.JTextField();
        pnlMau = new javax.swing.JPanel();
        pnlSize = new javax.swing.JPanel();
        pnlThuongHieu = new javax.swing.JPanel();
        lblTrangThai = new javax.swing.JLabel();
        pnlTrangThai = new javax.swing.JPanel();

        setBackground(new java.awt.Color(45, 47, 62));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlTblSanPham.setBackground(new java.awt.Color(45, 47, 62));
        pnlTblSanPham.setForeground(new java.awt.Color(53, 55, 70));

        scpSanPham.setBackground(new java.awt.Color(53, 55, 70));
        scpSanPham.setForeground(new java.awt.Color(53, 55, 70));

        tblSanPham.setAutoCreateRowSorter(true);
        tblSanPham.setBackground(new java.awt.Color(53, 55, 70));
        tblSanPham.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tblSanPham.setForeground(new java.awt.Color(255, 255, 255));
        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblSanPham.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblSanPham.setFocusable(false);
        tblSanPham.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblSanPham.setRowHeight(25);
        tblSanPham.setSelectionBackground(new java.awt.Color(72, 74, 89));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        scpSanPham.setViewportView(tblSanPham);

        javax.swing.GroupLayout pnlTblSanPhamLayout = new javax.swing.GroupLayout(pnlTblSanPham);
        pnlTblSanPham.setLayout(pnlTblSanPhamLayout);
        pnlTblSanPhamLayout.setHorizontalGroup(
            pnlTblSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTblSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scpSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTblSanPhamLayout.setVerticalGroup(
            pnlTblSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTblSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scpSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(pnlTblSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 185, -1, 560));

        pnlTaoSanPham.setBackground(new java.awt.Color(45, 47, 62));
        pnlTaoSanPham.setForeground(new java.awt.Color(210, 57, 44));
        pnlTaoSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlTaoSanPhamMouseClicked(evt);
            }
        });

        lblTaoSanPham.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblTaoSanPham.setForeground(new java.awt.Color(255, 255, 255));
        lblTaoSanPham.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTaoSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        lblTaoSanPham.setText("Tạo sản phẩm");

        javax.swing.GroupLayout pnlTaoSanPhamLayout = new javax.swing.GroupLayout(pnlTaoSanPham);
        pnlTaoSanPham.setLayout(pnlTaoSanPhamLayout);
        pnlTaoSanPhamLayout.setHorizontalGroup(
            pnlTaoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTaoSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTaoSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTaoSanPhamLayout.setVerticalGroup(
            pnlTaoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTaoSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTaoSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(pnlTaoSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 130, -1, -1));

        lblTimKiemTheoTenSanPham.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        lblTimKiemTheoTenSanPham.setForeground(new java.awt.Color(255, 255, 255));
        lblTimKiemTheoTenSanPham.setText("Tên sản phẩm:");
        add(lblTimKiemTheoTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 18, -1, 30));

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Mã sản phẩm:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 30));

        pnlTimKiemTheoTenSanPham.setBackground(new java.awt.Color(45, 47, 62));
        pnlTimKiemTheoTenSanPham.setForeground(new java.awt.Color(62, 64, 78));
        pnlTimKiemTheoTenSanPham.setFocusable(false);

        txtTimKiemTheoTenSanPham.setBackground(new java.awt.Color(62, 64, 78));
        txtTimKiemTheoTenSanPham.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTimKiemTheoTenSanPham.setForeground(new java.awt.Color(192, 192, 192));
        txtTimKiemTheoTenSanPham.setBorder(null);
        txtTimKiemTheoTenSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemTheoTenSanPhamKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlTimKiemTheoTenSanPhamLayout = new javax.swing.GroupLayout(pnlTimKiemTheoTenSanPham);
        pnlTimKiemTheoTenSanPham.setLayout(pnlTimKiemTheoTenSanPhamLayout);
        pnlTimKiemTheoTenSanPhamLayout.setHorizontalGroup(
            pnlTimKiemTheoTenSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemTheoTenSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiemTheoTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTimKiemTheoTenSanPhamLayout.setVerticalGroup(
            pnlTimKiemTheoTenSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemTheoTenSanPhamLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimKiemTheoTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(pnlTimKiemTheoTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, -1, -1));

        pnlTimKiemMaSanPham.setBackground(new java.awt.Color(45, 47, 62));
        pnlTimKiemMaSanPham.setForeground(new java.awt.Color(62, 64, 78));
        pnlTimKiemMaSanPham.setFocusable(false);

        txtTimKiemMaSanPham.setBackground(new java.awt.Color(62, 64, 78));
        txtTimKiemMaSanPham.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTimKiemMaSanPham.setForeground(new java.awt.Color(192, 192, 192));
        txtTimKiemMaSanPham.setBorder(null);
        txtTimKiemMaSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemMaSanPhamKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlTimKiemMaSanPhamLayout = new javax.swing.GroupLayout(pnlTimKiemMaSanPham);
        pnlTimKiemMaSanPham.setLayout(pnlTimKiemMaSanPhamLayout);
        pnlTimKiemMaSanPhamLayout.setHorizontalGroup(
            pnlTimKiemMaSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemMaSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiemMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTimKiemMaSanPhamLayout.setVerticalGroup(
            pnlTimKiemMaSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemMaSanPhamLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimKiemMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(pnlTimKiemMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, -1, -1));

        pnlMau.setBackground(new java.awt.Color(53, 55, 70));
        pnlMau.setPreferredSize(new java.awt.Dimension(170, 30));

        javax.swing.GroupLayout pnlMauLayout = new javax.swing.GroupLayout(pnlMau);
        pnlMau.setLayout(pnlMauLayout);
        pnlMauLayout.setHorizontalGroup(
            pnlMauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        pnlMauLayout.setVerticalGroup(
            pnlMauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        add(pnlMau, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 150, 30));

        pnlSize.setBackground(new java.awt.Color(53, 55, 70));
        pnlSize.setPreferredSize(new java.awt.Dimension(150, 30));

        javax.swing.GroupLayout pnlSizeLayout = new javax.swing.GroupLayout(pnlSize);
        pnlSize.setLayout(pnlSizeLayout);
        pnlSizeLayout.setHorizontalGroup(
            pnlSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        pnlSizeLayout.setVerticalGroup(
            pnlSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        add(pnlSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 140, 150, -1));

        pnlThuongHieu.setBackground(new java.awt.Color(53, 55, 70));
        pnlThuongHieu.setPreferredSize(new java.awt.Dimension(150, 30));

        javax.swing.GroupLayout pnlThuongHieuLayout = new javax.swing.GroupLayout(pnlThuongHieu);
        pnlThuongHieu.setLayout(pnlThuongHieuLayout);
        pnlThuongHieuLayout.setHorizontalGroup(
            pnlThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        pnlThuongHieuLayout.setVerticalGroup(
            pnlThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        add(pnlThuongHieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 140, 150, -1));

        lblTrangThai.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTrangThai.setForeground(new java.awt.Color(255, 255, 255));
        lblTrangThai.setText("Trạng thái:");
        add(lblTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 20, -1, 30));

        pnlTrangThai.setBackground(new java.awt.Color(53, 55, 70));

        javax.swing.GroupLayout pnlTrangThaiLayout = new javax.swing.GroupLayout(pnlTrangThai);
        pnlTrangThai.setLayout(pnlTrangThaiLayout);
        pnlTrangThaiLayout.setHorizontalGroup(
            pnlTrangThaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );
        pnlTrangThaiLayout.setVerticalGroup(
            pnlTrangThaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        add(pnlTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 20, 170, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int column = tblSanPham.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/tblSanPham.getRowHeight();

        if(row < tblSanPham.getRowCount() && row >= 0 && column < tblSanPham.getColumnCount() && column >= 0){
                    this.removeAll();
                    this.setLayout(new BorderLayout());
                    currentSanPham = quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.getByMaSanPham((String) tblSanPham.getModel().getValueAt(row, 0));
                    this.add(new ChiTietSanPhamJPanel(currentSanPham));
                    this.validate();
                    this.repaint();                
            }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void pnlTaoSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTaoSanPhamMouseClicked
        this.removeAll();
        this.setLayout(new BorderLayout());
        this.add(new TaoSanPhamJPanel());
        this.validate();
        this.repaint();   
    }//GEN-LAST:event_pnlTaoSanPhamMouseClicked

    private void txtTimKiemTheoTenSanPhamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemTheoTenSanPhamKeyReleased
        getAdvancedSearch();
    }//GEN-LAST:event_txtTimKiemTheoTenSanPhamKeyReleased

    private void txtTimKiemMaSanPhamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemMaSanPhamKeyReleased
        getAdvancedSearch();
    }//GEN-LAST:event_txtTimKiemMaSanPhamKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblTaoSanPham;
    private javax.swing.JLabel lblTimKiemTheoTenSanPham;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JPanel pnlMau;
    private javax.swing.JPanel pnlSize;
    private GUI.Rounded pnlTaoSanPham;
    private GUI.Rounded pnlTblSanPham;
    private javax.swing.JPanel pnlThuongHieu;
    private GUI.Rounded pnlTimKiemMaSanPham;
    private GUI.Rounded pnlTimKiemTheoTenSanPham;
    private javax.swing.JPanel pnlTrangThai;
    private javax.swing.JScrollPane scpSanPham;
    private static javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtTimKiemMaSanPham;
    private javax.swing.JTextField txtTimKiemTheoTenSanPham;
    // End of variables declaration//GEN-END:variables
}
