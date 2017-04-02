package com.emirates.springsample.repository.aggregation;

import com.emirates.springsample.domain.Country;
import lombok.Data;

/**
 * An helper type to collect aggregation results.
 *
 * @author alex
 */
@Data
public class UsersByCitizenshipResult {

    private Country id;
    private int total;

}
