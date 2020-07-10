package com.stivenaguino.service;

import java.util.List;

public interface IService<T> {

    void create(T t);

    void edit(T t);

    void destroy(T t);

    List<T> findAll();

    T findById(T t);
}
