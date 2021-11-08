/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.THEMMOI;

import DTO.ChiTietDonHangDTO;
import DTO.ChiTietKhuyenMaiDTO;
import DTO.DonHangDTO;
import DTO.KhachHangDTO;
import DTO.SanPhamDTO;
import DTO.SanPhamKhuyenMaiDTO;
import GUI.CHITIET.ChiTietDonHangJPanel;
import static GUI.CHITIET.ChiTietDonHangJPanel.SanPhamList;
import GUI.DonHangJPanel;
import GUI.POPUP.ThemSanPhamVaoDonHangJFrame;
import GUI.RenderTable;
import GUI.Sweet.SweetComboBox;
import static GUI.Sweet.SweetAlert.showSweetOption;
import static GUI.TongQuanJPanel.customTable;
import GUI.changeColorOfCellTable;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import quanlycuahanggiay.DateTime;
import static quanlycuahanggiay.DateTime.getCurrentTimestamp;
import static quanlycuahanggiay.Regexp.SoDienThoai;
import static quanlycuahanggiay.quanlycuahanggiay.KhachHangBUS;
import static quanlycuahanggiay.quanlycuahanggiay.ChiTietKhuyenMaiBUS;
import static quanlycuahanggiay.quanlycuahanggiay.currentNhanVien;
import static quanlycuahanggiay.unicode.removeAccent;
import static quanlycuahanggiay.Currency.changeCurrency;

/**
 *
 * @author admin
 */
public class TaoDonHangJPanel extends javax.swing.JPanel {
    boolean option = true;
    public KhachHangDTO KhachHang = new KhachHangDTO();
    public static DonHangDTO currentDonHang;  
    //public static ArrayList<ChiTietDonHangDTO> currentChiTietDonHang;
    public static ArrayList<ChiTietDonHangDTO> GioHang;
    public static DefaultTableModel listTableModel;
    public SanPhamDTO SanPham;
    public static long TamTinh = 0;
    public static long GiamGia = 0;
    public static long PhiVanChuyen = 30000;
    public static long TongTien = 0;
    static boolean isVangLai = true;
    static boolean KhachHangMoi;
    private static ChiTietKhuyenMaiDTO ChiTietKhuyenMaicuaMaCode;
    public static ThemSanPhamVaoDonHangJFrame TSPVDHJF;
    /**
     * Creates new form TaoDonHangJPanel
     */
    Object[] columnNames ={"Mã sản phẩm","Tên sản phẩm","Size","Màu","Đơn giá","Số lượng","Tạm tính"};
    SweetComboBox cbxTrangThai;
    public void ShowTable(JTable table,Object[] columnName){
        listTableModel = new DefaultTableModel(columnNames,0);
        table.setDefaultRenderer(Object.class, new RenderTable());
        fetchAll();
        /*
        Object columnNames[] = columnName;
        Object tensanpham[] = {"Adidas","Nike","Adidas","Nike","Adidas"};
        Object dongia[] ={"3.600.000đ","3.600.000đ","3.600.000đ","3.600.000đ","3.600.000đ"};
        Object soluong[] ={1,2,3,4,5};
        Object size[] ={8,9,9,8,7};
        Object mau[] = {"Hồng","Hồng","Hồng","Hồng","Hồng"};
        Object tamtinh[] = {"3.600.000đ","3.600.000đ","3.600.000đ","3.600.000đ","3.600.000đ"};
        for(int i=0 ; i<tensanpham.length ; i++){
                Object row[] = {tensanpham[i],dongia[i],soluong[i],size[i],mau[i],tamtinh[i],del};
                listTableModel.addRow(row);
            }
        
        table.setModel(listTableModel);
        */  
    }
    
    public static void fetchAll(){
        listTableModel.setRowCount(0);
        GioHang.forEach(SanPham ->{
            SanPhamDTO SanPhamTrongGio = quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.getByMaSanPham(SanPham.getMaSanPham());
            String Size = quanlycuahanggiay.quanlycuahanggiay.SizeBUS.getTenSizeByMaSize(SanPhamTrongGio.getMaSize());
            String Mau = quanlycuahanggiay.quanlycuahanggiay.MauBUS.getTenMauByMaMau(SanPhamTrongGio.getMaMau());
            Vector row = new Vector();
            row.add(SanPhamTrongGio.getMaSanPham());
            row.add(SanPhamTrongGio.getTenSanPham());
            row.add(Size);
            row.add(Mau);
            row.add(changeCurrency(SanPhamTrongGio.getDonGia()));
            row.add(changeCurrency(SanPham.getSoLuong()));
            row.add(changeCurrency(SanPham.getThanhTien()));
            
            listTableModel.addRow(row);            
        });
        tblSanPham.setModel(listTableModel);
        
    }
    
