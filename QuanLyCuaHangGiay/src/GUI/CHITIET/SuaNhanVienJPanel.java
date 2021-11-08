/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.CHITIET;

import DTO.NhanVienDTO;
import static GUI.CHITIET.ChiTietNhanVienJPanel.currentNhanVien;
import static GUI.Sweet.SweetFileChooser.FileExists;
import static GUI.Sweet.SweetFileChooser.PATH_NHANVIEN;
import static GUI.Sweet.SweetImage.readImageFromFilePath;
import static GUI.Sweet.SweetImage.resizeImage;
import static GUI.Sweet.SweetImage.saveImage;
import GUI.ThemTaiKhoanJPanel;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import static quanlycuahanggiay.Currency.changeCurrency;

/**
 *
 * @author admin
 */
public class SuaNhanVienJPanel extends javax.swing.JPanel {

    /**
     * Creates new form SuaNhanVienJPanel
     */
    
    public ArrayList<javax.swing.AbstractButton> arrButton =new ArrayList<>();
    public ImageIcon imgAnhDaiDien;
    public SuaNhanVienJPanel() {
        initComponents();
        
        initEvent();
        
        groupGioiTinh.add(radNam);
        groupGioiTinh.add(radNu);

        groupTrangThai.add(radConLam);
        groupTrangThai.add(radNghiLam);

        groupCaLam.add(radCa1);
        groupCaLam.add(radCa2);
        groupCaLam.add(radCa3); 
        setData();
        pnlCapNhat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnlHuyBo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    public void setData(){
    lblMaNhanVien.setText(GUI.CHITIET.ChiTietNhanVienJPanel.currentNhanVien.getMaNhanVien());
    lblNgayVaoLam.setText(quanlycuahanggiay.DateTime.TimestampToDateString(GUI.CHITIET.ChiTietNhanVienJPanel.currentNhanVien.getNgayVaoLam(),1));
    txtHoTen.setText(GUI.CHITIET.ChiTietNhanVienJPanel.currentNhanVien.getHoTen());
    txtNamSinh.setText(GUI.CHITIET.ChiTietNhanVienJPanel.currentNhanVien.getNamSinh());
    txtSoDienThoai.setText(GUI.CHITIET.ChiTietNhanVienJPanel.currentNhanVien.getSoDienThoai());
    txtDiaChi.setText(GUI.CHITIET.ChiTietNhanVienJPanel.currentNhanVien.getDiaChi());
    txtLuong.setText(changeCurrency(GUI.CHITIET.ChiTietNhanVienJPanel.currentNhanVien.getLuong()));
        Image imageScale = readImageFromFilePath(PATH_NHANVIEN + currentNhanVien.getImgSource(), 180, 240);
        imgAnhDaiDien = new ImageIcon(imageScale);
        lblAnh.setIcon(imgAnhDaiDien);
        
    setRole();
    }
        public void setRole(){
        String Role = GUI.CHITIET.ChiTietNhanVienJPanel.currentAccount.getRole();
        int i;
       for(i=0;i<Role.length();i++){
           if(Role.codePointAt(i) == 49){
               arrButton.get(i).setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/checked.png")));
               arrButton.get(i).setSelected(true);
       }
       }
       for(i=i-1;i<arrButton.size();i++){
           String Command = arrButton.get(i).getActionCommand();
           if(Command.equals(GUI.CHITIET.ChiTietNhanVienJPanel.currentNhanVien.getGioiTinh())||Command.equals(GUI.CHITIET.ChiTietNhanVienJPanel.currentNhanVien.getCaLam()) || Command.equals(GUI.CHITIET.ChiTietNhanVienJPanel.currentNhanVien.getTrangThai())){
               arrButton.get(i).setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/checked.png")));
           arrButton.get(i).setSelected(true);
        }
       }
    }
        
            public String getRole() {
        String Role = "";
        for(int i=0;i<GUI.CHITIET.ChiTietNhanVienJPanel.currentAccount.getRole().length()-1;i++){
            if(arrButton.get(i).isSelected()){
                Role +="1";
            }
            else Role+="0";
        }
        return Role += "0";
    }
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
        arrButton.add(radCa1);
        arrButton.add(radCa2);
        arrButton.add(radCa3);
        arrButton.add(radConLam);
        arrButton.add(radNghiLam);
        arrButton.add(radNam);
        arrButton.add(radNu);
        for(javax.swing.AbstractButton checkbox:arrButton){
            checkbox.setActionCommand(checkbox.getText());
            checkbox.addItemListener(new ItemListener(){
                @Override
                public void itemStateChanged(ItemEvent ie) {
                   javax.swing.AbstractButton currentItem = (javax.swing.AbstractButton) ie.getItem();
                   if(ie.getStateChange()==1)
                       currentItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/checked.png")));
                   else 
                       currentItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png")));
                }
            });
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

        groupGioiTinh = new javax.swing.ButtonGroup();
        groupTrangThai = new javax.swing.ButtonGroup();
        groupCaLam = new javax.swing.ButtonGroup();
        ThemTaiKhoanJPanel = new GUI.Rounded();
        pnlThongTin = new javax.swing.JPanel();
        lblMa = new javax.swing.JLabel();
        lblNam = new javax.swing.JLabel();
        lblTen = new javax.swing.JLabel();
        lblDienThoai = new javax.swing.JLabel();
        lblDienThoai1 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        txtNamSinh = new javax.swing.JTextField();
        txtSoDienThoai = new javax.swing.JTextField();
        lblNgayVL = new javax.swing.JLabel();
        lblNgayVaoLam = new javax.swing.JLabel();
        txtGioiTinh = new javax.swing.JLabel();
        radNam = new javax.swing.JRadioButton();
        radNu = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDiaChi = new javax.swing.JTextArea();
        lblAddImage = new javax.swing.JLabel();
        lblTen2 = new javax.swing.JLabel();
        radCa1 = new javax.swing.JRadioButton();
        radCa2 = new javax.swing.JRadioButton();
        radCa3 = new javax.swing.JRadioButton();
        lblMaNhanVien = new javax.swing.JLabel();
        lblAnh = new javax.swing.JLabel();
        pnlQuyen1 = new javax.swing.JPanel();
        lblTen1 = new javax.swing.JLabel();
        txtLuong = new javax.swing.JTextField();
        lblLuong = new javax.swing.JLabel();
        radConLam = new javax.swing.JRadioButton();
        radNghiLam = new javax.swing.JRadioButton();
        pnlQuyen = new javax.swing.JPanel();
        cbxTongQuan = new javax.swing.JCheckBox();
        cbxSanPham = new javax.swing.JCheckBox();
        cbxKhachHang = new javax.swing.JCheckBox();
        cbxDoanhThu = new javax.swing.JCheckBox();
        cbxNhapHang = new javax.swing.JCheckBox();
        cbxDonHang = new javax.swing.JCheckBox();
        cbxThongKe = new javax.swing.JCheckBox();
        cbxQuanLyNhanVien = new javax.swing.JCheckBox();
        cbxQuanLyDanhMuc = new javax.swing.JCheckBox();
        cbxKhuyenMai = new javax.swing.JCheckBox();
        pnlCapNhat = new GUI.Rounded();
        lblThem = new javax.swing.JLabel();
        pnlHuyBo = new GUI.Rounded();
        lblThem1 = new javax.swing.JLabel();

        ThemTaiKhoanJPanel.setBackground(new java.awt.Color(45, 47, 62));
        ThemTaiKhoanJPanel.setForeground(new java.awt.Color(45, 47, 62));
        ThemTaiKhoanJPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlThongTin.setBackground(new java.awt.Color(45, 47, 62));
        pnlThongTin.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin cá nhân", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlThongTin.setForeground(new java.awt.Color(45, 47, 62));

        lblMa.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblMa.setForeground(new java.awt.Color(255, 255, 255));
        lblMa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMa.setText("Mã nhân viên:");

        lblNam.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblNam.setForeground(new java.awt.Color(255, 255, 255));
        lblNam.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNam.setText("Năm sinh:");

        lblTen.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblTen.setForeground(new java.awt.Color(255, 255, 255));
        lblTen.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTen.setText("Họ tên:");

        lblDienThoai.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblDienThoai.setForeground(new java.awt.Color(255, 255, 255));
        lblDienThoai.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDienThoai.setText("Số điện thoại:");

        lblDienThoai1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblDienThoai1.setForeground(new java.awt.Color(255, 255, 255));
        lblDienThoai1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDienThoai1.setText("Địa chỉ:");

        txtHoTen.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtHoTen.setText("Phan Công Hà");
        txtHoTen.setBorder(null);
        txtHoTen.setPreferredSize(new java.awt.Dimension(150, 16));

        txtNamSinh.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtNamSinh.setText("2000");
        txtNamSinh.setBorder(null);
        txtNamSinh.setPreferredSize(new java.awt.Dimension(150, 16));

        txtSoDienThoai.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtSoDienThoai.setText("0913175755");
        txtSoDienThoai.setBorder(null);
        txtSoDienThoai.setPreferredSize(new java.awt.Dimension(150, 16));

        lblNgayVL.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblNgayVL.setForeground(new java.awt.Color(255, 255, 255));
        lblNgayVL.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNgayVL.setText("Ngày vào làm:");

        lblNgayVaoLam.setBackground(new java.awt.Color(255, 255, 255));
        lblNgayVaoLam.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 14)); // NOI18N
        lblNgayVaoLam.setForeground(new java.awt.Color(255, 255, 255));
        lblNgayVaoLam.setText("12/05/2000");

