package app.work;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Dog.class, name = "dog"),
    @JsonSubTypes.Type(value = Cat.class, name = "cat")
})
public abstract sealed class Animal {
    @JsonProperty
    String type;
}

final class Dog extends Animal {
    @JsonProperty
    String bone;
    public Dog() {
        this.type = "dog";
    }
}
final class Cat extends Animal {
    @JsonProperty
    String milk;
    public Cat() {
        this.type = "cat";
    }
}