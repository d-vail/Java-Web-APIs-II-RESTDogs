package com.lambdaschool.restdogs.dog;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * DogRepository is an interface that extends JpaRepository, automatically creating JPA queries from
 * the any included method names.
 */
public interface DogRepository extends JpaRepository<Dog, Long> {
  List<Dog> findByBreed(String breed);
}
