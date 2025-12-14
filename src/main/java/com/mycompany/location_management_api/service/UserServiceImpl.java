package com.mycompany.location_management_api.service;

import com.mycompany.location_management_api.constant.ErrorType;
import com.mycompany.location_management_api.converter.UserConverter;
import com.mycompany.location_management_api.entity.UserEntity;
import com.mycompany.location_management_api.exception.BusinessException;
import com.mycompany.location_management_api.exception.ErrorModel;
import com.mycompany.location_management_api.model.UserModel;
import com.mycompany.location_management_api.repository.UserEntityRepository;
import com.mycompany.location_management_api.validation.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserEntityRepository entityRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserValidator userValidator;

    @Override
    public boolean login(UserModel userModel) throws BusinessException {

        // empty check of email and password
        List<ErrorModel> errorModelList = userValidator.validateRequest(userModel);

        if(!CollectionUtils.isEmpty(errorModelList)){
            throw new BusinessException(errorModelList);
        }

        UserEntity userEntity = entityRepository.findByEmailAndPassword(userModel.getEmail(), userModel.getPassword());

        boolean result = false;
        if(userEntity == null){
            List<ErrorModel> errorList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(ErrorType.AUTH_INVALID_CREDENTIALS.toString());
            errorModel.setMessage("Incorrect email or Password");

            errorList.add(errorModel);
            logger.warn("Invalid login attempt");
            throw new BusinessException(errorList);
        } else {
            result = true;
            logger.info("Login was success");
        }

        logger.debug("Exiting method login");
        return result;
    }

    @Override
    public Long reqister(UserModel userModel) throws BusinessException {

        // empty check of email and password
        List<ErrorModel> errorModelList = userValidator.validateRequest(userModel);

        if(!CollectionUtils.isEmpty(errorModelList)){
            throw new BusinessException(errorModelList);
        }

        // check if user already exist
        UserEntity ue = entityRepository.findByEmail(userModel.getEmail());
        if(null != ue){
            List<ErrorModel> errorList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(ErrorType.ALREADY_EXIST.toString());
            errorModel.setMessage("User already exist with this email, try another email");

            errorList.add(errorModel);
            throw new BusinessException(errorList);
        }
        UserEntity userEntity = userConverter.convertModelToEntity(userModel);
        UserEntity  userEntity1 = entityRepository.save(userEntity);

        return userEntity1.getId();
    }
}
