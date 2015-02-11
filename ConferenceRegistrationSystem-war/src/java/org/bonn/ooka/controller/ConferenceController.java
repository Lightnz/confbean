/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.controller;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.bonn.ooka.conference.dtos.Teilnehmer;
import org.bonn.ooka.conference.dtos.Veranstalter;
import org.bonn.ooka.conference.ejb.QueryUsersEJBLocal;
import org.bonn.ooka.sessionbeans.LoginData;

/**
 *
 * @author alex
 */
@Named("conferenceController")
@SessionScoped
public class ConferenceController implements Serializable {

    @EJB
    QueryUsersEJBLocal userService;
    
    @Inject
    LoginData loginData;
    
    List<Veranstalter> veranstalterliste;
    
    List<Teilnehmer> teilnehmerliste;
    
    public List<Veranstalter> getVeranstalterliste(){
        veranstalterliste = userService.getUsers(Veranstalter.class);    
        return veranstalterliste;
    }
    
    public List<Teilnehmer> getTeilnehmerliste(){
        teilnehmerliste = userService.getUsers(Teilnehmer.class);    
        return teilnehmerliste;
    }
    
    /**
     * Creates a new instance of ConferenceController
     */
    public ConferenceController() {
    }
    
    public String startParticipantMask(Teilnehmer teilnehmer){
            loginData.setTeilnehmer(teilnehmer);
            return Pages.PARTICIPENT_INDEX_PAGE;
        }
    
    public String startParticipantMask(){
            return Pages.PARTICIPENT_INDEX_PAGE;
        }
        
    public String startOrganizerMask(Veranstalter veranstalter){
            loginData.setVeranstalter(veranstalter);
            return Pages.ORGANIZER_INDEX_PAGE;
        }
    
    public String startOrganizerMask(){
            return Pages.ORGANIZER_INDEX_PAGE;
        }
    
    public String startMainpage(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        //return "/home.xhtml?faces-redirect=true";
        return Pages.CONFSYS_MAINPAGE;
    }
    
}
