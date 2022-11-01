/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */
package com.hellocabs.mapper;

import com.hellocabs.dto.CabCategoryDto;
import com.hellocabs.model.CabCategory;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

public class CabCategoryMapper {
    private static final ModelMapper modelMapper = new ModelMapper();
    public static CabCategoryDto  CabCategoryToCabCategoryDto(CabCategory cabCategory) {
        return modelMapper.map(cabCategory, CabCategoryDto.class);
    }

    public static CabCategory CabCategoryDtoToCabCategory(CabCategoryDto cabCategoryDto) {
        return modelMapper.map(cabCategoryDto, CabCategory.class);
    }

    public static List<CabCategoryDto> CabCategoriesToCabCategoryDtos(List<CabCategory> cabCategories) {
        List<CabCategoryDto> cabCategoryDtos = new ArrayList<>();

        for (CabCategory cabCategory: cabCategories) {
            cabCategoryDtos.add(CabCategoryToCabCategoryDto(cabCategory));
        }
        return cabCategoryDtos;
    }
}
