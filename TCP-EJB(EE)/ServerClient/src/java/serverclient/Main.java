/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serverclient;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import pacServDB.ServerDBRemote;

/**
 *
 * @author ljuerm
 */
public class Main {

    //@EJB
    //private static ServerDBRemote serverDB;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        try (ServerSocket ss = new ServerSocket(8806)) {
            while (true) {
                Socket socket = ss.accept();
                MultiConn mcon= new MultiConn(socket);
                mcon.start();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    
    }
    
}

class MultiConn extends Thread {
    private ServerDBRemote serverDB;
    
    private Socket socket;
    
    public MultiConn(Socket socket) {
        this.socket=socket;
    }
    
    @Override
    public void run() {
        try {
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    boolean online=false;
                    serverDB=lookupServerDBRemote();
                    while(true) {
                        String [] arr= ((String)ois.readObject()).split("\n");
                        for(int i=0; i<arr.length; i++)
                            System.out.println(arr[i]);
                        if (arr[0].equals("login")) {
                            boolean b = serverDB.loginEJB(arr[1],arr[2]);
                            online = b;
                            oos.writeObject(b?"true":"false");
                        }
                        else if (arr[0].equals("logout")) {
                            boolean b = serverDB.logoutEJB();
                            online=b;
                            oos.writeObject(b?"true":"false");
                        }
                        else if (arr[0].equals("getHistoryCode")) {
                            if(online){
                                String[] resAr = serverDB.getHistoryEJB(Integer.parseInt(arr[1]));
                                for (int i=0; i<resAr.length; i++) {
                                    oos.writeObject(resAr[i]);
                                }
                                oos.writeObject("\n");
                            }
                        }
                        else if (arr[0].equals("getHistoryName")) {
                            if(online){
                                String[] resAr = serverDB.getHistoryEJB(arr[1]);
                                for (String resAr1 : resAr) {
                                    oos.writeObject(resAr1);
                                }
                                oos.writeObject("\n");
                            }
                        }
                        else if (arr[0].equals("exit")){
                            break;
                        }
                    }
                } 
        catch (IOException ex) {        
            Logger.getLogger(MultiConn.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MultiConn.class.getName()).log(Level.SEVERE, null, ex);
        }        
        finally {
            try { 
                socket.close(); 
            } catch (IOException ex) {
                    Logger.getLogger(MultiConn.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }

    private ServerDBRemote lookupServerDBRemote() {
        try {
            Context c = new InitialContext();
            return (ServerDBRemote) c.lookup("java:comp/env/ServerDB");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
