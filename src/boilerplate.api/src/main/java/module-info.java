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
  requires java.net.http;
  requires io.opentelemetry.instrumentation_annotations;
  requires io.opentelemetry.api;
  requires org.slf4j;
  requires dev.openfeature.sdk;

  requires com.fasterxml.jackson.databind;

  // IMPORTANT: Open the main package for component scanning
  opens boilerplate.api to
      spring.core,
      spring.beans,
      spring.context,
      spring.web,
      io.opentelemetry.instrumentation_annotations;
}
