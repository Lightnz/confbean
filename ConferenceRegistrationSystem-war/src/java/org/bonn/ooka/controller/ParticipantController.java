/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.bonn.ooka.conference.dao.JPADao;
import org.bonn.ooka.conference.dtos.Gutachten;
import org.bonn.ooka.conference.dtos.Gutachter;
import org.bonn.ooka.conference.dtos.Konferenz;
import org.bonn.ooka.conference.dtos.Publikation;
import org.bonn.ooka.conference.dtos.Teilnehmer;
import org.bonn.ooka.conference.ejb.CRUDConferenceEJBLocal;
import org.bonn.ooka.conference.ejb.CRUDPaperEJBLocal;
import org.bonn.ooka.conference.ejb.ConferenceRegisterEJBLocal;
import org.bonn.ooka.conference.ejb.ConferenceSearchLocal;
import org.bonn.ooka.util.Utils;

/**
 *
 * @author alex
 */
@Named("participantController")
@SessionScoped
public class ParticipantController implements Serializable {

    @EJB
    ConferenceSearchLocal conferenceSearchService;
    
    @EJB
    private CRUDPaperEJBLocal publicationService;
    
    @EJB
    private CRUDConferenceEJBLocal conferenceService;
    
    @EJB
    private ConferenceRegisterEJBLocal registerService;
    
    @EJB
    private JPADao dao;
    
    @Inject
    LoginData loginData;
    
    Date currentTime;
    
    private Teilnehmer teilnehmer;
    
    private Publikation publicationToCreate;
    
    private Publikation publicationToBeViewed;
    
    private String result;
    
    private List<Konferenz> konferenzliste;
    
    private List<Konferenz> angemeldeteKonferenzen;
    
    private List<Konferenz> vergangeneKonferenzen;
    
    private List<Publikation> publikationsListe;
    
    public ParticipantController(){
        
    }
    
    public void setPublicationToCreate(Publikation publikation){
        this.publicationToCreate=publikation;
    }
    
    public Publikation getPublicationToCreate(){
        return publicationToCreate;
    }
    public Publikation getPublicationToBeViewed() {
        return publicationToBeViewed;
    }

    public void setPublicationToBeViewed(Publikation publicationToBeViewed) {
        this.publicationToBeViewed = publicationToBeViewed;
    }

    public List<Publikation> getPublikationsListe() {
        return publikationsListe;
    }

    public void setPublikationsListe(List<Publikation> publikationsListe) {
        this.publikationsListe = Utils.sortPublikationen(publikationsListe);
    }
    
    @PostConstruct
    public void init(){
        teilnehmer = loginData.getTeilnehmer();
        angemeldeteKonferenzen = teilnehmer.getZukuenftigeKonferenzen();
        vergangeneKonferenzen = teilnehmer.getVergangeneKonferenzen();
        setPublikationsListe(publicationService.getAllPublicationsFor(teilnehmer));
        currentTime = new Date();
    }

    public Date getCurrentTime(){
        return currentTime;
    }
    
    public List<Konferenz> getAngemeldeteKonferenzen() {
        return angemeldeteKonferenzen;
    }
    
    public List<Konferenz> getVergangeneKonferenzen(){
        return vergangeneKonferenzen;
    }

    public Teilnehmer getTeilnehmer() {
        return teilnehmer;
    }

    
    public String getResult() {
        return result;
    }
    
    public String doRegister(Konferenz konferenz){
        result = registerService.registerToConference(teilnehmer, konferenz);
        currentTime = new Date();
        if(konferenz.getTeilnehmer().contains(teilnehmer)){
            if(konferenz.getDatum().getTime()<currentTime.getTime()){
                vergangeneKonferenzen.add(konferenz);
                vergangeneKonferenzen = Utils.sortKonferenzen(vergangeneKonferenzen);
            }
            else{
                angemeldeteKonferenzen.add(konferenz); 
                angemeldeteKonferenzen = Utils.sortKonferenzen(angemeldeteKonferenzen);
            }
        }
        return Pages.PARTICIPENT_CONFIRM_PAGE;
    }
      
    public String doUnregister(Konferenz konferenz){
        result = registerService.unregisterFromConference(teilnehmer, konferenz);
        currentTime = new Date();
        if(konferenz.getDatum().getTime()<currentTime.getTime()){
            vergangeneKonferenzen.remove(konferenz);
            vergangeneKonferenzen = Utils.sortKonferenzen(vergangeneKonferenzen);
        }
        else{
            angemeldeteKonferenzen.remove(konferenz);
            angemeldeteKonferenzen = Utils.sortKonferenzen(angemeldeteKonferenzen);
        }
        return Pages.PARTICIPENT_CONFIRM_PAGE;
    }
    
    public String doPublicize(){
        List<Publikation> publikationen = getPublikationsListe();
        publicationToCreate.setAutor(teilnehmer);
        Gutachten gutachten = new Gutachten();
        Gutachter[] kommittee = publicationToCreate.getKonferenz().getCommittee();
        if(kommittee.length>0){
            int randomGutachter = (int)(kommittee.length*Math.random());
            gutachten.setGutachter(kommittee[randomGutachter]);
        }
        publicationToCreate.setGutachten(gutachten);
        gutachten.setPublikation(publicationToCreate);
        result = publicationService.createPaper(publicationToCreate);
        publicationToCreate.getKonferenz().addPublikation(publicationToCreate);
        publikationen.add(publicationToCreate);
        setPublikationsListe(publikationen);
        return Pages.PARTICIPENT_CONFIRM_PAGE;
    }
    
    public String doUpdatePaper(){
        result = publicationService.updatePaper(publicationToCreate);
        return Pages.PARTICIPENT_CONFIRM_PAGE;
    }
    
    public String doDelete(Publikation publikation){
        publikation.setAutor(null);
        publicationService.deletePaper(publikation);
        publikationsListe.remove(publikation);
        return Pages.PARTICIPENT_CONFIRM_PAGE;
    }
    
    public String openPublicationCreation(Konferenz konferenz){
        publicationToCreate = new Publikation();
        publicationToCreate.setKonferenz(konferenz);
        return Pages.PARTICIPENT_PAPER_CREATION_PAGE;
    }
    
    public String openPublicationEdit(Publikation publikation){
        publicationToCreate = publikation;
        return Pages.PARTICIPENT_PAPER_EDIT_PAGE;
    }
    

    public List<Konferenz> getKonferenzliste() {
        return konferenzliste;
    }

    public void setKonferenzliste(List<Konferenz> konferenzliste) {
        this.konferenzliste = Utils.sortKonferenzen(konferenzliste);
    }
    
    public String showAllConferences(){
        List<Konferenz> konferenzen = conferenceSearchService.getAvailableConferences(teilnehmer);
        setKonferenzliste(konferenzen);
        return Pages.PARTICIPENT_RESULT_PAGE;
    }
    
}
