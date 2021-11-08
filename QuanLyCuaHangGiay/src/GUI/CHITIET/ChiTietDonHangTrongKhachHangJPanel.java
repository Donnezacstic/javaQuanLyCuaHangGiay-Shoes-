/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.CHITIET;
import DTO.ChiTietDonHangDTO;
import DTO.ChiTietKhuyenMaiDTO;
import DTO.DonHangDTO;
import DTO.SanPhamDTO;
import DTO.SizeDTO;
import GUI.DonHangJPanel;
import GUI.RenderTable;
import GUI.THEMMOI.TaoDonHangJPanel;
import static GUI.TongQuanJPanel.customTable;
import static GUI.TongQuanJPanel.setJTableColumnsWidth;
import java.awt.BorderLayout;
import java.awt.Cursor;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static GUI.CHITIET.ChiTietKhachHangJPanel.currentDonHang;
import static GUI.KhachHangJPanel.currentKhachHang;
import static GUI.DonHangJPanel.currentChiTietDonHangList;
import static GUI.DonHangJPanel.getTrangThai;
import GUI.Sweet.SweetAlert;
import GUI.Sweet.SweetFileChooser;
import GUI.THEMMOI.SuaDonHangJPanel;
import IO.PDF.HoaDonBanHangPDF;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import static quanlycuahanggiay.DateTime.TimestampToDateString;
import static quanlycuahanggiay.Currency.changeCurrency;
/**
 *
 * @author admin
 */
public class ChiTietDonHangTrongKhachHangJPanel extends javax.swing.JPanel {
    Object[] columnNames ={"Sản phẩm","Số lượng","Đơn giá","Size","Màu","Tạm tính"};
    public static DefaultTableModel listTableModel;
    public static ArrayList<ChiTietDonHangDTO> SanPhamList = new ArrayList<ChiTietDonHangDTO>();
    
   
    /**
     * Creates new form SanPhamJPanel
     */
    public void ShowTable(JTable table,Object[] columnName){
        listTableModel = new DefaultTableModel(columnNames,0);
        table.setDefaultRenderer(Object.class, new RenderTable());
        fetchAll();
        //Object columnNames[] = columnName;
        //DefaultTableModel listTableModel = new DefaultTableModel(columnNames,0);
        
        //Object tensanpham[] ={"Nike","Nike","Nike","Nike ","Adidas","Nike Air","Bitis Hunter","Bitis","Nike ","Adidas","Nike Air","Bitis Hunter","Bitis","Nike ","Adidas","Nike Air","Bitis Hunter","Bitis","Nike ","Adidas"};
        //Object soluong[] = {500,200,100,500,300,500,200,100,500,300,500,200,100,500,300,500,200,100,500,300};
        //Object dongia[] = {"3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000"};
        //Object size[] = {9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9};
        //Object mau[] = {"Hồng","Hồng","Hồng","Hồng","Hồng","Hồng","Hồng","Hồng","Hồng","Hồng","Hồng","Hồng","Hồng","Hồng","Hồng","Hồng","Hồng","Hồng","Hồng","Hồng"};
        //Object tamtinh[] = {"3.600.000đ","3.600.000đ","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000đ","3.600.000đ","3.600.000đ"};
        //for(int i=0 ; i<tensanpham.length ; i++){
        //        Object row[] = {tensanpham[i],dongia[i],soluong[i],size[i],mau[i],tamtinh[i]};
        //        listTableModel.addRow(row);
        //    }
        //table.setModel(listTableModel); 
    }
    
    public static void fetchAll(){
        listTableModel.setRowCount(0);
        quanlycuahanggiay.quanlycuahanggiay.ChiTietDonHangBUS.ChiTietDonHangList.forEach(ChiTietDonHang ->{
            //int i=0;
            if(currentDonHang.getMaDonHang().equals(ChiTietDonHang.getMaDonHang())){
                ArrayList<SanPhamDTO> arraySearch = new ArrayList<SanPhamDTO>();
            SanPhamDTO SanPham = quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.getByMaSanPham(ChiTietDonHang.getMaSanPham());
            String Size = quanlycuahanggiay.quanlycuahanggiay.SizeBUS.getTenSizeByMaSize(SanPham.getMaSize());
            String Mau = quanlycuahanggiay.quanlycuahanggiay.MauBUS.getTenMauByMaMau(SanPham.getMaMau());
            Vector row = new Vector();
            //row.add(currentChiTietDonHang.getMaDonHang());
            row.add(SanPham.getTenSanPham());
            row.add(ChiTietDonHang.getSoLuong());
            row.add(changeCurrency(ChiTietDonHang.getDonGia())+"đ");
            row.add(Size);
            row.add(Mau);
            row.add(changeCurrency(ChiTietDonHang.getThanhTien())+"đ");
            listTableModel.addRow(row);
            }
        });
        
        tblSanPham.setModel(listTableModel);
    }
    
