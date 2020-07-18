package com.stivenaguino.dao;

import com.stivenaguino.model.Persona;
import com.stivenaguino.model.Publicacion;
import java.util.List;
import javax.ejb.Local;

@Local
public interface IPublicationDao extends ICrud<Publicacion> {

    List<Publicacion> listPublicationByPublisher(Persona publisher) throws Exception;

    List<Publicacion> listPublicationByFollower(Persona persona) throws Exception;

}
