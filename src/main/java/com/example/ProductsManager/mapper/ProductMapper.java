package com.example.ProductsManager.mapper;

import com.example.ProductsManager.dtos.ProductResponse;
import com.example.ProductsManager.model.Products;
import org.springframework.stereotype.Component;


@Component
public class ProductMapper implements Mapper<Products, ProductResponse> {
    @Override
    public ProductResponse map(Products product) {
        return new ProductResponse(product.getId(), product.getName(), product.getQuantity());
    }

}
