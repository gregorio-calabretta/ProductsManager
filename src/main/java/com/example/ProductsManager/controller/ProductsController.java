package com.example.ProductsManager.controller;

import com.example.ProductsManager.dtos.ProductRequest;
import com.example.ProductsManager.dtos.ProductResponse;
import com.example.ProductsManager.exception.ProductException;
import com.example.ProductsManager.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/products")
public class ProductsController {

    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
public ResponseEntity<List<ProductResponse>> getAll(){
    return ResponseEntity.ok(productService.getAll());
}


    @PostMapping
public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest product) throws ProductException {
    return ResponseEntity.ok(productService.createProduct(product));
}

    @PutMapping("/add")
    public ResponseEntity<ProductResponse> increaseProduct(@RequestBody ProductRequest productRequest) throws ProductException {
        return ResponseEntity.ok(productService.addProduct(productRequest));
    }

    @PutMapping("/remove")
    public ResponseEntity<ProductResponse> decreaseProduct(@RequestBody ProductRequest productRequest) throws ProductException {
        return ResponseEntity.ok(productService.removeProduct(productRequest));
    }

}
