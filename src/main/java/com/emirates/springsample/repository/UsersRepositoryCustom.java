package com.emirates.springsample.repository;

import com.emirates.springsample.domain.Country;
import com.emirates.springsample.domain.User;

import java.util.List;
import java.util.Map;

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


    /**
     * Counts all active users by their citizenship
     *
     * @return a Map containing user count per country.
     */
    Map<Country, Integer> countByCitizenship();

}
