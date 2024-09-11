# grocery-store
That's a Project useful to create order customer for a grocery store, add products, apply promotions when is applicable, generate rhe customer savings and resume of an order.

In this project, I used Java 17 and framework Spring Boot at 3.3.3 version, including lombok, wiremock and swagger.

The architecture used was Spring MVC, witch has the pattern model-view-controller, using principles of object oriented programming with interfaces and implementations based on controller, service and repository. 

Required: Java 17 and Maven 4.0.0.

Project configs: 
# Clone the repository:
git clone https://github.com/luanalbm/grocery-store.git

# Set up the environment:
Make sure you have Java 17 and Maven installed. You can check this with:
```
java -version
mvn -version
```
3 - Install dependencies:
```
mvn install
```
4 - Init the application:
```
mvn spring-boot:run
```

The project will be available at 
http://localhost:8080

You can see the API documentation at
http://localhost:8080/swagger-ui.html .
