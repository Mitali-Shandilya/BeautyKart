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
        return productRepository.findByActiveTrue()
                .stream()
                .map(product -> {

                        CategoryResponseDto category =
                                categoryClient.getById(
                                        product.getCategoryId()
                                );

                        BrandResponseDto brand =
                                brandClient.getById(
                                        product.getBrandId()
                                );

                        return productMapper.toDto(
                                product,
                                category,
                                brand
                        );
                })
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
        existing.setActive(false);

        productRepository.save(existing);
    }

    // search by name
    public ProductResponseDto searchByName(String name) {

        Product existing = productRepository.findByNameIgnoreCase(name)
                .orElseThrow(() ->
                        new NotFoundException(
                                "Product " + name + " not found!"
                        ));

                CategoryResponseDto category =
                        categoryClient.getById(
                                existing.getCategoryId()
                        );

                BrandResponseDto brand =
                        brandClient.getById(
                                existing.getBrandId()
                        );

                return productMapper.toDto(
                        existing,
                        category,
                        brand
                );
        }

    // get by brand id
    public List<ProductResponseDto> getByBrandId(Long brandId) {

        List<Product> products =
                productRepository.findByBrandIdAndActiveTrue(brandId);

        if (products.isEmpty()) {
            throw new NotFoundException(
                    "No products found for brand id " + brandId
            );
        }

        return products.stream()
        .map(product -> {

            CategoryResponseDto category =
                    categoryClient.getById(
                            product.getCategoryId()
                    );

            BrandResponseDto brand =
                    brandClient.getById(
                            product.getBrandId()
                    );

            return productMapper.toDto(
                    product,
                    category,
                    brand
            );
        })
        .toList();
    }

    // get by category id
    public List<ProductResponseDto> getByCategoryId(Long categoryId) {

        List<Product> products =
                productRepository.findByCategoryIdAndActiveTrue(categoryId);

        if (products.isEmpty()) {
            throw new NotFoundException(
                    "No products found for category id " + categoryId
            );
        }

        return products.stream()
        .map(product -> {

            CategoryResponseDto category =
                    categoryClient.getById(
                            product.getCategoryId()
                    );

            BrandResponseDto brand =
                    brandClient.getById(
                            product.getBrandId()
                    );

            return productMapper.toDto(
                    product,
                    category,
                    brand
            );
        })
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

    //get all for admin
    public List<ProductResponseDto> getAllProductsForAdmin() {
        return productRepository.findAll()
                .stream()
                .map(product -> {

                        CategoryResponseDto category =
                                categoryClient.getById(
                                        product.getCategoryId()
                                );

                        BrandResponseDto brand =
                                brandClient.getById(
                                        product.getBrandId()
                                );

                        return productMapper.toDto(
                                product,
                                category,
                                brand
                        );
                })
                .toList();
        }

        //change status to active by admin
        public void activateProduct(Long id){

                Product product = productRepository.findById(id)
                        .orElseThrow(() ->
                                new NotFoundException(
                                        "Product with id " + id + " not found!"
                                ));

                product.setActive(true);

                productRepository.save(product);
        }

        //reduce stock
        public void reduceStock(Long productId, Integer quantity) {
                Product product = productRepository.findById(productId)
                        .orElseThrow(() ->
                                new NotFoundException(
                                        "Product not found with id " + productId
                                ));

                if (product.getQuantity() < quantity) {

                        throw new IllegalArgumentException(
                                "Insufficient stock available"
                        );
                }

                product.setQuantity(
                        product.getQuantity() - quantity
                );

                productRepository.save(product);
        }
}