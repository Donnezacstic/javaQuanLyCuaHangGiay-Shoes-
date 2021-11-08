/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ChuongTrinhKhuyenMaiDTO;
import DTO.DonHangDTO;
import java.util.ArrayList;
import quanlycuahanggiay.quanlycuahanggiay;

/**
 *
 * @author HUUNHAN
 */
public class DonHangDAO extends Database.Database {

    public ArrayList<DonHangDTO> fetchAll() {
        ArrayList<DonHangDTO> DonHangList = new ArrayList<>();
        DonHangDTO DonHang = new DonHangDTO();
        select(DonHang, null);
        try {
            while (Result.next()) {
                DonHang = new DonHangDTO();
                DonHang.setMaDonHang(Result.getString(1));
                DonHang.setMaKhachHang(Result.getString(2));
                DonHang.setMaNhanVien(Result.getString(3));
                DonHang.setNgayXuat(Result.getString(4));
                DonHang.setMaCode(Result.getString(5));
                DonHang.setTamTinh(Result.getString(6));
                DonHang.setGiamGia(Result.getString(7));
                DonHang.setPhiVanChuyen(Result.getString(8));
                DonHang.setTongTien(Result.getString(9));
                DonHang.setTrangThai(Result.getString(10));
                DonHangList.add(DonHang);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close();
        }
        return DonHangList;

    }

    public void add(DonHangDTO DonHang) {
        if(DonHang.getMaCode()==null){
            Query = "INSERT INTO DONHANG(MaDonHang, MaKhachHang, MaNhanVien, NgayXuat, MaCode, TamTinh, GiamGia, PhiVanChuyen, TongTien, TrangThai) VALUES('"+DonHang.getMaDonHang()+"','"+DonHang.getMaKhachHang()+"','"+DonHang.getMaNhanVien()+"','"+DonHang.getNgayXuat()+"',null,'"+DonHang.getTamTinh()+"','"+DonHang.getGiamGia()+"','"+DonHang.getPhiVanChuyen()+"','"+DonHang.getTongTien()+"','"+DonHang.getTrangThai()+"')";
            queryUpdate(Query);
        }
        else
        insert(DonHang);
    }

    public void edit(DonHangDTO DonHang) {
        if(DonHang.getMaCode()==null){
            Query = "UPDATE DONHANG SET MaDonHang='"+DonHang.getMaDonHang()+"',MaKhachHang='"+DonHang.getMaKhachHang()+"',MaNhanVien='"+DonHang.getMaNhanVien()+"',NgayXuat='"+DonHang.getNgayXuat()+"',MaCode=null,TamTinh='"+DonHang.getTamTinh()+"',GiamGia='"+DonHang.getGiamGia()+"',PhiVanChuyen='"+DonHang.getPhiVanChuyen()+"',TongTien='"+DonHang.getTongTien()+"',TrangThai='"+DonHang.getTrangThai()+"' WHERE donhang.madonhang='"+DonHang.getMaDonHang()+"'";
            queryUpdate(Query);
        }
        else
        //insert(DonHang);
        update(DonHang);
    }

    public void remove(DonHangDTO DonHang) {
        delete(DonHang);
    }

    public String getLatest() {
        Query = "SELECT COUNT(*) FROM DonHang";
        query(Query);
        try {
            Result.next();
            return "DH" + (Result.getInt(1) + 1);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close();
        }
        return null;
    }

    public static ArrayList<String[]> getSoLuongVaoNgay(String MaDonHang) {
        Query = "SELECT donhang.ngayxuat,chitietdonhang.soluong"
                + " FROM donhang join chitietdonhang on donhang.madonhang=chitietdonhang.madonhang"
                + " WHERE donhang.madonhang='" + MaDonHang + "'";
        ArrayList<String[]> result = new ArrayList<>();
        query(Query);
        try {
            while (Result.next()) {
                String[] data = new String[2];
                data[0] = Result.getString(1);
                data[1] = Result.getString(2);
                result.add(data);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close();
        };

        return result;
    }
    public static ArrayList<String[]> getSanPhamVaoNgay(String MaDonHang){
        Query = "SELECT donhang.ngayxuat,chitietdonhang.masanpham,chitietdonhang.soluong"
                + " FROM donhang join chitietdonhang on donhang.madonhang=chitietdonhang.madonhang"
                + " WHERE donhang.madonhang='" + MaDonHang + "'";
        ArrayList<String[]> result = new ArrayList<>();
        query(Query);
        try {
            while (Result.next()) {
                String[] data = new String[3];
                data[0] = Result.getString(1);
                data[1] = Result.getString(2);
                data[2] = Result.getString(3);
                result.add(data);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close();
        };

        return result;
    }
    public static ArrayList<String[]> getSanPhamNgayDoanhThu(String MaDonHang){
        Query = "SELECT donhang.ngayxuat,chitietdonhang.masanpham,chitietdonhang.thanhtien"
                + " FROM donhang join chitietdonhang on donhang.madonhang=chitietdonhang.madonhang"
                + " WHERE donhang.madonhang='" + MaDonHang + "'";
        ArrayList<String[]> result = new ArrayList<>();
        query(Query);
        try {
            while (Result.next()) {
                String[] data = new String[3];
                data[0] = Result.getString(1);
                data[1] = Result.getString(2);
                data[2] = Result.getString(3);
                result.add(data);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close();
        };

        return result;
    }
}
