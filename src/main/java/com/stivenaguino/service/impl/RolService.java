package com.stivenaguino.service.impl;

import com.stivenaguino.model.Rol;
import com.stivenaguino.service.IRolService;
import java.util.List;
import javax.ejb.EJB;
import com.stivenaguino.dao.IRolDao;
import com.stivenaguino.model.Usuario;
import com.stivenaguino.model.UsuarioRol;
import java.io.Serializable;
import java.util.ArrayList;
import javax.inject.Named;

@Named
public class RolService implements IRolService, Serializable {

    @EJB
    private IRolDao iRolDao;

    @Override
    public void create(Rol rol) {
        try {
            this.iRolDao.create(rol);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void edit(Rol rol) {
        try {
            this.iRolDao.edit(rol);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void destroy(Rol rol) {
        try {
            this.iRolDao.destroy(rol);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public List<Rol> findAll() {
        return this.iRolDao.findAll();
    }

    @Override
    public Rol findById(Rol rol) {
        return this.iRolDao.findById(rol);
    }

    @Override
    public void assign(Usuario usuario, List<Rol> listRoles) {
        List<UsuarioRol> usuarioRols = new ArrayList();
        listRoles.forEach(rol -> {
            usuarioRols.add(new UsuarioRol(usuario, rol));
        });
        try {
            this.iRolDao.assign(usuario, usuarioRols);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

}
