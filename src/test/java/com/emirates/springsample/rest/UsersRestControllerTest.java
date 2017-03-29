package com.emirates.springsample.rest;

import com.emirates.springsample.SampleApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * A test for the {@link UsersRestController}.
 *
 * @author alex
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SampleApplication.class)
public class UsersRestControllerTest {

    @Autowired
    private UsersRestController usersRestController;

    /**
     * Tests {@link UsersRestController#retrieve(String)}
     */
    @Test
    public void getUnknownUserShouldThrowEntityNotFoundException() {
        //given
        String id = "notFound";
        //then
        assertThatThrownBy(() -> usersRestController.retrieve(id)).isInstanceOf(EntityNotFoundException.class);
    }


}
