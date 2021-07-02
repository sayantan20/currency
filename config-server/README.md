# Config-server
## Running the Application
### Run com.currency.configserver.ConfigServerApplication as a Java Application.

* http://localhost:8888/exchange-service-dev/default
````
{
name: "exchange-service-dev",
profiles: [
"default"
],
label: null,
version: "bf37b165f880dd972b09ff142d1ded3539307e36",
state: null,
propertySources: [
{
name: "https://github.com/sayantan20/config.git/exchange-service-dev.yml",
source: {
server.port: 9090,
endpoints.actuator.enabled: true,
user.role: "Developer",
eureka.client.serviceUrl.defaultZone: "http://eureka-server:8761/eureka/",
management.endpoint.info.enabled: true,
management.endpoint.web.base-path: "/manage",
management.endpoint.web.expose.include: "*",
spring.application.name: "exchange-service",
spring.h2.console.enabled: true,
spring.h2.console.path: "/h2",
spring.datasource.platform: "h2",
spring.datasource.url: "jdbc:h2:mem:bankdb",
spring.datasource.driverClassName: "org.h2.Driver",
spring.datasource.username: "sa",
spring.datasource.password: "",
spring.jpa.database-platform: "org.hibernate.dialect.H2Dialect",
spring.jpa.show-sql: true,
spring.jpa.properties.hibernate.format_sql: true,
spring.jpa.hibernate.ddl-auto: "create",
}
}
]
}
````
* http://localhost:8888/convert-service-dev/default
```
{
name: "convert-service-dev",
profiles: [
"default"
],
label: null,
version: "bf37b165f880dd972b09ff142d1ded3539307e36",
state: null,
propertySources: [
{
name: "https://github.com/sayantan20/config.git/convert-service-dev.yml",
source: {
spring.application.name: "convert-service",
spring.h2.console.enabled: true,
spring.h2.console.path: "/h2",
spring.datasource.platform: "h2",
spring.datasource.url: "jdbc:h2:mem:bankdb",
spring.datasource.driverClassName: "org.h2.Driver",
spring.datasource.username: "sa",
spring.datasource.password: "",
spring.jpa.database-platform: "org.hibernate.dialect.H2Dialect",
spring.jpa.properties.hibernate.format_sql: true,
spring.jpa.show-sql: true,
spring.jpa.hibernate.ddl-auto: "create",
spring.zipkin.base-url: "http://zipkin-server:9411/",
server.port: 8080,
endpoints.actuator.enabled: true,
user.role: "Developer",
eureka.client.serviceUrl.defaultZone: "http://eureka-server:8761/eureka/",
management.endpoint.info.enabled: true,
management.endpoint.web.base-path: "/manage",
management.endpoint.web.expose.include: "*"
}
}
]
}

```