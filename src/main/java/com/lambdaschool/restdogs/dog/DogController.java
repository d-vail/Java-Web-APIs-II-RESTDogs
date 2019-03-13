package com.lambdaschool.restdogs.dog;

import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
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
  private final DogRepository DOG_REPO;
  private final DogResourceAssembler ASSEMBLER;

  public DogController(DogRepository dogRepo, DogResourceAssembler assembler) {
    this.DOG_REPO = dogRepo;
    this.ASSEMBLER = assembler;
  }

  /**
   * Returns all dog listings.
   *
   * @return  A collection of dog resource objects
   */
  @GetMapping()
  public Resources<Resource<Dog>> all() {
    List<Resource<Dog>> dogs = DOG_REPO.findAll().stream()
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
    Dog dog = DOG_REPO.findById(id)
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
    List<Resource<Dog>> dogs = DOG_REPO.findAll(new Sort(Sort.DEFAULT_DIRECTION, "breed"))
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
    List<Resource<Dog>> dogs = DOG_REPO.findAll(new Sort(Sort.DEFAULT_DIRECTION, "weight"))
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
    List<Resource<Dog>> dogs = DOG_REPO.findByBreed(breed).stream()
            .map(ASSEMBLER::toResource)
            .collect(Collectors.toList());

    return new Resources<>(dogs,
            linkTo(methodOn(DogController.class).findByBreed(breed)).withSelfRel());
  }

  /**
   * Returns a listing of all dogs suitable for apartments.
   *
   * @return  A collection of dog resource objects
   */
  @GetMapping("/apartment")
  public Resources<Resource<Dog>> findByApartment() {
    List<Resource<Dog>> dogs = DOG_REPO.findByApartmentIsTrue().stream()
            .map(ASSEMBLER::toResource)
            .collect(Collectors.toList());

    return new Resources<>(dogs,
            linkTo(methodOn(DogController.class).findByApartment()).withSelfRel());
  }

  /**
   * Add, or update if already present, a single dog listing with the given id.
   *
   * @return                    A Response Entity; a response body with Created status
   * @throws URISyntaxException If there was a problem updating the record
   */
  @PutMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Dog updatedDog) throws URISyntaxException {
    // If dog exists update fields, else set id on updatedDog and save as new record
    Dog dogToUpdate = DOG_REPO.findById(id)
            .map(dog -> {
              dog.setBreed(updatedDog.getBreed());
              dog.setWeight(updatedDog.getWeight());
              dog.setApartment(updatedDog.isApartment());
              return DOG_REPO.save(dog);
            }).orElseGet(() -> {
              updatedDog.setId(id);
              return DOG_REPO.save(updatedDog);
            });

    // Convert Dog object to Resource object
    Resource<Dog> dogResource = ASSEMBLER.toResource(dogToUpdate);

    // Return Response Entity with HTTP 201 Created status
    return ResponseEntity
            .created(new URI(dogResource.getId().expand().getHref()))
            .body(dogResource);
  }

  /**
   * Create a new dog record.
   *
   * @param newDog              A JSON object with data for a new dog
   * @return                    A Response Entity; a response body with Created status
   * @throws URISyntaxException If there was a problem creating the record
   */
  @PostMapping
  public ResponseEntity<?> create(@RequestBody Dog newDog) throws URISyntaxException {
    // Save the new dog
    Dog dogToCreate = DOG_REPO.save(newDog);

    // Convert Dog object to Resource object
    Resource<Dog> dogResource = ASSEMBLER.toResource(dogToCreate);

    // Return Response Entity with HTTP 201 Created status
    return ResponseEntity
            .created(new URI(dogResource.getId().expand().getHref()))
            .body(dogResource);
  }

  /**
   * Delete the dog with the given id.
   *
   * @param id  A dog id
   * @return    A Response Entity; a response body with No Content status
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    DOG_REPO.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  /**
   * Delete the dog of the given breed.
   *
   * @param breed  A dog breed
   * @return       A Response Entity; a response body with No Content status
   */
  @DeleteMapping("/breeds/{breed}")
  public ResponseEntity<?> delete(@PathVariable String breed) {
    DOG_REPO.deleteByBreed(breed);
    return ResponseEntity.noContent().build();
  }
}
