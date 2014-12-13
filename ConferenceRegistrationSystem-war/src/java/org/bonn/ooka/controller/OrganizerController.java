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
import org.bonn.ooka.conference.ejb.CreateConferenceEJB;

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
    private CreateConferenceEJB creationService;
    
    private Veranstalter veranstalter = FakeDB.getVeranstalter(2);
    
    private String creationResult;
    
    private List<Konferenz> erstellteKonferenzen = FakeDB.getKonferenzenOf(veranstalter);
    
    public List<Konferenz> getErstellteKonferenzen(){
        return erstellteKonferenzen;
    }
    
    private String titel;
    
    private String anzahl;

    /**
     * Creates a new instance of OrganizerController
     */
    public OrganizerController() {
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
                
        return Pages.ORGANIZER_CONFIRM_PAGE;
    }
    
    public String showConferenceCreation(){
        return Pages.ORGANIZER_RESULT_PAGE;
    }
    
}
