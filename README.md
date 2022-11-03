# Recipe Library Service

Recipe Library Service is a recipe application which has functionality like creating, updating, deleting, searching.

Features:
1. Create, Update, Delete recipe with ingredients
2. View Recipe details
3. Search with criterias like, recipeId, number of servicing, vegetarian, including and excluding ingredients, keyword to search in instructions

Service Documentation
- Service documentation will be accesible after running the service on local via this url <http://localhost:8181/swagger-ui/index.html> 

## Technology and Requirements

Developed using Java Programming Language (Version 17) and Spring Boot Framework (Version 2.7.5).

Prerequisites include:

* Java Software Developer's Kit (SE) 17 or higher
* Apache Maven 3.3 or higher


## Build and run the project

To build and run the project:

1. Clone the repo to local
2. Build project by running `mvn clean install`
3. Run the application `mvn -Dspring-boot.run.jvmArguments=-Dspring.profiles.active=local spring-boot:run`
4. After the message `Started RecipeLibraryApplication ...` displays, enter the following URL in a browser: <http://localhost:8181/swagger-ui/index.html> to access swagger UI

*Data is persisted in a in-memory database on local 

## Test the project

To run tests:

1. Use a command window to change into the example project directory, for example: `cd recipe-library`

2. Enter: `mvn test` to run all tests

3. After the execution of the test console will display the results



