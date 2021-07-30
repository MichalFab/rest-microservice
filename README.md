## REST Service for drug applications

### Requirements:

- Java 8 (configured `JAVA_HOME` environment variable)
- Maven 3.x.x

### Running locally
#### Starting from the console
Use the command `mvn spring-boot: run` to run the project in the console.
#### Importing in Intellij IDEA
To import a project in Intellij, press `File -> New -> Project from Version Control ..`.
In the window, paste the repository address: https://github.com/MichalFab/rest-microservice.git and click the "Clone" button.
![image](https://i.ibb.co/BP2nbL6/intelij.jpg)
After importing, you can run the main class `OpenfdaApplication` to run the application.

### Documentation
Documentation is available after starting the program at: http://localhost:8080/swagger-ui.html
![swagger](https://i.ibb.co/SJBd0L0/swagger.jpg)

### Continuous integration
Continuous integration is set up in Github Actions. Build and tests start automatically after each commit. The last builds are available here:
https://github.com/MichalFab/rest-microservice/actions
