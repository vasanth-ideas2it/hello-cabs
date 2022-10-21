package com.hellocabs.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerConfiguration {

    public static Logger getInstance(String name ) {
       // String log4jPath = "/hello-cabs/src/main/resources/log4j.properties";
       // PropertyConfigurator.configure(log4jPath);
        return Logger.getLogger(name);
    }

}
