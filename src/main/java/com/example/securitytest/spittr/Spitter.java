package com.example.securitytest.spittr;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by hx on 2019-04-10.
 */
public class Spitter {

    private Long id;
    @NotNull
    @Size(min = 5,max = 16, message = "{username.size}")
    private String username;
    @NotNull
    @Size(min = 5,max = 25, message = "{password.size}")
    private String password;
    @NotNull
    @Size(min = 2,max = 30, message = "{firstName.size}")
    private String firstName;
    @NotNull
    @Size(min = 2,max = 30, message = "{lastName.size}")
    private String lastName;
    @NotNull
    @Size(message = "{email.valid}")
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
