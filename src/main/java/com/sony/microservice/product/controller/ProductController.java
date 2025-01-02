package com.sony.microservice.product.controller;

import com.sony.microservice.product.dto.ProductRequest;
import com.sony.microservice.product.dto.ProductResponse;
import com.sony.microservice.product.model.Product;
import com.sony.microservice.product.service.ProductService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/product")
@Validated
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @PostMapping()
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest){
        log.info("POST method called");
        ProductResponse fromService = productService.createProduct(productRequest);
        return new ResponseEntity<ProductResponse>(fromService,HttpStatus.CREATED);

    }

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> getOurProduct(){
        log.info("GET method called");

        List<ProductResponse> fromDatabase = productService.getProductFromDatabase();
        return ResponseEntity.ok(fromDatabase);
    }
}
