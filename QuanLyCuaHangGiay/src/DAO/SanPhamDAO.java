
package DAO;

import DTO.SanPhamDTO;
import java.util.ArrayList;
import quanlycuahanggiay.quanlycuahanggiay;

public class SanPhamDAO extends Database.Database {
    public ArrayList<SanPhamDTO> fetchAll(){
        ArrayList<SanPhamDTO> SanPhamList = new ArrayList<>();
        SanPhamDTO SanPham = new SanPhamDTO();
        select(SanPham,null);
        try {
            while(Result.next()){
                SanPham = new SanPhamDTO();
                SanPham.setMaSanPham(Result.getString(1));
                SanPham.setTenSanPham(Result.getString(2));
                SanPham.setDonGia(Result.getString(6));
                SanPham.setSoLuong(Result.getString(7));
                SanPham.setMaMau(Result.getString(5));
                SanPham.setMaSize(Result.getString(4));
                SanPham.setMaThuongHieu(Result.getString(3));
                SanPham.setImgSource(Result.getString(8));
                SanPhamList.add(SanPham);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            close();
        }
        return SanPhamList;
    }
    public static void getById(String MaSanPham){
        
    }
    
    public void add(SanPhamDTO SanPham){
        insert(SanPham);
    }
    
    public void edit(SanPhamDTO SanPham){
        update(SanPham);
    }
    
    public void remove(SanPhamDTO SanPham){
        delete(SanPham);
    }
    
    
    public String getLatest(){
        Query = "Select count(*) From SanPham";
        query(Query);
        try {
            Result.next();
            return "SP"+(Result.getInt(1)+1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            close();
        }
        return null;
    }
}
