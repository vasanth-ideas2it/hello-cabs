package com.hellocabs.service.impl;

import com.hellocabs.dto.CabCategoryDto;
import com.hellocabs.mapper.CabCategoryMapper;
import com.hellocabs.model.CabCategory;
import com.hellocabs.repository.CabCategoryRepository;
import com.hellocabs.service.CabCategoryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CabCategoryServiceImpl implements CabCategoryService {
    CabCategoryRepository cabCategoryRepository;

    CabCategoryServiceImpl(CabCategoryRepository cabCategoryRepository) {
        this.cabCategoryRepository = cabCategoryRepository;
    }

    public int createCabCategory(CabCategoryDto cabCategoryDto) {
        CabCategory cabCategory = CabCategoryMapper.CabCategoryDtoToCabCategory(cabCategoryDto);
        int id = CabCategoryMapper.CabCategoryToCabCategoryDto(cabCategoryRepository.save(cabCategory)).getId();
        return id;
    }

    public CabCategoryDto getCabCategoryById(int id) {
        Optional<CabCategory> cabCategory = cabCategoryRepository.findById(id);

        if (!cabCategory.isPresent()) {
            return null;
        } else {
            CabCategoryDto cabCategoryDto = CabCategoryMapper.CabCategoryToCabCategoryDto(cabCategory.get());
            return cabCategoryDto;
        }
    }

    public boolean deleteCabCategoryById(int id) {
        Optional<CabCategory> cabCategory = cabCategoryRepository.findById(id);

        if (!cabCategory.isPresent()) {
            return false;
        } else {
            cabCategoryRepository.deleteById(id);
            return true;
        }
    }
}
