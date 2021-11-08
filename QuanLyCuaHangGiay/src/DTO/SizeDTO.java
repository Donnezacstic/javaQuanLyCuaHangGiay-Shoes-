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
public class SizeDTO {
   protected String MaSize,TenSize;

    public SizeDTO() {
    }

    public SizeDTO(String MaSize, String TenSize) {
        this.MaSize = MaSize;
        this.TenSize = TenSize;
    }
    public String getPrimaryKey(){
        return "MaSize='"+this.getMaSize()+"'";
    }

    public String getMaSize() {
        return MaSize;
    }

    public String getTenSize() {
        return TenSize;
    }

    public void setMaSize(String MaSize) {
        this.MaSize = MaSize;
    }

    public void setTenSize(String TenSize) {
        this.TenSize = TenSize;
    }
    
}
