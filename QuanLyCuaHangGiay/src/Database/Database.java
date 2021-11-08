/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mysql.cj.jdbc.Driver;
/**
 *
 * @author Administrator
 */
public class Database {
//<editor-fold defaultstate="collapse" desc="DATABASE SERVER">
    protected static  final String dbHost = "jdbc:mysql://localhost:3306/";
    protected static  final String dbName = "quanlycuahanggiay";
    protected static  final String dbUser = "root";
    protected static  final String dbPass = "";
    protected static  final String dbTimezone = "?serverTimezone=UTC";
//</editor-fold>
    
//<editor-fold defaultstate="collapse" desc="JAVA DATABASE CONNECTIVITY">  
    protected static Connection Connect = null;
    protected static PreparedStatement Statement = null;
    protected static ResultSet Result;
    protected static String Query = null;
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="REFLECTION"> 
    protected static Class Instance = null;
    protected static Field[] Fields = null;
    protected static Method[] Methods = null;
    
//</editor-fold>

//<editor-fold defaultstate="collapse" desc="INSTANCE">
    protected  static String InstanceName = null;
    protected  HashMap<String,String> InstanceKeyValue = null;
    protected  static String InstanceKeys = null;
    protected  static String InstanceValues = null;
    protected  static String InstancePrimaryKey = null;
//</editor-fold> 
    
    public Database(){
        if(Connect == null){
            ConnectOpen();
        }
    }
    public static void ConnectOpen(){
        if(Connect != null) return;
            try {
                    Connect = DriverManager.getConnection(dbHost+dbName+dbTimezone,dbUser,dbPass);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public  void getInstance(Object CurrentInstance){
        Instance = CurrentInstance.getClass();
        InstanceName = Instance.getName().replaceAll("DTO.?", ""); //Get Instance Name
        Fields = Instance.getDeclaredFields(); //Get Instance Fields
        InstanceKeyValue  = new HashMap<>();
        getInstanceKeyValue(CurrentInstance);
        
        try{
        InstancePrimaryKey = Instance.getDeclaredMethod("getPrimaryKey").invoke(CurrentInstance).toString();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        
    }

    public void getInstanceKeyValue(Object CurrentInstance){
        try{
        for(Field InstanceProperty: Fields){
            InstanceProperty.setAccessible(true);
            InstanceKeyValue.put(InstanceProperty.getName(), "'"+InstanceProperty.get(CurrentInstance)+"'");    
        }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void getKeysValues(){
        InstanceKeys = "";
        InstanceValues="";
        for(String key: InstanceKeyValue.keySet()){
            InstanceKeys += key+",";
            InstanceValues += InstanceKeyValue.get(key)+",";
        }
        InstanceKeys = InstanceKeys.replaceAll(",$",""); //    FIELD1,FIELD2,FIELD3
        InstanceValues = InstanceValues.replaceAll(",$",""); //VALUE1,VALUE2,VALUE3
    }
    
//<editor-fold defaultstate="collapsed" desc="JOIN SELECT ORDERBY INSERT UPDATE DELETE"> 
    public ResultSet join(Object CurrentInstance,String tableName,String condition){
        getInstance(CurrentInstance);
        Query = "Select * from "+tableName+" join "+InstanceName+" on "+condition;
        System.out.println(Query);
        return Result;
    }
    public void select(Object CurrentInstance,String Condition){
        getInstance(CurrentInstance); 
        Query = "SELECT * FROM "+InstanceName;
        if(!InstancePrimaryKey.contains("null")) Query += "WHERE "+InstancePrimaryKey;
        if(Condition!=null) orderBy(Condition);
        query(Query);
        System.out.println(Query);
    }
    
    public void orderBy(String Condition){
        Query += " ORDER BY "+Condition; // DEFAULT ASC 
    }
    
    public void insert(Object CurrentInstance){
        getInstance(CurrentInstance);
        getKeysValues();
        Query = "INSERT INTO "+InstanceName+"("+InstanceKeys+") VALUES("+InstanceValues+")";
        queryUpdate(Query);
        System.out.println(Query);
    }
    
    public void update(Object CurrentInstance){
        getInstance(CurrentInstance);
        getKeysValues();
        Query = "UPDATE "+InstanceName+" SET "+InstanceKeyValue.toString().replaceAll("\\{|\\}","")+" WHERE "+ InstancePrimaryKey;
        
        queryUpdate(Query);
    }
    public void delete(Object CurrentInstance){
        getInstance(CurrentInstance);
        getKeysValues();
        Query = "DELETE FROM "+InstanceName+" WHERE " +InstancePrimaryKey;
        System.out.println(Query);
        System.out.println(Query);
    }
//</editor-fold>
        
//<editor-fold defaultstate="collapsed" desc="QUERY QUERYUPDATE"> 
    public  static void query(String sql){
        try{
        Statement = Connect.prepareStatement(sql);
        Result = Statement.executeQuery(sql);

        }catch (Exception ex){
            ex.printStackTrace();
    }
    }
    public  static void queryUpdate(String sql){
        try{
            Statement = Connect.prepareStatement(sql);
            Statement.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally{
            close();
        }
    }
 //</editor-fold>
    public static void close(){
        try{
        Statement.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
