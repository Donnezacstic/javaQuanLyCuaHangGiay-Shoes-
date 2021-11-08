/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI.POPUP;

import DTO.ChiTietKhuyenMaiDTO;
import DTO.SanPhamDTO;
import DTO.SanPhamKhuyenMaiDTO;
import GUI.KhuyenMaiJPanel;
import GUI.RenderTable;
import GUI.Sweet.SweetComboBox;
import GUI.THEMMOI.TaoMaKhuyenMaiJPanel;
import static GUI.THEMMOI.TaoMaKhuyenMaiJPanel.DanhSachSPKM;
import static GUI.TongQuanJPanel.customTable;
import static GUI.TongQuanJPanel.setJTableColumnsWidth;
import GUI.changeColorOfCellTable;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import static quanlycuahanggiay.Currency.changeCurrency;

/**
 *
 * @author admin
 */
public class ThemSanPhamVaoKhuyenMaiJFrame extends javax.swing.JFrame {
    int xMouse,yMouse;
    private static Object[] columnNames ={"Mã sản phẩm","Tên sản phẩm","Size","Màu","Đơn giá","Số lượng",""};
    private static DefaultTableModel listTableModel;
    private static DefaultTableModel listTableModelBot;
    SweetComboBox cbxMau, cbxSize, cbxThuongHieu;
    public static ArrayList<SanPhamDTO> SanPhamList;
    public static ArrayList<SanPhamDTO> MangTimKiemSanPham;
    public static SanPhamKhuyenMaiDTO currentSanPhamKhuyenMai;
    /** Creates new form EditKhuyenMaiJFrame */
    public static void setIcon(){
        for(int j = 0 ; j<tblSanPham.getRowCount();j++){
                tblSanPham.getColumnModel().getColumn(6).setCellRenderer(new changeColorOfCellTable(tblSanPham,j,6));        
        }  
    }
    
    public static void setIconBottom(){
        for(int j = 0 ; j<tblSanPhamDaThem.getRowCount();j++){
            tblSanPhamDaThem.getColumnModel().getColumn(6).setCellRenderer(new changeColorOfCellTable(tblSanPhamDaThem,j,6));
        }
    }
    
