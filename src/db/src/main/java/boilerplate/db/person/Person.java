package boilerplate.db.person;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("people")
public record Person(@Id Long id, String name, Integer age) {}
