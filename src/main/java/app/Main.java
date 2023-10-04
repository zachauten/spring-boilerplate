package app;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Main {

  public static void main(String... args) {
    new SpringApplicationBuilder(Main.class)
    .bannerMode(Banner.Mode.OFF)
    .run(args);
  }
}
