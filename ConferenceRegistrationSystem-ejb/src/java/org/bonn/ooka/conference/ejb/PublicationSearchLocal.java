/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.ejb;

import java.util.List;
import org.bonn.ooka.conference.dtos.Gutachter;
import org.bonn.ooka.conference.dtos.Konferenz;
import org.bonn.ooka.conference.dtos.Publikation;
import org.bonn.ooka.conference.dtos.Veranstalter;

/**
 *
 * @author alex
 */
public interface PublicationSearchLocal {
    
       
    List<Publikation> getAllPublicationsFor(Gutachter g);
    
    List<Publikation> getAllPublications();
    
}
