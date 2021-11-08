/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.THEMMOI;

import DTO.ChiTietKhuyenMaiDTO;
import DTO.SanPhamDTO;
import DTO.SanPhamKhuyenMaiDTO;
import GUI.CustomScrollBarUI;
import GUI.KHUYENMAI.ChiTietKhuyenMaiJPanel;
import GUI.KHUYENMAI.ThongTinChiTietKhuyenMaiJPanel;
import GUI.KHUYENMAI.ThongTinChiTietKhuyenMaiTrongChuongTrinhKMJPanel;
import GUI.KhuyenMaiJPanel;
import GUI.POPUP.ThemSanPhamVaoKhuyenMaiJFrame;
import GUI.RenderTable;
import GUI.Sweet.SweetComboBox;
import static GUI.TongQuanJPanel.customTable;
import static GUI.TongQuanJPanel.setJTableColumnsWidth;
import GUI.changeColorOfCellTable;
import com.toedter.calendar.JCalendar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import static quanlycuahanggiay.DateTime.DateStringToTimestamp;
import static quanlycuahanggiay.Regexp.SoPhanTram;
import static quanlycuahanggiay.Regexp.SoTuNhien;
import static quanlycuahanggiay.Currency.changeCurrency;
import static quanlycuahanggiay.Regexp.TOITHIEUKHUYENMAI;

/**
 *
 * @author admin
 */
public class TaoMaKhuyenMaiJPanel extends javax.swing.JPanel {

    boolean option = false;
    boolean option2 = false;
    private static Object[] columnNames = {"Mã sản phẩm", "Tên sản phẩm", "Size", "Màu", "Đơn giá", "Số lượng", ""};
    SweetComboBox cbxMaChuongTrinhKhuyenMai;
    public static ChiTietKhuyenMaiDTO ChiTietKhuyenMai;
    public static ArrayList<SanPhamKhuyenMaiDTO> DanhSachSPKM;
    public static DefaultTableModel listTableModel;
    public static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Creates new form TaoMaKhuyenMaiJPanel
     */
    public void setComboBox() {
        String[] MaChuongTrinhKhuyenMai = quanlycuahanggiay.quanlycuahanggiay.ChuongTrinhKhuyenMaiBUS.getTenStringArray();
        cbxMaChuongTrinhKhuyenMai = new SweetComboBox("#353746", "#ffffff", 0, 0, 190, 30, MaChuongTrinhKhuyenMai);
        pnlMaChuongTrinhKhuyenMai.add(cbxMaChuongTrinhKhuyenMai);

    }

    public void changeTable(JTable table, JScrollPane scp) {
        table.setAutoCreateColumnsFromModel(true);
        //Custom a table
        table.getTableHeader().setBackground(Color.decode("#2D2F3E"));
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
        table.getTableHeader().setForeground(Color.decode("#C0C0C0"));
        table.getTableHeader().setPreferredSize(new Dimension(scp.getWidth(), 30));
        table.setShowHorizontalLines(true);
        table.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setShowGrid(false);
        table.setRowMargin(5);
        scp.getViewport().setBackground(Color.decode("#2D2F3E"));
        UIManager.getDefaults().put("TableHeader.cellBorder", BorderFactory.createEmptyBorder(0, 0, 0, 0));
        UIManager.getDefaults().put("Table.scrollPaneBorder", BorderFactory.createEmptyBorder(0, 0, 0, 0));
        scp.setViewportBorder(null);
        scp.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    }

    /**
     * Creates new form KhuyenMaiJPanel
     */
    public static void setIcon() {
        for (int j = 0; j < tblSanPham.getRowCount(); j++) {
            tblSanPham.getColumnModel().getColumn(6).setCellRenderer(new changeColorOfCellTable(tblSanPham, j, 6));
        }
    }

    public void setDate() {

        dateBatDau.setDateFormatString("dd/MM/yyyy");
        dateKetThuc.setDateFormatString("dd/MM/yyyy");
        dateBatDau.setDate(new Date());
        dateBatDau.setMinSelectableDate(new Date());

    }

