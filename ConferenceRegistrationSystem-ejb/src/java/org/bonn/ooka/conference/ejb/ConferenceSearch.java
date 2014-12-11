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

/**
 *
 * @author alex
 */
@Stateful
public class ConferenceSearch implements ConferenceSearchRemote {

    public List<String> conferenceList = new ArrayList<String>();
    
    
      
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<String> getConferenceList() {
               
        return conferenceList;
    }
    
    
    
    
    @PostConstruct        
    private void prefillExampleList(){
        conferenceList.add("Object Oriented Component Architecture Conference");
        conferenceList.add("Java EE Conference");
        conferenceList.add("Software Engineering Conference");
    }

    @Override
    public void addConference(String name) {
        conferenceList.add(name);
    }

    
    
}
