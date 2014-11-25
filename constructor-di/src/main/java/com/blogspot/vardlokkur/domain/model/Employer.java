package com.blogspot.vardlokkur.domain.model;

import javax.annotation.Nonnull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
import java.util.UUID;

/**
 * Employer.
 *
 * @author Warlock
 * @since 1.0
 */
@Entity
@Table(name = "EMPLOYER")
public class Employer {

    @Column(name = "ID")
    @Id
    private UUID id;

    @Column(name = "NAME", nullable = false)
    private String name;

    /**
     * Constructs new instance.
     */
    protected Employer() {
        super();
    }

    /**
     * Constructs new instance.
     *
     * @param name the name of the employer
     */
    public Employer(@Nonnull final String name) {
        this();

        // Verify constructor requirements, ...
        Objects.requireNonNull(name, "Employer name is required.");

        // ... and initialize this instance fields.
        id = UUID.randomUUID();
        this.name = name;
    }

    /**
     * @return the name of this Employer
     */
    public String getName() {
        return name;
    }

}
