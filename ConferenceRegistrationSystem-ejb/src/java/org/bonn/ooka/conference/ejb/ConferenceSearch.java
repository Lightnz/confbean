/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.ejb;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import org.bonn.ooka.conference.dao.ConferenceDAO;
import org.bonn.ooka.conference.dao.FakeDB;
import org.bonn.ooka.conference.dtos.Konferenz;

/**
 *
 * @author alex
 */
@Stateful
public class ConferenceSearch implements ConferenceSearchLocal  {

    private FakeDB dao;

    public ConferenceSearch() {
        this.dao = new FakeDB();
    }
    
    @Override
    public List<Konferenz> getConferencesIncludingString(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Konferenz> getAllConferences() {
        
        return dao.getKonferenzen();
    }


    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

   

    
  
    
    
    
    
  

    
    
}
