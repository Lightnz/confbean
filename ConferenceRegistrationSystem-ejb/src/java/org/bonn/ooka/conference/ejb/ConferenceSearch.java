/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.ejb;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.bonn.ooka.conference.dao.JPADao;
import org.bonn.ooka.conference.dtos.Gutachter;
import org.bonn.ooka.conference.dtos.Konferenz;
import org.bonn.ooka.conference.dtos.Veranstalter;

/**
 *
 * @author alex
 */
@Stateless
public class ConferenceSearch implements ConferenceSearchLocal  {

    @EJB
    JPADao dao;
    
    public ConferenceSearch() {
        
    }
    
    @Override
    public List<Konferenz> findConferences(String s) {
        return dao.findKonferenzByName(s);
    }

    @Override
    public List<Konferenz> getAllConferences() {
        return dao.findAll(Konferenz.class);
    } 
    
    @Override
    public List<Konferenz> getKonferenzenOf(Veranstalter v){
        return dao.getKonferenzenOf(v);
    }
    
}
