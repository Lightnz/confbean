/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.dtos;

import java.io.Serializable;

/**
 *
 * @author Fabian
 */
public class Veranstalter implements Serializable {
    
    private int ID;
    private String name;
    private int rep;
    
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
