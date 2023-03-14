package com.example.resumeportal.service;

import com.example.resumeportal.model.User;
import com.example.resumeportal.model.UserProfile;
import com.example.resumeportal.registration.token.ConfirmationToken;
import com.example.resumeportal.registration.token.ConfirmationTokenService;
import com.example.resumeportal.repository.UserProfileRepository;
import com.example.resumeportal.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final UserProfileRepository userProfileRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUserName(userName);

        userOptional.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));

        return userOptional.get();
    }

    public String signUpUser(User user) {
        boolean userExists = userRepository.findByUserName(user.getUserName()).isPresent();

        if (userExists) {
            throw new IllegalStateException("Username already taken");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        UserProfile userProfile = new UserProfile();
        userProfile.setUserName(user.getUserName());
        userProfile.setTheme(1);
        userProfile.setEmail(user.getEmail());
        userProfileRepository.save(userProfile);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    public void enableUser(String userName) {
        userRepository.enableAppUser(userName);
    }
}
