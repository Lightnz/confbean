/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.dtos;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import org.bonn.ooka.conference.dao.FakeDB;

/**
 *
 * @author Fabian
 */
@Entity
public class Publikation {
	
        @Id
        @GeneratedValue
	private int ID;
        @OneToOne(cascade = CascadeType.ALL)
	private Teilnehmer autor;
	private String titel;
	private boolean visible;
        @OneToOne(cascade = CascadeType.ALL)
        private Gutachter gutachter;
	

	public Publikation(Teilnehmer autor, String titel, int ID){
		this.autor=autor;
		this.titel=titel;
		this.ID=ID;
		visible=false;
	}
	
	public String getTitel(){
		return titel;
	}
	
        public void setTitel(String titel){
                this.titel=titel;
        }
        
        public void setAutor(Teilnehmer autor){
            this.autor=autor;
        }
        
	public Teilnehmer getAutor(){
		return autor;
	}
        
        public Gutachter getGutachter(){
            if(gutachter==null)
                return FakeDB.getDefaultGutachter();
            return gutachter;
        }
        
        public void setGutachter(Gutachter gutachter){
            this.gutachter=gutachter;
        }
	
	public int getID(){
		return ID;
	}
        
	
	public void setVisible(){
		visible=true;
	}
	
	public boolean getVisible(){
		return visible;
	}

}

