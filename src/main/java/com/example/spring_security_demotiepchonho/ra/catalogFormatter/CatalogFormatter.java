package com.example.spring_security_demotiepchonho.ra.catalogFormatter;

import com.example.spring_security_demotiepchonho.ra.model.Catalog;
import com.example.spring_security_demotiepchonho.ra.service.ICatalogService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Component
public class CatalogFormatter implements Formatter<Catalog> {
private ICatalogService catalogService;
public CatalogFormatter(ICatalogService catalogService){
    this.catalogService = catalogService;
}
    @Override
    public Catalog parse(String text, Locale locale) throws ParseException {
        Optional<Catalog> optionalCatalog = Optional.ofNullable(catalogService.findById(Long.valueOf(text)));
        return optionalCatalog.orElse(null);
    }

    @Override
    public String print(Catalog object, Locale locale) {
        return null;
    }
}
