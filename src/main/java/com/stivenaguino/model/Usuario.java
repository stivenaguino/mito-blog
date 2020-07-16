package com.stivenaguino.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({
    @NamedQuery(name = "usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "usuario.findByUsername", query = "SELECT u FROM Usuario u WHERE u.usuario = :username")
})
public class Usuario implements Serializable {

    @Id
    @Column(name = "id")
    private Integer idUsuario;
    @Column(nullable = false, length = 30, unique = true)
    private String usuario;
    @Column(nullable = false, length = 80)
    private String contrasenia;
    @Column(nullable = false, length = 1)
    private Character estado = '1';
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", nullable = false)
    @MapsId
    private Persona persona;

    public Usuario() {
    }

    public Usuario(Integer idUsuario, Persona persona) {
        this.idUsuario = idUsuario;
        this.persona = persona;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario{idUsuario=").append(idUsuario);
        sb.append(", usuario=").append(usuario);
        sb.append(", contrasenia=").append(contrasenia);
        sb.append(", estado=").append(estado);
        sb.append(", persona=").append(persona);
        sb.append('}');
        return sb.toString();
    }

}
