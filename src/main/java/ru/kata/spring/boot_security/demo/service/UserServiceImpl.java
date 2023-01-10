package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    final RoleServiceImpl roleServiceImpl;
    final private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleServiceImpl roleServiceImpl) {
        this.userRepository = userRepository;
        this.roleServiceImpl = roleServiceImpl;
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
    public User findByName(String name) {
        Optional<User> user = userRepository.findAll().stream()
                .filter(u -> Objects.equals(u.getUsername(), name))
                .findFirst();
        return user.orElse(null);
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void update(User updUser) {
        userRepository.save(updUser);
    }


    @Transactional
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' не найден!", email));
        }
        User nUser = new User(user.getEmail(), user.getPassword(), user.getRole());
        return nUser;

    }
}
