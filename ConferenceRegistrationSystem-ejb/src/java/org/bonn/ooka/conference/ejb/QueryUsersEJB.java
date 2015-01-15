/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.ejb;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.bonn.ooka.conference.dao.JPADao;

/**
 *
 * @author Fabian
 */
@Stateless
public class QueryUsersEJB implements QueryUsersEJBLocal {
    
    @EJB
    JPADao dao;
    
    @Override
    public <E> List<E> getUsers(Class<E> type){
        return dao.findAll(type);
    }
}
