package com.stivenaguino.service.impl;

import com.stivenaguino.dao.IPublicationDao;
import com.stivenaguino.model.Mencion;
import com.stivenaguino.model.Persona;
import com.stivenaguino.model.Publicacion;
import com.stivenaguino.model.Tag;
import com.stivenaguino.service.IPublicationService;
import com.stivenaguino.util.Constants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;

@Named
public class PublicationService implements IPublicationService, Serializable {

    @EJB
    private IPublicationDao iPublicationDao;

    @Override
    public void create(Publicacion publicacion) {
        List<Mencion> mencions = new LinkedList();
        List<Tag> tags = new LinkedList();
        String[] elements = publicacion.getContenido().split(" ");
        for (String element : elements) {
            //Mención
            if (element.startsWith(Constants.TAG_MENCION)) {
                Mencion mencion = new Mencion(element.substring(1, element.length()), publicacion);
                mencions.add(mencion);
            }
            //Tag
            if (element.startsWith(Constants.TAG_TAG)) {
                Tag tag = new Tag(element.substring(1, element.length()), publicacion);
                tags.add(tag);
            }
        }
        publicacion.setMensiones(mencions);
        publicacion.setTags(tags);
        try {
            this.iPublicationDao.create(publicacion);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void edit(Publicacion publicacion) {
        try {
            this.iPublicationDao.edit(publicacion);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void destroy(Publicacion publicacion) {
        try {
            this.iPublicationDao.destroy(publicacion);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public List<Publicacion> findAll() {
        return this.iPublicationDao.findAll();
    }

    @Override
    public Publicacion findById(Publicacion publicacion) {
        return this.iPublicationDao.findById(publicacion);
    }

    @Override
    public List<Publicacion> listPublicationByPublisher(Persona publisher) {
        List<Publicacion> list = new ArrayList();
        try {
            list = this.iPublicationDao.listPublicationByPublisher(publisher);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return list;
    }

    @Override
    public List<Publicacion> listPublicationByFollower(Persona persona) {
        List<Publicacion> list = new ArrayList();
        try {
            list = iPublicationDao.listPublicationByFollower(persona);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return list;
    }
}
