/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ChuongTrinhKhuyenMaiDTO;
import Database.Database;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class ChuongTrinhKhuyenMaiDAO extends Database {

    public ArrayList<ChuongTrinhKhuyenMaiDTO> fetchAll() {
        ArrayList<ChuongTrinhKhuyenMaiDTO> ChuongTrinhKhuyenMaiList = new ArrayList<>();
        ChuongTrinhKhuyenMaiDTO ChuongTrinhKhuyenMai = new ChuongTrinhKhuyenMaiDTO();
        select(ChuongTrinhKhuyenMai, null);
        try {
            while (Result.next()) {
                ChuongTrinhKhuyenMai = new ChuongTrinhKhuyenMaiDTO();
                ChuongTrinhKhuyenMai.setMaChuongTrinhKhuyenMai(Result.getString(1));
                ChuongTrinhKhuyenMai.setTenChuongTrinh(Result.getString(2));
                ChuongTrinhKhuyenMai.setNgayTao(Result.getString(3));
                ChuongTrinhKhuyenMaiList.add(ChuongTrinhKhuyenMai);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close();
        }
        return ChuongTrinhKhuyenMaiList;
    }

    public void add(ChuongTrinhKhuyenMaiDTO ChuongTrinhKhuyenMai) {
        insert(ChuongTrinhKhuyenMai);
    }

    public void edit(ChuongTrinhKhuyenMaiDTO ChuongTrinhKhuyenMai) {
        update(ChuongTrinhKhuyenMai);
    }

    public void remove(ChuongTrinhKhuyenMaiDTO ChuongTrinhKhuyenMai) {
        delete(ChuongTrinhKhuyenMai);
    }

    public String getLatest() {
        Query = "SELECT COUNT(*) FROM ChuongTrinhKhuyenMai";
        query(Query);
        try {
            Result.next();
            return "CT" + (Result.getInt(1) + 1);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close();
        }
        return null;
    }

}
