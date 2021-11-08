/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.SanPhamKhuyenMaiDTO;
import Database.Database;
import java.util.ArrayList;
/**
 *
 * @author TKH
 */

public class SanPhamKhuyenMaiDAO extends Database{
    public ArrayList<SanPhamKhuyenMaiDTO> fetchAll() {
        ArrayList<SanPhamKhuyenMaiDTO> SanPhamKhuyenMaiList = new ArrayList<>();
        SanPhamKhuyenMaiDTO SanPhamKhuyenMai = new SanPhamKhuyenMaiDTO();
        select(SanPhamKhuyenMai, null);
        try {
            while (Result.next()) {
                SanPhamKhuyenMai = new SanPhamKhuyenMaiDTO();
                SanPhamKhuyenMai.setMaChiTietKhuyenMai(Result.getString(1));
                SanPhamKhuyenMai.setMaSanPham(Result.getString(2));
                SanPhamKhuyenMaiList.add(SanPhamKhuyenMai);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close();
        }
        return SanPhamKhuyenMaiList;
    }
    
    public void add(SanPhamKhuyenMaiDTO SanPhamKhuyenMai) {
        Query = "INSERT INTO SanPhamKhuyenMai(MaSanPham,MaChiTietKhuyenMai) VALUES('"+SanPhamKhuyenMai.getMaSanPham()+"','"+SanPhamKhuyenMai.getMaChiTietKhuyenMai()+"')";
        System.out.println(SanPhamKhuyenMai.getMaSanPham());
        if(SanPhamKhuyenMai.getMaSanPham() == null)
        Query = "INSERT INTO SanPhamKhuyenMai(MaSanPham,MaChiTietKhuyenMai) VALUES(null,'"+SanPhamKhuyenMai.getMaChiTietKhuyenMai()+"')";
        queryUpdate(Query);
    }

    public void edit(SanPhamKhuyenMaiDTO SanPhamKhuyenMai) {
        update(SanPhamKhuyenMai);
    }

    public void remove(SanPhamKhuyenMaiDTO SanPhamKhuyenMai) {
        delete(SanPhamKhuyenMai);
    }

}