    public static void setIcon(){
        for(int j = 0 ; j<tblSanPham.getRowCount();j++){
                tblSanPham.getColumnModel().getColumn(7).setCellRenderer(new changeColorOfCellTable(tblSanPham,j,7));
        }
    }
    
    public void setComboBox(){
        String[] trangthai = {"Thành Công","Hủy"};
        cbxTrangThai = new SweetComboBox("#ffffff","#181818",0,0,130,30,trangthai);
        pnlTrangThai.add(cbxTrangThai);
    }
    
    public String ValuesofSelectedItemComboBox(String Item){
        return Item.equals("Hủy") ? "0" : "1";
    }
    
    public TaoDonHangJPanel(boolean option){
        if(!option){
            this.option = false;
        }
            initComponents();
            currentDonHang = new DonHangDTO();
            //currentChiTietDonHang = new ArrayList<>();
            GioHang = new ArrayList<>();
            customTable(tblSanPham,scpSanPham);
            ShowTable(tblSanPham,columnNames);
            setComboBox(); 
            pnlHuyDon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            //pnlThemSanPham.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            pnlXacNhan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            lblMaDonHang.setText(quanlycuahanggiay.quanlycuahanggiay.DonHangBUS.getLatest());
            lblNgayDat.setText(DateTime.TimestampToDateString(DateTime.getCurrentTimestamp(),1));
            currentDonHang.setMaDonHang(lblMaDonHang.getText().toString());
            lblTamTinh.setText("0đ");
            lblGiamGia.setText("0đ");
            lblPhiVanChuyen.setText("0đ");
            lblTongTien.setText("0đ");
            //txtHoTen.setText("Thái Kiến Hào");
            //txtSoDienThoai.setText("0123456789");
            //txtDiaChi.setText("273 An Duong Vuong P.3 Q.5 TP.HCM");
            //txtEmail.setText("thaikienhao@gmail.com");
    }
    
    public void NewCustomer(boolean isNew){
            txtEmail.setText("");
            txtHoTen.setText("");
            txtDiaChi.setText("");
        if(isNew == false){
            txtEmail.setText(KhachHang.getEmail());
            txtHoTen.setText(KhachHang.getHoTen());
            txtDiaChi.setText(KhachHang.getDiaChi());
        }
        txtEmail.setEnabled(isNew);
        txtHoTen.setEnabled(isNew);
        txtDiaChi.setEnabled(isNew);
    }
    
public void getData(){
        System.out.println("VANGLAI"+isVangLai);
        if(isVangLai == false && !KhachHangMoi){
            KhachHang = quanlycuahanggiay.quanlycuahanggiay.KhachHangBUS.findBySoDienThoai(txtSoDienThoai.getText());
            currentDonHang.setMaKhachHang(KhachHang.getMaKhachHang());
        }
        if(isVangLai == false && KhachHangMoi){
            KhachHang = new KhachHangDTO();
            KhachHang.setMaKhachHang(quanlycuahanggiay.quanlycuahanggiay.KhachHangBUS.getLatest());
            currentDonHang.setMaKhachHang(KhachHang.getMaKhachHang());
            KhachHang.setHoTen(txtHoTen.getText().toString());
            KhachHang.setSoDienThoai(txtSoDienThoai.getText().toString());
            KhachHang.setDiaChi(txtDiaChi.getText().toString());
            KhachHang.setEmail(txtEmail.getText().toString());
        }
        //KhachHang.setMaKhachHang(quanlycuahanggiay.quanlycuahanggiay.KhachHangBUS.findBySoDienThoai(txtSoDienThoai.getText()));
        //KhachHang.setHoTen(txtHoTen.getText().toString());
        //KhachHang.setSoDienThoai(txtSoDienThoai.getText().toString());
        //KhachHang.setDiaChi(txtDiaChi.getText().toString());
        //KhachHang.setEmail(txtEmail.getText().toString());
        //currentDonHang.setMaKhachHang(quanlycuahanggiay.quanlycuahanggiay.KhachHangBUS.getLatest());

        if(isVangLai==true && !KhachHangMoi){
                KhachHang = quanlycuahanggiay.quanlycuahanggiay.KhachHangBUS.getByMaKhachHang("KH0");
                currentDonHang.setMaKhachHang(KhachHang.getMaKhachHang());
            } /*else {
                KhachHang = new KhachHangDTO();
                KhachHang.setMaKhachHang(quanlycuahanggiay.quanlycuahanggiay.KhachHangBUS.getLatest());
                KhachHang.setHoTen(txtHoTen.getText().toString());
                KhachHang.setSoDienThoai(txtSoDienThoai.getText().toString());
                KhachHang.setDiaChi(txtDiaChi.getText().toString());
                KhachHang.setEmail(txtEmail.getText().toString());
            }*/
    }

