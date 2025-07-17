package com.kpurcell.BeatDrop.api.users.controller;

import com.kpurcell.BeatDrop.api.users.service.UserDataService;
import com.kpurcell.BeatDrop.api.users.service.data.User;
import com.kpurcell.BeatDrop.api.users.service.data.UserUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path="/api/users")
public class UserDataController
{
    @Autowired
    private UserDataService userDataService;

    @PostMapping(path="/addUser")
    public @ResponseBody User addUser(@RequestBody UserUpdateRequest newUser)
    {
        return userDataService.addNewUser(
                newUser.getFirstName(),
                newUser.getLastName(),
                newUser.getBirthDate(),
                newUser.getEmailAddress());
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
