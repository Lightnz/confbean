/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.ejb;

import javax.ejb.Stateless;
import org.bonn.ooka.conference.dtos.Konferenz;
import org.bonn.ooka.conference.dtos.Teilnehmer;

/**
 *
 * @author alex
 */
@Stateless
public class ConferenceRegisterEJB implements ConferenceRegisterEJBLocal {

    
    
    @Override
    public String registerToConference(Teilnehmer teilnehmer, Konferenz konferenz) {
        
        teilnehmer.addConference(konferenz);
        
        
        //TODO: dem Teilnehmer eine Konferenz hinzufügen
        
        return teilnehmer.getName() + ", Sie wurden erfolgreich zur Konferenz "+ konferenz.getTitel() + " hinzugefügt. Viel Spaß!";
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    
    
    
}
