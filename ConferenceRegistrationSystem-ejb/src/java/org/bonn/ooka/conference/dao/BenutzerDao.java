/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.dao;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.bonn.ooka.conference.dtos.Benutzer;
import org.bonn.ooka.conference.dtos.Konferenz;
import org.bonn.ooka.conference.dtos.Veranstalter;

/**
 *
 * @author Fabian
 */
@Stateless
@LocalBean
public class BenutzerDao {
    
    JPADao jPADao;
    
    @PersistenceContext
    private EntityManager em;

    public <E> boolean create(E entity) {
        return jPADao.create(entity);
    }

    public <E> boolean update(E entity) {
        return jPADao.update(entity);
    }

    public <E> boolean delete(E entity) {
        return jPADao.delete(entity);
    }

    public <E> E find(Class<E> type, int id) {
        return jPADao.find(type, id);
    }

    public <E> List<E> findAll(Class<E> type) {
        return jPADao.findAll(type);
    }

    public <E> E getDefault(Class<E> type) {
        return jPADao.getDefault(type);
    }

    public List<Konferenz> getKonferenzenOf(Veranstalter v) {
        return jPADao.getKonferenzenOf(v);
    }
    
    public Benutzer findByBenutzername(String benutzername){
        List<Benutzer> resultList = em.createQuery("select b from Benutzer b where b.name like :name", Benutzer.class).setParameter("name", benutzername).getResultList();
        
        if(resultList != null && !resultList.isEmpty()){
            return resultList.get(0);
        } else {
            return null;
        }
    }
    
    public boolean registerUser(Benutzer benutzer){
        em.persist(benutzer);
        return true;
    }
    
}
