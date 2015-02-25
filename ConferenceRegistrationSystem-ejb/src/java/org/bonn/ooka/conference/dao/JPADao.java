/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.conference.dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContexts;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.bonn.ooka.conference.dtos.Benutzer;
import org.bonn.ooka.conference.dtos.Konferenz;
import org.bonn.ooka.conference.dtos.Publikation;
import org.bonn.ooka.conference.dtos.Veranstalter;

/**
 *
 * @author Fabian
 */
@Stateless
@LocalBean
public class JPADao {

    @PersistenceContext
    protected EntityManager em;
    
    public <E> boolean create(E entity){
        this.em.persist(entity);
        //evictAll weil keine aktualisierungen sonst.
        this.em.getEntityManagerFactory().getCache().evictAll();
        return true;
    }
    
    public <E> boolean update(E entity){
        this.em.merge(entity);
        //evictAll weil keine aktualisierungen sonst.
        this.em.getEntityManagerFactory().getCache().evictAll();
        return true;
    }
    
    public <E> boolean delete(E entity){
        E toBeRemoved = this.em.merge(entity);
        this.em.remove(toBeRemoved);
        //evictAll weil keine aktualisierungen sonst.
        this.em.getEntityManagerFactory().getCache().evictAll();
        return true;
    }
    
    public <E> E find(Class<E> type, int id){
        return em.find(type, id);
    }
    
    public <E> List<E> findAll(Class<E> type){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<E> criteria = cb.createQuery(type);
        criteria.from(type);
        TypedQuery<E> query = em.createQuery(criteria);
        
        return query.getResultList();
    }
    
    //TODO: in extra DAO auslagern
    public List<Publikation> findPublikationByName(String s){
        List<Publikation> resultList = em.createQuery("select p from Publikation p where p.titel like :titel", Publikation.class).setParameter("titel", "%"+s+"%").getResultList();
        return resultList;
    }

    //TODO: in extra DAO auslagern
    public List<Konferenz> findKonferenzByName(String s){
        List<Konferenz> resultList = em.createQuery("select k from Konferenz k where k.titel like :titel", Konferenz.class).setParameter("titel", "%"+s+"%").getResultList();
        return resultList;
    }
    
    public <E> E getDefault(Class<E> type){
        return em.find(type, 1);
    }
    
    public List<Konferenz> getKonferenzenOf(Veranstalter v){
        List<Konferenz> result = new ArrayList<Konferenz>();
        for(Konferenz k : findAll(Konferenz.class)){
            if(k.getVeranstalter()==v){
                result.add(k);
            }
        }
        return result;
    }
    
    
}
