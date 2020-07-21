package com.stivenaguino.service;

import com.stivenaguino.model.Persona;
import com.stivenaguino.model.PublicadorSeguidor;
import java.util.List;

public interface ISeguidorService extends IService<PublicadorSeguidor> {

    void registerPostsFollowers(List<Persona> followers, List<Persona> publishers);

    List<PublicadorSeguidor> listFollowers(Persona persona);

    void stopFollowing(List<Persona> followers, List<Persona> publishers);

    List<PublicadorSeguidor> listFollowed(Persona persona);
}
