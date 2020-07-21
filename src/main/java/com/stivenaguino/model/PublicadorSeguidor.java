package com.stivenaguino.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "publicador_seguidor")
@NamedQueries({
    @NamedQuery(name = "PublicadorSeguidor.findByFollowed", query = "SELECT ps FROM PublicadorSeguidor ps WHERE ps.seguidor.idPersona = :idPersona"),
    @NamedQuery(name = "PublicadorSeguidor.findByFollowers", query = "SELECT ps FROM PublicadorSeguidor ps WHERE ps.publicador.idPersona = :idPersona"),})
public class PublicadorSeguidor implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPublicadorSeguidor;
    @ManyToOne
    @JoinColumn(name = "id_publicador", nullable = false)
    private Persona publicador;
    @ManyToOne
    @JoinColumn(name = "id_seguidor", nullable = false)
    private Persona seguidor;
    @Column(nullable = false)
    private LocalDateTime fecha;

    public PublicadorSeguidor() {
    }

    public PublicadorSeguidor(Persona publicador, Persona seguidor, LocalDateTime fecha) {
        this.publicador = publicador;
        this.seguidor = seguidor;
        this.fecha = fecha;
    }

    public Integer getIdPublicadorSeguidor() {
        return idPublicadorSeguidor;
    }

    public void setIdPublicadorSeguidor(Integer idPublicadorSeguidor) {
        this.idPublicadorSeguidor = idPublicadorSeguidor;
    }

    public Persona getPublicador() {
        return publicador;
    }

    public void setPublicador(Persona publicador) {
        this.publicador = publicador;
    }

    public Persona getSeguidor() {
        return seguidor;
    }

    public void setSeguidor(Persona seguidor) {
        this.seguidor = seguidor;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PublicadorSeguidor{idPublicadorSeguidor=").append(idPublicadorSeguidor);
        sb.append(", publicador=").append(publicador);
        sb.append(", seguidor=").append(seguidor);
        sb.append(", fecha=").append(fecha);
        sb.append('}');
        return sb.toString();
    }

}
