package boilerplate.api.widgets;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import boilerplate.db.widget.Widget;
import boilerplate.db.widget.WidgetRepository;

@RestController
@RequestMapping("/widgets")
public class WidgetController {
  private final WidgetRepository widgetRepository;

  public WidgetController(WidgetRepository widgetRepository) {
    this.widgetRepository = widgetRepository;
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
