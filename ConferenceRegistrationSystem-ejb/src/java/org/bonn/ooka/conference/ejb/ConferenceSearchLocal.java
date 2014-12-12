/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.ejb;

import java.util.List;
import org.bonn.ooka.conference.dtos.Konferenz;

/**
 *
 * @author alex
 */
public interface ConferenceSearchLocal {
    
       
    List<Konferenz> getAllConferences();
    
}
