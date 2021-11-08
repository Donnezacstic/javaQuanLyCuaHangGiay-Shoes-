/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Administrator
 */
public class AccountDTO {
    private String Username,Password,Role,Position;

    public AccountDTO() {
    }
    public AccountDTO(String Username,String Password){
        this.Username= Username;
        this.Password= Password;
    }
    public AccountDTO(String Username, String Password, String Role, String Position) {
        this.Username = Username;
        this.Password = Password;
        this.Role = Role;
        this.Position = Position;
    }

    public AccountDTO(AccountDTO Account) {
        this.Username = Account.getUsername();
        this.Password = Account.getPassword();
        this.Role = Account.getRole();
        this.Position = Account.getPosition();    
    }
    
    public String getPrimaryKey(){
        return "Username='"+this.getUsername()+"'";
    }
    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public String getRole() {
        return Role;
    }

    public String getPosition() {
        return Position;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }
    
}
