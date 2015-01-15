/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.ejb;

import javax.ejb.Stateless;
import javax.inject.Inject;
import org.bonn.ooka.conference.dao.JPADao;

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
}
