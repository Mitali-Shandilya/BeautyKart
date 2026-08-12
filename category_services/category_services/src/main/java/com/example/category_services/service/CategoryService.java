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
        if(newCategory.getActive() == null){
            newCategory.setActive(true);
        }
        Category savedCategory=categoryRespository.save(newCategory);
        return categoryMapper.toDto(savedCategory);
    }

    //getting all categories
    public List<CategoryResponseDto> getAllCategories(){
        return categoryRespository.findByActiveTrue().stream().map(categoryMapper::toDto).toList();
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
        existingCategory.setActive(false);
        categoryRespository.save(existingCategory);
    }

    //get by name
    public CategoryResponseDto findByName(String name){
        Category existingCategory=categoryRespository.findByNameIgnoreCase(name).orElseThrow(()->new NotFoundException("category "+name+" not found!"));
        return categoryMapper.toDto(existingCategory);
    }

    //get all categories for admin
    public List<CategoryResponseDto> getAllCategoryForAdmin() {

        return categoryRespository.findAll()
                .stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    //change status to active   
    public void activateCategory(Long id){

    Category category =categoryRespository.findById(id)
            .orElseThrow(() ->
                    new NotFoundException(
                            "Category with id " + id + " not found!"
                    ));

    category.setActive(true);

    categoryRespository.save(category);
}
}
