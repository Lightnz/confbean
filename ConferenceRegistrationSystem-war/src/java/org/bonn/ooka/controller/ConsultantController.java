/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.bonn.ooka.conference.dtos.Gutachten;
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
 * 
 * @author alex
 */
@Named("consultantController")
@SessionScoped
public class ConsultantController implements Serializable {

    Publikation publikationToBeReviewed;
    
    @EJB
    PublicationSearchLocal publicationService;
    
    @EJB
    CreateReviewEJBLocal reviewService;
    
    @Inject
    LoginData loginData;
    
    String result;
    
    int mark;
    
    String comment;
    
    boolean akzeptiert;
    
    Gutachter gutachter;
    
    List<Publikation> publikationsliste;
    
    public void setPublikationToBeReviewed(Publikation publikationToBeReviewed){
        this.publikationToBeReviewed=publikationToBeReviewed;
    }
    
    public String getResult(){
        return result;
    }
    
    public void setResult(String result){
        this.result=result;
    }
    
    public Publikation getPublikationToBeReviewed(){
        return publikationToBeReviewed;
    }
    
    public List<Publikation> getPublikationsliste(){   
        return publikationsliste;
    }
    
    public void setAkzeptiert(boolean akzeptiert){
        this.akzeptiert=akzeptiert;
    }
    
    public boolean getAkzeptiert(){
        return akzeptiert;
    }
    
    public String getComment(){
        return comment;
    }
    
    public void setComment(String comment){
        this.comment=comment;
    }
    
    public int getMark(){
        return mark;
    }
    
    public void setMark(int mark){
        this.mark=mark;
    }
    
    public Gutachter getGutachter(){
        return gutachter;
    }
    
    public void setGutachter(Gutachter g){
        this.gutachter=g;
    }
    
    public String doReview(Publikation publikation){
        publikationToBeReviewed = publikation;
        return Pages.CONSULTANT_RATE_PAGE;
    }
    
    public String doRate(){
        Gutachten gutachten = publikationToBeReviewed.getGutachten();
        gutachten.setKommentar(comment);
        gutachten.setAkzeptiert(akzeptiert);
        result = reviewService.createReview(gutachten);
        publikationsliste.remove(publikationToBeReviewed);
        return Pages.CONSULTANT_CONFIRM_PAGE;
    }
    
    public String cancelRating(){
        return Pages.CONSULTANT_INDEX_PAGE;
    }
    
    /**
     * Creates a new instance of ConferenceController
     */
    public ConsultantController() {
    }
    
    @PostConstruct
    public void init(){
        gutachter = loginData.getGutachter();
        publikationsliste = publicationService.getAllPublicationsFor(gutachter); 
    }
    
}
