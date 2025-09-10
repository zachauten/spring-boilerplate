package boilerplate.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import boilerplate.db.entities.Widget;

public interface WidgetJpaRepository extends JpaRepository<Widget, Integer> {}
