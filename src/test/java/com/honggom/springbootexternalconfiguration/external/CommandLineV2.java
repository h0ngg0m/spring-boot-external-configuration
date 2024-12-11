package com.honggom.springbootexternalconfiguration.external;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;

import java.util.List;
import java.util.Set;

// java -jar --url=devdb --username=dev --password=devpass mode=on
@Slf4j
public class CommandLineV2 {

    public static void main(String[] args) {
        for (String arg : args) {
            log.info("arg: {}", arg);
        }

        ApplicationArguments appArgs = new DefaultApplicationArguments(args);
        log.info("SourceArgs: {}", List.of(appArgs.getSourceArgs())); // SourceArgs: [--url=devdb, --username=dev, --password=devpass, mode=on]
        log.info("NonOptionsArgs: {}", appArgs.getNonOptionArgs()); // NonOptionsArgs: [mode=on]
        log.info("OptionNames: {}", appArgs.getOptionNames()); // OptionNames: [password, url, username]

        log.info("-------------------------------------------");

        Set<String> optionNames = appArgs.getOptionNames();
        for (String optionName : optionNames) {
            log.info("OptionName: {}, OptionValues: {}", optionName, appArgs.getOptionValues(optionName));
            // OptionName: password, OptionValues: [devpass]
            // OptionName: url, OptionValues: [devdb]
            // OptionName: username, OptionValues: [dev]
        }

        log.info("-------------------------------------------");

        List<String> password = appArgs.getOptionValues("password");
        List<String> url = appArgs.getOptionValues("url");
        List<String> username = appArgs.getOptionValues("username");
        List<String> mode = appArgs.getNonOptionArgs();

        log.info("password: {}", password); // password: [devpass]
        log.info("url: {}", url); // url: [devdb]
        log.info("username: {}", username); // username: [dev]
        log.info("mode: {}", mode); // mode: [mode=on]

    }
}
