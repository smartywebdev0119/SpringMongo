package com.emirates.springsample.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
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
    @NotNull
    private String name;
    @NotNull
    private LocalDate birthDate;
    @NotNull
    private Country citizenship;
    private boolean active = true;

    public User(String name, Country citizenship, LocalDate birthDate) {
        this.name = name;
        this.citizenship = citizenship;
        this.birthDate = birthDate;
    }

    @JsonIgnore
    public boolean isPersistent() {
        return id != null;
    }
}
