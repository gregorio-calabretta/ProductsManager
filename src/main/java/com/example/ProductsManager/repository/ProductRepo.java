package com.example.ProductsManager.repository;

import com.example.ProductsManager.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Products, Long> {
    List<Products> findAll();
    Products save(Products product);
    Optional<Products> findByName(String name);
}
