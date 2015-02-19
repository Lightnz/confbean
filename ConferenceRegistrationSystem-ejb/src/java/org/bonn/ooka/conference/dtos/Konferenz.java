/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.dtos;


import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Fabian
 */
@Entity
@Table(name = "konferenz", schema="confsys")
public class Konferenz implements Serializable{
        
        @Id
        @GeneratedValue
        int id;
        String titel;
        int bewertung;
        int slots;
        @ManyToOne
	Veranstalter veranstalter;
        @ManyToMany(mappedBy = "angemeldeteKonferenzen", cascade = CascadeType.REFRESH)
	List<Teilnehmer> teilnehmerliste = new ArrayList<Teilnehmer>();
        @OneToMany(mappedBy = "konferenz", cascade = CascadeType.REMOVE)
	List<Publikation> publikationen = new ArrayList<Publikation>();
        @Transient
        boolean registerflag;
        @Temporal(TemporalType.DATE)
        Date date;
        
        public Konferenz(){
            
        }
	
	public Konferenz(Veranstalter veranstalter, String titel, int slots, Date date){
		this.veranstalter = veranstalter;
                this.titel=titel;
                this.slots=slots;
                this.date=date;
	}
        
        public String getDate(){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String time = sdf.format(date);
            return time;
        }
        
        public int getId(){
            return id;
        }

        public void setId(int id){
            this.id=id;
        }

        public String getTitel(){
            return titel;
        }

        public void setTitel(String titel){
            this.titel=titel;
        }
        
        public Date getDatum(){
            return date;
        }
	
	public Veranstalter getVeranstalter(){
		return veranstalter;
	}
	
	public List<Teilnehmer> getTeilnehmer(){
		return teilnehmerliste;
	}
        
        public int getTeilnehmerZahl(){
            return teilnehmerliste.size();
        }
	
	public boolean addPublikation(Publikation p){
		publikationen.add(p);
                return true;
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
        
        public void removeTeilnehmer(Teilnehmer t){
            teilnehmerliste.remove(t);
        }
        
        public void setSlots(int slots){
            this.slots=slots;
        }
        
        public int getSlots(){
            return slots;
        }
        
        public String getRemainingSlots(){
            return teilnehmerliste.size()+"/"+slots;
        }
        
        public void setBewertung(int bewertung){
            this.bewertung=bewertung;
        }
        
        public int getBewertung(){
            return bewertung;
        }
	
}

