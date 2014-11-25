package com.blogspot.vardlokkur.domain.model;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Create employer command.
 *
 * @author Warlock
 * @since 1.0
 */
@Immutable
public final class CreateEmployer {

    private final String name;

    /**
     * Constructs new instance.
     *
     * @param name name of the employer
     */
    public CreateEmployer(@Nonnull final String name) {
        super();

        // Verify constructor requirements, ...
        Objects.requireNonNull(name, "Name is required.");

        // ... and initialize this instance fields.
        this.name = name;
    }

    /**
     * @return the name of the employer
     */
    @Nonnull
    public String getName() {
        return name;
    }

    /**
     * Defines the API contract for this command handler.
     */
    public interface Handler extends Consumer<CreateEmployer> {

        // Empty by design ...
    }

}