    public ThemSanPhamVaoKhuyenMaiJFrame() {
         
        SwingUtilities.getWindowAncestor(quanlycuahanggiay.quanlycuahanggiay.bg).setEnabled(false);
        initComponents();
        MangTimKiemSanPham = new ArrayList<>();
        customTable(tblSanPham,scpSanPham);
        ShowTable(tblSanPham,columnNames);
        customTable(tblSanPhamDaThem,scpSanPhamDaThem);
        ShowTableBottom(tblSanPhamDaThem,columnNames);
        pnlXacNhan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setComboBox();
        setLocationRelativeTo(null);
    }
    public void setComboBox(){
         String[] mau = quanlycuahanggiay.quanlycuahanggiay.MauBUS.getTenStringArray();
        cbxMau = new SweetComboBox("#353746","#C0C0C0",0,0,150,30,mau);
        pnlMau.add(cbxMau);
        String[] size = quanlycuahanggiay.quanlycuahanggiay.SizeBUS.getTenStringArray();
        cbxSize = new SweetComboBox("#353746","#C0C0C0",0,0,150,30,size);
        pnlSize.add(cbxSize);
        String[] thuonghieu = quanlycuahanggiay.quanlycuahanggiay.ThuongHieuBUS.getTenStringArray();
        cbxThuongHieu = new SweetComboBox("#353746","#C0C0C0",0,0,150,30,thuonghieu);
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
    public void ShowTableBottom(JTable table,Object[] columnName){
        listTableModelBot = new DefaultTableModel(columnName,0);
        table.setDefaultRenderer(Object.class, new RenderTable());
        table.setModel(listTableModelBot);
        setJTableColumnsWidth(tblSanPhamDaThem,884,7,10,5,5,10,5,5);
        fetchDataBottom();
    }    
    public void ShowTable(JTable table,Object[] columnName)
    { 
        String themsanpham = "Thêm sản phẩm";
        if(SanPhamList==null)
        {
            SanPhamList = new ArrayList();
            table.setDefaultRenderer(Object.class, new RenderTable());
            Object columnNames[] = columnName;
            listTableModel = new DefaultTableModel(columnNames,0);    
            quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.SanPhamList.forEach(SanPham ->{
                SanPhamList.add(SanPham);
            });
                            System.out.println("null");
            SanPhamList.forEach(SanPham ->{
                Vector row = new Vector();
                row.add(SanPham.getMaSanPham());
                row.add(SanPham.getTenSanPham());
                row.add(quanlycuahanggiay.quanlycuahanggiay.SizeBUS.getTenSizeByMaSize(SanPham.getMaSize()));
                row.add(quanlycuahanggiay.quanlycuahanggiay.MauBUS.getTenMauByMaMau(SanPham.getMaMau()));
                row.add(changeCurrency(SanPham.getDonGia())+"đ");
                row.add(SanPham.getSoLuong());
                row.add(themsanpham);
                listTableModel.addRow(row);            
            });
            table.setModel(listTableModel); 
            setIcon();
            setJTableColumnsWidth(tblSanPhamDaThem,884,15,10,5,5,10,10,5);
        }
        else
        {
            fetchData();
        }
    } 
    private void search(){
        listTableModel.setRowCount(0);
        MangTimKiemSanPham.forEach(SanPham ->{
        Vector row = new Vector();
                row.add(SanPham.getMaSanPham());
                row.add(SanPham.getTenSanPham());
                row.add(quanlycuahanggiay.quanlycuahanggiay.SizeBUS.getTenSizeByMaSize(SanPham.getMaSize()));
                row.add(quanlycuahanggiay.quanlycuahanggiay.MauBUS.getTenMauByMaMau(SanPham.getMaMau()));
                row.add(changeCurrency(SanPham.getDonGia())+"đ");
                row.add(SanPham.getSoLuong());
                row.add("Thêm sản phẩm");
        listTableModel.addRow(row);
        });
        tblSanPham.setModel(listTableModel);
        setJTableColumnsWidth(tblSanPham,884,15,10,5,5,10,10,5);
        setIcon();
    }
    private void getAdvancedSearch(){
        HashMap<String, String> advancedSearch = new HashMap<>();
        advancedSearch.put("MaSanPham",txtTimKiemMaSanPham.getText());
        advancedSearch.put("TenSanPham",txtTimKiemTheoTenSanPham.getText());
        advancedSearch.put("Mau","");
        advancedSearch.put("Size","");
        advancedSearch.put("ThuongHieu","");
        if(!cbxMau.getSelectedItem().toString().equals("Màu"))
            advancedSearch.replace("Mau", quanlycuahanggiay.quanlycuahanggiay.MauBUS.getMaMau(cbxMau.getSelectedItem().toString()));
        if(!cbxSize.getSelectedItem().toString().equals("Size"))
            advancedSearch.replace("Size", quanlycuahanggiay.quanlycuahanggiay.SizeBUS.getMaSize(cbxSize.getSelectedItem().toString()));
        if(!cbxThuongHieu.getSelectedItem().toString().equals("Thương hiệu"))
            advancedSearch.replace("ThuongHieu", quanlycuahanggiay.quanlycuahanggiay.ThuongHieuBUS.getMaThuongHieu(cbxThuongHieu.getSelectedItem().toString()));
        MangTimKiemSanPham = quanlycuahanggiay.quanlycuahanggiay.ChiTietKhuyenMaiBUS.advancedSearchThemSanPham(advancedSearch);
        search();
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
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
        lblDonHang1 = new javax.swing.JLabel();
        scpSanPhamDaThem = new javax.swing.JScrollPane();
        tblSanPhamDaThem = new javax.swing.JTable(){
            public boolean isCellEditable ( int row, int col)
            {
                return false;
            }

        };
        pnlTimKiemTheoTenSanPham = new GUI.Rounded();
        txtTimKiemTheoTenSanPham = new javax.swing.JTextField();
        lblTimKiemTheoTenSanPham = new javax.swing.JLabel();
        pnlXacNhan = new GUI.Rounded();
        lblXacNhan = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pnlTimKiemMaSanPham = new GUI.Rounded();
        txtTimKiemMaSanPham = new javax.swing.JTextField();
        pnlMau = new javax.swing.JPanel();
        pnlSize = new javax.swing.JPanel();
        pnlThuongHieu = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(45, 47, 62));
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
        lblDonHang.setText("Tất cả sản phẩm");

        lblDonHang1.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        lblDonHang1.setForeground(new java.awt.Color(210, 48, 44));
        lblDonHang1.setText("Sản phẩm đã thêm");

        scpSanPhamDaThem.setBackground(new java.awt.Color(53, 55, 70));
        scpSanPhamDaThem.setForeground(new java.awt.Color(53, 55, 70));

        tblSanPhamDaThem.setAutoCreateRowSorter(true);
        tblSanPhamDaThem.setBackground(new java.awt.Color(53, 55, 70));
        tblSanPhamDaThem.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tblSanPhamDaThem.setForeground(new java.awt.Color(255, 255, 255));
        tblSanPhamDaThem.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblSanPhamDaThem.setFocusable(false);
        tblSanPhamDaThem.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblSanPhamDaThem.setRowHeight(25);
        tblSanPhamDaThem.setSelectionBackground(new java.awt.Color(72, 74, 89));
        tblSanPhamDaThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamDaThemMouseClicked(evt);
            }
        });
        scpSanPhamDaThem.setViewportView(tblSanPhamDaThem);

        javax.swing.GroupLayout pnlThongTinLayout = new javax.swing.GroupLayout(pnlThongTin);
        pnlThongTin.setLayout(pnlThongTinLayout);
        pnlThongTinLayout.setHorizontalGroup(
            pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scpSanPham)
                    .addComponent(scpSanPhamDaThem, javax.swing.GroupLayout.DEFAULT_SIZE, 878, Short.MAX_VALUE)
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDonHang)
                            .addComponent(lblDonHang1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlThongTinLayout.setVerticalGroup(
            pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDonHang)
                .addGap(18, 18, 18)
                .addComponent(scpSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblDonHang1)
                .addGap(18, 18, 18)
                .addComponent(scpSanPhamDaThem, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(pnlThongTin, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 158, 890, 578));

        pnlTimKiemTheoTenSanPham.setBackground(new java.awt.Color(45, 47, 62));
        pnlTimKiemTheoTenSanPham.setForeground(new java.awt.Color(62, 64, 78));
        pnlTimKiemTheoTenSanPham.setFocusable(false);

        txtTimKiemTheoTenSanPham.setBackground(new java.awt.Color(62, 64, 78));
        txtTimKiemTheoTenSanPham.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTimKiemTheoTenSanPham.setForeground(new java.awt.Color(192, 192, 192));
        txtTimKiemTheoTenSanPham.setBorder(null);
        txtTimKiemTheoTenSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemTheoTenSanPhamKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlTimKiemTheoTenSanPhamLayout = new javax.swing.GroupLayout(pnlTimKiemTheoTenSanPham);
        pnlTimKiemTheoTenSanPham.setLayout(pnlTimKiemTheoTenSanPhamLayout);
        pnlTimKiemTheoTenSanPhamLayout.setHorizontalGroup(
            pnlTimKiemTheoTenSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemTheoTenSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiemTheoTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTimKiemTheoTenSanPhamLayout.setVerticalGroup(
            pnlTimKiemTheoTenSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtTimKiemTheoTenSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(pnlTimKiemTheoTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, -1, 30));

        lblTimKiemTheoTenSanPham.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        lblTimKiemTheoTenSanPham.setForeground(new java.awt.Color(255, 255, 255));
        lblTimKiemTheoTenSanPham.setText("Tên sản phẩm:");
        jPanel1.add(lblTimKiemTheoTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 18, -1, 30));

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
        lblXacNhan.setText("Thêm hoàn tất");

        javax.swing.GroupLayout pnlXacNhanLayout = new javax.swing.GroupLayout(pnlXacNhan);
        pnlXacNhan.setLayout(pnlXacNhanLayout);
        pnlXacNhanLayout.setHorizontalGroup(
            pnlXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblXacNhan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlXacNhanLayout.setVerticalGroup(
            pnlXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlXacNhanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblXacNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(pnlXacNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(738, 20, 170, -1));

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Mã sản phẩm:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, 30));

        pnlTimKiemMaSanPham.setBackground(new java.awt.Color(45, 47, 62));
        pnlTimKiemMaSanPham.setForeground(new java.awt.Color(62, 64, 78));
        pnlTimKiemMaSanPham.setFocusable(false);

        txtTimKiemMaSanPham.setBackground(new java.awt.Color(62, 64, 78));
        txtTimKiemMaSanPham.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTimKiemMaSanPham.setForeground(new java.awt.Color(192, 192, 192));
        txtTimKiemMaSanPham.setBorder(null);
        txtTimKiemMaSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
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
            .addComponent(txtTimKiemMaSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(pnlTimKiemMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, -1, 30));

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

        jPanel1.add(pnlMau, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 150, 30));

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

        jPanel1.add(pnlSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, 150, -1));

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

        jPanel1.add(pnlThuongHieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 110, 150, -1));

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
            .addGap(0, 930, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 753, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 
    private static void createSPKM(String MaSanPham){
        currentSanPhamKhuyenMai = new SanPhamKhuyenMaiDTO();
        currentSanPhamKhuyenMai.setMaChiTietKhuyenMai(TaoMaKhuyenMaiJPanel.ChiTietKhuyenMai.getMaChiTietKhuyenMai());
        currentSanPhamKhuyenMai.setMaSanPham(MaSanPham);
    }
    
    public static void fetchDSSPWhenAddingSPKM(String masanpham)
    {
        for(int i=0;i<SanPhamList.size();i++)
            {
                if(SanPhamList.get(i).getMaSanPham().equals(masanpham))
                {
                    SanPhamList.remove(i);
                }
            } 
    }
    public static void fetchData(){
        tblSanPham.setDefaultRenderer(Object.class, new RenderTable());
        listTableModel = new DefaultTableModel(columnNames,0);    
        String themsanpham = "Thêm sản phẩm";
        SanPhamList.forEach(SanPham ->{
            
            Vector row = new Vector();
            row.add(SanPham.getMaSanPham());
            row.add(SanPham.getTenSanPham());
            row.add(quanlycuahanggiay.quanlycuahanggiay.SizeBUS.getTenSizeByMaSize(SanPham.getMaSize()));
            row.add(quanlycuahanggiay.quanlycuahanggiay.MauBUS.getTenMauByMaMau(SanPham.getMaMau()));
            row.add(changeCurrency(SanPham.getDonGia())+"đ");
            row.add(SanPham.getSoLuong());
            row.add(themsanpham);
            listTableModel.addRow(row);            
        });
        tblSanPham.setModel(listTableModel);
        setIcon();
        setJTableColumnsWidth(tblSanPhamDaThem,884,15,10,5,5,10,10,5);
    }
    public static void fetchDataBottom()
    {
        tblSanPhamDaThem.setDefaultRenderer(Object.class, new RenderTable());
        listTableModelBot = new DefaultTableModel(columnNames,0);    
        String xoasanpham = "Xóa sản phẩm đã thêm";
        DanhSachSPKM.forEach(SanPham ->{
            SanPhamDTO temp = quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.getSanPhamByMaSanPham(SanPham.getMaSanPham());
            Vector row = new Vector();
            row.add(SanPham.getMaSanPham());
            row.add(temp.getTenSanPham());
            row.add(quanlycuahanggiay.quanlycuahanggiay.SizeBUS.getTenSizeByMaSize(temp.getMaSize()));
            row.add(quanlycuahanggiay.quanlycuahanggiay.MauBUS.getTenMauByMaMau(temp.getMaMau()));
            row.add(changeCurrency(quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.getByMaSanPham(SanPham.getMaSanPham()).getDonGia())+"đ");
            row.add(quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.getByMaSanPham(SanPham.getMaSanPham()).getSoLuong());
            row.add(xoasanpham);
            listTableModelBot.addRow(row);            
        });
        tblSanPhamDaThem.setModel(listTableModelBot); 
        setIconBottom();
        setJTableColumnsWidth(tblSanPhamDaThem,884,7,10,5,5,10,7,5);
    }
    
    public static void removeSanPhamKhuyenMai(String masanpham){
        for(int i=0;i<DanhSachSPKM.size();i++)
        {
            if(DanhSachSPKM.get(i).getMaSanPham().equals(masanpham)){
                DanhSachSPKM.remove(i);
                fetchDSSPWhenRemovingSPKM(masanpham);
            }
        }
    }
    
    public static void fetchDSSPWhenRemovingSPKM(String masanpham)
    {
        SanPhamList.add(quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.getByMaSanPham(masanpham));
    }
    
    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int column = tblSanPham.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/tblSanPham.getRowHeight();
        if(tblSanPham.getValueAt(row, column).toString().equalsIgnoreCase("Thêm sản phẩm")){
            String MaSanPham = tblSanPham.getValueAt(row,0).toString();
            createSPKM(MaSanPham);  
            fetchDSSPWhenAddingSPKM(MaSanPham);
            DanhSachSPKM.add(currentSanPhamKhuyenMai);
            fetchData();
            fetchDataBottom();
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void tblSanPhamDaThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamDaThemMouseClicked
        int column = tblSanPhamDaThem.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/tblSanPhamDaThem.getRowHeight();
        
        if(row < tblSanPhamDaThem.getRowCount() && row >=0 && column<tblSanPhamDaThem.getColumnCount() && column >=0){
            String MaSanPham = tblSanPhamDaThem.getValueAt(row,0).toString();
            if(tblSanPhamDaThem.getValueAt(row,column).toString().equalsIgnoreCase("Xóa sản phẩm đã thêm"))
            {
                removeSanPhamKhuyenMai(MaSanPham);
                fetchData();
                fetchDataBottom();     
            }
        }
    }//GEN-LAST:event_tblSanPhamDaThemMouseClicked

    private void pnlXacNhanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlXacNhanMouseClicked
        if (SwingUtilities.isLeftMouseButton(evt)) {
            SwingUtilities.getWindowAncestor(quanlycuahanggiay.quanlycuahanggiay.bg).setEnabled(true);
            this.setVisible(false);
            TaoMaKhuyenMaiJPanel.fetchTable();
        }
    }//GEN-LAST:event_pnlXacNhanMouseClicked

    private void jLabel4MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y-yMouse);
    }//GEN-LAST:event_jLabel4MouseDragged

    private void jLabel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_jLabel4MousePressed

    private void txtTimKiemTheoTenSanPhamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemTheoTenSanPhamKeyReleased
        getAdvancedSearch();
    }//GEN-LAST:event_txtTimKiemTheoTenSanPhamKeyReleased

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ThemSanPhamVaoKhuyenMaiJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemSanPhamVaoKhuyenMaiJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemSanPhamVaoKhuyenMaiJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemSanPhamVaoKhuyenMaiJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThemSanPhamVaoKhuyenMaiJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblDonHang;
    private javax.swing.JLabel lblDonHang1;
    private javax.swing.JLabel lblTimKiemTheoTenSanPham;
    private javax.swing.JLabel lblXacNhan;
    private javax.swing.JPanel pnlMau;
    private javax.swing.JPanel pnlSize;
    private GUI.Rounded pnlThongTin;
    private javax.swing.JPanel pnlThuongHieu;
    private GUI.Rounded pnlTimKiemMaSanPham;
    private GUI.Rounded pnlTimKiemTheoTenSanPham;
    private GUI.Rounded pnlXacNhan;
    private javax.swing.JScrollPane scpSanPham;
    private javax.swing.JScrollPane scpSanPhamDaThem;
    private static javax.swing.JTable tblSanPham;
    private static javax.swing.JTable tblSanPhamDaThem;
    private javax.swing.JTextField txtTimKiemMaSanPham;
    private javax.swing.JTextField txtTimKiemTheoTenSanPham;
    // End of variables declaration//GEN-END:variables

}
