package boilerplate.db.repository;

import boilerplate.db.entities.Person;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface PersonRepository extends JpaRepository<Person, Long> {
  @Override
  @NonNull
  List<Person> findAll();

  @Override
  @NonNull
  <S extends Person> S save(@NonNull S entity);
}