    public static void ShowTable(JTable table, Object[] columnName) {
        table.setDefaultRenderer(Object.class, new RenderTable());
        Object columnNames[] = columnName;
        listTableModel = new DefaultTableModel(columnNames, 0);
    }

    public static void fetchTable() {
        tblSanPham.setDefaultRenderer(Object.class, new RenderTable());
        String xoa = "Xóa";
        listTableModel = new DefaultTableModel(columnNames, 0);
        DanhSachSPKM.forEach(SPKM -> {
            Vector row = new Vector();
            SanPhamDTO SanPham = quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.getByMaSanPham(SPKM.getMaSanPham());
            row.add(SPKM.getMaSanPham());
            row.add(SanPham.getTenSanPham());
            row.add(quanlycuahanggiay.quanlycuahanggiay.SizeBUS.getTenSizeByMaSize(SanPham.getMaSize()));
            row.add(quanlycuahanggiay.quanlycuahanggiay.MauBUS.getTenMauByMaMau(SanPham.getMaMau()));
            row.add(changeCurrency(SanPham.getDonGia()));
            row.add(SanPham.getSoLuong());
            row.add(xoa);
            listTableModel.addRow(row);
        });
        tblSanPham.setModel(listTableModel);
        setIcon();
        setJTableColumnsWidth(tblSanPham, 884, 15, 25, 20, 10, 10, 10, 10, 5);
    }

    public TaoMaKhuyenMaiJPanel() {
        initComponents();
        ChiTietKhuyenMai = new ChiTietKhuyenMaiDTO();
        initEvent();
        changeTable(tblSanPham, scpSanPham);
        ShowTable(tblSanPham, columnNames);
        scpSanPham.setVisible(false);
        lblDonHang.setVisible(false);
        pnlThemSanPham.setVisible(false);
        setDate();
        setComboBox();
        pnlThemSanPham.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnlHuyBo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnlXacNhan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        dateBatDau.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        dateKetThuc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblMaChiTietKhuyenMai.setText(quanlycuahanggiay.quanlycuahanggiay.ChiTietKhuyenMaiBUS.getLatest());
        ChiTietKhuyenMai.setMaChiTietKhuyenMai(lblMaChiTietKhuyenMai.getText());
    }

