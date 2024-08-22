package app.config;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.instrumentation.httpclient.JavaHttpClientTelemetry;
import java.net.http.HttpClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaHttpClientConfig {

  //Use this HttpClient implementation for making standard http client calls.
  @Bean
  public HttpClient createTracedClient(OpenTelemetry openTelemetry) {
    return JavaHttpClientTelemetry.builder(openTelemetry).build().newHttpClient(createClient());
  }

  //your configuration of the Java HTTP Client goes here:
  private HttpClient createClient() {
    return HttpClient.newBuilder().build();
  }
}
