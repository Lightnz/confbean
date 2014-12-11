/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.dtos;

/**
 *
 * @author Fabian
 */
public class Publikation {
	
	private Autor autor;
	private String titel;
	private int ID;
	private boolean visible;
	

	public Publikation(Autor autor, String titel, int ID){
		this.autor=autor;
		this.titel=titel;
		this.ID=ID;
		visible=false;
	}
	
	public String getTitel(){
		return titel;
	}
	
	public Autor getAutor(){
		return autor;
	}
	
	public int getID(){
		return ID;
	}
	
	public void setVisible(){
		visible=true;
	}
	
	public boolean getVisible(){
		return visible;
	}

}

