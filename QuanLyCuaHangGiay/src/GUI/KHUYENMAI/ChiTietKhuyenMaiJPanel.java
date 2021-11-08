/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.KHUYENMAI;

import GUI.KhuyenMaiJPanel;
import GUI.RenderTable;
import GUI.Sweet.SweetComboBox;
import GUI.THEMMOI.TaoMaKhuyenMaiJPanel;
import static GUI.TongQuanJPanel.customTable;
import static GUI.TongQuanJPanel.setJTableColumnsWidth;
import DTO.ChiTietKhuyenMaiDTO;
import DTO.ChuongTrinhKhuyenMaiDTO;
import DTO.DonHangDTO;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static quanlycuahanggiay.DateTime.TimestampToDateString;

/**
 *
 * @author admin
 */
public class ChiTietKhuyenMaiJPanel extends javax.swing.JPanel {

    /**
     * Creates new form ChiTietKhuyenMaiJPanel
     */
    public static DefaultTableModel listTableModel;
    Object[] columnNames ={"Mã chương trình KM","Mã chi tiết KM","Mã khuyến mãi","Số lượng","Còn lại","Ngày tạo","Ngày bắt đầu"};
    
    SweetComboBox cbxTrangThai;
    public ArrayList<ChiTietKhuyenMaiDTO> arraySearch;
    public static ChiTietKhuyenMaiDTO currentChiTietKhuyenMai;
    public static ChuongTrinhKhuyenMaiDTO currentChuongTrinhKhuyenMai;
    public static DonHangDTO currentDonHang;
   /**
     * Creates new form KhuyenMaiJPanel
     */
    public void setComboBox(){
         String[] trangthai = {"Tất cả","Còn","Hết"};
        cbxTrangThai = new SweetComboBox("#353746","#C0C0C0",0,0,120,30,trangthai);
        pnlTrangThai.add(cbxTrangThai);
        cbxTrangThai.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie){
              getAdvancedSearch();  
            }
            }); 
    }
    
    public void ShowTable(JTable table,Object[] columnName){
        listTableModel = new DefaultTableModel(columnNames, 0);
        table.setDefaultRenderer(Object.class, new RenderTable());
        fetchAll();
    }
    
    public static void fetchAll(){
        listTableModel.setRowCount(0);
        
        quanlycuahanggiay.quanlycuahanggiay.ChiTietKhuyenMaiBUS.ChiTietKhuyenMaiList.forEach(ChiTietKhuyenMai ->{
            Vector row = new Vector();
            row.add(ChiTietKhuyenMai.getMaChuongTrinhKhuyenMai());
            row.add(ChiTietKhuyenMai.getMaChiTietKhuyenMai());
            row.add(ChiTietKhuyenMai.getMaCode());
            row.add(ChiTietKhuyenMai.getSoLuong());
            int conlai = Integer.parseInt(ChiTietKhuyenMai.getSoLuong()) - Integer.parseInt(ChiTietKhuyenMai.getSoLuongDaDung());
            row.add(conlai);
            row.add(TimestampToDateString(quanlycuahanggiay.quanlycuahanggiay.ChuongTrinhKhuyenMaiBUS.getNgayTaobyMaChuongTrinhKhuyenMai(ChiTietKhuyenMai.getMaChuongTrinhKhuyenMai()),1));
            row.add(TimestampToDateString(ChiTietKhuyenMai.getThoiGianBatDauSuDung(),1));
            listTableModel.addRow(row);
            conlai = 0;
        });
        tblChiTietKhuyenMai.setModel(listTableModel);
    }
    
    public ChiTietKhuyenMaiJPanel() {
        initComponents();
        customTable(tblChiTietKhuyenMai,scpDonHang);
        ShowTable(tblChiTietKhuyenMai,columnNames);
        tblChiTietKhuyenMai.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 12));
        pnlTaoChiTiet.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnlQuayVe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setComboBox();
    }
    private void getAdvancedSearch(){
        HashMap<String,String> advancedSearch = new HashMap<>();
        advancedSearch.put("MaChuongTrinhKhuyenMai",txtTimKiemMaChuongTrinh.getText());
        advancedSearch.put("MaChiTiet", txtTimKiemMaChiTiet.getText());
        advancedSearch.put("TrangThai","");
        if(!cbxTrangThai.getSelectedItem().equals("Tất cả"))
            advancedSearch.replace("TrangThai", cbxTrangThai.getSelectedItem().toString());
        arraySearch = quanlycuahanggiay.quanlycuahanggiay.ChiTietKhuyenMaiBUS.advancedSearch(advancedSearch);
        search();
    }
    
    public void search(){
        listTableModel.setRowCount(0);
        arraySearch.forEach(ChiTietKhuyenMai ->{
            Vector row = new Vector();
            row.add(ChiTietKhuyenMai.getMaChuongTrinhKhuyenMai());
            row.add(ChiTietKhuyenMai.getMaChiTietKhuyenMai());
            row.add(ChiTietKhuyenMai.getMaCode());
            row.add(ChiTietKhuyenMai.getSoLuong());
            int conlai = Integer.parseInt(ChiTietKhuyenMai.getSoLuong()) - Integer.parseInt(ChiTietKhuyenMai.getSoLuongDaDung());
            row.add(conlai);
            row.add(TimestampToDateString(quanlycuahanggiay.quanlycuahanggiay.ChuongTrinhKhuyenMaiBUS.getNgayTaobyMaChuongTrinhKhuyenMai(ChiTietKhuyenMai.getMaChuongTrinhKhuyenMai()),1));
            row.add(TimestampToDateString(ChiTietKhuyenMai.getThoiGianBatDauSuDung(),1));
            listTableModel.addRow(row);
            
        });
        tblChiTietKhuyenMai.setModel(listTableModel);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        lblTitleTrangThai = new javax.swing.JLabel();
        pnlTimKiemMaChiTiet = new GUI.Rounded();
        txtTimKiemMaChiTiet = new javax.swing.JTextField();
        pnlTimKiemMaChuongTrinh = new GUI.Rounded();
        txtTimKiemMaChuongTrinh = new javax.swing.JTextField();
        pnlTaoChiTiet = new GUI.Rounded();
        lblTaoPhieuNhap = new javax.swing.JLabel();
        pnlTblDonHangChuaXacNhan = new GUI.Rounded();
        scpDonHang = new javax.swing.JScrollPane();
        tblChiTietKhuyenMai = new javax.swing.JTable(){
            public boolean isCellEditable ( int row, int col)
            {
                return false;
            }

        };
        lblTitleMaChiTiet = new javax.swing.JLabel();
        pnlTrangThai = new javax.swing.JPanel();
        pnlQuayVe = new GUI.Rounded();
        lblQuayVe = new javax.swing.JLabel();

        setBackground(new java.awt.Color(45, 47, 62));
        setPreferredSize(new java.awt.Dimension(939, 751));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Mã chương trình:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, 30));

        lblTitleTrangThai.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTitleTrangThai.setForeground(new java.awt.Color(255, 255, 255));
        lblTitleTrangThai.setText("Trạng thái:");
        add(lblTitleTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 70, -1, 30));

        pnlTimKiemMaChiTiet.setBackground(new java.awt.Color(45, 47, 62));
        pnlTimKiemMaChiTiet.setForeground(new java.awt.Color(62, 64, 78));
        pnlTimKiemMaChiTiet.setFocusable(false);

        txtTimKiemMaChiTiet.setBackground(new java.awt.Color(62, 64, 78));
        txtTimKiemMaChiTiet.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTimKiemMaChiTiet.setForeground(new java.awt.Color(192, 192, 192));
        txtTimKiemMaChiTiet.setBorder(null);
        txtTimKiemMaChiTiet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemMaChiTietKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlTimKiemMaChiTietLayout = new javax.swing.GroupLayout(pnlTimKiemMaChiTiet);
        pnlTimKiemMaChiTiet.setLayout(pnlTimKiemMaChiTietLayout);
        pnlTimKiemMaChiTietLayout.setHorizontalGroup(
            pnlTimKiemMaChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemMaChiTietLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiemMaChiTiet, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE))
        );
        pnlTimKiemMaChiTietLayout.setVerticalGroup(
            pnlTimKiemMaChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemMaChiTietLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimKiemMaChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(pnlTimKiemMaChiTiet, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, 450, -1));

        pnlTimKiemMaChuongTrinh.setBackground(new java.awt.Color(45, 47, 62));
        pnlTimKiemMaChuongTrinh.setForeground(new java.awt.Color(62, 64, 78));
        pnlTimKiemMaChuongTrinh.setFocusable(false);

        txtTimKiemMaChuongTrinh.setBackground(new java.awt.Color(62, 64, 78));
        txtTimKiemMaChuongTrinh.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTimKiemMaChuongTrinh.setForeground(new java.awt.Color(192, 192, 192));
        txtTimKiemMaChuongTrinh.setBorder(null);
        txtTimKiemMaChuongTrinh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemMaChuongTrinhKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlTimKiemMaChuongTrinhLayout = new javax.swing.GroupLayout(pnlTimKiemMaChuongTrinh);
        pnlTimKiemMaChuongTrinh.setLayout(pnlTimKiemMaChuongTrinhLayout);
        pnlTimKiemMaChuongTrinhLayout.setHorizontalGroup(
            pnlTimKiemMaChuongTrinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemMaChuongTrinhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiemMaChuongTrinh, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTimKiemMaChuongTrinhLayout.setVerticalGroup(
            pnlTimKiemMaChuongTrinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemMaChuongTrinhLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimKiemMaChuongTrinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(pnlTimKiemMaChuongTrinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 450, -1));

        pnlTaoChiTiet.setBackground(new java.awt.Color(45, 47, 62));
        pnlTaoChiTiet.setForeground(new java.awt.Color(210, 48, 44));
        pnlTaoChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlTaoChiTietMouseClicked(evt);
            }
        });

        lblTaoPhieuNhap.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblTaoPhieuNhap.setForeground(new java.awt.Color(255, 255, 255));
        lblTaoPhieuNhap.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTaoPhieuNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        lblTaoPhieuNhap.setText("Tạo chi tiết");

        javax.swing.GroupLayout pnlTaoChiTietLayout = new javax.swing.GroupLayout(pnlTaoChiTiet);
        pnlTaoChiTiet.setLayout(pnlTaoChiTietLayout);
        pnlTaoChiTietLayout.setHorizontalGroup(
            pnlTaoChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTaoChiTietLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTaoPhieuNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTaoChiTietLayout.setVerticalGroup(
            pnlTaoChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTaoChiTietLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTaoPhieuNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(pnlTaoChiTiet, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 180, 130, -1));

        pnlTblDonHangChuaXacNhan.setBackground(new java.awt.Color(45, 47, 62));
        pnlTblDonHangChuaXacNhan.setForeground(new java.awt.Color(53, 55, 70));

        scpDonHang.setBackground(new java.awt.Color(53, 55, 70));

        tblChiTietKhuyenMai.setAutoCreateRowSorter(true);
        tblChiTietKhuyenMai.setBackground(new java.awt.Color(53, 55, 70));
        tblChiTietKhuyenMai.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tblChiTietKhuyenMai.setForeground(new java.awt.Color(255, 255, 255));
        tblChiTietKhuyenMai.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblChiTietKhuyenMai.setFocusable(false);
        tblChiTietKhuyenMai.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblChiTietKhuyenMai.setRowHeight(25);
        tblChiTietKhuyenMai.setSelectionBackground(new java.awt.Color(72, 74, 89));
        tblChiTietKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietKhuyenMaiMouseClicked(evt);
            }
        });
        scpDonHang.setViewportView(tblChiTietKhuyenMai);

        javax.swing.GroupLayout pnlTblDonHangChuaXacNhanLayout = new javax.swing.GroupLayout(pnlTblDonHangChuaXacNhan);
        pnlTblDonHangChuaXacNhan.setLayout(pnlTblDonHangChuaXacNhanLayout);
        pnlTblDonHangChuaXacNhanLayout.setHorizontalGroup(
            pnlTblDonHangChuaXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTblDonHangChuaXacNhanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scpDonHang, javax.swing.GroupLayout.DEFAULT_SIZE, 858, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTblDonHangChuaXacNhanLayout.setVerticalGroup(
            pnlTblDonHangChuaXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTblDonHangChuaXacNhanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scpDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        add(pnlTblDonHangChuaXacNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 238, 870, 480));

        lblTitleMaChiTiet.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTitleMaChiTiet.setForeground(new java.awt.Color(255, 255, 255));
        lblTitleMaChiTiet.setText("Mã chi tiết:");
        add(lblTitleMaChiTiet, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, 30));

        pnlTrangThai.setBackground(new java.awt.Color(53, 55, 70));

        javax.swing.GroupLayout pnlTrangThaiLayout = new javax.swing.GroupLayout(pnlTrangThai);
        pnlTrangThai.setLayout(pnlTrangThaiLayout);
        pnlTrangThaiLayout.setHorizontalGroup(
            pnlTrangThaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );
        pnlTrangThaiLayout.setVerticalGroup(
            pnlTrangThaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        add(pnlTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 70, 130, 30));

        pnlQuayVe.setBackground(new java.awt.Color(45, 47, 62));
        pnlQuayVe.setForeground(new java.awt.Color(210, 48, 44));
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

        add(pnlQuayVe, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void pnlTaoChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTaoChiTietMouseClicked
            this.removeAll();
            this.setLayout(new BorderLayout());
            this.add(new TaoMaKhuyenMaiJPanel());
            this.validate();
            this.repaint();        
    }//GEN-LAST:event_pnlTaoChiTietMouseClicked

    private void tblChiTietKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietKhuyenMaiMouseClicked
        int column = tblChiTietKhuyenMai.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/tblChiTietKhuyenMai.getRowHeight();

        if(row < tblChiTietKhuyenMai.getRowCount() && row >= 0 && column < tblChiTietKhuyenMai.getColumnCount() && column >= 0){
            this.removeAll();
            this.setLayout(new BorderLayout());
            currentChiTietKhuyenMai = quanlycuahanggiay.quanlycuahanggiay.ChiTietKhuyenMaiBUS.getByMaChiTietKhuyenMai((String) tblChiTietKhuyenMai.getModel().getValueAt(row, 1));
            currentChuongTrinhKhuyenMai = quanlycuahanggiay.quanlycuahanggiay.ChuongTrinhKhuyenMaiBUS.getByMaChuongTrinhKhuyenMai((String) tblChiTietKhuyenMai.getModel().getValueAt(row, 0));
            currentDonHang = quanlycuahanggiay.quanlycuahanggiay.DonHangBUS.getByMaDonHang((String) tblChiTietKhuyenMai.getModel().getValueAt(row, 0));
            this.add(new ThongTinChiTietKhuyenMaiJPanel());
            this.validate();
            this.repaint();               
        }
    }//GEN-LAST:event_tblChiTietKhuyenMaiMouseClicked

    private void pnlQuayVeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlQuayVeMouseClicked

        this.removeAll();
        this.setLayout(new BorderLayout());
        this.add(new KhuyenMaiJPanel());
        this.validate();
        this.repaint();        // TODO add your handling code here:
    }//GEN-LAST:event_pnlQuayVeMouseClicked

    private void txtTimKiemMaChuongTrinhKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemMaChuongTrinhKeyReleased
        getAdvancedSearch();
    }//GEN-LAST:event_txtTimKiemMaChuongTrinhKeyReleased

    private void txtTimKiemMaChiTietKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemMaChiTietKeyReleased
        getAdvancedSearch();
    }//GEN-LAST:event_txtTimKiemMaChiTietKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblQuayVe;
    private javax.swing.JLabel lblTaoPhieuNhap;
    private javax.swing.JLabel lblTitleMaChiTiet;
    private javax.swing.JLabel lblTitleTrangThai;
    private GUI.Rounded pnlQuayVe;
    private GUI.Rounded pnlTaoChiTiet;
    private GUI.Rounded pnlTblDonHangChuaXacNhan;
    private GUI.Rounded pnlTimKiemMaChiTiet;
    private GUI.Rounded pnlTimKiemMaChuongTrinh;
    private javax.swing.JPanel pnlTrangThai;
    private javax.swing.JScrollPane scpDonHang;
    private static javax.swing.JTable tblChiTietKhuyenMai;
    private javax.swing.JTextField txtTimKiemMaChiTiet;
    private javax.swing.JTextField txtTimKiemMaChuongTrinh;
    // End of variables declaration//GEN-END:variables
}
