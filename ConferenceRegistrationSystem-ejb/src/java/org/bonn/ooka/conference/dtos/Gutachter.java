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
	
	Map<Integer, Gutachten> gutachtenliste = new TreeMap<Integer, Gutachten>();
	
	public void addGutachten(Gutachten g){
		gutachtenliste.put(g.getID(), g);
	}
	
	public void removeGutachten(int ID){
		gutachtenliste.remove(ID);
	}
	
}