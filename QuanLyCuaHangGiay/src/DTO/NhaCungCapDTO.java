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
public class NhaCungCapDTO {
    protected String MaNhaCungCap,TenNhaCungCap,SoDienThoai,DiaChi;
    public NhaCungCapDTO(){}

    public NhaCungCapDTO(String MaNhaCungCap, String TenNhaCungCap, String SoDienThoai, String DiaChi) {
        this.MaNhaCungCap = MaNhaCungCap;
        this.TenNhaCungCap = TenNhaCungCap;
        this.SoDienThoai = SoDienThoai;
        this.DiaChi = DiaChi;
    }
    public String getPrimaryKey(){
        return "MaNhaCungCap='"+this.getMaNhaCungCap()+"'";
    }
    
    public String getMaNhaCungCap() {
        return MaNhaCungCap;
    }

    public String getTenNhaCungCap() {
        return TenNhaCungCap;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setMaNhaCungCap(String MaNhaCungCap) {
        this.MaNhaCungCap = MaNhaCungCap;
    }

    public void setTenNhaCungCap(String TenNhaCungCap) {
        this.TenNhaCungCap = TenNhaCungCap;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }
    
}
