package com.hellocabs.mapper;

import com.hellocabs.dto.CabCategoryDto;
import com.hellocabs.model.CabCategory;

public class CabCategoryMapper {
    public static CabCategoryDto CabCategoryToCabCategoryDto(CabCategory cabCategory){
        CabCategoryDto cabCategoryDto = new CabCategoryDto();

        cabCategoryDto.setCabType(cabCategory.getCabType());
        cabCategoryDto.setInitialFare(cabCategory.getInitialFare());
        cabCategoryDto.setExtraKmFare(cabCategory.getExtraKmFare());
        cabCategoryDto.setAdditionalFare(cabCategory.getAdditionalFare());

        return cabCategoryDto;
    }

    public static CabCategory CabCategoryDtoToCabCategory(CabCategoryDto cabCategoryDto) {
        CabCategory cabCategory = new CabCategory();

        cabCategory.setCabType(cabCategoryDto.getCabType());
        cabCategory.setInitialFare(cabCategory.getInitialFare());
        cabCategory.setExtraKmFare(cabCategoryDto.getExtraKmFare());
        cabCategory.setAdditionalFare(cabCategoryDto.getAdditionalFare());

        return cabCategory;
    }
}
