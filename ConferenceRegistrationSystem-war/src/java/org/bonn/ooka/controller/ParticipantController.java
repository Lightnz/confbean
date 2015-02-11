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
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.bonn.ooka.conference.dao.JPADao;
import org.bonn.ooka.conference.dtos.Gutachter;
import org.bonn.ooka.conference.dtos.Konferenz;
import org.bonn.ooka.conference.dtos.Teilnehmer;
import org.bonn.ooka.conference.ejb.ConferenceRegisterEJBLocal;
import org.bonn.ooka.conference.ejb.ConferenceSearchLocal;
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
    private JPADao dao;
    
    @Inject
    LoginData loginData;
    
    //TODO: User vorher im Login in die Session laden
    private Teilnehmer teilnehmer;
    
    private String registerResult;
    
    private String conferenceNameToSearch;
    
    private List<Konferenz> konferenzliste;
    
    private List<Konferenz> angemeldeteKonferenzen;
    
    public ParticipantController(){
        
    }
    
    @PostConstruct
    public void init(){
        teilnehmer = loginData.getTeilnehmer();
        angemeldeteKonferenzen = teilnehmer.getAngemeldeteKonferenzen();
    }

    public List<Konferenz> getAngemeldeteKonferenzen() {
        return angemeldeteKonferenzen;
    }

    public Teilnehmer getTeilnehmer() {
        return teilnehmer;
    }

    
    public String getRegisterResult() {
        return registerResult;
    }
    
    public String doRegister(Konferenz konferenz){
        
        
        registerResult = registerService.registerToConference(teilnehmer, konferenz);
                
        return Pages.PARTICIPENT_CONFIRM_PAGE;
    }
      
    
    

    public String getConferenceNameToSearch() {
        return conferenceNameToSearch;
    }

    public void setConferenceNameToSearch(String conferenceNameToSearch) {
        this.conferenceNameToSearch = conferenceNameToSearch;
    }
    

    public List<Konferenz> getKonferenzliste() {
        return konferenzliste;
    }

    public void setKonferenzliste(List<Konferenz> konferenzliste) {
        this.konferenzliste = konferenzliste;
    }

    
    
    public String showAllConferences(){
        this.konferenzliste = searchService.getAllConferences();
        return Pages.PARTICIPENT_RESULT_PAGE;
    }
    
}
