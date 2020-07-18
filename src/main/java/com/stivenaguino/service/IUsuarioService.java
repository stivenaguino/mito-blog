package com.stivenaguino.service;

import com.stivenaguino.model.Usuario;

public interface IUsuarioService extends IService<Usuario> {

    Usuario login(Usuario usuario);

}
