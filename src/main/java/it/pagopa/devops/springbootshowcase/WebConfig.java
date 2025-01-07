package it.pagopa.devops.springbootshowcase;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.pattern.PathPatternParser;
import org.springframework.lang.Nullable;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(@Nullable PathMatchConfigurer configurer) {
        // Use the new PathPatternParser with proper configuration for trailing slashes
        configurer.setPatternParser(new PathPatternParser());
    }
}