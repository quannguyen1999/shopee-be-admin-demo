package com.shopee.shopeebeadmindemo.services;

import com.shopee.shopeebeadmindemo.models.requests.ProductRequestDto;
import com.shopee.shopeebeadmindemo.models.responses.ProductResponseDto;

import java.util.List;

public interface ProductService {
    void createProduct(ProductRequestDto productRequestDto);

    List<ProductResponseDto> getProducts();
}
