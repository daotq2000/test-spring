package com.example.userkeyapi.controller;

import com.example.userkeyapi.controller.request.UserCreateRequest;
import com.example.userkeyapi.dto.User;
import com.example.userkeyapi.service.UserKeyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserKeyService service;

    public UserController(UserKeyService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<User> createKey(@RequestBody UserCreateRequest userCreateRequest) {
        return ResponseEntity.ok(service.createUserKey(userCreateRequest));
    }

    @GetMapping
    public List<User> getKeys(@RequestParam(required = false) String username) {
        return service.getUserKeys(username);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKey(@PathVariable Long id) {
        service.deleteUserKey(id);
        return ResponseEntity.ok().build();
    }
}