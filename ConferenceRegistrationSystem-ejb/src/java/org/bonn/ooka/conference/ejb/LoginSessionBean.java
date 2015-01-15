/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.ejb;

import javax.ejb.Stateful;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import org.bonn.ooka.conference.dtos.Veranstalter;

/**
 *
 * @author Fabian
 */
@Stateful
@SessionScoped
public class LoginSessionBean implements LoginSessionBeanLocal {

    private Veranstalter veranstalter;
    
    @Override
    public Veranstalter getVeranstalter(){
        return veranstalter;
    }
    
    @Override
    public void setVeranstalter(Veranstalter veranstalter){
        this.veranstalter=veranstalter;
    }
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
