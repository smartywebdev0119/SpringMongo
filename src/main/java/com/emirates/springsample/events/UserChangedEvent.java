package com.emirates.springsample.events;

import lombok.Data;

/**
 * An event that is fired during {@link com.emirates.springsample.domain.User} add or modify operations.
 *
 * @author alex
 */
@Data
public class UserChangedEvent {

    private String userId;

    public UserChangedEvent(String userId) {
        this.userId = userId;
    }
}
