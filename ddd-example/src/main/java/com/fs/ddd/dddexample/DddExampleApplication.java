package com.fs.ddd.dddexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DddExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(DddExampleApplication.class, args);
        testSplit();
    }


    private static void testSplit() {
        String uri = " http://ip:port/cluster/server/flowRules";
        String [] arrs = uri.split("/");
        System.out.println(arrs);
    }
}
