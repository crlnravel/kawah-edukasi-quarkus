package com.kawahedukasi.service;

import com.kawahedukasi.model.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ProductService {

    @Transactional
    public void updateAndPersist(Product oldProduct, Product updatedProduct) {
        oldProduct.setName( updatedProduct.getName());
        oldProduct.setDescription( updatedProduct.getDescription());
        oldProduct.setType( updatedProduct.getType());
        oldProduct.setCount( updatedProduct.getCount());
        oldProduct.setPrice( updatedProduct.getPrice());

        oldProduct.persist();
    }
}
