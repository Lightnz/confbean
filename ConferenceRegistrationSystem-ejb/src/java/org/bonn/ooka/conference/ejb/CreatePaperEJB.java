/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.ejb;

import javax.ejb.Stateless;
import javax.inject.Inject;
import org.bonn.ooka.conference.dao.JPADao;
import org.bonn.ooka.conference.dtos.Publikation;

/**
 *
 * @author Fabian
 */
@Stateless
public class CreatePaperEJB implements CreatePaperEJBLocal {

    
    @Inject
    JPADao dao;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Override
    public String createPaper(Publikation publikation){
        //TODO schöner schreiben.
        if(dao.create(publikation)){
            return publikation.getAutor().getName()+", ihre Publikation "+publikation.getTitel()+" zur Konferenz '"+publikation.getKonferenz()+"' wurde erfolgreich zur Überprüfung eingereicht!";
        }else{
            return publikation.getAutor().getName()+", ihre Publikation "+publikation.getTitel()+" zur Konferenz '"+publikation.getKonferenz()+"' konnte nicht erfolgreich zur Überprüfung eingereicht werden. <p/>Bitte versuchen sie es zu einem späteren Zeitpunkt erneut.";
        }
    }
    
}
