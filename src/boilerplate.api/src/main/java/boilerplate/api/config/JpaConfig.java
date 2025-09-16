package boilerplate.api.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "boilerplate.db.repository")
@EntityScan(basePackages = "boilerplate.db.entities")
public class JpaConfig {
}
