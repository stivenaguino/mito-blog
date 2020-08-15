package com.stivenaguino.dao;

import com.stivenaguino.model.Usuario;
import java.util.List;
import javax.ejb.Local;

@Local
public interface IUsuarioDao extends ICrud<Usuario> {

    Usuario findByUsername(String username);

    void updateUserPassword(Usuario usuario) throws Exception;

    List<Usuario> findByLikeUsername(String criterio);
}
