package app.work;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record Person(String name, int age, Optional<String> job) {
    public Person(String name, int age) {
        this(name, age, Optional.empty());
    }
}
