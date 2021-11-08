/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.PhieuNhapDTO;
import DTO.PhieuNhapDTO;
import DTO.PhieuNhapDTO;
import static Database.Database.ConnectOpen;
import static Database.Database.close;
import static Database.Database.query;
import java.util.ArrayList;
import quanlycuahanggiay.quanlycuahanggiay;

/**
 *
 * @author khanh
 */
public class PhieuNhapDAO extends Database.Database{
    
    public ArrayList<PhieuNhapDTO> fetchAll() {
        ArrayList<PhieuNhapDTO> PhieuNhapList = new ArrayList<>();
        PhieuNhapDTO PhieuNhap = new PhieuNhapDTO();
        select(PhieuNhap, null);
        try {
            while (Result.next()) {
                PhieuNhap = new PhieuNhapDTO();
                PhieuNhap.setMaPhieuNhap(Result.getString(1));
                PhieuNhap.setMaNhanVien(Result.getString(2));
                PhieuNhap.setMaNhaCungCap(Result.getString(3));
                PhieuNhap.setNgayNhap(Result.getString(4));
                PhieuNhap.setTongTien(Result.getString(5));
                PhieuNhapList.add(PhieuNhap);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close();
        }
        return PhieuNhapList;
    }

    public static void getById(String MaPhieuNhap) {

    }

    public void add(PhieuNhapDTO PhieuNhap) {
        insert(PhieuNhap);
    }

    public void edit(PhieuNhapDTO PhieuNhap) {
        update(PhieuNhap);
    }

    public void remove(PhieuNhapDTO PhieuNhap) {
        delete(PhieuNhap);
    }
    public String getLatest() {
        Query = "Select count(*) from  phieunhap";
        query(Query);
        try {
            Result.next();
            return "PN" + (Result.getInt(1) + 1);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close();
        }
        return null;
    }
}
