/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author alex
 */
@Named(value = "conferenceController")
@SessionScoped
public class ConferenceController implements Serializable {
    private List liste;
    

    /**
     * Creates a new instance of ConferenceController
     */
    public ConferenceController() {
    }
    
   //Name der zu registrierenden Konferenz
   private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String doRegister(){
        liste = null; //TODO gefüllt über einen EJB Zugriff (neue Seite mit neuer Konferenzliste)
        return Pages.RESULT_PAGE;
    }
    
}
