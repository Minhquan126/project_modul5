package com.example.spring_security_demotiepchonho.ra.service;

import java.util.Optional;

public interface IGenericService<T, E> {
    Iterable<T> findAll();
    boolean save( T t);
    boolean delete(E e);
    T findById(E e);
}
