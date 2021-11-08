/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.NhaCungCapDTO;
import Database.Database;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class NhaCungCapDAO extends Database{
    public ArrayList<NhaCungCapDTO> fetchAll(){
        ArrayList<NhaCungCapDTO> NhaCungCapList = new ArrayList<>();
        NhaCungCapDTO NhaCungCap = new NhaCungCapDTO();
        select(NhaCungCap,null);
        try{
            while(Result.next()){
                NhaCungCap = new NhaCungCapDTO();
                NhaCungCap.setMaNhaCungCap(Result.getString(1));
                NhaCungCap.setTenNhaCungCap(Result.getString(2));
                NhaCungCap.setSoDienThoai(Result.getString(3));
                NhaCungCap.setDiaChi(Result.getString(4));
                NhaCungCapList.add(NhaCungCap);
            }
        }catch(Exception ex){
        ex.printStackTrace();
                }finally{
        close();
        }
        return NhaCungCapList;
    }
        
    public void add(NhaCungCapDTO NhaCungCap){
        insert(NhaCungCap);
    }
    public void edit(NhaCungCapDTO NhaCungCap){
        update(NhaCungCap);
    }
    
    public void remove(NhaCungCapDTO NhaCungCap){
        delete(NhaCungCap);
    }
    public String getLatest(){
    Query = "SELECT COUNT(*) FROM NHACUNGCAP";
    query(Query);
    try{
    Result.next();
    return "NCC"+(Result.getInt(1)+1);
    }catch(Exception ex){
    ex.printStackTrace();
    }finally{
    close();
    }
    return null;
    }
}

