package com.emirates.springsample.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

/**
 * Represents a simple User entity.
 *
 * @author alex
 */
@Data
@Document
@NoArgsConstructor
public class User {

    @Id
    private String id;
    private String name;
    private LocalDate birthDate;
    private Country citizenship;
    private boolean active = true;

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
