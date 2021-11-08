/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.SizeDTO;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class SizeDAO extends Database.Database{
    public ArrayList<SizeDTO> fetchAll(){
        ArrayList<SizeDTO> sizeList = new ArrayList<>();
        SizeDTO size = new SizeDTO();
        select(size,null);
        try{
        while(Result.next()){
            size = new SizeDTO();
            size.setMaSize(Result.getString(1));
            size.setTenSize(Result.getString(2));

            sizeList.add(size);
        }
        }catch(Exception ex){
            ex.printStackTrace();
        }  
        return sizeList; 
    }
    
    public void add(SizeDTO Size){
        insert(Size);
    }
    public void edit(SizeDTO Size){
        update(Size);
    }
    public void remove(SizeDTO Size){
        delete(Size);
    }
    
    public String getLatest(){
        Query = "Select count(*) from size";
        query(Query);
        try{
        Result.next();
        return "S"+(Result.getInt(1)+1);
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
        close();}
        return null;
    }
    
}
