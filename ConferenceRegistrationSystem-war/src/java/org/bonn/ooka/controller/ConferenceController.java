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
import org.bonn.ooka.conference.dtos.Benutzer;
import org.bonn.ooka.conference.dtos.Gutachter;
import org.bonn.ooka.conference.dtos.Konferenz;
import org.bonn.ooka.conference.dtos.Publikation;
import org.bonn.ooka.conference.dtos.Teilnehmer;
import org.bonn.ooka.conference.dtos.Veranstalter;
import org.bonn.ooka.conference.ejb.ConferenceSearchLocal;
import org.bonn.ooka.conference.ejb.CreateReviewEJBLocal;
import org.bonn.ooka.conference.ejb.LoginEJB;
import org.bonn.ooka.conference.ejb.LoginEJBLocal;
import org.bonn.ooka.conference.ejb.PublicationSearchLocal;
import org.bonn.ooka.conference.ejb.QueryUsersEJBLocal;
import org.bonn.ooka.conference.ejb.RegisterEJBLocal;
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
    
    @EJB
    LoginEJBLocal loginService;
    
    @EJB
    RegisterEJBLocal registerService;

    @EJB
    ConferenceSearchLocal conferenceSearchService;
    
    @EJB
    private PublicationSearchLocal publicationSearchService;
    
    @Inject
    LoginData loginData;
    
    List<Veranstalter> veranstalterliste;
    
    List<Teilnehmer> teilnehmerliste;
    
    List<Gutachter> gutachterliste;
    
    List<Publikation> publikationsliste;
    
    Publikation publicationToBeViewed;
    
    String username;
    
    String password;
    
    String rolle;
    
    String[] rollen={"Teilnehmer", "Veranstalter", "Gutachter"};
    
    private String conferenceNameToSearch;
    
    private String suchText;
    
    private int suchTyp;
    
    private List<Konferenz> konferenzSuchErgebnis;
    
    private List<Publikation> publikationsSuchErgebnis;
    
    /**
     * Creates a new instance of ConferenceController
     */
    public ConferenceController() {
    }

    public String getRolle() {
        return rolle;
    }

    public void setRolle(String rolle) {
        this.rolle = rolle;
    }

    public String[] getRollen() {
        return rollen;
    }

    public void setRollen(String[] rollen) {
        this.rollen = rollen;
    }
    
    public String login(){
        Benutzer user = loginService.login(username, password);
        if(user!=null){
            if(user instanceof Teilnehmer){
                return ConferenceController.this.startParticipantMask((Teilnehmer)user);
            }
            if(user instanceof Veranstalter){
                return startOrganizerMask((Veranstalter)user);
            }
            if(user instanceof Gutachter){
                return startConsultantMask((Gutachter)user);
            }
        }
        return Pages.ERROR_PAGE;
    }
    
    public String openRegisterPage(){
        return Pages.REGISTER_PAGE;
    }
    
    public String doRegister(){
        if("Teilnehmer".equals(rolle)){
            Benutzer teilnehmer = new Teilnehmer();
            teilnehmer.setName(username);
            teilnehmer.setPassword(password);
            if(registerService.registerUser(teilnehmer)){
                return Pages.REGISTER_SUCCESS;
            }
        }else if("Veranstalter".equals(rolle)){
            Benutzer veranstalter = new Veranstalter();
            veranstalter.setName(username);
            veranstalter.setPassword(password);
            if(registerService.registerUser(veranstalter)){
                return Pages.REGISTER_SUCCESS;
            }
        }else if("Gutachter".equals(rolle)){
            Benutzer gutachter = new Gutachter();
            gutachter.setName(username);
            gutachter.setPassword(password);
            if(registerService.registerUser(gutachter)){
                return Pages.REGISTER_SUCCESS;
            }
        }
        return Pages.ERROR_PAGE;
    }
    
    public void setUsername(String username){
        this.username=username;
    }
    
    public void setPassword(String password){
        this.password=password;
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getPassword(){
        return username;
    }
    
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

    public String getSuchText() {
        return suchText;
    }

    public void setSuchText(String suchText) {
        this.suchText = suchText;
    }

    public int getSuchTyp() {
        return suchTyp;
    }

    public void setSuchTyp(int suchTyp) {
        this.suchTyp = suchTyp;
    }

    public List<Konferenz> getKonferenzSuchErgebnis() {
        return konferenzSuchErgebnis;
    }

    public void setKonferenzSuchErgebnis(List<Konferenz> konferenzSuchErgebnis) {
        this.konferenzSuchErgebnis = konferenzSuchErgebnis;
    }

    public List<Publikation> getPublikationsSuchErgebnis() {
        return publikationsSuchErgebnis;
    }

    public void setPublikationsSuchErgebnis(List<Publikation> publikationsSuchErgebnis) {
        this.publikationsSuchErgebnis = publikationsSuchErgebnis;
    }
    
    public String getConferenceNameToSearch() {
        return conferenceNameToSearch;
    }

    public void setConferenceNameToSearch(String conferenceNameToSearch) {
        this.conferenceNameToSearch = conferenceNameToSearch;
    }
    
    public String startParticipantMask(){
            return Pages.PARTICIPENT_INDEX_PAGE;
        }
    
    public String startParticipantMask(Teilnehmer teilnehmer){
            loginData.setTeilnehmer(teilnehmer);
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
        loginData.invalidate();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        //return "/home.xhtml?faces-redirect=true";
        return Pages.CONFSYS_MAINPAGE;
    }
    
    public String startLogin(){
        return Pages.CONFSYS_MAINPAGE;
    }
    
    public String startPublicationReader(Publikation publikation){
        publicationToBeViewed=publikation;
        return Pages.PUBLICATION_READER_VIEW;
    }
    
    public String startPublicationMask(){
        return Pages.PUBLICATION_VIEW;
    }
    
    public String doSearch(){
        if(suchTyp==1){
            publikationsSuchErgebnis = publicationSearchService.findPublications(suchText);
            return Pages.PUBLICATION_SEARCH_RESULT;
        } if(suchTyp==2){
            konferenzSuchErgebnis = conferenceSearchService.findConferences(suchText);
            return Pages.CONFERENCE_SEARCH_RESULT;
        }
        return Pages.ERROR_PAGE;
    }
    
    public String showPublication(Publikation publikation){
        publicationToBeViewed=publikation;
        return Pages.PUBLICATION_VIEW;
    }
    
}
