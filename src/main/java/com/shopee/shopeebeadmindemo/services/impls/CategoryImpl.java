package com.shopee.shopeebeadmindemo.services.impls;

import com.shopee.shopeebeadmindemo.models.requests.CategoryRequestDto;
import com.shopee.shopeebeadmindemo.models.responses.CategoryResponseDto;
import com.shopee.shopeebeadmindemo.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryImpl implements CategoryService {

    @Override
    public void createCategory(CategoryRequestDto categoryRequestDto) {

    }

    @Override
    public List<CategoryResponseDto> getCategories() {
        return null;
    }
    
}
