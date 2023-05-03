package com.chaim.coupons.controller;

import com.chaim.coupons.dto.UserDto;
import com.chaim.coupons.dto.UserLoginData;
import com.chaim.coupons.entitys.UserEntity;
import com.chaim.coupons.exceptions.ServerException;
import com.chaim.coupons.logic.UsersLogic;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    private UsersLogic usersLogic;

    @Autowired
    public UsersController(UsersLogic usersLogic) {
        this.usersLogic = usersLogic;
    }

    @PostMapping
    public void createUser(@RequestBody UserEntity user) throws ServerException {
        usersLogic.createUser(user);

    }
    @PostMapping("/login")
    public String login(@RequestBody UserLoginData userLoginData) throws ServerException, JsonProcessingException {
        String token=usersLogic. login(userLoginData);
        return token;
    }

    @PutMapping
    public void updateUser(@RequestBody UserEntity user) throws ServerException {
        usersLogic.updateUser(user);


    }

    @GetMapping("{userId}")
    public UserDto getUser(@PathVariable("userId") long id) throws ServerException {

        UserDto user = usersLogic.getUser(id);
        return user;
    }

    @DeleteMapping("{userId}")
    public void deleteCoupon(@PathVariable("userId") long id) throws ServerException {
        usersLogic.removeUser(id);


    }

    @GetMapping
    public List<UserDto> getUsers(@RequestParam("pageNumber") int pageNumber) throws ServerException {

        List<UserDto> users = usersLogic.getUsers(pageNumber);

        return users;
    }
}
