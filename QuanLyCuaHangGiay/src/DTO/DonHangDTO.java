/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author khanh
 */
public class DonHangDTO {

    protected String MaDonHang, MaKhachHang, MaNhanVien, NgayXuat, MaCode, TamTinh, GiamGia, PhiVanChuyen, TongTien, TrangThai;

    public DonHangDTO() {
    }

    

    public DonHangDTO(String MaDonHang, String MaKhachHang, String MaNhanVien, String NgayXuat, String MaCode, String TamTinh,String GiamGia,
            String PhiVanChuyen, String TongTien ,String TrangThai) {
        this.MaDonHang = MaDonHang;
        this.MaKhachHang = MaKhachHang;
        this.MaNhanVien = MaNhanVien;
        this.NgayXuat = NgayXuat;
        this.MaCode = MaCode;
        this.TamTinh = TamTinh;
        this.GiamGia = GiamGia;
        this.PhiVanChuyen = PhiVanChuyen;
        this.TongTien = TongTien;
        this.TrangThai = TrangThai;
    }

    public String getPrimaryKey() {
        return "MaDonHang='" + this.getMaDonHang() + "'";
    }

    public String getMaDonHang() {
        return MaDonHang;
    }

    public void setMaDonHang(String MaDonHang) {
        this.MaDonHang = MaDonHang;
    }

    public String getMaKhachHang() {
        return MaKhachHang;
    }

    public void setMaKhachHang(String MaKhachHang) {
        this.MaKhachHang = MaKhachHang;
    }

    public String getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(String MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }

    public String getNgayXuat() {
        return NgayXuat;
    }

    public void setNgayXuat(String NgayXuat) {
        this.NgayXuat = NgayXuat;
    }
    public String getMaCode() {
        return MaCode;
    }

    public void setMaCode(String MaCode) {
        this.MaCode = MaCode;
    }
    
    public String getTamTinh() {
        return TamTinh;
    }
    public void setTamTinh(String TamTinh) {
        this.TamTinh = TamTinh;
    }
    
    public String getPhiVanChuyen() {
        return PhiVanChuyen;
    }
    public void setPhiVanChuyen(String PhiVanChuyen) {
        this.PhiVanChuyen = PhiVanChuyen;
    }
    
    public String getGiamGia(){
        return GiamGia;
    }
    public void setGiamGia(String GiamGia){
        this.GiamGia = GiamGia;
    }
    
    public String getTongTien() {
        return TongTien;
    }
    public void setTongTien(String TongTien) {
        this.TongTien = TongTien;
    }
    
    public String getTrangThai(){
        return TrangThai;
    }
    public void setTrangThai(String TrangThai){
        this.TrangThai = TrangThai;
    }
}
