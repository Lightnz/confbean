/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.controller;

import javax.inject.Named;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author alex
 */
@Named(value = "conferenceController")
@RequestScoped
public class ConferenceController implements Serializable {

    /**
     * Creates a new instance of ConferenceController
     */
    public ConferenceController() {
              
        
        
    }
    
    public String startParticipantMask(){
            return Pages.PARTICIPENT_INDEX_PAGE;
        }
        
    public String startOrganizerMask(){
            return Pages.ORGANIZER_INDEX_PAGE;
        }
    
    
}
