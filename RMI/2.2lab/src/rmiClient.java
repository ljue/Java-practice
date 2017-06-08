
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
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
public class rmiClient {
    
    public static void main(String... args) throws RemoteException, IOException {
    
        try {
            Registry registry = LocateRegistry.getRegistry(null, 24256);
            DateTime stub = (DateTime) registry.lookup("DateTime");
            String response = stub.getDate();
            System.out.println(response);
            System.out.println(stub.getTime());
            System.out.println("Pass 't' to refresh the time. \nPass '0' to exit.");
            
            while (true) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String str = br.readLine();
                if ("0".equals(str.toLowerCase())) {                    
                    break;
                } else if ("t".equals(str.toLowerCase())) {
                    System.out.println(stub.getTime());
                }
            }
            
            System.exit(0);
            
            
        } catch (NotBoundException ex) {
            System.out.println ("Error occured: " + ex.getMessage());
        } catch (AccessException ex) {
            System.out.println ("Error occured: " + ex.getMessage());
        }
    }
}
