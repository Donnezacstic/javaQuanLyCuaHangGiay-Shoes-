/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ChiTietPhieuNhapDTO;
import Database.Database;
import static Database.Database.ConnectOpen;
import static Database.Database.close;
import static Database.Database.query;
import java.util.ArrayList;
import quanlycuahanggiay.quanlycuahanggiay;

/**
 *
 * @author khanh
 */
public class ChiTietPhieuNhapDAO extends Database{
    public ArrayList<ChiTietPhieuNhapDTO> fetchAll(){
        ArrayList<ChiTietPhieuNhapDTO> ChiTietPhieuNhapList = new ArrayList<>();
        ChiTietPhieuNhapDTO ChiTietPhieuNhap = new ChiTietPhieuNhapDTO();
        select(ChiTietPhieuNhap,null);
        try {
            while(Result.next()){
                ChiTietPhieuNhap = new ChiTietPhieuNhapDTO();
                ChiTietPhieuNhap.setMaPhieuNhap(Result.getString(1));
                ChiTietPhieuNhap.setMaSanPham(Result.getString(2));
                ChiTietPhieuNhap.setSoLuong(Result.getString(3));
                ChiTietPhieuNhap.setDonGia(Result.getString(4));
                ChiTietPhieuNhap.setThanhTien(Result.getString(5));
                ChiTietPhieuNhapList.add(ChiTietPhieuNhap);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            close();
        }
        return ChiTietPhieuNhapList;
    }
    public static void getById(String MaChiTietPhieuNhap){
        
    }
    public void add(ChiTietPhieuNhapDTO ChiTietPhieuNhap){
        insert(ChiTietPhieuNhap);
    }
    
    public void edit(ChiTietPhieuNhapDTO ChiTietPhieuNhap){
        update(ChiTietPhieuNhap);
    }
    
    public void remove(ChiTietPhieuNhapDTO ChiTietPhieuNhap){
        delete(ChiTietPhieuNhap);
    }
}
