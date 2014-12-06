package com.blogspot.vardlokkur;

import com.blogspot.vardlokkur.config.InfrastructureConfiguration;
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

        // ... get the task we want to run, ...
        final Runnable task = applicationContext.getBean("anniversaryNotifier", Runnable.class);

        // ... and go for it!
        task.run();
    }

}
