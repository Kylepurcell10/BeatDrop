package com.kpurcell.BeatDrop.api.users.service.data;

import java.time.LocalDate;

public class UserUpdateRequest
{

    /* */
    private final String firstName;

    /* */
    private final String lastName;

    /* */
    private final LocalDate birthDate;

    /* */
    private final String emailAddress;

    /* */
    private final String password;

    /* */
    public UserUpdateRequest(
            String firstName,
            String lastName,
            LocalDate birthDate,
            String emailAddress,
            String password)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    /* */
    public String getFirstName()
    {
        return firstName;
    }

    /* */
    public String getLastName()
    {
        return lastName;
    }

    /* */
    public LocalDate getBirthDate()
    {
        return birthDate;
    }
    /* */
    public String getEmailAddress()
    {
        return emailAddress;
    }

    /* */
    public String getPassword()
    {
        return password;
    };
}