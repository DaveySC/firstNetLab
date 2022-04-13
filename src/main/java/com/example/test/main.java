package com.example.test;

import org.apache.logging.log4j.*;

public class main {
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(main.class.getName());
        logger.error("error was occured");
        logger.info("this is info log");
    }
}
