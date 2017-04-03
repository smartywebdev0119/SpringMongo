package com.emirates.springsample.repository;

import com.emirates.springsample.domain.Country;
import com.emirates.springsample.domain.User;
import com.emirates.springsample.repository.aggregation.UsersByCitizenshipResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * An implementation of custom, more advanced repository capabilities of the {@link UsersRepository}.
 *
 * @author alex
 * @see UsersRepository
 */
public class UsersRepositoryImpl implements UsersRepositoryCustom {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public List<User> search(String name, Country citizenship, LocalDate birthDateFrom, LocalDate birthDateTo, Boolean active) {

        Query searchQuery = new Query();

        if (name != null) {
            searchQuery.addCriteria(where("name").regex(".*" + name + ".*"));
        }
        if (citizenship != null) {
            searchQuery.addCriteria(where("citizenship").is(citizenship));
        }
        if (birthDateFrom != null) {
            searchQuery.addCriteria(where("birthDate").gte(birthDateFrom));
        }
        if (birthDateTo != null) {
            //TODO this currently causes exception
            searchQuery.addCriteria(where("birthDate").lte(birthDateFrom));
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
