/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package appclient;

import demo5pac.DemoBeanRemote;
import javax.ejb.EJB;

/**
 *
 * @author antbor
 */
public class Main {
    @EJB
    private static DemoBeanRemote demo;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        boolean b = demo.login("user","password");
            System.out.println(b);
            System.out.println(demo.getMessage("user"));
            System.out.println(demo.getMessage("user"));
            System.out.println(demo.getMessage("user"));
            System.out.println(demo.getMessage("user"));
            
            System.out.println(demo.login("user","password"));
            System.out.println(demo.login("userrr","passworddd"));
            System.out.println(demo.getMessage("user"));
    }
    
}
