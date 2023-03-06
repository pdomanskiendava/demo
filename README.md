# TUI test application

This is spring boot component with Swagger UI, Spring integration test, JUnit test


## Overview
Requirement contains 3 based scenarios:
* Base scenario where we call GitHub api with existing GitHub login
* Scenario where we call with not existing user
* Scenario where we send incorrect Accept header

### To implement this we used:
* [OpenAPI Generator](https://openapi-generator.tech)
* [Spring-boot based on Initializr](https://start.spring.io/)
* [Lombok](https://projectlombok.org/)
* [Springdoc (swagger ui)](https://springdoc.org/)
* [Github API](https://developer.github.com/v3)
* [Swagger](https://swagger.io/)
* [JUnit](https://junit.org/junit5/)
* [Gradle](https://gradle.org/)
* [Docker](https://www.docker.com/)

### Important URL:
* [Springdoc](http://localhost:8080/v3/api-docs/)
* [Swagger UI](http://localhost:8080/swagger-ui.html)

### Implemented endpoint address:
* /repository/{username}/

### Location of swagger file:
* resources/swagger/test_api_contract.yaml

### Logback config file:
* resources/logback-spring.xml
* <span style="color:red">really important! remember to edit LOGS property! </span>

### Docker file
* [Dockerfile](Dockerfile)

In case of problems see HELP.md file