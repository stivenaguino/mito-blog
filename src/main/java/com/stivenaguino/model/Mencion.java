package com.stivenaguino.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Mencion implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMencion;
    @Column(length = 35)
    private String texto;
    @ManyToOne
    @JoinColumn(name = "id_publicacion", nullable = false)
    private Publicacion publicacion;

    public Mencion() {
    }

    public Mencion(String texto, Publicacion publicacion) {
        this.texto = texto;
        this.publicacion = publicacion;
    }

    public Integer getIdMencion() {
        return idMencion;
    }

    public void setIdMencion(Integer idMencion) {
        this.idMencion = idMencion;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mencion{idMencion=").append(idMencion);
        sb.append(", texto=").append(texto);
        sb.append(", publicacion=").append(publicacion);
        sb.append('}');
        return sb.toString();
    }

}
