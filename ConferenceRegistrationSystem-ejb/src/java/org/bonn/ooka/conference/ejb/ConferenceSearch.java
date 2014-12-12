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
import org.bonn.ooka.conference.dao.FakeDB;
import org.bonn.ooka.conference.dtos.Konferenz;

/**
 *
 * @author alex
 */
@Stateful
public class ConferenceSearch implements ConferenceSearchLocal  {

    

    public ConferenceSearch() {
        
    }
    
    

    @Override
    public List<Konferenz> getAllConferences() {
        
        return FakeDB.getKonferenzen();
    }


    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

   

    
  
    
    
    
    
  

    
    
}
