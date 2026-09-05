/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BookStoreApp;

/**
 *
 * @author i2wahid
 */

public abstract class User {
    //instance variables:
    protected String username;
    protected String password; 
    
    public User (String username, String password){
        this.username = username;
        this.password = password;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password; 
    }
    public boolean validateLogin(String u, String p){
        if (username.equals(u) && password.equals(p)) {
            return true;
        }
        return false;
    }
}
