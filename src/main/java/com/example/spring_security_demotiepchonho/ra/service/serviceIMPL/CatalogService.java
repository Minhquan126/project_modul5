package com.example.spring_security_demotiepchonho.ra.service.serviceIMPL;

import com.example.spring_security_demotiepchonho.ra.model.Catalog;
import com.example.spring_security_demotiepchonho.ra.repository.CatalogRepository;
import com.example.spring_security_demotiepchonho.ra.service.ICatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService implements ICatalogService {
    @Autowired
    CatalogRepository catalogRepository;
    @Override
    public Iterable<Catalog> findAll() {
        return catalogRepository.findAll();
    }

    @Override
    public boolean save(Catalog catalog) {
        catalogRepository.save(catalog);
        return true;
    }

    @Override
    public boolean delete(Long aLong) {
        catalogRepository.deleteById(aLong);
        return true;
    }

    @Override
    public Catalog findById(Long aLong) {
        return catalogRepository.findById(aLong).get();
    }

    @Override
    public List<Catalog> getAll() {
        return catalogRepository.getAll();
    }

    @Override
    public void changeStatusCatalog(Long id) {
        catalogRepository.changeStatusCatalog(id);
    }
}
