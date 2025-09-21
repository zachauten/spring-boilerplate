package boilerplate.db.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "boilerplate.db.repository")
@EntityScan("boilerplate.db.entities")
@EnableTransactionManagement
public class DatabaseConfig {}
