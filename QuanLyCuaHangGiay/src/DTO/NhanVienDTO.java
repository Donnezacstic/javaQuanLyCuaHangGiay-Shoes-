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
public class NhanVienDTO {
    
    protected String MaNhanVien, HoTen, GioiTinh, NamSinh, SoDienThoai, DiaChi, TrangThai, Luong, NgayVaoLam,CaLam,ImgSource;

    public NhanVienDTO() {
    }

    ;

    public NhanVienDTO(String MaNhanVien, String HoTen, String GioiTinh, String NamSinh, String SoDienThoai, String DiaChi, String TrangThai, String Luong, String NgayVaoLam,String CaLam,String ImgSource) {
        this.MaNhanVien = MaNhanVien;
        this.HoTen = HoTen;
        this.GioiTinh = GioiTinh;
        this.NamSinh = NamSinh;
        this.SoDienThoai = SoDienThoai;
        this.DiaChi = DiaChi;
        this.TrangThai = TrangThai;
        this.Luong = Luong;
        this.NgayVaoLam = NgayVaoLam;
        this.CaLam = CaLam;
        this.ImgSource = ImgSource;
    }

    public String getPrimaryKey() {
        return "MaNhanVien='" + this.getMaNhanVien() + "'";
    }

    public String getCaLam() {
        return CaLam;
    }
    public String getImgSource(){
        return ImgSource;
    }
    public String getTrangThai() {
        return TrangThai;
    }

    public String getLuong() {
        return Luong;
    }

    public String getNgayVaoLam() {
        return NgayVaoLam;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public String getMaNhanVien() {
        return MaNhanVien;
    }

    public String getHoTen() {
        return HoTen;
    }

    public String getNamSinh() {
        return NamSinh;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setMaNhanVien(String MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }
    public void setImgSource(String ImgSource){
        this.ImgSource = ImgSource;
    }
    public void setNamSinh(String NamSinh) {
        this.NamSinh = NamSinh;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    public void setLuong(String Luong) {
        this.Luong = Luong;
    }

    public void setNgayVaoLam(String NgayVaoLam) {
        this.NgayVaoLam = NgayVaoLam;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public void setCaLam(String CaLam) {
        this.CaLam = CaLam;
    }
    
}