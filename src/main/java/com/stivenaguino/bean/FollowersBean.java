package com.stivenaguino.bean;

import com.stivenaguino.model.PublicadorSeguidor;
import com.stivenaguino.model.Usuario;
import com.stivenaguino.service.ISeguidorService;
import com.stivenaguino.util.Utilities;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class FollowersBean implements Serializable {

    @Inject
    private ISeguidorService seguidorService;
    private List<PublicadorSeguidor> followers;
    private Usuario usuario;

    @PostConstruct
    public void init() {
        this.usuario = (Usuario) Utilities.getUserSession();
        this.listFollowers();
    }

    private void listFollowers() {
        this.followers = this.seguidorService.listFollowers(this.usuario.getPersona());
    }

    public List<PublicadorSeguidor> getFollowers() {
        return followers;
    }

    public void setFollowers(List<PublicadorSeguidor> followers) {
        this.followers = followers;
    }

}
