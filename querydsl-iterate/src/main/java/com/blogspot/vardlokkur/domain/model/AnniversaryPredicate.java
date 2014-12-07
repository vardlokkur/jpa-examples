package com.blogspot.vardlokkur.domain.model;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import java.time.Period;
import java.util.function.Predicate;

/**
 * Predicate matched by the employees having work anniversary.
 *
 * @author Warlock, AIS.PL
 * @since 1.0
 */
@Immutable
final class AnniversaryPredicate implements Predicate<Employee> {

    private static final AnniversaryPredicate INSTANCE = new AnniversaryPredicate();

    /**
     * Constructs new instance.
     */
    private AnniversaryPredicate() {
        super();
    }

    /**
     * @return predicate matched by employees having work anniversary
     */
    public static Predicate<Employee> workAnniversary() {
        return AnniversaryPredicate.INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test(@Nullable final Employee employee) {
        boolean result = false;
        if (null != employee) {
            final Period employmentPeriod = employee.getEmploymentPeriod();
            final int years = employmentPeriod.getYears();
            result = employmentPeriod.equals(Period.ofYears(years));
        }
        return result;
    }

}
