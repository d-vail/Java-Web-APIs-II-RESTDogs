package com.lambdaschool.restdogs.dog;

import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Dog Controller class to handle /dogs endpoints
 */
@RestController
@RequestMapping("/dogs")
public class DogController {
  private final DogRepository DOGREPO;
  private final DogResourceAssembler ASSEMBLER;

  public DogController(DogRepository dogRepo, DogResourceAssembler assembler) {
    this.DOGREPO = dogRepo;
    this.ASSEMBLER = assembler;
  }

  /**
   * Returns all dog listings.
   *
   * @return  A collection of dog resource objects
   */
  @GetMapping()
  public Resources<Resource<Dog>> all() {
    List<Resource<Dog>> dogs = DOGREPO.findAll().stream()
            .map(ASSEMBLER::toResource)
            .collect(Collectors.toList());

    return new Resources<>(dogs,
            linkTo(methodOn(DogController.class).all()).withSelfRel());
  }

  /**
   * Returns a single dog given an id.
   *
   * @param id                A dog id
   * @return                  A dog resource object
   * @throws RuntimeException If invalid id is given
   */
  @GetMapping("/{id}")
  public Resource<Dog> one(@PathVariable Long id) {
    Dog dog = DOGREPO.findById(id)
            .orElseThrow(() -> new DogNotFoundException(id));
    return ASSEMBLER.toResource(dog);
  }

  /**
   * Returns a listing of all dogs ordered by breed.
   *
   * @return A collection of dog resource objects
   */
  @GetMapping("/breeds")
  public Resources<Resource<Dog>> allByBreed() {
    List<Resource<Dog>> dogs = DOGREPO.findAll(new Sort(Sort.DEFAULT_DIRECTION, "breed"))
            .stream()
            .map(ASSEMBLER::toResource)
            .collect(Collectors.toList());

    return new Resources<>(dogs,
            linkTo(methodOn(DogController.class).allByBreed()).withSelfRel());
  }

  /**
   * Returns a listing of all dogs ordered by breed.
   *
   * @return A collection of dog resource objects
   */
  @GetMapping("/weight")
  public Resources<Resource<Dog>> allByWeight() {
    List<Resource<Dog>> dogs = DOGREPO.findAll(new Sort(Sort.DEFAULT_DIRECTION, "weight"))
            .stream()
            .map(ASSEMBLER::toResource)
            .collect(Collectors.toList());

    return new Resources<>(dogs,
            linkTo(methodOn(DogController.class).allByWeight()).withSelfRel());
  }

  /**
   * Returns a listing of all dogs of a specified breed.
   *
   * @param breed The dog breed
   * @return      A collection of dog resource objects
   */
  @GetMapping("/breeds/{breed}")
  public Resources<Resource<Dog>> findByBreed(@PathVariable String breed) {
    List<Resource<Dog>> dogs = DOGREPO.findByBreed(breed).stream()
            .map(ASSEMBLER::toResource)
            .collect(Collectors.toList());

    return new Resources<>(dogs,
            linkTo(methodOn(DogController.class).findByBreed(breed)).withSelfRel());
  }
}
