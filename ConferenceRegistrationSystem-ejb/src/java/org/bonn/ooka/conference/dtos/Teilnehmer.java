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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author Fabian
 */
@Entity
public class Teilnehmer implements Serializable {
    
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @ManyToMany(cascade = CascadeType.ALL)
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

    public Integer getId() {
        return id;
    }

    public void addConference(Konferenz konferenz) {
        this.angemeldeteKonferenzen.add(konferenz);
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
