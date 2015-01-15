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
    
    public Gutachten(){
        
    }
	
    public int getID(){
	return ID;
    }
	
    public void kommentieren(String kommentar){
	if(kommentar==null){
            this.kommentar=kommentar;
	} else{
            kommentar+="\nErgänzender Kommentar:"+kommentar;
	}
    }
	
    public String getKommentar(){
	return kommentar;
    }
	
    public void annehmen(){
	akzeptiert = true;
    }
	
    public void ablehnen(){
	akzeptiert = false;
    }
	
}
