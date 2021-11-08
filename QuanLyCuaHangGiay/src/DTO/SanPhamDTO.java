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
public class SanPhamDTO {
    protected String MaSanPham, TenSanPham, DonGia, SoLuong, MaMau, MaSize, MaThuongHieu,ImgSource;

    public SanPhamDTO() {
    }
    public SanPhamDTO(String MaSanPham, String TenSanPham, String DonGia, String SoLuong, String MaMau, String MaSize, String MaThuongHieu,String imgSource) {
        this.MaSanPham = MaSanPham;
        this.TenSanPham = TenSanPham;
        this.DonGia = DonGia;
        this.SoLuong = SoLuong;
        this.MaMau = MaMau;
        this.MaSize = MaSize;
        this.MaThuongHieu = MaThuongHieu;
        this.ImgSource = imgSource;
    }
    
    public String getPrimaryKey(){
        return "MaSanPham='"+this.getMaSanPham()+"'";
    }
    
    public String getMaSanPham() {
        return MaSanPham;
    }

    public String getImgSource() {
        return ImgSource;
    }

    public void setMaSanPham(String MaSanPham) {
        this.MaSanPham = MaSanPham;
    }

    public void setImgSource(String imgSource) {
        this.ImgSource = imgSource;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String TenSanPham) {
        this.TenSanPham = TenSanPham;
    }

    public String getDonGia() {
        return DonGia;
    }

    public void setDonGia(String DonGia) {
        this.DonGia = DonGia;
    }

    public String getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(String SoLuong) {
        this.SoLuong = SoLuong;
    }

    public String getMaMau() {
        return MaMau;
    }

    public void setMaMau(String MaMau) {
        this.MaMau = MaMau;
    }

    public String getMaSize() {
        return MaSize;
    }

    public void setMaSize(String MaSize) {
        this.MaSize = MaSize;
    }

    public String getMaThuongHieu() {
        return MaThuongHieu;
    }

    public void setMaThuongHieu(String MaThuongHieu) {
        this.MaThuongHieu = MaThuongHieu;
    }
    
}
