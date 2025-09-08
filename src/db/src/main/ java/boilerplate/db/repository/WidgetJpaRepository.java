package boilerplate.db;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WidgetJpaRepository extends JpaRepository<Widget, int> {
    
}
