package app.repositories;

import org.springframework.data.repository.CrudRepository;

import app.repositories.entities.Widget;

public interface WidgetRepository extends CrudRepository<Widget, Long> {
  
}
