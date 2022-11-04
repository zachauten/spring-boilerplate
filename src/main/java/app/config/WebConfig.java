package app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import app.interceptors.ResponseTimeInterceptor;
import io.opentelemetry.sdk.OpenTelemetrySdk;
// import io.opentelemetry.sdk.autoconfigure.AutoConfiguredOpenTelemetrySdk;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.SdkTracerProviderBuilder;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new ResponseTimeInterceptor());
  }

  // @Bean
  // public OpenTelemetrySdk openTelemetrySdk() {
  //   return AutoConfiguredOpenTelemetrySdk.initialize().getOpenTelemetrySdk();
  // }
}
