package com.hellocabs.service;

import com.hellocabs.dto.CabCategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CabCategoryService {
    int createCabCategory(CabCategoryDto cabCategoryDto);

    CabCategoryDto updateCabCategory(CabCategoryDto cabCategoryDto);

    CabCategoryDto getCabCategoryById(int id);

    boolean deleteCabCategoryById(int id);

    List<CabCategoryDto> retrieveAllCabCategories();
}
