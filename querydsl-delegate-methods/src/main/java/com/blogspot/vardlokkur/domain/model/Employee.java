package com.blogspot.vardlokkur.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

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

}
