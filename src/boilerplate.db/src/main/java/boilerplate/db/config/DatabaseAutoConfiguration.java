package boilerplate.db.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

@AutoConfiguration
@ConditionalOnClass(DataSource.class)
@Import(DatabaseConfig.class)
public class DatabaseAutoConfiguration {
}
