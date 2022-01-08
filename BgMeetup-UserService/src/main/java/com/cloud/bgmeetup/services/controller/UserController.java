package com.cloud.bgmeetup.services.controller;

import com.cloud.bgmeetup.services.dto.UserDto;
import com.cloud.bgmeetup.services.mapper.UserMapper;
import com.cloud.bgmeetup.services.model.User;
import com.cloud.bgmeetup.services.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto get(@PathVariable String id) {
        return userService.get(id);
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto update(@Valid @RequestBody UserDto request) {
        User user = userMapper.toEntity(request);
        return userService.update(user);
    }
}
