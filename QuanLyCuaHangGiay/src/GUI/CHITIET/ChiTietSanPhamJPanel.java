/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.CHITIET;

import BUS.MauBUS;
import BUS.SizeBUS;
import BUS.ThuongHieuBUS;

import DTO.SanPhamDTO;

import GUI.SanPhamJPanel;
import static GUI.Sweet.SweetFileChooser.FileExists;
import static GUI.Sweet.SweetFileChooser.PATH_SANPHAM;
import static GUI.Sweet.SweetImage.readImageFromFilePath;
import static GUI.Sweet.SweetImage.resizeImage;
import static controller.createChart.createBarChart;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Image;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import static DataThongKe.DataThongKeSanPham.DataChartSanPhamHienTaiMoiSanPham;
import static DataThongKe.DataThongKeSanPham.DataChartSanPhamNamTruocMoiSanPham;
import static DataThongKe.DataThongKeSanPham.DoanhThuSanPham;
import static DataThongKe.DataThongKeSanPham.SanPhamBanRaDoanhThuTuDen;
import static DataThongKe.DataThongKeSanPham.SanPhamDaBanThang;
import static DataThongKe.DataThongKeSanPham.TongDoanhThuSanPham;
import static DataThongKe.DataThongKeSanPham.TongSanPhamBanRa;
import static GUI.SanPhamJPanel.currentSanPham;
import java.util.Date;
import static quanlycuahanggiay.Currency.changeCurrency;


/**
 *
 * @author admin
 */
public class ChiTietSanPhamJPanel extends javax.swing.JPanel {
    
    public static MauBUS currentMau = new MauBUS();
    public static SizeBUS currentSize = new SizeBUS();
    public static ThuongHieuBUS currentThuongHieu = new ThuongHieuBUS();
    public ImageIcon imgAnhDaiDien;
    /**
     * Creates new form ChiTietSanPhamJPanel
     */
    public void showChart(){
        String[] year = {"T1","T2","T3","T4","T5","T6","T7","T8","T9","T10","T11","T12"};
        createBarChart(pnlChart,DataChartSanPhamNamTruocMoiSanPham(currentSanPham.getMaSanPham()),DataChartSanPhamHienTaiMoiSanPham(currentSanPham.getMaSanPham()),year);


    }
    public void setDate(){
        dateTu.setDateFormatString("dd/MM/yyyy");
        dateDen.setDateFormatString("dd/MM/yyyy");
        dateTu.setDate(new Date());
        dateDen.setDate(dateTu.getDate());
        dateTu.setMaxSelectableDate(new Date());
        dateDen.setMaxSelectableDate(new Date());
    }
    public ChiTietSanPhamJPanel(SanPhamDTO currentSanPham) {
        initComponents();
        showChart();
        pnlQuayVe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setDate();
        setData();
    }

