package com.lambdaschool.restdogs.dog;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DogRepository is an interface that extends JpaRepository, automatically creating JPA queries from
 * the any included method names.
 */
public interface DogRepository extends JpaRepository<Dog, Long> { }
