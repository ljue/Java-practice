/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package demo5pac;

import javax.ejb.Stateful;

/**
 *
 * @author antbor
 */
@Stateful
public class DemoBean implements DemoBeanRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    static private String login="user";
    static private String psw="password";
    static private String message="Hello ";
    static private boolean status = false;
    static private int count = 0;
    
    @Override
    public boolean login(String login, String psw) {
        if(login.equals(this.login) && psw.equals(this.psw))
        {
            status=true;
            System.out.println("You are logged in");
            return true;
        }
        else {
            if(status)
                status=false;
            return false;
        }
    }
    
    @Override
    public String getMessage (String login) {
        if(status==false)
                return "Uou are not logged in";
            else {
                if(count==2) {
                    status=false;
                    return message+login+"!";
                }
                else {
                    count++;
                    return message+login+"!";
                }
                    
            }
       
    }
}
