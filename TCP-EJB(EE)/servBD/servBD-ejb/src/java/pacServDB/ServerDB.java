/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pacServDB;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Singleton;


/**
 *
 * @author ljuerm
 */
@Singleton
public class ServerDB implements ServerDBRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    private static Connection conn = null;
    private static Statement stmt = null;

    public ServerDB(){
        if(conn==null) {
            try{
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
                conn = DriverManager.getConnection("jdbc:derby:myDB;create=true");
                stmt = conn.createStatement();
                
                try {
                    ResultSet rs = stmt.executeQuery("SELECT * FROM EMPLOYEES");
                } catch (Exception ex){
                    createTable();
                }
                    
                
            } catch (SQLException ex) {
                Logger.getLogger(ServerDB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServerDB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(ServerDB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ServerDB.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }
    
    @Override
    public  synchronized String[] getHistoryEJB (String name) {
        
            try {
                ResultSet rs = stmt.executeQuery("SELECT * "
                        + "FROM EMPLOYEEHISTORY "
                        + "WHERE CODE IN "
                        + "(SELECT CODE "
                        + "FROM EMPLOYEES "
                        + "WHERE LAST_NAME='"+name+"')");
                
                List<String> list = new ArrayList<String>();
                while (rs.next()) {
                list.add((rs.getString("ID")+ " " + rs.getString("POSITION") 
                + " " + rs.getString("MANAGER")+ " " + rs.getString("HIRE")+ " " 
                        + rs.getString("DISMISS")+ " " + rs.getString("CODE")).replaceAll(" +", " "));
                }
                String[] arr = (String[]) list.toArray(new String[list.size()]);
                return arr;
                
            } catch (SQLException ex) {
                Logger.getLogger(ServerDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return null;
        
    }
    
    @Override
    public  synchronized String[] getHistoryEJB (int code) {
        
                
            try {
                ResultSet rs = stmt.executeQuery("SELECT * "
                        + "FROM EMPLOYEEHISTORY "
                        + "WHERE CODE=" + code);
                
                List<String> list = new ArrayList<String>();
                while (rs.next()) {
                list.add((rs.getString("ID")+ " " + rs.getString("POSITION") 
                + " " + rs.getString("MANAGER")+ " " + rs.getString("HIRE")+ " " 
                        + rs.getString("DISMISS")+ " " + rs.getString("CODE")).replaceAll(" +", " "));
                }
                String[] arr = (String[]) list.toArray(new String[list.size()]);
                return arr;
                
                
            } catch (SQLException ex) {
                Logger.getLogger(ServerDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return null;
        
    }
    
    @Override
    public synchronized boolean loginEJB (String user, String password) {
        
            try {
                ResultSet rs = stmt.executeQuery("SELECT LOGIN "
                        + "FROM EMPLOYEES "
                        + "WHERE LOGIN='" + user 
                        + "' AND PSW='" + password + "'");
                    boolean b=rs.next();
                    return b;
                                
            } catch (SQLException ex) {
                Logger.getLogger(ServerDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return false;
    }
    
    @Override
    public synchronized boolean logoutEJB () {
       
        return true;
    }
   
    private void createTable() {
        
        try {
        String createTableEmp = "CREATE TABLE EMPLOYEES "
                    + "("
                    + "CODE INTEGER, "
                    + "NAME VARCHAR(24) NOT NULL, "
                    + "LAST_NAME VARCHAR(32) NOT NULL, "
                    + "LOGIN VARCHAR(16) NOT NULL, "
                    + "PSW VARCHAR(16), "
                    + "PRIMARY KEY (CODE)"
                    + ")";
            
            String createTableEmpHist = "CREATE TABLE EMPLOYEEHISTORY "
                    + "("
                    + "ID INTEGER, "
                    + "POSITION VARCHAR(24) NOT NULL, "
                    + "MANAGER INTEGER CHECK(MANAGER>0), "
                    + "HIRE DATE NOT NULL, "
                    + "DISMISS DATE, " 
                    + "CODE INTEGER, "
                    + "PRIMARY KEY (ID), " 
                    + "CONSTRAINT CHK_Person CHECK (DISMISS>=HIRE), "
                    + "FOREIGN KEY(CODE) REFERENCES EMPLOYEES(CODE)"
                    + ")";
            
            Statement stmt = conn.createStatement();
            stmt.execute(createTableEmp);
            stmt.execute(createTableEmpHist);
            
            /////////////////////////////////////////////////
            
            
                    String usersInsert = "INSERT INTO EMPLOYEES"
                    + "(CODE, NAME, LAST_NAME, LOGIN, PSW) " + "VALUES"
                    + "(1, 'Lesya', 'Kazankova', 'Lesya', 'Kazankova')";
                    stmt.executeUpdate(usersInsert);
                    
                    usersInsert = "INSERT INTO EMPLOYEEHISTORY"
                    + "(ID, POSITION, MANAGER, HIRE, DISMISS, CODE) " + "VALUES"
                    + "(1, 'Manager', 1, '2002-05-17', '2015-06-18', 1)";
                    stmt.executeUpdate(usersInsert);
                    
                    usersInsert = "INSERT INTO EMPLOYEES"
                    + "(CODE, NAME, LAST_NAME, LOGIN, PSW) " + "VALUES"
                    + "(2, 'Alexandra', 'Rekshina', 'Alexandra', 'Rekshina')";
                    stmt.executeUpdate(usersInsert);
                    
                    usersInsert = "INSERT INTO EMPLOYEEHISTORY"
                    + "(ID, POSITION, MANAGER, HIRE, DISMISS, CODE) " + "VALUES"
                    + "(2, 'QA', 1, '2004-03-14', '2012-03-12', 2)";
                    stmt.executeUpdate(usersInsert);
                    
                    usersInsert = "INSERT INTO EMPLOYEES"
                    + "(CODE, NAME, LAST_NAME, LOGIN, PSW) " + "VALUES"
                    + "(3, 'Stanislav', 'Kravzov', 'Stanislav', 'Kravzov')";
                    stmt.executeUpdate(usersInsert);
                    
                    usersInsert = "INSERT INTO EMPLOYEEHISTORY"
                    + "(ID, POSITION, MANAGER, HIRE, DISMISS, CODE) " + "VALUES"
                    + "(3, 'IT', 1, '2005-05-17', '2010-06-18', 3)";
                    stmt.executeUpdate(usersInsert);
                    
                    usersInsert = "INSERT INTO EMPLOYEES"
                    + "(CODE, NAME, LAST_NAME, LOGIN, PSW) " + "VALUES"
                    + "(4, 'Lev', 'Doljkov', 'Lev', 'Doljkov')";
                    stmt.executeUpdate(usersInsert);
                    
                    usersInsert = "INSERT INTO EMPLOYEEHISTORY"
                    + "(ID, POSITION, MANAGER, HIRE, DISMISS, CODE) " + "VALUES"
                    + "(4, 'IT', 1, '2002-05-17', '2015-06-18', 4)";
                    stmt.executeUpdate(usersInsert); 
        } catch (SQLException ex) {
            Logger.getLogger(ServerDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    protected void finalize() throws Throwable {
        conn.close();
        conn=null;
        super.finalize();
       
        
    }
}
