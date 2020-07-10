package com.stivenaguino.dao.impl;

import com.stivenaguino.model.Rol;
import com.stivenaguino.model.Usuario;
import com.stivenaguino.model.UsuarioRol;
import com.stivenaguino.util.Utilities;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.stivenaguino.dao.IRolDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Stateless
public class RolDao implements IRolDao {

    private final Logger log = LogManager.getRootLogger();

    @PersistenceContext(unitName = "blogPU")
    private EntityManager em;

    @Override
    public void create(Rol rol) throws Exception {
        em.persist(rol);
    }

    @Override
    public void edit(Rol rol) throws Exception {
        em.merge(rol);
    }

    @Override
    public void destroy(Rol rol) throws Exception {
        em.remove(em.merge(rol));
    }

    @Override
    public List<Rol> findAll() {
        return em.createNamedQuery("rol.findAll").getResultList();
    }

    @Override
    public Rol findById(Rol rol) {
        return (Rol) Utilities.nvl(
                em.createNamedQuery("rol.RolById").setParameter("idRol", rol.getIdRol()).getSingleResult(),
                new Rol()
        );
    }

    @Override
    public void assign(Usuario usuario, List<UsuarioRol> usuarioRols) throws Exception {
        log.info("Ingresa al método assign de la clase {}", RolDao.class.getSimpleName());
        Query query = em.createNativeQuery("DELETE FROM Usuario_Rol WHERE Id = :Id");
        query.setParameter("Id", usuario.getIdUsuario());
        log.info("Registros afectador {}", query.executeUpdate());
        //Scroll through the listRoles List
        int numeroIteraciones = 0;
        for (UsuarioRol usuarioRol : usuarioRols) {
            em.persist(usuarioRol);
            if (numeroIteraciones % 100 == 0) {
                //Clean EntityManager
                em.flush();
                em.clear();
            }
            numeroIteraciones++;
        }
        log.info("Sale del método assign de la clase {}", RolDao.class.getSimpleName());
    }

}