        txtGioiTinh.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtGioiTinh.setForeground(new java.awt.Color(255, 255, 255));
        txtGioiTinh.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtGioiTinh.setText("Giới tính:");

        radNam.setBackground(new java.awt.Color(45, 47, 62));
        radNam.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 12)); // NOI18N
        radNam.setForeground(new java.awt.Color(255, 255, 255));
        radNam.setText("Nam");
        radNam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N

        radNu.setBackground(new java.awt.Color(45, 47, 62));
        radNu.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 12)); // NOI18N
        radNu.setForeground(new java.awt.Color(255, 255, 255));
        radNu.setText("Nữ");
        radNu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtDiaChi.setColumns(20);
        txtDiaChi.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtDiaChi.setLineWrap(true);
        txtDiaChi.setRows(5);
        txtDiaChi.setText("120 Công Chúa Ngọc Hân\nPhường 12 Quận 11");
        txtDiaChi.setBorder(null);
        jScrollPane1.setViewportView(txtDiaChi);

        lblAddImage.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 14)); // NOI18N
        lblAddImage.setForeground(new java.awt.Color(255, 255, 255));
        lblAddImage.setText("Thêm ảnh mới");
        lblAddImage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblAddImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAddImageMouseClicked(evt);
            }
        });

        lblTen2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblTen2.setForeground(new java.awt.Color(255, 255, 255));
        lblTen2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTen2.setText("Ca làm:");

        radCa1.setBackground(new java.awt.Color(45, 47, 62));
        radCa1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        radCa1.setForeground(new java.awt.Color(255, 255, 255));
        radCa1.setText("Ca sáng");
        radCa1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N

        radCa2.setBackground(new java.awt.Color(45, 47, 62));
        radCa2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        radCa2.setForeground(new java.awt.Color(255, 255, 255));
        radCa2.setText("Ca chiều");
        radCa2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N

        radCa3.setBackground(new java.awt.Color(45, 47, 62));
        radCa3.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        radCa3.setForeground(new java.awt.Color(255, 255, 255));
        radCa3.setText("Ca tối");
        radCa3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N

        lblMaNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        lblMaNhanVien.setFont(new java.awt.Font("#9Slide03 Source Sans Pro SemiB", 0, 20)); // NOI18N
        lblMaNhanVien.setForeground(new java.awt.Color(210, 48, 44));
        lblMaNhanVien.setText("NV001");

        javax.swing.GroupLayout pnlThongTinLayout = new javax.swing.GroupLayout(pnlThongTin);
        pnlThongTin.setLayout(pnlThongTinLayout);
        pnlThongTinLayout.setHorizontalGroup(
            pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinLayout.createSequentialGroup()
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(lblAddImage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblMa)
                                .addComponent(lblNgayVL)
                                .addComponent(txtGioiTinh)
                                .addComponent(lblNam)
                                .addComponent(lblTen)
                                .addComponent(lblDienThoai1)
                                .addComponent(lblDienThoai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lblTen2))
                        .addGap(10, 10, 10)))
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(lblNgayVaoLam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNamSinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlThongTinLayout.createSequentialGroup()
                                .addComponent(radNam, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radNu))
                            .addGroup(pnlThongTinLayout.createSequentialGroup()
                                .addComponent(radCa1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radCa2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(radCa3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblMaNhanVien))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlThongTinLayout.setVerticalGroup(
            pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMa)
                            .addComponent(lblMaNhanVien))
                        .addGap(18, 18, 18)
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNgayVL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNgayVaoLam))
                        .addGap(15, 15, 15)
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGioiTinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(radNu)
                            .addComponent(radNam))
                        .addGap(15, 15, 15)
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNamSinh, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlThongTinLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(lblDienThoai))
                            .addGroup(pnlThongTinLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDienThoai1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(radCa2)
                                .addComponent(radCa1)
                                .addComponent(radCa3))
                            .addComponent(lblTen2))
                        .addGap(88, 88, 88))
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblAddImage)
                        .addGap(129, 129, 129))))
        );

        ThemTaiKhoanJPanel.add(pnlThongTin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, -1, 420));

        pnlQuyen1.setBackground(new java.awt.Color(45, 47, 62));
        pnlQuyen1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tác vụ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlQuyen1.setForeground(new java.awt.Color(45, 47, 62));

        lblTen1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblTen1.setForeground(new java.awt.Color(255, 255, 255));
        lblTen1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTen1.setText("Trạng thái:");

        txtLuong.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtLuong.setText("2000000");
        txtLuong.setBorder(null);
        txtLuong.setPreferredSize(new java.awt.Dimension(150, 16));

        lblLuong.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblLuong.setForeground(new java.awt.Color(255, 255, 255));
        lblLuong.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblLuong.setText("Mức lương:");

        radConLam.setBackground(new java.awt.Color(45, 47, 62));
        radConLam.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        radConLam.setForeground(new java.awt.Color(255, 255, 255));
        radConLam.setText("Còn làm");
        radConLam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N

        radNghiLam.setBackground(new java.awt.Color(45, 47, 62));
        radNghiLam.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        radNghiLam.setForeground(new java.awt.Color(255, 255, 255));
        radNghiLam.setText("Nghỉ làm");
        radNghiLam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N

        javax.swing.GroupLayout pnlQuyen1Layout = new javax.swing.GroupLayout(pnlQuyen1);
        pnlQuyen1.setLayout(pnlQuyen1Layout);
        pnlQuyen1Layout.setHorizontalGroup(
            pnlQuyen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuyen1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlQuyen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTen1)
                    .addComponent(lblLuong))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlQuyen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlQuyen1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(radConLam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radNghiLam))
                    .addGroup(pnlQuyen1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(txtLuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(30, 30, 30))
        );
        pnlQuyen1Layout.setVerticalGroup(
            pnlQuyen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuyen1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlQuyen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radNghiLam)
                    .addComponent(radConLam)
                    .addComponent(lblTen1))
                .addGap(18, 18, 18)
                .addGroup(pnlQuyen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLuong))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        ThemTaiKhoanJPanel.add(pnlQuyen1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 320, 290, 120));

        pnlQuyen.setBackground(new java.awt.Color(45, 47, 62));
        pnlQuyen.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tác vụ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlQuyen.setForeground(new java.awt.Color(45, 47, 62));
        pnlQuyen.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbxTongQuan.setBackground(new java.awt.Color(45, 47, 62));
        cbxTongQuan.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxTongQuan.setForeground(new java.awt.Color(255, 255, 255));
        cbxTongQuan.setText("Tổng quan");
        cbxTongQuan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        pnlQuyen.add(cbxTongQuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 38, 134, -1));

        cbxSanPham.setBackground(new java.awt.Color(45, 47, 62));
        cbxSanPham.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxSanPham.setForeground(new java.awt.Color(255, 255, 255));
        cbxSanPham.setText("Sản phẩm");
        cbxSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        pnlQuyen.add(cbxSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 132, -1));

        cbxKhachHang.setBackground(new java.awt.Color(45, 47, 62));
        cbxKhachHang.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        cbxKhachHang.setText("Khách hàng");
        cbxKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        pnlQuyen.add(cbxKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 132, -1));

        cbxDoanhThu.setBackground(new java.awt.Color(45, 47, 62));
        cbxDoanhThu.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxDoanhThu.setForeground(new java.awt.Color(255, 255, 255));
        cbxDoanhThu.setText("Doanh thu");
        cbxDoanhThu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        pnlQuyen.add(cbxDoanhThu, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 158, 134, -1));

        cbxNhapHang.setBackground(new java.awt.Color(45, 47, 62));
        cbxNhapHang.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxNhapHang.setForeground(new java.awt.Color(255, 255, 255));
        cbxNhapHang.setText("Nhập hàng");
        cbxNhapHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        pnlQuyen.add(cbxNhapHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 132, -1));

        cbxDonHang.setBackground(new java.awt.Color(45, 47, 62));
        cbxDonHang.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxDonHang.setForeground(new java.awt.Color(255, 255, 255));
        cbxDonHang.setText("Đơn hàng");
        cbxDonHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        pnlQuyen.add(cbxDonHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 78, 134, -1));

        cbxThongKe.setBackground(new java.awt.Color(45, 47, 62));
        cbxThongKe.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxThongKe.setForeground(new java.awt.Color(255, 255, 255));
        cbxThongKe.setText("Thống kê");
        cbxThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        pnlQuyen.add(cbxThongKe, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 118, 100, -1));

        cbxQuanLyNhanVien.setBackground(new java.awt.Color(45, 47, 62));
        cbxQuanLyNhanVien.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxQuanLyNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        cbxQuanLyNhanVien.setText("Quản lí nhân viên");
        cbxQuanLyNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        pnlQuyen.add(cbxQuanLyNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, -1, -1));

        cbxQuanLyDanhMuc.setBackground(new java.awt.Color(45, 47, 62));
        cbxQuanLyDanhMuc.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxQuanLyDanhMuc.setForeground(new java.awt.Color(255, 255, 255));
        cbxQuanLyDanhMuc.setText("Quản lí danh mục");
        cbxQuanLyDanhMuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        pnlQuyen.add(cbxQuanLyDanhMuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 198, -1, -1));

        cbxKhuyenMai.setBackground(new java.awt.Color(45, 47, 62));
        cbxKhuyenMai.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxKhuyenMai.setForeground(new java.awt.Color(255, 255, 255));
        cbxKhuyenMai.setText("Khuyến mãi");
        cbxKhuyenMai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        pnlQuyen.add(cbxKhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 132, -1));

        ThemTaiKhoanJPanel.add(pnlQuyen, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 20, 290, 300));

        pnlCapNhat.setBackground(new java.awt.Color(53, 55, 70));
        pnlCapNhat.setForeground(new java.awt.Color(37, 213, 54));
        pnlCapNhat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlCapNhatMouseClicked(evt);
            }
        });

        lblThem.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblThem.setForeground(new java.awt.Color(255, 255, 255));
        lblThem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThem.setText("Cập nhật");

        javax.swing.GroupLayout pnlCapNhatLayout = new javax.swing.GroupLayout(pnlCapNhat);
        pnlCapNhat.setLayout(pnlCapNhatLayout);
        pnlCapNhatLayout.setHorizontalGroup(
            pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCapNhatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblThem, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlCapNhatLayout.setVerticalGroup(
            pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblThem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        ThemTaiKhoanJPanel.add(pnlCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 480, 90, 40));

        pnlHuyBo.setBackground(new java.awt.Color(53, 55, 70));
        pnlHuyBo.setForeground(new java.awt.Color(210, 57, 44));
        pnlHuyBo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlHuyBoMouseClicked(evt);
            }
        });

        lblThem1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblThem1.setForeground(new java.awt.Color(255, 255, 255));
        lblThem1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThem1.setText("Hủy bỏ");

        javax.swing.GroupLayout pnlHuyBoLayout = new javax.swing.GroupLayout(pnlHuyBo);
        pnlHuyBo.setLayout(pnlHuyBoLayout);
        pnlHuyBoLayout.setHorizontalGroup(
            pnlHuyBoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHuyBoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblThem1, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlHuyBoLayout.setVerticalGroup(
            pnlHuyBoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblThem1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        ThemTaiKhoanJPanel.add(pnlHuyBo, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 480, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ThemTaiKhoanJPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ThemTaiKhoanJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblAddImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAddImageMouseClicked
        if (SwingUtilities.isLeftMouseButton(evt)) {
            try {
                File file =  GUI.Sweet.SweetFileChooser.ImageFileChooser();
                if(file!=null)
                lblAnh.setIcon(resizeImage(ImageIO.read(file),160,200));
            } catch (IOException ex) {
                Logger.getLogger(ThemTaiKhoanJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_lblAddImageMouseClicked

    
    private void setNhanVien(){
    GUI.CHITIET.ChiTietNhanVienJPanel.currentNhanVien.setHoTen(txtHoTen.getText());
GUI.CHITIET.ChiTietNhanVienJPanel.currentNhanVien.setNamSinh(txtNamSinh.getText());
GUI.CHITIET.ChiTietNhanVienJPanel.currentNhanVien.setSoDienThoai(txtSoDienThoai.getText());
GUI.CHITIET.ChiTietNhanVienJPanel.currentNhanVien.setDiaChi(txtDiaChi.getText());
GUI.CHITIET.ChiTietNhanVienJPanel.currentNhanVien.setLuong(txtLuong.getText().replace(".",""));
GUI.CHITIET.ChiTietNhanVienJPanel.currentNhanVien.setGioiTinh(groupGioiTinh.getSelection().getActionCommand());
GUI.CHITIET.ChiTietNhanVienJPanel.currentNhanVien.setTrangThai(groupTrangThai.getSelection().getActionCommand());
GUI.CHITIET.ChiTietNhanVienJPanel.currentNhanVien.setCaLam(groupCaLam.getSelection().getActionCommand());
GUI.CHITIET.ChiTietNhanVienJPanel.currentNhanVien.setImgSource(saveImage(lblAnh.getIcon(), lblMaNhanVien.getText()));
GUI.CHITIET.ChiTietNhanVienJPanel.currentAccount.setRole(getRole());
    
    }
    private void pnlCapNhatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCapNhatMouseClicked
               if (SwingUtilities.isLeftMouseButton(evt))
        if (   GUI.Sweet.SweetAlert.showSweetOption(new javax.swing.JFrame(), "Thông báo", "Xác nhận chỉnh sửa?", 0) == 1) {
            if (isNotFill()) {
                GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", "Không được bỏ trống", 0);
            }
            else{
                setNhanVien();
                if(quanlycuahanggiay.quanlycuahanggiay.NhanVienBUS.edit(GUI.CHITIET.ChiTietNhanVienJPanel.currentNhanVien)){
                quanlycuahanggiay.quanlycuahanggiay.AccountBUS.edit(GUI.CHITIET.ChiTietNhanVienJPanel.currentAccount);
                paint();
            }
            }
        } 
        
    }//GEN-LAST:event_pnlCapNhatMouseClicked
    private boolean isNotFill() {
        if (txtHoTen.getText().equals("") || txtNamSinh.getText().equals("") || txtSoDienThoai.getText().equals("") || txtDiaChi.getText().equals("") || txtLuong.getText().equals("")) {
            return true;
        }
        return false;
    }
    private void paint(){
    this.removeAll();
        this.setLayout(new BorderLayout());
        this.add(new ChiTietNhanVienJPanel(GUI.CHITIET.ChiTietNhanVienJPanel.currentNhanVien,GUI.CHITIET.ChiTietNhanVienJPanel.currentAccount));
        this.validate();
        this.repaint();   
    };
    private void pnlHuyBoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlHuyBoMouseClicked
               if (SwingUtilities.isLeftMouseButton(evt)){
        paint();  
               }
    }//GEN-LAST:event_pnlHuyBoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private GUI.Rounded ThemTaiKhoanJPanel;
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
    private javax.swing.ButtonGroup groupCaLam;
    private javax.swing.ButtonGroup groupGioiTinh;
    private javax.swing.ButtonGroup groupTrangThai;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAddImage;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JLabel lblDienThoai;
    private javax.swing.JLabel lblDienThoai1;
    private javax.swing.JLabel lblLuong;
    private javax.swing.JLabel lblMa;
    private javax.swing.JLabel lblMaNhanVien;
    private javax.swing.JLabel lblNam;
    private javax.swing.JLabel lblNgayVL;
    private javax.swing.JLabel lblNgayVaoLam;
    private javax.swing.JLabel lblTen;
    private javax.swing.JLabel lblTen1;
    private javax.swing.JLabel lblTen2;
    private javax.swing.JLabel lblThem;
    private javax.swing.JLabel lblThem1;
    private GUI.Rounded pnlCapNhat;
    private GUI.Rounded pnlHuyBo;
    private javax.swing.JPanel pnlQuyen;
    private javax.swing.JPanel pnlQuyen1;
    private javax.swing.JPanel pnlThongTin;
    private javax.swing.JRadioButton radCa1;
    private javax.swing.JRadioButton radCa2;
    private javax.swing.JRadioButton radCa3;
    private javax.swing.JRadioButton radConLam;
    private javax.swing.JRadioButton radNam;
    private javax.swing.JRadioButton radNghiLam;
    private javax.swing.JRadioButton radNu;
    private javax.swing.JTextArea txtDiaChi;
    private javax.swing.JLabel txtGioiTinh;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtLuong;
    private javax.swing.JTextField txtNamSinh;
    private javax.swing.JTextField txtSoDienThoai;
    // End of variables declaration//GEN-END:variables
}
