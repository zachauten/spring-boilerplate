package boilerplate.db.widget;

import org.springframework.data.annotation.Id;

import boilerplate.db.person.Person;

public record Widget(@Id Long id, String name, String value, Person owner) {}
