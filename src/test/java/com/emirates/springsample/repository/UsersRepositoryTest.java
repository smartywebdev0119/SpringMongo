package com.emirates.springsample.repository;

import com.emirates.springsample.SampleApplication;
import com.emirates.springsample.domain.Country;
import com.emirates.springsample.domain.User;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test for the {@link UsersRepository}.
 *
 * @author alex
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SampleApplication.class)
public class UsersRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    private List<User> testEntities = new ArrayList<>();

    @Test
    public void aggregationByCitizenShipReturnsValidResults() {

        //given

        testEntities.add(usersRepository.insert(new User("Steve Jobs", Country.US, LocalDate.now())));
        testEntities.add(usersRepository.insert(new User("Jan Kowalski", Country.PL, LocalDate.now())));

        //when

        Map<Country, Integer> countryIntegerMap = usersRepository.countByCitizenship();

        //then assert
        assertThat(countryIntegerMap).isNotEmpty();
        assertThat(countryIntegerMap.get(Country.PL)).isGreaterThan(0);
        assertThat(countryIntegerMap.get(Country.US)).isGreaterThan(0);
    }


    @After
    public void pruneTestObjects() {
        testEntities.stream().map(User::getId).forEach(usersRepository::delete);
    }

}
