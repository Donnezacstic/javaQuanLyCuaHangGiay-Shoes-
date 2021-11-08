
package GUI.POPUP;

import static GUI.POPUP.ThemSanPhamVaoPhieuNhapJFrame.currentChiTietPhieuNhap;
import GUI.THEMMOI.TaoPhieuNhapJPanel;
import javax.swing.SwingUtilities;

public class SoLuongVaDonGiaJFrame extends javax.swing.JFrame {

    int xMouse,yMouse;
    public SoLuongVaDonGiaJFrame() {
        ((javax.swing.JFrame) SwingUtilities.getWindowAncestor(TaoPhieuNhapJPanel.themsanphampanel.getRootPane())).setEnabled(false);
        initComponents();
        setLocationRelativeTo(null);        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        pnlDonGiaNhap = new GUI.Rounded();
        txtDonGiaSanPhamNhap = new javax.swing.JTextField();
        lblDonVi = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        pnlSoLuongSanPhamNhap = new GUI.Rounded();
        txtSoLuongSanPhamNhap = new javax.swing.JTextField();
        pnlXacNhan = new GUI.Rounded();
        lblXacNhan = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(45, 47, 62));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Đơn giá sản phẩm nhập:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, 30));

        pnlDonGiaNhap.setBackground(new java.awt.Color(45, 47, 62));
        pnlDonGiaNhap.setForeground(new java.awt.Color(255, 255, 255));
        pnlDonGiaNhap.setFocusable(false);

        txtDonGiaSanPhamNhap.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        txtDonGiaSanPhamNhap.setForeground(new java.awt.Color(18, 18, 18));
        txtDonGiaSanPhamNhap.setBorder(null);
        txtDonGiaSanPhamNhap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDonGiaSanPhamNhapKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout pnlDonGiaNhapLayout = new javax.swing.GroupLayout(pnlDonGiaNhap);
        pnlDonGiaNhap.setLayout(pnlDonGiaNhapLayout);
        pnlDonGiaNhapLayout.setHorizontalGroup(
            pnlDonGiaNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDonGiaNhapLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtDonGiaSanPhamNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDonGiaNhapLayout.setVerticalGroup(
            pnlDonGiaNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDonGiaNhapLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtDonGiaSanPhamNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(pnlDonGiaNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 170, -1));

        lblDonVi.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblDonVi.setForeground(new java.awt.Color(210, 48, 44));
        lblDonVi.setText("đ");
        jPanel1.add(lblDonVi, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 20, 30));

        jLabel10.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Số lượng sản phẩm nhập:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, 30));

        pnlSoLuongSanPhamNhap.setBackground(new java.awt.Color(45, 47, 62));
        pnlSoLuongSanPhamNhap.setForeground(new java.awt.Color(255, 255, 255));
        pnlSoLuongSanPhamNhap.setFocusable(false);

        txtSoLuongSanPhamNhap.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        txtSoLuongSanPhamNhap.setForeground(new java.awt.Color(18, 18, 18));
        txtSoLuongSanPhamNhap.setBorder(null);
        txtSoLuongSanPhamNhap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSoLuongSanPhamNhapKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout pnlSoLuongSanPhamNhapLayout = new javax.swing.GroupLayout(pnlSoLuongSanPhamNhap);
        pnlSoLuongSanPhamNhap.setLayout(pnlSoLuongSanPhamNhapLayout);
        pnlSoLuongSanPhamNhapLayout.setHorizontalGroup(
            pnlSoLuongSanPhamNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSoLuongSanPhamNhapLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSoLuongSanPhamNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlSoLuongSanPhamNhapLayout.setVerticalGroup(
            pnlSoLuongSanPhamNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSoLuongSanPhamNhapLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtSoLuongSanPhamNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(pnlSoLuongSanPhamNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, 170, 40));

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
        lblXacNhan.setText("Xác nhận");
        lblXacNhan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

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

        jPanel1.add(pnlXacNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 120, -1));

        jLabel5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel5MouseDragged(evt);
            }
        });
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel5MousePressed(evt);
            }
        });
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 180));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pnlXacNhanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlXacNhanMouseClicked
                String soluong = txtSoLuongSanPhamNhap.getText();
                String dongia = txtDonGiaSanPhamNhap.getText();
                ThemSanPhamVaoPhieuNhapJFrame.setSoLuongDonGiaCTPN(soluong,dongia);
                String Error = quanlycuahanggiay.quanlycuahanggiay.ChiTietPhieuNhapBUS.Validation(currentChiTietPhieuNhap);
                if(Error != null)
                {
                    GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", Error, 1);
                } 
                else
                {
                    int ThanhTien = Integer.parseInt(soluong) * Integer.parseInt(dongia);
                    ThemSanPhamVaoPhieuNhapJFrame.setThanhTienCTPN(Integer.toString(ThanhTien));
                    ThemSanPhamVaoPhieuNhapJFrame.addChiTietPhieuNhapList(ThemSanPhamVaoPhieuNhapJFrame.currentChiTietPhieuNhap);
                    ((javax.swing.JFrame) SwingUtilities.getWindowAncestor(TaoPhieuNhapJPanel.themsanphampanel.getRootPane())).setEnabled(true);
                    ThemSanPhamVaoPhieuNhapJFrame.SoLuongVaDonGiaJFrame.dispose();
                }
