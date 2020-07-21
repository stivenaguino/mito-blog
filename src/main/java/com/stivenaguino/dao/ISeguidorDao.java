package com.stivenaguino.dao;

import com.stivenaguino.model.Persona;
import com.stivenaguino.model.PublicadorSeguidor;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ISeguidorDao extends ICrud<PublicadorSeguidor> {

    void registerPostsFollowers(List<PublicadorSeguidor> publicadorSeguidors) throws Exception;

    List<PublicadorSeguidor> listFollowers(Persona persona);

    void stopFollowing(List<PublicadorSeguidor> publicadorSeguidors) throws Exception;

    List<PublicadorSeguidor> listFollowed(Persona persona);
}
