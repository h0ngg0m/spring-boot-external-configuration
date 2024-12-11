package com.honggom.springbootexternalconfiguration.external;

import lombok.extern.slf4j.Slf4j;

// 실행전 VM 옵션에 -Dkey=value -Dkey=value ... 형태로 넣어주면 해당 key=value가 출력된다.
@Slf4j
public class JavaSystemProperties {

    public static void main(String[] args) {
        System.getProperties().forEach((key, value) -> {
            log.info("key: {}, value: {}", key, value);
        });
    }
}
