package com.shopee.shopeebeadmindemo.services.impls;

import com.shopee.shopeebeadmindemo.models.requests.ProductRequestDto;
import com.shopee.shopeebeadmindemo.models.responses.ProductResponseDto;
import com.shopee.shopeebeadmindemo.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImpl implements ProductService {

    @Override
    public void createProduct(ProductRequestDto productRequestDto) {

    }

    @Override
    public List<ProductResponseDto> getProducts() {
        return null;
    }
    
}
