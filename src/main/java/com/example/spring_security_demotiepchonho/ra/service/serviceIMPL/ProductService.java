package com.example.spring_security_demotiepchonho.ra.service.serviceIMPL;

import com.example.spring_security_demotiepchonho.ra.model.Product;
import com.example.spring_security_demotiepchonho.ra.repository.ProductRepository;
import com.example.spring_security_demotiepchonho.ra.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    ProductRepository productRepository;
    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public boolean save(Product product) {
        productRepository.save(product);
        return true;
    }

    @Override
    public boolean delete(Long aLong) {
        productRepository.deleteById(aLong);
        return true;
    }

    @Override
    public Product findById(Long aLong) {
        return productRepository.findById(aLong).get();
    }

    @Override
    public List<Product> findProductByName(String name) {
        return productRepository.findProductByNameIgnoreCase(name);
    }

    @Override
    public List<Product> findProductByCatalog_Id(Long id) {
        return productRepository.findProductByCatalog_Id(id);
    }

    @Override
    public Page<Product> getAllByStar(Pageable pageable) {
pageable = PageRequest.of(pageable.getPageNumber(), 6);
        return productRepository.getAllByStar(pageable);
    }

    @Override
    public void changeStatusProduct(Long id) {
        productRepository.changeStatusProduct(id);
    }

    @Override
    public void updateStar(Long id) {
        productRepository.updateStar(id);
    }

    @Override
    public void setQuantityProduct(int quantity, Long id) {
        productRepository.setQuantityProduct(quantity,id);
    }
}
