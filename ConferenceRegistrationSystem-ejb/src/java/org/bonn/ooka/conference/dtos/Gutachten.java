/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.dtos;

import java.io.Serializable;

/**
 *
 * @author Fabian
 */
public class Gutachten implements Serializable {
    
    private Publikation publikation;
    private int ID;
    private String kommentar;
    private boolean akzeptiert=false;
	
	public Gutachten(Publikation publikation, int ID){
		this.publikation=publikation;
		this.ID=ID;
	}
	
	public Publikation getPublikation(){
		return publikation;
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
