/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.mapper;

import com.hellocabs.configuration.MapperConfig;
import com.hellocabs.dto.CabCategoryDto;
import com.hellocabs.model.CabCategory;

import java.util.ArrayList;
import java.util.List;

public class CabCategoryMapper {
    public static CabCategoryDto cabCategoryToCabCategoryDto(CabCategory cabCategory) {
        return MapperConfig.getConfigure().map(cabCategory, CabCategoryDto.class);
    }

    public static CabCategory cabCategoryDtoToCabCategory(CabCategoryDto cabCategoryDto) {
        return MapperConfig.getConfigure().map(cabCategoryDto, CabCategory.class);
    }

    public static List<CabCategoryDto> cabCategoriesToCabCategoryDtos(List<CabCategory> cabCategories) {
        List<CabCategoryDto> cabCategoryDtos = new ArrayList<>();

        for (CabCategory cabCategory: cabCategories) {
            cabCategoryDtos.add(cabCategoryToCabCategoryDto(cabCategory));
        }
        return cabCategoryDtos;
    }

    public static CabCategory convertPartialCabCategoryDtoIntoCabCatogory(CabCategoryDto cabCategoryDto) {
        CabCategory cabCategory = new CabCategory();
        cabCategory.setId(cabCategoryDto.getId());
        cabCategory.setCabType(cabCategoryDto.getCabType());
        cabCategory.setInitialFare(cabCategoryDto.getInitialFare());
        cabCategory.setExtraFarePerHour(cabCategoryDto.getExtraFarePerHour());
        cabCategory.setPeakHourFare(cabCategoryDto.getPeakHourFare());
        return cabCategory;
    }

    public static CabCategoryDto convertPartialCabCategoryIntoCabCatogoryDto(CabCategory cabCategory) {
        CabCategoryDto cabCategoryDto = new CabCategoryDto();
        cabCategoryDto.setId(cabCategory.getId());
        cabCategoryDto.setCabType(cabCategory.getCabType());
        cabCategoryDto.setInitialFare(cabCategory.getInitialFare());
        cabCategoryDto.setExtraFarePerHour(cabCategory.getExtraFarePerHour());
        cabCategoryDto.setPeakHourFare(cabCategory.getPeakHourFare());
        return cabCategoryDto;
    }
}
