package com.stivenaguino.dao.impl;

import com.stivenaguino.dao.IPublicationDao;
import com.stivenaguino.model.Persona;
import com.stivenaguino.model.Publicacion;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PublicationDao implements IPublicationDao {

    @PersistenceContext(unitName = "blogPU")
    private EntityManager em;

    @Override
    public void create(Publicacion publicacion) throws Exception {
        em.persist(publicacion);
    }

    @Override
    public void edit(Publicacion publicacion) throws Exception {
        em.merge(publicacion);
    }

    @Override
    public void destroy(Publicacion publicacion) throws Exception {
        em.remove(em.merge(publicacion));
    }

    @Override
    public List<Publicacion> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Publicacion findById(Publicacion publicacion) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Publicacion> listPublicationByPublisher(Persona publisher) throws Exception {
        Query query = em.createNamedQuery("publicacion.findByPublisher").setParameter("idPersona", publisher.getIdPersona());
        List<Publicacion> list = query.getResultList();
        return list.isEmpty() ? new ArrayList() : list;
    }

    @Override
    public List<Publicacion> listPublicationByFollower(Persona persona) throws Exception {
        Query query = em.createNamedQuery("publicacion.findByFollower").setParameter("idPersona", persona.getIdPersona());
        List<Publicacion> list = query.getResultList();
        return list.isEmpty() ? new ArrayList() : list;
    }
}
