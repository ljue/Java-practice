/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1.pkg2lab;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
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
        
        int Port = 15678;
        try {
                Demo demo=new Demo();
            try (DatagramSocket soc = new DatagramSocket()) {
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ObjectOutputStream oos= new ObjectOutputStream(os);
                oos.writeObject(demo);
                byte[] buffer = os.toByteArray();
                DatagramPacket pac=new DatagramPacket (buffer, buffer.length);

                pac.setAddress(InetAddress.getByName("localhost"));
                pac.setPort(15679);
                soc.send(pac);
            }
                System.out.println("Файл отправлен");

                } catch (SocketException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
