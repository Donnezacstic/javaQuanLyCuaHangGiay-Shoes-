/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ChiTietDonHangDTO;
import Database.Database;
import java.util.ArrayList;


/**
 *
 * @author HUUNHAN
 */
public class ChiTietDonHangDAO extends Database {
    public ArrayList<ChiTietDonHangDTO> fetchAll(){
        ArrayList<ChiTietDonHangDTO> ChiTietDonHangList = new ArrayList<>();
        ChiTietDonHangDTO ChiTietDonHang = new ChiTietDonHangDTO();
        select(ChiTietDonHang,null);
        try{
            while(Result.next()){
                ChiTietDonHang = new ChiTietDonHangDTO();
                ChiTietDonHang.setMaDonHang(Result.getString(1));
                ChiTietDonHang.setMaSanPham(Result.getString(2));
                ChiTietDonHang.setSoLuong(Result.getString(3));
                ChiTietDonHang.setDonGia(Result.getString(4));
                ChiTietDonHang.setThanhTien(Result.getString(5));   
                ChiTietDonHangList.add(ChiTietDonHang);
            }
        } catch(Exception ex){
            ex.printStackTrace();
        } finally{
            close();
        }
    return ChiTietDonHangList;
    }
    public void add(ChiTietDonHangDTO ChiTietDonHang) {
        insert(ChiTietDonHang);
    }

    public void edit(ChiTietDonHangDTO ChiTietDonHang) {
        update(ChiTietDonHang);
    }

    public void remove(ChiTietDonHangDTO ChiTietDonHang) {
        delete(ChiTietDonHang);
    }
    public String getLatest() {
        Query = "SELECT COUNT(*) FROM ChiTietDonHang";
        query(Query);
        try {
            Result.next();
            return "CTDH" + (Result.getInt(1) + 1);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close();
        }
        return null;
    }
}
