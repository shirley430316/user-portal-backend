package org.example.service;

import org.example.dao.User;
import org.example.dto.UserDTO;
import org.example.dto.UserLoginDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public void sendCode(String email);
    public Boolean verify(String email, String code);
    public User register(UserDTO userDTO);
    public String login(UserLoginDTO userLoginDTO);

}
