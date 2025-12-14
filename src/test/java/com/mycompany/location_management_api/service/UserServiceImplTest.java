package com.mycompany.location_management_api.service;

import com.mycompany.location_management_api.converter.UserConverter;
import com.mycompany.location_management_api.entity.UserEntity;
import com.mycompany.location_management_api.exception.BusinessException;
import com.mycompany.location_management_api.exception.ErrorModel;
import com.mycompany.location_management_api.model.UserModel;
import com.mycompany.location_management_api.repository.UserEntityRepository;
import com.mycompany.location_management_api.validation.UserValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserValidator userValidator;

    @Mock
    private UserConverter userConverter;

    @Mock
    private UserEntityRepository entityRepository;

    @Test
    public void test_login_error(){
        UserModel userModel = new UserModel();
        List<ErrorModel> errorModelList = new ArrayList<>();
        ErrorModel errorModel = new ErrorModel();
        errorModel.setCode("invalid_email");
        errorModel.setMessage("invalid email");
        errorModelList.add(errorModel);

        Mockito.when(userValidator.validateRequest(userModel)).thenReturn(errorModelList);
        Assertions.assertThrows(BusinessException.class, ()->{
            userService.login(userModel);
        });
    }

    @Test
    public void test_login_with_wrong_credentials() throws BusinessException {
        UserModel userModel = new UserModel();
        userModel.setEmail("abc@gmail");
        userModel.setPassword("xyz");
        List<ErrorModel> errorModelList = new ArrayList<>();
        UserEntity userEntity=null;

        Mockito.when(userValidator.validateRequest(userModel)).thenReturn(errorModelList);
        Mockito.when(entityRepository.findByEmailAndPassword(userModel.getEmail(), userModel.getPassword())).thenReturn(userEntity);
        Assertions.assertThrows(BusinessException.class, ()->{
            userService.login(userModel);
        });
    }

    @Test
    public void test_login_with_correct_credentials() throws BusinessException {
        UserModel userModel = new UserModel();
        userModel.setEmail("abc@gmail");
        userModel.setPassword("xyz");
        List<ErrorModel> errorModelList = new ArrayList<>();
        UserEntity userEntity= new UserEntity();
        userEntity.setEmail("abc@gmail");
        userEntity.setPassword("xyz");

        Mockito.when(userValidator.validateRequest(userModel)).thenReturn(errorModelList);
        Mockito.when(entityRepository.findByEmailAndPassword(userModel.getEmail(), userModel.getPassword())).thenReturn(userEntity);
        boolean result = userService.login(userModel);

        Assertions.assertTrue(result);
    }

    @Test
    public void test_register_error(){
        UserModel userModel = new UserModel();
        List<ErrorModel> errorModelList = new ArrayList<>();
        ErrorModel errorModel = new ErrorModel();
        errorModel.setCode("invalid_email");
        errorModel.setMessage("invalid email");
        errorModelList.add(errorModel);

        Mockito.when(userValidator.validateRequest(userModel)).thenReturn(errorModelList);
        Assertions.assertThrows(BusinessException.class, ()->{
            userService.reqister(userModel);
        });
    }

    @Test
    public void test_register_with_existing_credentials()  {
        UserModel userModel = new UserModel();
        userModel.setEmail("abc@gmail");
        userModel.setPassword("xyz");
        List<ErrorModel> errorModelList = new ArrayList<>();
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("abc@gmail");

        Mockito.when(userValidator.validateRequest(userModel)).thenReturn(errorModelList);
        Mockito.when(entityRepository.findByEmail(userModel.getEmail())).thenReturn(userEntity);
        Assertions.assertThrows(BusinessException.class, ()->{
            userService.reqister(userModel);
        });
    }

    @Test
    public void test_register_with_new_user() throws BusinessException {
        UserModel userModel = new UserModel();
        userModel.setEmail("abc@gmail");
        userModel.setPassword("xyz");
        List<ErrorModel> errorModelList = new ArrayList<>();
        UserEntity userEntity = null;

        Mockito.when(userValidator.validateRequest(userModel)).thenReturn(errorModelList);
        Mockito.when(entityRepository.findByEmail(userModel.getEmail())).thenReturn(userEntity);

        UserEntity userEntity1 = new UserEntity();
        userEntity1.setEmail("abc@gmail");
        userEntity1.setPassword("xyz");
        Mockito.when(userConverter.convertModelToEntity(userModel)).thenReturn(userEntity1);
        UserEntity userEntity2 = new UserEntity();
        userEntity2.setEmail("abc@gmail");
        userEntity2.setPassword("xyz");
        userEntity2.setId(11L);
        Mockito.when(entityRepository.save(userEntity1)).thenReturn(userEntity2);

        Long userId = userService.reqister(userModel);

        Assertions.assertEquals(11L, userId);

    }


}
