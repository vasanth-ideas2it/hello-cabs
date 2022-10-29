package com.hellocabs.mapper;

import com.hellocabs.dto.CabCategoryDto;
import com.hellocabs.model.CabCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CabCategoryMapper {
    public static CabCategoryDto  CabCategoryToCabCategoryDto(CabCategory cabCategory) {
        return new CabCategoryDto(cabCategory.getId(), cabCategory.getCabType(), cabCategory.getInitialFare(),
                                  cabCategory.getExtraFarePerHr(), cabCategory.getPeakHrFare(),
                                  cabCategory.getCabs().stream().map(CabMapper :: convertCabToCabDto).collect(Collectors.toList()));
    }

    public static CabCategory CabCategoryDtoToCabCategory(CabCategoryDto cabCategoryDto) {
        return new CabCategory(cabCategoryDto.getId(), cabCategoryDto.getCabType(),
                               cabCategoryDto.getInitialFare(),
                               cabCategoryDto.getExtraFarePerHr(), cabCategoryDto.getPeakHrFare(),
                               cabCategoryDto.getCabs().stream().map(CabMapper :: convertCabDtoToCab).collect(Collectors.toList()));
    }

    public static List<CabCategoryDto> CabCategoriesToCabCategoryDtos(List<CabCategory> cabCategories) {
        List<CabCategoryDto> cabCategoryDtos = new ArrayList<>();

        for (CabCategory cabCategory: cabCategories) {
            cabCategoryDtos.add(CabCategoryToCabCategoryDto(cabCategory));
        }
        return cabCategoryDtos;
    }
}
