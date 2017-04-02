package com.emirates.springsample.repository;

import com.emirates.springsample.repository.aggregation.UsersByCitizenshipResult;
import com.emirates.springsample.domain.Country;
import com.emirates.springsample.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

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

    @Override
    public Map<Country, Integer> countByCitizenship() {

        Aggregation aggregation = newAggregation(
                match(where("active").is(true)),
                group("citizenship").count().as("total"),
                project("_id", "total"),
                sort(Sort.Direction.DESC, "total")
        );

        List<UsersByCitizenshipResult> mappedResults = mongoOperations.aggregate(aggregation, User.class, UsersByCitizenshipResult.class).getMappedResults();
        return mappedResults.stream().collect(Collectors.toMap(UsersByCitizenshipResult::getId, UsersByCitizenshipResult::getTotal));
    }
}
