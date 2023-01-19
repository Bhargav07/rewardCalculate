# Rewards Calculation Application


This is a Spring Boot application that provides a RESTful API to calculate rewards points earned by customers based on their transactions. The application uses Spring Boot, Spring Data JPA and H2 database.

 
### Requirements

- Java 11 or later
- Maven 3.5 or later
### Getting Started
 - Clone the repository and navigate to the project directory.
### Running tests
   To run the tests, execute the following command:
```mvn test```

### Running App

Run the application using the following command :
- ```mvn spring-boot:run```

The application will be running at `http://localhost:8080/`

### API Endpoints
The following endpoints are available in the application:

- Retrieve rewards points for a customer
- Retrieve the rewards points earned by a specific customer for a given month and total.

```
URL : /rewards/{customerId}
Method : GET
URL Params : customerId=[string]
```



Retrieve the rewards points earned by all customers for a given month and total.
```
URL : /rewards
Method : GET
```

Add a new transaction for a customer
```
URL : /transactions
Method : POST
Body :
{
"customerId": "CUST-001",
"transactionAmount": 120,
"transactionDate": "2022-11-10"
}
 ```

 Sample curl command
 ```
 curl -X POST -H "Content-Type: application/json" -d '{"customerId":"CUST-001","transactionAmount":120,"transactionDate":"2022-11-10"}' http://localhost:8080/transactions
 ```
