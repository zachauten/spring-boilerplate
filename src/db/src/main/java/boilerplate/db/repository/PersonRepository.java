package boilerplate.db.repository;

import boilerplate.db.entities.Person;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

  @NonNull
  @Query("")
  List<Person> findAll();

  @Override
  @NonNull
  <S extends Person> S save(@NonNull S entity);
}
