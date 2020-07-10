package com.stivenaguino.bean;

import com.stivenaguino.model.Rol;
import com.stivenaguino.service.IRolService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

@Named
@RequestScoped
public class RolBean {
    
    @Inject
    private IRolService rolService;
    private List<Rol> roles;
    
    @PostConstruct
    public void init() {
        this.list();
    }
    
    public void list() {
        this.roles = rolService.findAll();
    }
    
    public void onRowEdit(RowEditEvent event) {
        Rol rol = (Rol) event.getObject();
        this.rolService.edit(rol);
        FacesMessage facesMessage = new FacesMessage("Se modificó", rol.getTipo());
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
    
    public List<Rol> getRoles() {
        return roles;
    }
    
    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }
    
}
