/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author HUUNHAN
 */
public class ChiTietDonHangDTO {
    protected String MaDonHang,MaSanPham,SoLuong,DonGia,ThanhTien;
    public ChiTietDonHangDTO(){
    
    }
    public ChiTietDonHangDTO(String MaDonHang,String MaSanPham,String SoLuong,String DonGia,String ThanhTien){
        this.MaDonHang=MaDonHang;
        this.MaSanPham=MaSanPham;
        this.SoLuong=SoLuong;
        this.DonGia=DonGia;
        this.ThanhTien=ThanhTien;
    }
    public String getPrimaryKey(){
           return "MaDonHang='"+this.getMaDonHang()+"AND"+this.getMaSanPham()+"'";
    }
    public String getMaDonHang() {
        return MaDonHang;
    }

    public void setMaDonHang(String MaDonHang) {
        this.MaDonHang = MaDonHang;
    }

    public String getMaSanPham() {
        return MaSanPham;
    }

    public void setMaSanPham(String MaSanPham) {
        this.MaSanPham = MaSanPham;
    }

    public String getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(String SoLuong) {
        this.SoLuong = SoLuong;
    }

    public String getDonGia() {
        return DonGia;
    }

    public void setDonGia(String DonGia) {
        this.DonGia = DonGia;
    }

    public String getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(String ThanhTien) {
        this.ThanhTien = ThanhTien;
    }
    
    
}
