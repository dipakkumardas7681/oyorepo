package com.oyo.service;

import com.oyo.entity.User;
import com.oyo.paylaod.SignIn;
import com.oyo.paylaod.UserDto;
import com.oyo.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private JWTService jwtService;

    public UserService(UserRepository userRepository, JWTService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public User addUser(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(BCrypt.hashpw(userDto.getPassword() , BCrypt.gensalt(10)));
        user.setUserRole(userDto.getUserRole());
        User savedUser = userRepository.save(user);
       return savedUser;
    }

    public String verify(SignIn signIn){
        Optional<User> opUser = userRepository.findByUsername(signIn.getUsername());
        if (opUser.isPresent()){
            User user = opUser.get();
            if(BCrypt.checkpw(signIn.getPassword() , user.getPassword())){
                return jwtService.generateToken(user);
            }
        }
         return null;
    }

}
