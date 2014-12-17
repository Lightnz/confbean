/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.dtos;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Fabian
 */
public class Gutachter implements Serializable {
	
    private String name;
    private int id;
    
    public Gutachter(int id, String name){
        this.id=id;
        this.name=name;
    }
    
    public void setID(int id){
        this.id=id;
    }
    
    public int getID(){
        return id;
    }
    
    public void setName(String name){
        this.name=name;
    }
    
    public String getName(){
        return name;
    }
	
}