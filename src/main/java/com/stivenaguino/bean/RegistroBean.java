package com.stivenaguino.bean;

import com.stivenaguino.model.Persona;
import com.stivenaguino.model.Rol;
import com.stivenaguino.model.Usuario;
import com.stivenaguino.service.IPersonaService;
import com.stivenaguino.service.IRolService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import org.mindrot.jbcrypt.BCrypt;

@Named
@RequestScoped
public class RegistroBean implements Serializable {

    @Inject
    private IPersonaService personaService;
    @Inject
    private IRolService rolService;
    private Persona persona;
    private Usuario usuario;

    @PostConstruct
    public void init() {
        this.persona = new Persona();
        this.usuario = new Usuario();
    }

    @Transactional
    public String register() {
        String redirect = "index?faces-redirect=true";
        //Hashing jBCrypt
        String hashingJbrypt = BCrypt.hashpw(this.usuario.getContrasenia(), BCrypt.gensalt());
        this.usuario.setContrasenia(hashingJbrypt);
        //User and Person insetion
        this.usuario.setPersona(this.persona);
        this.persona.setUsuario(this.usuario);
        personaService.create(this.persona);
        //Loading list of Roles
        List<Rol> roles = new ArrayList();
        roles.add(new Rol(1));
        this.rolService.assign(usuario, roles);
        return redirect;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
