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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Fabian
 */
@Entity
@Table(name = "veranstalter", schema="confsys")
public class Veranstalter extends Benutzer {
    
    private int rep;
    @OneToMany(mappedBy = "veranstalter", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Konferenz> konferenzen = new ArrayList<Konferenz>();
    /**/
    
    public Veranstalter(){
        
    }
    
    public List<Konferenz> getKonferenzen(){
        return konferenzen;
    }
    
    public void setKonferenzen(List<Konferenz> konferenzen){
        this.konferenzen=konferenzen;
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
