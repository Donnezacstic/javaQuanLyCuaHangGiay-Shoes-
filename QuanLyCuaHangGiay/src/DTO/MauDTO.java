/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Administrator
 */
public class MauDTO {
   protected String MaMau,TenMau;

    public MauDTO() {
    }

    public MauDTO(String MaMau, String TenMau) {
        this.MaMau = MaMau;
        this.TenMau = TenMau;
    }
    public String getPrimaryKey(){
        return "MaMau='"+this.getMaMau()+"'";
    }

    public String getMaMau() {
        return MaMau;
    }

    public String getTenMau() {
        return TenMau;
    }

    public void setMaMau(String MaMau) {
        this.MaMau = MaMau;
    }

    public void setTenMau(String TenMau) {
        this.TenMau = TenMau;
    }
    
}
