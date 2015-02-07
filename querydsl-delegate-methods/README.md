# QueryDSL delegate methods

One of the interesting [QueryDSL](http://www.querydsl.com/) features are _Delegate Methods_. Their usage allows adding
custom predicates to the Q-beans generated automatically by _QueryDSL_.

Before examining the example itself, you may be interested in reading [QueryDSL 3.6.0 - Delegate Methods](http://www.querydsl.com/static/querydsl/3.6.0/reference/html/ch03s03.html#d0e2357).

You may start with _hasAnniversary_ method of
[EmployeeSupplement](src/main/java/com/blogspot/vardlokkur/domain/model/EmployeeSupplement.java).
This method is marked as Delegate Method for Employee Q-bean (using _@QueryDelegate_ annotation).
When _QueryDSL_ annotation processor will process domain model, following method will be added to Employee Q-bean:
```java
public class QEmployee extends EntityPathBase<Employee> {
    ...
    public com.mysema.query.types.Predicate hasAnniversary() {
        return EmployeeSupplement.hasAnniversary(this);
    }
    ...
}
```
That will allow you to use _hasAnniversary_ predicate for Employees, as you may see in
[AnniversaryNotifier](src/main/java/com/blogspot/vardlokkur/domain/model/AnniversaryNotifier.java)
```
    new JPAQuery(entityManager, jpqlTemplates).from(employee)
                                              .where(employee.hasAnniversary())
                                              .iterate(employee.name, employee.dateEmployed)
```


