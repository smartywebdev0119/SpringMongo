package com.emirates.springsample.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Represents a simple User entity.
 *
 * @author alex
 */
@Data
@NoArgsConstructor
public class User {

    private String id;
    private String name;
    private LocalDate birthDate;

    public User(String id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    @JsonIgnore
    public boolean isPersistent() {
        return id != null;
    }
}
