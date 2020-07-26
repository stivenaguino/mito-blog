package com.stivenaguino.bean;

import com.stivenaguino.model.Publicacion;
import com.stivenaguino.model.Usuario;
import com.stivenaguino.service.IPublicationService;
import com.stivenaguino.util.Utilities;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class HomeBean implements Serializable {

    @Inject
    private IPublicationService publicationService;
    private List<Publicacion> publications;
    private Usuario usuario;

    @PostConstruct
    public void init() {
        this.usuario = Utilities.getUserSession();
        this.listPublications();
    }

    public void listPublications() {
        this.publications = this.publicationService.listPublicationByFollower(this.usuario.getPersona());
    }

    public List<Publicacion> getPublications() {
        return publications;
    }

    public void setPublications(List<Publicacion> publications) {
        this.publications = publications;
    }

}
