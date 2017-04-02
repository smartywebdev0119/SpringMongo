package com.emirates.springsample.events;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * An event listener implementation that observes the {@link UserChangedEvent}.
 *
 * @see UserChangedEvent
 *
 * @author alex
 */
@Component
public class UserEventListener {

    @Autowired
    private Logger logger;

    @EventListener
    public void handleUserChangedEvent(UserChangedEvent userEvent) {
        logger.info("User with id {} was created or modified!", userEvent.getUserId());
    }

}
