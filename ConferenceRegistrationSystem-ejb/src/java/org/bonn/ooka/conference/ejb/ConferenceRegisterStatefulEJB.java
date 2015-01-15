/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.ejb;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import org.bonn.ooka.conference.dao.JPADao;
import org.bonn.ooka.conference.dtos.Konferenz;
import org.bonn.ooka.conference.dtos.Teilnehmer;

/**
 *
 * @author alex
 */
@Stateful
public class ConferenceRegisterStatefulEJB implements ConferenceRegisterStatefulEJBLocal {

    private Teilnehmer user;
    @EJB
    private JPADao dao;

    public Teilnehmer getUser() {
        return user;
    }
    
    
    @Override
    public String registerToConference(Konferenz konferenz) {
        this.user.addConference(konferenz);
        
        return user.getName() + ", Sie wurden erfolgreich zur Konferenz "+ konferenz.getTitel() + " hinzugefügt. Viel Spaß!";
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    
    
    
    @Override
    public boolean login(int userID) {
        this.user = dao.find(Teilnehmer.class, userID);

        return false;
    }

    @Override
    public List<Konferenz> getAngemeldeteKonferenzen() {
        return this.user.getAngemeldeteKonferenzen();
    }




}
