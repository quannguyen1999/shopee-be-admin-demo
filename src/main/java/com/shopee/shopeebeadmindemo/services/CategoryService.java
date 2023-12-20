package com.shopee.shopeebeadmindemo.services;

import com.shopee.shopeebeadmindemo.models.requests.CategoryRequestDto;
import com.shopee.shopeebeadmindemo.models.responses.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    void createCategory(CategoryRequestDto categoryRequestDto);

    List<CategoryResponseDto> getCategories();

}
