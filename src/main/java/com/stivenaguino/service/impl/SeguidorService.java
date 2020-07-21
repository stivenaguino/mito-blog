package com.stivenaguino.service.impl;

import com.stivenaguino.dao.ISeguidorDao;
import com.stivenaguino.model.Persona;
import com.stivenaguino.model.PublicadorSeguidor;
import com.stivenaguino.service.ISeguidorService;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;

@Named
public class SeguidorService implements ISeguidorService, Serializable {
    
    @EJB
    private ISeguidorDao iSeguidorDao;
    
    @Override
    public void create(PublicadorSeguidor publicadorSeguidor) {
        try {
            this.iSeguidorDao.create(publicadorSeguidor);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    @Override
    public void edit(PublicadorSeguidor publicadorSeguidor) {
        try {
            this.iSeguidorDao.edit(publicadorSeguidor);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    @Override
    public void destroy(PublicadorSeguidor publicadorSeguidor) {
        try {
            this.iSeguidorDao.destroy(publicadorSeguidor);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    @Override
    public List<PublicadorSeguidor> findAll() {
        return this.iSeguidorDao.findAll();
    }
    
    @Override
    public PublicadorSeguidor findById(PublicadorSeguidor publicadorSeguidor) {
        return this.iSeguidorDao.findById(publicadorSeguidor);
    }
    
    @Override
    public void registerPostsFollowers(List<Persona> followers, List<Persona> publishers) {
        List<PublicadorSeguidor> publicadorSeguidors = this.createPublicadorSeguidor(followers, publishers);
        try {
            this.iSeguidorDao.registerPostsFollowers(publicadorSeguidors);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    @Override
    public List<PublicadorSeguidor> listFollowers(Persona persona) {
        return iSeguidorDao.listFollowers(persona);
    }
    
    @Override
    public void stopFollowing(List<Persona> followers, List<Persona> publishers) {
        List<PublicadorSeguidor> publicadorSeguidors = this.createPublicadorSeguidor(followers, publishers);
        try {
            this.iSeguidorDao.stopFollowing(publicadorSeguidors);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    @Override
    public List<PublicadorSeguidor> listFollowed(Persona persona) {
        return iSeguidorDao.listFollowed(persona);
    }
    
    private List<PublicadorSeguidor> createPublicadorSeguidor(List<Persona> followers, List<Persona> publishers) {
        List<PublicadorSeguidor> publicadorSeguidors = new LinkedList();
        publishers.forEach(publicher -> {
            followers.forEach(follower -> {
                LocalDateTime dateTime = LocalDateTime.now();
                PublicadorSeguidor publicadorSeguidor = new PublicadorSeguidor(publicher, follower, dateTime);
                publicadorSeguidors.add(publicadorSeguidor);
            });
        });
        return publicadorSeguidors;
    }
}
