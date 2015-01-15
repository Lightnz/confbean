/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.controller;

import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import org.bonn.ooka.conference.dao.JPADao;
import org.bonn.ooka.conference.dtos.Veranstalter;
import org.bonn.ooka.conference.ejb.LoginSessionBeanLocal;
import org.bonn.ooka.conference.ejb.QueryUsersEJBLocal;

/**
 *
 * @author alex
 */
@Named(value = "conferenceController")
@SessionScoped
public class ConferenceController implements Serializable {

    @EJB
    QueryUsersEJBLocal userService;
    
    @EJB
    LoginSessionBeanLocal loginSession;
    
    List<Veranstalter> veranstalterliste;
    
    public List<Veranstalter> getVeranstalterliste(){
        veranstalterliste = userService.getUsers(Veranstalter.class);    
        return veranstalterliste;
    }
    
    /**
     * Creates a new instance of ConferenceController
     */
    public ConferenceController() {
    }
    
    public String startParticipantMask(){
            return Pages.PARTICIPENT_INDEX_PAGE;
        }
        
    public String startOrganizerMask(Veranstalter veranstalter){
            loginSession.setVeranstalter(veranstalter);
            return Pages.ORGANIZER_INDEX_PAGE;
        }
    
    
}
