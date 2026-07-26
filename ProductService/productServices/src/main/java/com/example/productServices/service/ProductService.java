package com.example.productServices.service;

import com.example.productServices.client.BrandClient;
import com.example.productServices.client.CategoryClient;
import com.example.productServices.dto.request.ProductRequestDto;
import com.example.productServices.dto.response.BrandResponseDto;
import com.example.productServices.dto.response.CategoryResponseDto;
import com.example.productServices.dto.response.ProductResponseDto;
import com.example.productServices.entity.Product;
import com.example.productServices.exception.NotFoundException;
import com.example.productServices.mapper.ProductMapper;
import com.example.productServices.repository.ProductRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryClient categoryClient;
    private final BrandClient brandClient;

    // add product
    public ProductResponseDto addProduct(ProductRequestDto requestDto) {

        validateCategoryAndBrand(
                requestDto.categoryId(),
                requestDto.brandId()
        );

        Product newProduct = productMapper.toEntity(requestDto);
        Product savedProduct = productRepository.save(newProduct);

        CategoryResponseDto category =
                categoryClient.getById(savedProduct.getCategoryId());

        BrandResponseDto brand =
                brandClient.getById(savedProduct.getBrandId());

        return productMapper.toDto(
                savedProduct,
                category,
                brand
        );
    }

    // get all products
    public List<ProductResponseDto> getAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    // get by id
    public ProductResponseDto findById(Long id) {

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                "Product with id " + id + " not found!"
                        ));

        CategoryResponseDto category =
                categoryClient.getById(existingProduct.getCategoryId());

        BrandResponseDto brand =
                brandClient.getById(existingProduct.getBrandId());

        return productMapper.toDto(
                existingProduct,
                category,
                brand
        );
    }

    // update by id
    public ProductResponseDto updateProductById(
            Long id,
            ProductRequestDto request) {

        validateCategoryAndBrand(
                request.categoryId(),
                request.brandId()
        );

        Product existing = productRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                "Product with id " + id + " not found!"
                        ));

        existing.setName(request.name());
        existing.setDescription(request.description());
        existing.setPrice(request.price());
        existing.setQuantity(request.quantity());
        existing.setImageUrl(request.imageUrl());
        existing.setCategoryId(request.categoryId());
        existing.setBrandId(request.brandId());
        existing.setActive(request.active());

        Product updatedProduct =
                productRepository.save(existing);

        CategoryResponseDto category =
                categoryClient.getById(updatedProduct.getCategoryId());

        BrandResponseDto brand =
                brandClient.getById(updatedProduct.getBrandId());

        return productMapper.toDto(
                updatedProduct,
                category,
                brand
        );
    }

    // delete by id
    public void deleteById(Long id) {

        Product existing = productRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                "Product with id " + id + " not found!"
                        ));

        productRepository.delete(existing);
    }

    // search by name
    public ProductResponseDto searchByName(String name) {

        Product existing = productRepository.findByNameIgnoreCase(name)
                .orElseThrow(() ->
                        new NotFoundException(
                                "Product " + name + " not found!"
                        ));

        return productMapper.toDto(existing);
    }

    // get by brand id
    public List<ProductResponseDto> getByBrandId(Long brandId) {

        List<Product> products =
                productRepository.findByBrandId(brandId);

        if (products.isEmpty()) {
            throw new NotFoundException(
                    "No products found for brand id " + brandId
            );
        }

        return products.stream()
                .map(productMapper::toDto)
                .toList();
    }

    // get by category id
    public List<ProductResponseDto> getByCategoryId(Long categoryId) {

        List<Product> products =
                productRepository.findByCategoryId(categoryId);

        if (products.isEmpty()) {
            throw new NotFoundException(
                    "No products found for category id " + categoryId
            );
        }

        return products.stream()
                .map(productMapper::toDto)
                .toList();
    }

    // helper method to validate category and brand exists
    private void validateCategoryAndBrand(
            Long categoryId,
            Long brandId) {

        try {
            categoryClient.getById(categoryId);
        }
        catch (FeignException.NotFound ex) {
            throw new NotFoundException(
                    "Category with id " + categoryId + " not found!"
            );
        }

        try {
            brandClient.getById(brandId);
        }
        catch (FeignException.NotFound ex) {
            throw new NotFoundException(
                    "Brand with id " + brandId + " not found!"
            );
        }
    }
}