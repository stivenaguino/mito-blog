package com.stivenaguino.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Publicacion implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPublicacion;
    @Column(nullable = false, length = 250)
    private String contenido;
    @ManyToOne
    @JoinColumn(name = "id_publicador", nullable = false)
    private Persona publicador;
    @OneToMany(mappedBy = "publicacion", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Tag> tags;
    @OneToMany(mappedBy = "publicacion")
    private List<Mencion> mensiones;

    public Integer getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(Integer idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Persona getPublicador() {
        return publicador;
    }

    public void setPublicador(Persona publicador) {
        this.publicador = publicador;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public List<Mencion> getMensiones() {
        return mensiones;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Publicacion{idPublicacion=").append(idPublicacion);
        sb.append(", contenido=").append(contenido);
        sb.append(", publicador=").append(publicador);
        sb.append('}');
        return sb.toString();
    }

}
