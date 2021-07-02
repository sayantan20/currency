# Conversion Application

## Running the Application

### Run com.currency.conversion.ConversionApplication as a Java Application.

#### Build the application using maven

RUN
`
mvn clean package -DskipTests
`
* http://localhost:8080/currency-converter/from/USD/to/INR/quantity/5
```
{
    "id": 10001,
    "from": "USD",
    "to": "INR",
    "conversionMultiple": 65.00,
    "quantity": 5,
    "totalCalculatedAmount": 325.00
}
```
## Dockerize the appliaction

* Building the docker image

```
docker build -t convert-service:latest .
```

* Running the image in container

```
docker run -p 8080:8080 -p 8081:8081 -p 8082:8082 -d --name convert-service convert-service:latest
```
