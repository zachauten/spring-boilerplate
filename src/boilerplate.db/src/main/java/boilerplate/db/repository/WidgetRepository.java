package boilerplate.db.repository;

import boilerplate.db.entities.Widget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WidgetRepository extends JpaRepository<Widget, Integer> {}
