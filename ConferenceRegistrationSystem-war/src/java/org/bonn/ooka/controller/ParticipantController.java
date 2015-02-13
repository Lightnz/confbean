/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.bonn.ooka.conference.ejb.ConferenceRegisterEJBLocal;
import org.bonn.ooka.conference.ejb.ConferenceSearchLocal;
import org.bonn.ooka.conference.ejb.CreatePaperEJBLocal;
import org.bonn.ooka.sessionbeans.LoginData;

/**
 *
 * @author alex
 */
@Named("participantController")
@SessionScoped
public class ParticipantController implements Serializable {

    @EJB
    ConferenceSearchLocal searchService;
    
    @EJB
    private ConferenceRegisterEJBLocal registerService;
    
    @EJB
    private CreatePaperEJBLocal paperService;
    
    @EJB
    private JPADao dao;
    
    @Inject
    LoginData loginData;
    
    Date currentTime;
    
    private Teilnehmer teilnehmer;
    
    private Publikation publicationToCreate;
    
    private String registerResult;
    
    private String conferenceNameToSearch;
    
    private List<Konferenz> konferenzliste;
    
    private List<Konferenz> angemeldeteKonferenzen;
    
    private List<Konferenz> vergangeneKonferenzen;
    
    public ParticipantController(){
        
    }
    
    public void setPublicationToCreate(Publikation publikation){
        this.publicationToCreate=publikation;
    }
    
    public Publikation getPublicationToCreate(){
        return publicationToCreate;
    }
    
    @PostConstruct
    public void init(){
        teilnehmer = loginData.getTeilnehmer();
        angemeldeteKonferenzen = teilnehmer.getZukuenftigeKonferenzen();
        vergangeneKonferenzen = teilnehmer.getVergangeneKonferenzen();
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

    
    public String getRegisterResult() {
        return registerResult;
    }
    
    public String doRegister(Konferenz konferenz){
        registerResult = registerService.registerToConference(teilnehmer, konferenz);
        if(konferenz.getDatum().getTime()<currentTime.getTime()){
            vergangeneKonferenzen.add(konferenz);
        }else{
            angemeldeteKonferenzen.add(konferenz);
        }
        return Pages.PARTICIPENT_CONFIRM_PAGE;
    }
      
    public String doUnregister(Konferenz konferenz){
        registerResult = registerService.unregisterFromConference(teilnehmer, konferenz);
        currentTime = new Date();
        if(konferenz.getDatum().getTime()<currentTime.getTime()){
            vergangeneKonferenzen.remove(konferenz);
        }else{
            angemeldeteKonferenzen.remove(konferenz);
        }
        return Pages.PARTICIPENT_CONFIRM_PAGE;
    }
    
    public String doPublicize(){
        publicationToCreate.setAutor(teilnehmer);
        Gutachten gutachten = new Gutachten();
        gutachten.setGutachter(searchService.getDefaultGutachter());
        publicationToCreate.setGutachten(gutachten);
        gutachten.setPublikation(publicationToCreate);
        paperService.createPaper(publicationToCreate);
        return Pages.PARTICIPENT_CONFIRM_PAGE;
    }
    
    public String openPublicationCreation(Konferenz konferenz){
        publicationToCreate = new Publikation();
        publicationToCreate.setKonferenz(konferenz);
        return Pages.PARTICIPENT_PAPER_CREATION_PAGE;
    }
    
    public String getConferenceNameToSearch() {
        return conferenceNameToSearch;
    }

    public void setConferenceNameToSearch(String conferenceNameToSearch) {
        this.conferenceNameToSearch = conferenceNameToSearch;
    }
    

    public List<Konferenz> getKonferenzliste() {
        //TODO Die Konferenzen, zu denen an bereits angemeldet ist, nicht mitanzeigen
        //funktioniert nicht mit konferenzliste.removeAll(getAngemeldeteKonferenzen());
        return konferenzliste;
    }

    public void setKonferenzliste(List<Konferenz> konferenzliste) {
        this.konferenzliste = konferenzliste;
    }
    
    public String showAllConferences(){
        setKonferenzliste(searchService.getAllConferences());
        return Pages.PARTICIPENT_RESULT_PAGE;
    }
    
}
