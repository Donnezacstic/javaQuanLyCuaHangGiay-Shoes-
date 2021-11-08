/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.swing.JPanel;
import javax.swing.JLabel;

/**
 *
 * @author admin
 */
public class DanhMucBean {
    private String kindOfScreen;
    private JPanel pnlName;
    private JLabel lblName;
    
    DanhMucBean(){
    }
    
    public DanhMucBean(String kindOfScreen, JPanel pnlName, JLabel lblName) {
        this.kindOfScreen = kindOfScreen;
        this.pnlName = pnlName;
        this.lblName = lblName;
    }

    public String getKindOfScreen() {
        return kindOfScreen;
    }

    public JPanel getPnlName() {
        return pnlName;
    }

    public JLabel getLblName() {
        return lblName;
    }

    public void setKindOfScreen(String kindOfScreen) {
        this.kindOfScreen = kindOfScreen;
    }

    public void setPnlName(JPanel pnlName) {
        this.pnlName = pnlName;
    }

    public void setLblName(JLabel lblName) {
        this.lblName = lblName;
    }
    
}
