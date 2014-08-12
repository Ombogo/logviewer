/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.voting;

/**
 *
 * @author velma
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author velma
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserDataService2 implements Serializable{
 private static final long serialVersionUID = 1L;
    public  List<Voting2> getVotings2() {
       
        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection con = getConnection();

        String stm = "SELECT msisdn, short_code, created, text  FROM inboxes";
        List<Voting2> records2 = new ArrayList<Voting2>();
        try {
            pst = con.prepareStatement(stm);
            pst.execute();
            rs = pst.getResultSet();
            Voting2 voting2 = new Voting2();
     String valid;
            while (rs.next()) {
                voting2.setMsisdn(rs.getLong("msisdn"));
                voting2.setText(rs.getString("text"));
                valid=rs.getString("text");
              
               if(valid.equalsIgnoreCase("CHURCH")){
               voting2.setValid("YES");
               }else{
                   voting2.setValid("NO");
               }
                voting2.setShort_code(rs.getInt("short_code"));
                voting2.setCreated(rs.getTime("created"));
//                voting2.setValid(rs.getString("valid"));
                records2.add(voting2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return records2;
    }

    public Connection getConnection() {
       Connection con =null;

        String url = "jdbc:mysql://192.168.1.172/roamtech_dev";
        String user = "dev";
        String password = "root123";
        try {

            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your MYSQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
//			return;

        }

        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection completed.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
        }
        return con;
    }
        public static void main(String[] args) {
 
	Properties prop = new Properties();
	InputStream input = null;
 
	try {
 
		input = new FileInputStream("lottery.properties");
 
		// load a properties file
		prop.load(input);
 
		// get the property value and print it out
		System.out.println(prop.getProperty("keyword"));
}catch (IOException ex) {
		ex.printStackTrace();
	} finally {
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        }}}