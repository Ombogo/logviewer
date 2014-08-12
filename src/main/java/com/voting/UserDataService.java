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
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDataService implements Serializable{
 private static final long serialVersionUID = 1L;
    public  List<Voting> getVotings() {
       
        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection con = getConnection();

        String stm = "SELECT msisdn, short_code, text, created FROM inboxes";
        List<Voting> records = new ArrayList<Voting>();
        try {
            pst = con.prepareStatement(stm);
            pst.execute();
            rs = pst.getResultSet();
            Voting voting = new Voting();
            String valid;
            while (rs.next()) {
                voting.setMsisdn(rs.getLong("msisdn"));
                voting.setText(rs.getString("text"));
                
//               valid = rs.getString("text");
//               
//               if(valid.equalsIgnoreCase("CHURCH")){
//               voting.setValid("YES");
//               }else{
//                   voting.setValid("NO");
//               }
                
                voting.setShort_code(rs.getInt("short_code"));
                voting.setCreated(rs.getTime("created"));
               
                records.add(voting);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return records;
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
}
