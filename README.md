# ProductService

Implement a REST service in java that reads data from datasource ( CSV file) and present in possible JSON format to customer as a JSON response .
These are the below end points available with this service 

###### Endpoint - 
1. Gives all the products from data source
```
/product
Ex - http://localhost:8080/product
```
2. Gives specific product from the given data source based on the productId
```
/product/12341
Ex - http://localhost:8080/product/12341
```
### Technology Stack & API -
- Java 8
- Spring boot 2
- Spring REST API
- Opencsv
- Swagger API -  for easy testing, documentation and demonstration
- Mockito ,JUnit
- Maven

### Prerequisite -

- Java 8.
- Maven.
- CSV file (datasource) should be class path else please give the absolute path in application.properties file.

All necessary api for tech stack

### Run application -
To build the application, .
```
mvn clean install
```
#### Swagger Access -
```
http://localhost:8080/swagger-ui.html
```
