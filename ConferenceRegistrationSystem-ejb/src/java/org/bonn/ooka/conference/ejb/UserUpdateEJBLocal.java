/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.ejb;

import javax.ejb.Local;
import org.bonn.ooka.conference.dtos.Benutzer;

/**
 *
 * @author Fabian
 */
@Local
public interface UserUpdateEJBLocal {
    
    void updateUser(Benutzer user);
    
}
