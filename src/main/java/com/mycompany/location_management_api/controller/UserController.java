package com.mycompany.location_management_api.controller;

import com.mycompany.location_management_api.exception.BusinessException;
import com.mycompany.location_management_api.model.UserModel;
import com.mycompany.location_management_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<Boolean> login(@RequestBody UserModel userModel) throws BusinessException {

        boolean result = userService.login(userModel);

        ResponseEntity<Boolean> responseEntity = new ResponseEntity<>(result, HttpStatus.OK);

        return responseEntity;
    }

    @PostMapping("/users/register")
    public ResponseEntity<Long> register(@RequestBody UserModel userModel) throws BusinessException {

        Long result = userService.reqister(userModel);

        ResponseEntity<Long> responseEntity = new ResponseEntity<>(result, HttpStatus.CREATED);

        return responseEntity;
    }
}
