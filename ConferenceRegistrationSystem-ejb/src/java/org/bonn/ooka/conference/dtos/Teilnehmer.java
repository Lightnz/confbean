/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Fabian
 */
@Entity
@Table(name = "teilnehmer", schema="confsys")
public class Teilnehmer extends Benutzer {
    
    @ManyToMany(cascade = CascadeType.REFRESH)
     @JoinTable(
      name="teiln_konf",
      schema="confsys",
      joinColumns={@JoinColumn(name="TEIL_ID", referencedColumnName="id")},
      inverseJoinColumns={@JoinColumn(name="KONF_ID", referencedColumnName="id")})
    private List<Konferenz> angemeldeteKonferenzen = new ArrayList<Konferenz>();

    public Teilnehmer(){
        
    }
    
    public Teilnehmer(Integer id, String name){
        this.name = name;
        this.id = id;
    }
    
    public List<Konferenz> getAngemeldeteKonferenzen() {
        return angemeldeteKonferenzen;
    }
    
    public List<Konferenz> getZukuenftigeKonferenzen() {
        Date currentTime = new Date();
        List<Konferenz> zukuenftigeKonferenzen = new ArrayList<>();
        for(Konferenz k : angemeldeteKonferenzen){
            if(currentTime.getTime() <= k.getDatum().getTime())
                zukuenftigeKonferenzen.add(k);
        }
        return zukuenftigeKonferenzen;
    }
    
    public List<Konferenz> getVergangeneKonferenzen() {
        Date currentTime = new Date();
        List<Konferenz> vergangeneKonferenzen = new ArrayList<>();
        for(Konferenz k : angemeldeteKonferenzen){
            if(currentTime.getTime() > k.getDatum().getTime())
                vergangeneKonferenzen.add(k);
        }
        return vergangeneKonferenzen;
    }

    public boolean addConference(Konferenz konferenz) {
        angemeldeteKonferenzen.add(konferenz);
        //TODO Unschönes selbst-überschreiben durch signal an JPA vermeiden (möglich?)
        List<Konferenz> tmp = angemeldeteKonferenzen;
        angemeldeteKonferenzen = tmp;
        return true;
    }
    
    public boolean removeConference(Konferenz konferenz) {
        angemeldeteKonferenzen.remove(konferenz);
        //TODO Unschönes selbst-überschreiben durch signal an JPA vermeiden (möglich?)
        List<Konferenz> tmp = angemeldeteKonferenzen;
        angemeldeteKonferenzen = tmp;
        return true;
    }
    
}
