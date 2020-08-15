package com.stivenaguino.bean;

import com.stivenaguino.model.Usuario;
import com.stivenaguino.service.IUsuarioService;
import com.stivenaguino.util.Utilities;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.primefaces.PrimeFaces;

@Named
@ViewScoped
public class UserBean implements Serializable {

    private final Logger log = LogManager.getRootLogger();

    @Inject
    private IUsuarioService usuarioService;
    private List<Usuario> userList;
    private Usuario usuario;
    private String tipoDialog;
    private String currentPassword;
    private String newPasword;
    private boolean passwordSuccessful;
    private String criteriaSearch;

    @PostConstruct
    public void init() {
        this.listUsers();
    }

    public void listUsers() {
        this.userList = (this.criteriaSearch == null)
                ? this.usuarioService.findAll()
                : this.usuarioService.findByLikeUsername(this.criteriaSearch);
    }

    public void showData(Usuario usuario) {
        this.usuario = usuario;
        this.tipoDialog = new StringBuilder("Modificando Usuario: ").append(usuario.getUsuario()).toString();
        this.passwordSuccessful = false;
        Utilities.resetComponent("frm:dlg-grid");
    }

    public void verifyPassword() {
        try {
            this.passwordSuccessful = BCrypt.checkpw(this.currentPassword, usuario.getContrasenia());
            PrimeFaces.current().ajax().update("frm:btns-modal");
        } catch (Exception ex) {
            log.info("Credenciales incorrectas: {}", UserBean.class.getCanonicalName());
            ex.printStackTrace(System.out);
        }
    }

    public void updateUserPassword() {
        String hashingJbrypt = BCrypt.hashpw(this.newPasword, BCrypt.gensalt());
        this.usuario.setContrasenia(hashingJbrypt);
        this.usuarioService.updateUserPassword(usuario);
    }

    public List<Usuario> getUserList() {
        return userList;
    }

    public void setUserList(List<Usuario> userList) {
        this.userList = userList;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTipoDialog() {
        return tipoDialog;
    }

    public void setTipoDialog(String tipoDialog) {
        this.tipoDialog = tipoDialog;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPasword() {
        return newPasword;
    }

    public void setNewPasword(String newPasword) {
        this.newPasword = newPasword;
    }

    public boolean isPasswordSuccessful() {
        return passwordSuccessful;
    }

    public void setPasswordSuccessful(boolean passwordSuccessful) {
        this.passwordSuccessful = passwordSuccessful;
    }

    public String getCriteriaSearch() {
        return criteriaSearch;
    }

    public void setCriteriaSearch(String criteriaSearch) {
        this.criteriaSearch = criteriaSearch;
    }

}
