package org.bonn.ooka.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.bonn.ooka.conference.dtos.Gutachter;
import org.bonn.ooka.conference.dtos.Konferenz;
import org.bonn.ooka.conference.dtos.Publikation;
import org.bonn.ooka.conference.dtos.Teilnehmer;
import org.bonn.ooka.conference.dtos.Veranstalter;
import org.bonn.ooka.conference.ejb.CRUDPaperEJBLocal;
import org.bonn.ooka.conference.ejb.ConferenceSearchLocal;
import org.bonn.ooka.conference.ejb.CreateConferenceEJBLocal;
import org.bonn.ooka.conference.ejb.EditConferenceEJBLocal;
import org.bonn.ooka.conference.ejb.QueryUsersEJBLocal;
import org.bonn.ooka.conference.ejb.UserUpdateEJBLocal;
import org.bonn.ooka.util.Utils;

/**
 *
 * @author Fabian
 */
@Named("organizerController")
@SessionScoped
public class OrganizerController implements Serializable {
    
    @EJB
    ConferenceSearchLocal searchService;
    
    @EJB
    CreateConferenceEJBLocal creationService;
    
    @EJB
    EditConferenceEJBLocal editService;
    
    @EJB
    CRUDPaperEJBLocal publicationService;
    
    @EJB
    QueryUsersEJBLocal userService;
    
    @EJB
    UserUpdateEJBLocal userUpdateService; 
    
    @Inject
    LoginData loginData;
    
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
    private Veranstalter veranstalter;
    
    private Publikation publikationToEdit;
    
    private Publikation publicationToBeViewed;
    
    private String creationResult;
    
    private List<Gutachter> gutachterListe;
    
    private List<Konferenz> erstellteKonferenzen;
    
    /**
     * Titel der anzulegenden Konferenz
     */
    private String titel;
    
    /**
     * Maximale Anzahl an Teilnehmer an der anzulegenden Konferenz
     */
    private int anzahl;
    
    /**
     * Datum für anzulegende Konferenz
     */
    private Date datum;
    
    private Konferenz conferenceToCreate;
    
    private Konferenz conferenceToEdit;

    /**
     * Creates a new instance of OrganizerController
     */
    public OrganizerController() {
    }
    
    @PostConstruct
    public void init(){
        veranstalter = loginData.getVeranstalter();
        gutachterListe = userService.getUsers(Gutachter.class);
        erstellteKonferenzen =  veranstalter.getKonferenzen();
    }
    
    public String getTitel(){
        return titel;
    }
    
    public int getAnzahl(){
        return anzahl;
    }
    
    public Date getDatum(){
        return datum;
    }
    
    public void setDatum(Date datum){
        this.datum=datum;
    }

    public Publikation getPublicationToBeViewed() {
        return publicationToBeViewed;
    }

    public void setPublicationToBeViewed(Publikation publicationToBeViewed) {
        this.publicationToBeViewed = publicationToBeViewed;
    }

    public Publikation getPublikationToEdit() {
        return publikationToEdit;
    }

    public void setPublikationToEdit(Publikation publikationToEdit) {
        this.publikationToEdit = publikationToEdit;
    }

    public Konferenz getConferenceToCreate() {
        return conferenceToCreate;
    }

    public void setConferenceToCreate(Konferenz conferenceToCreate) {
        this.conferenceToCreate = conferenceToCreate;
    }
    
    public Konferenz getConferenceToEdit(){
        return conferenceToEdit;
    }
    
    public void setTitel(String titel){
        this.titel=titel;
    }
    
    public void setAnzahl(int anzahl){
        this.anzahl=anzahl;
    }
    
    public void setConferenceToEdit(Konferenz conferenceToEdit){
        this.conferenceToEdit = conferenceToEdit;
    }
    
    public List<Gutachter> getGutachterListe(){
        return gutachterListe;
    }
    
    public List<Konferenz> getErstellteKonferenzen(){
        erstellteKonferenzen = Utils.sortKonferenzen(erstellteKonferenzen);
        return erstellteKonferenzen;
    }
    
    public Veranstalter getVeranstalter(){
        return veranstalter;
    }
    
    public String getCreationResult(){
        return creationResult;
    }
    
    public String doCreate(){
        conferenceToCreate.setDate(datum);
        System.out.println("Datum: "+datum+"\nTitel: "+conferenceToCreate.getTitel()+"\nSlots: "+conferenceToCreate.getSlots()+"\nVeranstalter: "+conferenceToCreate.getVeranstalter().getName());
        veranstalter.addKonferenz(conferenceToCreate);
        userUpdateService.updateUser(veranstalter);
        creationResult = creationService.createConference(conferenceToCreate);
        refreshConferences();
        return Pages.ORGANIZER_CONFIRM_PAGE;
    }
    
    public String doChange(){
        creationResult = editService.editConference(conferenceToEdit);
        refreshConferences();
        return Pages.ORGANIZER_CONFIRM_PAGE;
    }
    
    public String doDelete(Konferenz conferenceToDelete){
        Iterator<Publikation> publikationsIterator = conferenceToDelete.getPublikationen().iterator();
        while(publikationsIterator.hasNext()){
            doDeletePublication(publikationsIterator.next());
        }
        for(Teilnehmer t : conferenceToDelete.getTeilnehmer()){
            t.removeConference(conferenceToDelete);
            userUpdateService.updateUser(t);
        }
        veranstalter.removeKonferenz(conferenceToDelete);
        userUpdateService.updateUser(veranstalter);
        creationResult = editService.deleteConference(conferenceToDelete);
        refreshConferences();
        return Pages.ORGANIZER_CONFIRM_PAGE;
    }
    
    public String showSetConsultant(Publikation publikation){
        publikationToEdit = publikation;
        return Pages.ORGANIZER_SET_CONSULTANT_PAGE;
    }
    
    public String doSetConsultant(Gutachter gutachter){
        creationResult = publicationService.addGutachterToPublikation(gutachter, publikationToEdit);
        refreshConferences();
        return Pages.ORGANIZER_CONFIRM_PAGE;
    }
    
    public String doDeletePublication(Publikation publikation){
        publikation.getKonferenz().removePublikation(publikation);
        creationResult = publicationService.deletePaper(publikation);
        
        //publikation.setAutor(null);
        //publikation.setKonferenz(null);
        refreshConferences();
        return Pages.ORGANIZER_CONFIRM_PAGE;
    }
    
    public String showConferenceCreation(){
        conferenceToCreate = new Konferenz();
        conferenceToCreate.setVeranstalter(veranstalter);
        return Pages.ORGANIZER_RESULT_PAGE;
    }
    
    public String showConferenceEdit(Konferenz konferenz){
        conferenceToEdit = konferenz;
        
        return Pages.ORGANIZER_EDIT_PAGE;
    }
    
    public void refreshConferences(){
        erstellteKonferenzen = veranstalter.getKonferenzen();
    }
    
    public String showPublication(Publikation publikation){
        publicationToBeViewed=publikation;
        return Pages.PUBLICATION_VIEW;
    }
    
}
