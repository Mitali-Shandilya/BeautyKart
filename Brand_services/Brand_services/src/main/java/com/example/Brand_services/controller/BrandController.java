package com.example.Brand_services.controller;

import com.example.Brand_services.dto.request.BrandRequestDto;
import com.example.Brand_services.dto.response.BrandResponseDto;
import com.example.Brand_services.service.BrandService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    //create a brand
    @PostMapping
    public BrandResponseDto create(@Valid @RequestBody BrandRequestDto requestDto){
        return brandService.addBrand(requestDto);
    }

    //get all brands
    @GetMapping
    public List<BrandResponseDto> getAll(){
        List<BrandResponseDto> listOfBrands=brandService.getAllBrands();
        return listOfBrands;
    }

    //get by id
    @GetMapping("/{id}")
    public BrandResponseDto getById(@PathVariable Long id){
        return brandService.getById(id);
    }

    //update by id
    @PutMapping("/{id}")
    public BrandResponseDto updateById(@PathVariable Long id, @Valid @RequestBody BrandRequestDto requestDto){
        return brandService.updateById(id,requestDto);
    }

    //delete by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        brandService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //get by name
    @GetMapping("/by-name/{name}")
    public BrandResponseDto getByName(@PathVariable String name){
        return brandService.getByName(name);
    }

    //get All for admin
    @GetMapping("/admin")
    public  List<BrandResponseDto> getAllBrandsForAdmin(){
        return brandService.getAllBrandsForAdmin();
    }

    //change status to active by admin
    @PutMapping("/admin/{id}/activate")
    public ResponseEntity<Void> activateBrand(@PathVariable Long id){
        brandService.activateBrand(id);
        return ResponseEntity.ok().build();
    }
}
