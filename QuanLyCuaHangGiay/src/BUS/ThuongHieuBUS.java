/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ThuongHieuDAO;
import DTO.ThuongHieuDTO;
import java.util.ArrayList;
import static quanlycuahanggiay.Regexp.MaThuongHieu;
import static quanlycuahanggiay.Regexp.Ten;

/**
 *
 * @author Administrator
 */
public class ThuongHieuBUS {
    public ArrayList<ThuongHieuDTO> ThuongHieuList;
    public ThuongHieuDAO ThuongHieuDAO;
    public ThuongHieuBUS(){
        ThuongHieuDAO = new ThuongHieuDAO();
        ThuongHieuList = new ArrayList<>();
        fetchAll();
    }
    public void fetchAll(){
        ThuongHieuList = ThuongHieuDAO.fetchAll();
    }
    public boolean Exists(String MaThuongHieu){
        for(ThuongHieuDTO ThuongHieu:ThuongHieuList)
            if(ThuongHieu.getMaThuongHieu().toLowerCase().equals(MaThuongHieu.toLowerCase())) return true;
            return false;
    }
    
public String Validation(ThuongHieuDTO ThuongHieu){
        if(!MaThuongHieu(ThuongHieu.getMaThuongHieu())) return "Mã thương hiệu phải có dạng S";
        if(!Ten(ThuongHieu.getTenThuongHieu())) return "Tên thương hiệu không được chứa ký tự đặc biệt";
        return null;
    }
    public boolean add(ThuongHieuDTO ThuongHieu){
         if(Exists(ThuongHieu.getMaThuongHieu())){ GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi","Mã nhà cung cấp đã tồn tại", 3);
            return false;
        }
        else {
            String Error = Validation(ThuongHieu);
            if(Error == null){
            ThuongHieuDAO.add(ThuongHieu);
            ThuongHieuList = ThuongHieuDAO.fetchAll();
            return true;
            }
            else
            GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", Error, 1);
             return false;
                }
            
            
    }    public void edit(ThuongHieuDTO ThuongHieu){
        
        ThuongHieuDAO.edit(ThuongHieu);
        fetchAll();
        GUI.CHITIET.QuanLyThuongHieuJPanel.fetchAll();
    }
    public void remove(ThuongHieuDTO ThuongHieu){
        
    }

    public ThuongHieuDTO getByMaThuongHieu(String MaThuongHieu) {
        for (ThuongHieuDTO ThuongHieu : ThuongHieuList) {
            if (ThuongHieu.getMaThuongHieu().toLowerCase().equals(MaThuongHieu.toLowerCase())) {
                return ThuongHieu;
            }
        }
        return null;
    }
    public String getLatest(){
        return ThuongHieuDAO.getLatest();
    }
    
    public String getMaThuongHieu(String TenThuongHieu){
        for (ThuongHieuDTO ThuongHieu : ThuongHieuList) {
            if(ThuongHieu.getTenThuongHieu().equals(TenThuongHieu))
                return ThuongHieu.getMaThuongHieu();
        }
        return null;
    }
    public String getTenThuongHieuByMaThuongHieu(String MaThuongHieu){
        for (ThuongHieuDTO ThuongHieu : ThuongHieuList) {
            if(ThuongHieu.getMaThuongHieu().equals(MaThuongHieu))
                return ThuongHieu.getTenThuongHieu();
        }
        return null;
    }
    public String[] getTenStringArray(){
        String[] TenThuongHieu = new String[ThuongHieuList.size()+1];
        TenThuongHieu[0] = "Thương hiệu";
        for (int i = 1; i <=ThuongHieuList.size(); i++) {
            TenThuongHieu[i] = ThuongHieuList.get(i-1).getTenThuongHieu();
        }
        return TenThuongHieu;
    }
    public String[] getTenKhongTieuDeStringArray(){
        String[] TenThuongHieu = new String[ThuongHieuList.size()];
        for (int i = 0; i < ThuongHieuList.size(); i++) {
            TenThuongHieu[i] = ThuongHieuList.get(i).getTenThuongHieu();
        }
        return TenThuongHieu;
    }
}