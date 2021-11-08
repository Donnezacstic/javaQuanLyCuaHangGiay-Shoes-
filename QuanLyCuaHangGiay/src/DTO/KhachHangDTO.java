/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author ASUS
 */
public class KhachHangDTO {
    protected String MaKhachHang, HoTen, SoDienThoai, DiaChi, Email;
    
    public String getPrimaryKey(){
        return "MaKhachHang='"+ this.getMaKhachHang() +"'";
    }

    public KhachHangDTO() {
    }
    
    public KhachHangDTO(String MaKhachHang, String TenKhachHang, String SoDienThoai, String DiaChi, String Email) {
        this.MaKhachHang = MaKhachHang;
        this.HoTen = TenKhachHang;
        this.SoDienThoai = SoDienThoai;
        this.DiaChi = DiaChi;
        this.Email = Email;
    }

    public String getMaKhachHang() {
        return MaKhachHang;
    }

    public void setMaKhachHang(String MaKhachHang) {
        this.MaKhachHang = MaKhachHang;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String TenKhachHang) {
        this.HoTen = TenKhachHang;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
  
}
