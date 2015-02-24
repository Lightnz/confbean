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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
        int id;
        String titel;
        @OneToOne(cascade = CascadeType.MERGE)
	private Teilnehmer autor;
        @OneToOne(cascade = CascadeType.ALL)
        private Gutachten gutachten;
        private String text;
        @ManyToOne
        private Konferenz konferenz;
	
        public Publikation(){
            
        }

	public Publikation(Teilnehmer autor, String titel, Gutachten gutachten){
		this.autor=autor;
                this.titel=titel;
                this.gutachten=gutachten;
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
        
        public Konferenz getKonferenz(){
            return konferenz;
        }
        
        public void setKonferenz(Konferenz konferenz){
            this.konferenz=konferenz;
        }
        
        public String getText(){
            return text;
        }
        
        public void setText(String text){
            this.text=text;
        }
        
        public void setAutor(Teilnehmer autor){
            this.autor=autor;
        }
        
	public Teilnehmer getAutor(){
		return autor;
	}
	
	public void setGutachten(Gutachten gutachten){
		this.gutachten=gutachten;
	}
	
	public Gutachten getGutachten(){
		return gutachten;
	}

}