    public ChiTietDonHangTrongKhachHangJPanel() {
        initComponents();
        customTable(tblSanPham,scpSanPham);
        ShowTable(tblSanPham,columnNames);
        setJTableColumnsWidth(tblSanPham,890,35,15,10,10,10,20);
        pnlQuayVe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblEdit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setData();
    }
    
    
    public void setData(){
        lblMaDonHang.setText(currentDonHang.getMaDonHang());
        lblNgayDat.setText(TimestampToDateString(currentDonHang.getNgayXuat(),1));
        lblMaGiamGia.setText(currentDonHang.getMaCode());
        lblTrangThai.setText(getTrangThai(currentDonHang.getTrangThai()));
        lblHoTen.setText(currentKhachHang.getHoTen());
        lblSoDienThoai.setText(currentKhachHang.getSoDienThoai());
        lblEmail.setText(currentKhachHang.getEmail());
        lblDiaChi.setText(currentKhachHang.getDiaChi());       
        //String TienTamTinh = TamTinh();
        //lblTamTinh.setText(changeCurrency(TienTamTinh)+"đ");
        lblTamTinh.setText(changeCurrency(currentDonHang.getTongTien())+"đ");
        //String TienGiamGia = GiamGia(currentDonHang.getMaCode(), TienTamTinh);
        lblGiamGia.setText(changeCurrency(currentDonHang.getGiamGia())+"đ");
        lblPhiVanChuyen.setText(changeCurrency("30000")+"đ");
        
        lblTongTien.setText(changeCurrency(currentDonHang.getTongTien())+"đ");
        /*
        lblMaDonHang.setText(currentDonHang.getMaDonHang());
        lblNgayDat.setText(TimestampToDateString(currentDonHang.getNgayXuat(),1));
        lblMaGiamGia.setText(currentDonHang.getMaCode());
        lblTrangThai.setText(getTrangThai(currentDonHang.getTrangThai()));
        lblHoTen.setText(currentKhachHang.getHoTen());
        lblSoDienThoai.setText(currentKhachHang.getSoDienThoai());
        lblEmail.setText(currentKhachHang.getEmail());
        lblDiaChi.setText(currentKhachHang.getDiaChi());
        lblTamTinh.setText(changeCurrency(currentDonHang.getMaDonHang())+"đ");
        lblGiamGia.setText(changeCurrency(currentDonHang.getGiamGia())+"đ");
        lblPhiVanChuyen.setText(changeCurrency("30000"+"đ"));
        lblTongTien.setText(changeCurrency(Tong(currentDonHang))+"đ");
        */
    }

    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlQuayVe = new GUI.Rounded();
        lblQuayVe = new javax.swing.JLabel();
        pnlThongTin = new GUI.Rounded();
        lblThongTinHoaDon = new javax.swing.JLabel();
        scpSanPham = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable(){
            public boolean isCellEditable ( int row, int col)
            {
                return false;
            }

        };
        lblThongTinHoaDon1 = new javax.swing.JLabel();
        pnlTongTien = new javax.swing.JPanel();
        lblTitleTamTinh = new javax.swing.JLabel();
        lblTitleMaGiamGia = new javax.swing.JLabel();
        lblTitlePhiVanChuyen = new javax.swing.JLabel();
        lblTitleTong = new javax.swing.JLabel();
        lblTamTinh = new javax.swing.JLabel();
        lblGiamGia = new javax.swing.JLabel();
        lblPhiVanChuyen = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        pnlThongTinHoaDon = new javax.swing.JPanel();
        lblTitleNgayDat = new javax.swing.JLabel();
        lblTitleSoDienThoai = new javax.swing.JLabel();
        lblTitleDiaChi = new javax.swing.JLabel();
        lblTitleEmail = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblTrangThai = new javax.swing.JLabel();
        lblSoDienThoai = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lblDiaChi = new javax.swing.JTextArea();
        lblTitleHoTen = new javax.swing.JLabel();
        lblTitleTrangThai = new javax.swing.JLabel();
        lblTitleMaDonHang = new javax.swing.JLabel();
        lblTitleMaGiamGia1 = new javax.swing.JLabel();
        lblHoTen = new javax.swing.JLabel();
        lblMaDonHang = new javax.swing.JLabel();
        lblNgayDat = new javax.swing.JLabel();
        lblMaGiamGia = new javax.swing.JLabel();
        pnlXuatHoaDon = new javax.swing.JPanel();
        lblXuatHoaDon = new javax.swing.JLabel();
        lblEdit = new javax.swing.JLabel();

