package com.olik.rentalSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.olik.rentalSystem.Entity.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
