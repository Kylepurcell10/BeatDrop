package com.kpurcell.BeatDrop.api.users.service;

import com.kpurcell.BeatDrop.api.users.service.data.User;
import com.kpurcell.BeatDrop.api.users.service.data.UserUpdateRequest;
import com.kpurcell.BeatDrop.api.users.service.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserDataService {

    @Autowired
    private UserDataRepository userDataRepository;

    public Optional<User> getMyUser(Long userId)
    {
        return userDataRepository.findById(userId);
    }

    public Iterable<User> getAllUsers()
    {
        return userDataRepository.findAll();
    }

    public User addNewUser(
            String firstName,
            String lastName,
            LocalDate birthDate,
            String emailAddress)
    {
        User newUser = new User(
                firstName,
                lastName,
                birthDate,
                emailAddress);

        return userDataRepository.save(newUser);
    }

    public User updateRequiredFields(
            Long userId,
            UserUpdateRequest userFieldsToUpdate)
    {

        Optional<User> user = userDataRepository.findById(userId);

        if (user.isPresent())
        {
            User userEntry = user.get();
            String firstName = userFieldsToUpdate.getFirstName();
            String lastName = userFieldsToUpdate.getLastName();
            LocalDate birthDate = userFieldsToUpdate.getBirthDate();
            String emailAddress = userFieldsToUpdate.getEmailAddress();

            if (!firstName.isEmpty())
            {
                userEntry.setFirstName(firstName);
            }
            if (!lastName.isEmpty())
            {
                userEntry.setLastName(lastName);
            }
            if (birthDate != null)
            {
                userEntry.setBirthDate(birthDate);
            }
            if (!emailAddress.isEmpty())
            {
                userEntry.setEmailAddress(emailAddress);
            }
            userDataRepository.save(userEntry);
            return userEntry;
        }
        else
        {
            return null;
        }
    }
}