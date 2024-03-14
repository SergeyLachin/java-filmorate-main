package ru.yandex.practicum.filmorate.storage.user;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface UserStorage {

    public User createUser (User user);

    public List<User> getUsers();

    public User updateUser (User user);

    public User getUserById(Long id);
}
