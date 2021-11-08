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
public class SanPhamKhuyenMaiDTO {
    protected String MaChiTietKhuyenMai, MaSanPham;
    
    public SanPhamKhuyenMaiDTO() {
    }

    public SanPhamKhuyenMaiDTO(String MaChiTietKhuyenMai, String MaSanPham) {
        this.MaChiTietKhuyenMai = MaChiTietKhuyenMai;
        this.MaSanPham = MaSanPham;
    }
    
    public String getPrimaryKey(){
        return "MaChiTietKhuyenMai='"+this.getMaChiTietKhuyenMai()+"' and MaSanPham='"+this.getMaSanPham()+"'";
    }
    
    public String getMaChiTietKhuyenMai() {
        return MaChiTietKhuyenMai;
    }

    public String getMaSanPham() {
        return MaSanPham;
    }
    
    public void setMaChiTietKhuyenMai(String MaChiTietKhuyenMai) {
        this.MaChiTietKhuyenMai = MaChiTietKhuyenMai;
    }

    public void setMaSanPham(String MaSanPham) {
        this.MaSanPham = MaSanPham;
    }
    
}
