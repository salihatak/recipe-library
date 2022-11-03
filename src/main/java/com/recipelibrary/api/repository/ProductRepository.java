package com.recipelibrary.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recipelibrary.api.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
