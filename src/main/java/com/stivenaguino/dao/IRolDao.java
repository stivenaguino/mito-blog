package com.stivenaguino.dao;

import com.stivenaguino.model.Rol;
import com.stivenaguino.model.Usuario;
import com.stivenaguino.model.UsuarioRol;
import java.util.List;
import javax.ejb.Local;

@Local
public interface IRolDao extends ICrud<Rol> {

    void assign(Usuario usuario, List<UsuarioRol> usuarioRols) throws Exception;
}
