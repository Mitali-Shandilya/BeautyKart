package com.example.productServices.mapper;

import com.example.productServices.dto.request.ProductRequestDto;
import com.example.productServices.dto.response.BrandResponseDto;
import com.example.productServices.dto.response.CategoryResponseDto;
import com.example.productServices.dto.response.ProductResponseDto;
import com.example.productServices.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    //entity to dto
    public ProductResponseDto toDto(Product product,
                                    CategoryResponseDto category,
                                    BrandResponseDto brand){
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getImageUrl(),
                product.getActive(),
                category,
                brand
        );
    }

    public ProductResponseDto toDto(Product product){
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getImageUrl(),
                product.getActive(),
                null,
                null
        );
    }

    //dto to entity
    public Product toEntity(ProductRequestDto request){
        Product product=new Product();
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setQuantity(request.quantity());
        product.setImageUrl(request.imageUrl());
        product.setCategoryId(request.categoryId());
        product.setBrandId(request.brandId());
        product.setActive(request.active());
        return product;
    }
}
