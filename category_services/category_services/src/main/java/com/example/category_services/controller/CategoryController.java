package com.example.category_services.controller;

import com.example.category_services.dto.request.CategoryRequestDto;
import com.example.category_services.dto.response.CategoryResponseDto;
import com.example.category_services.entity.Category;
import com.example.category_services.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //adding a category
    @PostMapping
    public CategoryResponseDto create(@Valid @RequestBody CategoryRequestDto requestDto){
        return categoryService.addCategory(requestDto);
    }

    //getting all categories
    @GetMapping
    public List<CategoryResponseDto> getAll(){
        List<CategoryResponseDto> listOfResponse=categoryService.getAllCategories();
        return listOfResponse;
    }

    //get by id
    @GetMapping("/{id}")
    public CategoryResponseDto getById(@PathVariable Long id){
        return categoryService.findById(id);
    }

    //update by id
    @PutMapping("/{id}")
    public CategoryResponseDto updateById(@PathVariable Long id, @Valid @RequestBody CategoryRequestDto requestdto){
        CategoryResponseDto response=categoryService.updateById(id,requestdto);
        return response;
    }

    //delete by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //get by name
    @GetMapping("/by-name/{name}")
    public CategoryResponseDto getByName(@PathVariable String name){
        return categoryService.findByName(name);
    }

    //get all for admin
    @GetMapping("/admin")
    public List<CategoryResponseDto> getAllCategoryForAdmin(){
        return categoryService.getAllCategoryForAdmin();
    }

    @PutMapping("/admin/{id}/activate")
    public ResponseEntity<Void> activateCategory(
            @PathVariable Long id) {

        categoryService.activateCategory(id);

        return ResponseEntity.ok().build();
    }
}
