package com.kpurcell.BeatDrop.api.users.controller;

import com.kpurcell.BeatDrop.api.users.service.UserDataService;
import com.kpurcell.BeatDrop.api.users.service.data.User;
import com.kpurcell.BeatDrop.api.users.service.data.UserUpdateRequest;
import com.kpurcell.BeatDrop.api.users.service.exception.DuplicateEmailAddressException;
import com.kpurcell.BeatDrop.api.users.service.exception.InvalidNewUserParametersException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping(path="/api/users")
public class UserDataController
{
    private static final Logger log = LogManager.getLogger(UserDataController.class);
    @Autowired
    private UserDataService userDataService;

    @PostMapping(path="/addUser")
    public @ResponseBody ResponseEntity<User> addUser(@RequestBody UserUpdateRequest newUser)
    {
        User user = userDataService.addNewUser(
                newUser.getFirstName(),
                newUser.getLastName(),
                newUser.getBirthDate(),
                newUser.getEmailAddress(),
                newUser.getPassword());

        return ResponseEntity.ok(user);
    }

    @ExceptionHandler(DuplicateEmailAddressException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateEmailAddressException(DuplicateEmailAddressException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(InvalidNewUserParametersException.class)
    public ResponseEntity<Map<String, String>> handleInvalidNewUserParametersException(InvalidNewUserParametersException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", ex.getMessage()));
    }

    @PatchMapping(path="/update/{userId}/")
    public @ResponseBody User updateUser(@PathVariable Long userId,
                                         @RequestBody UserUpdateRequest userFieldsToUpdate)
    {
        return userDataService.updateRequiredFields(
                userId,
                userFieldsToUpdate);
    }

    @GetMapping(path = "/getMyUser/{userId}")
    public @ResponseBody Optional<User> getMyUser(@PathVariable Long userId)
    {
        return userDataService.getMyUser(userId);
    }
}
