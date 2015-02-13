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
import org.bonn.ooka.conference.dtos.Gutachter;
import org.bonn.ooka.conference.dtos.Publikation;
import org.bonn.ooka.conference.dtos.Teilnehmer;
import org.bonn.ooka.conference.dtos.Veranstalter;
import org.bonn.ooka.conference.ejb.CreateReviewEJBLocal;
import org.bonn.ooka.conference.ejb.PublicationSearchLocal;
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
    
    @EJB
    PublicationSearchLocal publicationService;
    
    @Inject
    LoginData loginData;
    
    List<Veranstalter> veranstalterliste;
    
    List<Teilnehmer> teilnehmerliste;
    
    List<Gutachter> gutachterliste;
    
    List<Publikation> publikationsliste;
    
    Publikation publicationToBeViewed;
    
    public Publikation getPublicationToBeViewed(){
        return publicationToBeViewed;
    }
    
    public void setPublicationToBeViewed(Publikation publicationToBeViewed){
        this.publicationToBeViewed=publicationToBeViewed;
    }
    
    public List<Veranstalter> getVeranstalterliste(){
        veranstalterliste = userService.getUsers(Veranstalter.class);    
        return veranstalterliste;
    }
    
    public List<Teilnehmer> getTeilnehmerliste(){
        teilnehmerliste = userService.getUsers(Teilnehmer.class);    
        return teilnehmerliste;
    }
    
    public List<Gutachter> getGutachterliste(){
        gutachterliste = userService.getUsers(Gutachter.class);    
        return gutachterliste;
    }
    
    public List<Publikation> getPublikationsliste(){
        publikationsliste = publicationService.getAllPublications();    
        return publikationsliste;
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
    
    public String startConsultantMask(Gutachter gutachter){
        loginData.setGutachter(gutachter);
        return Pages.CONSULTANT_INDEX_PAGE;
    }
    
    public String startConsultantMask(){
        return Pages.CONSULTANT_INDEX_PAGE;
    }
    
    public String startMainpage(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        //return "/home.xhtml?faces-redirect=true";
        return Pages.CONFSYS_MAINPAGE;
    }
    
    public String startPublicationMask(Publikation publikation){
        publicationToBeViewed=publikation;
        return Pages.PUBLICATION_VIEW;
    }
    
}
