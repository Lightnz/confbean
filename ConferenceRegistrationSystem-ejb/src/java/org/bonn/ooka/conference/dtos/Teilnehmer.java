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

/**
 *
 * @author Fabian
 */
@Entity
@Table(name = "teilnehmer", schema="confsys")
public class Teilnehmer implements Serializable {
    
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @ManyToMany(cascade = CascadeType.MERGE)
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

    public Integer getId() {
        return id;
    }

    public boolean addConference(Konferenz konferenz) {
        if(konferenz.teilnehmerliste.size()<=konferenz.getSlots()){
            this.angemeldeteKonferenzen.add(konferenz);
            konferenz.addTeilnehmer(this);
            //TODO Unschönes selbst-überschreiben durch signal an JPA vermeiden (möglich?)
            List<Konferenz> tmp = angemeldeteKonferenzen;
            angemeldeteKonferenzen = tmp;
            return true;
        }
        else
            return false;
    }
    
    public boolean removeConference(Konferenz konferenz) {
        this.angemeldeteKonferenzen.remove(konferenz);
        konferenz.removeTeilnehmer(this);
        return true;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
