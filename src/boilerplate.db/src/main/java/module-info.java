open module boilerplate.db {
  exports boilerplate.db.repository;

  requires spring.data.jpa;
  requires jakarta.persistence;
}
