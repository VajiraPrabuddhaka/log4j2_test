package com.vajira.demo;

import static logging.error.format.ErrorLog.ErrorLog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static net.logstash.logback.argument.StructuredArguments.kv;

//import static com.sun.tools.doclint.Entity.prop;

public class Main {
    static Logger log = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        System.setProperty("log4j.configurationFile","/home/vajira/WSO2dev/my-work/test_projects/log4j2_test/src/main/resources/log4j2.properties");

        log.info("Helllllooooooooooo this is a sample info message");
        log.error("msg {}", 4, ErrorLog("critical", 4));
        log.error("Helllllooooooooooo this is a sample error message");
        log.debug("Helllllooooooooooo this is a sample debug message");
    }
}
