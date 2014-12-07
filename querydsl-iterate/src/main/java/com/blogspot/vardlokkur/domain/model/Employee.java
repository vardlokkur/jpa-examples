package com.blogspot.vardlokkur.domain.model;

import javax.annotation.Nonnull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;
import java.util.function.Predicate;

/**
 * Employee.
 *
 * @author Warlock
 * @since 1.0
 */
@Entity
@Table(name = "EMPLOYEE")
public class Employee {

    @Column(name = "EMPLOYED_ON")
    private LocalDate dateEmployed;

    @Column(name = "ID")
    @Id
    private UUID id;

    @Column(name = "NAME")
    private String name;

    /**
     * Constructs new instance.
     */
    protected Employee() {
        super();
    }

    /**
     * @return the employment period for this Employee
     */
    @Nonnull
    public Period getEmploymentPeriod() {
        return Period.between(dateEmployed, LocalDate.now());
    }

    /**
     * @return the name of this Employee
     */
    @Nonnull
    public String getName() {
        return name;
    }

    /**
     * @param feature the predicate to be matched by employees having some feature
     * @return {@code true} if this employee has given {@code feature}, {@code false} otherwise
     */
    public boolean has(@Nonnull final Predicate<Employee> feature) {
        return feature.test(this);
    }

}
