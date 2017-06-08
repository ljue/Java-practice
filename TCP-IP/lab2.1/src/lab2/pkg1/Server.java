package lab2.pkg1;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class Server {
    /**
     * @param args the command line arguments
     */
    public static final int Port = 22222;
    public static void main(String[] args) {
        // TODO code application logic here
        new Server().run();
    }
    
    private void run() {
        String outMessage="no date";
        try  {
            ServerSocket ss= new ServerSocket(Port);
            
            Socket s= ss.accept();
            
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            
            for (int i=0; i<2; i++) {
                String str = (String)ois.readObject();
               if (str.equals("date")) { 
                   SimpleDateFormat dateformat = new SimpleDateFormat("dd.MM.yyyy");
                   outMessage = "" + dateformat.format(new Date());
                   System.out.println(outMessage);
               }
               if (str.equals("time")) {
                   SimpleDateFormat dateformat = new SimpleDateFormat("hh:mm");
                   outMessage = "" + dateformat.format(new Date());
                   System.out.println(outMessage);
               }
               oos.writeObject(outMessage);
                oos.flush();
               
            }
            
            ois.close();
            oos.close();
            s.close();
            ss.close();
            
            } catch (ClassNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        }
        
    }
    

