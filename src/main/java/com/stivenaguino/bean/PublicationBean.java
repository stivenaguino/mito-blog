package com.stivenaguino.bean;

import com.stivenaguino.model.Persona;
import com.stivenaguino.model.Publicacion;
import com.stivenaguino.model.Usuario;
import com.stivenaguino.service.IPublicationService;
import com.stivenaguino.util.Constants;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class PublicationBean implements Serializable {

    @Inject
    private IPublicationService publicationService;
    private Publicacion publicacion;
    private Usuario usuario;
    private List<Publicacion> publications;

    @PostConstruct
    public void init() {
        this.publicacion = new Publicacion();
        this.usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(Constants.USER_SESSION);
        this.listPublications();
    }

    public void publish() {
        Persona persona = new Persona(this.usuario.getPersona().getIdPersona());
        this.publicacion.setPublicador(persona);
        this.publicationService.create(publicacion);
        this.publicacion = new Publicacion();
        this.listPublications();
    }

    public void listPublications() {
        this.publications = this.publicationService.listPublicationByFollower(this.usuario.getPersona());
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    public List<Publicacion> getPublications() {
        return publications;
    }

    public void setPublications(List<Publicacion> publications) {
        this.publications = publications;
    }

}
