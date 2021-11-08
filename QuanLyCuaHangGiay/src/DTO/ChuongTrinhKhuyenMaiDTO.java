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
public class ChuongTrinhKhuyenMaiDTO {
    protected String MaChuongTrinhKhuyenMai,TenChuongTrinh,NgayTao;

    public ChuongTrinhKhuyenMaiDTO() {
    }

    public ChuongTrinhKhuyenMaiDTO(String MaChuongTrinhKhuyenMai, String TenChuongTrinh,String NgayTao) {
        this.MaChuongTrinhKhuyenMai = MaChuongTrinhKhuyenMai;
        this.TenChuongTrinh = TenChuongTrinh;
        this.NgayTao = NgayTao;
    }
    
    public String getPrimaryKey(){
        return "MaChuongTrinhKhuyenMai='"+this.getMaChuongTrinhKhuyenMai()+"'";
    }
    public String getMaChuongTrinhKhuyenMai() {
        return MaChuongTrinhKhuyenMai;
    }

    public String getNgayTao() {
        return NgayTao;
    }
    
    public String getTenChuongTrinh() {
        return TenChuongTrinh;
    }

    public void setMaChuongTrinhKhuyenMai(String MaChuongTrinhKhuyenMai) {
        this.MaChuongTrinhKhuyenMai = MaChuongTrinhKhuyenMai;
    }

    public void setTenChuongTrinh(String TenChuongTrinh) {
        this.TenChuongTrinh = TenChuongTrinh;
    }

    public void setNgayTao(String NgayTao) {
        this.NgayTao = NgayTao;
    }
    
}
