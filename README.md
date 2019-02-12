# Java Web APIs II - Building RESTful Web Services with Spring

## RESTDogs

### Introduction

Develop a simple REST Web application to work with data about dogs Dogs have an auto generated id, breed name, average 
weight, boolean that true if the breed of dog is appropriate for apartment living otherwise false.

### Instructions

Create a Java RESTful API Web application using Web, DevTools, H2, hateoas, and Jackson and JPA.

#### Data

Seed the data with the following

```` java
new Dogs("Springer", 50, false);
new Dogs("Bulldog", 50, true);
new Dogs("Collie", 50, false);
new Dogs("Boston Terrie", 35, true);
new Dogs("Corgie", 35, true);
````

#### Expose the following endpoints

| Method | Endpoint                 | Description                                                  |
| ------ | -------------------------| -------------------------------------------------------------|
| GET    | `/dogs/breeds`           | Returns a listing of all dogs ordered by breed               |
| GET    | `/dogs/weight`           | Returns a listing of all dogs ordered by average weight      |
| GET    | `/dogs/breeds/{breed}`   | Returns dogs of just that breed                              |
| GET    | `/dogs/apartment`        | Returns dogs suitable for apartments                         |
| PUT    | `/dogs/{id}`             | Adds or update if already present the dog with id value id   |
| POST   | `/dogs`                  | Adds the dog                                                 |
| DELETE | `/dogs/{id}`             | Deletes the dog with that id                                 |

#### Recommended Steps
1. Build a new project
2. Add properties (DEBUG logs) and dependencies (Jackson)
3. Create the data class
4. Create the SeedDatabase class
5. Create the repository class
6. Create the ResourceAssembler class
7. Create the Controller class
8. Create the ItemNotFoundException class
9. Now you can finally expose your endpoints!

### Stretch Goals

- Add an additional endpoint

    | Method | Endpoint                 | Description                 |
    | ------ | -------------------------| ----------------------------|
    | DELETE | `/dogs/{breeds}`         | Deletes that dog breed      |

- Throughout the project, when adding dogs make sure the breed names are unique.