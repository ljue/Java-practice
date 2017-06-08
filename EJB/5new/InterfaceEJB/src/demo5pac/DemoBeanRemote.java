/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package demo5pac;

import javax.ejb.Remote;

/**
 *
 * @author antbor
 */
@Remote
public interface DemoBeanRemote {
    public boolean login(String login, String psw);
    public String getMessage (String login);
}
