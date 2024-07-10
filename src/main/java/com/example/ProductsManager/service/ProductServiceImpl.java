package com.example.ProductsManager.service;

import com.example.ProductsManager.dtos.ProductRequest;
import com.example.ProductsManager.dtos.ProductResponse;
import com.example.ProductsManager.exception.ProductException;
import com.example.ProductsManager.mapper.Mapper;
import com.example.ProductsManager.model.Products;
import com.example.ProductsManager.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepo productRepo;
    private final Mapper<Products, ProductResponse> mapper;


    public ProductServiceImpl(ProductRepo productRepo, Mapper<Products, ProductResponse> mapper) {
        this.productRepo = productRepo;
        this.mapper = mapper;
    }

    @Override
    public List<ProductResponse> getAll() {
    List<Products> productsList = productRepo.findAll();
    return mapper.mapAll(productsList);
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) throws ProductException {
        if(productRepo.findByName(productRequest.getName()).isPresent())
        {
            throw new ProductException("Product is already present");
        }
      Products products = Products.builder()
              .name(productRequest.getName())
              .quantity(productRequest.getQuantity())
              .build();
      productRepo.save(products);
      return mapper.map(products);
    }

    @Override
    public ProductResponse addProduct(ProductRequest productRequest) throws ProductException {
        Products products = productRepo.findByName(productRequest.getName()).orElseThrow(() ->  new ProductException("Product not found"));
        if((products.getQuantity() + productRequest.getQuantity()) < 100){
            products.setQuantity(products.getQuantity() + productRequest.getQuantity());
            productRepo.save(products);
            return  mapper.map(products);
        }
        else {
            throw new ProductException("Quantity exceed 100");
        }

    }

    @Override
    public ProductResponse removeProduct(ProductRequest productRequest) throws ProductException {
        Products products = productRepo.findByName(productRequest.getName()).orElseThrow(() ->  new ProductException("Product not found"));
        if((products.getQuantity() - productRequest.getQuantity()) > 0){
            products.setQuantity(products.getQuantity() - productRequest.getQuantity());
            productRepo.save(products);
            return  mapper.map(products);
        }
        else {
            throw new ProductException("Quantity is not available");
        }
    }


}
