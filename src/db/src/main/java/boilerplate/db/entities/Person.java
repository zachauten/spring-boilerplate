package boilerplate.db.entities;

import org.springframework.data.annotation.Id;

public record Person(@Id Long id, String name, Integer age) {}
