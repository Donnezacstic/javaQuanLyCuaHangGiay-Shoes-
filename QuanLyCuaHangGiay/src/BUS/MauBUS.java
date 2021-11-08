/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.MauDAO;
import DTO.MauDTO;
import java.util.ArrayList;
import static quanlycuahanggiay.Regexp.MaMau;
import static quanlycuahanggiay.Regexp.TenMau;

/**
 *
 * @author Administrator
 */
public class MauBUS {
    public ArrayList<MauDTO> MauList;
    public MauDAO MauDAO;
    public MauBUS(){
        MauDAO = new MauDAO();
        MauList = new ArrayList<>();
        fetchAll();
    }
    public void fetchAll(){
        MauList = MauDAO.fetchAll();
    }
    public boolean Exists(String MaMau){
        for(MauDTO Mau:MauList)
            if(Mau.getMaMau().toLowerCase().equals(MaMau.toLowerCase())) return true;
            return false;
    }
    
    public String Validation(MauDTO Mau){
        if(!MaMau(Mau.getMaMau())) return "Mã màu phải có dạng S";
        if(!TenMau(Mau.getTenMau())) return "Tên màu không được chứa số hoặc ký tự đặc biệt";
        return null;
    }
    public boolean add(MauDTO Mau){
        if(Exists(Mau.getMaMau()))
        { 
            GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi","Mã màu đã tồn tại.Mã màu mới là: "+getLatest(), 3);
            return false;
        }
        else {
            String Error = Validation(Mau);
            if(Error == null){
            MauDAO.add(Mau);
            MauList = MauDAO.fetchAll();
            return true;
            }
            else
            GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", Error, 1);
             return false;
                }
            
            
    }
    public void edit(MauDTO Mau){
        MauDAO.edit(Mau);
        fetchAll();
        GUI.CHITIET.QuanLyMauJPanel.fetchAll();
    }
    public MauDTO getByMaMau(String MaMau){
    for (MauDTO Mau : MauList) {
            if (Mau.getMaMau().toLowerCase().equals(MaMau.toLowerCase())) {
                return Mau;
            }
        }
        return null;
    }
    public String getLatest(){
        return MauDAO.getLatest();
    }
    
    public String getMaMau(String TenMau){
        for (MauDTO Mau : MauList) {
            if(Mau.getTenMau().equals(TenMau))
                return Mau.getMaMau();
        }
        return null;
    }
    public String getTenMauByMaMau(String MaMau){
        for (MauDTO Mau : MauList) {
            if(Mau.getMaMau().equals(MaMau))
                return Mau.getTenMau();
        }
        return null;
    }
    public  String[] getTenStringArray(){
        String[] TenMau = new String[MauList.size()+1];
        TenMau[0] = "Màu";
        for(int i=1;i<=MauList.size();i++){
            TenMau[i] = MauList.get(i-1).getTenMau();
        }
        return TenMau;
    }
    
    public  String[] getTenKhongTieuDeStringArray(){
        String[] TenMau = new String[MauList.size()];
        for(int i=0;i<MauList.size();i++){
            TenMau[i] = MauList.get(i).getTenMau();
        }
        return TenMau;
    }
}