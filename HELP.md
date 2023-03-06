### Basic usage information

This file will contain information how to work with project

1. How to build project?
   * ./gradle clean build
2. Where is swagger ui?
   * http://localhost:8080/swagger-ui/index.html
3. How can I configure logs? 
   * resources/logback-spring.xml
4. How to configure component contract? Where is swagger?
   * resources/swagger/test_api_contract.yaml
5. How to run project?
   * Easies it to run from [DemoApplication.java](src%2Fmain%2Fjava%2Fcom%2Fendava%2Fdemo%2FDemoApplication.java) in your IDE
   * There is possibility of run as "java -jar [demo-0.0.1-SNAPSHOT.jar](build%2Flibs%2Fdemo-0.0.1-SNAPSHOT.jar)". Point 1. is required first
6. How to run docker container?
   * docker run -p 8080:8080 endava/tui_demo
7. "PROBLEM_WITH_DOWNLOAD_REPOSITORY_LIST: 401 Unauthorized:"
   * [application.properties](src%2Fmain%2Fresources%2Fapplication.properties) token is deleted by GitHub, please add github.token new value