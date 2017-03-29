package com.emirates.springsample.dao;

import java.util.Optional;

/**
 * An interface providing additional querying capabilities to the entity repositories.
 *
 * @param <T> entity type
 * @author alex
 */
public interface Querable<T> {

    Optional<T> findById(String id);

}
