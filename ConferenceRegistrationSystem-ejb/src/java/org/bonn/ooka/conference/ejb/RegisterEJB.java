/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.ejb;

import javax.ejb.Stateless;
import javax.inject.Inject;
import org.bonn.ooka.conference.dao.BenutzerDao;
import org.bonn.ooka.conference.dtos.Benutzer;

/**
 *
 * @author Fabian
 */
@Stateless
public class RegisterEJB implements RegisterEJBLocal {

    @Inject
    BenutzerDao benutzerDao;
    
    @Override
    public boolean registerUser(Benutzer benutzer) {
        return benutzerDao.registerUser(benutzer);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
