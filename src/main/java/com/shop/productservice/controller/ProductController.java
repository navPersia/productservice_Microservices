package com.shop.productservice.controller;

import com.shop.productservice.model.Product;
import com.shop.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    public void fillDb(){
        if (productRepository.count()==0){
            productRepository.save(new Product("Laptop", "https://picsum.photos/200/300", "Best laptop ever made since 1995", Boolean.TRUE,"6179c6e5fd092603c4b986a8"));
            productRepository.save(new Product("CdPlayer", "https://picsum.photos/200/300", "Best CdPlayer ever made", Boolean.TRUE,"6179c6e5fd092603c4b986aa"));
            productRepository.save(new Product("Glass", "https://picsum.photos/200/300", "Best Glass ever made", Boolean.TRUE,"6179c6e5fd092603c4b986aa"));
        }
    }

    @GetMapping("/products")
    public List<Product> products (){
        return productRepository.findAll();
    }

    @GetMapping("/products/product/{id}")
    public Product getProduct(@PathVariable String id){
        return productRepository.findById(id).get();
    }

    @PostMapping("/products/product")
    public Product newProduct(@RequestBody Product poduct){
        productRepository.save(poduct);
        return poduct;
    }

    @DeleteMapping("/products/product/{id}")
    public ResponseEntity delProduct(@PathVariable String id){
        Product product = productRepository.findById(id).get();
        if (product != null){
            productRepository.delete(product);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/products/product/{id}")
    public Product updProduct(@PathVariable String id, @RequestBody Product updatedProduct){
        Product retProduct = productRepository.findById(id).get();

        retProduct.setActive(updatedProduct.getActive());
        retProduct.setDescription(updatedProduct.getDescription());
        retProduct.setImgUrl(updatedProduct.getImgUrl());
        retProduct.setName(updatedProduct.getName());

        productRepository.save(retProduct);
        return retProduct;
    }
}
