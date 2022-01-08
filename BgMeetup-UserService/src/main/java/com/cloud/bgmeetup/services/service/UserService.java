package com.cloud.bgmeetup.services.service;

import com.cloud.bgmeetup.services.dto.UserDto;
import com.cloud.bgmeetup.services.exceptions.EntityNotFoundException;
import com.cloud.bgmeetup.services.mapper.UserMapper;
import com.cloud.bgmeetup.services.model.User;
import com.cloud.bgmeetup.services.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserMapper userMapper;
    private UserRepository userRepository;

    public UserService(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public UserDto get(String id) {
        return userRepository.get(id).orElseThrow(()-> new EntityNotFoundException("User"));
    }

    public UserDto update(User user) {
        User updatedUser = userRepository.update(user);
        return userMapper.toDto(updatedUser);
    }
}
