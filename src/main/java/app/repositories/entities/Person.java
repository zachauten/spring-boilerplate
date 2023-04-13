package app.repositories.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Person {
  @Id
  long id;
  String name;
  int age;
}
