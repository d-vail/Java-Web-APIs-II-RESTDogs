package com.lambdaschool.restdogs.dog;

/**
 * DogNotFoundException extends RuntimeException to indicate when a dog is not found.
 */
public class DogNotFoundException extends RuntimeException {
  public DogNotFoundException(Long id) {
    super("Could not find dog " + id);
  }
}
