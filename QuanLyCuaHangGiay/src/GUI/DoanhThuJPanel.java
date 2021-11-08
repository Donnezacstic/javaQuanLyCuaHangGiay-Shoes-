/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DataThongKe.DataDoanhThu;
import GUI.Sweet.SweetComboBox;
import static controller.createChart.createBarChartVertical;
import java.awt.Cursor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import static quanlycuahanggiay.DateTime.setDate;
import static DataThongKe.DataDoanhThu.DoanhThuThang;
import static DataThongKe.DataDoanhThu.DoanhThuQuy;
import static DataThongKe.DataDoanhThu.DoanhThuNam;
import static DataThongKe.DataDoanhThu.DoanhThuNgay;
import static DataThongKe.DataDoanhThu.DoanhThuTuDen;
import static DataThongKe.DataDoanhThu.processBar;
import static DataThongKe.DataDoanhThu.DoanhThuNgayHomNay;
import static DataThongKe.DataDoanhThu.DoanhThuTuanNay;
import static DataThongKe.DataDoanhThu.DoanhThuThangNay;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import static DataThongKe.ChartData.createDataChartPresentDoanhThu;
import GUI.Sweet.SweetAlert;
import GUI.Sweet.SweetFileChooser;
import IO.Excel.DoanhThuQuyExcel;
import IO.Excel.DoanhThuThangExcel;
import java.io.IOException;
import static quanlycuahanggiay.Currency.changeCurrency;
        /**
 *
 * @author admin
 */
public class DoanhThuJPanel extends javax.swing.JPanel {

