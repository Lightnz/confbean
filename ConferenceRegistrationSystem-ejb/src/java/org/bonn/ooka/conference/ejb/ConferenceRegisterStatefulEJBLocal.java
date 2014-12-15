/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.ejb;

import java.util.List;
import javax.ejb.Local;
import org.bonn.ooka.conference.dtos.Konferenz;
import org.bonn.ooka.conference.dtos.Teilnehmer;

/**
 *
 * @author alex
 */
@Local
public interface ConferenceRegisterStatefulEJBLocal {

    String registerToConference(Konferenz konferenz);

    boolean login(int userID);

    List<Konferenz> getAngemeldeteKonferenzen();
    
    public Teilnehmer getUser();
    
}
