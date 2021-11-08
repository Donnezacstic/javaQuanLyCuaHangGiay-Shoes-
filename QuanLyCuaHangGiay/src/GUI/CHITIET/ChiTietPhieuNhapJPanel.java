package GUI.CHITIET;

import BUS.MauBUS;
import BUS.SanPhamBUS;
import BUS.SizeBUS;
import DTO.ChiTietPhieuNhapDTO;
import DTO.PhieuNhapDTO;
import DTO.SanPhamDTO;
import GUI.PhieuNhapHangJPanel;
import GUI.RenderTable;
import GUI.Sweet.SweetAlert;
import GUI.Sweet.SweetFileChooser;
import static GUI.TongQuanJPanel.customTable;
import static GUI.TongQuanJPanel.setJTableColumnsWidth;
import IO.PDF.HoaDonNhapHangPDF;
import com.itextpdf.text.DocumentException;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import static quanlycuahanggiay.Currency.changeCurrency;
import quanlycuahanggiay.DateTime;
import static quanlycuahanggiay.Currency.changeCurrency;
public class ChiTietPhieuNhapJPanel extends javax.swing.JPanel {
    public static ChiTietPhieuNhapDTO currentChiTietPhieuNhap;
    public static PhieuNhapDTO currentPhieuNhap;
    public static SanPhamDTO currentSanPham;
    public static DefaultTableModel listTableModel;
    public static ArrayList<ChiTietPhieuNhapDTO> DanhSachChiTietPhieuNhap;
    private static int tong = 0;
    Object[] columnNames ={"Mã sản phẩm","Tên sản phẩm","Đơn giá","Số lượng","Size","Màu","Thành tiền"};
 