//                ThemSanPhamVaoPhieuNhapJFrame.SoLuongVaDonGiaJFrame.removeAll();
    }//GEN-LAST:event_pnlXacNhanMouseClicked

    private void jLabel5MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y-yMouse);
    }//GEN-LAST:event_jLabel5MouseDragged

    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_jLabel5MousePressed

    private void txtSoLuongSanPhamNhapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongSanPhamNhapKeyPressed
        if( evt.getKeyCode() == 10)
        {
                String soluong = txtSoLuongSanPhamNhap.getText();
                String dongia = txtDonGiaSanPhamNhap.getText();
                ThemSanPhamVaoPhieuNhapJFrame.setSoLuongDonGiaCTPN(soluong,dongia);
                String Error = quanlycuahanggiay.quanlycuahanggiay.ChiTietPhieuNhapBUS.Validation(currentChiTietPhieuNhap);
                if(Error != null)
                {
                    GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", Error, 1);
                } 
                else
                {
                    int ThanhTien = Integer.parseInt(soluong) * Integer.parseInt(dongia);
                    ThemSanPhamVaoPhieuNhapJFrame.setThanhTienCTPN(Integer.toString(ThanhTien));
                    ThemSanPhamVaoPhieuNhapJFrame.addChiTietPhieuNhapList(ThemSanPhamVaoPhieuNhapJFrame.currentChiTietPhieuNhap);
                    ((javax.swing.JFrame) SwingUtilities.getWindowAncestor(TaoPhieuNhapJPanel.themsanphampanel.getRootPane())).setEnabled(true);
                    ThemSanPhamVaoPhieuNhapJFrame.SoLuongVaDonGiaJFrame.dispose();
                }
//                ThemSanPhamVaoPhieuNhapJFrame.SoLuongVaDonGiaJFrame.removeAll();
        }
    }//GEN-LAST:event_txtSoLuongSanPhamNhapKeyPressed

    private void txtDonGiaSanPhamNhapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDonGiaSanPhamNhapKeyPressed
        if( evt.getKeyCode()==10 ){
            txtSoLuongSanPhamNhap.requestFocus();
        }
    }//GEN-LAST:event_txtDonGiaSanPhamNhapKeyPressed

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
            java.util.logging.Logger.getLogger(SoLuongVaDonGiaJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SoLuongVaDonGiaJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SoLuongVaDonGiaJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SoLuongVaDonGiaJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SoLuongVaDonGiaJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblDonVi;
    private javax.swing.JLabel lblXacNhan;
    private GUI.Rounded pnlDonGiaNhap;
    private GUI.Rounded pnlSoLuongSanPhamNhap;
    private GUI.Rounded pnlXacNhan;
    private javax.swing.JTextField txtDonGiaSanPhamNhap;
    private javax.swing.JTextField txtSoLuongSanPhamNhap;
    // End of variables declaration//GEN-END:variables
}
