package com.example.bankcards.controller;

import com.example.bankcards.dto.UserDto;
import com.example.bankcards.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController()
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/registration")
    public String addUser(@RequestBody UserDto userDto){
        return userService.addUser(userDto);
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable String id) throws ChangeSetPersister.NotFoundException {
        return userService.getUserById(Long.parseLong(id));
    }

    @GetMapping("username/{username}")
    public UserDto getUserByUsername(@PathVariable String username) throws ChangeSetPersister.NotFoundException {
        return userService.getUserByUsername(username);
    }
}
