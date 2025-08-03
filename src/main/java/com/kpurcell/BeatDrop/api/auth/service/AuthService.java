package com.kpurcell.BeatDrop.api.auth.service;

import com.kpurcell.BeatDrop.api.auth.service.data.TokenUtil;
import com.kpurcell.BeatDrop.api.users.service.data.User;
import com.kpurcell.BeatDrop.repository.users.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService
{
    private final UserDataRepository userDataRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenUtil tokenUtility;

    @Autowired
    public AuthService(
            UserDataRepository userDataRepository,
            TokenUtil tokenUtility,
            PasswordEncoder passwordEncoder)
    {
        this.userDataRepository = userDataRepository;
        this.tokenUtility = tokenUtility;
        this.passwordEncoder = passwordEncoder;
    }

    public String authenticate(String emailAddress, String password) {
        // Find user by email
        User user = userDataRepository.findByEmailAddress(emailAddress)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Verify password
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        // Generate JWT with userId
        return tokenUtility.generateToken(user.getId().toString());
    }
}
