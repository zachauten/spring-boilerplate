package boilerplate.db.entities;

import org.springframework.data.annotation.Id;

public record Widget(@Id Long id, String name, String value, Person owner) {}