    public boolean ValidationKhachHang(KhachHangDTO KhachHang){
        String Val=null;
        if(!KhachHang.getEmail().equals("")){
            Val = KhachHangBUS.ValidationEmail(KhachHang);
        } else { GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(),"Lỗi!","Email không được phép bỏ trống",1); 
                return false; }
        if(Val == null)
        if(!KhachHang.getHoTen().equals("")){
            Val = KhachHangBUS.ValidationHoTen(KhachHang);
        } else { GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(),"Lỗi!","Họ tên không được phép bỏ trống",1); 
                return false; }
        if(Val == null)
        if(!KhachHang.getDiaChi().equals("")){
            Val = KhachHangBUS.ValidationDiaChi(KhachHang);
        } else { GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(),"Lỗi!","Địa chỉ không được phép bỏ trống",1); 
                return false; }
        if(Val == null) return true;
        else{
            GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(),"Lỗi!",Val,1);
            return false;
        }
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlThongTin = new GUI.Rounded();
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
        lblTitleHoTenn = new javax.swing.JLabel();
        lblTitleDiaChi = new javax.swing.JLabel();
        lblTitleEmail = new javax.swing.JLabel();
        lblTitleSoDienThoai = new javax.swing.JLabel();
        lblTitleTrangThai = new javax.swing.JLabel();
        lblTitleMaDonHang = new javax.swing.JLabel();
        lblTitleMaGiamGia1 = new javax.swing.JLabel();
        lblMaDonHang = new javax.swing.JLabel();
        lblNgayDat = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        txtSoDienThoai = new javax.swing.JTextField();
        txtMaGiamGia = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        pnlTrangThai = new javax.swing.JPanel();
        pnlHuyDon = new GUI.Rounded();
        lblHuyDon = new javax.swing.JLabel();
        pnlDungMa = new GUI.Rounded();
        lblHuyDon2 = new javax.swing.JLabel();
        pnlTim = new GUI.Rounded();
        lblTim = new javax.swing.JLabel();
        pnlXacNhan = new GUI.Rounded();
        lblXacNhan = new javax.swing.JLabel();
        pnlThemSanPham = new GUI.Rounded();
        lblThemSanPham = new javax.swing.JLabel();

        setBackground(new java.awt.Color(45, 47, 62));

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

        lblGiamGia.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblGiamGia.setForeground(new java.awt.Color(255, 255, 255));

        lblPhiVanChuyen.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblPhiVanChuyen.setForeground(new java.awt.Color(255, 255, 255));

        lblTongTien.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(210, 48, 44));

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
        pnlThongTinHoaDon.add(lblTitleNgayDat, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, 70, -1));

        lblTitleHoTenn.setForeground(new java.awt.Color(192, 192, 192));
        lblTitleHoTenn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleHoTenn.setText("Họ tên:");
        pnlThongTinHoaDon.add(lblTitleHoTenn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        lblTitleDiaChi.setForeground(new java.awt.Color(192, 192, 192));
        lblTitleDiaChi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleDiaChi.setText("Địa chỉ:");
        pnlThongTinHoaDon.add(lblTitleDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        lblTitleEmail.setForeground(new java.awt.Color(192, 192, 192));
        lblTitleEmail.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleEmail.setText("Email:");
        pnlThongTinHoaDon.add(lblTitleEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        lblTitleSoDienThoai.setForeground(new java.awt.Color(192, 192, 192));
        lblTitleSoDienThoai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleSoDienThoai.setText("Số điện thoại:");
        pnlThongTinHoaDon.add(lblTitleSoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 100, -1));

        lblTitleTrangThai.setForeground(new java.awt.Color(192, 192, 192));
        lblTitleTrangThai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleTrangThai.setText("Trạng thái:");
        pnlThongTinHoaDon.add(lblTitleTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 120, 80, -1));

        lblTitleMaDonHang.setForeground(new java.awt.Color(192, 192, 192));
        lblTitleMaDonHang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleMaDonHang.setText("Mã đơn hàng:");
        pnlThongTinHoaDon.add(lblTitleMaDonHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 90, -1));

        lblTitleMaGiamGia1.setForeground(new java.awt.Color(192, 192, 192));
        lblTitleMaGiamGia1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleMaGiamGia1.setText("Mã giảm giá:");
        pnlThongTinHoaDon.add(lblTitleMaGiamGia1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 70, 80, -1));

        lblMaDonHang.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblMaDonHang.setForeground(new java.awt.Color(210, 48, 44));
        pnlThongTinHoaDon.add(lblMaDonHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, -1, 20));

        lblNgayDat.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblNgayDat.setForeground(new java.awt.Color(255, 255, 255));
        lblNgayDat.setText("01/05/2000");
        pnlThongTinHoaDon.add(lblNgayDat, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 30, -1, -1));

        txtHoTen.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        txtHoTen.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtHoTen.setBorder(null);
        txtHoTen.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtHoTen.setEnabled(false);
        pnlThongTinHoaDon.add(txtHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 290, 30));

        txtSoDienThoai.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        txtSoDienThoai.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtSoDienThoai.setBorder(null);
        txtSoDienThoai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSoDienThoaiKeyPressed(evt);
            }
        });
        pnlThongTinHoaDon.add(txtSoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 290, 30));

        txtMaGiamGia.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtMaGiamGia.setForeground(new java.awt.Color(210, 48, 44));
        txtMaGiamGia.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtMaGiamGia.setBorder(null);
        pnlThongTinHoaDon.add(txtMaGiamGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 60, 200, 30));

        txtEmail.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        txtEmail.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtEmail.setBorder(null);
        txtEmail.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtEmail.setEnabled(false);
        pnlThongTinHoaDon.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 290, 30));

        txtDiaChi.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        txtDiaChi.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDiaChi.setBorder(null);
        txtDiaChi.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtDiaChi.setEnabled(false);
        pnlThongTinHoaDon.add(txtDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 290, 30));

        pnlTrangThai.setBackground(new java.awt.Color(255, 255, 255));

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

        pnlThongTinHoaDon.add(pnlTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, 130, 30));

        pnlHuyDon.setBackground(new java.awt.Color(53, 55, 70));
        pnlHuyDon.setForeground(new java.awt.Color(238, 38, 63));
        pnlHuyDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlHuyDonMouseClicked(evt);
            }
        });
        pnlHuyDon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblHuyDon.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblHuyDon.setForeground(new java.awt.Color(255, 255, 255));
        lblHuyDon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHuyDon.setText("Hủy đơn");
        pnlHuyDon.add(lblHuyDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 97, 56));

        pnlThongTinHoaDon.add(pnlHuyDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 110, 100, 40));

        pnlDungMa.setBackground(new java.awt.Color(53, 55, 70));
        pnlDungMa.setForeground(new java.awt.Color(238, 38, 63));
        pnlDungMa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlDungMaMouseClicked(evt);
            }
        });
        pnlDungMa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblHuyDon2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblHuyDon2.setForeground(new java.awt.Color(255, 255, 255));
        lblHuyDon2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHuyDon2.setText("Dùng");
        pnlDungMa.add(lblHuyDon2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 30));

        pnlThongTinHoaDon.add(pnlDungMa, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 60, 50, 30));

        pnlTim.setBackground(new java.awt.Color(53, 55, 70));
        pnlTim.setForeground(new java.awt.Color(238, 38, 63));
        pnlTim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlTimMouseClicked(evt);
            }
        });
        pnlTim.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTim.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblTim.setForeground(new java.awt.Color(255, 255, 255));
        lblTim.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTim.setText("Tìm");
        pnlTim.add(lblTim, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 30));

        pnlThongTinHoaDon.add(pnlTim, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 30, 50, 30));

        pnlXacNhan.setBackground(new java.awt.Color(53, 55, 70));
        pnlXacNhan.setForeground(new java.awt.Color(34, 212, 52));
        pnlXacNhan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlXacNhanMouseClicked(evt);
            }
        });

        lblXacNhan.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblXacNhan.setForeground(new java.awt.Color(255, 255, 255));
        lblXacNhan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblXacNhan.setText("Xác nhận");

        javax.swing.GroupLayout pnlXacNhanLayout = new javax.swing.GroupLayout(pnlXacNhan);
        pnlXacNhan.setLayout(pnlXacNhanLayout);
        pnlXacNhanLayout.setHorizontalGroup(
            pnlXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlXacNhanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlXacNhanLayout.setVerticalGroup(
            pnlXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlXacNhanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblXacNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlThemSanPham.setBackground(new java.awt.Color(53, 55, 70));
        pnlThemSanPham.setForeground(new java.awt.Color(210, 48, 44));
        pnlThemSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlThemSanPhamMouseClicked(evt);
            }
        });

        lblThemSanPham.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        lblThemSanPham.setForeground(new java.awt.Color(255, 255, 255));
        lblThemSanPham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThemSanPham.setText("Thêm sản phảm");

        javax.swing.GroupLayout pnlThemSanPhamLayout = new javax.swing.GroupLayout(pnlThemSanPham);
        pnlThemSanPham.setLayout(pnlThemSanPhamLayout);
        pnlThemSanPhamLayout.setHorizontalGroup(
            pnlThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThemSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblThemSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlThemSanPhamLayout.setVerticalGroup(
            pnlThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThemSanPhamLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblThemSanPham)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlThongTinLayout = new javax.swing.GroupLayout(pnlThongTin);
        pnlThongTin.setLayout(pnlThongTinLayout);
        pnlThongTinLayout.setHorizontalGroup(
            pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addComponent(lblThongTinHoaDon1)
                        .addGap(607, 607, 607)
                        .addComponent(pnlThemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scpSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 861, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 12, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongTinLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(pnlXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(122, 122, 122))
            .addGroup(pnlThongTinLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlThongTinHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlThongTinLayout.setVerticalGroup(
            pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlThongTinHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlThemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblThongTinHoaDon1)))
                .addGap(18, 18, 18)
                .addComponent(scpSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 920, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(pnlThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 890, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(15, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 751, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(pnlThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int column = tblSanPham.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/tblSanPham.getRowHeight();

        if(row < tblSanPham.getRowCount() && row >= 0 && column < tblSanPham.getColumnCount() && column >= 0){

            for(int i=0;i<tblSanPham.getColumnCount()-2;i++){
                System.out.println(tblSanPham.getModel().getValueAt(row,i));
            }

        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void pnlHuyDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlHuyDonMouseClicked
       if (SwingUtilities.isLeftMouseButton(evt)){
           if(GUI.Sweet.SweetAlert.showSweetOption(new javax.swing.JFrame(),"Thông báo","Bạn có muốn hủy đơn",0)==1){
               this.removeAll();
               this.setLayout(new BorderLayout());
               this.add(new DonHangJPanel());
               this.validate();
               this.repaint();
           }
       }
    }//GEN-LAST:event_pnlHuyDonMouseClicked

    public static void TamTinh(){
        TamTinh = 0;
        GioHang.forEach(ChiTietDonHang ->{
                //String thanhtien = ChiTietDonHang.getThanhTien();
                TamTinh += Long.parseLong(ChiTietDonHang.getThanhTien());
        });
        //return Double.toString(TamTinh);
    }
    
    public static void GiamGia(){
        GiamGia = 0;
        if(currentDonHang.getMaCode()!=null){ 
            ChiTietKhuyenMaicuaMaCode = quanlycuahanggiay.quanlycuahanggiay.ChiTietKhuyenMaiBUS.getByMaCode(currentDonHang.getMaCode());
            if(ChiTietKhuyenMaicuaMaCode!=null){
                if(Long.parseLong(getCurrentTimestamp()) < Long.parseLong(ChiTietKhuyenMaicuaMaCode.getThoiGianBatDauSuDung())){
                    GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(),"Lỗi!","Mã giảm giá chưa đến thời gian được phép sử dụng",1);
                    txtMaGiamGia.setText("");
                    GiamGia=0;
                    //return "0";
                }
                else if(Long.parseLong(getCurrentTimestamp()) > Long.parseLong(ChiTietKhuyenMaicuaMaCode.getThoiGianKetThucSuDung())){
                    GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(),"Lỗi!","Mã giảm giá đã hết thời gian được phép sử dụng",1);
                    txtMaGiamGia.setText("");
                    GiamGia=0;
                    //return "0";
                }
                else if(ChiTietKhuyenMaicuaMaCode.getSoLuongDaDung().equals(ChiTietKhuyenMaicuaMaCode.getSoLuong())){
                    GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(),"Lỗi!","Mã giảm giá đã hết lượt sử dụng",1);
                    txtMaGiamGia.setText("");
                    GiamGia=0;
                    //return "0";
                }
                else {
                    ArrayList<String> SanPhamKhuyenMaiList = new ArrayList<>();
                    SanPhamKhuyenMaiList = quanlycuahanggiay.quanlycuahanggiay.SanPhamKhuyenMaiBUS.getSanPhamKhuyenMai(ChiTietKhuyenMaicuaMaCode.getMaChiTietKhuyenMai());
                    if(SanPhamKhuyenMaiList==null){
                        long GiaTriToiThieuSuDung = Long.parseLong(ChiTietKhuyenMaicuaMaCode.getGiaTriToiThieuSuDung());
                        if(TamTinh < GiaTriToiThieuSuDung){
                            GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(),"Lỗi!","Mã áp dung cho đơn hàng từ "+ChiTietKhuyenMaicuaMaCode.getGiaTriToiThieuSuDung(),1);
                            GiamGia=0;
                            //return "0";
                        }
                        else {
                            long PhanTramKhuyenMai = Long.parseLong(ChiTietKhuyenMaicuaMaCode.getPhanTramKhuyenMai())/100;
                            long GiaTienKhuyenMai = Long.parseLong(ChiTietKhuyenMaicuaMaCode.getGiaTienKhuyenMai());
                            GiamGia = TamTinh*PhanTramKhuyenMai + GiaTienKhuyenMai;
                            if(GiamGia >= Long.parseLong(ChiTietKhuyenMaicuaMaCode.getGiaTriGiamToiDa())){
                                GiamGia=Long.parseLong(ChiTietKhuyenMaicuaMaCode.getGiaTriGiamToiDa());  
                            }
                        //return Double.toString(GiamGia);
                        }
                    }
                    else{
                        for(int i=0;i<GioHang.size();i++){
                            for(int j=0;j<SanPhamKhuyenMaiList.size();j++){
                                if(GioHang.get(i).getMaSanPham().equals(SanPhamKhuyenMaiList.get(j))){
                                    long PhanTramKhuyenMai = Long.parseLong(ChiTietKhuyenMaicuaMaCode.getPhanTramKhuyenMai())/100;
                                    GiamGia += Long.parseLong(GioHang.get(i).getDonGia())*PhanTramKhuyenMai;
                                }
                            }
                        }
                        if(GiamGia >= Long.parseLong(ChiTietKhuyenMaicuaMaCode.getGiaTriGiamToiDa())){
                            GiamGia=Long.parseLong(ChiTietKhuyenMaicuaMaCode.getGiaTriGiamToiDa());  
                        }
                    //return Double.toString(GiamGia);
                    }
                }
            }
            else {
                GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(),"Lỗi!","Mã giảm giá không tồn tại",1);
                txtMaGiamGia.setText("");
                GiamGia=0;
                //return "0";
            }
        }
        else {GiamGia=0;} //return "0";}
    }
    
    public static void TongTien(){
        TongTien = 0;
        if(TamTinh>0){
        TongTien = TamTinh + PhiVanChuyen - GiamGia;
        //return Double.toString(TongTien);
        } //else return "0";
    }
    
    public static void TongTiencoMaGiamGia(String GiamGia){
        if(TamTinh>0){
        //Double TienGiamGia = Double.parseDouble(GiamGia);
        //Double Tong = Double.parseDouble(TamTinh()) - TienGiamGia + 30000;
        TongTien = TongTien - Long.parseLong(GiamGia);
        //return Double.toString(TongTien);
        } //else return "0";
    }
    
    public static void MoneyMoney(){
        TamTinh();
        //GiamGia=0;
        GiamGia();
        TongTien();
        lblTamTinh.setText(changeCurrency(Long.toString(TamTinh))+"đ");
        lblPhiVanChuyen.setText(changeCurrency("30000")+"đ");
        lblTongTien.setText(changeCurrency(Long.toString(TongTien))+"đ");
    }
    
    private boolean isNull(){
        if(txtSoDienThoai.getText().equals("") && txtEmail.getText().equals("") && txtHoTen.getText().equals("") &&txtDiaChi.getText().equals(""))
        return true;
        return false;
    }
    
    private void pnlXacNhanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlXacNhanMouseClicked
        getData();
        if (SwingUtilities.isLeftMouseButton(evt)){
            System.out.println(GioHang);
            if(GioHang.size()==0){
                GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(),"Lỗi!","Giỏ hàng đang trống",1);
            } else {
            if(isVangLai==false){
                System.out.println("Vang Lai false!");
                if(ValidationKhachHang(KhachHang)){
                    System.out.println("KhachHang true");
                    if(GUI.Sweet.SweetAlert.showSweetOption(new javax.swing.JFrame(),"Thông báo","Bạn có muốn thêm đơn?",3)==1){
                        setData();
                        Print();
                        if(KhachHangMoi){
                        quanlycuahanggiay.quanlycuahanggiay.KhachHangBUS.add(KhachHang);
                        quanlycuahanggiay.quanlycuahanggiay.DonHangBUS.add(currentDonHang);
                        GioHang.forEach(sanphamtronggio->{
                            quanlycuahanggiay.quanlycuahanggiay.ChiTietDonHangBUS.add(sanphamtronggio);
                            SanPhamDTO capnhatsanpham = quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.getByMaSanPham(sanphamtronggio.getMaSanPham());
                            capnhatsanpham.setSoLuong(changeCurrency(Long.toString(Long.parseLong(capnhatsanpham.getSoLuong())-Long.parseLong(sanphamtronggio.getSoLuong()))));
                            quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.edit(capnhatsanpham);
                        });
                        } else {
                            //quanlycuahanggiay.quanlycuahanggiay.KhachHangBUS.add(KhachHang);
                            quanlycuahanggiay.quanlycuahanggiay.DonHangBUS.add(currentDonHang);
                            GioHang.forEach(sanphamtronggio->{
                            quanlycuahanggiay.quanlycuahanggiay.ChiTietDonHangBUS.add(sanphamtronggio);
                            SanPhamDTO capnhatsanpham = quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.getByMaSanPham(sanphamtronggio.getMaSanPham());
                            capnhatsanpham.setSoLuong(changeCurrency(Long.toString(Long.parseLong(capnhatsanpham.getSoLuong())-Long.parseLong(sanphamtronggio.getSoLuong()))));
                            quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.edit(capnhatsanpham);
                        });
                        }
                        ((javax.swing.JFrame) SwingUtilities.getWindowAncestor(quanlycuahanggiay.quanlycuahanggiay.bg)).setEnabled(true);
                        this.removeAll();
                        this.setLayout(new BorderLayout());
                        this.add(new DonHangJPanel());
                        //if(isNull()) KhachHang = quanlycuahanggiay.quanlycuahanggiay.KhachHangBUS.getByMaKhachHang("KH0");
                        //else{   
                        //}
                    }   
                }
            }
            else {
                System.out.println("Vang Lai true!");
                if(GUI.Sweet.SweetAlert.showSweetOption(new javax.swing.JFrame(),"Thông báo","Bạn có muốn thêm đơn?",3)==1){
                    setData();
                    quanlycuahanggiay.quanlycuahanggiay.DonHangBUS.add(currentDonHang);
                    GioHang.forEach(sanphamtronggio->{
                        quanlycuahanggiay.quanlycuahanggiay.ChiTietDonHangBUS.add(sanphamtronggio);
                        SanPhamDTO capnhatsanpham = quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.getByMaSanPham(sanphamtronggio.getMaSanPham());
                        capnhatsanpham.setSoLuong(changeCurrency(Long.toString(Long.parseLong(capnhatsanpham.getSoLuong())-Long.parseLong(sanphamtronggio.getSoLuong()))));
                        quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.edit(capnhatsanpham);                        
                    });
                    ((javax.swing.JFrame) SwingUtilities.getWindowAncestor(quanlycuahanggiay.quanlycuahanggiay.bg)).setEnabled(true);
                    this.removeAll();
                    this.setLayout(new BorderLayout());
                    this.add(new DonHangJPanel());
                } 
            }
            }
        }
        /*
        quanlycuahanggiay.quanlycuahanggiay.KhachHangBUS.add(KhachHang);
        quanlycuahanggiay.quanlycuahanggiay.DonHangBUS.add(currentDonHang);
        GioHang.forEach(sanphamtronggio->{
            quanlycuahanggiay.quanlycuahanggiay.ChiTietDonHangBUS.add(sanphamtronggio);
        });
        */
        /*
        this.removeAll();
        this.setLayout(new BorderLayout());        
        if(option){
            this.add(new DonHangJPanel());            
        }
        */
            this.validate();
            this.repaint();
            
            
           
    }//GEN-LAST:event_pnlXacNhanMouseClicked
    
    public void Print(){
        System.out.println("add BD");
        System.out.println(KhachHang.getMaKhachHang());
            System.out.println(KhachHang.getHoTen());
            System.out.println(KhachHang.getSoDienThoai());
            System.out.println(KhachHang.getDiaChi());
            System.out.println(KhachHang.getEmail());
            System.out.println(currentDonHang.getMaDonHang());
            System.out.println(currentDonHang.getMaKhachHang());
            System.out.println(currentDonHang.getMaNhanVien());
            System.out.println(currentDonHang.getMaCode());
            System.out.println(currentDonHang.getNgayXuat());
            System.out.println(currentDonHang.getTamTinh());
            System.out.println(currentDonHang.getGiamGia());
            System.out.println(currentDonHang.getPhiVanChuyen());
            System.out.println(currentDonHang.getTongTien());
            System.out.println(currentDonHang.getTrangThai());
            //System.out.println(currentDonHang.getMaCode());
    }
    
    public void setData(){
        //if(isVangLai==false){
        //    currentDonHang.setMaKhachHang(quanlycuahanggiay.quanlycuahanggiay.KhachHangBUS.getLatest());
        //} else { currentDonHang.setMaKhachHang("KH0"); }
        //currentDonHang.setMaKhachHang();
        currentDonHang.setNgayXuat(getCurrentTimestamp());
        currentDonHang.setTrangThai(ValuesofSelectedItemComboBox(cbxTrangThai.getSelectedItem().toString()));
        currentDonHang.setMaNhanVien(currentNhanVien.getMaNhanVien());
        currentDonHang.setTamTinh(Long.toString(TamTinh).replace(".0",""));
        currentDonHang.setGiamGia(Long.toString(GiamGia).replace(".0",""));
        if(ChiTietKhuyenMaicuaMaCode!=null)
        ChiTietKhuyenMaicuaMaCode.setSoLuongDaDung(Integer.parseInt(ChiTietKhuyenMaicuaMaCode.getSoLuongDaDung())+1+"");
        currentDonHang.setPhiVanChuyen("30000");
        currentDonHang.setTongTien(Long.toString(TongTien).replace(".0",""));
        
        /*
                    System.out.println("TamTinh: "+Double.toString(TamTinh).replace(".",""));
                    System.out.println("GiamGia: "+Double.toString(GiamGia).replace(".",""));
                    System.out.println("SoLuongDaDung: "+(Integer.parseInt(ChiTietKhuyenMaicuaMaCode.getSoLuongDaDung()))+"");
                    System.out.println("SoLuongDaDung+1: "+(Integer.parseInt(ChiTietKhuyenMaicuaMaCode.getSoLuongDaDung())+1)+"");
                    System.out.println("PhiVanChuyen: "+"30000");
                    System.out.println("TongTien: "+Double.toString(TongTien).replace(".",""));
                    */
    }
    
    private void pnlThemSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlThemSanPhamMouseClicked
        TSPVDHJF = new ThemSanPhamVaoDonHangJFrame();
        TSPVDHJF.setVisible(true);
        //new ThemSanPhamVaoDonHangJFrame().setVisible(true);
        ((javax.swing.JFrame) SwingUtilities.getWindowAncestor(quanlycuahanggiay.quanlycuahanggiay.bg)).setEnabled(false);

    }//GEN-LAST:event_pnlThemSanPhamMouseClicked

    private void pnlTimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTimMouseClicked
        timSoDienThoai();
    }//GEN-LAST:event_pnlTimMouseClicked

    private void pnlDungMaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlDungMaMouseClicked
        currentDonHang.setMaCode(txtMaGiamGia.getText().toString());
        TamTinh();
        //GiamGia=0;  
        GiamGia();
        TongTien();
        lblGiamGia.setText(changeCurrency(Long.toString(GiamGia))+"đ");
        //TongTiencoMaGiamGia(Double.toString(GiamGia));
        currentDonHang.setTongTien(Long.toString(TongTien));
        lblTongTien.setText(changeCurrency(Long.toString(TongTien))+"đ");
    }//GEN-LAST:event_pnlDungMaMouseClicked
    
    public void timSoDienThoai(){
    String SoDienThoai = txtSoDienThoai.getText();
        if(!SoDienThoai(SoDienThoai)){
            GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(),"Lỗi!","Số điện thoại không hợp lệ",1);
        }
        else{
            KhachHang = quanlycuahanggiay.quanlycuahanggiay.KhachHangBUS.findBySoDienThoai(SoDienThoai);
            if(KhachHang !=null){
                NewCustomer(false);
                isVangLai=false;
                KhachHangMoi = false;
            
            }
            else{
                if(GUI.Sweet.SweetAlert.showSweetOption(new javax.swing.JFrame(),"Lỗi!","Không tìm thấy khách hàng Bạn có muốn thêm khách hàng mới",1)==1){
                    NewCustomer(true);
                    isVangLai=false;
                    KhachHangMoi=true;
            
                }
                else {
                    GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(),"Thông báo","Đây là đơn hàng của khách vãng lai",3);
                    isVangLai = true;
                    KhachHangMoi = false;
                    txtSoDienThoai.setText("");
                    txtHoTen.setText("");
                    txtEmail.setText("");
                    txtDiaChi.setText("");
                }
            }
        }
}
    
    private void txtSoDienThoaiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoDienThoaiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
            timSoDienThoai();
    }//GEN-LAST:event_txtSoDienThoaiKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel lblGiamGia;
    private javax.swing.JLabel lblHuyDon;
    private javax.swing.JLabel lblHuyDon2;
    private javax.swing.JLabel lblMaDonHang;
    private javax.swing.JLabel lblNgayDat;
    public static javax.swing.JLabel lblPhiVanChuyen;
    public static javax.swing.JLabel lblTamTinh;
    private javax.swing.JLabel lblThemSanPham;
    private javax.swing.JLabel lblThongTinHoaDon1;
    private javax.swing.JLabel lblTim;
    private javax.swing.JLabel lblTitleDiaChi;
    private javax.swing.JLabel lblTitleEmail;
    private javax.swing.JLabel lblTitleHoTenn;
    private javax.swing.JLabel lblTitleMaDonHang;
    private javax.swing.JLabel lblTitleMaGiamGia;
    private javax.swing.JLabel lblTitleMaGiamGia1;
    private javax.swing.JLabel lblTitleNgayDat;
    private javax.swing.JLabel lblTitlePhiVanChuyen;
    private javax.swing.JLabel lblTitleSoDienThoai;
    private javax.swing.JLabel lblTitleTamTinh;
    private javax.swing.JLabel lblTitleTong;
    private javax.swing.JLabel lblTitleTrangThai;
    public static javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblXacNhan;
    private GUI.Rounded pnlDungMa;
    private GUI.Rounded pnlHuyDon;
    private GUI.Rounded pnlThemSanPham;
    private GUI.Rounded pnlThongTin;
    private javax.swing.JPanel pnlThongTinHoaDon;
    private GUI.Rounded pnlTim;
    private javax.swing.JPanel pnlTongTien;
    private javax.swing.JPanel pnlTrangThai;
    private GUI.Rounded pnlXacNhan;
    private javax.swing.JScrollPane scpSanPham;
    private static javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private static javax.swing.JTextField txtMaGiamGia;
    private javax.swing.JTextField txtSoDienThoai;
    // End of variables declaration//GEN-END:variables
}
