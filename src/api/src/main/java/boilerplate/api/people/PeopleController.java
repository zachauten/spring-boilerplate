package boilerplate.api.people;

import boilerplate.db.entities.Person;
import boilerplate.db.entities.Widget;
import boilerplate.db.repository.PersonRepository;
import boilerplate.db.repository.WidgetRepository;
import boilerplate.model.requests.PersonUpdateRequest;
import boilerplate.model.responses.PersonResponse;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo")
public class PeopleController {

  private PersonRepository personRepository;
  private WidgetRepository widgetRepository;

  public PeopleController(PersonRepository personRepository, WidgetRepository widgetRepository) {
    this.personRepository = personRepository;
    this.widgetRepository = widgetRepository;
  }

  @GetMapping("/people")
  public List<Person> getAllPeople() {
    return personRepository.findAll();
  }

  @PostMapping("/people")
  public Person createPerson(@RequestBody Person person) {
    return personRepository.save(person);
  }

  @GetMapping("/widgets")
  public List<Widget> getAllWidgets() {
    return widgetRepository.findAll();
  }

  @PostMapping("/widgets")
  public Widget createWidget(@RequestBody Widget widget) {
    return widgetRepository.save(widget);
  }

  @PatchMapping("/people")
  public PersonResponse updatePerson(@RequestBody PersonUpdateRequest person) {
    return new PersonResponse(person.name().orElse(null), person.age().orElse(0));
  }
}
