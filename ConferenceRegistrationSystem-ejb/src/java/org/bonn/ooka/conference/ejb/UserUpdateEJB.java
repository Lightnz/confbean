/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.ejb;

import javax.ejb.Stateless;
import javax.inject.Inject;
import org.bonn.ooka.conference.dao.JPADao;
import org.bonn.ooka.conference.dtos.Benutzer;

/**
 *
 * @author Fabian
 */
@Stateless
public class UserUpdateEJB implements UserUpdateEJBLocal {
    
    @Inject
    JPADao dao;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public String updateUser(Benutzer user){
        if(dao.update(user))
            return user.getName() + ", ihre Konferenz wurde erfolgreich erstellt.";
        else{
            return user.getName() + ", ihre Konferenz darf aufgrund negativer Bewertungen nicht mehr als 200 Teilnehmer groß sein. Die Konferenz wurde nicht erstellt.";
        }
    }
}
