/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.dtos;


import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Fabian
 */
@Entity
public class Konferenz implements Serializable{
        
        @Id
        @GeneratedValue
	int ID;
        int bewertung;
        int slots;
        @ManyToOne(cascade = CascadeType.ALL)
	Veranstalter veranstalter;
        @ManyToMany(mappedBy = "angemeldeteKonferenzen", cascade = CascadeType.ALL)
	List<Teilnehmer> teilnehmerliste = new ArrayList<Teilnehmer>();
	String titel;
        @Transient
	List<Publikation> publikationen = new ArrayList<Publikation>();
        @Temporal(TemporalType.DATE)
        Date date;
        
        public Konferenz(){
            
        }
	
	public Konferenz(Veranstalter veranstalter, String titel, int ID, int slots, Date date){
		this.veranstalter = veranstalter;
		this.titel=titel;
                //this.ID = ID;
                this.slots=slots;
                this.date=date;
	}

        public int getID() {
            return ID;
        }
        
        public String getDatum(){
            return date.toString();
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
        
        public List<Publikation> getPublikationen(){
            return publikationen;
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

