/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ChiTietKhuyenMaiDAO;
import DTO.ChiTietKhuyenMaiDTO;
import DTO.SanPhamDTO;
import GUI.POPUP.ThemSanPhamVaoKhuyenMaiJFrame;
import java.util.ArrayList;
import java.util.HashMap;
import static quanlycuahanggiay.Regexp.MaChiTietKhuyenMai;
import static quanlycuahanggiay.Regexp.Ten;
import static quanlycuahanggiay.unicode.removeAccent;
/**
 *
 * @author TKH
 */
public class ChiTietKhuyenMaiBUS {
    public ArrayList<ChiTietKhuyenMaiDTO> ChiTietKhuyenMaiList;
    public ChiTietKhuyenMaiDAO ChiTietKhuyenMaiDAO;
    
    public ChiTietKhuyenMaiBUS() {
        ChiTietKhuyenMaiList = new ArrayList<>();
        ChiTietKhuyenMaiDAO = new ChiTietKhuyenMaiDAO();
        fetchAll();
    }
    
    public void fetchAll() {
        ChiTietKhuyenMaiList = ChiTietKhuyenMaiDAO.fetchAll();
    }
    
    public boolean Exists(String MaChiTietKhuyenMai) {
        for (ChiTietKhuyenMaiDTO ChiTietKhuyenMai : ChiTietKhuyenMaiList) {
            if (ChiTietKhuyenMai.getMaChiTietKhuyenMai().toLowerCase().equals(MaChiTietKhuyenMai.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    
    public boolean ExistsMaCode(String MaCode) {
        for (ChiTietKhuyenMaiDTO ChiTietKhuyenMai : ChiTietKhuyenMaiList) {
            if (ChiTietKhuyenMai.getMaCode().toLowerCase().equals(MaCode.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    
    public boolean add(ChiTietKhuyenMaiDTO ChiTietKhuyenMai) {
        if (Exists(ChiTietKhuyenMai.getMaChiTietKhuyenMai())) {
            GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", "Mã phiếu nhập đã tồn tại", 1);
            return false;
        } else {
            String Error = Validation(ChiTietKhuyenMai);
            if (Error == null) {
                ChiTietKhuyenMaiDAO.add(ChiTietKhuyenMai);
                ChiTietKhuyenMaiList = ChiTietKhuyenMaiDAO.fetchAll();
                return true;
            }
            else
            GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", Error, 1);
            return false;
        }
    }
    
    private String Validation(ChiTietKhuyenMaiDTO ChiTietKhuyenMai) {
        if (!MaChiTietKhuyenMai(ChiTietKhuyenMai.getMaChiTietKhuyenMai())) {
            return "Mã chi tiết khuyến mãi phải có dạng CTKM";
        }
        if (!Ten(ChiTietKhuyenMai.getMaCode())) {
            return "Mã Code không hợp lệ";
        }
        return null;
    }
    
    public ChiTietKhuyenMaiDTO getByMaCode(String MaCode) {
        for (ChiTietKhuyenMaiDTO ChiTietKhuyenMai : ChiTietKhuyenMaiList) {
            if (ChiTietKhuyenMai.getMaCode().toLowerCase().equals(MaCode.toLowerCase())) {
                return ChiTietKhuyenMai;
            }
        }
        return null;
    }
    
    public ArrayList<ChiTietKhuyenMaiDTO> advancedSearch(HashMap<String, String> advancedKeyValue) {
        ArrayList<ChiTietKhuyenMaiDTO> result = new ArrayList<>();
        ChiTietKhuyenMaiList.forEach(ChiTietKhuyenMai -> {
            boolean TrangThai = true;
            if(advancedKeyValue.get("TrangThai").equals("Còn"))
                TrangThai = (Integer.parseInt(ChiTietKhuyenMai.getSoLuong()) - Integer.parseInt(ChiTietKhuyenMai.getSoLuongDaDung())) > 0;
            if(advancedKeyValue.get("TrangThai").equals("Hết"))
                TrangThai = (Integer.parseInt(ChiTietKhuyenMai.getSoLuong()) - Integer.parseInt(ChiTietKhuyenMai.getSoLuongDaDung())) == 0;
            if (ChiTietKhuyenMai.getMaChuongTrinhKhuyenMai().toLowerCase().contains(advancedKeyValue.get("MaChuongTrinhKhuyenMai").toLowerCase()) &&
                    ChiTietKhuyenMai.getMaChiTietKhuyenMai().toLowerCase().contains(advancedKeyValue.get("MaChiTiet").toLowerCase()) &&
                    TrangThai)
                result.add(ChiTietKhuyenMai);
        });
        return result;
    }
    public ArrayList<SanPhamDTO> advancedSearchThemSanPham(HashMap<String, String> advancedKeyValue){
        ArrayList<SanPhamDTO> result = new ArrayList<>();
        ThemSanPhamVaoKhuyenMaiJFrame.SanPhamList.forEach(SanPham ->{
        boolean TenSanPham = SanPham.getTenSanPham().toLowerCase().contains(advancedKeyValue.get("TenSanPham").toLowerCase());
        if(!TenSanPham)
                TenSanPham = removeAccent(SanPham.getTenSanPham().toLowerCase()).contains(advancedKeyValue.get("TenSanPham").toLowerCase());
        if(SanPham.getMaSanPham().toLowerCase().contains(advancedKeyValue.get("MaSanPham").toLowerCase()) &&
                TenSanPham &&
                SanPham.getMaMau().contains(advancedKeyValue.get("Mau")) &&
                SanPham.getMaSize().contains(advancedKeyValue.get("Size")) &&
                SanPham.getMaThuongHieu().contains(advancedKeyValue.get("ThuongHieu"))
            )
                result.add(SanPham);
        });    
        return result;
    }
    public ChiTietKhuyenMaiDTO getByMaChiTietKhuyenMai(String MaChiTietKhuyenMai){
        for(ChiTietKhuyenMaiDTO ChiTietKhuyenMai : ChiTietKhuyenMaiList){
            if(ChiTietKhuyenMai.getMaChiTietKhuyenMai().toLowerCase().equals(MaChiTietKhuyenMai.toLowerCase()))
                return ChiTietKhuyenMai;
        }
        return null;
    }
    
    public String getSoLuongbyMaChuongTrinhKhuyenMai(String MaChuongTrinhKhuyenMai){
        for (ChiTietKhuyenMaiDTO ChiTietKhuyenMai : ChiTietKhuyenMaiList) {
            if(ChiTietKhuyenMai.getMaChuongTrinhKhuyenMai().equals(MaChuongTrinhKhuyenMai))
                return ChiTietKhuyenMai.getSoLuong();
        }
        return null;
    }
     public String getLatest() {
        return ChiTietKhuyenMaiDAO.getLatest();
    }
}
