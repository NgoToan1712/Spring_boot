package com.company.opentalk.service;

import com.company.opentalk.dto.UserDto;
import com.company.opentalk.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<UserDto> getUserList() {
        return userRepository.findAll().stream().map(item -> item.toDto()).collect(Collectors.toList());
    }

    public UserDto getUserById(Integer id) {
        return userRepository.findById(id).get().toDto();
    }

    public UserDto getUserByEmail(String email) {
        return userRepository.findUsersByEmail(email).toDto();
    }


    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }


}



