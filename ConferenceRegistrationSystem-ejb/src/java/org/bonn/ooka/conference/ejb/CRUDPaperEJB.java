/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.ejb;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.bonn.ooka.conference.dao.JPADao;
import org.bonn.ooka.conference.dtos.Gutachter;
import org.bonn.ooka.conference.dtos.Konferenz;
import org.bonn.ooka.conference.dtos.Publikation;
import org.bonn.ooka.conference.dtos.Teilnehmer;

/**
 *
 * @author alex
 */
@Stateless
public class CRUDPaperEJB implements CRUDPaperEJBLocal {

    @Inject
    JPADao dao;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Override
    public String createPaper(Publikation publikation){
        String autor = publikation.getAutor().getName();
        String titel = publikation.getAutor().getName();
        String konferenz = publikation.getKonferenz().getTitel();
        if(dao.create(publikation)){
            return autor+", ihre Publikation '"+titel+"' zur Konferenz '"+konferenz+"' wurde erfolgreich zur Überprüfung eingereicht!";
        }else{
            return autor+", ihre Publikation '"+titel+"' zur Konferenz '"+konferenz+"' konnte nicht erfolgreich zur Überprüfung eingereicht werden. <p/>Bitte versuchen sie es zu einem späteren Zeitpunkt erneut.";
        }
    }
    
    @Override
    public String updatePaper(Publikation publikation){
        String autor = publikation.getAutor().getName();
        String titel = publikation.getAutor().getName();
        String konferenz = publikation.getKonferenz().getTitel();
        if(dao.update(publikation)){
            return autor+", ihre Publikation '"+titel+"' zur Konferenz '"+konferenz+"' wurde erfolgreich geändert!";
        }else{
            return autor+", ihre Publikation '"+titel+"' zur Konferenz '"+konferenz+"' konnte nicht geändert werden. <p/>Bitte versuchen sie es zu einem späteren Zeitpunkt erneut.";
        }
    }
    
    @Override
    public String deletePaper(Publikation publikation){
        String titel = publikation.getTitel();
        String konferenz = publikation.getKonferenz().getTitel();
        if(dao.delete(publikation)){
            return "Die Publikation '"+titel+"' zur Konferenz '"+konferenz+"' wurde erfolgreich gelöscht!";
        }else{
            return "Die Publikation '"+titel+"' zur Konferenz '"+konferenz+"' konnte nicht gelöscht werden. <p/>Bitte versuchen sie es zu einem späteren Zeitpunkt erneut.";
        }
    }
    
    @Override
    public String addPublikationToConference(Publikation publikation, Konferenz konferenz){
        if(konferenz.addPublikation(publikation))
            return konferenz.getVeranstalter().getName() + ", ihre Publikation '" + publikation.getTitel() + "' wurde erfolgreich der Konferenz '"+konferenz.getTitel()+"' hinzugefügt.";
        else
            return konferenz.getVeranstalter().getName() + ", es ist beim Hinzufügen ihrer Publikation '"+ publikation.getTitel() + "' zu einem Fehler gekommen.";
    }
    
    @Override
    public String addGutachterToPublikation(Gutachter gutachter, Publikation publikation){
        publikation.getGutachten().setGutachter(gutachter);
        if(dao.update(publikation))
            return "Der Publikation '" + publikation.getTitel() + "' wurde erfolgreich der Gutachter '"+gutachter.getName()+"' hinzugefügt.";
        else
            return "Es ist beim Hinzufügen eines Gutachters zu ihrer Publikation '"+ publikation.getTitel() + "' zu einem Fehler gekommen.";
    }
    
    @Override
    public List<Publikation> findPublications(String s){
        List<Publikation> publikationen = new ArrayList<Publikation>();
        for(Publikation publikation : dao.findPublikationByName(s)){
            if(publikation.getGutachten().getAkzeptiert())
                publikationen.add(publikation);
        }
        return publikationen;
    }
    
    @Override
    public List<Publikation> getAllPublicationsFor(Gutachter g) {
        List<Publikation> publikationen = new ArrayList<Publikation>();
        for(Publikation publikation : dao.findAll(Publikation.class)){
            if(publikation.getGutachten().getGutachter().getId()==g.getId() && !publikation.getGutachten().getAkzeptiert())
                publikationen.add(publikation);
        }
        return publikationen;
    } 
    
    @Override
    public List<Publikation> getAllPublicationsFor(Teilnehmer t) {
        List<Publikation> publikationen = new ArrayList<Publikation>();
        for(Publikation publikation : dao.findAll(Publikation.class)){
            if(publikation.getAutor().getId()==t.getId() && !publikation.getGutachten().getAkzeptiert())
                publikationen.add(publikation);
        }
        return publikationen;
    } 
    
    @Override
    public List<Publikation> getAllPublications(){
        List<Publikation> publikationen = new ArrayList<>();
        for(Publikation publikation : dao.findAll(Publikation.class)){
            if(publikation.getGutachten().getAkzeptiert())
                publikationen.add(publikation);
        }
        return publikationen;
    }
    
    
}
