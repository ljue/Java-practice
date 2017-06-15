/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pacServDB;

import javax.ejb.Remote;

/**
 *
 * @author ljuerm
 */
@Remote
public interface ServerDBRemote {
    public String[] getHistoryEJB (String name);
    public String[] getHistoryEJB (int code);
    public boolean loginEJB (String user, String password);
    public boolean logoutEJB ();
}
