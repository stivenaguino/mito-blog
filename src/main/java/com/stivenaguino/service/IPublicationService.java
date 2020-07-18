package com.stivenaguino.service;

import com.stivenaguino.model.Persona;
import com.stivenaguino.model.Publicacion;
import java.util.List;

public interface IPublicationService extends IService<Publicacion> {

    List<Publicacion> listPublicationByPublisher(Persona publisher);

    List<Publicacion> listPublicationByFollower(Persona persona);

}
