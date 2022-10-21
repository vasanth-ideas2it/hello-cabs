package com.hellocabs.mapper;

import com.hellocabs.dto.CabCategoryDto;
import com.hellocabs.dto.LocationDto;
import com.hellocabs.model.CabCategory;
import com.hellocabs.model.Location;

import java.util.ArrayList;
import java.util.List;

public class CabCategoryMapper {
    public static CabCategoryDto CabCategoryToCabCategoryDto(CabCategory cabCategory){
        CabCategoryDto cabCategoryDto = new CabCategoryDto();

        cabCategoryDto.setId(cabCategory.getId());
        cabCategoryDto.setCabType(cabCategory.getCabType());
        cabCategoryDto.setInitialFare(cabCategory.getInitialFare());
        cabCategoryDto.setExtraKmFare(cabCategory.getExtraKmFare());
        cabCategoryDto.setAdditionalFare(cabCategory.getAdditionalFare());

        return cabCategoryDto;
    }

    public static CabCategory CabCategoryDtoToCabCategory(CabCategoryDto cabCategoryDto) {
        CabCategory cabCategory = new CabCategory();

        cabCategory.setId(cabCategoryDto.getId());
        cabCategory.setCabType(cabCategoryDto.getCabType());
        cabCategory.setInitialFare(cabCategory.getInitialFare());
        cabCategory.setExtraKmFare(cabCategoryDto.getExtraKmFare());
        cabCategory.setAdditionalFare(cabCategoryDto.getAdditionalFare());

        return cabCategory;
    }

    public static List<CabCategoryDto> CabCategoriesToCabCategoryDtos(List<CabCategory> cabCategories) {
        List<CabCategoryDto> cabCategoryDtos = new ArrayList<CabCategoryDto>();

        for (CabCategory cabCategory: cabCategories) {
            cabCategoryDtos.add(CabCategoryToCabCategoryDto(cabCategory));
        }
        return cabCategoryDtos;
    }
}
