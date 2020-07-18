package com.stivenaguino.bean;

import com.stivenaguino.model.Usuario;
import com.stivenaguino.service.IUsuarioService;
import com.stivenaguino.util.Constants;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class IndexBean {

    private Usuario usuario;
    @Inject
    IUsuarioService usuarioService;

    @PostConstruct
    public void init() {
        this.usuario = new Usuario();
    }

    public String login() {
        String redirect = "";
        Usuario usuarioLogin = usuarioService.login(usuario);
        if (usuarioLogin != null) {
            redirect = "/site/roles?faces-redirect=true";
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Constants.USER_SESSION, usuarioLogin);
        } else {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Credenciales incorrectas");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
        return redirect;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
