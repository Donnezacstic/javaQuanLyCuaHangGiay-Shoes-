/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.POPUP;

import DTO.ChiTietDonHangDTO;
import DTO.SanPhamDTO;
import DTO.MauDTO;
import DTO.SizeDTO;
import DTO.ThuongHieuDTO;
import GUI.CHITIET.ChiTietDonHangJPanel;
import GUI.RenderTable;
import GUI.Sweet.SweetComboBox;
import GUI.THEMMOI.TaoDonHangJPanel;
import static GUI.TongQuanJPanel.customTable;
import GUI.changeColorOfCellTable;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import static GUI.THEMMOI.TaoDonHangJPanel.GioHang;
import javax.swing.SwingUtilities;

import static quanlycuahanggiay.Currency.changeCurrency;

//import static GUI.POPUP.SoLuong.SoLuongMuaHopLe;

/**
 *
 * @author admin
 */
public class ThemSanPhamVaoDonHangJFrame extends javax.swing.JFrame {
    int xMouse,yMouse;
    public static DefaultTableModel listTableModel;
    public static DefaultTableModel listTableModelBottom;
    //public static ArrayList<ChiTietDonHangDTO> GioHang;
    public ArrayList<SanPhamDTO> arraySearch;
    public static ArrayList<SanPhamDTO> MangSanPham;
    public static ChiTietDonHangDTO currentChiTietDonHang;
    public static SanPhamDTO SanPham;
    SweetComboBox cbxMau, cbxTrangThai, cbxSize, cbxThuongHieu;
    Object[] columnNames ={"Mã sản phẩm","Tên sản phẩm","Size","Màu","Đơn giá","Số lượng đang có",""};
    Object[] columnNamesBottom ={"Mã sản phẩm","Tên sản phẩm","Size","Màu","Đơn giá","Số lượng","Thành tiền",""};
    /**
     * Creates new form ThemSanPhamVaoDonHangJFrame
     */
    public static void setIcon(){
        for(int j = 0 ; j<tblSanPham.getRowCount();j++){
                tblSanPham.getColumnModel().getColumn(6).setCellRenderer(new changeColorOfCellTable(tblSanPham,j,6));
        }
        
        for(int j = 0 ; j<tblSanPhamDaThem.getRowCount();j++){
        tblSanPhamDaThem.getColumnModel().getColumn(7).setCellRenderer(new changeColorOfCellTable(tblSanPhamDaThem,j,7));
        }
        
    } 
    
