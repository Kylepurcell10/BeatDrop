package com.kpurcell.BeatDrop.api.users.service.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.boot.internal.GenerationStrategyInterpreter;

import java.time.LocalDate;

@Entity
public class User {

    /* */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /* */
    private String firstName;

    /* */
    private String lastName;

    /* */
    private LocalDate birthDate;

    /* */
    private String emailAddress;

    /* */
    public User(){};

    /* */
    public User(
            String firstName,
            String lastName,
            LocalDate birthDate,
            String emailAddress)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.emailAddress = emailAddress;
    }

    /* */
    public String getFirstName() {
        return firstName;
    }

    /* */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /* */
    public String getLastName() {
        return lastName;
    }

    /* */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /* */
    public LocalDate getBirthDate() { return birthDate; }

    /* */
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    /* */
    public String getEmailAddress() {
        return emailAddress;
    }

    /* */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}