    /**
     * Creates new form DoanhThuJPanel
     */
    SweetComboBox cbxNam;
    SweetComboBox cbxNamQuy;
    public void runningRound(){
        float[] process = processBar();
                new Thread(new Runnable(){
            public void run(){
                try {
                    for(float i = 0;i<100;i=i+0.5f){
                    if(i<=process[0]){
                        pgbDoanhThu.UpdateProgress(i);
                        pgbDoanhThu.repaint();
                    }
                    if(i<=process[1]){
                        pgbDoanhThuTuanNay.UpdateProgress(i);
                        pgbDoanhThuTuanNay.repaint();
                    }
                    if(i<=process[2]){
                        pgbDoanhThuThangNay.UpdateProgress(i);
                        pgbDoanhThuThangNay.repaint();
                    }
                        Thread.sleep(50);
                    }
                }
                catch (InterruptedException ex) {
                    Logger.getLogger(TongQuanJPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
    
    }
        ).start();        
    }
    public void setComboBox(){
        String[] namthang = {"2020","2019","2018","2017"};
        SweetComboBox cbxNamThang = new SweetComboBox("#ffffff","#181818",0,0,140,30,namthang);
        String[] thang = {"01","02","03","04","05","06","07","08","09","10","11","12"};
        SweetComboBox cbxThang = new SweetComboBox("#ffffff","#181818",0,0,140,30,thang);
        pnlNamThang.add(cbxNamThang);
        pnlThang.add(cbxThang);
        cbxNamThang.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
             int[] doanhThu = DoanhThuThang(cbxNamThang,cbxThang);
             lblDoanhThuNamThang.setText(changeCurrency(Integer.toString(doanhThu[0]))+"đ");
             lblChiNamThang.setText(changeCurrency(Integer.toString(doanhThu[1]))+"đ");
             int loiNhuan = doanhThu[0]-doanhThu[1];
             if(loiNhuan<0){
                 loiNhuan=0;
                 lblLoiNhuanNamThang.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
             }
             else{
                 lblLoiNhuanNamThang.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
             }
             
            }
        });
        cbxThang.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
             int[] doanhThu = DoanhThuThang(cbxNamThang,cbxThang);
             lblDoanhThuNamThang.setText(changeCurrency(Integer.toString(doanhThu[0]))+"đ");
             lblChiNamThang.setText(changeCurrency(Integer.toString(doanhThu[1]))+"đ");
             int loiNhuan = doanhThu[0]-doanhThu[1];
             if(loiNhuan<0){
                 loiNhuan=0;
                 lblLoiNhuanNamThang.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
             }
             else{
                 lblLoiNhuanNamThang.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
             }
            }
        });
        cbxNamThang.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                int[] doanhThu = DoanhThuThang(cbxNamThang,cbxThang);
                lblDoanhThuNamThang.setText(changeCurrency(Integer.toString(doanhThu[0]))+"đ");
                lblChiNamThang.setText(changeCurrency(Integer.toString(doanhThu[1]))+"đ");
                int loiNhuan = doanhThu[0]-doanhThu[1];
                if(loiNhuan<0){
                    loiNhuan=0;
                    lblLoiNhuanNamThang.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
                }
                else{
                    lblLoiNhuanNamThang.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
                }
            }
        });
        cbxThang.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                int[] doanhThu = DoanhThuThang(cbxNamThang,cbxThang);
                lblDoanhThuNamThang.setText(changeCurrency(Integer.toString(doanhThu[0]))+"đ");
                lblChiNamThang.setText(changeCurrency(Integer.toString(doanhThu[1]))+"đ");
                int loiNhuan = doanhThu[0]-doanhThu[1];
                if(loiNhuan<0){
                    loiNhuan=0;
                    lblLoiNhuanNamThang.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
                }
                else{
                    lblLoiNhuanNamThang.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
                }
            }
        });
        
        
        
        String[] namquy = {"2020","2019","2018","2017"};
        cbxNamQuy = new SweetComboBox("#ffffff","#181818",0,0,140,30,namquy);   
        String[] quy = {"1","2","3","4"};
        SweetComboBox cbxQuy = new SweetComboBox("#ffffff","#181818",0,0,140,30,quy);
        pnlNamQuy.add(cbxNamQuy);
        pnlQuy.add(cbxQuy);
        cbxNamQuy.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
             int[] doanhThu = DoanhThuQuy(cbxNamQuy,cbxQuy);
             lblDoanhThuNamQuy.setText(changeCurrency(Integer.toString(doanhThu[0]))+"đ");
             lblChiNamQuy.setText(changeCurrency(Integer.toString(doanhThu[1]))+"đ");
             int loiNhuan = doanhThu[0]-doanhThu[1];
             if(loiNhuan<0){
                 loiNhuan=0;
                 lblLoiNhuanNamQuy.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
             }
             else{
                 lblLoiNhuanNamQuy.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
             }
             
            }
        });
        cbxQuy.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
             int[] doanhThu = DoanhThuQuy(cbxNamQuy,cbxQuy);
             lblDoanhThuNamQuy.setText(changeCurrency(Integer.toString(doanhThu[0]))+"đ");
             lblChiNamQuy.setText(changeCurrency(Integer.toString(doanhThu[1]))+"đ");
             int loiNhuan = doanhThu[0]-doanhThu[1];
             if(loiNhuan<0){
                 loiNhuan=0;
                 lblLoiNhuanNamQuy.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
             }
             else{
                 lblLoiNhuanNamQuy.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
             }
            }
        });
        cbxNamQuy.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                int[] doanhThu = DoanhThuQuy(cbxNamQuy,cbxQuy);
                lblDoanhThuNamQuy.setText(changeCurrency(Integer.toString(doanhThu[0]))+"đ");
                lblChiNamQuy.setText(changeCurrency(Integer.toString(doanhThu[1]))+"đ");
                int loiNhuan = doanhThu[0]-doanhThu[1];
                if(loiNhuan<0){
                    loiNhuan=0;
                    lblLoiNhuanNamQuy.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
                }
                else{
                    lblLoiNhuanNamQuy.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
                }
            }
        });
        cbxQuy.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                int[] doanhThu = DoanhThuQuy(cbxNamQuy,cbxQuy);
                lblDoanhThuNamQuy.setText(changeCurrency(Integer.toString(doanhThu[0]))+"đ");
                lblChiNamQuy.setText(changeCurrency(Integer.toString(doanhThu[1]))+"đ");
                int loiNhuan = doanhThu[0]-doanhThu[1];
                if(loiNhuan<0){
                    loiNhuan=0;
                    lblLoiNhuanNamQuy.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
                }
                else{
                    lblLoiNhuanNamQuy.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
                }
            }
        });
        
        
        
        String[] nam = {"2019","2018","2017","2016"};
         cbxNam = new SweetComboBox("#ffffff","#181818",0,0,140,30,nam);  
        pnlNam.add(cbxNam);
        cbxNam.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int[] doanhThu = DoanhThuNam(cbxNam);
                lblDoanhThuNam.setText(changeCurrency(Integer.toString(doanhThu[0]))+"đ");
                lblChiNam.setText(changeCurrency(Integer.toString(doanhThu[1]))+"đ");
                int loiNhuan = doanhThu[0]-doanhThu[1];
                if(loiNhuan<0){
                    loiNhuan=0;
                    lblLoiNhuanNam.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
                }
                else{
                    lblLoiNhuanNam.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
                }
            }
        });
        cbxNam.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                int[] doanhThu = DoanhThuNam(cbxNam);
                lblDoanhThuNam.setText(changeCurrency(Integer.toString(doanhThu[0]))+"đ");
                lblChiNam.setText(changeCurrency(Integer.toString(doanhThu[1]))+"đ");
                int loiNhuan = doanhThu[0]-doanhThu[1];
                if(loiNhuan<0){
                    loiNhuan=0;
                    lblLoiNhuanNam.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
                }
                else{
                    lblLoiNhuanNam.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
                } 
            }
        });

    } 
       
      
    public void createChart(int[] data){
        createBarChartVertical(pnlChartVertical,data);
    }
    public DoanhThuJPanel() {
        initComponents();
        setDate(dateNgay,dateTu,dateDen);
        setComboBox();
        createChart(createDataChartPresentDoanhThu(DataDoanhThu.getNgayXuatDonHang()));
        lblSoLuongDoanhThuHomNay.setText(changeCurrency(Integer.toString(DoanhThuNgayHomNay()))+"đ");
        lblSoLuongDoanhThuTuanNay.setText(changeCurrency(Integer.toString(DoanhThuTuanNay()))+"đ");
        lblSoLuongDoanhThuThangNay.setText(changeCurrency(Integer.toString(DoanhThuThangNay()))+"đ");
        pnlXuatExcelNam.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnlXuatExcelNamQuy.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));        
        runningRound();
