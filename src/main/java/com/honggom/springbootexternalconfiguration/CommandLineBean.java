package com.honggom.springbootexternalconfiguration;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommandLineBean {

    private final ApplicationArguments arguments;

    // --url=devdb --username=dev --password=devpass mode=on
    @PostConstruct
    public void init() {
        log.info("source {}", List.of(arguments.getSourceArgs())); // source [--url=devdb, --username=dev, --password=devpass, mode=on]
        log.info("optionNames {}", arguments.getOptionNames()); // optionNames [password, url, username]
        Set<String> optionNames = arguments.getOptionNames();
        for (String optionName : optionNames) {
            log.info("option args {}={}", optionName, arguments.getOptionValues(optionName));
            // option args password=[devpass]
            // option args url=[devdb]
            // option args username=[dev]
        }
    }
}
