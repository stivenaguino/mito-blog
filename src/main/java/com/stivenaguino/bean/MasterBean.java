package com.stivenaguino.bean;

import com.stivenaguino.model.Usuario;
import com.stivenaguino.util.Constants;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class MasterBean implements Serializable {
    
    public void verifySession() {
        FacesContext context = FacesContext.getCurrentInstance();
        Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get(Constants.USER_SESSION);
        if (usuario == null) {
            this.redirectToStart(context);
        }
    }
    
    public void signOff() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        this.verifySession();
    }
    
    private void redirectToStart(FacesContext context) {
        try {
            context.getExternalContext().redirect("../index.xhtml");
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
