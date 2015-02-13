/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.ejb;


import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.bonn.ooka.conference.dao.JPADao;
import org.bonn.ooka.conference.dtos.Gutachten;

/**
 *
 * @author Fabian
 */
@Stateless
@LocalBean
public class CreateReviewEJB implements CreateReviewEJBLocal {

    @EJB
    JPADao dao;
    
    @Override
    public String createReview(Gutachten gutachten){
        
        if(dao.createAndUpdate(gutachten))
            return gutachten.getGutachter().getName() + ", ihr Gutachten zu Publikation '" + gutachten.getPublikation().getTitel() + "' wurde erfolgreich erstellt.";
        else{
            return gutachten.getGutachter().getName() + ", ihr Gutachten zu Publikation'" + gutachten.getPublikation().getTitel() + "' konnte nicht erstellt werden.";
        }
    }
    
}
