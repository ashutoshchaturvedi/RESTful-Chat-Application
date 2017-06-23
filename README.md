# RESTful-Chat-Application
A RESTful Chat Application developed using:  
* Spring Boot
* Hibernate ORM
* My SQL
* Spring Security

#### Refer to [Wiki](https://github.com/ashutoshchaturvedi/RESTful-Chat-Application/wiki) pages for application details, usage and endpoint informations.

## How to run the application  
There are two ways to run this application, through the pre-built jar or by importing the project in choice of IDE.  
### Pre-requisites  
* My SQL Server running on port 3306 (or update application.properties under resources if running from IDE)
* The embedded tomcat server will run on port 8080, make sure it is free.
### Through Jar  
* Download the Jar file ChatApplication-1.0-SNAPSHOT.jar
* Run the jar file using below command:
```
java -jar ChatApplication-1.0-SNAPSHOT.jar
```
* pass -Dserver.port=8888 as argument to java jar statement.
***  

### By Importing in IDE  
Below steps are for Eclipse IDE  
1. Download the project and unzip in local system.
2. Open eclipse.
3. Click File > Import.
4. Type Maven in the search box under Select an import source.
5. Select Existing Maven Projects.
6. Click Next.
7. Click Browse and select the folder that was unzipped in step 1 (contains the pom.xml file)
8. Click Next.
9. Click Finish.  
* If need to change the tomcat server port, modify server.port in application.properties
10. Run ChatApplication.java present under com.cirtual package as Java Application.  
***  

## Future work/ToDo  
- [ ] Change HTTP Basic auth to OAuth  
- [ ] Add more JUnit testing for Services and Repositories
- [ ] Build UI
- [ ] Include WebSocket for runtime polling of chat messages.
- [ ] Notification to recipient user when new message is received.
