package com.kpurcell.BeatDrop.api.users.service;

import com.kpurcell.BeatDrop.api.users.controller.UserDataController;
import com.kpurcell.BeatDrop.api.users.service.data.User;
import com.kpurcell.BeatDrop.api.users.service.data.UserUpdateRequest;
import com.kpurcell.BeatDrop.api.users.service.exception.DuplicateEmailAddressException;
import com.kpurcell.BeatDrop.api.users.service.exception.InvalidNewUserParametersException;
import com.kpurcell.BeatDrop.repository.users.UserDataRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserDataService {

    /* */
    private static final Logger log = LogManager.getLogger(UserDataService.class);

    /* */
    private final UserDataRepository userDataRepository;

    /* */
    private final PasswordEncoder passwordEncoder;

    @Autowired
    UserDataService(
            UserDataRepository userDataRepository,
            PasswordEncoder passwordEncoder)
    {
        this.userDataRepository = userDataRepository;
        this.passwordEncoder = passwordEncoder;
    }

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
            String emailAddress,
            String password)
    {
        if(validateUserInputs(firstName, lastName, birthDate, emailAddress, password))
        {
            if (userDataRepository.findByEmailAddress(emailAddress).isPresent())
            {
                throw new DuplicateEmailAddressException();
            }

            User newUser = new User(
                    firstName,
                    lastName,
                    birthDate,
                    emailAddress,
                    passwordEncoder.encode(password));

            return userDataRepository.save(newUser);
        }
        throw new InvalidNewUserParametersException();
    }

    private boolean validateUserInputs(
            String firstName,
            String lastName,
            LocalDate birthDate,
            String emailAddress,
            String password)
    {
        if(firstName.isEmpty() || lastName.isEmpty() || birthDate == null || emailAddress.isEmpty() || password.isEmpty() ||
           firstName.isBlank() || lastName.isBlank() || emailAddress.isBlank() || password.isBlank())
        {
            return false;
        }
        return true;
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