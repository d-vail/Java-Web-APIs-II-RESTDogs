package com.lambdaschool.restdogs;

import com.lambdaschool.restdogs.dog.Dog;
import com.lambdaschool.restdogs.dog.DogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The SeedDatabase class creates a Spring Bean responsible for initializing the database on the
 * application's startup. It makes use of the CommandLineRunner to load the database once the Spring
 * Application has started. The CommandLineRunner has access to application arguments via a String
 * array.
 */
@Slf4j          // Enable slf4j based logs
@Configuration  // Spring config
public class SeedDatabase {
  /**
   * Seeds database with each Dog object and logs action to console.
   *
   * @param dogRepo Repository or DB interface for the Dog class.
   * @return        A Spring CommandLineRunner method.
   */
  @Bean
  public CommandLineRunner initDatabase(DogRepository dogRepo) {
    return args -> {
      log.info("Seeding " + dogRepo.save(new Dog("Springer", 50, false)));
      log.info("Seeding " + dogRepo.save(new Dog("Bulldog", 50, true)));
      log.info("Seeding " + dogRepo.save(new Dog("Collie", 50, false)));
      log.info("Seeding " + dogRepo.save(new Dog("Boston Terrier", 35, true)));
      log.info("Seeding " + dogRepo.save(new Dog("Corgie", 35, true)));
    };
  }
}
