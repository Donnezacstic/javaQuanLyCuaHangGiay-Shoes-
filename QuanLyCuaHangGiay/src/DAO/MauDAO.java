/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.MauDTO;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class MauDAO extends Database.Database{
    public ArrayList<MauDTO> fetchAll(){
        ArrayList<MauDTO> sizeList = new ArrayList<>();
        MauDTO size = new MauDTO();
        select(size,null);
        try{
        while(Result.next()){
            size = new MauDTO();
            size.setMaMau(Result.getString(1));
            size.setTenMau(Result.getString(2));
            sizeList.add(size);
        }
        }catch(Exception ex){
            ex.printStackTrace();
        }  
        return sizeList; 
    }
    
    public void add(MauDTO Mau){
        insert(Mau);
    }
    public void edit(MauDTO Mau){
        update(Mau);
    }
    public void remove(MauDTO Mau){
        delete(Mau);
    }
    
    public String getLatest(){
        Query = "Select count(*) from mau";
        query(Query);
        try{
        Result.next();
        return "M"+(Result.getInt(1)+1);
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
        close();}
        return null;
    }
}
