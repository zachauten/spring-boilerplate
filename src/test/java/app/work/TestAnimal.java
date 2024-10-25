package app.work;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestAnimal {
    @Test
    public void deserialize() throws IOException {
        var file = new File("src/test/resources/dog.json");
        Animal dog = new ObjectMapper().readerFor(Animal.class).readValue(file);

        assertEquals("dog", dog.type);
        assertEquals(Dog.class, dog.getClass());
    }

    @Test
    public void testDeserializeDog() throws Exception {
        String json = "{\"type\":\"dog\", \"bone\":\"chew toy\"}";

        Animal animal = new ObjectMapper().readValue(json, Animal.class);
        assertEquals(Dog.class, animal.getClass());
        Dog dog = (Dog) animal;
        assertEquals("chew toy", dog.bone);
    }

    @Test
    public void testDeserializeCat() throws Exception {
        String json = "{\"type\":\"cat\", \"milk\":\"almond milk\"}";

        Animal animal = new ObjectMapper().readValue(json, Animal.class);
        assertEquals(Cat.class, animal.getClass());
        Cat cat = (Cat) animal;
        assertEquals("almond milk", cat.milk);
    }

}
