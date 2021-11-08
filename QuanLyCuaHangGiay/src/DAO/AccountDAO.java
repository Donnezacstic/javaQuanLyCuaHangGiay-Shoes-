/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.AccountDTO;
import Database.Database;
import static Database.Database.ConnectOpen;
import static Database.Database.query;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class AccountDAO extends Database {

    public ArrayList<AccountDTO> fetchAll() {
        ArrayList<AccountDTO> accountList = new ArrayList<>();
        AccountDTO account = new AccountDTO();
        select(account, null);
        try {
            while (Result.next()) {
                account = new AccountDTO();
                account.setUsername(Result.getString(1));
                account.setPassword(Result.getString(2));
                account.setRole(Result.getString(3));
                account.setPosition(Result.getString(4));
                accountList.add(account);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return accountList;
    }

    public void add(AccountDTO Account) {
        insert(Account);
    }

    public void edit(AccountDTO Account) {
        update(Account);
    }

    public void remove(AccountDTO Account) {
        delete(Account);
    }

    public static void changePassword(String MaNhanVien, String NewPassword) {
        try {
            Query = "Update Account set `password` ='" + NewPassword + "' where username='" + MaNhanVien + "'";
            queryUpdate(Query);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close();
        }
    }

    public void resetPassword(String MaNhanVien) {
        try {
            Query = "Update Account set `password`='123456' where username='" + MaNhanVien + "'";
            queryUpdate(Query);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close();
        }
    }

    public static AccountDTO getAuth(AccountDTO Account) {
        try {
            ConnectOpen();
            Query = "Select * from Account where Username='" + Account.getUsername() + "'";
            query(Query);
            if (Result.next()) {
                if (Account.getPassword().equals(Result.getString(2))) {
                    Account.setPosition(Result.getString(4));
                    Account.setRole(Result.getString(3));
                    return Account;
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close();
        }
        return null;
    }
}
