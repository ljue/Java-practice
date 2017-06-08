/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1lab;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 *
 * @author Людмила
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        String s = "http://www.avalon.ru/";
        try {
            URL url = new URL(s);
            InputStream inStream = url.openStream();

            try (Scanner scn = new Scanner(inStream).useDelimiter("\\A")) {
                String str = scn.hasNext() ? scn.next() : "";

                
                
                str = str.replaceAll("<head>([\\w\\W]*)</head>", "");
                //System.out.print(str);
                str = str.replaceAll("<script([\\w\\W]*?)</script>", "");
                //System.out.print(str);
                str = str.replaceAll("<[^>]+>", "");
                str = str.replaceAll("[ \t]+", " ");
                str = str.replaceAll("(?m)^[\\n\\t\\s]*$", "");
                System.out.print(str);
                System.out.print(str.length());
                System.exit(0);

            }
           
        } catch (MalformedURLException e) {
            System.out.println(e.toString());
        }

    }

}
