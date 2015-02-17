/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.bonn.ooka.conference.dao.BenutzerDao;
import org.bonn.ooka.conference.dao.JPADao;
import org.bonn.ooka.conference.dtos.Benutzer;

/**
 *
 * @author Fabian
 */
@Stateless
public class LoginEJB implements LoginEJBLocal {

    @Inject
    BenutzerDao dao;
    
    @Override
    public Benutzer login(String username, String password) {
        Benutzer benutzer = dao.findByBenutzername(username);
        
        if(benutzer != null){
            if(password != null & password.equals(benutzer.getPassword())){
                return benutzer;
            }
        }
        return null;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
