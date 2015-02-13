/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.sessionbeans;

import java.io.Serializable;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.bonn.ooka.conference.dtos.Gutachter;
import org.bonn.ooka.conference.dtos.Teilnehmer;
import org.bonn.ooka.conference.dtos.Veranstalter;

/**
 *
 * @author Fabian
 */
@Named("loginData")
@SessionScoped
public class LoginData implements Serializable{

    private Veranstalter veranstalter;
    
    private Teilnehmer teilnehmer;
    
    private Gutachter gutachter;
    
    public Teilnehmer getTeilnehmer(){
        return teilnehmer;
    }
    
    public void setTeilnehmer(Teilnehmer teilnehmer){
        this.teilnehmer=teilnehmer;
    }
    
    public Veranstalter getVeranstalter(){
        return veranstalter;
    }
    
    public void setVeranstalter(Veranstalter veranstalter){
        this.veranstalter=veranstalter;
    }
    
    public Gutachter getGutachter(){
        return gutachter;
    }
    
    public void setGutachter(Gutachter gutachter){
        this.gutachter=gutachter;
    }
    
    public void terminate(){
        this.terminate();
    }

}
