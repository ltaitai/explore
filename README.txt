I implemented the application in order to get indepth understanding of springboot, kafka, spring data and also dynamic configuration
of spring boot application.

The app has a crud for for user, role, kafka consumer and publisher, it also have a login.

1. From the root level of the folder
2. Compile the project : mvn clean install
3. Run Spring Boot app : mvn spring-boot:run
4. Access the application from the browser on this link : http://localhost:8181/swagger-ui.html#/
5. To publish message to kafka will have to be setup, configuration can be made available if required.


To Test if the functionality is working

You need mysql server, you then need to create a database explore, when this is done upon starting the app, table will be create.

To understand ports, database and hibernate configuration please go through application.yml