    public ThemSanPhamVaoDonHangJFrame() {
        initComponents();
        //GioHang = new ArrayList<>();
        customTable(tblSanPham,scpSanPham);
        ShowTable(tblSanPham,columnNames);
        customTable(tblSanPhamDaThem,scpSanPhamDaThem);
        ShowTableBottom(tblSanPhamDaThem,columnNames);
        //fetchAllBottom();
        //setIcon();
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
        
        //cbxTrangThai = new SweetComboBox("#353746","#ffffff",0,0,170,30,trangthai);
        //pnlTrangThai.add(cbxTrangThai);
        /*
        cbxTrangThai.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie){
                if(ie.getStateChange() == ItemEvent.SELECTED)
                getAdvancedSearch();
            }
        });
        */
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
    
    public void ShowTable(JTable table,Object[] columnName){
        listTableModel = new DefaultTableModel(columnNames,0);
        table.setDefaultRenderer(Object.class, new RenderTable());
        if(MangSanPham==null){
            MangSanPham = new ArrayList<>();
            quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.SanPhamList.forEach(sanpham->{
                MangSanPham.add(sanpham);
                fetchAll();
            });
        }
        else{
        fetchAll();
    }}
    
    public static void fetchAll(){
        listTableModel.setRowCount(0);    
        String themsanpham = "Thêm sản phẩm";
        quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.SanPhamList.forEach(SanPham ->{
            String Size = quanlycuahanggiay.quanlycuahanggiay.SizeBUS.getTenSizeByMaSize(SanPham.getMaSize());
            String Mau = quanlycuahanggiay.quanlycuahanggiay.MauBUS.getTenMauByMaMau(SanPham.getMaMau());
            Vector row = new Vector();
            row.add(SanPham.getMaSanPham());
            row.add(SanPham.getTenSanPham());
            row.add(Size);
            row.add(Mau);
            row.add(changeCurrency(SanPham.getDonGia()));
            row.add(SanPham.getSoLuong());
            row.add(themsanpham);
            listTableModel.addRow(row);            
        });
        tblSanPham.setModel(listTableModel);
        setIcon();
    }
    
    public void ShowTableBottom(JTable table,Object[] columnName){        
        listTableModelBottom = new DefaultTableModel(columnNamesBottom,0);
        table.setDefaultRenderer(Object.class, new RenderTable());
        fetchAllBottom();
        /*
        Object columnNames[] = columnName;
        Object dongia[] = {"3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000","3.600.000"};
        Object masanpham[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40};
        Object tensanpham[] ={"Nike ","Adidas","Nike Air","Bitis Hunter","Bitis","Nike ","Adidas","Nike Air","Bitis Hunter","Bitis","Nike ","Adidas","Nike Air","Bitis Hunter","Bitis","Nike ","Adidas","Nike Air","Bitis Hunter","Bitis","Nike ","Adidas","Nike Air","Bitis Hunter","Bitis","Nike ","Adidas","Nike Air","Bitis Hunter","Bitis","Nike ","Adidas","Nike Air","Bitis Hunter","Bitis","Nike ","Adidas","Nike Air","Bitis Hunter","Bitis","Nike ","Adidas","Nike Air","Bitis Hunter","Bitis","Nike ","Adidas","Nike Air","Bitis Hunter","Bitis","Nike ","Adidas","Nike Air","Bitis Hunter","Bitis","Nike ","Adidas","Nike Air","Bitis Hunter","Bitis","Nike ","Adidas","Nike Air","Bitis Hunter","Bitis","Nike ","Adidas","Nike Air","Bitis Hunter","Bitis","Nike ","Adidas","Nike Air","Bitis Hunter","Bitis","Nike ","Adidas","Nike Air","Bitis Hunter","Bitis"};
        Object soluong[] = {500,200,100,500,300,500,200,100,500,300,500,200,100,500,300,500,200,100,500,300,500,200,100,500,300,500,200,100,500,300,500,200,100,500,300,500,200,100,500,300,500,200,100,500,300,500,200,100,500,300,500,200,100,500,300,500,200,100,500,300,500,200,100,500,300,500,200,100,500,300,500,200,100,500,300,500,200,100,500,300};
        String xoasanpham = "Xóa sản phẩm đã thêm";
        for(int i=0 ; i<masanpham.length ; i++){
                Object row[] = {masanpham[i],tensanpham[i],dongia[i],soluong[i],xoasanpham};
                listTableModel.addRow(row);
            }
        table.setModel(listTableModel);
        */
    }
    
    public static void fetchWhenAddingGioHang(){
        for(int i=0;i<MangSanPham.size();i++){
            if(MangSanPham.get(i).getMaSanPham().equals(currentChiTietDonHang.getMaSanPham())){
                MangSanPham.remove(i);
            }
        }
    }
    
    public static void fetchAllBottom(){
        listTableModelBottom.setRowCount(0);    
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
            row.add("Xóa sản phẩm đã thêm");
            listTableModelBottom.addRow(row);            
        });
        tblSanPhamDaThem.setModel(listTableModelBottom);
        setIcon();
    }
    
    private void getAdvancedSearch(){
        HashMap<String, String> advancedSearch = new HashMap<>();
        advancedSearch.put("MaSanPham",txtTimKiemMaSanPham.getText());
        advancedSearch.put("TenSanPham",txtTimKiemTenSanPham.getText());
        advancedSearch.put("Mau","");
        advancedSearch.put("Size","");
        advancedSearch.put("ThuongHieu", "");
        advancedSearch.put("TrangThai","");  
        if(!cbxMau.getSelectedItem().equals("Màu"))
            advancedSearch.replace("Mau", quanlycuahanggiay.quanlycuahanggiay.MauBUS.getMaMau(cbxMau.getSelectedItem().toString()));
        if(!cbxSize.getSelectedItem().equals("Size"))
            advancedSearch.replace("Size", quanlycuahanggiay.quanlycuahanggiay.SizeBUS.getMaSize(cbxSize.getSelectedItem().toString()));
        if(!cbxThuongHieu.getSelectedItem().equals("Thương hiệu"))
            advancedSearch.replace("ThuongHieu", quanlycuahanggiay.quanlycuahanggiay.ThuongHieuBUS.getMaThuongHieu(cbxThuongHieu.getSelectedItem().toString()));
        arraySearch = quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.advancedSearch(advancedSearch);
        search();
    }
    
    public void search(){
        String themsanpham = "Thêm sản phẩm";
        listTableModel.setRowCount(0);
        arraySearch.forEach(SanPham ->{
            String Size = quanlycuahanggiay.quanlycuahanggiay.SizeBUS.getTenSizeByMaSize(SanPham.getMaSize());
            String Mau = quanlycuahanggiay.quanlycuahanggiay.MauBUS.getTenMauByMaMau(SanPham.getMaMau());
            Vector row = new Vector();
            row.add(SanPham.getMaSanPham());
            row.add(SanPham.getTenSanPham());
            row.add(Size);
            row.add(Mau);
            row.add(SanPham.getDonGia());
            row.add(SanPham.getSoLuong());
            row.add(themsanpham);
            listTableModel.addRow(row);
        });
        tblSanPham.setModel(listTableModel);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
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
        pnlTimKiemTenSanPham = new GUI.Rounded();
        txtTimKiemTenSanPham = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pnlTimKiemMaSanPham = new GUI.Rounded();
        txtTimKiemMaSanPham = new javax.swing.JTextField();
        pnlMau = new javax.swing.JPanel();
        pnlSize = new javax.swing.JPanel();
        pnlThuongHieu = new javax.swing.JPanel();
        pnlXacNhan = new GUI.Rounded();
        lblXacNhan = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(new java.awt.Dimension(930, 753));

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

        pnlTimKiemTenSanPham.setBackground(new java.awt.Color(45, 47, 62));
        pnlTimKiemTenSanPham.setForeground(new java.awt.Color(62, 64, 78));
        pnlTimKiemTenSanPham.setFocusable(false);

        txtTimKiemTenSanPham.setBackground(new java.awt.Color(62, 64, 78));
        txtTimKiemTenSanPham.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTimKiemTenSanPham.setForeground(new java.awt.Color(192, 192, 192));
        txtTimKiemTenSanPham.setBorder(null);
        txtTimKiemTenSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTimKiemTenSanPhamLayout.setVerticalGroup(
            pnlTimKiemTenSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemTenSanPhamLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimKiemTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(pnlTimKiemTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, -1, -1));

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mã sản phẩm:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, 30));

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tên sản phẩm:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 18, -1, 30));

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemMaSanPhamLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimKiemMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(pnlTimKiemMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, -1, -1));

        pnlMau.setBackground(new java.awt.Color(53, 55, 70));

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

        jPanel1.add(pnlMau, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 150, 30));

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

        jPanel1.add(pnlSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, -1, -1));

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

        jPanel1.add(pnlThuongHieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 120, -1, -1));

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 753, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int column = tblSanPham.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/tblSanPham.getRowHeight();
        if(row < tblSanPham.getRowCount() && row >= 0 && column < tblSanPham.getColumnCount() && column >= 0){
            //for(int i=0;i<tblSanPham.getColumnCount()-1;i++){
                //System.out.println(tblSanPham.getModel().getValueAt(row,i));
                //System.out.println(tblSanPham.getModel().getValueAt(row,0));
            //}
            if(tblSanPham.getValueAt(row, column).toString().equalsIgnoreCase("Thêm sản phẩm")){
                SanPham = new SanPhamDTO();
                SanPham = quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.getByMaSanPham(tblSanPham.getValueAt(row, 0).toString());
                if(GioHang.size()==0){
                    NewChiTietDonHang();
                    /*
                    currentChiTietDonHang = new ChiTietDonHangDTO();
                    currentChiTietDonHang.setMaDonHang(TaoDonHangJPanel.currentDonHang.getMaDonHang());
                    currentChiTietDonHang.setMaSanPham(SanPham.getMaSanPham());
                    currentChiTietDonHang.setDonGia(SanPham.getDonGia());
                    new SoLuong().setVisible(true);
                    */
                }
                else{
                    int flag = 1;
                    for(int i=0;i<GioHang.size();i++){
                        if(tblSanPham.getValueAt(row, 0).toString().equals(GioHang.get(i).getMaSanPham())){
                            flag = 0;
                            new SoLuong().setVisible(true);
                            //((javax.swing.JFrame) SwingUtilities.getWindowAncestor(GUI.THEMMOI.TaoDonHangJPanel.TSPVDHJF)).setEnabled(true);

                        }
                    }
                    if(flag==1){
                        NewChiTietDonHang();
                        /*
                        currentChiTietDonHang = new ChiTietDonHangDTO();
                        currentChiTietDonHang.setMaDonHang(TaoDonHangJPanel.currentDonHang.getMaDonHang());
                        currentChiTietDonHang.setMaSanPham(SanPham.getMaSanPham());
                        currentChiTietDonHang.setDonGia(SanPham.getDonGia());
                        new SoLuong().setVisible(true);
                        */
                    }     
                }
            }
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked
   
    public void NewChiTietDonHang(){
        currentChiTietDonHang = new ChiTietDonHangDTO();
        currentChiTietDonHang.setMaDonHang(TaoDonHangJPanel.currentDonHang.getMaDonHang());
        currentChiTietDonHang.setMaSanPham(SanPham.getMaSanPham());
        currentChiTietDonHang.setDonGia(SanPham.getDonGia());
        new SoLuong().setVisible(true);
        //((javax.swing.JFrame) SwingUtilities.getWindowAncestor(GUI.THEMMOI.TaoDonHangJPanel.TSPVDHJF)).setEnabled(true);

    }
    /*
    for(int i=0;i<MangSanPham.size();i++){
                    if(MangSanPham.get(i).getMaSanPham().equals(tblSanPham.getValueAt(row,0).toString())){
                        MangSanPham.remove(i);
                        fetchAll();
                    }
                }
    */
    
    private void tblSanPhamDaThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamDaThemMouseClicked
        int column = tblSanPhamDaThem.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/tblSanPhamDaThem.getRowHeight();

        if(row < tblSanPhamDaThem.getRowCount() && row >=0 && column<tblSanPhamDaThem.getColumnCount() && column >=0){
            if(tblSanPhamDaThem.getValueAt(row,column).toString().equalsIgnoreCase("Xóa sản phẩm đã thêm")){
                    for(int i=0;i<GioHang.size();i++){
                    String masanpham = tblSanPhamDaThem.getValueAt(row,0).toString();
                    if(GioHang.get(i).getMaSanPham().equals(masanpham)){
                        GioHang.remove(i);
                        SanPhamDTO SanPhamVuaXoa = quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.getByMaSanPham(masanpham);
                        //System.out.println(SanPhamVuaXoa.getTenSanPham());
                        MangSanPham.add(SanPhamVuaXoa);
                        fetchAllBottom();
                        fetchAll();
                        
                    }
                }
            }

        }

    }//GEN-LAST:event_tblSanPhamDaThemMouseClicked

    private void jLabel4MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y-yMouse);
    }//GEN-LAST:event_jLabel4MouseDragged

    private void jLabel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_jLabel4MousePressed

    private void pnlXacNhanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlXacNhanMouseClicked
        
        ((javax.swing.JFrame) SwingUtilities.getWindowAncestor(quanlycuahanggiay.quanlycuahanggiay.bg)).setEnabled(true);
        this.dispose();
        TaoDonHangJPanel.MoneyMoney();
        TaoDonHangJPanel.fetchAll();
    }//GEN-LAST:event_pnlXacNhanMouseClicked

    private void txtTimKiemTenSanPhamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemTenSanPhamKeyReleased
        getAdvancedSearch();
    }//GEN-LAST:event_txtTimKiemTenSanPhamKeyReleased

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ThemSanPhamVaoDonHangJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemSanPhamVaoDonHangJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemSanPhamVaoDonHangJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemSanPhamVaoDonHangJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThemSanPhamVaoDonHangJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblDonHang;
    private javax.swing.JLabel lblDonHang1;
    private javax.swing.JLabel lblXacNhan;
    private javax.swing.JPanel pnlMau;
    private javax.swing.JPanel pnlSize;
    private GUI.Rounded pnlThongTin;
    private javax.swing.JPanel pnlThuongHieu;
    private GUI.Rounded pnlTimKiemMaSanPham;
    private GUI.Rounded pnlTimKiemTenSanPham;
    private GUI.Rounded pnlXacNhan;
    private javax.swing.JScrollPane scpSanPham;
    private javax.swing.JScrollPane scpSanPhamDaThem;
    public static javax.swing.JTable tblSanPham;
    private static javax.swing.JTable tblSanPhamDaThem;
    private javax.swing.JTextField txtTimKiemMaSanPham;
    private javax.swing.JTextField txtTimKiemTenSanPham;
    // End of variables declaration//GEN-END:variables
}
