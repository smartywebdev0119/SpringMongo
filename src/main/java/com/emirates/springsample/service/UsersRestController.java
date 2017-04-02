package com.emirates.springsample.service;

import com.emirates.springsample.domain.User;
import com.emirates.springsample.events.UserChangedEvent;
import com.emirates.springsample.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import javax.validation.Valid;
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

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    /**
     * Searches for {@link User} entities that matches the given criteria if provided.
     *
     * @param name   name name to search for using like
     * @param active active flag
     * @return users matching the given criteria
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<User> search(@RequestParam(required = false) String name, @RequestParam(required = false) Boolean active) {
        return usersRepository.search(name, active);
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
     * @return {@link HttpStatus#CREATED} and persisted user as body or {@link HttpStatus#BAD_REQUEST} when user already exists.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> create(@RequestBody @Valid User user) {
        if (!user.isPersistent()) {
            //save user to the database
            User savedUser = usersRepository.save(user);
            UriComponents userUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("users/").path(savedUser.getId()).build();

            //publish event to notify listeners
            eventPublisher.publishEvent(new UserChangedEvent(savedUser.getId()));
            return ResponseEntity.created(userUri.toUri()).build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    /**
     * Updates an existing {@link User} entity.
     *
     * @param user an {@link User} instance to update
     * @return {@link HttpStatus#NO_CONTENT} when successful or {@link HttpStatus#BAD_REQUEST} when given user does not exist.
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<User> update(@RequestBody @Valid User user) {
        if (user.isPersistent()) {
            usersRepository.save(user);

            //publish event to notify listeners
            eventPublisher.publishEvent(new UserChangedEvent(user.getId()));

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Delete user by ID.
     *
     * @param id identifier of an user to delete
     * @return {@link HttpStatus#OK} when success
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable String id) {
        usersRepository.delete(id);
        return ResponseEntity.ok().build();
    }


}
