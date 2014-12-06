package com.blogspot.vardlokkur.domain;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

/**
 * Defines the API contract for entity iterator.
 *
 * @param <E> the type of entities
 * @author Warlock
 * @since 1.0
 */
public interface EntityIterator<E> {

    /**
     * Performs given {@code action} for each entity until all entities have been processed or the action
     * throws an exception.
     *
     * @param action the action to be performed for each entity
     * @throws NullPointerException if the specified action is null
     */
    void forEach(@Nonnull final Consumer<? super E> action);

}
