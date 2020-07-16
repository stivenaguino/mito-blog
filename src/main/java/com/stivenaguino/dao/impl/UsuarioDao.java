package com.stivenaguino.dao.impl;

import com.stivenaguino.dao.IUsuarioDao;
import com.stivenaguino.model.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class UsuarioDao implements IUsuarioDao {

    @PersistenceContext(unitName = "blogPU")
    private EntityManager em;

    @Override
    public void create(Usuario usuario) throws Exception {
        em.persist(usuario);
    }

    @Override
    public void edit(Usuario usuario) throws Exception {
        em.merge(usuario);
    }

    @Override
    public void destroy(Usuario usuario) throws Exception {
        em.remove(em.merge(usuario));
    }

    @Override
    public List<Usuario> findAll() {
        return em.createNamedQuery("usuario.findAll").getResultList();
    }

    @Override
    public Usuario findById(Usuario usuario) {
        return null;
    }

    @Override
    public Usuario findByUsername(String username) {
        Query query = em.createNamedQuery("usuario.findByUsername").setParameter("username", username);
        List<Usuario> listUsers = query.getResultList();
        return listUsers.isEmpty() ? new Usuario() : listUsers.get(0);
    }

}
