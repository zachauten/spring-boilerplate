package app.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HealthController {
  
  @RequestMapping("/health")
  public ResponseEntity<String> health() {
    return ResponseEntity.ok("Ok");
  }
}
