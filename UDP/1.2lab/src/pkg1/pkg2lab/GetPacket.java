/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1.pkg2lab;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
public class GetPacket {
    
    
        public static void main(String[] args) {
            int Port = 15679;    
            try (DatagramSocket soc = new DatagramSocket(Port)) {
                
                byte[] buffer = new byte[1024];
                DatagramPacket pac=new DatagramPacket (buffer, buffer.length);
                soc.receive(pac);
                ByteArrayInputStream is = new ByteArrayInputStream(buffer);
                ObjectInputStream iis= new ObjectInputStream(is);
                Demo demo= (Demo)iis.readObject();
                System.out.println(demo.toString());
                
            } catch (SocketException ex) {
            Logger.getLogger(GetPacket.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GetPacket.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetPacket.class.getName()).log(Level.SEVERE, null, ex);
        }
                System.out.println("Файл принят");

                
        }
}
