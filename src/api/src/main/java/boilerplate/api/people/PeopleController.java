package boilerplate.api.people;

import boilerplate.db.person.Person;
import boilerplate.db.person.PersonRepository;
import boilerplate.db.widget.Widget;
import boilerplate.db.widget.WidgetRepository;
import boilerplate.model.requests.PersonUpdateRequest;
import boilerplate.model.responses.PersonResponse;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/people")
public class PeopleController {

  private final PersonRepository personRepository;

  public PeopleController(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @GetMapping("/people")
  public List<Person> getAllPeople() {
    return personRepository.findAll();
  }

  @PostMapping("/people")
  public Person createPerson(@RequestBody Person person) {
    return personRepository.save(person);
  }

  @PatchMapping("/people")
  public PersonResponse updatePerson(@RequestBody PersonUpdateRequest person) {
    return new PersonResponse(person.name().orElse(null), person.age().orElse(0));
  }
}
