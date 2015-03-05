/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.bonn.ooka.conference.dao.JPADao;
import org.bonn.ooka.conference.dtos.Konferenz;
import org.bonn.ooka.conference.dtos.Teilnehmer;

/**
 *
 * @author alex
 */
@Stateless
public class ConferenceRegisterEJB implements ConferenceRegisterEJBLocal {

    @EJB
    JPADao dao;
    
    @Override
    public String registerToConference(Teilnehmer teilnehmer, Konferenz konferenz) {
        if(konferenz.getSlots()>konferenz.getTeilnehmerZahl()){
            teilnehmer.addConference(konferenz);
            konferenz.addTeilnehmer(teilnehmer);
            dao.update(teilnehmer);
            dao.update(konferenz);
            return teilnehmer.getName() + ", Sie wurden erfolgreich zur Konferenz "+ konferenz.getTitel() + " hinzugefügt. Viel Spaß!";
        }else{
            return teilnehmer.getName() + ", die von Ihnen gewählte Konferenz "+ konferenz.getTitel() + " ist leider bereits voll.";
        }
    }
    
    @Override
    public String unregisterFromConference(Teilnehmer teilnehmer, Konferenz konferenz){
        if(teilnehmer.removeConference(konferenz)){
            teilnehmer.removeConference(konferenz);
            konferenz.removeTeilnehmer(teilnehmer);
            dao.update(teilnehmer);
            dao.update(konferenz);
            return teilnehmer.getName() + ", Sie wurden erfolgreich von Konferenz "+ konferenz.getTitel() + " abgemeldet.";
        }
        else{
            return teilnehmer.getName() + ", leider ist beim Abmelden von der Konferenz "+ konferenz.getTitel() + " ein Fehler aufgetreten.";
        }
        
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    
    
    
}
