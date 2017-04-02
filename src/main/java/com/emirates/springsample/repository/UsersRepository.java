package com.emirates.springsample.repository;

import com.emirates.springsample.domain.Country;
import com.emirates.springsample.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Data repository for {@link User} entities.
 *
 * @author alex
 * @see User
 */
public interface UsersRepository extends MongoRepository<User, String>, UsersRepositoryCustom {

    Optional<User> findById(String id);

    Stream<User> findByCitizenship(Country citizenship);

}
