package com.lambdaschool.restdogs.dog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * DogRepository is an interface that extends JpaRepository, automatically creating JPA queries from
 * the any included method names.
 */
public interface DogRepository extends JpaRepository<Dog, Long> {
  List<Dog> findByBreed(String breed);
  List<Dog> findByApartmentIsTrue();

  @Transactional
  void deleteByBreed(String breed);
}
