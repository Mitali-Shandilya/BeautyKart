package com.example.category_services.mapper;

import com.example.category_services.dto.request.CategoryRequestDto;
import com.example.category_services.dto.response.CategoryResponseDto;
import com.example.category_services.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    //entity to dto
    public CategoryResponseDto toDto(Category category){
        return new CategoryResponseDto(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getActive()
        );
    }

    //dto to entity
    public Category toEntity(CategoryRequestDto request){
        Category category=new Category();
        category.setName(request.name());
        category.setDescription(request.description());
        category.setActive(request.active());
        return category;
    }
}
