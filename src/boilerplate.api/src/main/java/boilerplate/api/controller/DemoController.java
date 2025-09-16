package boilerplate.api.controller;

import boilerplate.db.entities.Person;
import boilerplate.db.entities.Widget;
import boilerplate.db.repository.PersonRepository;
import boilerplate.db.repository.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private WidgetRepository widgetRepository;

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
}
