package boilerplate.db.widget;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import boilerplate.db.person.Person;

@Table("widgets")
public record Widget(@Id Long id, String name, String value, Person owner) {}
