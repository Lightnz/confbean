/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.bonn.ooka.conference.dao.JPADao;
import org.bonn.ooka.conference.dtos.Gutachter;
import org.bonn.ooka.conference.dtos.Konferenz;
import org.bonn.ooka.conference.dtos.Publikation;

/**
 *
 * @author Fabian
 */
@Stateless
public class CRUDConferenceEJB implements CRUDConferenceEJBLocal {

    @Inject
    JPADao dao;
    
    @Override
    public String editConference(Konferenz konferenz){
        if(dao.update(konferenz))
            return konferenz.getVeranstalter().getName() + ", ihre Konferenz '" + konferenz.getTitel() + "' wurde erfolgreich geändert.";
        else
            return konferenz.getVeranstalter().getName() + ", ihre Konferenz '" + konferenz.getTitel() + "' konnte nicht geändert werden. Entweder wurde die Zielkonferenz nicht gefunden oder sie dürfen keine Konferenzen mit über 200 Teilnehmern erstellen.";
    }
    
    @Override
    public String deleteConference(Konferenz konferenz){
        if(dao.delete(konferenz))
            return konferenz.getVeranstalter().getName() + ", ihre Konferenz '" + konferenz.getTitel() + "' wurde erfolgreich gelöscht.";
        else
            return konferenz.getVeranstalter().getName() + ", es ist bei der Löschung ihrer Konferenz '"+ konferenz.getTitel() + " zu einem Fehler gekommen.";
    }
    
    @Override
    public String createConference(Konferenz konferenz){
        
        if(dao.create(konferenz))
            return konferenz.getVeranstalter().getName() + ", ihre Konferenz '" + konferenz.getTitel() + "' wurde erfolgreich erstellt.";
        else{
            return konferenz.getVeranstalter().getName() + ", ihre Konferenz '" + konferenz.getTitel() + "' darf aufgrund negativer Bewertungen nicht mehr als 200 Teilnehmer groß sein. Die Konferenz wurde nicht erstellt.";
        }
    }
    
}
