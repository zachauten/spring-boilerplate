package boilerplate.db.repository;

import boilerplate.db.entities.Widget;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface WidgetRepository extends JpaRepository<Widget, Long> {
  @Override
  @NonNull
  List<Widget> findAll();

  @Override
  @NonNull
  <S extends Widget> S save(@NonNull S entity);
}