        setBackground(new java.awt.Color(45, 47, 62));

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

        pnlThongTin.setBackground(new java.awt.Color(45, 47, 62));
        pnlThongTin.setForeground(new java.awt.Color(53, 55, 70));
        pnlThongTin.setPreferredSize(new java.awt.Dimension(920, 750));

        lblThongTinHoaDon.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        lblThongTinHoaDon.setForeground(new java.awt.Color(210, 48, 44));
        lblThongTinHoaDon.setText("Thông tin hóa đơn");

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

        lblThongTinHoaDon1.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        lblThongTinHoaDon1.setForeground(new java.awt.Color(210, 48, 44));
        lblThongTinHoaDon1.setText("Sản phẩm");

        pnlTongTien.setBackground(new java.awt.Color(53, 55, 70));

        lblTitleTamTinh.setBackground(new java.awt.Color(192, 192, 192));
        lblTitleTamTinh.setForeground(new java.awt.Color(192, 192, 192));
        lblTitleTamTinh.setText("Tạm tính");

        lblTitleMaGiamGia.setBackground(new java.awt.Color(192, 192, 192));
        lblTitleMaGiamGia.setForeground(new java.awt.Color(192, 192, 192));
        lblTitleMaGiamGia.setText("Mã giảm giá");

        lblTitlePhiVanChuyen.setBackground(new java.awt.Color(192, 192, 192));
        lblTitlePhiVanChuyen.setForeground(new java.awt.Color(192, 192, 192));
        lblTitlePhiVanChuyen.setText("Phí vận chuyển");

        lblTitleTong.setForeground(new java.awt.Color(255, 255, 255));
        lblTitleTong.setText("Tổng");

        lblTamTinh.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblTamTinh.setForeground(new java.awt.Color(255, 255, 255));
        lblTamTinh.setText("3.600.000đ");

        lblGiamGia.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblGiamGia.setForeground(new java.awt.Color(255, 255, 255));
        lblGiamGia.setText("3.600.000đ");

        lblPhiVanChuyen.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblPhiVanChuyen.setForeground(new java.awt.Color(255, 255, 255));
        lblPhiVanChuyen.setText("3.600.000đ");

