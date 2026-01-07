package org.example.dasbackend.services.interfaces;

import org.example.dasbackend.dto.LoginUserDto;
import org.example.dasbackend.dto.RegisterUserDto;
import org.example.dasbackend.model.userroles.User;

public interface AuthenticationService {
    User signup(RegisterUserDto input);
    User authenticate(LoginUserDto input);
}
