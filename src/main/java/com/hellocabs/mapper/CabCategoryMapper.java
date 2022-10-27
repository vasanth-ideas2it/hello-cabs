package com.hellocabs.mapper;

import com.hellocabs.dto.CabCategoryDto;
import com.hellocabs.model.CabCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CabCategoryMapper {
    public static CabCategoryDto CabCategoryToCabCategoryDto(CabCategory cabCategory){
        CabCategoryDto cabCategoryDto = new CabCategoryDto();

        cabCategoryDto.setId(cabCategory.getId());
        cabCategoryDto.setCabType(cabCategory.getCabType());
        cabCategoryDto.setInitialFare(cabCategory.getInitialFare());
        cabCategoryDto.setExtraKmFare(cabCategory.getExtraKmFare());
        cabCategoryDto.setAdditionalFare(cabCategory.getAdditionalFare());
        cabCategoryDto.setCabDtos(cabCategory.getCabs()
                .stream().map(CabMapper::convertCabToCabDto)
                .collect(Collectors.toList()));


        return cabCategoryDto;
    }

    public static CabCategory CabCategoryDtoToCabCategory(CabCategoryDto cabCategoryDto) {
        CabCategory cabCategory = new CabCategory();

        cabCategory.setId(cabCategoryDto.getId());
        cabCategory.setCabType(cabCategoryDto.getCabType());
        cabCategory.setInitialFare(cabCategory.getInitialFare());
        cabCategory.setExtraKmFare(cabCategoryDto.getExtraKmFare());
        cabCategory.setAdditionalFare(cabCategoryDto.getAdditionalFare());
        cabCategory.setCabs(cabCategoryDto.getCabDtos()
                .stream().map(CabMapper::convertCabDtoToCab)
                .collect(Collectors.toList()));

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
