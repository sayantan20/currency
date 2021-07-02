# Eureka

## Running the Application

### Run com.currency.eureka.EurekaApplication as a Java Application.

#### Build the application using maven
eureka-server
RUN
`
mvn clean package -DskipTests
`
* http://localhost:8761 

This will open Spring Eureka [eureka-server](https://spring.io/guides/gs/service-registration-and-discovery/)


## Dockerize the appliaction

* Building the docker image

```
docker build -t eureka-server:latest .
```

* Running the image in container

```
docker run -p 8761:8761 -d --name eureka-server eureka-server:latest
```
