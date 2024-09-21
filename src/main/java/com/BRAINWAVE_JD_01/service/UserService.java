package com.BRAINWAVE_JD_01.service;

import com.BRAINWAVE_JD_01.DTO.UserDTO;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO login(String name, String password);
}
