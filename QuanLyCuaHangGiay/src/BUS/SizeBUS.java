/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.SizeDAO;
import DTO.SizeDTO;
import java.util.ArrayList;
import static quanlycuahanggiay.Regexp.MaSize;
import static quanlycuahanggiay.Regexp.TenSize;

/**
 *
 * @author Administrator
 */
public class SizeBUS {
    public ArrayList<SizeDTO> SizeList;
    public SizeDAO SizeDAO;
    public SizeBUS(){
        SizeDAO = new SizeDAO();
        SizeList = new ArrayList<>();
        fetchAll();
    }
    public void fetchAll(){
        SizeList = SizeDAO.fetchAll();
    }
    public boolean Exists(String MaSize){
        for(SizeDTO Size:SizeList)
            if(Size.getMaSize().toLowerCase().equals(MaSize.toLowerCase())) return true;
            return false;
    }
    public String Validation(SizeDTO Size){
        if(!MaSize(Size.getMaSize())) return "Mã size phải có dạng S";
        if(!TenSize(Size.getTenSize())) return "Tên size không được chứa chữ và ký tự đặc biệt";
        return null;
    }
    public boolean add(SizeDTO Size){
         if(Exists(Size.getMaSize())){ GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi","Mã size đã tồn tại.Mã size mới là "+getLatest(), 3);
            return false;
        }
        else {
            String Error = Validation(Size);
            if(Error == null){
            SizeDAO.add(Size);
            SizeList = SizeDAO.fetchAll();
            return true;
            }
            else
            GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", Error, 1);
             return false;
                }
            
            
    }
    public void edit(SizeDTO Size){
        SizeDAO.edit(Size);
        fetchAll();
        GUI.CHITIET.QuanLySizeJPanel.fetchAll();
    }
    public SizeDTO getByMaSize(String MaSize){
    for (SizeDTO Size : SizeList) {
            if (Size.getMaSize().toLowerCase().equals(MaSize.toLowerCase())) {
                return Size;
            }
        }
        return null;
    }
    public String getLatest(){
        return SizeDAO.getLatest();
    }
    public String getMaSize(String TenSize){
        for(SizeDTO Size: SizeList){
            if(Size.getTenSize().equals(TenSize)) return Size.getMaSize();
        }
        return null;
    }
    public String getTenSizeByMaSize(String MaSize){
        for (SizeDTO Size : SizeList) {
            if(Size.getMaSize().equals(MaSize))
                return Size.getTenSize();
        }
        return null;
    }
    public String[] getTenStringArray(){
        String[] TenSize = new String[SizeList.size()+1];
        TenSize[0] = "Size";
        for (int i = 1; i <=SizeList.size(); i++) {
            TenSize[i] = SizeList.get(i-1).getTenSize();
        }
        return TenSize;
    }
    
    public String[] getTenKhongTieuDeStringArray(){
        String[] TenSize = new String[SizeList.size()];
        for (int i = 0; i < SizeList.size(); i++) {
            TenSize[i] = SizeList.get(i).getTenSize();
        }
        return TenSize;
    }
}