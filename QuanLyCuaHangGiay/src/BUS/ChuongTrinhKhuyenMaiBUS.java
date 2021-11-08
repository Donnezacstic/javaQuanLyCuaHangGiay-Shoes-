/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ChuongTrinhKhuyenMaiDAO;
import DTO.ChuongTrinhKhuyenMaiDTO;
import java.util.ArrayList;
import java.util.HashMap;
import static quanlycuahanggiay.Regexp.MaChuongTrinhKhuyenMai;
import static quanlycuahanggiay.Regexp.Ten;
import static quanlycuahanggiay.unicode.removeAccent;

/**
 *
 * @author Administrator
 */
public class ChuongTrinhKhuyenMaiBUS {

    public ArrayList<ChuongTrinhKhuyenMaiDTO> ChuongTrinhKhuyenMaiList;
    public ChuongTrinhKhuyenMaiDAO ChuongTrinhKhuyenMaiDAO;

    public ChuongTrinhKhuyenMaiBUS() {
        ChuongTrinhKhuyenMaiList = new ArrayList<>();
        ChuongTrinhKhuyenMaiDAO = new ChuongTrinhKhuyenMaiDAO();
        fetchAll();
    }

    public void fetchAll() {
        ChuongTrinhKhuyenMaiList = ChuongTrinhKhuyenMaiDAO.fetchAll();
    }

    public boolean Exists(String MaChuongTrinhKhuyenMai) {
        for (ChuongTrinhKhuyenMaiDTO ChuongTrinhKhuyenMai : ChuongTrinhKhuyenMaiList) {
            if (ChuongTrinhKhuyenMai.getMaChuongTrinhKhuyenMai().toLowerCase().equals(MaChuongTrinhKhuyenMai.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    private String Validation(ChuongTrinhKhuyenMaiDTO ChuongTrinhKhuyenMai) {
        if (!MaChuongTrinhKhuyenMai(ChuongTrinhKhuyenMai.getMaChuongTrinhKhuyenMai())) {
            return "Mã chương trình khuyến mãi phải có dạng CT";
        }
        if (!Ten(ChuongTrinhKhuyenMai.getTenChuongTrinh())) {
            return "Tên chương trình không hợp lệ";
        }
        return null;
    }

    public ArrayList<ChuongTrinhKhuyenMaiDTO> advancedSearch(HashMap<String, String> advancedKeyValue) {
        ArrayList<ChuongTrinhKhuyenMaiDTO> result = new ArrayList<>();
        ChuongTrinhKhuyenMaiList.forEach(ChuongTrinhKhuyenMai -> {
            if (ChuongTrinhKhuyenMai.getMaChuongTrinhKhuyenMai().toLowerCase().contains(advancedKeyValue.get("MaChuongTrinhKhuyenMai").toLowerCase())
                    && (removeAccent(ChuongTrinhKhuyenMai.getTenChuongTrinh().toLowerCase()).contains(advancedKeyValue.get("TenChuongTrinhKhuyenMai").toLowerCase()))) {
                result.add(ChuongTrinhKhuyenMai);
            }
        });
        return result;
    }

    public boolean add(ChuongTrinhKhuyenMaiDTO ChuongTrinhKhuyenMai) {
        if (Exists(ChuongTrinhKhuyenMai.getMaChuongTrinhKhuyenMai())) {
            GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", "Mã chương trình đã tồn tại", 1);
            return false;
        } else {
            String Error = Validation(ChuongTrinhKhuyenMai);
            if (Error == null) {
                ChuongTrinhKhuyenMaiDAO.add(ChuongTrinhKhuyenMai);
                ChuongTrinhKhuyenMaiList = ChuongTrinhKhuyenMaiDAO.fetchAll();
                return true;
            } else {
                GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", Error, 1);
            }
            return false;
        }
    }

    public boolean edit(ChuongTrinhKhuyenMaiDTO ChuongTrinhKhuyenMai) {
        String Error = Validation(ChuongTrinhKhuyenMai);
        if (Error == null) {
            ChuongTrinhKhuyenMaiDAO.edit(ChuongTrinhKhuyenMai);
            ChuongTrinhKhuyenMaiList = ChuongTrinhKhuyenMaiDAO.fetchAll();
            return true;
        }
        GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", Error, 1);
        return false;
    }

    public ChuongTrinhKhuyenMaiDTO getByMaChuongTrinhKhuyenMai(String MaChuongTrinhKhuyenMai) {
        for (ChuongTrinhKhuyenMaiDTO ChuongTrinhKhuyenMai : ChuongTrinhKhuyenMaiList) {
            if (ChuongTrinhKhuyenMai.getMaChuongTrinhKhuyenMai().toLowerCase().equals(MaChuongTrinhKhuyenMai.toLowerCase())) {
                return ChuongTrinhKhuyenMai;
            }
        }
        return null;
    }
    
    public String getNgayTaobyMaChuongTrinhKhuyenMai(String MaChuongTrinhKhuyenMai){
        for (ChuongTrinhKhuyenMaiDTO ChuongTrinhKhuyenMai : ChuongTrinhKhuyenMaiList) {
            if(ChuongTrinhKhuyenMai.getMaChuongTrinhKhuyenMai().equals(MaChuongTrinhKhuyenMai))
                return ChuongTrinhKhuyenMai.getNgayTao();
        }
        return null;
    }
    
    public void remove(ChuongTrinhKhuyenMaiDTO ChuongTrinhKhuyenMai) {
    }
    
    public  String[] getTenStringArray(){
        String[] MaChuongTrinhKhuyenMai = new String[ChuongTrinhKhuyenMaiList.size()];
        for(int i=0;i<ChuongTrinhKhuyenMaiList.size();i++){
            MaChuongTrinhKhuyenMai[i] = ChuongTrinhKhuyenMaiList.get(i).getMaChuongTrinhKhuyenMai();
        }
        return MaChuongTrinhKhuyenMai;
    }
    
    public String getLatest() {
        return ChuongTrinhKhuyenMaiDAO.getLatest();
    }

}
