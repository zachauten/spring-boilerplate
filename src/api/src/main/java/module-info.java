module boilerplate.api {
  // Requires our internal modules
  requires boilerplate.model;
  requires boilerplate.db;

  // Requires Spring Boot and Web modules
  requires spring.boot;
  requires spring.boot.autoconfigure;
  requires spring.web;
  requires spring.context;
  requires spring.beans;
  requires spring.core;

  // Required by auto-configuration
  // requires spring.data.jpa;
  // requires com.h2database;
  // requires jakarta.persistence;
  // requires org.hibernate.orm.core;
  // requires java.sql;
  // requires com.fasterxml.jackson.databind;

  // IMPORTANT: Open the main package for component scanning
  opens com.example.api to
      spring.core,
      spring.beans,
      spring.context,
      spring.web;
}
