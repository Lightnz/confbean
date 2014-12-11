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
public class Autor implements Serializable {
	
	private String name;
	private List<Publikation> publikationen=new ArrayList<Publikation>();
	
	public Autor(String name){
		this.name=name;
	}
	
	public void addPublikation(Publikation p){
		publikationen.add(p);
	}
	
	public List<Publikation> getPublikationen(){
		return publikationen;
	}
	
	public String getName(){
		return name;
	}
	
}
