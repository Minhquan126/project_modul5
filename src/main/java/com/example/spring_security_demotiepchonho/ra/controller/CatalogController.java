package com.example.spring_security_demotiepchonho.ra.controller;

import com.example.spring_security_demotiepchonho.ra.dto.request.CatalogDTO;
import com.example.spring_security_demotiepchonho.ra.dto.response.ResponseMessage;
import com.example.spring_security_demotiepchonho.ra.model.Catalog;
import com.example.spring_security_demotiepchonho.ra.service.ICatalogService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@Builder
@RequestMapping("api/v4/catalogs")
public class CatalogController {
    @Autowired
    ICatalogService catalogService;
    @GetMapping
    public ResponseEntity<List<Catalog>> getAll(){
      List<Catalog> list = catalogService.getAll();
      if (list.isEmpty()){
          return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<ResponseMessage> createCatalog(@RequestBody CatalogDTO catalogDTO){
//        String fileName = image.getOriginalFilename();
        Catalog catalog = Catalog.builder().
                name(catalogDTO.getName()).
                description(catalogDTO.getDescription()).
                country(catalogDTO.getCountry()).
                image(catalogDTO.getImage()).
                status(true).
                build();
        catalogService.save(catalog);
        return new ResponseEntity<>(new ResponseMessage("create is success!"),HttpStatus.OK);
    }
    @PutMapping("update")
    public ResponseEntity<Catalog> updateCatalog(@RequestBody Catalog catalog){
        Catalog cata = catalogService.findById(catalog.getId());
        if (cata == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catalogService.save(catalog);
        return new ResponseEntity<>(catalog,HttpStatus.OK);
    }
@PutMapping("delete/{id}")
    public ResponseEntity<ResponseMessage> deleteCatalog(@PathVariable("id") Long id){
         catalogService.changeStatusCatalog(id);
         return new ResponseEntity<>(new ResponseMessage("delete is success!"),HttpStatus.OK);
}
}
