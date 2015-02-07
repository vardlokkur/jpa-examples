package com.blogspot.vardlokkur.domain.model;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.annotations.QueryDelegate;
import com.mysema.query.types.Predicate;

import javax.annotation.Nonnull;
import java.time.LocalDate;

/**
 * Provides set of QueryDSL delegate methods for {@link Employee}.
 *
 * @author Warlock
 * @since 1.0
 */
public class EmployeeSupplement {

    @QueryDelegate(Employee.class)
    public static Predicate hasAnniversary(@Nonnull final QEmployee employee) {
        final LocalDate today = LocalDate.now();
        final BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(employee.dateEmployed.month().eq(today.getMonthValue()));
        predicate.and(employee.dateEmployed.dayOfMonth().eq(today.getDayOfMonth()));
        return predicate;
    }
}
