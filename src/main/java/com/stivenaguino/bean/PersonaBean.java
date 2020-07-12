package com.stivenaguino.bean;

import com.stivenaguino.model.Persona;
import com.stivenaguino.service.IPersonaService;
import com.stivenaguino.util.Constants;
import com.stivenaguino.util.Utilities;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;

@Named
@ViewScoped
public class PersonaBean implements Serializable {

    @Inject
    private IPersonaService personaService;
    private Persona persona;
    private List<Persona> personas;
    private String tipoDialog;
    private String btnDialog;
    private Character accion;

    @PostConstruct
    public void init() {
        this.persona = new Persona();
        this.listPersons();
    }

    private void listPersons() {
        this.personas = personaService.findAll();
    }

    public void operate() {
        switch (this.accion) {
            case Constants.INSERT:
                this.personaService.create(this.persona);
                this.listPersons();
                break;
            case Constants.UPDATE:
                this.personaService.edit(this.persona);
                break;
            default:
        }
        PrimeFaces.current().ajax().update("frm:dt");
    }

    public void handleFileUpload(FileUploadEvent event) {
        if (event.getFile() != null) {
            this.persona.setFoto(event.getFile().getContent());
            FacesMessage msg = new FacesMessage("Enhorabuena", event.getFile().getFileName() + " Se carga exitozamente.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void showData(Persona persona) {
        this.tipoDialog = "Modificar Persona";
        this.btnDialog = "Modificar";
        this.persona = persona;
        this.accion = Constants.UPDATE;
        Utilities.resetComponent("frm:dlg-grid");
    }

    public void cleanControls() {
        this.tipoDialog = "Nueva Persona";
        this.btnDialog = "Registrar";
        this.persona = new Persona();
        this.accion = Constants.INSERT;
        Utilities.resetComponent("frm:dlg-grid");
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public String getTipoDialog() {
        return tipoDialog;
    }

    public void setTipoDialog(String tipoDialog) {
        this.tipoDialog = tipoDialog;
    }

    public String getBtnDialog() {
        return btnDialog;
    }

    public void setBtnDialog(String btnDialog) {
        this.btnDialog = btnDialog;
    }

}
