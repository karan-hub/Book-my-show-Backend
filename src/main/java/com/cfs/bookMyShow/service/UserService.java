package com.cfs.bookMyShow.service;

import com.cfs.bookMyShow.dto.UserDTO;
import com.cfs.bookMyShow.dto.UserProfile;
import com.cfs.bookMyShow.exception.UserAlreadyExistsException;
import com.cfs.bookMyShow.model.User;
import com.cfs.bookMyShow.model.type.Role;
import com.cfs.bookMyShow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserProfile createUser(UserDTO userDTO){

        Optional<User> number = userRepository.findByPhoneNumber(userDTO.getNumber());
        if (number.isPresent()) throw new UserAlreadyExistsException("Phone Number All Ready Exits ");

        Optional<User> email = userRepository.findByEmail(userDTO.getEmail());
        if (email.isPresent())throw new UserAlreadyExistsException("Email All Ready Exits ");

        User newUser = new User();
        newUser.setName(userDTO.getName());
        newUser.setEmail(userDTO.getEmail());
        newUser.setPhoneNumber(userDTO.getNumber());
        newUser.setRole(Role.CUSTOMER);
        User user = userRepository.save(newUser);
        return  mapToDto(user);
    }

    private UserProfile mapToDto(User user) {
        UserProfile profile = new UserProfile();
        profile.setNumber(user.getPhoneNumber());
        profile.setRole(String.valueOf(user.getRole()));
        profile.setEmail(user.getEmail());
        profile.setName(user.getName());
        profile.setId(user.getId());
        return  profile;
    }


}
