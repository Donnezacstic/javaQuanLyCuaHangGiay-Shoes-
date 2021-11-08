/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlycuahanggiay;

import BUS.ThuongHieuBUS;
import BUS.MauBUS;
import BUS.AccountBUS;
import BUS.ChiTietDonHangBUS;
import BUS.ChiTietPhieuNhapBUS;
import BUS.ChiTietKhuyenMaiBUS;
import BUS.ChuongTrinhKhuyenMaiBUS;
import BUS.DonHangBUS;
import BUS.KhachHangBUS;
import BUS.SanPhamBUS;
import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import BUS.PhieuNhapBUS;
import BUS.SizeBUS;
import BUS.SanPhamBUS;
import BUS.SanPhamKhuyenMaiBUS;
import DTO.AccountDTO;
import DTO.ChiTietDonHangDTO;
import DTO.ChiTietPhieuNhapDTO;
//import DTO.ChiTietKhuyenMaiDTO;
//import DTO.ChiTietKhuyenMaiDTO;
import DTO.ChuongTrinhKhuyenMaiDTO;
import DTO.DonHangDTO;
import DTO.KhachHangDTO;
import DTO.NhanVienDTO;
import bean.DanhMucBean;
import controller.changeScreenController;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author admin
 */
public class quanlycuahanggiay extends javax.swing.JFrame {
    public static AccountDTO currentAccount = null;
    public static AccountBUS AccountBUS;
    public static NhanVienDTO currentNhanVien = null;
    public static NhanVienBUS NhanVienBUS;
    public static PhieuNhapBUS PhieuNhapBUS;
    public static KhachHangBUS KhachHangBUS;
    public static SanPhamBUS SanPhamBUS;
    public static ThuongHieuBUS ThuongHieuBUS;
    public static MauBUS MauBUS;
    public static SizeBUS SizeBUS;
    public static NhaCungCapBUS NhaCungCapBUS;
    public static ChuongTrinhKhuyenMaiBUS ChuongTrinhKhuyenMaiBUS;
    public static ChuongTrinhKhuyenMaiDTO currentChuongTrinhKhuyetMai = null;
    public static DonHangDTO currentDonHang = null;
    public static ChiTietKhuyenMaiBUS ChiTietKhuyenMaiBUS;
    public static DonHangBUS DonHangBUS;
    public static ChiTietPhieuNhapBUS  ChiTietPhieuNhapBUS;
    public static ChiTietDonHangBUS ChiTietDonHangBUS;

//    public static ChiTietKhuyenMaiBUS ChiTietKhuyenMaiBUS;

    public static SanPhamKhuyenMaiBUS SanPhamKhuyenMaiBUS;
    int xMouse,yMouse;
    
    public quanlycuahanggiay() {
        
        initComponents();
        initData();
        setScreenBaseOnRole(currentAccount.getRole());
        lblName1.setText(currentAccount.getPosition());
        if(!currentAccount.getRole().equals("admin")){
            BUS.NhanVienBUS.getAuth(currentAccount.getUsername());
            lblName1.setText(currentNhanVien.getHoTen());
        }
        lblRole1.setText(currentAccount.getPosition());       
        this.setLocationRelativeTo(null);       

    }
    
