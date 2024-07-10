package com.example.ProductsManager.service;

import com.example.ProductsManager.dtos.ProductRequest;
import com.example.ProductsManager.dtos.ProductResponse;
import com.example.ProductsManager.exception.ProductException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
List<ProductResponse> getAll();
ProductResponse createProduct(ProductRequest productRequest) throws ProductException;
ProductResponse addProduct(ProductRequest productRequest) throws ProductException;

ProductResponse removeProduct(ProductRequest productRequest) throws ProductException;
}
