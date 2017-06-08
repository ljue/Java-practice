
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Людмила
 */
public class rmiServer implements DateTime { //extends UnicastRemoteObject 
    
    public rmiServer () throws RemoteException {
        
    }
    
    @Override
    public String getDate () throws RemoteException {
        SimpleDateFormat dateformat = new SimpleDateFormat("dd.MM.yyyy");
        return "" + dateformat.format(new Date());
    }

    @Override
    public String getTime () throws RemoteException {
        SimpleDateFormat dateformat = new SimpleDateFormat("hh:mm");
        return "" + dateformat.format(new Date());
    }
    
    
    	public static void main(String... args) {
            
            try {
                
                Registry registry = LocateRegistry.createRegistry(24256);
                
                DateTime dt = new rmiServer();
                DateTime stub = (DateTime)UnicastRemoteObject.exportObject(dt, 0); 
                
                registry.rebind("DateTime", stub);
                
                while (true) {
                    Thread.sleep(Integer.MAX_VALUE);
		}
                
            } catch (Exception e) {
                System.out.println ("Error occured: " + e.getMessage());
            }
                        
 
	}
    
}
