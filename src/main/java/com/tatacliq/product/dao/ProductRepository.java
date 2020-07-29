package com.tatacliq.product.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tatacliq.product.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	

}
