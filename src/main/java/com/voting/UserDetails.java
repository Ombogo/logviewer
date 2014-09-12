/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.voting;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.NamingException;
@ManagedBean(name="UserDetails", eager = true)
@SessionScoped
public class UserDetails implements Serializable{

 private static final long serialVersionUID = 1L;

  private String name;
   private String password;
 
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public String getPassword() {
      return password;
   }
   public void setPassword(String password) {
      this.password = password;
   }
 public String login() throws NamingException, NoSuchAlgorithmException, UnsupportedEncodingException{
if(Ldap.validateLogin(name, password)==false){
    //System.out.println("Not authenticated");
return "error?faces-redirect=true";

}
 // System.out.println("Not authenticated");
     return "page1?faces-redirect=true";
 }
   
      
      

      
      
   

}

//   
//  


    

