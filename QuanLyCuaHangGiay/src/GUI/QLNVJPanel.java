/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DTO.AccountDTO;
import DTO.NhanVienDTO;
import GUI.CHITIET.ChiTietNhanVienJPanel;
import GUI.Sweet.SweetComboBox;
import static GUI.TongQuanJPanel.customTable;
import static GUI.TongQuanJPanel.setJTableColumnsWidth;
import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static quanlycuahanggiay.Regexp.So;


/**
 *
 * @author admin
 */
public class QLNVJPanel extends javax.swing.JPanel {
    static DefaultTableModel listTableModel;
    public NhanVienDTO currentNhanVien = new NhanVienDTO();
    public AccountDTO currentAccount = new AccountDTO();
    public ArrayList<NhanVienDTO> arraySearch;
    SweetComboBox cbxTrangThai;
    SweetComboBox cbxCaLam;
    SweetComboBox cbxGioiTinh;
    
  
    public ArrayList<javax.swing.AbstractButton> arrButton =new ArrayList<>();
     Object[] columnNames ={"Mã nhân viên","Họ tên","Năm sinh","Số điện thoại","Ca làm","Trạng thái"};
    /**
     * Creates new form QLNVJPanel
     */
    public void initEvent(){
        arrButton.add(cbxTongQuan);
        arrButton.add(cbxDonHang);
        arrButton.add(cbxSanPham);
        arrButton.add(cbxKhachHang);
        arrButton.add(cbxDoanhThu);
        arrButton.add(cbxThongKe);
        arrButton.add(cbxKhuyenMai);
        arrButton.add(cbxNhapHang);
        arrButton.add(cbxQuanLyNhanVien);
        arrButton.add(cbxQuanLyDanhMuc);
        arrButton.add(cbxCaSang);
        arrButton.add(cbxCaChieu);
        arrButton.add(cbxCaToi);
        
        for(javax.swing.AbstractButton checkbox:arrButton){
            checkbox.addItemListener(new ItemListener(){
                @Override
                public void itemStateChanged(ItemEvent ie) {
                   
                    javax.swing.AbstractButton currentItem = (javax.swing.AbstractButton) ie.getItem();
                   if(ie.getStateChange()==1)
                       currentItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/checked.png")));
                   else 
                       currentItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png")));
                   getAdvancedSearch();
                }
            });
    }
        cbxCaSang.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie){
                getAdvancedSearch();
            }
        });
        cbxCaChieu.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie){
                getAdvancedSearch();
            }
        });
        cbxCaToi.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie){
                getAdvancedSearch();
            }
        });
        arrButton.remove(cbxCaSang);
        arrButton.remove(cbxCaChieu);
        arrButton.remove(cbxCaToi);        
    }
    public void setComboBox(){
        String[] trangthai = {"Tất cả","Còn làm","Nghỉ làm"};
        cbxTrangThai = new SweetComboBox("#353746","#ffffff",0,0,170,30,trangthai);
        String[] gioitinh = {"Tất cả","Nam","Nữ"};
        cbxGioiTinh = new SweetComboBox("#353746","#ffffff",0,0,130,30,gioitinh);
        pnlGioiTinh.add(cbxGioiTinh);
         pnlTrangThai.add(cbxTrangThai);
         
        cbxTrangThai.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie){
                if(ie.getStateChange() == ItemEvent.SELECTED)
                getAdvancedSearch();
            }
        });
        cbxGioiTinh.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie){
                if(ie.getStateChange() == ItemEvent.SELECTED)
                getAdvancedSearch();
            }
        });        
    }
    
    public static void fetchAll(){
        
        listTableModel.setRowCount(0);
        quanlycuahanggiay.quanlycuahanggiay.NhanVienBUS.NhanVienList.forEach(NhanVien ->{
            Vector row = new Vector();
            row.add(NhanVien.getMaNhanVien());
            row.add(NhanVien.getHoTen());
            row.add(NhanVien.getNamSinh());
            row.add(NhanVien.getSoDienThoai());
            row.add(NhanVien.getCaLam());
            row.add(NhanVien.getTrangThai());
            listTableModel.addRow(row);
        });
        tblQLNV.setModel(listTableModel);
    }
    
    public  void search(){
        
        listTableModel.setRowCount(0);
        arraySearch.forEach( NhanVien ->{
            Vector row = new Vector();
            row.add(NhanVien.getMaNhanVien());
            row.add(NhanVien.getHoTen());
            row.add(NhanVien.getNamSinh());
            row.add(NhanVien.getSoDienThoai());
            row.add(NhanVien.getCaLam());
            row.add(NhanVien.getTrangThai());
            listTableModel.addRow(row);
        });
        tblQLNV.setModel(listTableModel);
    }
       
    public void ShowTable(JTable table,Object[] columnName){
        
        listTableModel = new DefaultTableModel(columnNames,0);
        table.setDefaultRenderer(Object.class, new RenderTable());
        fetchAll(); 

    }       
    public QLNVJPanel() {
        initComponents();
        customTable(tblQLNV,scpQLNV);
        ShowTable(tblQLNV,columnNames);
        setComboBox();
        initEvent();
        setJTableColumnsWidth(tblQLNV,890,15,25,15,15,10,20);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTblQLNV = new GUI.Rounded();
        scpQLNV = new javax.swing.JScrollPane();
        tblQLNV = new javax.swing.JTable(){
            public boolean isCellEditable ( int row, int col)
            {
                return false;
            }

        };
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pnlTimKiemHoTenSDT = new GUI.Rounded();
        txtTimKiemHoTenSDT = new javax.swing.JTextField();
        lblDonHang = new javax.swing.JLabel();
        pnlTrangThai = new javax.swing.JPanel();
        lblTrangThai = new javax.swing.JLabel();
        pnlTimKiemMaNhanVien = new GUI.Rounded();
        txtTimKiemMaNhanVien = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbxTongQuan = new javax.swing.JCheckBox();
        cbxDonHang = new javax.swing.JCheckBox();
        cbxThongKe = new javax.swing.JCheckBox();
        cbxDoanhThu = new javax.swing.JCheckBox();
        cbxQuanLyDanhMuc = new javax.swing.JCheckBox();
        cbxKhuyenMai = new javax.swing.JCheckBox();
        cbxQuanLyNhanVien = new javax.swing.JCheckBox();
        cbxKhachHang = new javax.swing.JCheckBox();
        cbxSanPham = new javax.swing.JCheckBox();
        cbxNhapHang = new javax.swing.JCheckBox();
        lblGioiTinh = new javax.swing.JLabel();
        pnlGioiTinh = new javax.swing.JPanel();
        cbxCaSang = new javax.swing.JCheckBox();
        cbxCaChieu = new javax.swing.JCheckBox();
        cbxCaToi = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(45, 47, 62));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlTblQLNV.setBackground(new java.awt.Color(45, 47, 62));
        pnlTblQLNV.setForeground(new java.awt.Color(53, 55, 70));

        scpQLNV.setBackground(new java.awt.Color(53, 55, 70));
        scpQLNV.setForeground(new java.awt.Color(53, 55, 70));

        tblQLNV.setAutoCreateRowSorter(true);
        tblQLNV.setBackground(new java.awt.Color(53, 55, 70));
        tblQLNV.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tblQLNV.setForeground(new java.awt.Color(255, 255, 255));
        tblQLNV.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblQLNV.setFocusable(false);
        tblQLNV.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblQLNV.setRowHeight(25);
        tblQLNV.setSelectionBackground(new java.awt.Color(72, 74, 89));
        tblQLNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQLNVMouseClicked(evt);
            }
        });
        scpQLNV.setViewportView(tblQLNV);

        javax.swing.GroupLayout pnlTblQLNVLayout = new javax.swing.GroupLayout(pnlTblQLNV);
        pnlTblQLNV.setLayout(pnlTblQLNVLayout);
        pnlTblQLNVLayout.setHorizontalGroup(
            pnlTblQLNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTblQLNVLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scpQLNV, javax.swing.GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTblQLNVLayout.setVerticalGroup(
            pnlTblQLNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTblQLNVLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scpQLNV, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(pnlTblQLNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, -1, 470));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Quyền:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, 30));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Họ tên/ Số điện thoại:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, 30));

        pnlTimKiemHoTenSDT.setBackground(new java.awt.Color(45, 47, 62));
        pnlTimKiemHoTenSDT.setForeground(new java.awt.Color(62, 64, 78));
        pnlTimKiemHoTenSDT.setFocusable(false);

        txtTimKiemHoTenSDT.setBackground(new java.awt.Color(62, 64, 78));
        txtTimKiemHoTenSDT.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTimKiemHoTenSDT.setForeground(new java.awt.Color(192, 192, 192));
        txtTimKiemHoTenSDT.setBorder(null);
        txtTimKiemHoTenSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemHoTenSDTKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlTimKiemHoTenSDTLayout = new javax.swing.GroupLayout(pnlTimKiemHoTenSDT);
        pnlTimKiemHoTenSDT.setLayout(pnlTimKiemHoTenSDTLayout);
        pnlTimKiemHoTenSDTLayout.setHorizontalGroup(
            pnlTimKiemHoTenSDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemHoTenSDTLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiemHoTenSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTimKiemHoTenSDTLayout.setVerticalGroup(
            pnlTimKiemHoTenSDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemHoTenSDTLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimKiemHoTenSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(pnlTimKiemHoTenSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, -1, 30));

        lblDonHang.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblDonHang.setForeground(new java.awt.Color(255, 255, 255));
        lblDonHang.setText("Ca làm:");
        add(lblDonHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 20, -1, 30));

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

        add(pnlTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 70, 170, 30));

        lblTrangThai.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTrangThai.setForeground(new java.awt.Color(255, 255, 255));
        lblTrangThai.setText("Trạng thái:");
        add(lblTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 70, -1, 30));

        pnlTimKiemMaNhanVien.setBackground(new java.awt.Color(45, 47, 62));
        pnlTimKiemMaNhanVien.setForeground(new java.awt.Color(62, 64, 78));
        pnlTimKiemMaNhanVien.setFocusable(false);

        txtTimKiemMaNhanVien.setBackground(new java.awt.Color(62, 64, 78));
        txtTimKiemMaNhanVien.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTimKiemMaNhanVien.setForeground(new java.awt.Color(192, 192, 192));
        txtTimKiemMaNhanVien.setBorder(null);
        txtTimKiemMaNhanVien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemMaNhanVienKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlTimKiemMaNhanVienLayout = new javax.swing.GroupLayout(pnlTimKiemMaNhanVien);
        pnlTimKiemMaNhanVien.setLayout(pnlTimKiemMaNhanVienLayout);
        pnlTimKiemMaNhanVienLayout.setHorizontalGroup(
            pnlTimKiemMaNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemMaNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiemMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTimKiemMaNhanVienLayout.setVerticalGroup(
            pnlTimKiemMaNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemMaNhanVienLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimKiemMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(pnlTimKiemMaNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 140, 30));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Mã nhân viên:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, 30));

        cbxTongQuan.setBackground(new java.awt.Color(45, 47, 62));
        cbxTongQuan.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxTongQuan.setForeground(new java.awt.Color(255, 255, 255));
        cbxTongQuan.setText("Tổng quan");
        cbxTongQuan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        add(cbxTongQuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 134, 20));

        cbxDonHang.setBackground(new java.awt.Color(45, 47, 62));
        cbxDonHang.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxDonHang.setForeground(new java.awt.Color(255, 255, 255));
        cbxDonHang.setText("Đơn hàng");
        cbxDonHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        add(cbxDonHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, 100, 20));

        cbxThongKe.setBackground(new java.awt.Color(45, 47, 62));
        cbxThongKe.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxThongKe.setForeground(new java.awt.Color(255, 255, 255));
        cbxThongKe.setText("Thống kê");
        cbxThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        add(cbxThongKe, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 100, 20));

        cbxDoanhThu.setBackground(new java.awt.Color(45, 47, 62));
        cbxDoanhThu.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxDoanhThu.setForeground(new java.awt.Color(255, 255, 255));
        cbxDoanhThu.setText("Doanh thu");
        cbxDoanhThu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        add(cbxDoanhThu, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 180, 134, 20));

        cbxQuanLyDanhMuc.setBackground(new java.awt.Color(45, 47, 62));
        cbxQuanLyDanhMuc.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxQuanLyDanhMuc.setForeground(new java.awt.Color(255, 255, 255));
        cbxQuanLyDanhMuc.setText("Quản lí danh mục");
        cbxQuanLyDanhMuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        add(cbxQuanLyDanhMuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 180, -1, 20));

        cbxKhuyenMai.setBackground(new java.awt.Color(45, 47, 62));
        cbxKhuyenMai.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxKhuyenMai.setForeground(new java.awt.Color(255, 255, 255));
        cbxKhuyenMai.setText("Khuyến mãi");
        cbxKhuyenMai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        add(cbxKhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 210, 132, 20));

        cbxQuanLyNhanVien.setBackground(new java.awt.Color(45, 47, 62));
        cbxQuanLyNhanVien.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxQuanLyNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        cbxQuanLyNhanVien.setText("Quản lí nhân viên");
        cbxQuanLyNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        add(cbxQuanLyNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, -1, 20));

        cbxKhachHang.setBackground(new java.awt.Color(45, 47, 62));
        cbxKhachHang.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        cbxKhachHang.setText("Khách hàng");
        cbxKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        add(cbxKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 210, 132, 20));

        cbxSanPham.setBackground(new java.awt.Color(45, 47, 62));
        cbxSanPham.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxSanPham.setForeground(new java.awt.Color(255, 255, 255));
        cbxSanPham.setText("Sản phẩm");
        cbxSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        add(cbxSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 180, 132, 20));

        cbxNhapHang.setBackground(new java.awt.Color(45, 47, 62));
        cbxNhapHang.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxNhapHang.setForeground(new java.awt.Color(255, 255, 255));
        cbxNhapHang.setText("Nhập hàng");
        cbxNhapHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        add(cbxNhapHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 210, 132, 20));

        lblGioiTinh.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblGioiTinh.setForeground(new java.awt.Color(255, 255, 255));
        lblGioiTinh.setText("Giới tính:");
        add(lblGioiTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, -1, 30));

        pnlGioiTinh.setBackground(new java.awt.Color(53, 55, 70));
        pnlGioiTinh.setForeground(new java.awt.Color(202, 229, 246));

        javax.swing.GroupLayout pnlGioiTinhLayout = new javax.swing.GroupLayout(pnlGioiTinh);
        pnlGioiTinh.setLayout(pnlGioiTinhLayout);
        pnlGioiTinhLayout.setHorizontalGroup(
            pnlGioiTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );
        pnlGioiTinhLayout.setVerticalGroup(
            pnlGioiTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        add(pnlGioiTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, 130, -1));

        cbxCaSang.setBackground(new java.awt.Color(45, 47, 62));
        cbxCaSang.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxCaSang.setForeground(new java.awt.Color(255, 255, 255));
        cbxCaSang.setText("Ca sáng");
        cbxCaSang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        add(cbxCaSang, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 20, -1, 30));

        cbxCaChieu.setBackground(new java.awt.Color(45, 47, 62));
        cbxCaChieu.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxCaChieu.setForeground(new java.awt.Color(255, 255, 255));
        cbxCaChieu.setText("Ca chiều");
        cbxCaChieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        add(cbxCaChieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 20, -1, 30));

        cbxCaToi.setBackground(new java.awt.Color(45, 47, 62));
        cbxCaToi.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxCaToi.setForeground(new java.awt.Color(255, 255, 255));
        cbxCaToi.setText("Ca tối");
        cbxCaToi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        add(cbxCaToi, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 20, -1, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void getAdvancedSearch(){
        HashMap<String,String> advancedSearch = new HashMap<>();
        advancedSearch.put("HoTenSDT","");
        advancedSearch.put("MaNhanVien","");
        advancedSearch.put("TrangThai","");
        advancedSearch.put("CaSang","");
        advancedSearch.put("CaChieu","");
        advancedSearch.put("CaToi","");
        advancedSearch.put("GioiTinh","");
        advancedSearch.put("Quyen","");
        
        if(!txtTimKiemHoTenSDT.getText().equals(""))
        advancedSearch.replace("HoTenSDT", txtTimKiemHoTenSDT.getText());
        if(!txtTimKiemMaNhanVien.getText().equals(""))
        advancedSearch.replace("MaNhanVien", txtTimKiemMaNhanVien.getText());
        if(cbxGioiTinh.getSelectedItem().equals("Nam"))
            advancedSearch.replace("GioiTinh", "Nam");
        if(cbxGioiTinh.getSelectedItem().equals("Nữ"))
            advancedSearch.replace("GioiTinh","Nữ");
        if(cbxTrangThai.getSelectedItem().equals("Còn làm"))
            advancedSearch.replace("TrangThai", "Còn làm");
        if(cbxCaSang.isSelected())
            advancedSearch.replace("CaSang","Ca sáng");
        if(cbxCaChieu.isSelected())
            advancedSearch.replace("CaChieu","Ca chiều");
        if(cbxCaToi.isSelected())
            advancedSearch.replace("CaToi","Ca tối");
        if(cbxTrangThai.getSelectedItem().equals("Nghỉ làm"))
            advancedSearch.replace("TrangThai", "Nghỉ làm");
            advancedSearch.replace("Quyen",getRole());
            
            
        arraySearch = quanlycuahanggiay.quanlycuahanggiay.NhanVienBUS.advancedSearch(advancedSearch);
        search();
    }
    public String getRole() {
        String Role = "";
        for(int i=0;i<arrButton.size();i++){
            if(arrButton.get(i).isSelected()){
                Role +="1";
            }
            else Role+="0";
        }
        return Role += "0";
    }
    private void tblQLNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQLNVMouseClicked
        int column = tblQLNV.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/tblQLNV.getRowHeight();

        if(row < tblQLNV.getRowCount() && row >= 0 && column < tblQLNV.getColumnCount() && column >= 0){

                
                    this.removeAll();
                    this.setLayout(new BorderLayout());
                    currentNhanVien = quanlycuahanggiay.quanlycuahanggiay.NhanVienBUS.getByMaNhanVien((String) tblQLNV.getModel().getValueAt(row,0));
                    currentAccount = quanlycuahanggiay.quanlycuahanggiay.AccountBUS.getByMaNhanVien((String) tblQLNV.getModel().getValueAt(row,0));
                    this.add(new ChiTietNhanVienJPanel(currentNhanVien,currentAccount));
                    this.validate();
                    this.repaint();                  
            

        }
    }//GEN-LAST:event_tblQLNVMouseClicked

    private void txtTimKiemHoTenSDTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemHoTenSDTKeyReleased
            getAdvancedSearch();
    }//GEN-LAST:event_txtTimKiemHoTenSDTKeyReleased

    private void txtTimKiemMaNhanVienKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemMaNhanVienKeyReleased
        getAdvancedSearch();
    }//GEN-LAST:event_txtTimKiemMaNhanVienKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbxCaChieu;
    private javax.swing.JCheckBox cbxCaSang;
    private javax.swing.JCheckBox cbxCaToi;
    private javax.swing.JCheckBox cbxDoanhThu;
    private javax.swing.JCheckBox cbxDonHang;
    private javax.swing.JCheckBox cbxKhachHang;
    private javax.swing.JCheckBox cbxKhuyenMai;
    private javax.swing.JCheckBox cbxNhapHang;
    private javax.swing.JCheckBox cbxQuanLyDanhMuc;
    private javax.swing.JCheckBox cbxQuanLyNhanVien;
    private javax.swing.JCheckBox cbxSanPham;
    private javax.swing.JCheckBox cbxThongKe;
    private javax.swing.JCheckBox cbxTongQuan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblDonHang;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JPanel pnlGioiTinh;
    private GUI.Rounded pnlTblQLNV;
    private GUI.Rounded pnlTimKiemHoTenSDT;
    private GUI.Rounded pnlTimKiemMaNhanVien;
    private javax.swing.JPanel pnlTrangThai;
    private javax.swing.JScrollPane scpQLNV;
    private static javax.swing.JTable tblQLNV;
    private javax.swing.JTextField txtTimKiemHoTenSDT;
    private javax.swing.JTextField txtTimKiemMaNhanVien;
    // End of variables declaration//GEN-END:variables
}
