/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Fabian
 */
@Entity
@Table(name = "veranstalter", schema="confsys")
public class Veranstalter implements Serializable {
    
    @Id
    @GeneratedValue
    private int ID;
    private String name;
    private int rep;
    @OneToMany(mappedBy = "veranstalter", cascade = CascadeType.ALL)
    private List<Konferenz> konferenzen = new ArrayList<Konferenz>();
    
    public Veranstalter(){
        
    }
    
    public Veranstalter(int ID, String name){
        this.name = name;
        this.ID=ID;
        this.rep = 10;
    }
    
    public String getName(){
        return name;
    }
    
    public int getID(){
        return ID;
    }
    
    public List<Konferenz> getKonferenzen(){
        return konferenzen;
    }
    
    public void addKonferenz(Konferenz konferenz){
        konferenzen.add(konferenz);
    }
    
    public void removeKonferenz(Konferenz konferenz){
        konferenzen.remove(konferenz);
    }
    
    public void incRep(){
        rep++;
    }
    
    public void decRep(){
        rep--;
    }
    
    public int getRep(){
        return rep;
    }
    
}
