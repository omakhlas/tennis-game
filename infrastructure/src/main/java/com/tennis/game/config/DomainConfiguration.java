package com.tennis.game.config;

import com.tennis.game.domain.DomainService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "com.tennis.game.domain",
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = DomainService.class)
)
public class DomainConfiguration {
}