        lblTongTien.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(210, 48, 44));
        lblTongTien.setText("3.600.000đ");

        javax.swing.GroupLayout pnlTongTienLayout = new javax.swing.GroupLayout(pnlTongTien);
        pnlTongTien.setLayout(pnlTongTienLayout);
        pnlTongTienLayout.setHorizontalGroup(
            pnlTongTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTongTienLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTongTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTongTienLayout.createSequentialGroup()
                        .addGroup(pnlTongTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitleMaGiamGia)
                            .addComponent(lblTitlePhiVanChuyen)
                            .addComponent(lblTitleTong))
                        .addGap(48, 48, 48)
                        .addGroup(pnlTongTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTongTien)
                            .addComponent(lblGiamGia)
                            .addComponent(lblPhiVanChuyen))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlTongTienLayout.createSequentialGroup()
                        .addComponent(lblTitleTamTinh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTamTinh)))
                .addContainerGap())
        );
        pnlTongTienLayout.setVerticalGroup(
            pnlTongTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTongTienLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlTongTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitleTamTinh)
                    .addComponent(lblTamTinh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTongTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitleMaGiamGia)
                    .addComponent(lblGiamGia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTongTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitlePhiVanChuyen)
                    .addComponent(lblPhiVanChuyen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTongTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitleTong)
                    .addComponent(lblTongTien)))
        );

        pnlThongTinHoaDon.setBackground(new java.awt.Color(53, 55, 70));
        pnlThongTinHoaDon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitleNgayDat.setForeground(new java.awt.Color(192, 192, 192));
        lblTitleNgayDat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleNgayDat.setText("Ngày đặt:");
        pnlThongTinHoaDon.add(lblTitleNgayDat, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 30, 70, -1));

        lblTitleSoDienThoai.setForeground(new java.awt.Color(192, 192, 192));
        lblTitleSoDienThoai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleSoDienThoai.setText("Số điện thoại:");
        pnlThongTinHoaDon.add(lblTitleSoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        lblTitleDiaChi.setForeground(new java.awt.Color(192, 192, 192));
        lblTitleDiaChi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleDiaChi.setText("Địa chỉ:");
        pnlThongTinHoaDon.add(lblTitleDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        lblTitleEmail.setForeground(new java.awt.Color(192, 192, 192));
        lblTitleEmail.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleEmail.setText("Email:");
        pnlThongTinHoaDon.add(lblTitleEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        lblEmail.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(255, 255, 255));
        lblEmail.setText("abcxyz@gmail.com");
        pnlThongTinHoaDon.add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, -1, -1));

        lblTrangThai.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblTrangThai.setForeground(new java.awt.Color(255, 255, 255));
        lblTrangThai.setText("Đã xác nhận");
        pnlThongTinHoaDon.add(lblTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 100, -1, -1));

        lblSoDienThoai.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblSoDienThoai.setForeground(new java.awt.Color(255, 255, 255));
        lblSoDienThoai.setText("0123456789");
        pnlThongTinHoaDon.add(lblSoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, -1, -1));

        jScrollPane1.setBackground(new java.awt.Color(53, 55, 70));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        lblDiaChi.setBackground(new java.awt.Color(53, 55, 70));
        lblDiaChi.setColumns(20);
        lblDiaChi.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblDiaChi.setForeground(new java.awt.Color(255, 255, 255));
        lblDiaChi.setLineWrap(true);
        lblDiaChi.setRows(5);
        lblDiaChi.setText("120 Công Chúa Ngọc Hân, Phường 12\nQuận 11, TP Hồ Chí Minh\n");
        lblDiaChi.setBorder(null);
        lblDiaChi.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        lblDiaChi.setEnabled(false);
        jScrollPane1.setViewportView(lblDiaChi);

        pnlThongTinHoaDon.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, -1, 60));

        lblTitleHoTen.setForeground(new java.awt.Color(192, 192, 192));
        lblTitleHoTen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleHoTen.setText("Họ tên:");
        pnlThongTinHoaDon.add(lblTitleHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 50, -1));

        lblTitleTrangThai.setForeground(new java.awt.Color(192, 192, 192));
        lblTitleTrangThai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleTrangThai.setText("Trạng thái:");
        pnlThongTinHoaDon.add(lblTitleTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 100, 80, -1));

        lblTitleMaDonHang.setForeground(new java.awt.Color(192, 192, 192));
        lblTitleMaDonHang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleMaDonHang.setText("Mã đơn hàng:");
        pnlThongTinHoaDon.add(lblTitleMaDonHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 0, 90, -1));

        lblTitleMaGiamGia1.setForeground(new java.awt.Color(192, 192, 192));
        lblTitleMaGiamGia1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleMaGiamGia1.setText("Mã giảm giá:");
        pnlThongTinHoaDon.add(lblTitleMaGiamGia1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 60, 80, -1));

        lblHoTen.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblHoTen.setForeground(new java.awt.Color(255, 255, 255));
        lblHoTen.setText("Phan Công Sơn");
        pnlThongTinHoaDon.add(lblHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, -1, -1));

        lblMaDonHang.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblMaDonHang.setForeground(new java.awt.Color(255, 255, 255));
        lblMaDonHang.setText("12345");
        pnlThongTinHoaDon.add(lblMaDonHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 0, -1, -1));

        lblNgayDat.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblNgayDat.setForeground(new java.awt.Color(255, 255, 255));
        lblNgayDat.setText("01/05/2000");
        pnlThongTinHoaDon.add(lblNgayDat, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 30, -1, -1));

        lblMaGiamGia.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblMaGiamGia.setForeground(new java.awt.Color(210, 48, 44));
        lblMaGiamGia.setText("MAGIAMGIA2020");
        pnlThongTinHoaDon.add(lblMaGiamGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 60, -1, -1));

        pnlXuatHoaDon.setBackground(new java.awt.Color(210, 48, 44));

        lblXuatHoaDon.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblXuatHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        lblXuatHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblXuatHoaDon.setText("Xuất hóa đơn");
        lblXuatHoaDon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblXuatHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblXuatHoaDonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlXuatHoaDonLayout = new javax.swing.GroupLayout(pnlXuatHoaDon);
        pnlXuatHoaDon.setLayout(pnlXuatHoaDonLayout);
        pnlXuatHoaDonLayout.setHorizontalGroup(
            pnlXuatHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblXuatHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
        );
        pnlXuatHoaDonLayout.setVerticalGroup(
            pnlXuatHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblXuatHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        pnlThongTinHoaDon.add(pnlXuatHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 10, -1, -1));

        lblEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.png"))); // NOI18N
        lblEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlThongTinLayout = new javax.swing.GroupLayout(pnlThongTin);
        pnlThongTin.setLayout(pnlThongTinLayout);
        pnlThongTinLayout.setHorizontalGroup(
            pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addComponent(lblThongTinHoaDon1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongTinLayout.createSequentialGroup()
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlThongTinLayout.createSequentialGroup()
                                .addComponent(lblThongTinHoaDon)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblEdit))
                            .addComponent(pnlThongTinHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 861, Short.MAX_VALUE))
                        .addGap(17, 17, 17))
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pnlTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(scpSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 861, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 19, Short.MAX_VALUE))))
        );
        pnlThongTinLayout.setVerticalGroup(
            pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblThongTinHoaDon)
                    .addComponent(lblEdit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlThongTinHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblThongTinHoaDon1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scpSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlQuayVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 890, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(pnlQuayVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(pnlThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int column = tblSanPham.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/tblSanPham.getRowHeight();

        if(row < tblSanPham.getRowCount() && row >= 0 && column < tblSanPham.getColumnCount() && column >= 0){

            for(int i=0;i<tblSanPham.getColumnCount();i++){
                System.out.println(tblSanPham.getModel().getValueAt(row,i));
            }
          
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void pnlQuayVeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlQuayVeMouseClicked
                    this.removeAll();
                    this.setLayout(new BorderLayout());
                    this.add(new ChiTietKhachHangJPanel());
                    this.validate();
                    this.repaint();        // TODO add your handling code here:
    }//GEN-LAST:event_pnlQuayVeMouseClicked

    private void lblEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditMouseClicked
                    this.removeAll();
                    this.setLayout(new BorderLayout());
                    this.add(new SuaDonHangJPanel(false));
                    this.validate();
                    this.repaint();         
    }//GEN-LAST:event_lblEditMouseClicked

    private void lblXuatHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblXuatHoaDonMouseClicked
        try {
            String path = SweetFileChooser.PDFFileChooser();
            if(path==null){
            SweetAlert.showSweetAlert(new JFrame(), "Lỗi", "Vui lòng chọn file phù hợp", 1);
                return;
            }
            HoaDonBanHangPDF.exportPdf(path, DonHangJPanel.currentDonHang, quanlycuahanggiay.quanlycuahanggiay.ChiTietDonHangBUS.getList(currentDonHang.getMaDonHang()));
            SweetAlert.showSweetAlert(new JFrame(), "Hoàn tất", "Xuất dữ liệu thành công", 0);
        } catch (IOException ex) {
            Logger.getLogger(ChiTietDonHangTrongKhachHangJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblXuatHoaDonMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea lblDiaChi;
    private javax.swing.JLabel lblEdit;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGiamGia;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JLabel lblMaDonHang;
    private javax.swing.JLabel lblMaGiamGia;
    private javax.swing.JLabel lblNgayDat;
    private javax.swing.JLabel lblPhiVanChuyen;
    private javax.swing.JLabel lblQuayVe;
    private javax.swing.JLabel lblSoDienThoai;
    private javax.swing.JLabel lblTamTinh;
    private javax.swing.JLabel lblThongTinHoaDon;
    private javax.swing.JLabel lblThongTinHoaDon1;
    private javax.swing.JLabel lblTitleDiaChi;
    private javax.swing.JLabel lblTitleEmail;
    private javax.swing.JLabel lblTitleHoTen;
    private javax.swing.JLabel lblTitleMaDonHang;
    private javax.swing.JLabel lblTitleMaGiamGia;
    private javax.swing.JLabel lblTitleMaGiamGia1;
    private javax.swing.JLabel lblTitleNgayDat;
    private javax.swing.JLabel lblTitlePhiVanChuyen;
    private javax.swing.JLabel lblTitleSoDienThoai;
    private javax.swing.JLabel lblTitleTamTinh;
    private javax.swing.JLabel lblTitleTong;
    private javax.swing.JLabel lblTitleTrangThai;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JLabel lblXuatHoaDon;
    private GUI.Rounded pnlQuayVe;
    private GUI.Rounded pnlThongTin;
    private javax.swing.JPanel pnlThongTinHoaDon;
    private javax.swing.JPanel pnlTongTien;
    private javax.swing.JPanel pnlXuatHoaDon;
    private javax.swing.JScrollPane scpSanPham;
    private static javax.swing.JTable tblSanPham;
    // End of variables declaration//GEN-END:variables
}
