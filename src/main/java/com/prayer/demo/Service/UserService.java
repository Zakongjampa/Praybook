package com.prayer.demo.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.prayer.demo.Repo.UserRepo;
import com.prayer.demo.utility.User;
import com.prayer.demo.dto.UserResponseDTO;
import com.prayer.demo.dto.UserRequestDTO;
import com.prayer.demo.mapper.UserMapper;

@Service
public class UserService {

    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepo repo, PasswordEncoder encoder) {
        this.userRepo = repo;
        this.passwordEncoder = encoder;
    }

    // Create
    public UserResponseDTO Create(UserRequestDTO user) {
        User u = UserMapper.toEntity(user);
        // ensure username is populated in entity
        u.setUsername(user.getUsername());
        String pass = user.getHashedPassword();
        String hashedPass = passwordEncoder.encode(pass);
        u.setHashedPassword(hashedPass);
        User saved = userRepo.save(u);
        return UserMapper.toDTO(saved);
    }

    // Read
    // one
    public UserResponseDTO getUser(Long id) {
        User u = userRepo.findById(id).orElse(null);
        return UserMapper.toDTO(u);
    }

    // all
    public List<UserResponseDTO> getAllUsers() {
        return userRepo.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Update
    public UserResponseDTO updateUser(Long id, UserRequestDTO upcoming) {
        User user = userRepo.findById(id).orElse(null);
        if (user != null) {
            user.setUsername(upcoming.getUsername());
            user.setFirstName(upcoming.getFirstName());
            user.setLastName(upcoming.getLastName());
            if (upcoming.getHashedPassword() != null && !upcoming.getHashedPassword().isEmpty()) {
                // re-hash any new password
                user.setHashedPassword(passwordEncoder.encode(upcoming.getHashedPassword()));
            }
            User updated = userRepo.save(user);
            return UserMapper.toDTO(updated);
        }
        return null;
    }

    // Delete
    public void deleteUserById(Long id) {
        userRepo.deleteById(id);
    }

    /**
     * Authenticate a user by username and raw password. Returns a DTO of the user
     * if credentials are valid, otherwise null.
     */
    public UserResponseDTO login(String username, String password) {
        if (username == null || password == null) {
            return null;
        }

        User u = userRepo.findByUsername(username);
        if (u != null && passwordEncoder.matches(password, u.getHashedPassword())) {
            return UserMapper.toDTO(u);
        }
        return null;
    }

}
