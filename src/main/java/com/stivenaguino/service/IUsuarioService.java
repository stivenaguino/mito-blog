package com.stivenaguino.service;

import com.stivenaguino.model.Usuario;
import java.util.List;

public interface IUsuarioService extends IService<Usuario> {

    Usuario login(Usuario usuario);

    void updateUserPassword(Usuario usuario);

    List<Usuario> findByLikeUsername(String criterio);
}
