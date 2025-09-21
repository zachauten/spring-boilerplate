package boilerplate.db.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "people")
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private Integer age;

  // Default constructor
  public Person() {}

  // Constructor with parameters
  public Person(String name, Integer age) {
    this.name = name;
    this.age = age;
  }

  // Getters and setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "Person{" + "id=" + id + ", name='" + name + '\'' + ", age=" + age + '}';
  }
}
