package com.stivenaguino.service.impl;

import com.stivenaguino.dao.IUsuarioDao;
import com.stivenaguino.model.Usuario;
import com.stivenaguino.service.IUsuarioService;
import com.stivenaguino.util.Constants;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

@Named
public class UsuarioService implements Serializable, IUsuarioService {

    private final Logger log = LogManager.getRootLogger();

    @EJB
    private IUsuarioDao iUsuarioDao;

    @Override
    public void create(Usuario usuario) {
        try {
            iUsuarioDao.create(usuario);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void edit(Usuario usuario) {
        try {
            iUsuarioDao.edit(usuario);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void destroy(Usuario usuario) {
        try {
            iUsuarioDao.destroy(usuario);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public List<Usuario> findAll() {
        return iUsuarioDao.findAll();
    }

    @Override
    public Usuario findById(Usuario usuario) {
        return iUsuarioDao.findById(usuario);
    }

    @Override
    public Usuario login(Usuario usuario) {
        boolean loginSuccess = false;
        Usuario usuarioDB = iUsuarioDao.findByUsername(usuario.getUsuario());
        try {
            //Valida la contraseña y el estado del usuario
            loginSuccess = BCrypt.checkpw(usuario.getContrasenia(), usuarioDB.getContrasenia())
                    && usuarioDB.getEstado().equals(Constants.ESTADO_ACTIVO);
        } catch (Exception ex) {
            log.info("Credenciales incorrectas: {}", UsuarioService.class.getCanonicalName());
            //ex.printStackTrace(System.out);
        }
        return loginSuccess ? usuarioDB : new Usuario();
    }

}
