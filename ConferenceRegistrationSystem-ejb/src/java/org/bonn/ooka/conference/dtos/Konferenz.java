/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.dtos;


import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        @ManyToMany
            @JoinTable(
            name="konf_guta",
            schema="confsys",
            joinColumns={@JoinColumn(name="KONF_ID", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="GUTA_ID", referencedColumnName="id")})
        List<Gutachter> committee = new ArrayList<Gutachter>();
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

        public Gutachter[] getCommittee() {
            Gutachter[] committee = new Gutachter[this.committee.size()];
            this.committee.toArray(committee);
            return committee;
        }
        
        public void setCommittee(List<Gutachter> committee){
            this.committee=committee;
        }

        public void setCommittee(Gutachter[] committee) {
            this.committee = new ArrayList<Gutachter>();
            for(int i=0; i<committee.length; i++){
                this.committee.add(committee[i]);
            }
        }
        
        public void setDate(Date date){
            this.date=date;
        }
        
        public void setDate(String date){
            SimpleDateFormat sdf = new SimpleDateFormat("ddd/MM/yyyy HH:mm:ss");
            try {
                this.date = sdf.parse(date);
            } catch (ParseException ex) {
                Logger.getLogger(Konferenz.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public Date getDatum(){
            return date;
        }
        
        public String getDate(){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
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
	
	public void setVeranstalter(Veranstalter veranstalter){
		this.veranstalter=veranstalter;
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
        
        public boolean removePublikation(Publikation p){
                publikationen.remove(p);
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

