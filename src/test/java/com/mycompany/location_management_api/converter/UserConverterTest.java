package com.mycompany.location_management_api.converter;

import com.mycompany.location_management_api.entity.UserEntity;
import com.mycompany.location_management_api.model.UserModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserConverterTest {

    @InjectMocks
    private UserConverter userConverter;

    @Test
    public void test_convertModelToEntity(){
        UserModel userModel =  new UserModel();
        userModel.setEmail(("abc@gmail.com"));
        userModel.setFullName("ABC qwe");
        userModel.setMobileNumber("9999900000");
        userModel.setPassword("secret");

        UserEntity userEntity = userConverter.convertModelToEntity(userModel);
        Assertions.assertEquals(userModel.getEmail(), userEntity.getEmail());
        Assertions.assertEquals(userModel.getFullName(), userEntity.getFullName());
        Assertions.assertEquals(userModel.getMobileNumber(), userEntity.getMobileNumber());
        Assertions.assertEquals(userModel.getPassword(), userEntity.getPassword());
    }
}
