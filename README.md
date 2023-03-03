# TUI test application

This is spring boot component with Swagger UI, Spring integration test, JUnit test

## Overview
Requirement contains 3 based scenarios:
* Base scenario where we call GitHub api with existing GitHub login
* Scenario where we call with not existing user
* Scenario where we send incorrect Accept header

To implement this we used:
* [OpenAPI Generator](https://openapi-generator.tech)
* [Spring-boot based on Initializr](https://start.spring.io/)
* [Lombok](https://projectlombok.org/)
* [Springdoc (swagger ui)](https://springdoc.org/)
* [Github API](https://developer.github.com/v3)
* [Swagger](https://swagger.io/)
* [JUnit](https://junit.org/junit5/)
* [Gradle](https://gradle.org/)

Important URL:
* [Springdoc](https://springdoc.org)
* [Swagger UI](localhost:8080/swagger-ui.html)

Implemented endpoint address:
* /repository/{username}/

Location of swagger file:
* resources/swagger/test_api_contract.yaml

Logback config file:
* resources/logback-spring.xml

In case of problems see HELP.md file