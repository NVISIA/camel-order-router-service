# camel-order-router-service

## Overview
This project holds an example that leverages Apache Camel 2.17.1 and Spring Boot 1.3.5 to provide out an API around order routing. Here are the technologies used:

* Apache Maven - build management
* Apache Camel - Mediator and routing framework implementing EIPs
* Spring Boot - Opinionated framework for quickly newing up applications that leverage the Spring Framework

## Testing 
The best way to test out the API is to leverage Swagger. I've added the Swagger dist into the project's webapp folder. Just start up the server and you can access Swagger UI from the following URL:

http://localhost:8083/camel-order-router-service/swagger/index.htm 

When the page opens, simply enter the following as the API URL:

http://localhost:8083/camel-order-router-service/api/api-doc

## Notes
There is currently an issue with version mis-matches between Camel and Spring Boot using the Jackson libraries. In this example, you will see that I've need to explicitly override Boot's dependency on Jackson 2.6 in favor of Camel's supported dependency of Jackson 2.7. This will be fixed as part of the Boot 1.4 release. 


