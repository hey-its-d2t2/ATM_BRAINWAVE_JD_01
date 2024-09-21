package com.BRAINWAVE_JD_01.serviceImpl;

import com.BRAINWAVE_JD_01.DTO.UserDTO;
import com.BRAINWAVE_JD_01.entity.Account;
import com.BRAINWAVE_JD_01.entity.User;
import com.BRAINWAVE_JD_01.repository.UserRepository;
import com.BRAINWAVE_JD_01.service.AccountService;
import com.BRAINWAVE_JD_01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if(userDTO.getAge() < 18){
            throw new RuntimeException("User is under 18 cannot  open account");
        }

        //set details
        User user = new User();
        user.setName(userDTO.getName());
        user.setAddress(userDTO.getAddress());
        user.setAge(userDTO.getAge());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        //create account
        Account account = accountService.createAccount(user);

        //save the user
        userRepository.save(user);

        return new UserDTO();
    }

    @Override
    public UserDTO login(String name, String password) {

        Optional<User> user = userRepository.findByName(name);
        if(user.isPresent() && passwordEncoder.matches(password,user.get().getPassword())) {
            //Return the user details with account information
            return  new UserDTO();
        }else {
            throw new RuntimeException("Invalid username or password");
        }
    }
}
