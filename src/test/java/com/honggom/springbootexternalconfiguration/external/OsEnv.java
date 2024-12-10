package com.honggom.springbootexternalconfiguration.external;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OsEnv {

    public static void main(String[] args) {
        System.getenv().forEach((key, value) -> {
            log.info("key: {}, value: {}", key, value);
        });
    }

}
