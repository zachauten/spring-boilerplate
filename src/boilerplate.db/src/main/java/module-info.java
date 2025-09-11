open module boilerplate.db {
  exports boilerplate.db.repository;
  exports boilerplate.db.entities;

  requires spring.data.jpa;
  requires jakarta.persistence;
}