    public void initData(){
        NhanVienBUS = new NhanVienBUS();
        KhachHangBUS = new KhachHangBUS();
        SanPhamBUS = new SanPhamBUS();
        AccountBUS = new AccountBUS();
        MauBUS = new MauBUS();
        ThuongHieuBUS = new ThuongHieuBUS(); 
        PhieuNhapBUS = new PhieuNhapBUS();
        SizeBUS = new SizeBUS();
        NhaCungCapBUS = new NhaCungCapBUS();
        ChuongTrinhKhuyenMaiBUS = new ChuongTrinhKhuyenMaiBUS();
        DonHangBUS = new DonHangBUS();
        ChiTietPhieuNhapBUS = new ChiTietPhieuNhapBUS();
        ChiTietKhuyenMaiBUS = new ChiTietKhuyenMaiBUS();
        ChiTietDonHangBUS = new ChiTietDonHangBUS();
        SanPhamKhuyenMaiBUS = new SanPhamKhuyenMaiBUS();
    }
    public void setScreenBaseOnRole(String Role){
        changeScreenController  controller = new changeScreenController(pnlView); 
        controller.setScreen("TongQuan",pnlTongQuan,lblTongQuan);
        List<DanhMucBean> listItem = new ArrayList<>();
        listItem.add(new DanhMucBean("TongQuan",pnlTongQuan,lblTongQuan));
        listItem.add(new DanhMucBean("DonHang",pnlDonHang,lblDonHang));
        listItem.add(new DanhMucBean("SanPham",pnlSanPham,lblSanPham));
        listItem.add(new DanhMucBean("KhachHang",pnlKhachHang,lblKhachHang)); 
        listItem.add(new DanhMucBean("DoanhThu",pnlDoanhThu,lblDoanhThu));
        listItem.add(new DanhMucBean("ThongKe",pnlThongKe,lblThongKe));
        listItem.add(new DanhMucBean("KhuyenMai",pnlKhuyenMai,lblKhuyenMai));
        listItem.add(new DanhMucBean("PhieuNhapHang",pnlPhieuNhapHang,lblPhieuNhapHang));
        listItem.add(new DanhMucBean("QLNV",pnlQLNV,lblQLNV));
        listItem.add(new DanhMucBean("QuanLy",pnlQLDM,lblQLDM));
        listItem.add(new DanhMucBean("ThemTaiKhoan",pnlThemTaiKhoan,lblThemTaiKhoan));
        listItem.add(new DanhMucBean("CaiDat",pnlCaiDat,lblCaiDat));
        if(Role.equals("admin")){
            for(int i=0;i<listItem.size();i++){
            
                listItem.get(i).getPnlName().setVisible(false);
                if(i == listItem.size()-2 || i == listItem.size()-1)
                
                listItem.get(i).getPnlName().setVisible(true);
                
        }
        controller.setScreen(listItem.get((listItem.size()-2)).getKindOfScreen(),listItem.get(listItem.size()-2).getPnlName(), listItem.get(listItem.size()-2).getLblName());
        
        lblCaiDat.setText("Đăng xuất");
        pnlCaiDat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                    ((javax.swing.JFrame) SwingUtilities.getWindowAncestor(quanlycuahanggiay.bg)).dispose();
                    new GUI.LoginJFrame().setVisible(true);
            }
        });        
             
        }
        if(!Role.equals("admin")){
            
        for(int i=0;i<listItem.size();i++){
            if(49 == Role.codePointAt(i))
                listItem.get(i).getPnlName().setVisible(true);
                
            else 
                listItem.get(i).getPnlName().setVisible(false);
        }
        controller.setScreen(listItem.get(Role.indexOf("1")).getKindOfScreen(),listItem.get(Role.indexOf("1")).getPnlName(), listItem.get(Role.indexOf("1")).getLblName());
        }
        controller.setEvent(listItem);       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        pnlSideBar = new javax.swing.JPanel();
        pnlLogo = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        pnlTongQuan = new javax.swing.JPanel();
        lblTongQuan = new javax.swing.JLabel();
        pnlDonHang = new javax.swing.JPanel();
        lblDonHang = new javax.swing.JLabel();
        pnlSanPham = new javax.swing.JPanel();
        lblSanPham = new javax.swing.JLabel();
        pnlKhachHang = new javax.swing.JPanel();
        lblKhachHang = new javax.swing.JLabel();
        pnlDoanhThu = new javax.swing.JPanel();
        lblDoanhThu = new javax.swing.JLabel();
        pnlThongKe = new javax.swing.JPanel();
        lblThongKe = new javax.swing.JLabel();
        pnlKhuyenMai = new javax.swing.JPanel();
        lblKhuyenMai = new javax.swing.JLabel();
        pnlPhieuNhapHang = new javax.swing.JPanel();
        lblPhieuNhapHang = new javax.swing.JLabel();
        pnlQLNV = new javax.swing.JPanel();
        lblQLNV = new javax.swing.JLabel();
        pnlQLDM = new javax.swing.JPanel();
        lblQLDM = new javax.swing.JLabel();
        pnlThemTaiKhoan = new javax.swing.JPanel();
        lblThemTaiKhoan = new javax.swing.JLabel();
        pnlCaiDat = new javax.swing.JPanel();
        lblCaiDat = new javax.swing.JLabel();
        pnlView = new javax.swing.JPanel();
        pnlHeader1 = new javax.swing.JPanel();
        lblExit1 = new javax.swing.JLabel();
        pnlNameUser1 = new javax.swing.JPanel();
        lblName1 = new javax.swing.JLabel();
        lblRole1 = new javax.swing.JLabel();
        lblAvatar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlSideBar.setBackground(new java.awt.Color(46, 49, 67));

        pnlLogo.setBackground(new java.awt.Color(46, 49, 67));
        pnlLogo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        pnlLogo.setPreferredSize(new java.awt.Dimension(240, 55));
        pnlLogo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnlHeader1MouseDragged(evt);
            }
        });
        pnlLogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlHeader1MousePressed(evt);
            }
        });

        lblLogo.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/01.png"))); // NOI18N

        javax.swing.GroupLayout pnlLogoLayout = new javax.swing.GroupLayout(pnlLogo);
        pnlLogo.setLayout(pnlLogoLayout);
        pnlLogoLayout.setHorizontalGroup(
            pnlLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLogoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogo)
                .addContainerGap(84, Short.MAX_VALUE))
        );
        pnlLogoLayout.setVerticalGroup(
            pnlLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLogoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        pnlSideBar.add(pnlLogo);

        pnlTongQuan.setBackground(new java.awt.Color(46, 49, 67));

        lblTongQuan.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        lblTongQuan.setForeground(new java.awt.Color(255, 255, 255));
        lblTongQuan.setText("Tổng quan");

        javax.swing.GroupLayout pnlTongQuanLayout = new javax.swing.GroupLayout(pnlTongQuan);
        pnlTongQuan.setLayout(pnlTongQuanLayout);
        pnlTongQuanLayout.setHorizontalGroup(
            pnlTongQuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTongQuanLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(lblTongQuan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlTongQuanLayout.setVerticalGroup(
            pnlTongQuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTongQuan, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        pnlSideBar.add(pnlTongQuan);

        pnlDonHang.setBackground(new java.awt.Color(46, 49, 67));

        lblDonHang.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        lblDonHang.setForeground(new java.awt.Color(255, 255, 255));
        lblDonHang.setText("Đơn hàng");

        javax.swing.GroupLayout pnlDonHangLayout = new javax.swing.GroupLayout(pnlDonHang);
        pnlDonHang.setLayout(pnlDonHangLayout);
        pnlDonHangLayout.setHorizontalGroup(
            pnlDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDonHangLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(lblDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlDonHangLayout.setVerticalGroup(
            pnlDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDonHang, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        pnlSideBar.add(pnlDonHang);

        pnlSanPham.setBackground(new java.awt.Color(46, 49, 67));

        lblSanPham.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        lblSanPham.setForeground(new java.awt.Color(255, 255, 255));
        lblSanPham.setText("Sản phẩm");

        javax.swing.GroupLayout pnlSanPhamLayout = new javax.swing.GroupLayout(pnlSanPham);
        pnlSanPham.setLayout(pnlSanPhamLayout);
        pnlSanPhamLayout.setHorizontalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSanPhamLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(lblSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlSanPhamLayout.setVerticalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        pnlSideBar.add(pnlSanPham);

        pnlKhachHang.setBackground(new java.awt.Color(46, 49, 67));

        lblKhachHang.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        lblKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        lblKhachHang.setText("Khách hàng");

        javax.swing.GroupLayout pnlKhachHangLayout = new javax.swing.GroupLayout(pnlKhachHang);
        pnlKhachHang.setLayout(pnlKhachHangLayout);
        pnlKhachHangLayout.setHorizontalGroup(
            pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlKhachHangLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(lblKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlKhachHangLayout.setVerticalGroup(
            pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        pnlSideBar.add(pnlKhachHang);

        pnlDoanhThu.setBackground(new java.awt.Color(46, 49, 67));

        lblDoanhThu.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        lblDoanhThu.setForeground(new java.awt.Color(255, 255, 255));
        lblDoanhThu.setText("Doanh thu");

        javax.swing.GroupLayout pnlDoanhThuLayout = new javax.swing.GroupLayout(pnlDoanhThu);
        pnlDoanhThu.setLayout(pnlDoanhThuLayout);
        pnlDoanhThuLayout.setHorizontalGroup(
            pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDoanhThuLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(lblDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlDoanhThuLayout.setVerticalGroup(
            pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        pnlSideBar.add(pnlDoanhThu);

        pnlThongKe.setBackground(new java.awt.Color(46, 49, 67));

        lblThongKe.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        lblThongKe.setForeground(new java.awt.Color(255, 255, 255));
        lblThongKe.setText("Thống kê");

        javax.swing.GroupLayout pnlThongKeLayout = new javax.swing.GroupLayout(pnlThongKe);
        pnlThongKe.setLayout(pnlThongKeLayout);
        pnlThongKeLayout.setHorizontalGroup(
            pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongKeLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(lblThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlThongKeLayout.setVerticalGroup(
            pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        pnlSideBar.add(pnlThongKe);

        pnlKhuyenMai.setBackground(new java.awt.Color(46, 49, 67));

        lblKhuyenMai.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        lblKhuyenMai.setForeground(new java.awt.Color(255, 255, 255));
        lblKhuyenMai.setText("Khuyến mãi");

        javax.swing.GroupLayout pnlKhuyenMaiLayout = new javax.swing.GroupLayout(pnlKhuyenMai);
        pnlKhuyenMai.setLayout(pnlKhuyenMaiLayout);
        pnlKhuyenMaiLayout.setHorizontalGroup(
            pnlKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlKhuyenMaiLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(lblKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlKhuyenMaiLayout.setVerticalGroup(
            pnlKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblKhuyenMai, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        pnlSideBar.add(pnlKhuyenMai);

        pnlPhieuNhapHang.setBackground(new java.awt.Color(46, 49, 67));

        lblPhieuNhapHang.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        lblPhieuNhapHang.setForeground(new java.awt.Color(255, 255, 255));
        lblPhieuNhapHang.setText("Phiếu nhập hàng");

        javax.swing.GroupLayout pnlPhieuNhapHangLayout = new javax.swing.GroupLayout(pnlPhieuNhapHang);
        pnlPhieuNhapHang.setLayout(pnlPhieuNhapHangLayout);
        pnlPhieuNhapHangLayout.setHorizontalGroup(
            pnlPhieuNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPhieuNhapHangLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(lblPhieuNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlPhieuNhapHangLayout.setVerticalGroup(
            pnlPhieuNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblPhieuNhapHang, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        pnlSideBar.add(pnlPhieuNhapHang);

        pnlQLNV.setBackground(new java.awt.Color(46, 49, 67));

        lblQLNV.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        lblQLNV.setForeground(new java.awt.Color(255, 255, 255));
        lblQLNV.setText("Nhân viên");

        javax.swing.GroupLayout pnlQLNVLayout = new javax.swing.GroupLayout(pnlQLNV);
        pnlQLNV.setLayout(pnlQLNVLayout);
        pnlQLNVLayout.setHorizontalGroup(
            pnlQLNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlQLNVLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(lblQLNV, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlQLNVLayout.setVerticalGroup(
            pnlQLNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblQLNV, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        pnlSideBar.add(pnlQLNV);

        pnlQLDM.setBackground(new java.awt.Color(46, 49, 67));

        lblQLDM.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        lblQLDM.setForeground(new java.awt.Color(255, 255, 255));
        lblQLDM.setText("Quản lý danh mục");

        javax.swing.GroupLayout pnlQLDMLayout = new javax.swing.GroupLayout(pnlQLDM);
        pnlQLDM.setLayout(pnlQLDMLayout);
        pnlQLDMLayout.setHorizontalGroup(
            pnlQLDMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlQLDMLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(lblQLDM, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlQLDMLayout.setVerticalGroup(
            pnlQLDMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblQLDM, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        pnlSideBar.add(pnlQLDM);

        pnlThemTaiKhoan.setBackground(new java.awt.Color(46, 49, 67));

        lblThemTaiKhoan.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        lblThemTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        lblThemTaiKhoan.setText("Thêm tài khoản");

        javax.swing.GroupLayout pnlThemTaiKhoanLayout = new javax.swing.GroupLayout(pnlThemTaiKhoan);
        pnlThemTaiKhoan.setLayout(pnlThemTaiKhoanLayout);
        pnlThemTaiKhoanLayout.setHorizontalGroup(
            pnlThemTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThemTaiKhoanLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(lblThemTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlThemTaiKhoanLayout.setVerticalGroup(
            pnlThemTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblThemTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        pnlSideBar.add(pnlThemTaiKhoan);

        pnlCaiDat.setBackground(new java.awt.Color(46, 49, 67));

        lblCaiDat.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        lblCaiDat.setForeground(new java.awt.Color(255, 255, 255));
        lblCaiDat.setText("Cài đặt");

        javax.swing.GroupLayout pnlCaiDatLayout = new javax.swing.GroupLayout(pnlCaiDat);
        pnlCaiDat.setLayout(pnlCaiDatLayout);
        pnlCaiDatLayout.setHorizontalGroup(
            pnlCaiDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCaiDatLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(lblCaiDat, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlCaiDatLayout.setVerticalGroup(
            pnlCaiDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblCaiDat, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        pnlSideBar.add(pnlCaiDat);

        bg.add(pnlSideBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 810));

        pnlView.setBackground(new java.awt.Color(45, 47, 62));

        javax.swing.GroupLayout pnlViewLayout = new javax.swing.GroupLayout(pnlView);
        pnlView.setLayout(pnlViewLayout);
        pnlViewLayout.setHorizontalGroup(
            pnlViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 920, Short.MAX_VALUE)
        );
        pnlViewLayout.setVerticalGroup(
            pnlViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );

        bg.add(pnlView, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, 920, 750));

        pnlHeader1.setBackground(new java.awt.Color(45, 47, 62));
        pnlHeader1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        pnlHeader1.setPreferredSize(new java.awt.Dimension(1200, 71));
        pnlHeader1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnlHeader1MouseDragged(evt);
            }
        });
        pnlHeader1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlHeader1MousePressed(evt);
            }
        });

        lblExit1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        lblExit1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblExit1MouseClicked(evt);
            }
        });

        pnlNameUser1.setBackground(new java.awt.Color(45, 47, 62));

        lblName1.setBackground(new java.awt.Color(135, 135, 135));
        lblName1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblName1.setForeground(new java.awt.Color(255, 255, 255));
        lblName1.setText("Phan Công Sơn");

        lblRole1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblRole1.setForeground(new java.awt.Color(255, 255, 255));
        lblRole1.setText("Admin");

        lblAvatar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user.png"))); // NOI18N

        javax.swing.GroupLayout pnlNameUser1Layout = new javax.swing.GroupLayout(pnlNameUser1);
        pnlNameUser1.setLayout(pnlNameUser1Layout);
        pnlNameUser1Layout.setHorizontalGroup(
            pnlNameUser1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNameUser1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlNameUser1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRole1)
                    .addComponent(lblName1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlNameUser1Layout.setVerticalGroup(
            pnlNameUser1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNameUser1Layout.createSequentialGroup()
                .addGroup(pnlNameUser1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlNameUser1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlNameUser1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblName1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblRole1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlHeader1Layout = new javax.swing.GroupLayout(pnlHeader1);
        pnlHeader1.setLayout(pnlHeader1Layout);
        pnlHeader1Layout.setHorizontalGroup(
            pnlHeader1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHeader1Layout.createSequentialGroup()
                .addContainerGap(700, Short.MAX_VALUE)
                .addComponent(pnlNameUser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblExit1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlHeader1Layout.setVerticalGroup(
            pnlHeader1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeader1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblExit1)
                .addContainerGap(36, Short.MAX_VALUE))
            .addGroup(pnlHeader1Layout.createSequentialGroup()
                .addComponent(pnlNameUser1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        bg.add(pnlHeader1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 920, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblExit1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExit1MouseClicked
        System.exit(0);
    }//GEN-LAST:event_lblExit1MouseClicked

    private void pnlHeader1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlHeader1MouseDragged
       int x = evt.getXOnScreen();
       int y = evt.getYOnScreen();
       this.setLocation(x - xMouse, y-yMouse);
    }//GEN-LAST:event_pnlHeader1MouseDragged

    private void pnlHeader1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlHeader1MousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_pnlHeader1MousePressed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel bg;
    private javax.swing.JLabel lblAvatar;
    private javax.swing.JLabel lblCaiDat;
    private javax.swing.JLabel lblDoanhThu;
    private javax.swing.JLabel lblDonHang;
    private javax.swing.JLabel lblExit1;
    private javax.swing.JLabel lblKhachHang;
    private javax.swing.JLabel lblKhuyenMai;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblName1;
    private javax.swing.JLabel lblPhieuNhapHang;
    private javax.swing.JLabel lblQLDM;
    private javax.swing.JLabel lblQLNV;
    private javax.swing.JLabel lblRole1;
    private javax.swing.JLabel lblSanPham;
    private javax.swing.JLabel lblThemTaiKhoan;
    private javax.swing.JLabel lblThongKe;
    private javax.swing.JLabel lblTongQuan;
    private javax.swing.JPanel pnlCaiDat;
    private javax.swing.JPanel pnlDoanhThu;
    private javax.swing.JPanel pnlDonHang;
    private javax.swing.JPanel pnlHeader1;
    private javax.swing.JPanel pnlKhachHang;
    private javax.swing.JPanel pnlKhuyenMai;
    private javax.swing.JPanel pnlLogo;
    private javax.swing.JPanel pnlNameUser1;
    private javax.swing.JPanel pnlPhieuNhapHang;
    private javax.swing.JPanel pnlQLDM;
    private javax.swing.JPanel pnlQLNV;
    private javax.swing.JPanel pnlSanPham;
    private javax.swing.JPanel pnlSideBar;
    private javax.swing.JPanel pnlThemTaiKhoan;
    private javax.swing.JPanel pnlThongKe;
    private javax.swing.JPanel pnlTongQuan;
    private javax.swing.JPanel pnlView;
    // End of variables declaration//GEN-END:variables
}
