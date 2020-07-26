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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
    @NamedQuery(name = "publicacion.findByPublisher", query = "SELECT p FROM Publicacion p WHERE p.publicador.idPersona = :idPersona ORDER BY p.idPublicacion DESC"),
    @NamedQuery(name = "publicacion.findByFollower", query = "SELECT p FROM Publicacion p WHERE p.publicador.idPersona = (SELECT ps.publicador.idPersona FROM PublicadorSeguidor ps WHERE ps.seguidor.idPersona = :idPersona) ORDER BY p.idPublicacion DESC"),})
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
    @OneToMany(mappedBy = "publicacion", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
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

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Mencion> getMensiones() {
        return mensiones;
    }

    public void setMensiones(List<Mencion> mensiones) {
        this.mensiones = mensiones;
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
