package com.emirates.springsample.dao;

import com.emirates.springsample.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * An implementation of custom, more advanced repository capabilities of the {@link UsersRepository}.
 *
 * @see UsersRepository
 *
 * @author alex
 */
public class UsersRepositoryImpl implements UsersRepositoryCustom {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public List<User> search(String name, Boolean active) {

        Query searchQuery = new Query();

        if (name != null) {
            searchQuery.addCriteria(where("name").regex(".*" + name + ".*"));
        }
        if (active != null) {
            searchQuery.addCriteria(where("active").is(active));
        }
        return mongoOperations.find(searchQuery, User.class);
    }
}
