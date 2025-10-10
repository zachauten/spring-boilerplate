package boilerplate.db.repository;

import boilerplate.db.entities.Widget;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface WidgetRepository extends CrudRepository<Widget, Long> {
  @Override
  @NonNull
  List<Widget> findAll();

  @Override
  @NonNull
  <S extends Widget> S save(@NonNull S entity);
}