    private void setData(){
        lblMaPhieuNhap.setText(PhieuNhapHangJPanel.currentPhieuNhap.getMaPhieuNhap());
        lblMaNhaCungCap.setText(PhieuNhapHangJPanel.currentPhieuNhap.getMaNhaCungCap());
        lblNgayNhap.setText(DateTime.TimestampToDateString(PhieuNhapHangJPanel.currentPhieuNhap.getNgayNhap(),1));

        lblTongTien.setText(changeCurrency(PhieuNhapHangJPanel.currentPhieuNhap.getTongTien()));

        lblTongTien.setText(changeCurrency(PhieuNhapHangJPanel.currentPhieuNhap.getTongTien())+"đ");

        lblNhanVienTaoPhieu.setText(quanlycuahanggiay.quanlycuahanggiay.NhanVienBUS.getTenNhanVienByMaNhanVien(currentPhieuNhap.getMaNhanVien()));
    }
    public ChiTietPhieuNhapJPanel(PhieuNhapDTO PhieuNhap) {
        initComponents();
        customTable(tblPhieuNhapHang,scpPhieuNhapHang);
        setJTableColumnsWidth(tblPhieuNhapHang,890,15,30,15,10,5,15,15);  
        pnlQuayVe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.currentPhieuNhap = PhieuNhap; 
        setData();
        ShowTable(tblPhieuNhapHang,columnNames,PhieuNhap.getMaPhieuNhap());
        tong=0;
    }
    public void ShowTable(JTable table,Object[] columnName,String maphieunhap){
        listTableModel = new DefaultTableModel(columnNames,0);
        table.setDefaultRenderer(Object.class, new RenderTable());
        fetchAll(maphieunhap);
    }
    public static void fetchAll(String maphieunhap)
    {
        listTableModel.setRowCount(0);
        DanhSachChiTietPhieuNhap = quanlycuahanggiay.quanlycuahanggiay.ChiTietPhieuNhapBUS.getChiTietPhieuNhapByMaPhieuNhap(currentPhieuNhap.getMaPhieuNhap());
        DanhSachChiTietPhieuNhap.forEach(ChiTietPhieuNhap ->{
            Vector row = new Vector();
            currentSanPham = quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.getSanPhamByMaSanPham(ChiTietPhieuNhap.getMaSanPham());
            row.add(ChiTietPhieuNhap.getMaSanPham());
            row.add(currentSanPham.getTenSanPham());

            row.add(changeCurrency(ChiTietPhieuNhap.getDonGia()));
            row.add(ChiTietPhieuNhap.getSoLuong());
            row.add(quanlycuahanggiay.quanlycuahanggiay.SizeBUS.getTenSizeByMaSize(currentSanPham.getMaSize()));
            row.add(quanlycuahanggiay.quanlycuahanggiay.MauBUS.getTenMauByMaMau(currentSanPham.getMaMau()));
            row.add(changeCurrency(ChiTietPhieuNhap.getThanhTien()));

            row.add(changeCurrency(ChiTietPhieuNhap.getDonGia())+"đ");
            row.add(ChiTietPhieuNhap.getSoLuong());

            listTableModel.addRow(row);
            tong += Integer.parseInt(ChiTietPhieuNhap.getThanhTien());          
        });
        tblPhieuNhapHang.setModel(listTableModel);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlQuayVe = new GUI.Rounded();
        lblQuayVe = new javax.swing.JLabel();
        pnlThongTin = new GUI.Rounded();
        lblThongTinHoaDon = new javax.swing.JLabel();
        scpPhieuNhapHang = new javax.swing.JScrollPane();
        tblPhieuNhapHang = new javax.swing.JTable(){
            public boolean isCellEditable ( int row, int col)
            {
                return false;
            }

        };
        lblThongTinHoaDon1 = new javax.swing.JLabel();
        pnlTongTien = new javax.swing.JPanel();
        pnlThongTinPhieuNhap = new javax.swing.JPanel();
        lblTitleMaNhaCungCap = new javax.swing.JLabel();
        lblTitleNhanVienTaoPhieu = new javax.swing.JLabel();
        lblTitleNgayNhap = new javax.swing.JLabel();
        lblNgayNhap = new javax.swing.JLabel();
        lblMaNhaCungCap = new javax.swing.JLabel();
        lblTitleMaPhieuNhao = new javax.swing.JLabel();
        lblMaPhieuNhap = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        lblTitleTongTien = new javax.swing.JLabel();
        lblNhanVienTaoPhieu = new javax.swing.JLabel();
        pnlXuatPhieuNhap = new javax.swing.JPanel();
        lblXuatPhieuNhap = new javax.swing.JLabel();

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
        lblThongTinHoaDon.setText("Thông tin phiếu nhập hàng");

        scpPhieuNhapHang.setBackground(new java.awt.Color(53, 55, 70));
        scpPhieuNhapHang.setForeground(new java.awt.Color(53, 55, 70));

        tblPhieuNhapHang.setAutoCreateRowSorter(true);
        tblPhieuNhapHang.setBackground(new java.awt.Color(53, 55, 70));
        tblPhieuNhapHang.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tblPhieuNhapHang.setForeground(new java.awt.Color(255, 255, 255));
        tblPhieuNhapHang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblPhieuNhapHang.setFocusable(false);
        tblPhieuNhapHang.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblPhieuNhapHang.setRowHeight(25);
        tblPhieuNhapHang.setSelectionBackground(new java.awt.Color(72, 74, 89));
        scpPhieuNhapHang.setViewportView(tblPhieuNhapHang);

        lblThongTinHoaDon1.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        lblThongTinHoaDon1.setForeground(new java.awt.Color(210, 48, 44));
        lblThongTinHoaDon1.setText("Chi tiết phiếu nhập hàng");

        pnlTongTien.setBackground(new java.awt.Color(53, 55, 70));

        javax.swing.GroupLayout pnlTongTienLayout = new javax.swing.GroupLayout(pnlTongTien);
        pnlTongTien.setLayout(pnlTongTienLayout);
        pnlTongTienLayout.setHorizontalGroup(
            pnlTongTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 214, Short.MAX_VALUE)
        );
        pnlTongTienLayout.setVerticalGroup(
            pnlTongTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 108, Short.MAX_VALUE)
        );

        pnlThongTinPhieuNhap.setBackground(new java.awt.Color(53, 55, 70));
        pnlThongTinPhieuNhap.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitleMaNhaCungCap.setForeground(new java.awt.Color(192, 192, 192));
        lblTitleMaNhaCungCap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleMaNhaCungCap.setText("Mã nhà cung cấp:");
        pnlThongTinPhieuNhap.add(lblTitleMaNhaCungCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, -1, -1));

        lblTitleNhanVienTaoPhieu.setForeground(new java.awt.Color(192, 192, 192));
        lblTitleNhanVienTaoPhieu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleNhanVienTaoPhieu.setText("Người tạo phiếu:");
        pnlThongTinPhieuNhap.add(lblTitleNhanVienTaoPhieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, -1, -1));

        lblTitleNgayNhap.setForeground(new java.awt.Color(192, 192, 192));
        lblTitleNgayNhap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleNgayNhap.setText("Ngày nhập:");
        pnlThongTinPhieuNhap.add(lblTitleNgayNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, -1, -1));

        lblNgayNhap.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblNgayNhap.setForeground(new java.awt.Color(255, 255, 255));
        lblNgayNhap.setText("01/05/2000");
        pnlThongTinPhieuNhap.add(lblNgayNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, -1, -1));

        lblMaNhaCungCap.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblMaNhaCungCap.setForeground(new java.awt.Color(255, 255, 255));
        lblMaNhaCungCap.setText("12345");
        pnlThongTinPhieuNhap.add(lblMaNhaCungCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, -1, -1));

        lblTitleMaPhieuNhao.setForeground(new java.awt.Color(192, 192, 192));
        lblTitleMaPhieuNhao.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleMaPhieuNhao.setText("Mã phiếu nhập:");
        pnlThongTinPhieuNhap.add(lblTitleMaPhieuNhao, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 100, -1));

        lblMaPhieuNhap.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblMaPhieuNhap.setForeground(new java.awt.Color(255, 255, 255));
        lblMaPhieuNhap.setText("12345");
        pnlThongTinPhieuNhap.add(lblMaPhieuNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, -1, -1));

        lblTongTien.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(210, 48, 44));
        lblTongTien.setText("123456789đ");
        pnlThongTinPhieuNhap.add(lblTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, -1, -1));

        lblTitleTongTien.setForeground(new java.awt.Color(192, 192, 192));
        lblTitleTongTien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleTongTien.setText("Tổng tiền:");
        pnlThongTinPhieuNhap.add(lblTitleTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, -1, -1));

        lblNhanVienTaoPhieu.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblNhanVienTaoPhieu.setForeground(new java.awt.Color(255, 255, 255));
        lblNhanVienTaoPhieu.setText("NV001");
        pnlThongTinPhieuNhap.add(lblNhanVienTaoPhieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 380, -1));

        pnlXuatPhieuNhap.setBackground(new java.awt.Color(210, 48, 44));

        lblXuatPhieuNhap.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblXuatPhieuNhap.setForeground(new java.awt.Color(255, 255, 255));
        lblXuatPhieuNhap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblXuatPhieuNhap.setText("Xuất Phiếu Nhập");
        lblXuatPhieuNhap.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblXuatPhieuNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblXuatPhieuNhapMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlXuatPhieuNhapLayout = new javax.swing.GroupLayout(pnlXuatPhieuNhap);
        pnlXuatPhieuNhap.setLayout(pnlXuatPhieuNhapLayout);
        pnlXuatPhieuNhapLayout.setHorizontalGroup(
            pnlXuatPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlXuatPhieuNhapLayout.createSequentialGroup()
                .addComponent(lblXuatPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );
        pnlXuatPhieuNhapLayout.setVerticalGroup(
            pnlXuatPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblXuatPhieuNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlThongTinLayout = new javax.swing.GroupLayout(pnlThongTin);
        pnlThongTin.setLayout(pnlThongTinLayout);
        pnlThongTinLayout.setHorizontalGroup(
            pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongTinLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
            .addGroup(pnlThongTinLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongTinLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblThongTinHoaDon1)
                            .addComponent(scpPhieuNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, 861, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblThongTinHoaDon)
                            .addComponent(pnlThongTinPhieuNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(90, 90, 90)
                        .addComponent(pnlXuatPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)))
                .addGap(17, 17, 17))
        );
        pnlThongTinLayout.setVerticalGroup(
            pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addComponent(lblThongTinHoaDon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlThongTinPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlXuatPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblThongTinHoaDon1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scpPhieuNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 671, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void pnlQuayVeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlQuayVeMouseClicked
        this.removeAll();
        this.setLayout(new BorderLayout());
        this.add(new PhieuNhapHangJPanel());
        this.validate();
        this.repaint();
    }//GEN-LAST:event_pnlQuayVeMouseClicked

    private void lblXuatPhieuNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblXuatPhieuNhapMouseClicked
        try {
            String path = SweetFileChooser.PDFFileChooser();
            if(path==null){
                SweetAlert.showSweetAlert(new JFrame(), "Lỗi", "Vui lòng chọn file phù hợp", 1);
                return;
            }
            HoaDonNhapHangPDF.exportPdf(path, DanhSachChiTietPhieuNhap, currentPhieuNhap);
            SweetAlert.showSweetAlert(new JFrame(), "Hoàn tất", "Xuất phiếu nhập thành công", 0);
        } catch (IOException | DocumentException ex) {
            Logger.getLogger(ChiTietPhieuNhapJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblXuatPhieuNhapMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblMaNhaCungCap;
    private javax.swing.JLabel lblMaPhieuNhap;
    private javax.swing.JLabel lblNgayNhap;
    private javax.swing.JLabel lblNhanVienTaoPhieu;
    private javax.swing.JLabel lblQuayVe;
    private javax.swing.JLabel lblThongTinHoaDon;
    private javax.swing.JLabel lblThongTinHoaDon1;
    private javax.swing.JLabel lblTitleMaNhaCungCap;
    private javax.swing.JLabel lblTitleMaPhieuNhao;
    private javax.swing.JLabel lblTitleNgayNhap;
    private javax.swing.JLabel lblTitleNhanVienTaoPhieu;
    private javax.swing.JLabel lblTitleTongTien;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblXuatPhieuNhap;
    private GUI.Rounded pnlQuayVe;
    private GUI.Rounded pnlThongTin;
    private javax.swing.JPanel pnlThongTinPhieuNhap;
    private javax.swing.JPanel pnlTongTien;
    private javax.swing.JPanel pnlXuatPhieuNhap;
    private javax.swing.JScrollPane scpPhieuNhapHang;
    private static javax.swing.JTable tblPhieuNhapHang;
    // End of variables declaration//GEN-END:variables
}
