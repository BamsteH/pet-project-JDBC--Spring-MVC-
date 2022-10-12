# pet-project-JDBC--Spring-MVC-

Docker:
Start docker-compose.yml with ur settings

After downloading volumes and initialize docker containers start init.sql from src/main/resources/ for creating tables

In directory src/main/resources/appplication.propeties set up 
datasource username,password and url (also connect Driver your in  datasource.driver-class-name jdbc for MySql server) 

Reload all Maven Project dependencies in pom.xml

Start the project src/main/java/com/example/demo/DemoApplication.java using Java11

Go to URL
http://localhost:*port*/swagger-ui/index.html?configUrl=/v2/api-docs/swagger-config#/
