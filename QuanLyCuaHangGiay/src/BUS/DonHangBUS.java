/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DonHangDAO;
import DTO.DonHangDTO;
import BUS.KhachHangBUS;
import DTO.KhachHangDTO;
import DTO.NhanVienDTO;
import DTO.ChiTietDonHangDTO;
import java.util.ArrayList;
import java.util.HashMap;
import static quanlycuahanggiay.Regexp.DiaChi;
import static quanlycuahanggiay.Regexp.MaDonHang;
import static quanlycuahanggiay.Regexp.MaNhanVien;
import static quanlycuahanggiay.Regexp.MucLuong;
import static quanlycuahanggiay.Regexp.NamSinh;
import static quanlycuahanggiay.Regexp.So;
import static quanlycuahanggiay.Regexp.SoDienThoai;
import static quanlycuahanggiay.Regexp.Ten;
import static quanlycuahanggiay.unicode.removeAccent;

/**
 *
 * @author HUUNHAN
 */
public class DonHangBUS {
    public ArrayList<DonHangDTO> DonHangList;
    public DonHangDAO DonHangDAO;
    
    public DonHangBUS(){
        DonHangDAO = new DonHangDAO();
        DonHangList = new ArrayList<>();
        fetchAll();   
    }
    public void  fetchAll(){
        DonHangList = DonHangDAO.fetchAll();
    }
    
public boolean Exists(String MaDonHang){
    for(DonHangDTO DonHang : DonHangList){
        if(DonHang.getMaDonHang().toLowerCase().equals(MaDonHang.toLowerCase())){
            return true;
        }
    }
        return false;
    }
    /*
    public ArrayList<DonHangDTO> findByMaDonHang(String MaDonHang){
        ArrayList<DonHangDTO> result = new ArrayList<>();
        DonHangList.forEach(DonHang ->{
            if(DonHang.getMaDonHang().toLowerCase().contains(MaDonHang.toLowerCase()))
                result.add(DonHang);
        });
        return result;
    }
    */
    public DonHangDTO getByMaDonHang(String MaDonHang) {
        for (DonHangDTO DonHang : DonHangList) {
            if (DonHang.getMaDonHang().toLowerCase().equals(MaDonHang.toLowerCase())) {
                return DonHang;
            }
        }
        return null;
    }
    public ArrayList<DonHangDTO> getByMaKhachHang(String MaKhachHang) {
        System.out.println(MaKhachHang);
        ArrayList<DonHangDTO> result = new ArrayList<>();
        for (DonHangDTO DonHang : DonHangList) {
            if (DonHang.getMaKhachHang().toLowerCase().equals(MaKhachHang.toLowerCase())) {
                result.add(DonHang);
            }
        }
        return result;
    }
    /*
    public ArrayList<DonHangDTO> findByHoTen(String HoTen){
        
        ArrayList<DonHangDTO> result = new ArrayList<>();
        DonHangList.forEach(DonHang ->{
            KhachHangDTO KhachHang = quanlycuahanggiay.quanlycuahanggiay.KhachHangBUS.getByMaKhachHang(DonHang.getMaKhachHang());
            if(KhachHang.getHoTen().toLowerCase().contains(HoTen.toLowerCase())) result.add(DonHang);
        });
        return result;
    }
    */
    public ArrayList<DonHangDTO> advancedSearch(HashMap<String,String> advancedKeyValue){
            ArrayList<DonHangDTO> result = new ArrayList<>();
         DonHangList.forEach(DonHang ->{
             KhachHangDTO KhachHang = quanlycuahanggiay.quanlycuahanggiay.KhachHangBUS.getByMaKhachHang(DonHang.getMaKhachHang());
             System.out.println(KhachHang.getHoTen());
             System.out.println(advancedKeyValue.get("HoTenSDT"));
             boolean TenHoacSDT = KhachHang.getHoTen().toLowerCase().contains(advancedKeyValue.get("HoTenSDT").toLowerCase());//
             if(!TenHoacSDT) TenHoacSDT = removeAccent(KhachHang.getHoTen().toLowerCase()).contains(advancedKeyValue.get("HoTenSDT").toLowerCase());
             if(So(advancedKeyValue.get("HoTenSDT"))){
                 TenHoacSDT = KhachHang.getSoDienThoai().contains(advancedKeyValue.get("HoTenSDT"));
             }
             if(DonHang.getMaDonHang().contains(advancedKeyValue.get("MaDonHang").toUpperCase()) &&
                TenHoacSDT &&
                DonHang.getTrangThai().contains(advancedKeyValue.get("TrangThai")))
                
                result.add(DonHang);
         });
         return result;  
    }
    
    public boolean add(DonHangDTO DonHang) {
        if (Exists(DonHang.getMaKhachHang())) {
            GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", "Mã đơn hàng đã tồn tại", 1);
            return false;
        } else {
            String Error = Validation(DonHang);
            if (Error == null) {
                DonHangDAO.add(DonHang);
                DonHangList = DonHangDAO.fetchAll();
                return true;
            }
            else
            GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", Error, 1);
            return false;
        }
    }

    public boolean edit(DonHangDTO DonHang) {
        String Error = Validation(DonHang);
                if (Error == null) {
                DonHangDAO.edit(DonHang);
                DonHangList = DonHangDAO.fetchAll();
                return true;
            }
        GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", Error, 1);
            return false;
    }
    
    private String Validation(DonHangDTO DonHang) {
        if (!MaDonHang(DonHang.getMaDonHang())) {
            return "Mã đon hàng phải có dạng DH";
        }
        return null;
    }
    
     public String getLatest(){
    return DonHangDAO.getLatest();
    }
    
     public static  ArrayList<String[]> getSoLuongVaoNgay(String MaDon){
         return DAO.DonHangDAO.getSoLuongVaoNgay(MaDon);
     }
     public static ArrayList<String[]> getSanPhamVaoNgay(String MaDon){
         return DAO.DonHangDAO.getSanPhamVaoNgay(MaDon);
     }
     public static ArrayList<String[]> getSanPhamNgayDoanhThu(String MaDon){
         return DAO.DonHangDAO.getSanPhamNgayDoanhThu(MaDon);
     }
}
