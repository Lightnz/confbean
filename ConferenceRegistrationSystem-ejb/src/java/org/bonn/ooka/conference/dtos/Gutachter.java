/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.dtos;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Fabian
 */
@Entity
@Table(name = "gutachter", schema="confsys")
public class Gutachter extends Benutzer {
	
    //@OneToOne(cascade = CascadeType.ALL)
    //private Teilnehmer user;
    
    public Gutachter(){
        
    }
    
    //public Gutachter(Teilnehmer user){
    //    this.user=user;
    //}
    
    public void setID(int id){
        this.id=id;
    }
    
    public int getID(){
        return id;
    }
	
    //public Teilnehmer getTeilnehmer(){
    //    return user;
    //}
    
}