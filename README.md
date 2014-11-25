# Miscellaneous JPA setup/usage examples

## [Constructor Dependency Injection and Entity Manager](constructor-di)

The problem:
Persistence Context interaction in JPA requires usage of Entity Manager, which has to be injected
into the service using it. The injection is enforced by *@PersistenceContext* annotation usage, which since
the JPA version 1.0 (up to current 2.1) can be used on *TYPE* (class), *METHOD* (setter)
or *FIELD* (the Entity Manager field itself), but cannot be used with constructors.

This example provides workaround for this situation, allowing *Constructor Dependency Injection* usage
instead of *Field Dependency Injection*, when using [Spring Framework](http://projects.spring.io/spring-framework/).