    private void setData(){
        lblMaSanPham.setText(currentSanPham.getMaSanPham());
        lblTenSanPham.setText(currentSanPham.getTenSanPham());
        lblDonGia.setText(changeCurrency(currentSanPham.getDonGia())+"đ");
        lblMau.setText(currentMau.getTenMauByMaMau(currentSanPham.getMaMau()));
        lblSoLuong.setText(currentSanPham.getSoLuong());
        lblSize.setText(currentSize.getTenSizeByMaSize(currentSanPham.getMaSize()));
        lblThuongHieu.setText(currentThuongHieu.getTenThuongHieuByMaThuongHieu(currentSanPham.getMaThuongHieu()));
        if (!FileExists(PATH_SANPHAM + currentSanPham.getImgSource())) {
            currentSanPham.setImgSource("default.jpg");
            Image imageScale = readImageFromFilePath(PATH_SANPHAM + currentSanPham.getImgSource(), 180, 170);
            imgAnhDaiDien = new ImageIcon(imageScale);
            lblAnh.setIcon(imgAnhDaiDien);            
        }
        else{
        Image imageScale = readImageFromFilePath(PATH_SANPHAM + currentSanPham.getImgSource(), 180, 170);
        imgAnhDaiDien = new ImageIcon(imageScale);
        lblAnh.setIcon(imgAnhDaiDien);
    }
        lblEdit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblTongSanPhamBanRa.setText(changeCurrency(Integer.toString(TongSanPhamBanRa(currentSanPham.getMaSanPham())[0])));
        lblSanPhamBanRaThangNay.setText(changeCurrency(Integer.toString(SanPhamDaBanThang(currentSanPham.getMaSanPham())[0])));
        lblTongDoanhThu.setText(changeCurrency(Integer.toString(TongDoanhThuSanPham(currentSanPham.getMaSanPham())[0]))+"đ");
        lblSanPhamBanThangTruoc.setText(changeCurrency(Integer.toString(SanPhamDaBanThang(currentSanPham.getMaSanPham())[1])));
        lblDoanhThuThangNay.setText(changeCurrency(Integer.toString(DoanhThuSanPham(currentSanPham.getMaSanPham())[0]))+"đ");
        lblDoanhThuThangTruoc.setText(changeCurrency(Integer.toString(DoanhThuSanPham(currentSanPham.getMaSanPham())[1]))+"đ");
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlQuayVe = new GUI.Rounded();
        lblQuayVe = new javax.swing.JLabel();
        pnlThongTin = new GUI.Rounded();
        lblThongTinHoaDon = new javax.swing.JLabel();
        lblThongTinHoaDon1 = new javax.swing.JLabel();
        pnlThongTinPhieuNhap = new javax.swing.JPanel();
        lblTitleMaNhaCungCap = new javax.swing.JLabel();
        lblTitleNhanVienTaoPhieu = new javax.swing.JLabel();
        lblTitleNgayNhap = new javax.swing.JLabel();
        lblTenSanPham = new javax.swing.JLabel();
        lblMau = new javax.swing.JLabel();
        lblTitleTongTien = new javax.swing.JLabel();
        lblSize = new javax.swing.JLabel();
        lblTitleMaPhieuNhao1 = new javax.swing.JLabel();
        lblTitleMaPhieuNhao2 = new javax.swing.JLabel();
        lblTitleMaPhieuNhao4 = new javax.swing.JLabel();
        lblMaSanPham = new javax.swing.JLabel();
        lblSoLuong = new javax.swing.JLabel();
        lblThuongHieu = new javax.swing.JLabel();
        lblDonGia = new javax.swing.JLabel();
        lblAnh = new javax.swing.JLabel();
        lblEdit = new javax.swing.JLabel();
        pnlChart = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblDoanhThuThangNay = new javax.swing.JLabel();
        lblSanPhamBanRaThangNay = new javax.swing.JLabel();
        lblTongSanPhamBanRa = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        dateTu = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblDoanhThu = new javax.swing.JLabel();
        lblSanPhamBanRa = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dateDen = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblTongDoanhThu = new javax.swing.JLabel();
        lblDoanhThuThangTruoc = new javax.swing.JLabel();
        lblSanPhamBanThangTruoc = new javax.swing.JLabel();

        setBackground(new java.awt.Color(45, 47, 62));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        pnlThongTin.setBackground(new java.awt.Color(45, 47, 62));
        pnlThongTin.setForeground(new java.awt.Color(53, 55, 70));
        pnlThongTin.setPreferredSize(new java.awt.Dimension(920, 750));
        pnlThongTin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblThongTinHoaDon.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        lblThongTinHoaDon.setForeground(new java.awt.Color(210, 48, 44));
        lblThongTinHoaDon.setText("Thông tin sản phẩm");
        pnlThongTin.add(lblThongTinHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 14, -1, -1));

