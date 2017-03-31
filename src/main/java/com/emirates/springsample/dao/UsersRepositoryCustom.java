package com.emirates.springsample.dao;

import com.emirates.springsample.domain.User;

import java.util.List;

/**
 * This is an extended Users repository interface that does not contain any Spring auto-generated methods. Custom implementation is provided instead.
 *
 * @author alex
 * @see UsersRepositoryImpl
 */
public interface UsersRepositoryCustom {

    /**
     * Searches for {@link User} entities that matches the given criteria if provided.
     *
     * @param name   name name to search for using like
     * @param active   active flag
     * @return users matching criteria
     */
    List<User> search(String name, Boolean active);
}
