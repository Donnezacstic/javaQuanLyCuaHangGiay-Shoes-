package GUI.THEMMOI;

import DTO.ChiTietPhieuNhapDTO;
import DTO.PhieuNhapDTO;
import DTO.SanPhamDTO;
import GUI.CustomScrollBarUI;
import GUI.POPUP.ThemSanPhamVaoPhieuNhapJFrame;
import GUI.PhieuNhapHangJPanel;
import GUI.RenderTable;
import GUI.Sweet.SweetComboBox;
import GUI.Sweet.SweetAlert;
import static GUI.TongQuanJPanel.setJTableColumnsWidth;
import GUI.changeColorOfCellTable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import static quanlycuahanggiay.DateTime.DateStringToTimestamp;
import static quanlycuahanggiay.Currency.changeCurrency;

public class TaoPhieuNhapJPanel extends javax.swing.JPanel {

    private static Object[] columnNames = {"Mã sản phẩm", "Tên sản phẩm", "Size", "Màu", "Đơn giá", "Số lượng", "Tạm tính", ""};
    public static PhieuNhapDTO PhieuNhap;
    public static ArrayList<ChiTietPhieuNhapDTO> ChiTietPhieuNhapList;
    public static ArrayList<ChiTietPhieuNhapDTO> arraySearch;
    private static DefaultTableModel listTableModel;
    private static SanPhamDTO SanPhamTrongTaoPhieu;
    private static int Tong;
    SweetComboBox cbxNhaCungCap;
    SweetComboBox cbxThuongHieu;
    SweetComboBox cbxSize;
    SweetComboBox cbxMau;
    public static ThemSanPhamVaoPhieuNhapJFrame themsanphampanel;

    public TaoPhieuNhapJPanel() 
    {
        initComponents();
        ChiTietPhieuNhapList = new ArrayList();
        changeTable(tblSanPham, scpSanPham);
        ShowTable(tblSanPham, columnNames);
        setDate();
        setComboBox();
        pnlThemSanPham.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnlHuyBo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnlXacNhan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setData();
    }

    public void setData() 
    {
        lblMaPhieuNhap.setText(quanlycuahanggiay.quanlycuahanggiay.PhieuNhapBUS.getLatest());
    }

