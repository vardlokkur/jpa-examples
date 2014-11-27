# Constructor Dependency Injection and Entity Manager

Persistence Context interaction in JPA requires usage of Entity Manager, which has to be injected
into the service using it. The injection is enforced by *@PersistenceContext* annotation usage, which since
the JPA version 1.0 (up to current 2.1) can be used on *TYPE* (class), *METHOD* (setter)
or *FIELD* (the Entity Manager field itself), but cannot be used with constructors.

Workaround is possible when using:

- [Spring Framework](http://projects.spring.io/spring-framework/) IoC container
- [Spring Data JPA](http://projects.spring.io/spring-data-jpa/) provided *BeanFactoryPostProcessor* - *EntityManagerBeanDefinitionRegistrarPostProcessor*

Workaround is build upon:

- *EntityManagerBeanDefinitionRegistrarPostProcessor* registration in [Spring Framework](http://projects.spring.io/spring-framework/) IoC container,
either manually, or using *@EnableJpaRepositories* annotation, as you may see in [InfrastructureConfiguration](src/main/java/com/blogspot/vardlokkur/config/InfrastructureConfiguration.java) class
- The above post processor will search for all registered beans of type either *EntityManagerFactory* or *AbstractEntityManagerFactoryBean*,
and for each of them it will adjust the owning bean factory to be able to autowire constructor parameters of type *EntityManager*


