package org.example.dasbackend.services.interfaces;

import org.example.dasbackend.model.userroles.User;

import java.util.List;

public interface UserService {
    List<User> allUsers();

    User getUserById(Integer id);
}
