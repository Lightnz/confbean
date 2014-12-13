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
public class User implements Serializable{
    
    int id;
    
    public User(int id){
        this.id=id;
    }
    
    public int getID(){
        return id;
    }
    
}
