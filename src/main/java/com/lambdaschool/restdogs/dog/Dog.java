package com.lambdaschool.restdogs.dog;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * The Dog class represents a dog breed type.
 */
@Data   // Generates getters, setters, toString
@Entity // Preps object for JPA storage
public class Dog {
  private @Id @GeneratedValue Long id;
  private @Column(unique = true) String breed;
  private int weight;
  private boolean apartment;

  /**
   * Default Constructor
   *
   * Necessary for JPA.
   */
  public Dog() { }

  /**
   * Constructor
   *
   * Constructs a dog object with a given breed, weight, and apartment.
   *
   * @param breed     The breed of the dog.
   * @param weight    The weight of the dog.
   * @param apartment Whether the dog breed is good for apartments.
   */
  public Dog(String breed, int weight, boolean apartment) {
    this.breed = breed;
    this.weight = weight;
    this.apartment = apartment;
  }
}