/*        int[] temp = DataDoanhThu.DoanhThu12Thang("2020");
        System.out.println(temp[0]+" "+temp[1]);*/
        
     }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlThongTin = new GUI.Rounded();
        lblNgay8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblNgay1 = new javax.swing.JLabel();
        lblChiNamThang = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        pnlNamQuy = new javax.swing.JPanel();
        lblNgay7 = new javax.swing.JLabel();
        dateDen = new com.toedter.calendar.JDateChooser();
        pnlNamThang = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        dateNgay = new com.toedter.calendar.JDateChooser();
        pnlQuy = new javax.swing.JPanel();
        pnlNam = new javax.swing.JPanel();
        lblChiNamQuy = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblNgay = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblLoiNhuanNamQuy = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        dateTu = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        lblNgay5 = new javax.swing.JLabel();
        lblNgay4 = new javax.swing.JLabel();
        lblNgay6 = new javax.swing.JLabel();
        lblDoanhThuNamThang = new javax.swing.JLabel();
        lblDoanhThuTuDen = new javax.swing.JLabel();
        lblDoanhThuNamQuy = new javax.swing.JLabel();
        lblLoiNhuanNam = new javax.swing.JLabel();
        lblChiNam = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblDoanhThuNam = new javax.swing.JLabel();
        pnlThang = new javax.swing.JPanel();
        lblLoiNhuanNamThang = new javax.swing.JLabel();
        lblNgay3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblDoanhThuNgay = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        pnlXuatExcelNam = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        pnlXuatExcelNamQuy = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        pnlChartVertical = new javax.swing.JPanel();
        pnlChiTietDoanhThu = new GUI.Rounded();
        pgbDoanhThu = new GUI.ProgressBarJPanel();
        pnlDoanhThuHomNay = new javax.swing.JPanel();
        lblDoanhThuHomNay = new javax.swing.JLabel();
        lblSoLuongDoanhThuHomNay = new javax.swing.JLabel();
        pnlChiTietDoanhThu1 = new GUI.Rounded();
        pgbDoanhThuThangNay = new GUI.ProgressBarJPanel();
        pnlDoanhThuHomNay1 = new javax.swing.JPanel();
        lblDoanhThuHomNay1 = new javax.swing.JLabel();
        lblSoLuongDoanhThuThangNay = new javax.swing.JLabel();
        pnlChiTietDoanhThu2 = new GUI.Rounded();
        pgbDoanhThuTuanNay = new GUI.ProgressBarJPanel();
        pnlDoanhThuHomNay2 = new javax.swing.JPanel();
        lblDoanhThuHomNay2 = new javax.swing.JLabel();
        lblSoLuongDoanhThuTuanNay = new javax.swing.JLabel();

        setBackground(new java.awt.Color(45, 47, 62));

        pnlThongTin.setBackground(new java.awt.Color(45, 47, 62));
        pnlThongTin.setForeground(new java.awt.Color(53, 55, 70));
        pnlThongTin.setPreferredSize(new java.awt.Dimension(920, 750));
        pnlThongTin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNgay8.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 1, 14)); // NOI18N
        lblNgay8.setForeground(new java.awt.Color(255, 255, 255));
        lblNgay8.setText("Quý:");
        pnlThongTin.add(lblNgay8, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 620, -1, 30));

        jLabel14.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Lợi nhuận:");
        pnlThongTin.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 710, -1, -1));

        lblNgay1.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 1, 14)); // NOI18N
        lblNgay1.setForeground(new java.awt.Color(255, 255, 255));
        lblNgay1.setText("Năm:");
        pnlThongTin.add(lblNgay1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 690, -1, 30));

        lblChiNamThang.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblChiNamThang.setForeground(new java.awt.Color(255, 0, 0));
        lblChiNamThang.setText("20.000.000");
        pnlThongTin.add(lblChiNamThang, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 580, -1, -1));

        jLabel7.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Doanh thu:");
        pnlThongTin.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 550, -1, -1));

        pnlNamQuy.setBackground(new java.awt.Color(255, 255, 255));
        pnlNamQuy.setForeground(new java.awt.Color(255, 255, 255));
        pnlNamQuy.setPreferredSize(new java.awt.Dimension(150, 30));

        javax.swing.GroupLayout pnlNamQuyLayout = new javax.swing.GroupLayout(pnlNamQuy);
        pnlNamQuy.setLayout(pnlNamQuyLayout);
        pnlNamQuyLayout.setHorizontalGroup(
            pnlNamQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        pnlNamQuyLayout.setVerticalGroup(
            pnlNamQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        pnlThongTin.add(pnlNamQuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 620, 140, -1));

        lblNgay7.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 1, 14)); // NOI18N
        lblNgay7.setForeground(new java.awt.Color(255, 255, 255));
        lblNgay7.setText("Tháng:");
        pnlThongTin.add(lblNgay7, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 550, -1, 30));

        dateDen.setBackground(new java.awt.Color(255, 255, 255));
        dateDen.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateDenPropertyChange(evt);
            }
        });
        pnlThongTin.add(dateDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 490, 140, -1));

        pnlNamThang.setBackground(new java.awt.Color(255, 255, 255));
        pnlNamThang.setForeground(new java.awt.Color(255, 255, 255));
        pnlNamThang.setPreferredSize(new java.awt.Dimension(150, 30));

        javax.swing.GroupLayout pnlNamThangLayout = new javax.swing.GroupLayout(pnlNamThang);
        pnlNamThang.setLayout(pnlNamThangLayout);
        pnlNamThangLayout.setHorizontalGroup(
            pnlNamThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        pnlNamThangLayout.setVerticalGroup(
            pnlNamThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        pnlThongTin.add(pnlNamThang, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 550, 140, 30));

        jLabel10.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Lợi nhuận:");
        pnlThongTin.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 650, -1, -1));

        dateNgay.setBackground(new java.awt.Color(255, 255, 255));
        dateNgay.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateNgayPropertyChange(evt);
            }
        });
        pnlThongTin.add(dateNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 420, 140, -1));

        pnlQuy.setBackground(new java.awt.Color(255, 255, 255));
        pnlQuy.setForeground(new java.awt.Color(255, 255, 255));
        pnlQuy.setPreferredSize(new java.awt.Dimension(150, 30));

        javax.swing.GroupLayout pnlQuyLayout = new javax.swing.GroupLayout(pnlQuy);
        pnlQuy.setLayout(pnlQuyLayout);
        pnlQuyLayout.setHorizontalGroup(
            pnlQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        pnlQuyLayout.setVerticalGroup(
            pnlQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        pnlThongTin.add(pnlQuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 620, 140, -1));

        pnlNam.setBackground(new java.awt.Color(255, 255, 255));
        pnlNam.setForeground(new java.awt.Color(255, 255, 255));
        pnlNam.setPreferredSize(new java.awt.Dimension(150, 30));

        javax.swing.GroupLayout pnlNamLayout = new javax.swing.GroupLayout(pnlNam);
        pnlNam.setLayout(pnlNamLayout);
        pnlNamLayout.setHorizontalGroup(
            pnlNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        pnlNamLayout.setVerticalGroup(
            pnlNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        pnlThongTin.add(pnlNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 690, 140, -1));

        lblChiNamQuy.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblChiNamQuy.setForeground(new java.awt.Color(255, 0, 0));
        lblChiNamQuy.setText("20.000.000");
        pnlThongTin.add(lblChiNamQuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 650, -1, -1));

        jLabel8.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Lợi nhuận:");
        pnlThongTin.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 580, -1, -1));

        lblNgay.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 1, 14)); // NOI18N
        lblNgay.setForeground(new java.awt.Color(255, 255, 255));
        lblNgay.setText("Ngày:");
        pnlThongTin.add(lblNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, -1, 40));

        jLabel11.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Doanh thu:");
        pnlThongTin.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 620, -1, -1));

        jLabel15.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Chi:");
        pnlThongTin.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 710, -1, -1));

        lblLoiNhuanNamQuy.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblLoiNhuanNamQuy.setForeground(new java.awt.Color(37, 213, 54));
        lblLoiNhuanNamQuy.setText("123456");
        pnlThongTin.add(lblLoiNhuanNamQuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 650, -1, -1));

        jLabel12.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Chi:");
        pnlThongTin.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 650, -1, -1));

        dateTu.setBackground(new java.awt.Color(255, 255, 255));
        dateTu.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateTuPropertyChange(evt);
            }
        });
        pnlThongTin.add(dateTu, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 490, 140, -1));

        jLabel3.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Doanh thu:");
        pnlThongTin.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 420, -1, -1));

        lblNgay5.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 1, 14)); // NOI18N
        lblNgay5.setForeground(new java.awt.Color(255, 255, 255));
        lblNgay5.setText("Năm:");
        pnlThongTin.add(lblNgay5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 620, -1, 30));

        lblNgay4.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 1, 14)); // NOI18N
        lblNgay4.setForeground(new java.awt.Color(255, 255, 255));
        lblNgay4.setText("Năm:");
        pnlThongTin.add(lblNgay4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, -1, 30));

        lblNgay6.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 1, 14)); // NOI18N
        lblNgay6.setForeground(new java.awt.Color(255, 255, 255));
        lblNgay6.setText("Đến:");
        pnlThongTin.add(lblNgay6, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 490, -1, 30));

        lblDoanhThuNamThang.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblDoanhThuNamThang.setForeground(new java.awt.Color(255, 255, 255));
        lblDoanhThuNamThang.setText("123456");
        pnlThongTin.add(lblDoanhThuNamThang, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 550, -1, -1));

        lblDoanhThuTuDen.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblDoanhThuTuDen.setForeground(new java.awt.Color(255, 255, 255));
        lblDoanhThuTuDen.setText("123456");
        pnlThongTin.add(lblDoanhThuTuDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 480, -1, -1));

        lblDoanhThuNamQuy.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblDoanhThuNamQuy.setForeground(new java.awt.Color(255, 255, 255));
        lblDoanhThuNamQuy.setText("123456");
        pnlThongTin.add(lblDoanhThuNamQuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 620, -1, -1));

        lblLoiNhuanNam.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblLoiNhuanNam.setForeground(new java.awt.Color(37, 213, 54));
        lblLoiNhuanNam.setText("123456");
        pnlThongTin.add(lblLoiNhuanNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 710, -1, -1));

        lblChiNam.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblChiNam.setForeground(new java.awt.Color(255, 0, 0));
        lblChiNam.setText("20.000.000");
        pnlThongTin.add(lblChiNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 710, -1, -1));

        jLabel5.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Doanh thu:");
        pnlThongTin.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 480, -1, -1));

        lblDoanhThuNam.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblDoanhThuNam.setForeground(new java.awt.Color(255, 255, 255));
        lblDoanhThuNam.setText("123456");
        pnlThongTin.add(lblDoanhThuNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 680, -1, -1));

        pnlThang.setBackground(new java.awt.Color(255, 255, 255));
        pnlThang.setForeground(new java.awt.Color(255, 255, 255));
        pnlThang.setPreferredSize(new java.awt.Dimension(150, 30));

        javax.swing.GroupLayout pnlThangLayout = new javax.swing.GroupLayout(pnlThang);
        pnlThang.setLayout(pnlThangLayout);
        pnlThangLayout.setHorizontalGroup(
            pnlThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        pnlThangLayout.setVerticalGroup(
            pnlThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        pnlThongTin.add(pnlThang, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 550, 140, -1));

        lblLoiNhuanNamThang.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblLoiNhuanNamThang.setForeground(new java.awt.Color(37, 213, 54));
        lblLoiNhuanNamThang.setText("123456");
        pnlThongTin.add(lblLoiNhuanNamThang, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 580, -1, -1));

        lblNgay3.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 1, 14)); // NOI18N
        lblNgay3.setForeground(new java.awt.Color(255, 255, 255));
        lblNgay3.setText("Từ:");
        pnlThongTin.add(lblNgay3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, -1, 30));

        jLabel9.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Chi:");
        pnlThongTin.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 580, -1, -1));

        lblDoanhThuNgay.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblDoanhThuNgay.setForeground(new java.awt.Color(255, 255, 255));
        lblDoanhThuNgay.setText("1213414134");
        pnlThongTin.add(lblDoanhThuNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 420, -1, -1));

        jLabel13.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Doanh thu:");
        pnlThongTin.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 680, -1, -1));

        pnlXuatExcelNam.setBackground(new java.awt.Color(210, 48, 44));

        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Xuất Excel");
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlXuatExcelNamLayout = new javax.swing.GroupLayout(pnlXuatExcelNam);
        pnlXuatExcelNam.setLayout(pnlXuatExcelNamLayout);
        pnlXuatExcelNamLayout.setHorizontalGroup(
            pnlXuatExcelNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
            .addGroup(pnlXuatExcelNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlXuatExcelNamLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        pnlXuatExcelNamLayout.setVerticalGroup(
            pnlXuatExcelNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
            .addGroup(pnlXuatExcelNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlXuatExcelNamLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pnlThongTin.add(pnlXuatExcelNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 680, 80, 20));

        pnlXuatExcelNamQuy.setBackground(new java.awt.Color(210, 48, 44));

        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Xuất Excel");
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlXuatExcelNamQuyLayout = new javax.swing.GroupLayout(pnlXuatExcelNamQuy);
        pnlXuatExcelNamQuy.setLayout(pnlXuatExcelNamQuyLayout);
        pnlXuatExcelNamQuyLayout.setHorizontalGroup(
            pnlXuatExcelNamQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
            .addGroup(pnlXuatExcelNamQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlXuatExcelNamQuyLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        pnlXuatExcelNamQuyLayout.setVerticalGroup(
            pnlXuatExcelNamQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
            .addGroup(pnlXuatExcelNamQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlXuatExcelNamQuyLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pnlThongTin.add(pnlXuatExcelNamQuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 620, 80, 20));

        pnlChartVertical.setLayout(new javax.swing.BoxLayout(pnlChartVertical, javax.swing.BoxLayout.LINE_AXIS));
        pnlThongTin.add(pnlChartVertical, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 880, 190));

        pnlChiTietDoanhThu.setBackground(new java.awt.Color(53, 55, 70));
        pnlChiTietDoanhThu.setForeground(new java.awt.Color(60, 62, 77));

        pgbDoanhThu.setBackground(new java.awt.Color(60, 62, 77));

        javax.swing.GroupLayout pgbDoanhThuLayout = new javax.swing.GroupLayout(pgbDoanhThu);
        pgbDoanhThu.setLayout(pgbDoanhThuLayout);
        pgbDoanhThuLayout.setHorizontalGroup(
            pgbDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 151, Short.MAX_VALUE)
        );
        pgbDoanhThuLayout.setVerticalGroup(
            pgbDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 94, Short.MAX_VALUE)
        );

        pnlDoanhThuHomNay.setBackground(new java.awt.Color(60, 62, 77));
        pnlDoanhThuHomNay.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(153, 153, 153)));

        lblDoanhThuHomNay.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblDoanhThuHomNay.setForeground(new java.awt.Color(255, 255, 255));
        lblDoanhThuHomNay.setText("Doanh thu hôm nay");

        lblSoLuongDoanhThuHomNay.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblSoLuongDoanhThuHomNay.setForeground(new java.awt.Color(210, 57, 44));
        lblSoLuongDoanhThuHomNay.setText("123.456.789đ");

        javax.swing.GroupLayout pnlDoanhThuHomNayLayout = new javax.swing.GroupLayout(pnlDoanhThuHomNay);
        pnlDoanhThuHomNay.setLayout(pnlDoanhThuHomNayLayout);
        pnlDoanhThuHomNayLayout.setHorizontalGroup(
            pnlDoanhThuHomNayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoanhThuHomNayLayout.createSequentialGroup()
                .addComponent(lblDoanhThuHomNay)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDoanhThuHomNayLayout.createSequentialGroup()
                .addComponent(lblSoLuongDoanhThuHomNay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDoanhThuHomNayLayout.setVerticalGroup(
            pnlDoanhThuHomNayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoanhThuHomNayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDoanhThuHomNay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSoLuongDoanhThuHomNay, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
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
                .addGap(40, 40, 40)
                .addComponent(pgbDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        pnlChiTietDoanhThuLayout.setVerticalGroup(
            pnlChiTietDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChiTietDoanhThuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pgbDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDoanhThuHomNay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlThongTin.add(pnlChiTietDoanhThu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        pnlChiTietDoanhThu1.setBackground(new java.awt.Color(53, 55, 70));
        pnlChiTietDoanhThu1.setForeground(new java.awt.Color(60, 62, 77));

        pgbDoanhThuThangNay.setBackground(new java.awt.Color(60, 62, 77));

        javax.swing.GroupLayout pgbDoanhThuThangNayLayout = new javax.swing.GroupLayout(pgbDoanhThuThangNay);
        pgbDoanhThuThangNay.setLayout(pgbDoanhThuThangNayLayout);
        pgbDoanhThuThangNayLayout.setHorizontalGroup(
            pgbDoanhThuThangNayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 151, Short.MAX_VALUE)
        );
        pgbDoanhThuThangNayLayout.setVerticalGroup(
            pgbDoanhThuThangNayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 94, Short.MAX_VALUE)
        );

        pnlDoanhThuHomNay1.setBackground(new java.awt.Color(60, 62, 77));
        pnlDoanhThuHomNay1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(153, 153, 153)));

        lblDoanhThuHomNay1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblDoanhThuHomNay1.setForeground(new java.awt.Color(255, 255, 255));
        lblDoanhThuHomNay1.setText("Doanh thu tháng này");

        lblSoLuongDoanhThuThangNay.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblSoLuongDoanhThuThangNay.setForeground(new java.awt.Color(210, 57, 44));
        lblSoLuongDoanhThuThangNay.setText("123.456.789đ");

        javax.swing.GroupLayout pnlDoanhThuHomNay1Layout = new javax.swing.GroupLayout(pnlDoanhThuHomNay1);
        pnlDoanhThuHomNay1.setLayout(pnlDoanhThuHomNay1Layout);
        pnlDoanhThuHomNay1Layout.setHorizontalGroup(
            pnlDoanhThuHomNay1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoanhThuHomNay1Layout.createSequentialGroup()
                .addComponent(lblDoanhThuHomNay1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDoanhThuHomNay1Layout.createSequentialGroup()
                .addComponent(lblSoLuongDoanhThuThangNay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDoanhThuHomNay1Layout.setVerticalGroup(
            pnlDoanhThuHomNay1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoanhThuHomNay1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDoanhThuHomNay1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSoLuongDoanhThuThangNay, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlChiTietDoanhThu1Layout = new javax.swing.GroupLayout(pnlChiTietDoanhThu1);
        pnlChiTietDoanhThu1.setLayout(pnlChiTietDoanhThu1Layout);
        pnlChiTietDoanhThu1Layout.setHorizontalGroup(
            pnlChiTietDoanhThu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlChiTietDoanhThu1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDoanhThuHomNay1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(pnlChiTietDoanhThu1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(pgbDoanhThuThangNay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        pnlChiTietDoanhThu1Layout.setVerticalGroup(
            pnlChiTietDoanhThu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChiTietDoanhThu1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pgbDoanhThuThangNay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDoanhThuHomNay1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlThongTin.add(pnlChiTietDoanhThu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 210, -1, -1));

        pnlChiTietDoanhThu2.setBackground(new java.awt.Color(53, 55, 70));
        pnlChiTietDoanhThu2.setForeground(new java.awt.Color(60, 62, 77));

        pgbDoanhThuTuanNay.setBackground(new java.awt.Color(60, 62, 77));

        javax.swing.GroupLayout pgbDoanhThuTuanNayLayout = new javax.swing.GroupLayout(pgbDoanhThuTuanNay);
        pgbDoanhThuTuanNay.setLayout(pgbDoanhThuTuanNayLayout);
        pgbDoanhThuTuanNayLayout.setHorizontalGroup(
            pgbDoanhThuTuanNayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 151, Short.MAX_VALUE)
        );
        pgbDoanhThuTuanNayLayout.setVerticalGroup(
            pgbDoanhThuTuanNayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 94, Short.MAX_VALUE)
        );

        pnlDoanhThuHomNay2.setBackground(new java.awt.Color(60, 62, 77));
        pnlDoanhThuHomNay2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(153, 153, 153)));

        lblDoanhThuHomNay2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblDoanhThuHomNay2.setForeground(new java.awt.Color(255, 255, 255));
        lblDoanhThuHomNay2.setText("Doanh thu tuần này");

        lblSoLuongDoanhThuTuanNay.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblSoLuongDoanhThuTuanNay.setForeground(new java.awt.Color(210, 57, 44));
        lblSoLuongDoanhThuTuanNay.setText("123.456.789đ");

        javax.swing.GroupLayout pnlDoanhThuHomNay2Layout = new javax.swing.GroupLayout(pnlDoanhThuHomNay2);
        pnlDoanhThuHomNay2.setLayout(pnlDoanhThuHomNay2Layout);
        pnlDoanhThuHomNay2Layout.setHorizontalGroup(
            pnlDoanhThuHomNay2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoanhThuHomNay2Layout.createSequentialGroup()
                .addComponent(lblDoanhThuHomNay2)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDoanhThuHomNay2Layout.createSequentialGroup()
                .addComponent(lblSoLuongDoanhThuTuanNay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDoanhThuHomNay2Layout.setVerticalGroup(
            pnlDoanhThuHomNay2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoanhThuHomNay2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDoanhThuHomNay2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSoLuongDoanhThuTuanNay, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlChiTietDoanhThu2Layout = new javax.swing.GroupLayout(pnlChiTietDoanhThu2);
        pnlChiTietDoanhThu2.setLayout(pnlChiTietDoanhThu2Layout);
        pnlChiTietDoanhThu2Layout.setHorizontalGroup(
            pnlChiTietDoanhThu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlChiTietDoanhThu2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDoanhThuHomNay2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(pnlChiTietDoanhThu2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(pgbDoanhThuTuanNay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        pnlChiTietDoanhThu2Layout.setVerticalGroup(
            pnlChiTietDoanhThu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChiTietDoanhThu2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pgbDoanhThuTuanNay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDoanhThuHomNay2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlThongTin.add(pnlChiTietDoanhThu2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 210, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(pnlThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 737, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void dateTuPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateTuPropertyChange
        dateDen.setDate(dateTu.getDate());
        dateDen.setMinSelectableDate(dateTu.getDate());
    }//GEN-LAST:event_dateTuPropertyChange

    private void dateNgayPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateNgayPropertyChange
        int doanhThu=DoanhThuNgay(dateNgay.getDate());
        lblDoanhThuNgay.setText(changeCurrency(Integer.toString(doanhThu))+"đ");
    }//GEN-LAST:event_dateNgayPropertyChange

    private void dateDenPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateDenPropertyChange
        int doanhThu=DoanhThuTuDen(dateTu.getDate(),dateDen.getDate());
        lblDoanhThuTuDen.setText(changeCurrency(Integer.toString(doanhThu))+"đ");
    }//GEN-LAST:event_dateDenPropertyChange

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        try {
            String path = SweetFileChooser.ExcelFileChooser();
            if(path==null){
                SweetAlert.showSweetAlert(new JFrame(), "Lỗi", "Vui lòng chọn file phù hợp", 1);
                return;
            }
            DoanhThuQuyExcel.ExportExcelFile(path,DataDoanhThu.DoanhThuQuyExcel(cbxNamQuy.getSelectedItem().toString()) );
        } catch (IOException ex) {
            Logger.getLogger(DoanhThuJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel19MouseClicked

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        try {
            String path = SweetFileChooser.ExcelFileChooser();
            if(path==null){
                SweetAlert.showSweetAlert(new JFrame(), "Lỗi", "Vui lòng chọn file phù hợp", 1);
                return;
            }
            DoanhThuThangExcel.ExportExcelFile(path, DataDoanhThu.DoanhThuThangExcel(cbxNam.getSelectedItem().toString()));
        } catch (IOException ex) {
            Logger.getLogger(DoanhThuJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel20MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser dateDen;
    private com.toedter.calendar.JDateChooser dateNgay;
    private com.toedter.calendar.JDateChooser dateTu;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblChiNam;
    private javax.swing.JLabel lblChiNamQuy;
    private javax.swing.JLabel lblChiNamThang;
    private javax.swing.JLabel lblDoanhThuHomNay;
    private javax.swing.JLabel lblDoanhThuHomNay1;
    private javax.swing.JLabel lblDoanhThuHomNay2;
    private javax.swing.JLabel lblDoanhThuNam;
    private javax.swing.JLabel lblDoanhThuNamQuy;
    private javax.swing.JLabel lblDoanhThuNamThang;
    private javax.swing.JLabel lblDoanhThuNgay;
    private javax.swing.JLabel lblDoanhThuTuDen;
    private javax.swing.JLabel lblLoiNhuanNam;
    private javax.swing.JLabel lblLoiNhuanNamQuy;
    private javax.swing.JLabel lblLoiNhuanNamThang;
    private javax.swing.JLabel lblNgay;
    private javax.swing.JLabel lblNgay1;
    private javax.swing.JLabel lblNgay3;
    private javax.swing.JLabel lblNgay4;
    private javax.swing.JLabel lblNgay5;
    private javax.swing.JLabel lblNgay6;
    private javax.swing.JLabel lblNgay7;
    private javax.swing.JLabel lblNgay8;
    private javax.swing.JLabel lblSoLuongDoanhThuHomNay;
    private javax.swing.JLabel lblSoLuongDoanhThuThangNay;
    private javax.swing.JLabel lblSoLuongDoanhThuTuanNay;
    private GUI.ProgressBarJPanel pgbDoanhThu;
    private GUI.ProgressBarJPanel pgbDoanhThuThangNay;
    private GUI.ProgressBarJPanel pgbDoanhThuTuanNay;
    private javax.swing.JPanel pnlChartVertical;
    private GUI.Rounded pnlChiTietDoanhThu;
    private GUI.Rounded pnlChiTietDoanhThu1;
    private GUI.Rounded pnlChiTietDoanhThu2;
    private javax.swing.JPanel pnlDoanhThuHomNay;
    private javax.swing.JPanel pnlDoanhThuHomNay1;
    private javax.swing.JPanel pnlDoanhThuHomNay2;
    private javax.swing.JPanel pnlNam;
    private javax.swing.JPanel pnlNamQuy;
    private javax.swing.JPanel pnlNamThang;
    private javax.swing.JPanel pnlQuy;
    private javax.swing.JPanel pnlThang;
    private GUI.Rounded pnlThongTin;
    private javax.swing.JPanel pnlXuatExcelNam;
    private javax.swing.JPanel pnlXuatExcelNamQuy;
    // End of variables declaration//GEN-END:variables
}
