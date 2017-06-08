package lab2.pkg1;


import lab2.pkg1.Server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Людмила
 */
public class Client {
    //public static final int Port = 15678;
    private String host;
    public Client(String host) {
        this.host = host == null? "localhost" : host;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        new Client(null).run();
    }
    
    private void run() {
        try (Socket s= new Socket(host, Server.Port);) {
            
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
               ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
               
               oos.writeObject("date");
               oos.flush();
               System.out.println(">>"+ois.readObject());
               
               oos.writeObject("time");
               oos.flush();
               System.out.println(">>"+ois.readObject());
               
               oos.close();
               ois.close();
               
            } catch (IOException ex) { 
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } 
            
        
        
    }
    
}