    public void initEvent() {
        javax.swing.ButtonGroup z = new javax.swing.ButtonGroup();
        z.add(radPhanTram);
        z.add(radGiaTien);
        txtGiaTienKhuyenMai.setEnabled(false);
        txtGiaTienKhuyenMai.setBackground(Color.decode("#EBEBE4"));
        txtPhanTram.setEnabled(false);
        txtPhanTram.setBackground(Color.decode("#EBEBE4"));
        radPhanTram.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                javax.swing.JRadioButton currentItem = (javax.swing.JRadioButton) ie.getItem();
                if (ie.getStateChange() == 1) {
                    currentItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/checked.png")));
                    txtPhanTram.setEnabled(true);
                    txtPhanTram.setBackground(Color.white);
                    scpSanPham.setVisible(true);
                    lblDonHang.setVisible(true);
                    pnlThemSanPham.setVisible(true);
                } else {
                    currentItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png")));
                }
                txtGiaTienKhuyenMai.setEnabled(false);
                txtGiaTienKhuyenMai.setText("");
                txtGiaTienKhuyenMai.setBackground(Color.decode("#dddddd"));

            }
        });
        radGiaTien.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                javax.swing.JRadioButton currentItem = (javax.swing.JRadioButton) ie.getItem();
                if (ie.getStateChange() == 1) {
                    System.out.println(ie.getItem().toString());
                    currentItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/checked.png")));
                    txtGiaTienKhuyenMai.setEnabled(true);
                    txtGiaTienKhuyenMai.setBackground(Color.white);
                    txtGiamToiDa.setEditable(false);
                    txtGiamToiDa.setText("");
                    txtGiamToiDa.setBackground(Color.decode("#dddddd"));
                    scpSanPham.setVisible(false);
                    lblDonHang.setVisible(false);
                    pnlThemSanPham.setVisible(false);

                } else {
                    currentItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png")));
                }
                txtPhanTram.setEnabled(false);
                txtPhanTram.setText("");
                txtPhanTram.setBackground(Color.decode("#dddddd"));
            }
        });

    }

    private boolean isNotFillGiaTien() {
        if (txtMaKhuyenMai.getText().equals("") || txtSoLuong.getText().equals("") || txtGiaTienKhuyenMai.getText().equals("") || txtGiaTriToiThieu.getText().equals("") || txtGiamToiDa.getText().equals("")) {
            return true;
        }
        return false;
    }

    private boolean isNotFillPhanTram() {
        if (txtMaKhuyenMai.getText().equals("") || txtSoLuong.getText().equals("") || txtPhanTram.getText().equals("") || txtGiaTriToiThieu.getText().equals("") || txtGiamToiDa.getText().equals("")) {
            return true;
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    public boolean ErrorCheck() {
        if (quanlycuahanggiay.quanlycuahanggiay.ChiTietKhuyenMaiBUS.ExistsMaCode(txtMaKhuyenMai.getText())) {
            GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", "Mã khuyến mãi đã tồn tại", 1);
            return false;
        }
        if (!SoTuNhien(txtSoLuong.getText())) {
            GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", "Số lượng không hợp lệ", 1);
            return false;
        }
        if (radGiaTien.isSelected()) {
            if (!TOITHIEUKHUYENMAI(txtGiaTienKhuyenMai.getText())) {
                GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", "Giá tiền khuyến mãi phải lớn hơn 1.000đ", 1);
                return false;
            }
        }
        if (radPhanTram.isSelected()) {
            if (!SoPhanTram(txtPhanTram.getText())) {
                GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", "Phần trăm không hợp lệ", 1);
                return false;
            }
        }
        if (!TOITHIEUKHUYENMAI(txtGiaTriToiThieu.getText())) {
            GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", "Giá trị tối thiểu phải lớn hơn 1.000đ", 1);
            return false;
        }
        
        
        return true;
    }

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
        lblDonHang = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblTitleMaKhuyenMai = new javax.swing.JLabel();
        lblTitleMaPhieuNhao = new javax.swing.JLabel();
        lblTitleHinhThucKhuyenMai = new javax.swing.JLabel();
        lblTitleMaChiTietKhuyenMai = new javax.swing.JLabel();
        lblMaChiTietKhuyenMai = new javax.swing.JLabel();
        lblTitleMaPhieuNhao4 = new javax.swing.JLabel();
        lblTitleMaPhieuNhao5 = new javax.swing.JLabel();
        lblTitleMaPhieuNhao7 = new javax.swing.JLabel();
        txtGiaTienKhuyenMai = new javax.swing.JTextField();
        txtGiamToiDa = new javax.swing.JTextField();
        lblTitleSoLuong = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        radPhanTram = new javax.swing.JRadioButton();
        radGiaTien = new javax.swing.JRadioButton();
        txtMaKhuyenMai = new javax.swing.JTextField();
        txtPhanTram = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtGiaTriToiThieu = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dateKetThuc = new com.toedter.calendar.JDateChooser();
        dateBatDau = new com.toedter.calendar.JDateChooser();
        lblTitleMaChuongTrinhKhuyenMai = new javax.swing.JLabel();
        pnlMaChuongTrinhKhuyenMai = new javax.swing.JPanel();
        pnlThemSanPham = new GUI.Rounded();
        lblThemSanPham = new javax.swing.JLabel();
        pnlXacNhan = new GUI.Rounded();
        lblXacNhan = new javax.swing.JLabel();
        pnlHuyBo = new GUI.Rounded();
        lblHuyBo = new javax.swing.JLabel();

        setBackground(new java.awt.Color(45, 47, 62));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlThongTin.setBackground(new java.awt.Color(45, 47, 62));
        pnlThongTin.setForeground(new java.awt.Color(45, 47, 62));
        pnlThongTin.setPreferredSize(new java.awt.Dimension(920, 750));
        pnlThongTin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scpSanPham.setBackground(new java.awt.Color(45, 47, 62));
        scpSanPham.setForeground(new java.awt.Color(45, 47, 62));

        tblSanPham.setAutoCreateRowSorter(true);
        tblSanPham.setBackground(new java.awt.Color(45, 47, 62));
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

        pnlThongTin.add(scpSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 920, 353));

        lblDonHang.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        lblDonHang.setForeground(new java.awt.Color(255, 255, 255));
        lblDonHang.setText("Sản phẩm sử dụng mã");
        pnlThongTin.add(lblDonHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 253, 24));

        jPanel1.setBackground(new java.awt.Color(45, 47, 62));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitleMaKhuyenMai.setForeground(new java.awt.Color(192, 192, 192));
        lblTitleMaKhuyenMai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleMaKhuyenMai.setText("Mã khuyến mãi:");
        jPanel1.add(lblTitleMaKhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, 20));

        lblTitleMaPhieuNhao.setForeground(new java.awt.Color(192, 192, 192));
        lblTitleMaPhieuNhao.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleMaPhieuNhao.setText("Thời gian kết thúc:");
        jPanel1.add(lblTitleMaPhieuNhao, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 130, 150, -1));

        lblTitleHinhThucKhuyenMai.setForeground(new java.awt.Color(192, 192, 192));
        lblTitleHinhThucKhuyenMai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleHinhThucKhuyenMai.setText("Hình thức khuyến mãi:");
        jPanel1.add(lblTitleHinhThucKhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        lblTitleMaChiTietKhuyenMai.setForeground(new java.awt.Color(192, 192, 192));
        lblTitleMaChiTietKhuyenMai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleMaChiTietKhuyenMai.setText("Mã chi tiết khuyến mãi:");
        jPanel1.add(lblTitleMaChiTietKhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 190, 30));

        lblMaChiTietKhuyenMai.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblMaChiTietKhuyenMai.setForeground(new java.awt.Color(210, 48, 44));
        lblMaChiTietKhuyenMai.setText("12345");
        jPanel1.add(lblMaChiTietKhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, -1, 30));

        lblTitleMaPhieuNhao4.setForeground(new java.awt.Color(192, 192, 192));
        lblTitleMaPhieuNhao4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleMaPhieuNhao4.setText("Giá trị sử dụng tối thiểu:");
        jPanel1.add(lblTitleMaPhieuNhao4, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, 170, -1));

        lblTitleMaPhieuNhao5.setForeground(new java.awt.Color(192, 192, 192));
        lblTitleMaPhieuNhao5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleMaPhieuNhao5.setText("Giá trị giảm tối đa:");
        jPanel1.add(lblTitleMaPhieuNhao5, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, 150, -1));

        lblTitleMaPhieuNhao7.setForeground(new java.awt.Color(192, 192, 192));
        lblTitleMaPhieuNhao7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleMaPhieuNhao7.setText("Thời gian bắt đầu:");
        jPanel1.add(lblTitleMaPhieuNhao7, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, 150, 20));

        txtGiaTienKhuyenMai.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        txtGiaTienKhuyenMai.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtGiaTienKhuyenMai.setBorder(null);
        txtGiaTienKhuyenMai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGiaTienKhuyenMaiKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGiaTienKhuyenMaiKeyReleased(evt);
            }
        });
        jPanel1.add(txtGiaTienKhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, 120, 30));

        txtGiamToiDa.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        txtGiamToiDa.setBorder(null);
        jPanel1.add(txtGiamToiDa, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, 180, 30));

        lblTitleSoLuong.setForeground(new java.awt.Color(192, 192, 192));
        lblTitleSoLuong.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleSoLuong.setText("Số lượng:");
        jPanel1.add(lblTitleSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, 20));

        txtSoLuong.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        txtSoLuong.setBorder(null);
        txtSoLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSoLuongKeyPressed(evt);
            }
        });
        jPanel1.add(txtSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, 190, 30));

        radPhanTram.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        radPhanTram.setForeground(new java.awt.Color(192, 192, 192));
        radPhanTram.setText("Phần trăm khuyến mãi:");
        radPhanTram.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        radPhanTram.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radPhanTramMouseClicked(evt);
            }
        });
        jPanel1.add(radPhanTram, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 180, -1));

        radGiaTien.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        radGiaTien.setForeground(new java.awt.Color(192, 192, 192));
        radGiaTien.setText("Giá tiền khuyến mãi:");
        radGiaTien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        jPanel1.add(radGiaTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, 170, -1));

        txtMaKhuyenMai.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        txtMaKhuyenMai.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtMaKhuyenMai.setBorder(null);
        txtMaKhuyenMai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMaKhuyenMaiKeyPressed(evt);
            }
        });
        jPanel1.add(txtMaKhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 190, 30));

        txtPhanTram.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        txtPhanTram.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPhanTram.setBorder(null);
        txtPhanTram.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPhanTramKeyPressed(evt);
            }
        });
        jPanel1.add(txtPhanTram, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, 120, 30));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(210, 48, 44));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("đ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 40, 20, -1));

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(210, 48, 44));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("%");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 180, 20, 30));

        txtGiaTriToiThieu.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        txtGiaTriToiThieu.setBorder(null);
        txtGiaTriToiThieu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGiaTriToiThieuKeyPressed(evt);
            }
        });
        jPanel1.add(txtGiaTriToiThieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 0, 180, 30));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(210, 48, 44));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("đ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 224, 20, 30));

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(210, 48, 44));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("đ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 0, 20, -1));

        dateKetThuc.setBackground(new java.awt.Color(45, 47, 62));
        jPanel1.add(dateKetThuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 120, 180, 30));

        dateBatDau.setBackground(new java.awt.Color(45, 47, 62));
        dateBatDau.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateBatDauPropertyChange(evt);
            }
        });
        jPanel1.add(dateBatDau, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 80, 180, 30));

        lblTitleMaChuongTrinhKhuyenMai.setForeground(new java.awt.Color(192, 192, 192));
        lblTitleMaChuongTrinhKhuyenMai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleMaChuongTrinhKhuyenMai.setText("Mã chương trình khuyến mãi:");
        jPanel1.add(lblTitleMaChuongTrinhKhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 190, -1));

        pnlMaChuongTrinhKhuyenMai.setBackground(new java.awt.Color(53, 55, 70));

        javax.swing.GroupLayout pnlMaChuongTrinhKhuyenMaiLayout = new javax.swing.GroupLayout(pnlMaChuongTrinhKhuyenMai);
        pnlMaChuongTrinhKhuyenMai.setLayout(pnlMaChuongTrinhKhuyenMaiLayout);
        pnlMaChuongTrinhKhuyenMaiLayout.setHorizontalGroup(
            pnlMaChuongTrinhKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlMaChuongTrinhKhuyenMaiLayout.setVerticalGroup(
            pnlMaChuongTrinhKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(pnlMaChuongTrinhKhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 190, 30));

        pnlThemSanPham.setBackground(new java.awt.Color(45, 47, 62));
        pnlThemSanPham.setForeground(new java.awt.Color(210, 48, 44));
        pnlThemSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlThemSanPhamMouseClicked(evt);
            }
        });

        lblThemSanPham.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        lblThemSanPham.setForeground(new java.awt.Color(255, 255, 255));
        lblThemSanPham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThemSanPham.setText("Thêm sản phẩm");

        javax.swing.GroupLayout pnlThemSanPhamLayout = new javax.swing.GroupLayout(pnlThemSanPham);
        pnlThemSanPham.setLayout(pnlThemSanPhamLayout);
        pnlThemSanPhamLayout.setHorizontalGroup(
            pnlThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThemSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblThemSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlThemSanPhamLayout.setVerticalGroup(
            pnlThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblThemSanPham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(pnlThemSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 210, 170, 40));

        pnlThongTin.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 920, 260));

        add(pnlThongTin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, 933, 651));

        pnlXacNhan.setBackground(new java.awt.Color(45, 47, 62));
        pnlXacNhan.setForeground(new java.awt.Color(34, 212, 52));
        pnlXacNhan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlXacNhanMouseClicked(evt);
            }
        });

        lblXacNhan.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblXacNhan.setForeground(new java.awt.Color(255, 255, 255));
        lblXacNhan.setText("Tạo mã");

        javax.swing.GroupLayout pnlXacNhanLayout = new javax.swing.GroupLayout(pnlXacNhan);
        pnlXacNhan.setLayout(pnlXacNhanLayout);
        pnlXacNhanLayout.setHorizontalGroup(
            pnlXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlXacNhanLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblXacNhan)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        pnlXacNhanLayout.setVerticalGroup(
            pnlXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblXacNhan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        add(pnlXacNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 681, -1, 50));

        pnlHuyBo.setBackground(new java.awt.Color(45, 47, 62));
        pnlHuyBo.setForeground(new java.awt.Color(238, 38, 64));
        pnlHuyBo.setPreferredSize(new java.awt.Dimension(95, 50));
        pnlHuyBo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlHuyBoMouseClicked(evt);
            }
        });

        lblHuyBo.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblHuyBo.setForeground(new java.awt.Color(255, 255, 255));
        lblHuyBo.setText("Hủy bỏ");

        javax.swing.GroupLayout pnlHuyBoLayout = new javax.swing.GroupLayout(pnlHuyBo);
        pnlHuyBo.setLayout(pnlHuyBoLayout);
        pnlHuyBoLayout.setHorizontalGroup(
            pnlHuyBoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHuyBoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblHuyBo)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        pnlHuyBoLayout.setVerticalGroup(
            pnlHuyBoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHuyBo, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        add(pnlHuyBo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 680, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int column = tblSanPham.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / tblSanPham.getRowHeight();

        if (row < tblSanPham.getRowCount() && row >= 0 && column < tblSanPham.getColumnCount() && column >= 0) {
            if (tblSanPham.getValueAt(row, column).toString().equalsIgnoreCase("Xóa")) {
                String MaSanPham = tblSanPham.getValueAt(row, 0).toString();
                for (int i = 0; i < DanhSachSPKM.size(); i++) {
                    if (DanhSachSPKM.get(i).getMaSanPham().equals(MaSanPham)) {
                        DanhSachSPKM.remove(i);
                        ThemSanPhamVaoKhuyenMaiJFrame.SanPhamList.add(quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.getByMaSanPham(MaSanPham));
                    }
                    fetchTable();
                }
            }
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void pnlHuyBoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlHuyBoMouseClicked
        this.removeAll();
        this.setLayout(new BorderLayout());
        this.add(new ChiTietKhuyenMaiJPanel());
        this.validate();
        this.repaint();
    }//GEN-LAST:event_pnlHuyBoMouseClicked

    private void pnlXacNhanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlXacNhanMouseClicked
        if (!radGiaTien.isSelected() && !radPhanTram.isSelected() && !ErrorCheck()) {
            GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", "Không được bỏ trống hình thức khuyến mãi!", 1);
            return;
        }
        if (radGiaTien.isSelected()) {
            if (SwingUtilities.isLeftMouseButton(evt)) {
                if (GUI.Sweet.SweetAlert.showSweetOption(new javax.swing.JFrame(), "Thêm mới tài khoản", "Xác nhận thêm?", 0) == 1) {
                    if (isNotFillGiaTien()) {
                        GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", "Không được bỏ trống!", 1);
                    } else {

                        ChiTietKhuyenMai = new ChiTietKhuyenMaiDTO(lblMaChiTietKhuyenMai.getText(),
                                cbxMaChuongTrinhKhuyenMai.getSelectedItem().toString(),
                                txtMaKhuyenMai.getText(),
                                "0",
                                txtGiaTienKhuyenMai.getText(),
                                txtGiaTriToiThieu.getText(),
                                txtGiamToiDa.getText(),
                                DateStringToTimestamp(dateFormat.format(dateBatDau.getDate())),
                                DateStringToTimestamp(dateFormat.format(dateKetThuc.getDate())),
                                txtSoLuong.getText(),
                                "0");
                        if (ErrorCheck()) {
                            if (quanlycuahanggiay.quanlycuahanggiay.ChiTietKhuyenMaiBUS.add(ChiTietKhuyenMai)) {
                                SanPhamKhuyenMaiDTO SanPhamKhuyenMai = new SanPhamKhuyenMaiDTO();
                                SanPhamKhuyenMai.setMaSanPham(null);
                                SanPhamKhuyenMai.setMaChiTietKhuyenMai(lblMaChiTietKhuyenMai.getText());
                                quanlycuahanggiay.quanlycuahanggiay.SanPhamKhuyenMaiBUS.add(SanPhamKhuyenMai);
                                GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Thành Công", "Lưu thành công", 0);

                                this.removeAll();
                                this.setLayout(new BorderLayout());
                                this.add(new ChiTietKhuyenMaiJPanel());
                                this.validate();
                                this.repaint();

                            }
                        }
                    }
                }
            }
        }
        if (radPhanTram.isSelected()) {
            if (SwingUtilities.isLeftMouseButton(evt)) {
                if (GUI.Sweet.SweetAlert.showSweetOption(new javax.swing.JFrame(), "Thêm mã khuyến mãi", "Xác nhận thêm?", 0) == 1) {
                    if (isNotFillPhanTram()) {
                        GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", "Không được bỏ trống", 1);
                    } else {
                        ChiTietKhuyenMai = new ChiTietKhuyenMaiDTO(
                                lblMaChiTietKhuyenMai.getText(),
                                cbxMaChuongTrinhKhuyenMai.getSelectedItem().toString(),
                                txtMaKhuyenMai.getText(),
                                txtPhanTram.getText(),
                                "0",
                                txtGiaTriToiThieu.getText(),
                                txtGiamToiDa.getText(),
                                DateStringToTimestamp(dateFormat.format(dateBatDau.getDate())),
                                DateStringToTimestamp(dateFormat.format(dateKetThuc.getDate())),
                                txtSoLuong.getText(),
                                "0");
                        if (ErrorCheck()) {
                            if (DanhSachSPKM != null) {
                                if (DanhSachSPKM.size() == quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.SanPhamList.size()) {
                                    if (quanlycuahanggiay.quanlycuahanggiay.ChiTietKhuyenMaiBUS.add(ChiTietKhuyenMai)) {
                                        SanPhamKhuyenMaiDTO SanPhamKhuyenMai = new SanPhamKhuyenMaiDTO();
                                        SanPhamKhuyenMai.setMaSanPham(null);
                                        SanPhamKhuyenMai.setMaChiTietKhuyenMai(lblMaChiTietKhuyenMai.getText());
                                        quanlycuahanggiay.quanlycuahanggiay.SanPhamKhuyenMaiBUS.add(SanPhamKhuyenMai);
                                        GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Thành Công", "Lưu thành công (Tất cả sản phẩm được áp dụng khuyến mãi cho mã chi tiết khuyến mãi: " + lblMaChiTietKhuyenMai.getText() + " )", 0);
                                        this.removeAll();
                                        this.setLayout(new BorderLayout());
                                        this.add(new ChiTietKhuyenMaiJPanel());
                                        this.validate();
                                        this.repaint();
                                    }
                                } else {
                                    if (quanlycuahanggiay.quanlycuahanggiay.ChiTietKhuyenMaiBUS.add(ChiTietKhuyenMai)) {

                                        DanhSachSPKM.forEach(SPKM -> {
                                            SanPhamKhuyenMaiDTO SanPhamKhuyenMai = new SanPhamKhuyenMaiDTO();
                                            SanPhamKhuyenMai.setMaSanPham(SPKM.getMaSanPham());
                                            SanPhamKhuyenMai.setMaChiTietKhuyenMai(SPKM.getMaChiTietKhuyenMai());
                                            quanlycuahanggiay.quanlycuahanggiay.SanPhamKhuyenMaiBUS.add(SanPhamKhuyenMai);
                                        });
                                        GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Thành Công", "Lưu thành công với các sản phẩm được chọn đã được áp dụng vào mã chi tiết khuyến mãi: " + lblMaChiTietKhuyenMai.getText(), 0);
                                        this.removeAll();
                                        this.setLayout(new BorderLayout());
                                        this.add(new ChiTietKhuyenMaiJPanel());
                                        this.validate();
                                        this.repaint();
                                    }
                                }
                            } else {
                                GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", "Danh sách sản phẩm khuyến mãi không được bỏ trống!", 1);
                                return;
                            }
                        }
                    }
                }
            }
        }
        GUI.KHUYENMAI.ChiTietKhuyenMaiJPanel.fetchAll();
    }//GEN-LAST:event_pnlXacNhanMouseClicked

    private void dateBatDauPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateBatDauPropertyChange
        Date tomorrow = new Date(dateBatDau.getDate().getTime() + (24 * 60 * 60 * 1000));
        dateKetThuc.setDate(tomorrow);
        dateKetThuc.setMinSelectableDate(tomorrow);
    }//GEN-LAST:event_dateBatDauPropertyChange

    private void pnlThemSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlThemSanPhamMouseClicked
        System.out.println("DanhSach: " + DanhSachSPKM);
        new ThemSanPhamVaoKhuyenMaiJFrame().setVisible(true);
    }//GEN-LAST:event_pnlThemSanPhamMouseClicked

    private void txtMaKhuyenMaiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaKhuyenMaiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtSoLuong.requestFocus();
        }
    }//GEN-LAST:event_txtMaKhuyenMaiKeyPressed

    private void txtSoLuongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtGiaTriToiThieu.requestFocus();
        }
    }//GEN-LAST:event_txtSoLuongKeyPressed

    private void txtGiaTriToiThieuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiaTriToiThieuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtGiamToiDa.requestFocus();
        }
    }//GEN-LAST:event_txtGiaTriToiThieuKeyPressed

    private void txtPhanTramKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPhanTramKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtGiaTriToiThieu.requestFocus();
        }
    }//GEN-LAST:event_txtPhanTramKeyPressed

    private void txtGiaTienKhuyenMaiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiaTienKhuyenMaiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtGiaTriToiThieu.requestFocus();
        }
    }//GEN-LAST:event_txtGiaTienKhuyenMaiKeyPressed

    private void radPhanTramMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radPhanTramMouseClicked
        DanhSachSPKM = new ArrayList<>();
        fetchTable();
    }//GEN-LAST:event_radPhanTramMouseClicked

    private void txtGiaTienKhuyenMaiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiaTienKhuyenMaiKeyReleased
        txtGiamToiDa.setText(txtGiaTienKhuyenMai.getText());
    }//GEN-LAST:event_txtGiaTienKhuyenMaiKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser dateBatDau;
    private com.toedter.calendar.JDateChooser dateKetThuc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblDonHang;
    private javax.swing.JLabel lblHuyBo;
    private javax.swing.JLabel lblMaChiTietKhuyenMai;
    private javax.swing.JLabel lblThemSanPham;
    private javax.swing.JLabel lblTitleHinhThucKhuyenMai;
    private javax.swing.JLabel lblTitleMaChiTietKhuyenMai;
    private javax.swing.JLabel lblTitleMaChuongTrinhKhuyenMai;
    private javax.swing.JLabel lblTitleMaKhuyenMai;
    private javax.swing.JLabel lblTitleMaPhieuNhao;
    private javax.swing.JLabel lblTitleMaPhieuNhao4;
    private javax.swing.JLabel lblTitleMaPhieuNhao5;
    private javax.swing.JLabel lblTitleMaPhieuNhao7;
    private javax.swing.JLabel lblTitleSoLuong;
    private javax.swing.JLabel lblXacNhan;
    private GUI.Rounded pnlHuyBo;
    private javax.swing.JPanel pnlMaChuongTrinhKhuyenMai;
    private GUI.Rounded pnlThemSanPham;
    private GUI.Rounded pnlThongTin;
    private GUI.Rounded pnlXacNhan;
    private javax.swing.JRadioButton radGiaTien;
    private javax.swing.JRadioButton radPhanTram;
    private javax.swing.JScrollPane scpSanPham;
    private static javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtGiaTienKhuyenMai;
    private javax.swing.JTextField txtGiaTriToiThieu;
    private javax.swing.JTextField txtGiamToiDa;
    private javax.swing.JTextField txtMaKhuyenMai;
    private javax.swing.JTextField txtPhanTram;
    private javax.swing.JTextField txtSoLuong;
    // End of variables declaration//GEN-END:variables
}
