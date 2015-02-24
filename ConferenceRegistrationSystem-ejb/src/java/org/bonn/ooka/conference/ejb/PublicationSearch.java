/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.ejb;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.bonn.ooka.conference.dao.JPADao;
import org.bonn.ooka.conference.dtos.Gutachter;
import org.bonn.ooka.conference.dtos.Konferenz;
import org.bonn.ooka.conference.dtos.Publikation;
import org.bonn.ooka.conference.dtos.Teilnehmer;
import org.bonn.ooka.conference.dtos.Veranstalter;

/**
 *
 * @author alex
 */
@Stateless
public class PublicationSearch implements PublicationSearchLocal  {

    @EJB
    JPADao dao;
    
    public PublicationSearch() {
        
    }
    
    @Override
    public List<Publikation> findPublications(String s){
        return dao.findPublikationByName(s);
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
