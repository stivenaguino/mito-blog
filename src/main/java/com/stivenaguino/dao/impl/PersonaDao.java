package com.stivenaguino.dao.impl;

import com.stivenaguino.dao.IPersonaDao;
import com.stivenaguino.model.Persona;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PersonaDao implements IPersonaDao {

    @PersistenceContext(unitName = "blogPU")
    private EntityManager em;

    @Override
    public void create(Persona persona) throws Exception {
        em.persist(persona);
    }

    @Override
    public void edit(Persona persona) throws Exception {
        em.merge(persona);
    }

    @Override
    public void destroy(Persona persona) throws Exception {
        em.remove(em.merge(persona));
    }

    @Override
    public List<Persona> findAll() {
        return em.createNamedQuery("persona.findAll").getResultList();
    }

    @Override
    public Persona findById(Persona persona) {
        Query query = em.createNamedQuery("persona.findById").setParameter("IdPersona", persona.getIdPersona());
        List<Persona> list = query.getResultList();
        return list.isEmpty() ? persona : list.get(0);
    }

}
