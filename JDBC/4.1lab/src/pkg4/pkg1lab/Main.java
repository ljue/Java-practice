/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4.pkg1lab;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Людмила
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        
        
        try {
            //Class.forName("com.mysql.jdbc.Driver").newInstance();
            //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb"+"user=dbuser&password=dbpassword");
            
            //Class.forName("org.postgresql.Driver");
            //conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dbname","alex", "123456");
            //connection.close();
            
            
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            try ( Connection conn = DriverManager.getConnection("jdbc:derby:myDB;create=true")) {
            
            if (conn == null) {
                System.out.println("Нет соединения с БД!");
                System.exit(0);
            }
            
            
            
            ////////////////////////////////////////////
            
            String createTableUsers = "CREATE TABLE USERS "
                    + "("
                    + "ID INTEGER NOT NULL, "
                    + "LOGIN CHAR(20) NOT NULL, "
                    + "PRIMARY KEY (ID), "
                    + "UNIQUE (LOGIN)"
                    + ")";
            
            String createTableRegistration = "CREATE TABLE REGISTRATION "
                    + "("
                    + "CODE INTEGER NOT NULL, "
                    + "ID INTEGER NOT NULL, "
                    + "ROLE VARCHAR(16) NOT NULL, "
                    + "DATE TIMESTAMP NOT NULL, " 
                    + "PRIMARY KEY (CODE), " 
                    + "FOREIGN KEY(ID) REFERENCES USERS(ID)"
                    + ")";
            
            Statement stmt = conn.createStatement();
            stmt.execute(createTableUsers);
            stmt.execute(createTableRegistration);
            
            /////////////////////////////////////////////////
            
            FileInputStream fstream = new FileInputStream("bdinfo.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            int id=1, code = 1;
            
            ArrayList<String> logins = new ArrayList<String>();
            
            while ((strLine = br.readLine()) != null){
                
                String [] tmp = strLine.split(" +");
                
                if (!logins.contains(tmp[0])) {
                    logins.add(tmp[0]);
                    String usersInsert = "INSERT INTO USERS"
                    + "(ID, LOGIN) " + "VALUES"
                    + "(" + id + ",'" + tmp[0] + "')";
                    stmt.executeUpdate(usersInsert);
                    id++;
                }
                    
                    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
                    java.util.Date date = (java.util.Date) formatter.parse(tmp[2]);
                    formatter.applyPattern("yyyy-MM-dd");
                    String strDate = formatter.format(date);
                    java.sql.Timestamp timeStampDate = Timestamp.valueOf(strDate + " " + tmp[3]);
                    //System.out.println(timeStampDate.toString());

                    String regInsert = "INSERT INTO REGISTRATION"
                        + "(CODE, ID, ROLE, DATE) " + "VALUES"
                        + "(" + code + "," + (logins.indexOf(tmp[0])+1) + ",'" + tmp[1] + "','" 
                        + timeStampDate + "')";  
                    stmt.executeUpdate(regInsert);
                    code++;
                    
                
            }
            
            //////////////////////////////////////////////////////////////////
            
            System.out.println("All base");
            ResultSet rs = stmt.executeQuery("SELECT U.LOGIN, R.ROLE, R.DATE "
                    + "FROM USERS U JOIN REGISTRATION R"
                    + " ON U.ID=R.ID");
            while (rs.next()) {
                System.out.println((rs.getString("LOGIN")+ " " + rs.getString("ROLE") 
                + " " + rs.getString("DATE")).replaceAll(" +", " "));
            }
            
            System.out.println("****************************");
            System.out.println("Laterer than 2006-05-22");
            ResultSet rs1 = stmt.executeQuery("SELECT LOGIN"
                    + " FROM USERS "
                    + "WHERE ID NOT IN "
                    + "(SELECT ID "
                    + " FROM REGISTRATION "
                    + "WHERE DATE < '2006-05-22 00:00:00')");
            while (rs1.next()) {
                System.out.println(rs1.getString("LOGIN"));
            }
            
            System.out.println("****************************");
            System.out.println("Several roles");
            ResultSet rs2 = stmt.executeQuery("SELECT LOGIN "
                    + "FROM USERS "
                    + "WHERE ID IN ("
                        + "SELECT R1.ID "
                        + "FROM REGISTRATION R1 CROSS JOIN REGISTRATION R2 "
                        + "WHERE R1.ROLE<>R2.ROLE AND R1.ID=R2.ID)");
            while (rs2.next()) {
                System.out.println(rs2.getString("LOGIN") );
            }
            
            System.out.println("****************************");
            System.out.println("Several users");
            ResultSet rs3 = stmt.executeQuery("SELECT DISTINCT DATE(R1.DATE) as newdate "
                        + "FROM REGISTRATION R1 CROSS JOIN REGISTRATION R2 "
                        + "WHERE DATE(R1.DATE)=DATE(R2.DATE)"
                        + " AND R1.ID<>R2.ID");
            while (rs3.next()) {
                System.out.println(rs3.getString("newdate") );
            }
            
            stmt.execute("DROP TABLE REGISTRATION");
            stmt.execute("DROP TABLE USERS");
            
            
            stmt.close();
            }
    
            
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);

    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } 
         
    
}
}
