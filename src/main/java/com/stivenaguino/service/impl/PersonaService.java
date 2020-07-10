package com.stivenaguino.service.impl;

import com.stivenaguino.dao.IPersonaDao;
import com.stivenaguino.model.Persona;
import com.stivenaguino.service.IPersonaService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;

@Named
public class PersonaService implements IPersonaService, Serializable {

    @EJB
    private IPersonaDao iPersonaDao;

    @Override
    public void create(Persona persona) {
        try {
            iPersonaDao.create(persona);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void edit(Persona persona) {
        try {
            iPersonaDao.edit(persona);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void destroy(Persona persona) {
        try {
            iPersonaDao.destroy(persona);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public List<Persona> findAll() {
        return iPersonaDao.findAll();
    }

    @Override
    public Persona findById(Persona persona) {
        return iPersonaDao.findById(persona);
    }

}
