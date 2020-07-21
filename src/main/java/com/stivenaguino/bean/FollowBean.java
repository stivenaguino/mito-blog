package com.stivenaguino.bean;

import com.stivenaguino.emuns.Severity;
import com.stivenaguino.model.Persona;
import com.stivenaguino.model.PublicadorSeguidor;
import com.stivenaguino.model.Usuario;
import com.stivenaguino.service.IPersonaService;
import com.stivenaguino.service.ISeguidorService;
import com.stivenaguino.util.Utilities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class FollowBean implements Serializable {

    @Inject
    private IPersonaService personaService;
    @Inject
    private ISeguidorService seguidorService;
    private List<Persona> persons;
    private List<PublicadorSeguidor> followed;
    private Usuario usuario;

    @PostConstruct
    public void init() {
        this.usuario = Utilities.getUserSession();
        this.readyLoading();
    }

    private void listPersons() {
        this.persons = this.personaService.findAll();
        this.persons.remove(this.usuario.getPersona());
        this.persons.forEach(person -> {
            this.followed.forEach(follow -> {
                if (person.getIdPersona().intValue() == follow.getPublicador().getIdPersona().intValue()) {
                    person.setFollowing(true);
                }
            });
        });

    }

    private void listFollowing() {
        this.followed = this.seguidorService.listFollowed(((Usuario) Utilities.getUserSession()).getPersona());
    }

    public void follow(Persona person) {
        List<Persona> followers = new ArrayList();
        List<Persona> publisher = new ArrayList();
        followers.add(((Usuario) Utilities.getUserSession()).getPersona());
        publisher.add(person);
        this.seguidorService.registerPostsFollowers(followers, publisher);
        Utilities.showMessage("Aviso", new StringBuilder("¡Ahora sigues a ").append(person.getNombre())
                .append(" ").append(person.getApellido()).append("!").toString(), Severity.INFO);
        this.readyLoading();
    }

    public void unFollow(Persona person) {
        List<Persona> followers = new ArrayList();
        List<Persona> publisher = new ArrayList();
        followers.add(this.usuario.getPersona());
        publisher.add(person);
        this.seguidorService.stopFollowing(followers, publisher);
        Utilities.showMessage("Aviso", new StringBuilder("Dejaste de seguir a ").append(person.getNombre())
                .append(" ").append(person.getApellido()).toString(), Severity.ERROR);
        this.readyLoading();
    }

    private void readyLoading() {
        this.listFollowing();
        this.listPersons();
    }

    public List<Persona> getPersons() {
        return persons;
    }

    public void setPersons(List<Persona> persons) {
        this.persons = persons;
    }

}
