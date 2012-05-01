# Example domain

Here is an example domain to use as a staring point.

## Technologies
Spring, JAXB, JPA via the Hibernate EntityManager, Validation, JUnit with Hamcrest.

## What's what
There are two domain classes: User and Role. 
Both are JPA entities with relationships specified as annotations.
(Hibernate is included from the pom)
The fields include javax.validation annotations.

There are XML annotations to help out JAXB; there is a JAXBHelper and a JAXB List wrapper (UserList) to see this.

There are DAO and Service layers.

DefaultUserDAO harcodes the test data in a Map. 

JDBCUserDAO is just stubs, but there is a partial JPA DAO implementation to get you started.

DefaultUserService is injected by Spring.

There are two tests, each with their own Spring context.
- UserTest
- UserServiceTest

UserTest uses the Spring validation config (org.springframework.validation.beanvalidation.LocalValidatorFactoryBean)
and includes a shouldFailValidation example test.


## Usage

    mvn test
    or
    mvn -Dtest=com.rockhoppertech.example.domain.UserTest test
    mvn -Dtest=com.rockhoppertech.example.domain.UserServiceTest test
    
    

## License

Copyright (C) 2007 Gene De Lisa

Distributed under the Eclipse Public License.
