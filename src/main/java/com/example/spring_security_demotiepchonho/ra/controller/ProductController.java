package com.example.spring_security_demotiepchonho.ra.controller;

import com.example.spring_security_demotiepchonho.ra.dto.request.ProductDTO;
import com.example.spring_security_demotiepchonho.ra.dto.response.ResponseMessage;
import com.example.spring_security_demotiepchonho.ra.model.Product;
import com.example.spring_security_demotiepchonho.ra.service.ICatalogService;
import com.example.spring_security_demotiepchonho.ra.service.IProductService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v4/products")
public class ProductController {
    @Autowired
    IProductService productService;
@Autowired
    ICatalogService catalogService;
    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable) {
        Page<Product> list = productService.getAllByStar(pageable);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PostMapping("create")
    public ResponseEntity<ResponseMessage> create(@RequestBody ProductDTO productDTO){
//        String fileName = image.getOriginalFilename();
        Product product = Product.builder().
                name(productDTO.getName()).
                price(productDTO.getPrice()).
                quantity(productDTO.getQuantity()).
                description(productDTO.getDescription()).
                image(productDTO.getImage()).
                catalog(catalogService.findById(productDTO.getCatalogId())).
                status(true).
                type(true).
                build();
        productService.save(product);

       return new ResponseEntity<>(new ResponseMessage("Create success"),HttpStatus.OK);
    }
    @PutMapping("update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product){
        Product prd = productService.findById(product.getId());
        if (prd == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.save(product);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }
    @PutMapping("delete/{id}")
    public ResponseEntity<ResponseMessage> deleteProduct(@PathVariable("id") Long id){
        productService.changeStatusProduct(id);
        return new ResponseEntity<>(new ResponseMessage("delete is success!"),HttpStatus.OK);
    }
    @GetMapping("byName/{name}")
    public ResponseEntity<List<Product>> searchByName(@PathVariable("name") String name){
        List<Product> list = productService.findProductByName(name);
        if (list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    @GetMapping("byCatalog/{id}")
    public ResponseEntity<List<Product>> searchByCatalog(@PathVariable("id") Long id){
        List<Product> list = productService.findProductByCatalog_Id(id);
        if (list.isEmpty()){
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    @PutMapping("star/{id}")
    public ResponseEntity<ResponseMessage> updateStar(@PathVariable("id") Long id){
        productService.updateStar(id);
        return new ResponseEntity<>(new ResponseMessage("update is success!"),HttpStatus.OK);
    }
}
