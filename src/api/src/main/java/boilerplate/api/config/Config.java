package boilerplate.api.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"boilerplate.api", "boilerplate.db", "boilerplate.model"})
public class Config {}
