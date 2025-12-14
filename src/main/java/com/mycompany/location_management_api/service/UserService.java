package com.mycompany.location_management_api.service;

import com.mycompany.location_management_api.exception.BusinessException;
import com.mycompany.location_management_api.model.UserModel;

public interface UserService {
    public boolean login(UserModel userModel) throws BusinessException;
    public Long reqister(UserModel userModel) throws BusinessException;
}
