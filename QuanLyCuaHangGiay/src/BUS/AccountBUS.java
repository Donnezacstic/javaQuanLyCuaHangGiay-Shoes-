/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.AccountDAO;
import DTO.AccountDTO;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class AccountBUS {
    public  ArrayList<AccountDTO> accountList;
    public AccountDAO accountDAO;
    public AccountBUS(){
            accountDAO = new AccountDAO();
            fetchAll();
    }
    public void fetchAll(){
        accountList = accountDAO.fetchAll();
    }
    public static boolean getAuth(AccountDTO account){
       account = AccountDAO.getAuth(account);
       if(account==null) return false;
       quanlycuahanggiay.quanlycuahanggiay.currentAccount = new AccountDTO(account);        
       return true;
    }
    public void add(AccountDTO NhanVien){
        accountDAO.add(NhanVien);
    }
    public void edit(AccountDTO NhanVien){
        accountDAO.edit(NhanVien);
    }
    public void remove(AccountDTO NhanVien){
    }
    public AccountDTO getByMaNhanVien(String MaNhanVien){
        
        for (AccountDTO Account: accountList){
            if(Account.getUsername().toLowerCase().equals(MaNhanVien.toLowerCase())) return Account;
        }
    
    return null;
    }
    
    
    public  boolean checkRole(String MaNhanVien,String Role){
        String NhanVienRole = getByMaNhanVien(MaNhanVien).getRole();
        for(int i=0;i<NhanVienRole.length()-2;i++){
            if(Role.codePointAt(i) == 49)
                if(NhanVienRole.codePointAt(i) != 49)
                    return false;
        }
        
        return true;
    }
    public static String changePassword(String MaNhanVien,String OldPass,String NewPassword){
        if(AccountDAO.getAuth(new AccountDTO(MaNhanVien,OldPass))==null) return "Mật khẩu không khớp.";
        AccountDAO.changePassword(MaNhanVien,NewPassword);
        return "Đổi mật khẩu thành công.";
    }
    public void resetPassword(String MaNhanVien){
        accountDAO.resetPassword(MaNhanVien);
    }
}
