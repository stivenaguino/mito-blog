package com.stivenaguino.bean;

import com.stivenaguino.model.Rol;
import com.stivenaguino.model.Usuario;
import com.stivenaguino.service.IRolService;
import com.stivenaguino.service.IUsuarioService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.DualListModel;

@Named
@ViewScoped
public class AsignarBean implements Serializable {

    @Inject
    private IUsuarioService usuarioService;
    @Inject
    private IRolService rolService;
    private List<Usuario> usuarios;
    private Usuario usuario;
    private DualListModel<Rol> roles;

    @PostConstruct
    public void init() {
        this.listPersons();
        this.listRoles();
    }

    public void listPersons() {
        this.usuarios = usuarioService.findAll();
    }

    public void listRoles() {
        List<Rol> rolesSource = rolService.rolesUsername(this.usuario, false);
        List<Rol> rolesTarget = rolService.rolesUsername(this.usuario, true);
        this.roles = new DualListModel(rolesSource, rolesTarget);
    }

    public void assign() {
        this.rolService.assign(this.usuario, this.roles.getTarget());
    }

    public DualListModel<Rol> getRoles() {
        return roles;
    }

    public void setRoles(DualListModel<Rol> roles) {
        this.roles = roles;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
