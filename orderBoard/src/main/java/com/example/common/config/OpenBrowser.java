package com.example.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 项目自启动 Project self-start
 */
@Component // It is a Spring component that is automatically scanned and registered in the Spring container.
public class OpenBrowser implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(OpenBrowser.class);

    @Value("${open.browser.url}")
    private String url;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Project start successful……");
        try {
            // it can specify your own path
            Runtime.getRuntime().exec("cmd   /c   start   " + url);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}