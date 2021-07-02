# Exchange Application 

## Running the Application

### Run com.currency.exchange.ExchangeApplication as a Java Application.

#### Build the application using maven

RUN
`
mvn clean package -DskipTests
`
* http://localhost:9090/currency/USD/to/INR
```
{
    "id": 10001,
    "from": "USD",
    "to": "INR",
    "conversionMultiple": 65.00
}
```
## Dockerize the appliaction

* Building the docker image

```
docker build -t exchange-service:latest .
```

* Running the image in container

```
docker run -p 9090:9090 -p 9091:9091 -p 9092:9092 -d --name exchange-service exchange-service:latest
```
