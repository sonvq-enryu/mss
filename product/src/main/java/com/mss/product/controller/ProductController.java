package com.mss.product.controller;

import com.mss.product.dto.product.ProductDto;
import com.mss.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody ProductDto.PostProductRequest request) {
        productService.create(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto.ProductVm>> getProducts() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }
}
