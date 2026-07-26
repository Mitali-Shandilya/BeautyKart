package com.example.category_services.service;

import com.example.category_services.dto.request.CategoryRequestDto;
import com.example.category_services.dto.response.CategoryResponseDto;
import com.example.category_services.entity.Category;
import com.example.category_services.exception.NotFoundException;
import com.example.category_services.mapper.CategoryMapper;
import com.example.category_services.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRespository;
    private final CategoryMapper categoryMapper;

    //adding a category
    public CategoryResponseDto addCategory(CategoryRequestDto requestDto){
        Category newCategory=categoryMapper.toEntity(requestDto);
        Category savedCategory=categoryRespository.save(newCategory);
        return categoryMapper.toDto(savedCategory);
    }

    //getting all categories
    public List<CategoryResponseDto> getAllCategories(){
        return categoryRespository.findAll().stream().map(categoryMapper::toDto).toList();
    }

    //getting by id
    public CategoryResponseDto findById(Long id){
        Category existingCategory=categoryRespository.findById(id).orElseThrow(()->new NotFoundException("category with id "+id+" not found!"));
        return categoryMapper.toDto(existingCategory);
    }

    //update by id
    public CategoryResponseDto updateById(Long id, CategoryRequestDto requestDto){
        Category existingCategory=categoryRespository.findById(id).orElseThrow(()->new NotFoundException("category with id "+id+" not found!"));
        existingCategory.setName(requestDto.name());
        existingCategory.setDescription(requestDto.description());
        existingCategory.setActive(requestDto.active());
        Category updatedCategory=categoryRespository.save(existingCategory);
        return categoryMapper.toDto(updatedCategory);
    }

    //delete by id
    public void deleteById(Long id){
        Category existingCategory=categoryRespository.findById(id).orElseThrow(()-> new NotFoundException("category with id "+id+" not found!"));
        categoryRespository.delete(existingCategory);
    }

    //get by name
    public CategoryResponseDto findByName(String name){
        Category existingCategory=categoryRespository.findByNameIgnoreCase(name).orElseThrow(()->new NotFoundException("category "+name+" not found!"));
        return categoryMapper.toDto(existingCategory);
    }
}
