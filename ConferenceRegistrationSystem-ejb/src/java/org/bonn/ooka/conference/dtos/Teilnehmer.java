/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fabian
 */
public class Teilnehmer implements Serializable {
    
    private String name;
    private Integer id;
    private List<Konferenz> angemeldeteKonferenzen;

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
    
    public Teilnehmer(Integer id, String name){
        this.name = name;
        this.id = id;
        this.angemeldeteKonferenzen = new ArrayList<Konferenz>();
                
    }
    
    
}
