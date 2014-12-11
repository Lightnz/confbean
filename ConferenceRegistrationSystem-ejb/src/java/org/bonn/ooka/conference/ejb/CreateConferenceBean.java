/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.ejb;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import org.bonn.ooka.conference.dao.ConferenceDAO;
import org.bonn.ooka.conference.dao.FakeDB;
import org.bonn.ooka.conference.dtos.Konferenz;

/**
 *
 * @author Fabian
 */
@Stateless
@LocalBean
public class CreateConferenceBean {
    
    //TODO: Stattdessen Inject benutzen
    ConferenceDAO conferenceDAO;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public void addKonferenz(Konferenz k){
        //TODO: Stattdessen Inject benutzen
        if(conferenceDAO==null)
            setDAO(new FakeDB());
        conferenceDAO.addKonferenz(k);
    }
    
    //TODO: Stattdessen Inject benutzen
    public void setDAO(ConferenceDAO dao){
        conferenceDAO = dao;
    }
    
}
