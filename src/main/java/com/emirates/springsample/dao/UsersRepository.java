package com.emirates.springsample.dao;

import com.emirates.springsample.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Data repository for {@link User} entities.
 *
 * @author alex
 * @see User
 */
public interface UsersRepository extends MongoRepository<User, String>, Querable<User> {

}
