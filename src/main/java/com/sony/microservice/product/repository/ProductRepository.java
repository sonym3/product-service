package com.sony.microservice.product.repository;

import com.sony.microservice.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
//this is our repo
public interface ProductRepository extends MongoRepository<Product, String> {
}
