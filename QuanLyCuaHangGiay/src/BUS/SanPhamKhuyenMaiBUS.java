/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.SanPhamKhuyenMaiDAO;
import DTO.SanPhamKhuyenMaiDTO;
import java.util.ArrayList;
import java.util.HashMap;
import static quanlycuahanggiay.unicode.removeAccent;

/**
 *
 * @author ASUS
 */
public class SanPhamKhuyenMaiBUS {
    public ArrayList<SanPhamKhuyenMaiDTO> SanPhamKhuyenMaiList;
    public SanPhamKhuyenMaiDAO SanPhamKhuyenMaiDAO;
    
    public SanPhamKhuyenMaiBUS(){
    SanPhamKhuyenMaiList = new ArrayList<>();
    SanPhamKhuyenMaiDAO = new SanPhamKhuyenMaiDAO();
    fetchAll();
    }
    public void fetchAll(){
        SanPhamKhuyenMaiList = SanPhamKhuyenMaiDAO.fetchAll();
    
    }
    
    public void add(SanPhamKhuyenMaiDTO SanPhamKhuyenMai){
        SanPhamKhuyenMaiDAO.add(SanPhamKhuyenMai);
        SanPhamKhuyenMaiList = SanPhamKhuyenMaiDAO.fetchAll();
    }
    public void edit(SanPhamKhuyenMaiDTO SanPhamKhuyenMai){
        SanPhamKhuyenMaiDAO.edit(SanPhamKhuyenMai);
        SanPhamKhuyenMaiList = SanPhamKhuyenMaiDAO.fetchAll();
    }
    public void remove(SanPhamKhuyenMaiDTO SanPhamKhuyenMai){
    }
    public ArrayList<String> advancedSearch(HashMap<String, String> advancedKeyValue) {
        ArrayList<String> result = new ArrayList<>();
        quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.SanPhamList.forEach(ChiTietKhuyenMai -> {
        boolean TenSanPham = ChiTietKhuyenMai.getTenSanPham().toLowerCase().contains(advancedKeyValue.get("TenSanPham").toLowerCase());
        if(!TenSanPham)
                TenSanPham = removeAccent(ChiTietKhuyenMai.getTenSanPham().toLowerCase()).contains(advancedKeyValue.get("TenSanPham").toLowerCase());
        if(ChiTietKhuyenMai.getMaSanPham().toLowerCase().contains(advancedKeyValue.get("MaSanPham").toLowerCase()) &&
                TenSanPham &&
                ChiTietKhuyenMai.getMaMau().contains(advancedKeyValue.get("Mau")) &&
                ChiTietKhuyenMai.getMaSize().contains(advancedKeyValue.get("Size")) &&
                ChiTietKhuyenMai.getMaThuongHieu().contains(advancedKeyValue.get("ThuongHieu"))
            )
                result.add(ChiTietKhuyenMai.getMaSanPham());
            
            
        });
        return result;
    }
    public ArrayList<String> getSanPhamKhuyenMai(String MaChiTietKhuyenMai){
        ArrayList<String> MaSanPhamList = new ArrayList<>();
        for(SanPhamKhuyenMaiDTO SanPhamKhuyenMai: SanPhamKhuyenMaiList)
        {
            if(SanPhamKhuyenMai.getMaChiTietKhuyenMai().equals(MaChiTietKhuyenMai))
                MaSanPhamList.add(SanPhamKhuyenMai.getMaSanPham());
        }
        if(MaSanPhamList.size()==1){
            if(MaSanPhamList.get(0)==null)
                return null;
            else 
                return MaSanPhamList;
        }
        return MaSanPhamList;
    }
    /*
    public ArrayList<SanPhamKhuyenMaiDTO> advancedSearch(HashMap<String, String> advancedKeyValue){
        ArrayList<SanPhamKhuyenMaiDTO> result = new ArrayList<>();
        SanPhamKhuyenMaiList.forEach(SanPhamKhuyenMai ->{
            if( SanPhamKhuyenMai.getMaSanPham().toLowerCase().contains(advancedKeyValue.get("MaSanPham").toLowerCase()) &&
                SanPhamKhuyenMai.
                    )
        });
    }
*/
}
