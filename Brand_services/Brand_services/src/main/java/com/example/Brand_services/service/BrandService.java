package com.example.Brand_services.service;

import com.example.Brand_services.dto.request.BrandRequestDto;
import com.example.Brand_services.dto.response.BrandResponseDto;
import com.example.Brand_services.entity.Brand;
import com.example.Brand_services.exception.NotFoundException;
import com.example.Brand_services.mapper.BrandMapper;
import com.example.Brand_services.repository.BrandRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {


    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    //adding a brand
    public BrandResponseDto addBrand(BrandRequestDto requestDto){
        Brand newBrand=brandMapper.toEntity(requestDto);
        Brand savedBrand=brandRepository.save(newBrand);
        return brandMapper.toDto(savedBrand);
    }

    //get all brands
    public List<BrandResponseDto> getAllBrands(){
        return brandRepository.findByActiveTrue().stream().map(brandMapper::toDto).toList();
    }

    //get by id
    public BrandResponseDto getById(Long id){
        Brand existingBrand=brandRepository.findById(id).orElseThrow(()-> new NotFoundException("brand with id "+id+" not found!"));
        return brandMapper.toDto(existingBrand);
    }

    //update by id
    public BrandResponseDto updateById(Long id, BrandRequestDto requestDto){
        Brand existingBrand=brandRepository.findById(id).orElseThrow(()->new NotFoundException("brand with id "+id+" not found!"));
        existingBrand.setName(requestDto.name());
        existingBrand.setCountry(requestDto.country());
        existingBrand.setDescription(requestDto.description());
        existingBrand.setActive(requestDto.active());
        Brand updatedBrand=brandRepository.save(existingBrand);
        return brandMapper.toDto(updatedBrand);
    }

    //delete by id
    public void deleteById(Long id){
        Brand existingBrand=brandRepository.findById(id).orElseThrow(()->new NotFoundException("brand with id "+id+" not found!"));
        existingBrand.setActive(false);
        brandRepository.save(existingBrand);
    }

    //get by name
    public BrandResponseDto getByName(String name){
        Brand existingBrand=brandRepository.findByNameIgnoreCase(name).orElseThrow(()->new NotFoundException("brand "+name+" not found!"));
        return brandMapper.toDto(existingBrand);
    }

    //get All brands for admin
    public List<BrandResponseDto> getAllBrandsForAdmin() {

        return brandRepository.findAll()
                .stream()
                .map(brandMapper::toDto)
                .toList();
    }

    //change status to active by admin
    public void activateBrand(Long id){

        Brand brand = brandRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                "Brand with id " + id + " not found!"
                        ));

        brand.setActive(true);

        brandRepository.save(brand);
    }
}