        lblThongTinHoaDon1.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        lblThongTinHoaDon1.setForeground(new java.awt.Color(210, 48, 44));
        lblThongTinHoaDon1.setText("Thống kê sản phẩm");
        pnlThongTin.add(lblThongTinHoaDon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 253, -1, -1));

        pnlThongTinPhieuNhap.setBackground(new java.awt.Color(53, 55, 70));
        pnlThongTinPhieuNhap.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitleMaNhaCungCap.setForeground(new java.awt.Color(192, 192, 192));
        lblTitleMaNhaCungCap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleMaNhaCungCap.setText("Tên sản phẩm:");
        pnlThongTinPhieuNhap.add(lblTitleMaNhaCungCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, -1, 20));

        lblTitleNhanVienTaoPhieu.setForeground(new java.awt.Color(192, 192, 192));
        lblTitleNhanVienTaoPhieu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleNhanVienTaoPhieu.setText("Size:");
        pnlThongTinPhieuNhap.add(lblTitleNhanVienTaoPhieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 130, -1, 20));

        lblTitleNgayNhap.setForeground(new java.awt.Color(192, 192, 192));
        lblTitleNgayNhap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleNgayNhap.setText("Đơn giá:");
        pnlThongTinPhieuNhap.add(lblTitleNgayNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 70, -1, 20));

        lblTenSanPham.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblTenSanPham.setForeground(new java.awt.Color(255, 255, 255));
        lblTenSanPham.setText("Tên sản phẩm");
        pnlThongTinPhieuNhap.add(lblTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 30, -1, 20));

        lblMau.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblMau.setForeground(new java.awt.Color(255, 255, 255));
        lblMau.setText("Hồng");
        pnlThongTinPhieuNhap.add(lblMau, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, -1, 20));

        lblTitleTongTien.setForeground(new java.awt.Color(192, 192, 192));
        lblTitleTongTien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleTongTien.setText("Màu:");
        pnlThongTinPhieuNhap.add(lblTitleTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 100, -1, 20));

        lblSize.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblSize.setForeground(new java.awt.Color(255, 255, 255));
        lblSize.setText("Size");
        pnlThongTinPhieuNhap.add(lblSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 130, 30, 20));

        lblTitleMaPhieuNhao1.setForeground(new java.awt.Color(192, 192, 192));
        lblTitleMaPhieuNhao1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleMaPhieuNhao1.setText("Mã sản phẩm:");
        pnlThongTinPhieuNhap.add(lblTitleMaPhieuNhao1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 100, -1));

        lblTitleMaPhieuNhao2.setForeground(new java.awt.Color(192, 192, 192));
        lblTitleMaPhieuNhao2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleMaPhieuNhao2.setText("Số lượng:");
        pnlThongTinPhieuNhap.add(lblTitleMaPhieuNhao2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 0, 100, -1));

        lblTitleMaPhieuNhao4.setForeground(new java.awt.Color(192, 192, 192));
        lblTitleMaPhieuNhao4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleMaPhieuNhao4.setText("Thương hiệu:");
        pnlThongTinPhieuNhap.add(lblTitleMaPhieuNhao4, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 30, 100, -1));

        lblMaSanPham.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblMaSanPham.setForeground(new java.awt.Color(255, 255, 255));
        lblMaSanPham.setText("12345");
        pnlThongTinPhieuNhap.add(lblMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 0, -1, -1));

        lblSoLuong.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblSoLuong.setForeground(new java.awt.Color(255, 255, 255));
        lblSoLuong.setText("12345");
        pnlThongTinPhieuNhap.add(lblSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 0, -1, -1));

        lblThuongHieu.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblThuongHieu.setForeground(new java.awt.Color(255, 255, 255));
        lblThuongHieu.setText("12345");
        pnlThongTinPhieuNhap.add(lblThuongHieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 30, -1, -1));

        lblDonGia.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblDonGia.setForeground(new java.awt.Color(255, 255, 255));
        lblDonGia.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDonGia.setText("123.456.789");
        pnlThongTinPhieuNhap.add(lblDonGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 70, -1, 20));
        pnlThongTinPhieuNhap.add(lblAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, -4, 180, 170));

        pnlThongTin.add(pnlThongTinPhieuNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 49, 863, -1));

        lblEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.png"))); // NOI18N
        lblEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditMouseClicked(evt);
            }
        });
        pnlThongTin.add(lblEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(855, 14, -1, -1));

        pnlChart.setBackground(new java.awt.Color(53, 55, 70));
        pnlChart.setPreferredSize(new java.awt.Dimension(894, 302));
        pnlChart.setLayout(new javax.swing.BoxLayout(pnlChart, javax.swing.BoxLayout.LINE_AXIS));
        pnlThongTin.add(pnlChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 298, 890, 212));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/checked.png"))); // NOI18N
        jLabel1.setText("Năm nay");
        pnlThongTin.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(724, 233, 91, -1));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/namtruoc.png"))); // NOI18N
        jLabel2.setText("Năm trước");
        pnlThongTin.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(724, 259, 100, -1));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tổng sản phẩm bán ra:");
        pnlThongTin.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 560, -1, -1));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Sản phẩm bán tháng này:");
        pnlThongTin.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 590, -1, -1));

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Doanh thu tháng này:");
        pnlThongTin.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 620, -1, -1));

        lblDoanhThuThangNay.setForeground(new java.awt.Color(218, 52, 52));
        lblDoanhThuThangNay.setText("123");
        pnlThongTin.add(lblDoanhThuThangNay, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 620, -1, -1));

        lblSanPhamBanRaThangNay.setForeground(new java.awt.Color(255, 255, 255));
        lblSanPhamBanRaThangNay.setText("123");
        pnlThongTin.add(lblSanPhamBanRaThangNay, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 590, -1, -1));

        lblTongSanPhamBanRa.setForeground(new java.awt.Color(255, 255, 255));
        lblTongSanPhamBanRa.setText("123456");
        pnlThongTin.add(lblTongSanPhamBanRa, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 560, -1, -1));

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Từ:");
        pnlThongTin.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 560, -1, -1));

        dateTu.setBackground(new java.awt.Color(238, 243, 247));
        dateTu.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateTuPropertyChange(evt);
            }
        });
        pnlThongTin.add(dateTu, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 550, 130, 30));

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Sản phẩm bán ra:");
        pnlThongTin.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 590, -1, -1));

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Doanh thu:");
        pnlThongTin.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 620, -1, -1));

        lblDoanhThu.setForeground(new java.awt.Color(218, 52, 52));
        lblDoanhThu.setText("12345");
        pnlThongTin.add(lblDoanhThu, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 620, -1, -1));

        lblSanPhamBanRa.setForeground(new java.awt.Color(255, 255, 255));
        lblSanPhamBanRa.setText("12345");
        pnlThongTin.add(lblSanPhamBanRa, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 590, -1, -1));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Đến:");
        pnlThongTin.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 560, -1, -1));

        dateDen.setBackground(new java.awt.Color(238, 243, 247));
        dateDen.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateDenPropertyChange(evt);
            }
        });
        pnlThongTin.add(dateDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 550, 130, 30));

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Tổng doanh thu:");
        pnlThongTin.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 560, -1, -1));

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Sản phẩm bán tháng trước:");
        pnlThongTin.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 590, -1, -1));

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Doanh thu tháng trước:");
        pnlThongTin.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 620, -1, -1));

        lblTongDoanhThu.setForeground(new java.awt.Color(255, 0, 0));
        lblTongDoanhThu.setText("12345");
        pnlThongTin.add(lblTongDoanhThu, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 560, -1, -1));

        lblDoanhThuThangTruoc.setForeground(new java.awt.Color(218, 52, 52));
        lblDoanhThuThangTruoc.setText("12345");
        pnlThongTin.add(lblDoanhThuThangTruoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 620, -1, -1));

        lblSanPhamBanThangTruoc.setForeground(new java.awt.Color(255, 255, 255));
        lblSanPhamBanThangTruoc.setText("12345");
        pnlThongTin.add(lblSanPhamBanThangTruoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 590, -1, -1));

        add(pnlThongTin, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 69, 890, 677));
    }// </editor-fold>//GEN-END:initComponents

    private void pnlQuayVeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlQuayVeMouseClicked
        if(SwingUtilities.isLeftMouseButton(evt)){
        this.removeAll();
        this.setLayout(new BorderLayout());
        this.add(new SanPhamJPanel());
        this.validate();
        this.repaint();
        }
    }//GEN-LAST:event_pnlQuayVeMouseClicked

    private void lblEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditMouseClicked
        if(SwingUtilities.isLeftMouseButton(evt)){
            this.removeAll();
            this.setLayout(new BorderLayout());
            this.add(new SuaSanPhamJPanel(currentSanPham));
            this.validate();
            this.repaint();
        }
    }//GEN-LAST:event_lblEditMouseClicked

    private void dateTuPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateTuPropertyChange
        dateDen.setDate(dateTu.getDate());
        dateDen.setMinSelectableDate(dateTu.getDate());
    }//GEN-LAST:event_dateTuPropertyChange

    private void dateDenPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateDenPropertyChange
        lblSanPhamBanRa.setText(changeCurrency(Integer.toString(SanPhamBanRaDoanhThuTuDen(currentSanPham.getMaSanPham(),dateTu.getDate(),dateDen.getDate())[0])));
        lblDoanhThu.setText(changeCurrency(Integer.toString(SanPhamBanRaDoanhThuTuDen(currentSanPham.getMaSanPham(),dateTu.getDate(),dateDen.getDate())[1]))+"đ");
        
    }//GEN-LAST:event_dateDenPropertyChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser dateDen;
    private com.toedter.calendar.JDateChooser dateTu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JLabel lblDoanhThu;
    private javax.swing.JLabel lblDoanhThuThangNay;
    private javax.swing.JLabel lblDoanhThuThangTruoc;
    private javax.swing.JLabel lblDonGia;
    private javax.swing.JLabel lblEdit;
    private javax.swing.JLabel lblMaSanPham;
    private javax.swing.JLabel lblMau;
    private javax.swing.JLabel lblQuayVe;
    private javax.swing.JLabel lblSanPhamBanRa;
    private javax.swing.JLabel lblSanPhamBanRaThangNay;
    private javax.swing.JLabel lblSanPhamBanThangTruoc;
    private javax.swing.JLabel lblSize;
    private javax.swing.JLabel lblSoLuong;
    private javax.swing.JLabel lblTenSanPham;
    private javax.swing.JLabel lblThongTinHoaDon;
    private javax.swing.JLabel lblThongTinHoaDon1;
    private javax.swing.JLabel lblThuongHieu;
    private javax.swing.JLabel lblTitleMaNhaCungCap;
    private javax.swing.JLabel lblTitleMaPhieuNhao1;
    private javax.swing.JLabel lblTitleMaPhieuNhao2;
    private javax.swing.JLabel lblTitleMaPhieuNhao4;
    private javax.swing.JLabel lblTitleNgayNhap;
    private javax.swing.JLabel lblTitleNhanVienTaoPhieu;
    private javax.swing.JLabel lblTitleTongTien;
    private javax.swing.JLabel lblTongDoanhThu;
    private javax.swing.JLabel lblTongSanPhamBanRa;
    private javax.swing.JPanel pnlChart;
    private GUI.Rounded pnlQuayVe;
    private GUI.Rounded pnlThongTin;
    private javax.swing.JPanel pnlThongTinPhieuNhap;
    // End of variables declaration//GEN-END:variables
}
