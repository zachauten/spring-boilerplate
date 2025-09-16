package boilerplate.db.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "widgets")
public class Widget {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  private String value;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "owner_id")
  private Person owner;

  // Default constructor
  public Widget() {}

  // Constructor with parameters
  public Widget(String name, String value, Person owner) {
    this.name = name;
    this.value = value;
    this.owner = owner;
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

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public Person getOwner() {
    return owner;
  }

  public void setOwner(Person owner) {
    this.owner = owner;
  }

  @Override
  public String toString() {
    return "Widget{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", value='"
        + value
        + '\''
        + ", owner="
        + (owner != null ? owner.getId() : null)
        + '}';
  }
}
