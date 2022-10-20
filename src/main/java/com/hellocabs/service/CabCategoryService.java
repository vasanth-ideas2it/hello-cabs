package com.hellocabs.service;

import com.hellocabs.dto.CabCategoryDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CabCategoryService {
    int createCabCategory(CabCategoryDto cabCategoryDto);

 //   CabCategoryDto updateCabCategoryById();

    CabCategoryDto getCabCategoryById(int id);

    boolean deleteCabCategoryById(int id);
}
