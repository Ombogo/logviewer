/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.voting;
import javax.faces.bean.ManagedBean;
  

/**
 *
 * @author velma
 */
@ManagedBean(name="Voting",eager=true)
public class Voting {
   private  int short_code;
   private String text;
   private long msisdn;
 private java.sql.Time created;
 private String valid;
           

   // JDBC driver name and database URL
    public  int getShort_code(){
       return short_code;
   }
   public void setShort_code(int short_code){
       this.short_code=short_code;
   }
   public String getText(){
       return text;
   }
   public void setText(String text){
       this.text=text;
       
   }
   public long  getMsisdn(){
    return msisdn;       
   }
   public void setMsisdn(long msisdn){
       this.msisdn=msisdn;
   }
   public java.sql.Time getCreated(){
       return created;
   }
   public void  setCreated(java.sql.Time created){
       this.created=created;
   }


   
}