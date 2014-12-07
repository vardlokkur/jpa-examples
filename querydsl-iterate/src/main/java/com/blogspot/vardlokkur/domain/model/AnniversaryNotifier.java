package com.blogspot.vardlokkur.domain.model;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.util.Objects;

import static com.blogspot.vardlokkur.domain.model.AnniversaryPredicate.workAnniversary;

/**
 * Task notifying about the employee's work anniversary.
 *
 * @author Warlock
 * @since 1.0
 */
@Service
class AnniversaryNotifier implements Runnable {

    private final EmployeeRepository employees;

    /**
     * Constructs new instance.
     *
     * @param employees employee repository
     */
    @Inject
    protected AnniversaryNotifier(@Nonnull final EmployeeRepository employees) {
        super();

        // Verify constructor requirements, ...
        Objects.requireNonNull(employees, "Employee Repository is required.");

        // ... and initialize this instance fields.
        this.employees = employees;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public void run() {
        employees.forEach(employee -> {
            if (employee.has(workAnniversary())) {
                System.out.println(employee.getName() + " is working for us "
                    + employee.getEmploymentPeriod().getYears() + " year(s).");
            }
        });
    }

}
