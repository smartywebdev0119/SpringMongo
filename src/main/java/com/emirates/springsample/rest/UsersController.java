package com.emirates.springsample.rest;

import com.emirates.springsample.domain.User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Users service implementation serving {@link User} instances.
 *
 * @author alex
 * @see User
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    @RequestMapping("/hello")
    public String index() {
        return "hello";
    }

    /**
     * Gets user by ID.
     *
     * @param id identifier of requested user
     * @return {@link User} object
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable String id) {
        return new User(id, "Milena", LocalDate.of(1989, 12, 28));
    }

}
