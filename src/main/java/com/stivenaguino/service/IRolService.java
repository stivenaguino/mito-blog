package com.stivenaguino.service;

import com.stivenaguino.model.Rol;
import com.stivenaguino.model.Usuario;
import java.util.List;

public interface IRolService extends IService<Rol> {

    void assign(Usuario usuario, List<Rol> listRoles);

    List<Rol> rolesUsername(Usuario usuario, boolean rolesUsername);
}
