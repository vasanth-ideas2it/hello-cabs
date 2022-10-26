package com.hellocabs.controller;

import com.hellocabs.dto.CabCategoryDto;
import com.hellocabs.service.CabCategoryService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cabcategory")
public class CabCategoryController {
    CabCategoryService cabCategoryService;
    CabCategoryController(CabCategoryService cabCategoryService) {
        this.cabCategoryService = cabCategoryService;
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
    @PostMapping("/create")
    public int addCabCategory(@Valid @RequestBody CabCategoryDto cabCategoryDto) {
        int id = cabCategoryService.createCabCategory(cabCategoryDto);
        return  id;
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
     *         searched cab category is returned
     */
    @GetMapping("/search/{id}")
    public CabCategoryDto searchCabCategoryById(@PathVariable int id) {
        CabCategoryDto cabCategoryDto = cabCategoryService.getCabCategoryById(id);

        if (cabCategoryDto == null) {
            return null;
        } else {
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
    @PutMapping("/update")
    public CabCategoryDto updateCabCategory(@Valid @RequestBody CabCategoryDto cabCategoryDto) {
        CabCategoryDto updatedCabCategoryDto = cabCategoryService.updateCabCategory(cabCategoryDto);
        return  updatedCabCategoryDto;
    }

    /**
     * <p>
     * This method is to delete cab category Details.
     * </p>
     *
     * @param id
     *        for which the id of the cab category need to
     *        be deleted is given
     * @return String
     *         gets the message whether the cab category is
     *         deleted or not
     */
    @DeleteMapping("/delete/{id}")
    public String deleteCabCategoryById(@PathVariable int id) {
        if (cabCategoryService.deleteCabCategoryById(id)) {
            return "Cab category is deleted for given id";
        } else {
            return "Cab category is not found for given id";
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
    @GetMapping("/cabcategories")
    public List<CabCategoryDto> getAllCabCategories() {
        List<CabCategoryDto> cabCategoryDtos = cabCategoryService.retrieveAllCabCategories();
        return cabCategoryDtos;
    }
}
