/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.KhachHangDTO;
import java.util.ArrayList;
import quanlycuahanggiay.quanlycuahanggiay;

/**
 *
 * @author ASUS
 */
public class KhachHangDAO extends Database.Database{
    public ArrayList<KhachHangDTO> fetchAll(){
        ArrayList<KhachHangDTO> KhachHangList = new ArrayList<>();
        KhachHangDTO KhachHang = new KhachHangDTO();
        select(KhachHang,null);
        try{
            while(Result.next()){
                KhachHang = new KhachHangDTO();
                KhachHang.setMaKhachHang(Result.getString(1));
                KhachHang.setHoTen(Result.getString(2));
                KhachHang.setEmail(Result.getString(5));
                KhachHang.setSoDienThoai(Result.getString(3));
                KhachHang.setDiaChi(Result.getString(4));
                KhachHangList.add(KhachHang);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            close();
        }
        return KhachHangList;
    }
    
    public void add(KhachHangDTO KhachHang) {
        insert(KhachHang);
    }

    public void edit(KhachHangDTO KhachHang) {
        update(KhachHang);
    }

    public void remove(KhachHangDTO KhachHang) {
        delete(KhachHang);
    }

    public String getLatest(){
        Query = "Select count(*) from khachhang";
        query(Query);
        try {
           Result.next();
           return "KH"+(Result.getInt(1)+1);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally{
            close();
        }
        return null;
    }
}
