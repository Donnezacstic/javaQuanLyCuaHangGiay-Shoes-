/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import GUI.CHITIET.ChiTietDonHangJPanel;
import GUI.CHITIET.ChiTietSanPhamJPanel;
import GUI.CaiDatJPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import GUI.SanPhamJPanel;
import GUI.DoanhThuJPanel;
import GUI.ThongKeJPanel;
import GUI.TongQuanJPanel;
import GUI.DonHangJPanel;
import GUI.KHUYENMAI.ChiTietKhuyenMaiJPanel;
import GUI.KHUYENMAI.ChuongTrinhKhuyenMaiJPanel;
import GUI.KhuyenMaiJPanel;
import GUI.KhachHangJPanel;
import GUI.PhieuNhapHangJPanel;
import GUI.QLNVJPanel;
import GUI.QuanLyJPanel;
import GUI.THEMMOI.TaoMaKhuyenMaiJPanel;
import GUI.ThemTaiKhoanJPanel;
import bean.DanhMucBean;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;

/**
 *
 * @author admin 
 */
public class changeScreenController {
    private JPanel pnlRoot;
    private String selectedScreen = "";
    private List<DanhMucBean> listItem = null; 
    public changeScreenController(JPanel pnlRoot) {
        this.pnlRoot = pnlRoot;
    }
   
    public void setScreen(String selectedScreen,JPanel pnlItem,JLabel lblItem){
        
        this.selectedScreen = selectedScreen;
        lblItem.setForeground(Color.decode("#d2302c"));
        pnlItem.setBackground(Color.decode("#353749"));
        pnlItem.setBorder(BorderFactory.createMatteBorder(
                    0, 3, 0, 0, Color.decode("#d2302c")));         
        pnlRoot.removeAll();
        pnlRoot.setLayout(new BorderLayout());
        try {
            pnlRoot.add((Component) Class.forName("GUI."+this.selectedScreen+"JPanel").newInstance());
        } catch (Exception ex) {
            Logger.getLogger(changeScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        pnlRoot.validate();
        pnlRoot.repaint();
       
        
    }
    
    public void setEvent(List<DanhMucBean> listItem){
        this.listItem = listItem;
        for(DanhMucBean item : listItem){
            item.getPnlName().addMouseListener(new changeScreen(item.getKindOfScreen(),item.getPnlName(),item.getLblName()));
        }
        
    } 
class changeScreen implements MouseListener {
        private JPanel current;
        private String kindOfScreen = "";
        private JPanel pnlName;
        private JLabel lblName;
        
        
       
        
        changeScreen(String kindOfScreen,JPanel pnlName,JLabel lblName){
            this.kindOfScreen = kindOfScreen;
            this.pnlName = pnlName;
            this.lblName = lblName;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            switch(kindOfScreen){
                case "TongQuan":
                    current = new TongQuanJPanel();
                    break;
                case "DonHang":
                    current = new DonHangJPanel();
                    break;
                case "SanPham":
                    current = new SanPhamJPanel();
                    break;
                case "KhachHang":
                    current = new KhachHangJPanel();
                    break;
                case "DoanhThu":
                    current = new DoanhThuJPanel();
                    break;
                case "ThongKe":
                    current = new ThongKeJPanel();
                    break;
                case "ThemTaiKhoan":
                    current = new ThemTaiKhoanJPanel();
                    break;
                case "KhuyenMai":
                    current = new KhuyenMaiJPanel();
                    break;
                case "QLNV":
                    current = new QLNVJPanel();
                    break;
                case "PhieuNhapHang":
                    current = new PhieuNhapHangJPanel();
                    break;
                case "QuanLy":
                    current = new QuanLyJPanel();
                    break;
                case "CaiDat":
                    current = new CaiDatJPanel();
                    break;
                    
            }
            current.setBackground(Color.decode("#2d2f3e"));//SET BG COLOR PANEL VIEW
            pnlRoot.removeAll();
            pnlRoot.setLayout(new BorderLayout());
            pnlRoot.add(current);
            pnlRoot.validate();
            pnlRoot.repaint();
            setBackgroundColor(kindOfScreen);
            
            
        }

        @Override
        public void mousePressed(MouseEvent e) {
            selectedScreen = kindOfScreen;
            lblName.setForeground(Color.decode("#d2302c"));
            pnlName.setBackground(Color.decode("#353749"));
            
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
                lblName.setForeground(Color.decode("#d2302c"));
                pnlName.setBackground(Color.decode("#353749"));
                pnlName.setBorder(BorderFactory.createMatteBorder(
                            0, 4, 0, 0, Color.decode("#d2302c")));               
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(!selectedScreen.equalsIgnoreCase(kindOfScreen)){
                lblName.setForeground(Color.decode("#FFFFFF"));
                pnlName.setBackground(Color.decode("#2e3143"));
                pnlName.setBorder(BorderFactory.createMatteBorder(
                            0, 4, 0, 0, Color.decode("#2e3143")));                  
                
            }
        }
        public void setBackgroundColor(String kind){
            for(DanhMucBean item : listItem){
                if(item.getKindOfScreen().equalsIgnoreCase(kind)){
                            item.getLblName().setForeground(Color.decode("#d2302c"));
                            item.getPnlName().setBackground(Color.decode("#353749"));//Hover
                            item.getPnlName().setBorder(BorderFactory.createMatteBorder(
                                    0, 4, 0, 0, Color.decode("#d2302c")));
                }
                else{
                            item.getLblName().setForeground(Color.decode("#FFFFFF"));
                            item.getPnlName().setBackground(Color.decode("#2e3143"));
                            item.getPnlName().setBorder(BorderFactory.createMatteBorder(
                                    0, 4, 0, 0, Color.decode("#2e3143")));                            
                }
            }
        }
        
    }
    
}
