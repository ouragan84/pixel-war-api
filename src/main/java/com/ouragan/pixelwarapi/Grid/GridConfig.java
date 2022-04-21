package com.ouragan.pixelwarapi.Grid;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class GridConfig {

    public static final int initialWidth = 100;
    public static final int initialHeight = 100;
    public static final int initialColor = 0;

    @Bean
    CommandLineRunner commandLineRunner(GridRepository repository){
        return args -> {
            GirdData initialGrid = new GirdData(
                    initialHeight,
                    initialWidth,
                    initialColor
            );


            if(repository.count() < 1){
                repository.saveAll(
                        Arrays.asList(initialGrid)
                );
            }
        };
    }
}
