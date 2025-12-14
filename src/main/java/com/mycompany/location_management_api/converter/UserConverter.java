package com.mycompany.location_management_api.converter;

import com.mycompany.location_management_api.entity.UserEntity;
import com.mycompany.location_management_api.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

     public UserEntity convertModelToEntity(UserModel userModel){
         UserEntity userEntity =  new UserEntity();

         userEntity.setEmail(userModel.getEmail());
         userEntity.setFullName(userModel.getFullName());
         userEntity.setMobileNumber(userModel.getMobileNumber());
         userEntity.setPassword(userModel.getPassword());

         return userEntity;

     }
}
