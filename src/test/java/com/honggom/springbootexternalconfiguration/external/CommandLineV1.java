package com.honggom.springbootexternalconfiguration.external;

import lombok.extern.slf4j.Slf4j;

// java -jar dataA dataB dataC
@Slf4j
public class CommandLineV1 {

    public static void main(String[] args) {
        for (String arg : args) {
            log.info("arg: {}", arg);
        }
        // arg: dataA
        // arg: dataB
        // arg: dataC
    }
}
