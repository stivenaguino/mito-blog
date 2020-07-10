package com.stivenaguino.dao;

import com.stivenaguino.model.Persona;
import javax.ejb.Local;

@Local
public interface IPersonaDao extends ICrud<Persona> {
    
}
