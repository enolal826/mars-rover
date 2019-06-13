# MARS ROVER - Wallapop


**Important:** I haven't been managed to configure Maven to generate a Kotlin executable.
I'm new to Kotlin and that is the first improvement of this development

## Package distribution
The aim of this application is to provide a common interface for the movement
of the Mars Rover around a map.

##### "api" package
The classes inside this package belong to the common interface its data model,
they impose some light restrictions on how implemetation must be done.

##### "app" package
A possible implementation of the MarsRoverService interface

##### "user" package
Regardless of the implementation of the interface, we use this package as a separation
of concerns between the service itself and how we collect data from the user.

The data collection could be done just by reading from standard input or even exposing
a REST API through a remote channel

## Design considerations

### API
Since the whole purpose of the app is to move a rover, the interface just provides the very basic
actions: move() and getMarsRoverPosition().

It does not even impose a restriction on the type of map
to be used, as it is open for a map structure change in the future

### Implementation
However, the service implementation does impose a restriction in terms
of its dependencies.

Since this service does not make sense without a constructed map and a constructed rover,
there is a composition relation.

Anyway, the implementation of this service might have been different as long as it complies with
the interface

#### · Locator
This class holds the business logic for the movement of the rover, it has a composition relation with
the Map class

#### · Map
This class encapsulates how the grid is built. We are using the Kotlin data structure Array

We are taking as a reference the standardx and y axes but to simplify the implementation, we are
considering the zero (0) matches with the natural zero position in a matrix. That is:

|       |       |       |
|-------|-------|-------|
| (0,0) | (1,0) | (2,0) |
| (0,1) | (1,1) | (2,1) |
| (0,2) | (1,2) | (2,2) |
| (0,3) | (1,3) | (2,3) |

## Test
**Important:** I have used the Spock framework, which has some limitations working with Kotlin.
As any class, function or member is "final" in Kotlin, I have added the keyword "open" just for
testing purposes.

## Improvements
- In a real-world problem, having more information about the API and the requirements, maybe
the aproach of this implementation might have been different (e.g. exposing it through a computer network) 

Regarding tests
- Find a way to integrate Spock with Kotlin without using "open" keyword for just testing purposes
- Map class unit testing, finding the way to mock array Kotlin built-in class
- Unify data used in test when possible to avoid duplicate code