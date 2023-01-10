package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users/")
public class AdminUserRestController {

    final UserService userService;
    final RoleService roleService;

    @Autowired
    public AdminUserRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = this.userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Integer id) {
        User user = this.userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {

        this.userService.save(user);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("{id}")
    public ResponseEntity<HttpStatus> userSaveEdit(@RequestBody User user, @PathVariable("id") Integer id) {
        user.setId(id);
        this.userService.update(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Integer id) {
        User user = this.userService.findById(id);
        this.userService.delete(id);
        return ResponseEntity.ok().build();
    }
}