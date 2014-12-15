package org.bonn.ooka.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import org.bonn.ooka.conference.dao.FakeDB;
import org.bonn.ooka.conference.dtos.Konferenz;
import org.bonn.ooka.conference.dtos.Veranstalter;
import org.bonn.ooka.conference.ejb.ConferenceSearchLocal;
import org.bonn.ooka.conference.ejb.CreateConferenceEJBLocal;
import org.bonn.ooka.conference.ejb.EditConferenceEJBLocal;

/**
 *
 * @author Fabian
 */
@Named(value = "organizerController")
@SessionScoped
public class OrganizerController implements Serializable {
    
    @EJB
    ConferenceSearchLocal searchService;
    
    @EJB
    CreateConferenceEJBLocal creationService;
    
    @EJB
    EditConferenceEJBLocal editService;
    
    private Veranstalter veranstalter = FakeDB.getVeranstalter(2);
    
    private String creationResult;
    
    private List<Konferenz> erstellteKonferenzen = FakeDB.getKonferenzenOf(veranstalter);
    
    /**
     * Titel der anzulegenden Konferenz
     */
    private String titel;
    
    /**
     * Maximale Anzahl an Teilnehmer an der anzulegenden Konferenz
     */
    private String anzahl;
    
    private Konferenz conferenceToEdit;

    /**
     * Creates a new instance of OrganizerController
     */
    public OrganizerController() {
    }
    
    public String getTitel(){
        return titel;
    }
    
    public String getAnzahl(){
        return anzahl;
    }
    
    public Konferenz getConferenceToEdit(){
        return conferenceToEdit;
    }
    
    public void setTitel(String titel){
        this.titel=titel;
    }
    
    public void setAnzahl(String anzahl){
        this.anzahl=anzahl;
    }
    
    public void setConferenceToEdit(Konferenz conferenceToEdit){
        this.conferenceToEdit = conferenceToEdit;
    }
    
    public List<Konferenz> getErstellteKonferenzen(){
        return erstellteKonferenzen;
    }
    
    public Veranstalter getVeranstalter(){
        return veranstalter;
    }
    
    public String getCreationResult(){
        return creationResult;
    }
    
    public String doCreate(){
        Konferenz konferenz = new Konferenz(veranstalter, titel, FakeDB.getNextKonferenzID(), Integer.parseInt(anzahl));
        creationResult = creationService.createConference(konferenz);
        refreshConferences();
        return Pages.ORGANIZER_CONFIRM_PAGE;
    }
    
    public String doChange(){
        creationResult = editService.editConference(conferenceToEdit);
        refreshConferences();
        return Pages.ORGANIZER_CONFIRM_PAGE;
    }
    
    public String doDelete(Konferenz conferenceToDelete){
        creationResult = editService.deleteConference(conferenceToDelete);
        refreshConferences();
        return Pages.ORGANIZER_CONFIRM_PAGE;
    }
    
    public String showConferenceCreation(){
        anzahl = "";
        titel = "";
        return Pages.ORGANIZER_RESULT_PAGE;
    }
    
    public String showConferenceEdit(Konferenz konferenz){
        conferenceToEdit = konferenz;
        
        return Pages.ORGANIZER_EDIT_PAGE;
    }
    
    public void refreshConferences(){
        erstellteKonferenzen = FakeDB.getKonferenzenOf(veranstalter);
    }
    
}
