/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.ejb;

import java.util.List;
import javax.ejb.Local;
import org.bonn.ooka.conference.dtos.Gutachter;
import org.bonn.ooka.conference.dtos.Konferenz;
import org.bonn.ooka.conference.dtos.Publikation;
import org.bonn.ooka.conference.dtos.Teilnehmer;

/**
 *
 * @author alex
 */
@Local
public interface CRUDPaperEJBLocal {
    
    String createPaper(Publikation publikation);
    
    String updatePaper(Publikation publikation);
    
    String deletePaper(Publikation publikation);
    
    String addPublikationToConference(Publikation publikation, Konferenz konferenz);
    
    String addGutachterToPublikation(Gutachter gutachter, Publikation publikation);
    
    List<Publikation> findPublications(String s);
       
    List<Publikation> getAllPublicationsFor(Gutachter g);
    
    List<Publikation> getAllPublicationsFor(Teilnehmer t);
    
    List<Publikation> getAllPublications();
    
}
