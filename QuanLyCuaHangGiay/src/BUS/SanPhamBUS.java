
package BUS;

import DAO.SanPhamDAO;
import DTO.SanPhamDTO;
import java.util.ArrayList;
import java.util.HashMap;
import static quanlycuahanggiay.Regexp.DonGia;
import static quanlycuahanggiay.Regexp.SoTuNhien;
import static quanlycuahanggiay.Regexp.Ten;
import static quanlycuahanggiay.unicode.removeAccent;

public class SanPhamBUS {
    public ArrayList<SanPhamDTO> SanPhamList;
    public SanPhamDAO SanPhamDAO;
    
    public SanPhamBUS(){
        SanPhamDAO = new SanPhamDAO();
        SanPhamList = new ArrayList<>();
        fetchAll();
    }
    
    public void fetchAll(){
        SanPhamList = SanPhamDAO.fetchAll();
    }
    
    
    public boolean Exists(String MaSanPham){
        for (SanPhamDTO SanPham : SanPhamList) {
            if(SanPham.getMaSanPham().toLowerCase().equals(MaSanPham.toLowerCase())){
                return true;
            }
        }
        return false;
    }
    
    public boolean add(SanPhamDTO SanPham){
        if(Exists(SanPham.getMaSanPham())){
            GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", "Mã sản phẩm đã tồn tại", 1);
            return false;
        } else{
            String Error = Validation(SanPham);
            if(Error == null){
                    SanPhamDAO.add(SanPham);
                    SanPhamList = SanPhamDAO.fetchAll();
                    return true;
            }
            else
                GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", Error, 1);
                return false;
        }
    }
    
    public boolean edit(SanPhamDTO SanPham){
        String Error = Validation(SanPham);
            if(Error == null){
                SanPhamDAO.edit(SanPham);
                SanPhamList = SanPhamDAO.fetchAll();
                return true;
            }
        GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", Error, 1);
            return false;
    }
    
    public ArrayList<SanPhamDTO> advancedSearch(HashMap<String, String> advancedKeyValue){
        ArrayList<SanPhamDTO> result = new ArrayList<>();
        SanPhamList.forEach(SanPham ->{
        boolean TrangThai = true;
        if(advancedKeyValue.get("TrangThai").equals("Còn hàng"))
            TrangThai = (Integer.parseInt(SanPham.getSoLuong())) > 0;
        if(advancedKeyValue.get("TrangThai").equals("Hết hàng"))
            TrangThai = (Integer.parseInt(SanPham.getSoLuong())) == 0;
        boolean TenSanPham = SanPham.getTenSanPham().toLowerCase().contains(advancedKeyValue.get("TenSanPham").toLowerCase());
        if(!TenSanPham)
            TenSanPham = removeAccent(SanPham.getTenSanPham().toLowerCase()).contains(advancedKeyValue.get("TenSanPham").toLowerCase());
        if(SanPham.getMaSanPham().toLowerCase().contains(advancedKeyValue.get("MaSanPham").toLowerCase()) &&
                TenSanPham &&
                SanPham.getMaMau().contains(advancedKeyValue.get("Mau")) &&
                SanPham.getMaSize().contains(advancedKeyValue.get("Size")) &&
                SanPham.getMaThuongHieu().contains(advancedKeyValue.get("ThuongHieu")) &&
                TrangThai)
                result.add(SanPham);
        });   
        return result;
    }
    public SanPhamDTO getByMaSanPham(String MaSanPham){
        for (SanPhamDTO SanPham : SanPhamList) {
            if(SanPham.getMaSanPham().toLowerCase().equals(MaSanPham.toLowerCase())){
                return SanPham;
            }
        }
        return null;
    }
    
    public ArrayList<SanPhamDTO> findByTenSanPham(String TenSanPham){
        ArrayList<SanPhamDTO> result = new ArrayList<>();
        SanPhamList.forEach(SanPham ->{
            if(SanPham.getTenSanPham().toLowerCase().contains(TenSanPham.toLowerCase()))
                result.add(SanPham);
        });
        return result;
    }
    
    public String getTenSanPhamByMaSanPham(String MaSanPham){
        for (SanPhamDTO Ten : SanPhamList) {
            if(Ten.getMaSanPham().equals(MaSanPham))
                return Ten.getTenSanPham();
        }
        return null;
    }
    public String getMaSizeByMaSanPham(String MaSanPham){
        for (SanPhamDTO Mau : SanPhamList) {
            if(Mau.getMaSanPham().equals(MaSanPham))
                return Mau.getMaSize();
        }
        return null;
    }
    public SanPhamDTO getSanPhamByMaSanPham(String MaSanPham){
        for (SanPhamDTO sanpham : SanPhamList){
            if(sanpham.getMaSanPham().equals(MaSanPham))
                return sanpham;
        }
        return null;
    }
    public SanPhamDTO getSanPhamByTenSanPham(String TenSanPham){
        for (SanPhamDTO sanpham : SanPhamList){
            if(sanpham.getTenSanPham().equals(TenSanPham))
                return sanpham;
        }
        return null;
    }
    private String Validation(SanPhamDTO SanPham){
        if(!SoTuNhien(SanPham.getDonGia())){
            return "Đơn giá không hợp lệ";
        }
        if(!Ten(SanPham.getTenSanPham())){
            return "Tên sản phẩm không hợp lệ";
        }
        return null;
    }
    public void remove(SanPhamDTO SanPham ){
    }
    public String getLatest(){
        return SanPhamDAO.getLatest();
    }
}
