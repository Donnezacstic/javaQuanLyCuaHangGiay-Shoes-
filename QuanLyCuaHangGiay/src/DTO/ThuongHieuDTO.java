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
public class ThuongHieuDTO {
   protected String MaThuongHieu,TenThuongHieu;

    public ThuongHieuDTO() {
    }

    public ThuongHieuDTO(String MaThuongHieu, String TenThuongHieu) {
        this.MaThuongHieu = MaThuongHieu;
        this.TenThuongHieu = TenThuongHieu;
    }
    public String getPrimaryKey(){
        return "MaThuongHieu='"+this.getMaThuongHieu()+"'";
    }

    public String getMaThuongHieu() {
        return MaThuongHieu;
    }

    public String getTenThuongHieu() {
        return TenThuongHieu;
    }

    public void setMaThuongHieu(String MaThuongHieu) {
        this.MaThuongHieu = MaThuongHieu;
    }

    public void setTenThuongHieu(String TenThuongHieu) {
        this.TenThuongHieu = TenThuongHieu;
    }
    
}
