/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ThuongHieuDTO;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class ThuongHieuDAO extends Database.Database{
    public ArrayList<ThuongHieuDTO> fetchAll(){
        ArrayList<ThuongHieuDTO> ThuongHieuList = new ArrayList<>();
        ThuongHieuDTO ThuongHieu = new ThuongHieuDTO();
        select(ThuongHieu,null);
        try{
        while(Result.next()){
            ThuongHieu = new ThuongHieuDTO();
            ThuongHieu.setMaThuongHieu(Result.getString(1));
            ThuongHieu.setTenThuongHieu(Result.getString(2));
            ThuongHieuList.add(ThuongHieu);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            close();
        }  
        return ThuongHieuList; 
    }
    
    public void add(ThuongHieuDTO ThuongHieu){
        insert(ThuongHieu);
    }
    public void edit(ThuongHieuDTO ThuongHieu){
        update(ThuongHieu);
    }
    
    public void remove(ThuongHieuDTO ThuongHieu){
        delete(ThuongHieu);
    }
    
    public String getLatest(){
        Query = "Select count(*) from thuonghieu";
        query(Query);
        try{
        Result.next();
        return "TH"+(Result.getInt(1)+1);
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            close();
        }
        return null;
    }
        
    
    
}
