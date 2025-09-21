open module boilerplate.db {
  exports boilerplate.db.repository;
  exports boilerplate.db.entities;
  exports boilerplate.db.config;

  requires spring.core;
  requires spring.boot;
  requires spring.boot.autoconfigure;
  requires spring.data.jpa;
  requires spring.context;
  requires spring.jdbc;
  requires org.hibernate.orm.core;
  requires java.naming;
  requires transitive jakarta.persistence;
  requires transitive java.sql;
  requires transitive spring.orm;
  requires transitive spring.tx;
}
