# POC
Execute jar file:
In command prompt, enter command:
    java -jar POC-0.0.1-SNAPSHOT.jar
    
Execute using STS(Spring Tool Suite) IDE:
Install Spring Tools Suite version 3: https://github.com/spring-projects/toolsuite-distribution/wiki/Spring-Tool-Suite-3
Import the POC application
Right click POC application -> Run as -> Spring boot App

Test results using Postman:
To store account data-
    Path: localhost:8080/account
    Method: POST
    Params:
        id
        name
        balance
        city
To delete account details- 
    Path: localhost:8080/account/{id}
    Method: DELETE
To update account data- 
    Path: localhost:8080/account/{id}
    Method: PUT
    Params: 
        name
        balance
        city
To get account data- 
    Path: localhost:8080/account/{id}
    Method: GET
To get all accounts data- 
    Path: localhost:8080/account
    Method: GET
To get account data citywise- 
    Path: localhost:8080/account/city/{cityname}
    Method: GET
To update account balance- 
    Path: localhost:8080/account/balUpdate/{id}
    Method: PUT
    Params: 
        balance

Stopping Application:
In command prompt:
    ctrl + c
In STS IDE:
    Click on Stop option
