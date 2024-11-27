package com.mss.product.service;

import com.mss.product.dto.product.ProductDto;
import com.mss.product.mapper.ProductMapper;
import com.mss.product.repository.postgres.ProductRepository;
import com.mss.product.repository.redis.impl.RedisCacheRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final RedisCacheRepository redisCacheRepository;
    private final ProductMapper productMapper;

    @Transactional
    public void create(ProductDto.PostProductRequest request) {
        var product = productMapper.toEntity(request);
        productRepository.save(product);
    }

    @Transactional
    public List<ProductDto.ProductVm> findAll() {
        return productRepository.findAll().stream().map(productMapper::toVm).toList();
    }
}
