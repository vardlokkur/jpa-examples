# QueryDSL based iterating over set of entities

[QueryDSL](http://www.querydsl.com/), among all interesting features, provides possibility of iterating over set
 of entities defined by JPQL query. This feature is implemented using JDBC Scrollable Result Sets in the background,
 and provides fast and reliable method of processing large entities sets without loading all the entities into memory.

You may take a look at _forEach_ method of
[EmployeeRepositoryPrecursor](src/main/java/com/blogspot/vardlokkur/domain/model/internal/EmployeeRepositoryPrecursor.java).
First we create JPQL query fetching some set employees (all existing in our case), then we create _CloseableIterator_
and enclose it within [try-with-resources](http://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html)
statement to automatically close it on exit. Having this iterator we may iterate over all entities returned by JPQL query,
and perform some action for each of them.

This example demonstrates also custom implementation of Spring Data JPA based repository methods.
First we define custom methods in separate interface (or set of interfaces), like
[EntityIterator](src/main/java/com/blogspot/vardlokkur/domain/EntityIterator.java).
Then we provide the implementation of the methods, like
[EmployeeRepositoryPrecursor](src/main/java/com/blogspot/vardlokkur/domain/model/internal/EmployeeRepositoryPrecursor.java)
and let Spring Framework know it should create the instance of the implementing class, using _@Repository_ annotation.
Final touch is made in
[InfrastructureConfiguration](src/main/java/com/blogspot/vardlokkur/config/InfrastructureConfiguration.java) class,
using
```
    @ComponentScan("com.blogspot.vardlokkur.domain")
    @EnableJpaRepositories(
        basePackages = {"com.blogspot.vardlokkur.domain"},
        repositoryImplementationPostfix = "Precursor")
```
Spring Framework will find [EmployeeRepository](src/main/java/com/blogspot/vardlokkur/domain/model/EmployeeRepository.java),
create bean named _employeeRepository_ and delegate all the methods defined in our custom interface (_EntityIterator_)
to the bean named _employeeRepositoryPrecursor_.
