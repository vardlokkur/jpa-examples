# Miscellaneous JPA setup/usage examples

## [Constructor Dependency Injection and Entity Manager](constructor-di)

Persistence Context interaction in JPA requires usage of Entity Manager, which has to be injected
into the service using it. The injection is enforced by *@PersistenceContext* annotation usage, which since
the JPA version 1.0 (up to current 2.1) can be used on *TYPE* (class), *METHOD* (setter)
or *FIELD* (the Entity Manager field itself), but cannot be used with constructors.

This example provides workaround for this situation, allowing *Constructor Dependency Injection* usage
instead of *Field/Setter Dependency Injection*, when using [Spring Framework](http://projects.spring.io/spring-framework/).

## [QueryDSL based iterating over set of entities](querydsl-iterate)

[QueryDSL](http://www.querydsl.com/), among all interesting features, provides possibility of iterating over set
 of entities defined by JPQL query. This feature is implemented using JDBC Scrollable Result Sets in the background,
 and provides fast and reliable method of processing large entities sets without loading all the entities into memory.

This example demonstrates also custom implementation of Spring Data JPA based repository methods.

## [QueryDSL delegate methods](querydsl-delegate-methods)

One of the interesting [QueryDSL](http://www.querydsl.com/) features are _Delegate Methods_. Their usage allows adding
custom predicates to the Q-beans generated automatically by _QueryDSL_.

Before examining the example itself, you may be interested in reading [QueryDSL 3.6.0 - Delegate Methods](http://www.querydsl.com/static/querydsl/3.6.0/reference/html/ch03s03.html#d0e2357).
