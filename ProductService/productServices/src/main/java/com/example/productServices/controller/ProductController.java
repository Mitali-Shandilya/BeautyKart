package com.example.productServices.controller;

import com.example.productServices.dto.request.ProductRequestDto;
import com.example.productServices.dto.response.ProductResponseDto;
import com.example.productServices.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    //adding a product
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ProductResponseDto create(@Valid @RequestBody ProductRequestDto requestDto){
        return productService.addProduct(requestDto);
    }

    //get all products
    @GetMapping
    public List<ProductResponseDto> getAll(){
        List<ProductResponseDto> listOfProducts=productService.getAllProducts();
        return listOfProducts;
    }

    //get all for admin
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public List<ProductResponseDto> getAllProductsForAdmin(){
        return productService.getAllProductsForAdmin();
    }
    
    //get all products by id
    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id){
        return productService.findById(id);
    }

    //update product by id
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ProductResponseDto updatingProductById(@PathVariable Long id,@Valid @RequestBody ProductRequestDto requestDto){
        ProductResponseDto response=productService.updateProductById(id,requestDto);
        return response;
    }

    //delete product by id
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    //get all by brandId
    @GetMapping("/brand/{brandId}")
    public List<ProductResponseDto> getByBrandId(@PathVariable Long brandId){
        List<ProductResponseDto> listOfProducts=productService.getByBrandId(brandId);
        return listOfProducts;
    }

    //get all by categoryId 
    @GetMapping("/category/{categoryId}")
    public List<ProductResponseDto> getByCategoryId(@PathVariable Long categoryId){
        List<ProductResponseDto> listOfProducts=productService.getByCategoryId(categoryId);
        return listOfProducts;
    }

    //change status sto active by admin
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/{id}/activate")
    public ResponseEntity<Void> activateProduct(@PathVariable Long id){
        productService.activateProduct(id);
        return ResponseEntity.ok().build();
    }

    //reduce stock
    @PutMapping("/{id}/reduce-stock")
    public void reduceStock(
            @PathVariable Long id,
            @RequestParam Integer quantity
    ) {
        productService.reduceStock(id, quantity);
    }

    //get multiple product with same name
    @GetMapping("/search")
public List<ProductResponseDto> getByName(
        @RequestParam String name
){
    return productService.searchByName(name);
}
}
