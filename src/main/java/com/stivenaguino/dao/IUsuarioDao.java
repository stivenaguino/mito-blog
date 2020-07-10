package com.stivenaguino.dao;

import com.stivenaguino.model.Usuario;
import javax.ejb.Local;

@Local
public interface IUsuarioDao extends ICrud<Usuario> {

    Usuario findByUsername(String username);
}
