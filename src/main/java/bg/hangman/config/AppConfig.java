package bg.hangman.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import bg.hangman.practice.GameService;

@Configuration
@ComponentScan(basePackages = "bg.hangman")
public class AppConfig {

  @Bean
  @Primary
  public GameService getService() {
    return new GameService();
  }

}
