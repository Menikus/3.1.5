package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    public List<User> findAll();

    public User findById(int id);


    public User findByName(String name);


    public void save(User user);


    public void update(User updUser);


    public void delete(int id);
}
