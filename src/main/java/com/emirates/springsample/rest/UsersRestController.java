package com.emirates.springsample.rest;

import com.emirates.springsample.dao.UsersRepository;
import com.emirates.springsample.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Users service implementation serving {@link User} instances.
 *
 * @author alex
 * @see User
 */
@RestController
@RequestMapping("/users")
public class UsersRestController {

    @Autowired
    private UsersRepository usersRepository;

    /**
     * Queries all the {@link User} objects.
     *
     * @return all of the {@link User} entities as list
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<User> queryAll() {
        return usersRepository.findAll();
    }

    /**
     * Gets user by ID.
     *
     * @param id identifier of requested user
     * @return {@link User} object
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User retrieve(@PathVariable String id) {
        return usersRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    /**
     * Stores a new {@link User} entity.
     *
     * @param user an {@link User} instance to store
     * @return store result
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> create(@RequestBody User user) {
        User savedUser = usersRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

}
