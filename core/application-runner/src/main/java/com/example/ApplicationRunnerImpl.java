package com.example;

import java.util.Arrays;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunnerImpl implements ApplicationRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationRunnerImpl.class);

    @Override
    public void run(ApplicationArguments args) {
        final Set<String> optionNames = args.getOptionNames();
        LOGGER.info("SourceArgs: {}", Arrays.toString(args.getSourceArgs()));
        LOGGER.info("NonOptionArgs: {}", args.getNonOptionArgs());
        LOGGER.info("OptionNames: {}", optionNames);
        optionNames.forEach(name -> LOGGER.info("OptionValues: {}", args.getOptionValues(name)));
    }
}
