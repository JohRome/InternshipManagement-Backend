# Johan's Internship Management System

## Description
+ My motivation for this project was to fully be able to set up a CI/CD-Pipeline on AWS and to host my backend on AWS using ElasticBeanstalk and EC2, which has an RDS/MySQL database connected to it.
+ During my hunt for internships, I quickly understood that you need an Internship Management System to keep track of all internships you've applied for. With this system, you can easily track all your internships in one place.
+ During my project I learned a great deal about AWS (which by the way has caused me "some" hair loss, due to the fact that things are easily broken in AWS when not taking caution). I also learned a great deal about Mockito and TestContainers.
+ It was really cool to make a frontend using React. [This InternshipManagement-frontend](https://github.com/JohRome/InternshipManagement-Frontend) is the client. Please use that when using this web service.

## Credits
* Emil Sivertsson - Encouraged me to try out TestContainers and for being a "bollplank" when times were bitter and sweet.
* ChatGPT - Used for inspiration and clarification.

## Endpoints
+ Please visit [my published requests](https://documenter.getpostman.com/view/29777299/2sA2rCSLRc#85672b5f-1115-4b5d-b53a-bfa7315b3edc) to take a closer look at what each request is doing.

## Dependencies
+ All dependencies are found in the [pom.xml](pom.xml).

## License
MIT licence

## Tests
+ Service layer. Mockito and JUnit 5 used --> [InternshipServiceTest](src/test/java/com/jromeo/internshipbackend/service/InternshipServiceTest.java).
+ Controller layer. TestContainers used --> [InternshipControllerTest](src/test/java/com/jromeo/internshipbackend/controller/InternshipControllerTest.java).
 + Before running tests inside the **InternShipControllerTest** class. Make sure your engine is running in Docker Desktop.