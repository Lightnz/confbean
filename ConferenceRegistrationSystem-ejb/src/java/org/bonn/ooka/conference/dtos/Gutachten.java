/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.dtos;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Fabian
 */
@Entity
@Table(name = "gutachten", schema="confsys")
public class Gutachten implements Serializable {
    
    @Id
    @GeneratedValue
    private int ID;
    private String kommentar;
    private boolean akzeptiert=false;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Publikation publikation;
    @OneToOne(cascade = CascadeType.REMOVE)
    private Gutachter gutachter;
    
    public Gutachten(){
        
    }
    
    public Gutachter getGutachter(){
        return gutachter;
    }
    
    public boolean setGutachter(Gutachter gutachter){
        this.gutachter=gutachter;
        return true;
    }
    
    public Publikation getPublikation(){
        return publikation;
    }
    
    public void setPublikation(Publikation publikation){
        this.publikation=publikation;
    }
    
    public void setAkzeptiert(boolean akzeptiert){
        this.akzeptiert=akzeptiert;
    }
    
    public boolean getAkzeptiert(){
        return akzeptiert;
    }
	
    public int getID(){
	return ID;
    }
	
    public void setKommentar(String kommentar){
	if(kommentar==null){
            this.kommentar=kommentar;
	} else{
            kommentar+="\nErgänzender Kommentar:"+kommentar;
	}
    }
	
    public String getKommentar(){
	return kommentar;
    }
	
}
