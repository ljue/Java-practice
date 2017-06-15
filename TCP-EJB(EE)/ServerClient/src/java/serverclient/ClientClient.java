/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverclient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Людмила
 */
public class ClientClient {
    private static Socket ClientSocket=null;
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;
    
    private String host;
    public ClientClient(String host) {
        this.host = host == null? "localhost" : host;
    }
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        new ClientClient(null).run();
    }
    
    private void run() throws IOException {

        try  {
            ClientSocket = new Socket(host, 8806);
            oos = new ObjectOutputStream(ClientSocket.getOutputStream());
            ois = new ObjectInputStream(ClientSocket.getInputStream());
            System.out.println(login("Lesya","Kazankova"));
            String [] ar = getHistory("Kazankova");
            for (int i=0; i<ar.length; i++) {
                System.out.println(ar[i]);
            }
            System.out.println(logout());
            
            System.out.println(login("Alexandra","Rekshina"));
            String [] ar1 = getHistory(2);
            for (int i=0; i<ar.length; i++) {
                System.out.println(ar1[i]);
            }
            System.out.println(logout());
            
            oos.writeObject("exit");
            oos.flush();
            ClientSocket.close();
            
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClientClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        
    String[] getHistory (String name) throws IOException {
        oos.writeObject("getHistoryName\n"+name);
        oos.flush();
        List<String> list = new ArrayList<String>();
        String res;
        while(true){
            try {
                res = ois.readObject().toString();
                if (!res.equals("\n")) {
                    list.add(res);
                }
                else break;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String [] arr = (String[]) list.toArray(new String[list.size()]);
        return arr;
    }
    
    String[] getHistory (int code) throws IOException {
        oos.writeObject("getHistoryInt\n"+code);
        oos.flush();
        List<String> list = new ArrayList<String>();
        String res;
        while(true){
            try {
                res = ois.readObject().toString();
                if (!res.equals("\n")) {
                    list.add(res);
                }
                else break;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String [] arr = (String[]) list.toArray(new String[list.size()]);
        return arr;
        
    }
    
    boolean login (String user, String password) throws IOException {
        try {
               
        oos.writeObject("login\n"+user+"\n" +password);
        oos.flush();
        String result;
        result = ois.readObject().toString();
        
        if(result.equals("true"))
            return true;
        else return false;
        
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    boolean logout () throws IOException {
        try {
        oos.writeObject("logout");
        oos.flush();
        
        String result;
        result = ois.readObject().toString();
        
        if(result.equals("true"))
            return true;
        else return false;
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }
}
