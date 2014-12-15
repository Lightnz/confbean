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
        int bewertung;
        int slots;
	Veranstalter veranstalter;
	List<Teilnehmer> teilnehmerliste = new ArrayList<Teilnehmer>();
	String titel;
	List<Publikation> publikationen = new ArrayList<Publikation>();
	
	public Konferenz(Veranstalter veranstalter, String titel, int ID, int slots){
		this.veranstalter = veranstalter;
		this.titel=titel;
                this.ID = ID;
                this.slots=slots;
	}

        public int getID() {
            return ID;
        }
	
	public Veranstalter getVeranstalter(){
		return veranstalter;
	}
	
	public String getTitel(){
		return titel;
	}
        
        public void setTitel(String titel){
            this.titel=titel;
        }
	
	public List<Teilnehmer> getTeilnehmer(){
		return teilnehmerliste;
	}
        
        public int getTeilnehmerZahl(){
            return teilnehmerliste.size();
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
        
        public void setSlots(int slots){
            this.slots=slots;
        }
        
        public int getSlots(){
            return slots;
        }
        
        public void setBewertung(int bewertung){
            this.bewertung=bewertung;
        }
        
        public int getBewertung(){
            return bewertung;
        }
	
}

