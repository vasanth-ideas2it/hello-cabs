/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */
package com.hellocabs.service.impl;

import com.hellocabs.constants.HelloCabsConstants;
import com.hellocabs.dto.CabCategoryDto;
import com.hellocabs.exception.HelloCabsException;
import com.hellocabs.mapper.CabCategoryMapper;
import com.hellocabs.model.CabCategory;
import com.hellocabs.repository.CabCategoryRepository;
import com.hellocabs.service.CabCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CabCategoryServiceImpl implements CabCategoryService {
    private final CabCategoryRepository cabCategoryRepository;

    /**
     * <p>
     * This method is to add cab category Details.
     * </p>
     *
     * @param cabCategoryDto
     *        for which the cab category to be added is given
     * @return {@link String}
     *         inserted cab category id is returned
     */
    public String createCabCategory(CabCategoryDto cabCategoryDto) {
        CabCategory cabCategory = CabCategoryMapper
                .cabCategoryDtoToCabCategory(cabCategoryDto);

        if (!cabCategoryRepository.existsByCabType(cabCategory.getCabType())) {
            Integer id = CabCategoryMapper.cabCategoryToCabCategoryDto
                    (cabCategoryRepository.save(cabCategory)).getId();

            return HelloCabsConstants.CAB_CATEGORY_CREATED + id;
        }
        return HelloCabsConstants.CAB_TYPE_ALREADY_EXISTS;
    }

    /**
     * <p>
     * This method is to search cab category Details.
     * </p>
     *
     * @param id
     *        for which the id of the cab category need to
     *        be searched is given
     * @return CabCategoryDto
     *         searched cab category is returned if present
     *         otherwise null
     */
    public CabCategoryDto getCabCategoryById(Integer id) {
        CabCategory cabCategory = cabCategoryRepository
                .findByIdAndIsDeleted(id, false);

        if (null != cabCategory) {
            return CabCategoryMapper.cabCategoryToCabCategoryDto(cabCategory);
        }
        throw new HelloCabsException(HelloCabsConstants.CAB_CATEGORY_NOT_FOUND);
    }

    /**
     * <p>
     * This method is to update cab category Details.
     * </p>
     *
     * @param id
     *        for which the cab category id to be updated is given
     * @param cabCategoryDto
     *        for which the cab category to be updated is given
     * @return CabCategoryDto
     *         updated cab category is returned
     */
    public CabCategoryDto updateCabCategory(int id, CabCategoryDto cabCategoryDto) {
        if (cabCategoryRepository.existsByIdAndIsDeleted(id, false)) {
            return CabCategoryMapper.cabCategoryToCabCategoryDto(cabCategoryRepository
                    .save(CabCategoryMapper.cabCategoryDtoToCabCategory(cabCategoryDto)));
        }
        throw new HelloCabsException(HelloCabsConstants.CAB_CATEGORY_NOT_FOUND);

    }

    /**
     * <p>
     * This method is to delete cab category Details.
     * </p>
     *
     * @param id
     *        for which the id of the cab category need to
     *        be deleted is given
     * @return boolean
     *         returns true if the cab category of given id is deleted
     *         otherwise false
     */
    public boolean deleteCabCategoryById(Integer id) {
        CabCategory cabCategory = cabCategoryRepository
                .findByIdAndIsDeleted(id, false);

        if (null == cabCategory) {
            return false;
        }
        cabCategory.setDeleted(true);
        cabCategoryRepository.save(cabCategory);
        return true;
    }

    /**
     * <p>
     * This method is to display all cab category Details.
     * </p>
     *
     * @return List<CabCategoryDto>
     *         retrieves all the cab categories
     */
    public List<CabCategoryDto> retrieveAllCabCategories() {
        return CabCategoryMapper.cabCategoriesToCabCategoryDtos
                (cabCategoryRepository.findAllByIsDeleted(false));
    }
}
