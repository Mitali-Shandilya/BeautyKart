package com.example.Brand_services.mapper;

import com.example.Brand_services.dto.request.BrandRequestDto;
import com.example.Brand_services.dto.response.BrandResponseDto;
import com.example.Brand_services.entity.Brand;
import org.springframework.stereotype.Component;

@Component
public class BrandMapper {
    //entity to dto
    public BrandResponseDto toDto(Brand brand){
        return new BrandResponseDto(
                brand.getId(),
                brand.getName(),
                brand.getCountry(),
                brand.getDescription(),
                brand.getActive()
        );
    }

    //dto to entity
    public Brand toEntity(BrandRequestDto request){
        Brand brand=new Brand();
        brand.setName(request.name());
        brand.setDescription(request.description());
        brand.setActive(request.active());
        brand.setCountry(request.country());
        return brand;
    }
}
