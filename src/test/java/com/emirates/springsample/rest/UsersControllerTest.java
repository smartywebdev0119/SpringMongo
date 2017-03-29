package com.emirates.springsample.rest;

import com.emirates.springsample.SampleApplication;
import com.emirates.springsample.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test for the {@link UsersController}.
 *
 * @author alex
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SampleApplication.class)
public class UsersControllerTest {

    @Autowired
    private UsersController usersController;

    /**
     * Tests {@link UsersController#getUserById(String)}
     */
    @Test
    public void getUserByIdReturnsGivenUser() {
        //given
        String id = "1";
        //when
        User user = usersController.getUserById(id);
        //then
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(id);
    }


}
