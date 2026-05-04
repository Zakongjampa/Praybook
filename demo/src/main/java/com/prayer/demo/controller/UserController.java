package com.prayer.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prayer.demo.Service.UserService;
import com.prayer.demo.dto.UserResponseDTO;
import com.prayer.demo.dto.UserRequestDTO;
import com.prayer.demo.dto.LoginRequestDTO;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5175")
@RestController
public class UserController {
    UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // Create
    @PostMapping("/user")
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO dto) {
        UserResponseDTO create = service.Create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(create);
    }

    // Read
    // one
    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO user = service.getUser(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // all
    @GetMapping("/user")
    public ResponseEntity<List<UserResponseDTO>> getAllUser() {
        List<UserResponseDTO> user = service.getAllUsers();
        return ResponseEntity.ok(user);

    }

    // up to 20
    // Update
    @PutMapping("/user/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDTO dto) {
        UserResponseDTO user = service.updateUser(id, dto);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@Valid @RequestBody LoginRequestDTO login) {
        UserResponseDTO authenticated = service.login(login.getUsername(), login.getPassword());
        if (authenticated != null) {
            return ResponseEntity.ok(authenticated);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    // Delete
    @DeleteMapping("/user/{id}")
    public ResponseEntity<UserResponseDTO> removeUser(@PathVariable Long id) {
        service.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

}
