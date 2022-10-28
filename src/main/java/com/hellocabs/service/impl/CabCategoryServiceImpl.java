package com.hellocabs.service.impl;

import com.hellocabs.dto.CabCategoryDto;
import com.hellocabs.mapper.CabCategoryMapper;
import com.hellocabs.mapper.LocationMapper;
import com.hellocabs.model.CabCategory;
import com.hellocabs.repository.CabCategoryRepository;
import com.hellocabs.service.CabCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CabCategoryServiceImpl implements CabCategoryService {
    CabCategoryRepository cabCategoryRepository;

    CabCategoryServiceImpl(CabCategoryRepository cabCategoryRepository) {
        this.cabCategoryRepository = cabCategoryRepository;
    }

    /**
     * <p>
     * This method is to add cab category Details.
     * </p>
     *
     * @param cabCategoryDto
     *        for which the cab category to be added is given
     * @return int
     *         inserted cab category id is returned
     */
    public int createCabCategory(CabCategoryDto cabCategoryDto) {
        CabCategory cabCategory = CabCategoryMapper.CabCategoryDtoToCabCategory(cabCategoryDto);
        int id = CabCategoryMapper.CabCategoryToCabCategoryDto(cabCategoryRepository.save(cabCategory)).getId();

        return id;
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
    public CabCategoryDto getCabCategoryById(int id) {
        Optional<CabCategory> cabCategory = cabCategoryRepository.findById(id);

        if (!cabCategory.isPresent()) {
            return null;
        } else {
            CabCategoryDto cabCategoryDto = CabCategoryMapper.CabCategoryToCabCategoryDto(cabCategory.get());
            return cabCategoryDto;
        }
    }

    /**
     * <p>
     * This method is to update cab category Details.
     * </p>
     *
     * @param cabCategoryDto
     *        for which the cab category to be updated is given
     * @return CabCategoryDto
     *         updated cab category is returned
     */
    public CabCategoryDto updateCabCategory(CabCategoryDto cabCategoryDto) {
        CabCategory cabCategory = CabCategoryMapper.CabCategoryDtoToCabCategory(cabCategoryDto);

        if (cabCategoryRepository.existsById(cabCategory.getId())) {
            return CabCategoryMapper.CabCategoryToCabCategoryDto(cabCategoryRepository.save(cabCategory));
        } else {
            return null;
        }
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
    public boolean deleteCabCategoryById(int id) {
        Optional<CabCategory> cabCategory = cabCategoryRepository.findById(id);

        if (!cabCategory.isPresent()) {
            return false;
        } else {
            cabCategoryRepository.deleteById(id);
            return true;
        }
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
        return CabCategoryMapper.CabCategoriesToCabCategoryDtos(cabCategoryRepository.findAll());
    }
}
