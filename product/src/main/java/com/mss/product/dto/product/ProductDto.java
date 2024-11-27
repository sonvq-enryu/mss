package com.mss.product.dto.product;

import lombok.Data;

import java.util.UUID;

public class ProductDto {

    @Data
    public static class PostProductRequest {
        private String name;
        private String description;
        private Double price;
        private Integer stock;
    }

    @Data
    public static class ProductVm {
        private UUID id;
        private String name;
        private String description;
        private double price;
        private int stock;
    }
}
