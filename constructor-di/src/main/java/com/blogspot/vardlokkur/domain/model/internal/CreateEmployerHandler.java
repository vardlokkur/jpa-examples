package com.blogspot.vardlokkur.domain.model.internal;

import com.blogspot.vardlokkur.domain.model.CreateEmployer;
import com.blogspot.vardlokkur.domain.model.Employer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Objects;

/**
 * Default {@link CreateEmployer} command handler.
 *
 * @author Warlock
 * @since 1.0
 */
@Service
public class CreateEmployerHandler implements CreateEmployer.Handler {

    private final EntityManager entityManager;

    /**
     * Constructs new instance.
     *
     * @param entityManager entity manager to be used for persistence context interactions
     */
    @Inject
    public CreateEmployerHandler(@Nonnull final EntityManager entityManager) {
        super();

        // Verify constructor requirements, ...
        Objects.requireNonNull(entityManager, "Entity Manager is required.");

        // ... and initialize this instance fields.
        this.entityManager = entityManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public void accept(@Nonnull final CreateEmployer command) {
        entityManager.persist(new Employer(command.getName()));
    }

}
