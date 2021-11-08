/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author TKH
 */
public class ChiTietKhuyenMaiDTO {
    protected String MaChiTietKhuyenMai, MaChuongTrinhKhuyenMai, MaCode, PhanTramKhuyenMai, GiaTienKhuyenMai, GiaTriToiThieuSuDung,
                     GiaTriGiamToiDa, ThoiGianBatDauSuDung, ThoiGianKetThucSuDung, SoLuong, SoLuongDaDung;

    public ChiTietKhuyenMaiDTO() {
        
    }
    
    public ChiTietKhuyenMaiDTO(String MaChiTietKhuyenMai, String MaChuongTrinhKhuyenMai, String MaCode, String PhanTramKhuyenMai, String GiaTienKhuyenMai, String GiaTriToiThieuSuDung,
                     String GiaTriGiamToiDa, String ThoiGianBatDauSuDung, String ThoiGianKetThucSuDung, String SoLuong, String SoLuongDaDung) {
        this.MaChiTietKhuyenMai = MaChiTietKhuyenMai;
        this.MaChuongTrinhKhuyenMai = MaChuongTrinhKhuyenMai;
        this.MaCode = MaCode;
        this.PhanTramKhuyenMai = PhanTramKhuyenMai;
        this.GiaTienKhuyenMai = GiaTienKhuyenMai;
        this.GiaTriToiThieuSuDung = GiaTriToiThieuSuDung;
        this.GiaTriGiamToiDa = GiaTriGiamToiDa;
        this.ThoiGianBatDauSuDung = ThoiGianBatDauSuDung;
        this.ThoiGianKetThucSuDung = ThoiGianKetThucSuDung;
        this.SoLuong = SoLuong;
        this.SoLuongDaDung = SoLuongDaDung;
    }
    
    public String getPrimaryKey(){
        return "MaChiTietKhuyenMai='"+this.getMaChiTietKhuyenMai()+"'";
    }
    
    public String getMaChiTietKhuyenMai(){
        return MaChiTietKhuyenMai;
    }
    
    public String getMaChuongTrinhKhuyenMai(){
        return MaChuongTrinhKhuyenMai;
    }
    
    public String getMaCode(){
        return MaCode;
    }
    
    public String getPhanTramKhuyenMai(){
        return PhanTramKhuyenMai;
    }
    
    public String getGiaTienKhuyenMai(){
        return GiaTienKhuyenMai;
    }
    
    public String getGiaTriToiThieuSuDung(){
        return GiaTriToiThieuSuDung;
    }
    
    public String getGiaTriGiamToiDa(){
        return GiaTriGiamToiDa;
    }
    
    public String getThoiGianBatDauSuDung(){
        return ThoiGianBatDauSuDung;
    }
    
    public String getThoiGianKetThucSuDung(){
        return ThoiGianKetThucSuDung;
    }
    
    public String getSoLuong(){
        return SoLuong;
    }
    
    public String getSoLuongDaDung(){
        return SoLuongDaDung;
    }
    
    public void setMaChiTietKhuyenMai(String MaChiTietKhuyenMai){
        this.MaChiTietKhuyenMai = MaChiTietKhuyenMai;
    }
    
    public void setMaChuongTrinhKhuyenMai(String MaChuongTrinhKhuyenMai){
        this.MaChuongTrinhKhuyenMai = MaChuongTrinhKhuyenMai;
    }
    
    public void setMaCode(String MaCode){
        this.MaCode = MaCode;
    }
    
    public void setPhanTramKhuyenMai(String PhanTramKhuyenMai){
        this.PhanTramKhuyenMai = PhanTramKhuyenMai;
    }
    
    public void setGiaTienKhuyenMai(String GiaTienKhuyenMai){
        this.GiaTienKhuyenMai = GiaTienKhuyenMai;
    }
    
    public void setGiaTriToiThieuSuDung(String GiaTriToiThieuSuDung){
        this.GiaTriToiThieuSuDung = GiaTriToiThieuSuDung;
    }
    
    public void setGiaTriGiamToiDa(String GiaTriGiamToiDa){
        this.GiaTriGiamToiDa = GiaTriGiamToiDa;
    }
    
    public void setThoiGianBatDauSuDung(String ThoiGianBatDauSuDung){
        this.ThoiGianBatDauSuDung = ThoiGianBatDauSuDung;
    }
    
    public void setThoiGianKetThucSuDung(String ThoiGianKetThucSuDung){
        this.ThoiGianKetThucSuDung = ThoiGianKetThucSuDung;
    }
    
    public void setSoLuong(String SoLuong){
        this.SoLuong = SoLuong;
    }
    
    public void setSoLuongDaDung(String SoLuongDaDung){
        this.SoLuongDaDung = SoLuongDaDung;
    }
    
    
}
