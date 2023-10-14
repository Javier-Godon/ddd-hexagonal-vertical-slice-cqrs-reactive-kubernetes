package es.bluesolution.pokedex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"es.bluesolution.pokedex"})
public class PokeSourceApplication {

  public static void main(String[] args) {
    SpringApplication.run(PokeSourceApplication.class, args);
  }

}
