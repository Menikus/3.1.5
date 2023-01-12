package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    final private PasswordEncoder passwordEncoder;
    final private RoleService roleService;
    final private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findById(int id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public void save(User user) {
        user.setPassword((passwordEncoder.encode(user.getPassword())));
        userRepository.save(user);
    }

    @Transactional
    public void update(User updUser) {
        updUser.setPassword((passwordEncoder.encode(updUser.getPassword())));
        userRepository.save(updUser);
    }

    @Transactional
    public void delete(int id) {
        userRepository.deleteById(id);
    }
}
