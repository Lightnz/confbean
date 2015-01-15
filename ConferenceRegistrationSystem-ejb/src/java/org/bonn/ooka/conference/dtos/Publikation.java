/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.dtos;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.bonn.ooka.conference.dao.JPADao;

/**
 *
 * @author Fabian
 */
@Entity
@Table(name = "publikation", schema="confsys")
public class Publikation implements Serializable{
	
        @Id
        @GeneratedValue
	private int ID;
        @OneToOne(cascade = CascadeType.ALL)
	private Teilnehmer autor;
	private String titel;
	private boolean visible;
        @OneToOne(cascade = CascadeType.ALL)
        private Gutachter gutachter;
        @OneToOne(cascade = CascadeType.ALL)
        private Gutachten gutachten;
	
        public Publikation(){
            
        }

	public Publikation(Teilnehmer autor, String titel, Gutachten gutachten){
		this.autor=autor;
		this.titel=titel;
                this.gutachten=gutachten;
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
            return gutachter;
        }
        
        public boolean setGutachter(Gutachter gutachter){
            this.gutachter=gutachter;
            return true;
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

