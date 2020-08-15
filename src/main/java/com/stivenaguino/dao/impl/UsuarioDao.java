package com.stivenaguino.dao.impl;

import com.stivenaguino.dao.IUsuarioDao;
import com.stivenaguino.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Stateless
public class UsuarioDao implements IUsuarioDao {

    private final Logger log = LogManager.getRootLogger();

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

    @Override
    public void updateUserPassword(Usuario usuario) throws Exception {
        log.info("Ingresa al método updateUserPassword de la clase {}", UsuarioDao.class.getSimpleName());
        Query query = em.createNativeQuery("UPDATE Usuario SET Contrasenia = :contrasenia WHERE Id = :idUsuario");
        query.setParameter("contrasenia", usuario.getContrasenia());
        query.setParameter("idUsuario", usuario.getIdUsuario());
        log.info("Registros afectados {}", query.executeUpdate());
        log.info("Sale del método updateUserPassword de la clase {}", UsuarioDao.class.getSimpleName());
    }

    @Override
    public List<Usuario> findByLikeUsername(String criterio) {
        criterio = new StringBuilder("%").append(criterio).append("%").toString();
        Query query = em.createNamedQuery("usuario.findByLikeUsername").setParameter("username", criterio);
        List<Usuario> list = query.getResultList();
        return list.isEmpty() ? new ArrayList() : list;
    }

}
