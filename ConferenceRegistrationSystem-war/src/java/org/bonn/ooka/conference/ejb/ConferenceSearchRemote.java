/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.ejb;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author alex
 */
@Remote
public interface ConferenceSearchRemote {

    List<String> getConferenceList();
    
    void addConference(String name);
    
}
