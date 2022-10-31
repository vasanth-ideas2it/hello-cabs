package com.hellocabs.configuration;

import org.modelmapper.ModelMapper;

/**
 * <p>
 *   Class provide Mapper configuration to all classes to access
 *   the model mapper object
 * </p>
 *
 * @author : Jaganathan R
 * @version 1.0
 *
 */
public class MapperConfig {

    public static ModelMapper getConfigure() {
        return new ModelMapper();
    }

}
