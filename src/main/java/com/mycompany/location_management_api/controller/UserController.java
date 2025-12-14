package com.mycompany.location_management_api.controller;

import com.mycompany.location_management_api.exception.BusinessException;
import com.mycompany.location_management_api.model.UserModel;
import com.mycompany.location_management_api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Autowired
    private UserService userService = new UserService() {
        @Override
        public boolean login(UserModel userModel) throws BusinessException {
            return false;
        }

        @Override
        public Long reqister(UserModel userModel) throws BusinessException {
            return 0L;
        }
    };

    @PostMapping("/users")
    public ResponseEntity<Boolean> login(@RequestBody UserModel userModel) throws BusinessException {
        logger.debug("Entering method login");
        boolean result = userService.login(userModel);

        ResponseEntity<Boolean> responseEntity = new ResponseEntity<>(result, HttpStatus.OK);
        logger.debug("Exiting method login");
        return responseEntity;
    }

    @PostMapping("/users/register")
    public ResponseEntity<Long> register(@RequestBody UserModel userModel) throws BusinessException {

        Long result = userService.reqister(userModel);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
