/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.dtos;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Fabian
 */
@Entity
@Table(name = "gutachter", schema="confsys")
public class Gutachter extends Benutzer {
	
    @ManyToMany(mappedBy="committee")
    List<Konferenz> consultantFor = new ArrayList<Konferenz>();
    
    public Gutachter(){
        
    }
    
}