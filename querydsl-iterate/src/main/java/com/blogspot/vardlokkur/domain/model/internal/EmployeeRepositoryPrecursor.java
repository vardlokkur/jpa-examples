package com.blogspot.vardlokkur.domain.model.internal;

import com.blogspot.vardlokkur.domain.EntityIterator;
import com.blogspot.vardlokkur.domain.model.Employee;
import com.mysema.commons.lang.CloseableIterator;
import com.mysema.query.Projectable;
import com.mysema.query.jpa.JPQLTemplates;
import com.mysema.query.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Objects;
import java.util.function.Consumer;

import static com.blogspot.vardlokkur.domain.model.QEmployee.employee;

/**
 * Provides custom implementation for some of {@link com.blogspot.vardlokkur.domain.model.EmployeeRepository
 * EmployeeRepository} methods, specified in {@link EntityIterator}.
 *
 * @author Warlock
 * @see <a href="http://docs.spring.io/spring-data/jpa/docs/1.7.1.RELEASE/reference/html/#repositories.custom-implementations">Custom
 * implementations for Spring Data repositories</a>
 * @since 1.0
 */
@Repository
class EmployeeRepositoryPrecursor implements EntityIterator<Employee> {

    private final EntityManager entityManager;

    private final JPQLTemplates jpqlTemplates;

    /**
     * Constructs new instance.
     *
     * @param entityManager entity manager to be used for persistence context interactions
     * @param jpqlTemplates JPQL templates
     */
    @Inject
    protected EmployeeRepositoryPrecursor(@Nonnull final EntityManager entityManager,
                                          @Nonnull final JPQLTemplates jpqlTemplates) {
        super();

        // Verify constructor requirements, ...
        Objects.requireNonNull(entityManager, "Entity Manager is required.");
        Objects.requireNonNull(jpqlTemplates, "JPQL Templates are required.");

        // ... and initialize this instance fields.
        this.entityManager = entityManager;
        this.jpqlTemplates = jpqlTemplates;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Note that there are JPA providers requiring this method to be transactional (like some Hibernate versions).
     * Using {@link CloseableIterator} outside of the transactional context may cause database connection leak,
     * and quickly make your application unusable.
     * </p>
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void forEach(final Consumer<? super Employee> action) {

        // Create JPQL query fetching all employees, ...
        final Projectable query = new JPAQuery(entityManager, jpqlTemplates).from(employee);

        // ... iterate over all employees determined by the above query, ...
        try (final CloseableIterator<Employee> iterator = query.iterate(employee)) {

            // ... and for each employee perform action provided by the caller.
            iterator.forEachRemaining(action);
        }
    }

}
