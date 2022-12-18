package com.example;

import java.util.Arrays;
import java.util.Set;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunnerImpl implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) {
        Set<String> optionNames = args.getOptionNames();
        System.out.println("SourceArgs: " + Arrays.toString(args.getSourceArgs()));
        System.out.println("NonOptionArgs: " + args.getNonOptionArgs());
        System.out.println("OptionNames: " + optionNames);
        optionNames.forEach(name -> System.out.println("OptionValues: " + args.getOptionValues(name)));
    }
}