    public void changeTable(JTable table, JScrollPane scp) 
    {
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

    public static void ShowTable(JTable table, Object[] columnName) 
    {
        table.setDefaultRenderer(Object.class, new RenderTable());
        Object columnNames[] = columnName;
        listTableModel = new DefaultTableModel(columnNames, 0);
        table.setModel(listTableModel);
        setJTableColumnsWidth(tblSanPham, 884, 15, 25, 20, 10, 10, 10, 10, 5);
    }

    public static void fetchTable() 
    {
        tblSanPham.setDefaultRenderer(Object.class, new RenderTable());
        String xoa = "Xóa";
        listTableModel = new DefaultTableModel(columnNames, 0);
        ChiTietPhieuNhapList.forEach(CTPN -> {
            Vector row = new Vector();
            SanPhamDTO SanPhamTrongThemPhieu = quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.getByMaSanPham(CTPN.getMaSanPham());
            row.add(CTPN.getMaSanPham());
            row.add(SanPhamTrongThemPhieu.getTenSanPham());
            row.add(quanlycuahanggiay.quanlycuahanggiay.SizeBUS.getTenSizeByMaSize(SanPhamTrongThemPhieu.getMaSize()));
            row.add(quanlycuahanggiay.quanlycuahanggiay.MauBUS.getTenMauByMaMau(SanPhamTrongThemPhieu.getMaMau()));
            row.add(changeCurrency(CTPN.getDonGia()));
            row.add(CTPN.getSoLuong());
            row.add(changeCurrency(CTPN.getThanhTien()));
            row.add(xoa);
            listTableModel.addRow(row);
        });
        tblSanPham.setModel(listTableModel);
        setIcon();
        setJTableColumnsWidth(tblSanPham, 884, 15, 25, 20, 10, 10, 10, 10, 5);
    }

    public void setComboBox() 
    {
        String[] mau = quanlycuahanggiay.quanlycuahanggiay.MauBUS.getTenStringArray();
        cbxMau = new SweetComboBox("#353746", "#ffffff", 0, 0, 150, 30, mau);
        pnlMau.add(cbxMau);
        String[] size = quanlycuahanggiay.quanlycuahanggiay.SizeBUS.getTenStringArray();
        cbxSize = new SweetComboBox("#353746", "#ffffff", 0, 0, 150, 30, size);
        pnlSize.add(cbxSize);
        String[] thuonghieu = quanlycuahanggiay.quanlycuahanggiay.ThuongHieuBUS.getTenStringArray();
        cbxThuongHieu = new SweetComboBox("#353746", "#ffffff", 0, 0, 150, 30, thuonghieu);
        pnlThuongHieu.add(cbxThuongHieu);
        String[] nhacungcap = quanlycuahanggiay.quanlycuahanggiay.NhaCungCapBUS.getTenStringArray();
        cbxNhaCungCap = new SweetComboBox("#353746", "#ffffff", 0, 0, 170, 30, nhacungcap);
        pnlNhaCungCap.add(cbxNhaCungCap);
        cbxMau.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                if (ie.getStateChange() == ItemEvent.SELECTED) {
                    getAdvancedSearch();
                }
            }
        });
        cbxSize.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                if (ie.getStateChange() == ItemEvent.SELECTED) {
                    getAdvancedSearch();
                }
            }
        });
        cbxThuongHieu.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                if (ie.getStateChange() == ItemEvent.SELECTED) {
                    getAdvancedSearch();
                }
            }
        });
    }

    public void search() 
    {
        listTableModel.setRowCount(0);
        arraySearch.forEach(SanPham -> {
            Vector row = new Vector();
            SanPhamTrongTaoPhieu = quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.getSanPhamByMaSanPham(SanPham.getMaSanPham());
            row.add(SanPham.getMaSanPham());
            row.add(SanPhamTrongTaoPhieu.getTenSanPham());
            row.add(quanlycuahanggiay.quanlycuahanggiay.SizeBUS.getTenSizeByMaSize(SanPhamTrongTaoPhieu.getMaSize()));
            row.add(quanlycuahanggiay.quanlycuahanggiay.MauBUS.getTenMauByMaMau(SanPhamTrongTaoPhieu.getMaMau()));
            row.add(SanPham.getDonGia());
            row.add(SanPham.getSoLuong());
            row.add(SanPham.getThanhTien());
            row.add("Xóa");
            listTableModel.addRow(row);
        });
        tblSanPham.setModel(listTableModel);
        setIcon();
    }

    private void getAdvancedSearch() 
    {
        HashMap<String, String> advancedSearch = new HashMap<>();
        advancedSearch.put("MaSanPham", txtTimKiemMaSanPham.getText());
        advancedSearch.put("TenSanPham", txtTimKiemTenSanPham.getText());
        advancedSearch.put("Mau", "");
        advancedSearch.put("Size", "");
        advancedSearch.put("ThuongHieu", "");
        if (!cbxMau.getSelectedItem().toString().equals("Màu")) {
            advancedSearch.replace("Mau", quanlycuahanggiay.quanlycuahanggiay.MauBUS.getMaMau(cbxMau.getSelectedItem().toString()));
        }
        if (!cbxSize.getSelectedItem().toString().equals("Size")) {
            advancedSearch.replace("Size", quanlycuahanggiay.quanlycuahanggiay.SizeBUS.getMaSize(cbxSize.getSelectedItem().toString()));
        }
        if (!cbxThuongHieu.getSelectedItem().toString().equals("Thương hiệu")) {
            advancedSearch.replace("ThuongHieu", quanlycuahanggiay.quanlycuahanggiay.ThuongHieuBUS.getMaThuongHieu(cbxThuongHieu.getSelectedItem().toString()));
        }
        arraySearch = quanlycuahanggiay.quanlycuahanggiay.ChiTietPhieuNhapBUS.advancedSearchThemSanPham(advancedSearch);
        search();
    }

    public static void setIcon() 
    {
        for (int i = 0; i < tblSanPham.getRowCount(); i++) {
            tblSanPham.getColumnModel().getColumn(7).setCellRenderer(new changeColorOfCellTable(tblSanPham, i, 7));
        }
    }

    public void setDate() 
    {
        Date d = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        lblNgayNhap.setText(dateFormat.format(d));
    }

    public static void TinhTien() 
    {
        Tong = 0;
        ChiTietPhieuNhapList.forEach(CTPN -> {
            Tong += Integer.parseInt(CTPN.getThanhTien());
        });
        lblTongTien.setText(changeCurrency(Integer.toString(Tong)));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pnlTimKiemMaSanPham = new GUI.Rounded();
        txtTimKiemMaSanPham = new javax.swing.JTextField();
        pnlMau = new javax.swing.JPanel();
        pnlSize = new javax.swing.JPanel();
        pnlThuongHieu = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        pnlNhaCungCap = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lblNgayNhap = new javax.swing.JLabel();
        pnlTimKiemTenSanPham = new GUI.Rounded();
        txtTimKiemTenSanPham = new javax.swing.JTextField();
        lblTongTien = new javax.swing.JLabel();
        pnlThemSanPham = new GUI.Rounded();
        lblThemSanPham = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        scpSanPham = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable(){
            public boolean isCellEditable ( int row, int col)
            {
                return false;
            }

        };
        pnlXacNhan = new GUI.Rounded();
        lblXacNhan = new javax.swing.JLabel();
        pnlHuyBo = new GUI.Rounded();
        lblHuyBo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblMaPhieuNhap = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(45, 47, 62));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mã sản phẩm:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 150, -1, 40));

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(192, 192, 192));
        jLabel3.setText("Ngày nhập hàng:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, -1, 30));

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
                .addComponent(txtTimKiemMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTimKiemMaSanPhamLayout.setVerticalGroup(
            pnlTimKiemMaSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemMaSanPhamLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimKiemMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(pnlTimKiemMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 150, 160, -1));

        pnlMau.setBackground(new java.awt.Color(53, 55, 70));
        pnlMau.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

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

        add(pnlMau, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 150, 30));

        pnlSize.setBackground(new java.awt.Color(53, 55, 70));
        pnlSize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        add(pnlSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, -1, -1));

        pnlThuongHieu.setBackground(new java.awt.Color(53, 55, 70));
        pnlThuongHieu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        add(pnlThuongHieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 210, -1, -1));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Sản phẩm nhập");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tổng:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 620, -1, 30));

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(192, 192, 192));
        jLabel5.setText("Mã phiếu nhập:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, 30));

        pnlNhaCungCap.setBackground(new java.awt.Color(53, 55, 70));
        pnlNhaCungCap.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlNhaCungCap.setPreferredSize(new java.awt.Dimension(170, 30));

        javax.swing.GroupLayout pnlNhaCungCapLayout = new javax.swing.GroupLayout(pnlNhaCungCap);
        pnlNhaCungCap.setLayout(pnlNhaCungCapLayout);
        pnlNhaCungCapLayout.setHorizontalGroup(
            pnlNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );
        pnlNhaCungCapLayout.setVerticalGroup(
            pnlNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        add(pnlNhaCungCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, -1, -1));

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(192, 192, 192));
        jLabel6.setText("Nhà cung cấp:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, 30));

        lblNgayNhap.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        lblNgayNhap.setForeground(new java.awt.Color(255, 255, 255));
        lblNgayNhap.setText("01/01/2000");
        add(lblNgayNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, -1, 30));

        pnlTimKiemTenSanPham.setBackground(new java.awt.Color(45, 47, 62));
        pnlTimKiemTenSanPham.setForeground(new java.awt.Color(62, 64, 78));
        pnlTimKiemTenSanPham.setFocusable(false);

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

        javax.swing.GroupLayout pnlTimKiemTenSanPhamLayout = new javax.swing.GroupLayout(pnlTimKiemTenSanPham);
        pnlTimKiemTenSanPham.setLayout(pnlTimKiemTenSanPhamLayout);
        pnlTimKiemTenSanPhamLayout.setHorizontalGroup(
            pnlTimKiemTenSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemTenSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiemTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        pnlTimKiemTenSanPhamLayout.setVerticalGroup(
            pnlTimKiemTenSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemTenSanPhamLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimKiemTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(pnlTimKiemTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, -1, -1));

        lblTongTien.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(210, 48, 44));
        lblTongTien.setText("0");
        add(lblTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 620, -1, 30));

        pnlThemSanPham.setBackground(new java.awt.Color(45, 47, 62));
        pnlThemSanPham.setForeground(new java.awt.Color(210, 48, 44));
        pnlThemSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlThemSanPhamMouseClicked(evt);
            }
        });

        lblThemSanPham.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblThemSanPham.setForeground(new java.awt.Color(255, 255, 255));
        lblThemSanPham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThemSanPham.setText("Thêm sản phẩm");
        lblThemSanPham.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pnlThemSanPhamLayout = new javax.swing.GroupLayout(pnlThemSanPham);
        pnlThemSanPham.setLayout(pnlThemSanPhamLayout);
        pnlThemSanPhamLayout.setHorizontalGroup(
            pnlThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblThemSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );
        pnlThemSanPhamLayout.setVerticalGroup(
            pnlThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblThemSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
        );

        add(pnlThemSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 240, 150, -1));

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Tìm kiếm sản phẩm");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

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

        add(scpSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 900, 300));

        pnlXacNhan.setBackground(new java.awt.Color(45, 47, 62));
        pnlXacNhan.setForeground(new java.awt.Color(34, 212, 52));
        pnlXacNhan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlXacNhanMouseClicked(evt);
            }
        });

        lblXacNhan.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblXacNhan.setForeground(new java.awt.Color(255, 255, 255));
        lblXacNhan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblXacNhan.setText("Tạo phiếu");

        javax.swing.GroupLayout pnlXacNhanLayout = new javax.swing.GroupLayout(pnlXacNhan);
        pnlXacNhan.setLayout(pnlXacNhanLayout);
        pnlXacNhanLayout.setHorizontalGroup(
            pnlXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlXacNhanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlXacNhanLayout.setVerticalGroup(
            pnlXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlXacNhanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblXacNhan, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(pnlXacNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 690, -1, -1));

        pnlHuyBo.setBackground(new java.awt.Color(45, 47, 62));
        pnlHuyBo.setForeground(new java.awt.Color(238, 38, 64));
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
                .addGap(22, 22, 22)
                .addComponent(lblHuyBo)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        pnlHuyBoLayout.setVerticalGroup(
            pnlHuyBoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHuyBo, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
        );

        add(pnlHuyBo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 690, -1, -1));

        jSeparator1.setForeground(new java.awt.Color(56, 50, 65));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 600, 900, -1));

        lblMaPhieuNhap.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblMaPhieuNhap.setForeground(new java.awt.Color(210, 48, 44));
        lblMaPhieuNhap.setText("#PN1234");
        add(lblMaPhieuNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, -1, 30));

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(210, 48, 44));
        jLabel8.setText("vnđ");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 620, -1, 30));

        jLabel11.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Tên sản phẩm:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void pnlThemSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlThemSanPhamMouseClicked
        if (quanlycuahanggiay.quanlycuahanggiay.currentNhanVien == null) {
            SweetAlert.showSweetAlert(new JFrame(), "Truy cập giới hạn", "Truy cập bị giới hạn, vui lòng thử lại với tài khoản khác !", 2);
            return;
        } else {
            PhieuNhap = new PhieuNhapDTO();
            PhieuNhap.setMaPhieuNhap(lblMaPhieuNhap.getText());
            PhieuNhap.setMaNhanVien(quanlycuahanggiay.quanlycuahanggiay.currentNhanVien.getMaNhanVien());
            PhieuNhap.setMaNhaCungCap(quanlycuahanggiay.quanlycuahanggiay.NhaCungCapBUS.getMaNhaCungCapByTenNhaCungCap(cbxNhaCungCap.getSelectedItem().toString()));
            PhieuNhap.setNgayNhap(DateStringToTimestamp(lblNgayNhap.getText()));
            themsanphampanel = new ThemSanPhamVaoPhieuNhapJFrame();
            themsanphampanel.setVisible(true);
        }
    }//GEN-LAST:event_pnlThemSanPhamMouseClicked

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int column = tblSanPham.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / tblSanPham.getRowHeight();
        if (row < tblSanPham.getRowCount() && row >= 0 && column < tblSanPham.getColumnCount() && column >= 0) {
            if (tblSanPham.getValueAt(row, column).toString().equalsIgnoreCase("Xóa")) {
                String MaSanPham = tblSanPham.getValueAt(row, 0).toString();
                ThemSanPhamVaoPhieuNhapJFrame.removeChiTietPhieuNhap(MaSanPham);
                fetchTable();
                TinhTien();
            }
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked
private void updateIntoSanPham()
    {
        ChiTietPhieuNhapList.forEach(CTPN -> {
                SanPhamDTO sanphamnhap = quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.getByMaSanPham(CTPN.getMaSanPham());
                int soluongthem = Integer.parseInt(sanphamnhap.getSoLuong()) + Integer.parseInt(CTPN.getSoLuong());
                sanphamnhap.setSoLuong(soluongthem+"");
                quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.edit(sanphamnhap);
        });
    }
    private void pnlXacNhanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlXacNhanMouseClicked
        if (PhieuNhap == null) {
            SweetAlert.showSweetAlert(new JFrame(), "Lỗi", "Vui lòng thêm sản phẩm vào phiếu nhập", 1);
        } else {
            PhieuNhap.setTongTien(Integer.toString(Tong));
            if (!ChiTietPhieuNhapList.isEmpty()) {
                quanlycuahanggiay.quanlycuahanggiay.PhieuNhapBUS.add(PhieuNhap);
                ChiTietPhieuNhapList.forEach(CTPN -> {
                    quanlycuahanggiay.quanlycuahanggiay.ChiTietPhieuNhapBUS.add(CTPN);
                });
                SweetAlert.showSweetAlert(new JFrame(), "Thành Công", "Thêm phiếu nhập thành công", 0);
                this.removeAll();
                this.setLayout(new BorderLayout());
                this.add(new PhieuNhapHangJPanel());
                this.validate();
                this.repaint();
            } else {
                SweetAlert.showSweetAlert(new JFrame(), "Lỗi", "Không có sản phẩm trong giỏ", 1);
            }
            ThemSanPhamVaoPhieuNhapJFrame.DanhSachSanPham.forEach(ctpn->{
                SanPhamDTO sanpham = quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.getByMaSanPham(ctpn.getMaSanPham());
                sanpham.setSoLuong((Integer.parseInt(sanpham.getSoLuong())+Integer.parseInt(ctpn.getSoLuong()))+"");
                quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.edit(sanpham);
            });
            updateIntoSanPham();
            ThemSanPhamVaoPhieuNhapJFrame.DanhSachSanPham = null;

        }
    }//GEN-LAST:event_pnlXacNhanMouseClicked

    private void pnlHuyBoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlHuyBoMouseClicked
        this.removeAll();
        this.setLayout(new BorderLayout());
        this.add(new PhieuNhapHangJPanel());
        this.validate();
        this.repaint();
    }//GEN-LAST:event_pnlHuyBoMouseClicked

    private void txtTimKiemTenSanPhamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemTenSanPhamKeyPressed
        getAdvancedSearch();
    }//GEN-LAST:event_txtTimKiemTenSanPhamKeyPressed

    private void txtTimKiemMaSanPhamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemMaSanPhamKeyReleased
        getAdvancedSearch();
    }//GEN-LAST:event_txtTimKiemMaSanPhamKeyReleased

    private void txtTimKiemTenSanPhamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemTenSanPhamKeyReleased
        getAdvancedSearch();
    }//GEN-LAST:event_txtTimKiemTenSanPhamKeyReleased

    private void txtTimKiemMaSanPhamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemMaSanPhamKeyPressed
        getAdvancedSearch();
    }//GEN-LAST:event_txtTimKiemMaSanPhamKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblHuyBo;
    private javax.swing.JLabel lblMaPhieuNhap;
    private javax.swing.JLabel lblNgayNhap;
    private javax.swing.JLabel lblThemSanPham;
    private static javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblXacNhan;
    private GUI.Rounded pnlHuyBo;
    private javax.swing.JPanel pnlMau;
    private javax.swing.JPanel pnlNhaCungCap;
    private javax.swing.JPanel pnlSize;
    private GUI.Rounded pnlThemSanPham;
    private javax.swing.JPanel pnlThuongHieu;
    private GUI.Rounded pnlTimKiemMaSanPham;
    private GUI.Rounded pnlTimKiemTenSanPham;
    private GUI.Rounded pnlXacNhan;
    private javax.swing.JScrollPane scpSanPham;
    private static javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtTimKiemMaSanPham;
    private javax.swing.JTextField txtTimKiemTenSanPham;
    // End of variables declaration//GEN-END:variables
}
