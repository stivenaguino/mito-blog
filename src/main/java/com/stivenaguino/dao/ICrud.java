package com.stivenaguino.dao;

import java.util.List;

public interface ICrud<T> {

    void create(T t) throws Exception;

    void edit(T t) throws Exception;

    void destroy(T t) throws Exception;

    List<T> findAll();

    T findById(T t);
}
