/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static DataThongKe.DataDoanhThu.DoanhThuNgayHomNay;
import static DataThongKe.DataDoanhThu.processBar;
import static DataThongKe.DataThongKeDonHang.DonHangNgayHomNay;
import GUI.CHITIET.ChiTietDonHangTongQuanJPanel;
import controller.changeScreenController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import static quanlycuahanggiay.Currency.changeCurrency;
import static DataThongKe.DataThongKeDonHang.processBarDonHang;
import static DataThongKe.DataThongKeNhanVien.ThongKeSoLuongNhanVienConLam;

/**
 *
 * @author admin
 */
public class TongQuanJPanel extends javax.swing.JPanel {
     Object[] columnNames ={"Mã đơn hàng","Tên","Số điện thoại","Tổng tiền","Ngày lập đơn","Trạng thái"};
    /**
     * Creates new form TongQuanJPanel
     */
    public static void changeColorColumn4(JTable table,Color color){
        table.getColumnModel().getColumn(4).setCellRenderer(new ColumnColorRenderer(Color.decode("#353746"), color));
    }
    public static void changeColorColumn3(JTable table,Color color){
        table.getColumnModel().getColumn(3).setCellRenderer(new ColumnColorRenderer(Color.decode("#353746"), color));
        
    }     
    public static void customTable(JTable table,JScrollPane scp){
        table.setAutoCreateColumnsFromModel(true);
        //Custom a table
        table.getTableHeader().setBackground(Color.decode("#353746"));
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
        table.getTableHeader().setForeground(Color.decode("#C0C0C0"));
        table.getTableHeader().setPreferredSize(new Dimension(scp.getWidth(),30));
        table.setShowHorizontalLines(true);
        table.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setShowGrid(false);
        table.setRowMargin(5);
        scp.getViewport().setBackground(Color.decode("#353746"));
        UIManager.getDefaults().put("TableHeader.cellBorder" , BorderFactory.createEmptyBorder(0,0,0,0));
        UIManager.getDefaults().put("Table.scrollPaneBorder" , BorderFactory.createEmptyBorder(0,0,0,0));
        scp.setViewportBorder(null);  
        scp.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER); 
    }


    public void ShowTable(JTable table,Object[] columnName){
        int i = 0;
        table.setDefaultRenderer(Object.class, new RenderTable());
       
        ArrayList<DTO.NhanVienDTO> arList = new ArrayList<>();
        Object columnNames[] = columnName;
        DefaultTableModel listTableModel = new DefaultTableModel(columnNames,0);
       
        for(DTO.NhanVienDTO nv : arList){
        }
        table.setModel(listTableModel);

       
        
    }
    
    public static void setJTableColumnsWidth(JTable table, int tablePreferredWidth,
        double... percentages) {
        double total = 0;
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            total += percentages[i];
        }
 
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth((int) (tablePreferredWidth * (percentages[i] / total)));
    }
}    
    public TongQuanJPanel() {
        initComponents();
        float[] processBar = processBar();
        float processBarDonHang =processBarDonHang();
                new Thread(new Runnable(){
            public void run(){
                try {
                    for(float i=0;i<=100;i=i+0.5f){
                        if(i<=processBar[0]){
                            pgbDoanhThu.UpdateProgress(i);
                            pgbDoanhThu.repaint();
                        }
                        if(i<=processBarDonHang){
                            pgbDonHang.UpdateProgress(i);
                            pgbDonHang.repaint();
                        }
                        if(i<=20){
                            pgbNhanVien.UpdateProgress(i);
                            pgbNhanVien.repaint();
                        }
                        pgbSanPham.UpdateProgress(i);
                        pgbSanPham.repaint();
                        Thread.sleep(50);
                } 
                }
                catch (InterruptedException ex) {
                    Logger.getLogger(TongQuanJPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
    
    }
        ).start();
        customTable(tblDonHang,scpDonHang);
        ShowTable(tblDonHang,columnNames);
        lblSoLuongDoanhThu.setText(changeCurrency(Integer.toString(DoanhThuNgayHomNay()))+"đ");
        lblSoLuongDonHang.setText(changeCurrency(Integer.toString(DonHangNgayHomNay())));  
        lblSoLuongSanPham.setText(changeCurrency(Integer.toString(quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.SanPhamList.size())));
        lblSoLuongNhanVien.setText(changeCurrency(Integer.toString(ThongKeSoLuongNhanVienConLam())));
    }
        

    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlDonHang = new javax.swing.JPanel();
        pnlChiTietDonHang = new GUI.Rounded();
        pgbDonHang = new GUI.ProgressBarJPanel();
        pnlDonHangHomNay = new javax.swing.JPanel();
        lblDonHangHomNay = new javax.swing.JLabel();
        lblSoLuongDonHang = new javax.swing.JLabel();
        lblDonHang = new javax.swing.JLabel();
        pnlDoanhThu = new javax.swing.JPanel();
        pnlChiTietDoanhThu = new GUI.Rounded();
        pgbDoanhThu = new GUI.ProgressBarJPanel();
        pnlDoanhThuHomNay = new javax.swing.JPanel();
        lblDoanhThuHomNay = new javax.swing.JLabel();
        lblSoLuongDoanhThu = new javax.swing.JLabel();
        lblDoanhThu = new javax.swing.JLabel();
        pnlNhanVien = new javax.swing.JPanel();
        pnlChiTietNhanVien = new GUI.Rounded();
        pgbNhanVien = new GUI.ProgressBarJPanel();
        pnlNguoiDungHienCo = new javax.swing.JPanel();
        lblNhanVienHienCo = new javax.swing.JLabel();
        lblSoLuongNhanVien = new javax.swing.JLabel();
        lblNhanVien = new javax.swing.JLabel();
        pnlSanPham = new javax.swing.JPanel();
        pnlChiTietSanPham = new GUI.Rounded();
        pgbSanPham = new GUI.ProgressBarJPanel();
        pnlSanPhamHienCo = new javax.swing.JPanel();
        lblSanPhamHienCo = new javax.swing.JLabel();
        lblSoLuongSanPham = new javax.swing.JLabel();
        lblSanPham = new javax.swing.JLabel();
        pnlTblDonHangChuaXacNhan = new GUI.Rounded();
        jLabel2 = new javax.swing.JLabel();
        scpDonHang = new javax.swing.JScrollPane();
        tblDonHang = new javax.swing.JTable(){
            public boolean isCellEditable ( int row, int col)
            {
                return false;
            }
        };

        setBackground(new java.awt.Color(45, 47, 62));
        setPreferredSize(new java.awt.Dimension(920, 750));
        setRequestFocusEnabled(false);

        pnlDonHang.setBackground(new java.awt.Color(45, 47, 62));

        pnlChiTietDonHang.setBackground(new java.awt.Color(45, 47, 62));
        pnlChiTietDonHang.setForeground(new java.awt.Color(53, 55, 70));

        pgbDonHang.setBackground(new java.awt.Color(53, 55, 70));

        javax.swing.GroupLayout pgbDonHangLayout = new javax.swing.GroupLayout(pgbDonHang);
        pgbDonHang.setLayout(pgbDonHangLayout);
        pgbDonHangLayout.setHorizontalGroup(
            pgbDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
        );
        pgbDonHangLayout.setVerticalGroup(
            pgbDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 124, Short.MAX_VALUE)
        );

        pnlDonHangHomNay.setBackground(new java.awt.Color(53, 55, 70));
        pnlDonHangHomNay.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(153, 153, 153)));

        lblDonHangHomNay.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblDonHangHomNay.setForeground(new java.awt.Color(255, 255, 255));
        lblDonHangHomNay.setText("Đơn hàng hôm nay");

        lblSoLuongDonHang.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblSoLuongDonHang.setForeground(new java.awt.Color(210, 57, 44));
        lblSoLuongDonHang.setText("123.456.789đ");

        javax.swing.GroupLayout pnlDonHangHomNayLayout = new javax.swing.GroupLayout(pnlDonHangHomNay);
        pnlDonHangHomNay.setLayout(pnlDonHangHomNayLayout);
        pnlDonHangHomNayLayout.setHorizontalGroup(
            pnlDonHangHomNayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDonHangHomNayLayout.createSequentialGroup()
                .addComponent(lblDonHangHomNay)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDonHangHomNayLayout.createSequentialGroup()
                .addComponent(lblSoLuongDonHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDonHangHomNayLayout.setVerticalGroup(
            pnlDonHangHomNayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDonHangHomNayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDonHangHomNay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSoLuongDonHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlChiTietDonHangLayout = new javax.swing.GroupLayout(pnlChiTietDonHang);
        pnlChiTietDonHang.setLayout(pnlChiTietDonHangLayout);
        pnlChiTietDonHangLayout.setHorizontalGroup(
            pnlChiTietDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlChiTietDonHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDonHangHomNay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(pnlChiTietDonHangLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(pgbDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        pnlChiTietDonHangLayout.setVerticalGroup(
            pnlChiTietDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChiTietDonHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pgbDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDonHangHomNay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblDonHang.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblDonHang.setForeground(new java.awt.Color(255, 255, 255));
        lblDonHang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDonHang.setText("Đơn hàng");

        javax.swing.GroupLayout pnlDonHangLayout = new javax.swing.GroupLayout(pnlDonHang);
        pnlDonHang.setLayout(pnlDonHangLayout);
        pnlDonHangLayout.setHorizontalGroup(
            pnlDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDonHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDonHang)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlDonHangLayout.createSequentialGroup()
                .addComponent(pnlChiTietDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlDonHangLayout.setVerticalGroup(
            pnlDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDonHangLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblDonHang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlChiTietDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlDoanhThu.setBackground(new java.awt.Color(45, 47, 62));

        pnlChiTietDoanhThu.setBackground(new java.awt.Color(45, 47, 62));
        pnlChiTietDoanhThu.setForeground(new java.awt.Color(53, 55, 70));

        pgbDoanhThu.setBackground(new java.awt.Color(53, 55, 70));

        javax.swing.GroupLayout pgbDoanhThuLayout = new javax.swing.GroupLayout(pgbDoanhThu);
        pgbDoanhThu.setLayout(pgbDoanhThuLayout);
        pgbDoanhThuLayout.setHorizontalGroup(
            pgbDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
        );
        pgbDoanhThuLayout.setVerticalGroup(
            pgbDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 124, Short.MAX_VALUE)
        );

        pnlDoanhThuHomNay.setBackground(new java.awt.Color(53, 55, 70));
        pnlDoanhThuHomNay.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(153, 153, 153)));

        lblDoanhThuHomNay.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblDoanhThuHomNay.setForeground(new java.awt.Color(255, 255, 255));
        lblDoanhThuHomNay.setText("Doanh thu hôm nay");

        lblSoLuongDoanhThu.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblSoLuongDoanhThu.setForeground(new java.awt.Color(210, 57, 44));
        lblSoLuongDoanhThu.setText("123.456.789đ");

        javax.swing.GroupLayout pnlDoanhThuHomNayLayout = new javax.swing.GroupLayout(pnlDoanhThuHomNay);
        pnlDoanhThuHomNay.setLayout(pnlDoanhThuHomNayLayout);
        pnlDoanhThuHomNayLayout.setHorizontalGroup(
            pnlDoanhThuHomNayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoanhThuHomNayLayout.createSequentialGroup()
                .addComponent(lblDoanhThuHomNay)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDoanhThuHomNayLayout.createSequentialGroup()
                .addComponent(lblSoLuongDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDoanhThuHomNayLayout.setVerticalGroup(
            pnlDoanhThuHomNayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoanhThuHomNayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDoanhThuHomNay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSoLuongDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlChiTietDoanhThuLayout = new javax.swing.GroupLayout(pnlChiTietDoanhThu);
        pnlChiTietDoanhThu.setLayout(pnlChiTietDoanhThuLayout);
        pnlChiTietDoanhThuLayout.setHorizontalGroup(
            pnlChiTietDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlChiTietDoanhThuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDoanhThuHomNay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(pnlChiTietDoanhThuLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(pgbDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlChiTietDoanhThuLayout.setVerticalGroup(
            pnlChiTietDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChiTietDoanhThuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pgbDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDoanhThuHomNay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblDoanhThu.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblDoanhThu.setForeground(new java.awt.Color(255, 255, 255));
        lblDoanhThu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDoanhThu.setText("Doanh thu");

        javax.swing.GroupLayout pnlDoanhThuLayout = new javax.swing.GroupLayout(pnlDoanhThu);
        pnlDoanhThu.setLayout(pnlDoanhThuLayout);
        pnlDoanhThuLayout.setHorizontalGroup(
            pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoanhThuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDoanhThu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlDoanhThuLayout.createSequentialGroup()
                .addComponent(pnlChiTietDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );
        pnlDoanhThuLayout.setVerticalGroup(
            pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDoanhThuLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblDoanhThu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlChiTietDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlNhanVien.setBackground(new java.awt.Color(45, 47, 62));

        pnlChiTietNhanVien.setBackground(new java.awt.Color(45, 47, 62));
        pnlChiTietNhanVien.setForeground(new java.awt.Color(53, 55, 70));

        pgbNhanVien.setBackground(new java.awt.Color(53, 55, 70));

        javax.swing.GroupLayout pgbNhanVienLayout = new javax.swing.GroupLayout(pgbNhanVien);
        pgbNhanVien.setLayout(pgbNhanVienLayout);
        pgbNhanVienLayout.setHorizontalGroup(
            pgbNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
        );
        pgbNhanVienLayout.setVerticalGroup(
            pgbNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 124, Short.MAX_VALUE)
        );

        pnlNguoiDungHienCo.setBackground(new java.awt.Color(53, 55, 70));
        pnlNguoiDungHienCo.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(153, 153, 153)));

        lblNhanVienHienCo.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblNhanVienHienCo.setForeground(new java.awt.Color(255, 255, 255));
        lblNhanVienHienCo.setText("Hiện có");

        lblSoLuongNhanVien.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblSoLuongNhanVien.setForeground(new java.awt.Color(210, 57, 44));
        lblSoLuongNhanVien.setText("123.456.789");

        javax.swing.GroupLayout pnlNguoiDungHienCoLayout = new javax.swing.GroupLayout(pnlNguoiDungHienCo);
        pnlNguoiDungHienCo.setLayout(pnlNguoiDungHienCoLayout);
        pnlNguoiDungHienCoLayout.setHorizontalGroup(
            pnlNguoiDungHienCoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNguoiDungHienCoLayout.createSequentialGroup()
                .addComponent(lblNhanVienHienCo)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNguoiDungHienCoLayout.createSequentialGroup()
                .addComponent(lblSoLuongNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlNguoiDungHienCoLayout.setVerticalGroup(
            pnlNguoiDungHienCoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNguoiDungHienCoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNhanVienHienCo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSoLuongNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlChiTietNhanVienLayout = new javax.swing.GroupLayout(pnlChiTietNhanVien);
        pnlChiTietNhanVien.setLayout(pnlChiTietNhanVienLayout);
        pnlChiTietNhanVienLayout.setHorizontalGroup(
            pnlChiTietNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChiTietNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlChiTietNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlNguoiDungHienCo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlChiTietNhanVienLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(pgbNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlChiTietNhanVienLayout.setVerticalGroup(
            pnlChiTietNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChiTietNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pgbNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlNguoiDungHienCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblNhanVien.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        lblNhanVien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNhanVien.setText("Nhân viên");

        javax.swing.GroupLayout pnlNhanVienLayout = new javax.swing.GroupLayout(pnlNhanVien);
        pnlNhanVien.setLayout(pnlNhanVienLayout);
        pnlNhanVienLayout.setHorizontalGroup(
            pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNhanVien)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNhanVienLayout.createSequentialGroup()
                .addComponent(pnlChiTietNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlNhanVienLayout.setVerticalGroup(
            pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNhanVienLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblNhanVien)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlChiTietNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlSanPham.setBackground(new java.awt.Color(45, 47, 62));

        pnlChiTietSanPham.setBackground(new java.awt.Color(45, 47, 62));
        pnlChiTietSanPham.setForeground(new java.awt.Color(53, 55, 70));

        pgbSanPham.setBackground(new java.awt.Color(53, 55, 70));

        javax.swing.GroupLayout pgbSanPhamLayout = new javax.swing.GroupLayout(pgbSanPham);
        pgbSanPham.setLayout(pgbSanPhamLayout);
        pgbSanPhamLayout.setHorizontalGroup(
            pgbSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
        );
        pgbSanPhamLayout.setVerticalGroup(
            pgbSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 124, Short.MAX_VALUE)
        );

        pnlSanPhamHienCo.setBackground(new java.awt.Color(53, 55, 70));
        pnlSanPhamHienCo.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(153, 153, 153)));

        lblSanPhamHienCo.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblSanPhamHienCo.setForeground(new java.awt.Color(255, 255, 255));
        lblSanPhamHienCo.setText("Hiện có");

        lblSoLuongSanPham.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblSoLuongSanPham.setForeground(new java.awt.Color(210, 57, 44));
        lblSoLuongSanPham.setText("123.456.789");

        javax.swing.GroupLayout pnlSanPhamHienCoLayout = new javax.swing.GroupLayout(pnlSanPhamHienCo);
        pnlSanPhamHienCo.setLayout(pnlSanPhamHienCoLayout);
        pnlSanPhamHienCoLayout.setHorizontalGroup(
            pnlSanPhamHienCoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSanPhamHienCoLayout.createSequentialGroup()
                .addComponent(lblSanPhamHienCo)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSanPhamHienCoLayout.createSequentialGroup()
                .addComponent(lblSoLuongSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlSanPhamHienCoLayout.setVerticalGroup(
            pnlSanPhamHienCoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSanPhamHienCoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSanPhamHienCo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSoLuongSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlChiTietSanPhamLayout = new javax.swing.GroupLayout(pnlChiTietSanPham);
        pnlChiTietSanPham.setLayout(pnlChiTietSanPhamLayout);
        pnlChiTietSanPhamLayout.setHorizontalGroup(
            pnlChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlChiTietSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlSanPhamHienCo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(pnlChiTietSanPhamLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(pgbSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlChiTietSanPhamLayout.setVerticalGroup(
            pnlChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChiTietSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pgbSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlSanPhamHienCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblSanPham.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblSanPham.setForeground(new java.awt.Color(255, 255, 255));
        lblSanPham.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSanPham.setText("Sản phẩm");

        javax.swing.GroupLayout pnlSanPhamLayout = new javax.swing.GroupLayout(pnlSanPham);
        pnlSanPham.setLayout(pnlSanPhamLayout);
        pnlSanPhamLayout.setHorizontalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSanPham)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlSanPhamLayout.createSequentialGroup()
                .addComponent(pnlChiTietSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlSanPhamLayout.setVerticalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSanPhamLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblSanPham)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlChiTietSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlTblDonHangChuaXacNhan.setBackground(new java.awt.Color(45, 47, 62));
        pnlTblDonHangChuaXacNhan.setForeground(new java.awt.Color(53, 55, 70));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Đơn hàng gần đây");

        scpDonHang.setBackground(new java.awt.Color(53, 55, 70));

        tblDonHang.setAutoCreateRowSorter(true);
        tblDonHang.setBackground(new java.awt.Color(53, 55, 70));
        tblDonHang.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        tblDonHang.setForeground(new java.awt.Color(255, 255, 255));
        tblDonHang.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblDonHang.setFocusable(false);
        tblDonHang.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblDonHang.setRowHeight(25);
        tblDonHang.setSelectionBackground(new java.awt.Color(72, 74, 89));
        tblDonHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDonHangMouseClicked(evt);
            }
        });
        scpDonHang.setViewportView(tblDonHang);

        javax.swing.GroupLayout pnlTblDonHangChuaXacNhanLayout = new javax.swing.GroupLayout(pnlTblDonHangChuaXacNhan);
        pnlTblDonHangChuaXacNhan.setLayout(pnlTblDonHangChuaXacNhanLayout);
        pnlTblDonHangChuaXacNhanLayout.setHorizontalGroup(
            pnlTblDonHangChuaXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTblDonHangChuaXacNhanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTblDonHangChuaXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scpDonHang, javax.swing.GroupLayout.DEFAULT_SIZE, 862, Short.MAX_VALUE)
                    .addGroup(pnlTblDonHangChuaXacNhanLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlTblDonHangChuaXacNhanLayout.setVerticalGroup(
            pnlTblDonHangChuaXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTblDonHangChuaXacNhanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scpDonHang, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlTblDonHangChuaXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnlDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(pnlSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnlNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(pnlTblDonHangChuaXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblDonHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDonHangMouseClicked
        int column = tblDonHang.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/tblDonHang.getRowHeight();
        if(row < tblDonHang.getRowCount() && row >= 0 && column < tblDonHang.getColumnCount() && column >= 0){
             Object value = tblDonHang.getValueAt(row, column);
                if(value instanceof JLabel){
                JLabel label = (JLabel) value;
                
                for(int i=0;i<tblDonHang.getColumnCount()-3;i++){
                    System.out.println(tblDonHang.getModel().getValueAt(row,i));
                }
                
            }
                if(value instanceof JButton){
                JButton label = (JButton) value;
                for(int i=0;i<tblDonHang.getColumnCount()-3;i++){
                    System.out.println(tblDonHang.getModel().getValueAt(row,i));
                }
                
            }
            if(tblDonHang.getValueAt(row,column).toString().equalsIgnoreCase("xem chi tiết")){
                    for(int i=0;i<tblDonHang.getColumnCount()-3;i++){
                        this.removeAll();
                        this.setLayout(new BorderLayout());
                        this.add(new ChiTietDonHangTongQuanJPanel());
                        this.validate();
                        this.repaint();
                        
                    }
            }
        }
    }//GEN-LAST:event_tblDonHangMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblDoanhThu;
    private javax.swing.JLabel lblDoanhThuHomNay;
    private javax.swing.JLabel lblDonHang;
    private javax.swing.JLabel lblDonHangHomNay;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblNhanVienHienCo;
    private javax.swing.JLabel lblSanPham;
    private javax.swing.JLabel lblSanPhamHienCo;
    private javax.swing.JLabel lblSoLuongDoanhThu;
    private javax.swing.JLabel lblSoLuongDonHang;
    private javax.swing.JLabel lblSoLuongNhanVien;
    private javax.swing.JLabel lblSoLuongSanPham;
    private GUI.ProgressBarJPanel pgbDoanhThu;
    private GUI.ProgressBarJPanel pgbDonHang;
    private GUI.ProgressBarJPanel pgbNhanVien;
    private GUI.ProgressBarJPanel pgbSanPham;
    private GUI.Rounded pnlChiTietDoanhThu;
    private GUI.Rounded pnlChiTietDonHang;
    private GUI.Rounded pnlChiTietNhanVien;
    private GUI.Rounded pnlChiTietSanPham;
    private javax.swing.JPanel pnlDoanhThu;
    private javax.swing.JPanel pnlDoanhThuHomNay;
    private javax.swing.JPanel pnlDonHang;
    private javax.swing.JPanel pnlDonHangHomNay;
    private javax.swing.JPanel pnlNguoiDungHienCo;
    private javax.swing.JPanel pnlNhanVien;
    private javax.swing.JPanel pnlSanPham;
    private javax.swing.JPanel pnlSanPhamHienCo;
    private GUI.Rounded pnlTblDonHangChuaXacNhan;
    private javax.swing.JScrollPane scpDonHang;
    private javax.swing.JTable tblDonHang;
    // End of variables declaration//GEN-END:variables
}
class ColumnColorRenderer extends DefaultTableCellRenderer {
   Color backgroundColor, foregroundColor;
   public ColumnColorRenderer(Color backgroundColor, Color foregroundColor) {
      super();
      this.backgroundColor = backgroundColor;
      this.foregroundColor = foregroundColor;
   }
   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,   boolean hasFocus, int row, int column) {
      Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
      cell.setBackground(backgroundColor);
      cell.setForeground(foregroundColor);
      setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
      return cell;
   }
}