/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.NhanVienDTO;
import java.util.ArrayList;
import quanlycuahanggiay.quanlycuahanggiay;

/**
 *
 * @author Administrator
 */
public class NhanVienDAO extends Database.Database {

    public ArrayList<NhanVienDTO> fetchAll() {
        ArrayList<NhanVienDTO> NhanVienList = new ArrayList<>();
        NhanVienDTO NhanVien = new NhanVienDTO();
        select(NhanVien, null);
        try {
            while (Result.next()) {
                NhanVien = new NhanVienDTO();
                NhanVien.setMaNhanVien(Result.getString(1));
                NhanVien.setHoTen(Result.getString(2));
                NhanVien.setGioiTinh(Result.getString(3));
                NhanVien.setNamSinh(Result.getString(4));
                NhanVien.setSoDienThoai(Result.getString(5));
                NhanVien.setDiaChi(Result.getString(6));
                NhanVien.setTrangThai(Result.getString(7));
                NhanVien.setLuong(Result.getString(8));
                NhanVien.setNgayVaoLam(Result.getString(9));
                NhanVien.setCaLam(Result.getString(10));
                NhanVien.setImgSource(Result.getString(11));
                NhanVienList.add(NhanVien);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close();
        }
        return NhanVienList;
    }

    public static void getById(String MaNhanVien) {

    }

    public void add(NhanVienDTO NhanVien) {
        insert(NhanVien);
    }

    public void edit(NhanVienDTO NhanVien) {
        update(NhanVien);
    }

    public void remove(NhanVienDTO NhanVien) {
        delete(NhanVien);
    }

    public static void getAuth(String MaNhanVien) {
        try {
            ConnectOpen();
            Query = "Select * From NhanVien Where MaNhanVien='" + MaNhanVien + "'";
            query(Query);
            if (Result.next()) {
                quanlycuahanggiay.currentNhanVien = new NhanVienDTO();
                quanlycuahanggiay.currentNhanVien.setMaNhanVien(Result.getString(1));
                quanlycuahanggiay.currentNhanVien.setHoTen(Result.getString(2));
                quanlycuahanggiay.currentNhanVien.setGioiTinh(Result.getString(3));
                quanlycuahanggiay.currentNhanVien.setNamSinh(Result.getString(4));
                quanlycuahanggiay.currentNhanVien.setSoDienThoai(Result.getString(5));
                quanlycuahanggiay.currentNhanVien.setDiaChi(Result.getString(6));
                quanlycuahanggiay.currentNhanVien.setTrangThai(Result.getString(7));
                quanlycuahanggiay.currentNhanVien.setLuong(Result.getString(8));
                quanlycuahanggiay.currentNhanVien.setNgayVaoLam(Result.getString(9));
                quanlycuahanggiay.currentNhanVien.setCaLam(Result.getString(10));
                quanlycuahanggiay.currentNhanVien.setImgSource(Result.getString(11));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close();
        }

    }

    public String getLatest() {
        Query = "Select count(*) from  nhanvien";
        query(Query);
        try {
            Result.next();
            return "NV" + (Result.getInt(1) + 1);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close();
        }
        return null;                           
    }

}
