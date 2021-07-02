# Netflix-Zuul-API

## Running the Application

### Run com.currency.zuul.ZuulApplication as a Java Application.

#### Build the application using maven
eureka-server
RUN
`
mvn clean package -DskipTests
`
* http://localhost:8765/api


## Dockerize the appliaction

* Building the docker image

```
docker build -t netflix-zuul-api:latest .
```

* Running the image in container

```
docker run -p 8765:8765 -d --name netflix-zuul-api netflix-zuul-api:latest
```
