open module boilerplate.db {
    exports boilerplate.db.repository;
    exports boilerplate.db.entities;
    exports boilerplate.db.config;

    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.data.jpa;
    requires jakarta.persistence;
    requires spring.context;
    requires spring.tx;
    requires org.hibernate.orm.core;
    requires java.sql;
    requires java.naming;
    requires spring.orm;
    requires spring.jdbc;
}
