package com.sony.microservice.product.service;

import com.sony.microservice.product.dto.ProductRequest;
import com.sony.microservice.product.dto.ProductResponse;
import com.sony.microservice.product.model.Product;
import com.sony.microservice.product.repository.ProductRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    public ProductResponse createProduct(ProductRequest productRequest){
        Product product = new Product(productRequest.name(), productRequest.description(), productRequest.price());
        log.info("Product object created");
        Product fromDatabase = productRepository.save(product);
        log.info("Product stored in - database " + fromDatabase.toString());

        ProductResponse p = new ProductResponse(fromDatabase.getId(), fromDatabase.getName(), fromDatabase.getDescription(), fromDatabase.getPrice());

        return p;
    }

    public List<ProductResponse> getProductFromDatabase() {
        log.info("GET method called");
        return productRepository.findAll()
                .stream().map(product ->
                        new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice()))
                .toList();
    }
}
