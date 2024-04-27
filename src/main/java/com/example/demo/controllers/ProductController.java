package com.example.demo.controllers;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Product;
import com.example.demo.models.ResponseObject;
import com.example.demo.repositories.ProductRepository;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@RestController // nhận dạng đây là 1 controller
@RequestMapping(path = "/api/v1/Products") // đường dẫn của controller
public class ProductController {

    // DI: Dependency Injection
    @Autowired // đối tượng được tạo 1 lần khi repo được tạo, khá giống singleton
    private ProductRepository repository;

    // this request is: http://localhost:8080/api/v1/Products
    @GetMapping("")
    List<Product> getAllProducts() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    // respone: data, status, message
    ResponseEntity<ResponseObject> findById(@PathVariable long id) {
        Optional<Product> foundProduct = repository.findById(id);
        if (foundProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(foundProduct, "find success", "ok"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("", "Not found Product with id: " + id, "fail"));
        }
    }

    // insert product
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct) {
        // 2 products must not have same name
        List<Product> findByProductName = repository.findByProductName(newProduct.getProductName().trim());
        if (findByProductName.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("", "Product name is existed", "failed"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(repository.save(newProduct), "insert success", "ok"));
    }

    // update product by id
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable long id) {
        Product updatedProducts = repository.findById(id)
                .map(product -> {
                    product.setProductName(newProduct.getProductName());
                    product.setProductYear(newProduct.getProductYear());
                    product.setPrice(newProduct.getPrice());
                    product.setUrl(newProduct.getUrl());
                    repository.save(product);
                    return repository.save(product);
                }).orElseGet(() -> {
                    newProduct.setId(id);
                    return repository.save(newProduct);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(updatedProducts, "update success", "ok"));
    }

    // delete product
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable long id) {
        boolean existed = repository.existsById(id);
        if (existed) {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("", "delete success", "ok"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("", "Not found Product with id: " + id, "fail"));
    }
}
