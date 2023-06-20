package com.example.spring_security_demotiepchonho.ra.service;

import com.example.spring_security_demotiepchonho.ra.model.Catalog;

import java.util.List;

public interface ICatalogService extends IGenericService<Catalog,Long>{
    List<Catalog> getAll();
    void changeStatusCatalog(Long id);
}
