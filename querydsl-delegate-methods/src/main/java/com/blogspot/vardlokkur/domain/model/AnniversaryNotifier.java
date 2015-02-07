package com.blogspot.vardlokkur.domain.model;

import com.mysema.commons.lang.CloseableIterator;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.JPQLTemplates;
import com.mysema.query.jpa.impl.JPAQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

import static com.blogspot.vardlokkur.domain.model.QEmployee.employee;

/**
 * Task notifying about the employee's work anniversary.
 *
 * @author Warlock
 * @since 1.0
 */
@Service
class AnniversaryNotifier implements Runnable {

    private final EntityManager entityManager;

    private final JPQLTemplates jpqlTemplates;

    /**
     * Constructs new instance.
     *
     * entity manager to be used for persistence context interactions
     *
     * @param jpqlTemplates JPQL templates
     */
    @Inject
    protected AnniversaryNotifier(@Nonnull final EntityManager entityManager,
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
     */
    @Override
    @Transactional(readOnly = true)
    public void run() {
        try (final CloseableIterator<Tuple> iterator =
                 new JPAQuery(entityManager, jpqlTemplates).from(employee)
                                                           .where(employee.hasAnniversary())
                                                           .iterate(employee.name, employee.dateEmployed)) {
            while (iterator.hasNext()) {
                final Tuple tuple = iterator.next();
                final String employeeName = tuple.get(employee.name);
                final LocalDate dateEmployed = tuple.get(employee.dateEmployed);
                final Period employmentPeriod = Period.between(dateEmployed, LocalDate.now());
                System.out.println(employeeName + " is working for us " + employmentPeriod.getYears() + " year(s).");
            }
        }
    }

}
