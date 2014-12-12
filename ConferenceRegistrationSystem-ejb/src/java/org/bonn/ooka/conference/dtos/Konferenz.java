/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.dtos;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fabian
 */
public class Konferenz implements Serializable{
    
	int ID;

    public int getID() {
        return ID;
    }
        int slots;
	Veranstalter veranstalter;
	List<Teilnehmer> teilnehmerliste = new ArrayList<Teilnehmer>();
	String titel;
	List<Publikation> publikationen = new ArrayList<Publikation>();
	
	public Konferenz(Veranstalter veranstalter, String titel, int ID, int slots){
		this.veranstalter = veranstalter;
		this.titel=titel;
	}
	
	public Veranstalter getVeranstalter(){
		return veranstalter;
	}
	
	public String getTitel(){
		return titel;
	}
	
	public List<Teilnehmer> getTeilnehmer(){
		return teilnehmerliste;
	}
	
	public void addPublikation(Publikation p){
		publikationen.add(p);
	}
        
        public void addTeilnehmer(Teilnehmer t){
            if(teilnehmerliste.size()<slots){
                teilnehmerliste.add(t);
            } else{
                //TODO: vernünftigen Output..
                System.out.println("Maximale Teilnehmerzahl erreicht!");
            }
        }
        
        public int getSlots(){
            return slots;
        }
	
}

