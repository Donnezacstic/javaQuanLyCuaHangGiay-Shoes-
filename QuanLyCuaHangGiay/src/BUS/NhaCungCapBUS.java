/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;
import java.util.ArrayList;
import static quanlycuahanggiay.Regexp.DiaChi;
import static quanlycuahanggiay.Regexp.MaNhaCungCap;
import static quanlycuahanggiay.Regexp.SoDienThoai;
import static quanlycuahanggiay.Regexp.Ten;

/**
 *
 * @author Administrator
 */
public class NhaCungCapBUS {
    public ArrayList<NhaCungCapDTO> NhaCungCapList;
    public NhaCungCapDAO NhaCungCapDAO;
    public NhaCungCapBUS(){
        NhaCungCapList = new ArrayList<>();
        NhaCungCapDAO = new NhaCungCapDAO();
        fetchAll();
    }
    public void fetchAll(){
        NhaCungCapList = NhaCungCapDAO.fetchAll();
    }
       public boolean Exists(String MaNhaCungCap){
        for(NhaCungCapDTO NhaCungCap:NhaCungCapList)
            if(NhaCungCap.getMaNhaCungCap().toLowerCase().equals(MaNhaCungCap.toLowerCase())) return true;
            return false;
    } 
    
    public boolean add(NhaCungCapDTO NhaCungCap){
        if(Exists(NhaCungCap.getMaNhaCungCap())){ GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi","Nhà cung cấp đã tồn tại.Nhà cung cấp mới là "+getLatest(), 3);
            return false;
        }
        else {
            String Error = Validation(NhaCungCap);
            if(Error == null){
            NhaCungCapDAO.add(NhaCungCap);
            NhaCungCapList = NhaCungCapDAO.fetchAll();
            return true;
            }
            else
            GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", Error, 1);
             return false;
                }
            
    }
    public void edit(NhaCungCapDTO NhaCungCap){
        NhaCungCapDAO.edit(NhaCungCap);
        fetchAll();
        GUI.CHITIET.QuanLyNhaCungCapJPanel.fetchAll();
    }
    public void remove(NhaCungCapDTO NhaCungCap){
        
    }
    public String Validation(NhaCungCapDTO NhaCungCap){
        if(!MaNhaCungCap(NhaCungCap.getMaNhaCungCap())) return "Mã nhà cung cấp phải có dạng NCC";
        if(!SoDienThoai(NhaCungCap.getSoDienThoai())) return "Số điện thoại không hợp lệ";
        if(!Ten(NhaCungCap.getTenNhaCungCap())) return "Tên không được chứa ký tự hợp lệ";
        if(!DiaChi(NhaCungCap.getDiaChi())) return "Địa chỉ không được chứa ký tự đặc biệt";
        return null;
    }
    public NhaCungCapDTO getByMaNhaCungCap(String MaNhaCungCap) {
        for (NhaCungCapDTO NhaCungCap : NhaCungCapList) {
            if (NhaCungCap.getMaNhaCungCap().toLowerCase().equals(MaNhaCungCap.toLowerCase())) {
                return NhaCungCap;
            }
        }
        return null;
    }
    public String getTenNhaCungCapByMaNhaCungCap(NhaCungCapDTO MaNhaCungCap){
        for (NhaCungCapDTO NhaCungCap : NhaCungCapList) {
            if(NhaCungCap.getMaNhaCungCap().equals(MaNhaCungCap))
                return NhaCungCap.getTenNhaCungCap();
        }
        return null;
    }
    public String getMaNhaCungCapByTenNhaCungCap(String TenNhaCungCap){
        for (NhaCungCapDTO NhaCungCap : NhaCungCapList) {
            if(NhaCungCap.getTenNhaCungCap().equals(TenNhaCungCap))
                return NhaCungCap.getMaNhaCungCap();
        }
        return null;
    }
    public String getLatest(){
        return NhaCungCapDAO.getLatest();
    }
    public String [] getTenStringArray(){
        String[] TenNhaCungCap = new String[NhaCungCapList.size()];
        for (int i = 0; i < NhaCungCapList.size(); i++) {
            TenNhaCungCap[i] = NhaCungCapList.get(i).getTenNhaCungCap();
        }
        return TenNhaCungCap;
    }
}
    
