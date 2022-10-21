package com.hellocabs.controller;

import com.hellocabs.dto.CabCategoryDto;
import com.hellocabs.service.CabCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cabcategory")
public class CabCategoryController {

    CabCategoryService cabCategoryService;
    CabCategoryController(CabCategoryService cabCategoryService) {
        this.cabCategoryService = cabCategoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<Integer> addCabCategory(@RequestBody CabCategoryDto cabCategoryDto) {
        int id = cabCategoryService.createCabCategory(cabCategoryDto);
        return  new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping("/search/{id}")
    public CabCategoryDto searchCabCategoryById(@PathVariable int id) {
        CabCategoryDto cabCategoryDto = cabCategoryService.getCabCategoryById(id);

        if (cabCategoryDto == null) {
            return null;
        } else {
            return cabCategoryDto;
        }
    }

    @PutMapping("/update")
    public ResponseEntity<CabCategoryDto> updateCabCategory(@RequestBody CabCategoryDto cabCategoryDto) {
        cabCategoryService.updateCabCategory(cabCategoryDto);
        return  new ResponseEntity<>(cabCategoryDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCabCategoryById(@PathVariable int id) {
        if (cabCategoryService.deleteCabCategoryById(id)) {
            System.out.println("Cab category is deleted for given id");
        } else {
            System.out.println("Cab category is not found for given id");
        }
    }

    @GetMapping("/cabcategories")
    public ResponseEntity<List<CabCategoryDto>> getAllCabCategories() {
        List<CabCategoryDto> cabCategoryDtos = cabCategoryService.retrieveAllCabCategories();
        return new ResponseEntity<>(cabCategoryDtos, HttpStatus.OK);
    }
}
