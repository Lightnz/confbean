/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.ejb;

import java.util.List;
import javax.ejb.Stateful;
import org.bonn.ooka.conference.dao.FakeDB;
import org.bonn.ooka.conference.dtos.Konferenz;
import org.bonn.ooka.conference.dtos.Teilnehmer;

/**
 *
 * @author alex
 */
@Stateful
public class ConferenceRegisterStatefulEJB implements ConferenceRegisterStatefulEJBLocal {

    private Teilnehmer user;

    public Teilnehmer getUser() {
        return user;
    }
    
    
    @Override
    public String registerToConference(Konferenz konferenz) {
        FakeDB.registerParticipantToConference(this.user, konferenz);
        
        
                
        return user.getName() + ", Sie wurden erfolgreich zur Konferenz "+ konferenz.getTitel() + " hinzugefügt. Viel Spaß!";
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    
    
    
    @Override
    public boolean login(int userID) {
        this.user= FakeDB.getTeilnehmerGlobal().get(userID);
        
        
        return false;
    }

    @Override
    public List<Konferenz> getAngemeldeteKonferenzen() {
        return this.user.getAngemeldeteKonferenzen();
    }




}
