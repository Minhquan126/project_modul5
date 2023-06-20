package com.example.spring_security_demotiepchonho.ra.service;

import com.example.spring_security_demotiepchonho.ra.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService extends IGenericService<Product,Long>{
    List<Product> findProductByName(String name);
    List<Product> findProductByCatalog_Id(Long id);
    Page<Product> getAllByStar(Pageable pageable);
    void changeStatusProduct(Long id);
    void updateStar(Long id);
    void setQuantityProduct(int quantity,Long id);
}
