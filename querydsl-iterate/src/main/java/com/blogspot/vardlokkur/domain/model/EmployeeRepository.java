package com.blogspot.vardlokkur.domain.model;

import com.blogspot.vardlokkur.domain.EntityIterator;
import org.springframework.data.repository.Repository;

import java.util.UUID;

/**
 * Defines the API contract for Employee repository.
 *
 * @author Warlock, AIS.PL
 * @since 1.0
 */
public interface EmployeeRepository extends EntityIterator<Employee>, Repository<Employee, UUID> {

    // Put any Spring Data handled methods here ...

}
