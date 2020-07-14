/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Stiven Aguiño
 */
@Named
@ViewScoped
public class AsignarBean implements Serializable {

    @Inject
    private IPersonaService personaService;
    @Inject
    private IRolService rolService;
    private List<Persona> personas;
    private Persona persona;
    private DualListModel<Rol> roles;

    @PostConstruct
    public void init() {
        this.listPersons();
        this.listRoles();
    }

    public void listPersons() {
        this.personas = personaService.findAll();
    }

    public void listRoles() {
        List<Rol> rolesSource = rolService.findAll();
        List<Rol> rolesTarget = new ArrayList();
        this.roles = new DualListModel(rolesSource, rolesTarget);
    }

    public void assign() {
        Usuario usuario = new Usuario(this.persona.getIdPersona(), this.persona);
        this.rolService.assign(usuario, this.roles.getTarget());
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public DualListModel<Rol> getRoles() {
        return roles;
    }

    public void setRoles(DualListModel<Rol> roles) {
        this.roles = roles;
    }

}
