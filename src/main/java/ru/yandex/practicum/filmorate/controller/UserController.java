package ru.yandex.practicum.filmorate.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/users")
@RestController
public class UserController {

    private int id = 0;
    private final UserStorage userStorage;
    private final UserService userService;

    @Autowired
    UserController(UserStorage userStorage, UserService userService) {
        this.userStorage = userStorage;
        this.userService = userService;
    }

    @PostMapping
    public User create(@Valid @RequestBody User user) {
        userValidation(user);
        return userStorage.createUser(user);

    }

    @GetMapping
    public List<User> getUsers() {
        return userStorage.getUsers();

    }

    @PutMapping
    public User update(@Valid @RequestBody User user) {
        userValidation(user);
        return userStorage.updateUser(user);

    }

    private void userValidation(User user) {
        if (user.getId() == 0 || user.getId() < 0) {
            user.setId(++id);
            log.info("Некорректно указан id.");
        }
        if (user.getName() == null) {
            user.setName(user.getLogin());
            log.info("Имя для отображения может быть пустым — в таком случае будет использован логин.");
        }
    }
}