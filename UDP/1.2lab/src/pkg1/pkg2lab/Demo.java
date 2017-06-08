/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1.pkg2lab;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Людмила
 */
public class Demo implements Serializable{
    public static int last;
    private int id; 
    private String name; 
    private String message;
    private Date date;
    private transient int temp;
    
    Demo(){
        id=++last;
        name="Anonym";
        message="Privet!";
        date= new Date();
    }
    
    Demo(String nname, String nmessage){
        id=++last;
        name=nname;
        message=nmessage;
        date= new Date();
    }
    
    @Override
    public String toString() {
        return "last:"+id+" "+name+" "+ message+" "+ date.toString();
    }
}
