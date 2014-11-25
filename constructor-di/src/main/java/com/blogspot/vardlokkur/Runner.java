package com.blogspot.vardlokkur;

import com.blogspot.vardlokkur.config.InfrastructureConfiguration;
import com.blogspot.vardlokkur.domain.model.CreateEmployer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Example runner.
 *
 * @author Warlock
 * @since 1.0
 */
public class Runner {

    public static void main(String ... params) {

        // Create an application context, ...
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(InfrastructureConfiguration.class);

        // ... and for specific command instance, ...
        final CreateEmployer command = new CreateEmployer("Mighty Ducks");

        // ... handle it using command handler existing in application context.
        applicationContext.getBean(CreateEmployer.Handler.class).accept(command);
    }

}
