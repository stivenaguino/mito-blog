package com.stivenaguino.dao.impl;

import com.stivenaguino.dao.ISeguidorDao;
import com.stivenaguino.model.Persona;
import com.stivenaguino.model.PublicadorSeguidor;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Stateless
public class SeguidorDao implements ISeguidorDao {

    private final Logger log = LogManager.getRootLogger();

    @PersistenceContext(unitName = "blogPU")
    private EntityManager em;

    @Override
    public void create(PublicadorSeguidor publicadorSeguidor) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(PublicadorSeguidor publicadorSeguidor) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy(PublicadorSeguidor publicadorSeguidor) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PublicadorSeguidor findById(PublicadorSeguidor publicadorSeguidor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void registerPostsFollowers(List<PublicadorSeguidor> publicadorSeguidors) throws Exception {
        int numeroIteraciones = 0;

        for (PublicadorSeguidor publicadorSeguidor : publicadorSeguidors) {
            if (numeroIteraciones % 100 == 0) {
                //Clean EntityManager
                em.flush();
                em.clear();
            }
            em.persist(publicadorSeguidor);
            numeroIteraciones++;
        }
    }

    @Override
    public List<PublicadorSeguidor> listFollowers(Persona persona) {
        Query query = em.createNamedQuery("PublicadorSeguidor.findByFollowers").setParameter("idPersona", persona.getIdPersona());
        List<PublicadorSeguidor> list = query.getResultList();
        return list.isEmpty() ? new ArrayList() : list;
    }

    @Override
    public void stopFollowing(List<PublicadorSeguidor> publicadorSeguidors) throws Exception {
        log.info("Ingresa al método stopFollowing de la clase {}", SeguidorDao.class.getSimpleName());
        publicadorSeguidors.forEach(publicadorSeguidor -> {
            Query query = em.createQuery("DELETE FROM PublicadorSeguidor ps WHERE ps.publicador.idPersona = ?1 AND ps.seguidor.idPersona = ?2");
            query.setParameter(1, publicadorSeguidor.getPublicador().getIdPersona());
            query.setParameter(2, publicadorSeguidor.getSeguidor().getIdPersona());
            log.info("Registros afectados {}", query.executeUpdate());
        });
        log.info("Sale del método stopFollowing de la clase {}", SeguidorDao.class.getSimpleName());
    }

    @Override
    public List<PublicadorSeguidor> listFollowed(Persona persona) {
        Query query = em.createNamedQuery("PublicadorSeguidor.findByFollowed").setParameter("idPersona", persona.getIdPersona());
        List<PublicadorSeguidor> list = query.getResultList();
        return list.isEmpty() ? new ArrayList() : list;
    }
}
