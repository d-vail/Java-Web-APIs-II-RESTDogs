package com.lambdaschool.restdogs.dog;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

/**
 * A Dog Resource Assembler class. Converts a Dog object into a Resource<Dog> object.
 */
@Component
public class DogResourceAssembler implements ResourceAssembler<Dog, Resource<Dog>> {
  /**
   * Convert the non-resource object Dog into a resource based Resource<Dog> object. Resource is a
   * generic container from Spring HATEOAS that includes an object's data and a collection of links.
   *
   * @param dog A non-resource Dog object
   * @return    A resource based object, Resource<Dog>; the data from Dog object, a self link and a
   *            link to the root. (/dogs)
   */
  @Override
  public Resource<Dog> toResource(Dog dog) {
    return new Resource<Dog>(dog,
            linkTo(methodOn(DogController).one(id)).withSelfRel(),
            linkTo(methodOn(DogController).all()).withRel("dogs"));
  }
}
