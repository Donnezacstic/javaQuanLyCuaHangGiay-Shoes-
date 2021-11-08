/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.POPUP;

import DTO.SanPhamDTO;
import static GUI.KHUYENMAI.ChiTietKhuyenMaiJPanel.currentChiTietKhuyenMai;
import GUI.RenderTable;
import GUI.Sweet.SweetComboBox;
import static GUI.TongQuanJPanel.customTable;
import java.awt.Cursor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import static quanlycuahanggiay.Currency.changeCurrency;

/**
 *
 * @author admin
 */
public class XemSanPhamKhuyenMaiTrongChiTietKMJFrame extends javax.swing.JFrame {

    int xMouse, yMouse;
    static DefaultTableModel listTableModel;
    SweetComboBox cbxThuongHieu;
    SweetComboBox cbxSize;
    SweetComboBox cbxMau;
    Object[] columnNames = {"Mã sản phẩm", "Tên sản phẩm", "Size", "Màu", "Đơn giá", "Số lượng", "Thương hiệu"};
    public static ArrayList<String> MangSanPham;
    
    public void setComboBox(){
        String[] mau = quanlycuahanggiay.quanlycuahanggiay.MauBUS.getTenStringArray();
        cbxMau = new SweetComboBox("#353746","#ffffff",0,0,150,30,mau);
        pnlMau.add(cbxMau);
        String[] size = quanlycuahanggiay.quanlycuahanggiay.SizeBUS.getTenStringArray();
        cbxSize = new SweetComboBox("#353746","#ffffff",0,0,150,30,size);
        pnlSize.add(cbxSize);
        String[] thuonghieu = quanlycuahanggiay.quanlycuahanggiay.ThuongHieuBUS.getTenStringArray();
        cbxThuongHieu = new SweetComboBox("#353746","#ffffff",0,0,150,30,thuonghieu);
        pnlThuongHieu.add(cbxThuongHieu);    
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
    public void search(){
        listTableModel.setRowCount(0);
        MangSanPham.forEach(SanPham ->{
        Vector row = new Vector();
        SanPhamDTO sanpham = quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.getSanPhamByMaSanPham(SanPham);
            row.add(sanpham.getMaSanPham());
            row.add(sanpham.getTenSanPham());
            row.add(quanlycuahanggiay.quanlycuahanggiay.SizeBUS.getTenSizeByMaSize(sanpham.getMaSize()));
            row.add(quanlycuahanggiay.quanlycuahanggiay.MauBUS.getTenMauByMaMau(sanpham.getMaMau()));
            row.add(changeCurrency(sanpham.getDonGia()));
            row.add(sanpham.getSoLuong());
            row.add(quanlycuahanggiay.quanlycuahanggiay.ThuongHieuBUS.getTenThuongHieuByMaThuongHieu(sanpham.getMaThuongHieu()));
        listTableModel.addRow(row);
        });
        tblSanPham.setModel(listTableModel);
    }
    private void getAdvancedSearch(){
        HashMap<String, String> advancedSearch = new HashMap<>();
        advancedSearch.put("MaSanPham",txtTimKiemMaSanPham.getText());
        advancedSearch.put("TenSanPham",txtTimKiemTenSanPham.getText());
        advancedSearch.put("Mau","");
        advancedSearch.put("Size","");
        advancedSearch.put("ThuongHieu","");
        if(!cbxMau.getSelectedItem().toString().equals("Màu"))
            advancedSearch.replace("Mau", quanlycuahanggiay.quanlycuahanggiay.MauBUS.getMaMau(cbxMau.getSelectedItem().toString()));
        if(!cbxSize.getSelectedItem().toString().equals("Size"))
            advancedSearch.replace("Size", quanlycuahanggiay.quanlycuahanggiay.SizeBUS.getMaSize(cbxSize.getSelectedItem().toString()));
        if(!cbxThuongHieu.getSelectedItem().toString().equals("Thương hiệu"))
            advancedSearch.replace("ThuongHieu", quanlycuahanggiay.quanlycuahanggiay.ThuongHieuBUS.getMaThuongHieu(cbxThuongHieu.getSelectedItem().toString()));
        MangSanPham = quanlycuahanggiay.quanlycuahanggiay.SanPhamKhuyenMaiBUS.advancedSearch(advancedSearch);
        search();
    }
    public void ShowTable(JTable table, Object[] columnName) {
        listTableModel = new DefaultTableModel(columnNames, 0);
        table.setDefaultRenderer(Object.class, new RenderTable());
        fetchAll(); 
    }

    public static void fetchAll() {
        System.out.print(currentChiTietKhuyenMai.getMaChiTietKhuyenMai());
        MangSanPham = quanlycuahanggiay.quanlycuahanggiay.SanPhamKhuyenMaiBUS.getSanPhamKhuyenMai(currentChiTietKhuyenMai.getMaChiTietKhuyenMai());
        listTableModel.setRowCount(0);
        if(MangSanPham==null)
        {
            quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.SanPhamList.forEach(sanpham->{
            Vector row = new Vector();
                row.add(sanpham.getMaSanPham());
                row.add(sanpham.getTenSanPham());
                row.add(quanlycuahanggiay.quanlycuahanggiay.SizeBUS.getTenSizeByMaSize(sanpham.getMaSize()));
                row.add(quanlycuahanggiay.quanlycuahanggiay.MauBUS.getTenMauByMaMau(sanpham.getMaMau()));
                row.add(changeCurrency(sanpham.getDonGia()));
                row.add(sanpham.getSoLuong());
                row.add(quanlycuahanggiay.quanlycuahanggiay.ThuongHieuBUS.getTenThuongHieuByMaThuongHieu(sanpham.getMaThuongHieu()));  
            listTableModel.addRow(row);
            });
        }
        else
        {       
            MangSanPham.forEach(MaSanPham ->
            {
                Vector row = new Vector();
                SanPhamDTO SanPhamKhuyenMai = quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.getByMaSanPham(MaSanPham);
                row.add(MaSanPham);
                row.add(SanPhamKhuyenMai.getTenSanPham());
                row.add(quanlycuahanggiay.quanlycuahanggiay.SizeBUS.getTenSizeByMaSize(SanPhamKhuyenMai.getMaSize()));
                row.add(quanlycuahanggiay.quanlycuahanggiay.MauBUS.getTenMauByMaMau(SanPhamKhuyenMai.getMaMau()));
                row.add(changeCurrency(SanPhamKhuyenMai.getDonGia()));
                row.add(SanPhamKhuyenMai.getSoLuong());
                row.add(quanlycuahanggiay.quanlycuahanggiay.ThuongHieuBUS.getTenThuongHieuByMaThuongHieu(SanPhamKhuyenMai.getMaThuongHieu()));  
            listTableModel.addRow(row);
            });
        }
        tblSanPham.setModel(listTableModel);
    }

    public XemSanPhamKhuyenMaiTrongChiTietKMJFrame() {
        SwingUtilities.getWindowAncestor(quanlycuahanggiay.quanlycuahanggiay.bg).setEnabled(false);
        initComponents();
        customTable(tblSanPham, scpSanPham);
        ShowTable(tblSanPham, columnNames);
        setComboBox();
        setLocationRelativeTo(null);
        lblEdit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnlThongTin = new GUI.Rounded();
        scpSanPham = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable(){
            public boolean isCellEditable ( int row, int col)
            {
                return false;
            }

        };
        lblDonHang = new javax.swing.JLabel();
        pnlTimKiem = new GUI.Rounded();
        txtTimKiemTenSanPham = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lblEdit = new javax.swing.JLabel();
        pnlMau = new javax.swing.JPanel();
        pnlSize = new javax.swing.JPanel();
        pnlThuongHieu = new javax.swing.JPanel();
        pnlTimKiemMaSanPham = new GUI.Rounded();
        txtTimKiemMaSanPham = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(45, 47, 62));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlThongTin.setBackground(new java.awt.Color(45, 47, 62));
        pnlThongTin.setForeground(new java.awt.Color(53, 55, 70));
        pnlThongTin.setPreferredSize(new java.awt.Dimension(920, 750));

        scpSanPham.setBackground(new java.awt.Color(53, 55, 70));
        scpSanPham.setForeground(new java.awt.Color(53, 55, 70));

        tblSanPham.setAutoCreateRowSorter(true);
        tblSanPham.setBackground(new java.awt.Color(53, 55, 70));
        tblSanPham.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tblSanPham.setForeground(new java.awt.Color(255, 255, 255));
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

        lblDonHang.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        lblDonHang.setForeground(new java.awt.Color(210, 48, 44));
        lblDonHang.setText("Sản phẩm được phép dùng mã");

        javax.swing.GroupLayout pnlThongTinLayout = new javax.swing.GroupLayout(pnlThongTin);
        pnlThongTin.setLayout(pnlThongTinLayout);
        pnlThongTinLayout.setHorizontalGroup(
            pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scpSanPham)
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addComponent(lblDonHang)
                        .addGap(0, 592, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlThongTinLayout.setVerticalGroup(
            pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDonHang)
                .addGap(18, 18, 18)
                .addComponent(scpSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(pnlThongTin, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 158, 890, 560));

        pnlTimKiem.setBackground(new java.awt.Color(45, 47, 62));
        pnlTimKiem.setForeground(new java.awt.Color(62, 64, 78));
        pnlTimKiem.setFocusable(false);

        txtTimKiemTenSanPham.setBackground(new java.awt.Color(62, 64, 78));
        txtTimKiemTenSanPham.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTimKiemTenSanPham.setForeground(new java.awt.Color(192, 192, 192));
        txtTimKiemTenSanPham.setBorder(null);
        txtTimKiemTenSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemTenSanPhamKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemTenSanPhamKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlTimKiemLayout = new javax.swing.GroupLayout(pnlTimKiem);
        pnlTimKiem.setLayout(pnlTimKiemLayout);
        pnlTimKiemLayout.setHorizontalGroup(
            pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiemTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTimKiemLayout.setVerticalGroup(
            pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimKiemTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(pnlTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, -1, -1));

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Tên sản phẩm:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 18, -1, 30));

        lblEdit.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        lblEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditMouseClicked(evt);
            }
        });
        jPanel1.add(lblEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, 60, -1));

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

        jPanel1.add(pnlMau, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 150, 30));

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

        jPanel1.add(pnlSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 120, 150, -1));

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

        jPanel1.add(pnlThuongHieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 120, 150, -1));

        pnlTimKiemMaSanPham.setBackground(new java.awt.Color(45, 47, 62));
        pnlTimKiemMaSanPham.setForeground(new java.awt.Color(62, 64, 78));
        pnlTimKiemMaSanPham.setFocusable(false);

        txtTimKiemMaSanPham.setBackground(new java.awt.Color(62, 64, 78));
        txtTimKiemMaSanPham.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTimKiemMaSanPham.setForeground(new java.awt.Color(192, 192, 192));
        txtTimKiemMaSanPham.setBorder(null);
        txtTimKiemMaSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemMaSanPhamKeyPressed(evt);
            }
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

        jPanel1.add(pnlTimKiemMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 390, -1));

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Mã sản phẩm:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, 30));

        jLabel4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel4MouseDragged(evt);
            }
        });
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel4MousePressed(evt);
            }
        });
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 730));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 920, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 736, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int column = tblSanPham.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / tblSanPham.getRowHeight();

        if (row < tblSanPham.getRowCount() && row >= 0 && column < tblSanPham.getColumnCount() && column >= 0) {

            for (int i = 0; i < tblSanPham.getColumnCount() - 1; i++) {
                System.out.println(tblSanPham.getModel().getValueAt(row, i));
            }

        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void lblEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditMouseClicked
        
        this.dispose();
        
        SwingUtilities.getWindowAncestor(quanlycuahanggiay.quanlycuahanggiay.bg).setEnabled(true);
    }//GEN-LAST:event_lblEditMouseClicked

    private void jLabel4MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_jLabel4MouseDragged

    private void jLabel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_jLabel4MousePressed

    private void txtTimKiemTenSanPhamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemTenSanPhamKeyPressed
        getAdvancedSearch();
    }//GEN-LAST:event_txtTimKiemTenSanPhamKeyPressed

    private void txtTimKiemTenSanPhamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemTenSanPhamKeyReleased
        getAdvancedSearch();
    }//GEN-LAST:event_txtTimKiemTenSanPhamKeyReleased

    private void txtTimKiemMaSanPhamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemMaSanPhamKeyPressed
        getAdvancedSearch();
    }//GEN-LAST:event_txtTimKiemMaSanPhamKeyPressed

    private void txtTimKiemMaSanPhamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemMaSanPhamKeyReleased
        getAdvancedSearch();
    }//GEN-LAST:event_txtTimKiemMaSanPhamKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(XemSanPhamKhuyenMaiTrongChiTietKMJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(XemSanPhamKhuyenMaiTrongChiTietKMJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(XemSanPhamKhuyenMaiTrongChiTietKMJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(XemSanPhamKhuyenMaiTrongChiTietKMJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new XemSanPhamKhuyenMaiTrongChiTietKMJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblDonHang;
    private javax.swing.JLabel lblEdit;
    private javax.swing.JPanel pnlMau;
    private javax.swing.JPanel pnlSize;
    private GUI.Rounded pnlThongTin;
    private javax.swing.JPanel pnlThuongHieu;
    private GUI.Rounded pnlTimKiem;
    private GUI.Rounded pnlTimKiemMaSanPham;
    private javax.swing.JScrollPane scpSanPham;
    private static javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtTimKiemMaSanPham;
    private javax.swing.JTextField txtTimKiemTenSanPham;
    // End of variables declaration//GEN-END:variables
}
