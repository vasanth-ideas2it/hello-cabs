/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Implementation of custom logger configuration
 * in order to log data
 *
 * This file is created on 21/10/2022
 * @author : Pradeep
 *
 */
public class LoggerConfiguration {

    /**
     * Used to get the logger object
     *
     * @param name {@link String} name of the class in which
     *                           logger has to be implemented
     * @return logger {@link Logger} created logger
     */
    public static Logger getInstance(String name ) {
        /*String log4jPath = "/hello-cabs/src/main/resources/log4j.properties";
        PropertyConfigurator.configure(log4jPath);
        */
        return Logger.getLogger(name);
    }

}
