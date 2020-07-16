package com.stivenaguino.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "rol.findAll", query = "SELECT r FROM Rol r"),
    @NamedQuery(name = "rol.RolById", query = "SELECT r FROM Rol r WHERE r.idRol = :idRol"),
    @NamedQuery(name = "rol.RolesUsername", query = "SELECT r FROM Rol r WHERE EXISTS (SELECT 'x' FROM UsuarioRol ur WHERE ur.usuario.idUsuario = :usuario AND ur.rol.idRol = r.idRol)"),
    @NamedQuery(name = "rol.RolesNotUsername", query = "SELECT r FROM Rol r WHERE NOT EXISTS (SELECT 'x' FROM UsuarioRol ur WHERE ur.usuario.idUsuario = :usuario AND ur.rol.idRol = r.idRol)")
})
public class Rol implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRol;
    @Column(nullable = false, length = 50)
    private String tipo;

    public Rol() {
    }

    public Rol(Integer idRol) {
        this.idRol = idRol;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Rol{idRol=").append(idRol);
        sb.append(", tipo=").append(tipo);
        sb.append('}');
        return sb.toString();
    }

}
