package boilerplate.db.repository;

import boilerplate.db.entities.Widget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WidgetRepository extends JpaRepository<Widget, Integer> {
    
    // Example of derived query method
    List<Widget> findByNameContainingIgnoreCase(String name);
    
    // Example of custom JPQL query
    @Query("SELECT w FROM Widget w WHERE w.description IS NOT NULL")
    List<Widget> findWidgetsWithDescription();
}
