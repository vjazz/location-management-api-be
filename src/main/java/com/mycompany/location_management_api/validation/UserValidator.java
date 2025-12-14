package com.mycompany.location_management_api.validation;

import com.mycompany.location_management_api.constant.ErrorType;
import com.mycompany.location_management_api.exception.ErrorModel;
import com.mycompany.location_management_api.model.UserModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserValidator {

    public List<ErrorModel> validateRequest(UserModel userModel){

        List<ErrorModel> errorModelList = new ArrayList<>();

        if(null != userModel && userModel.getEmail() == null){
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(ErrorType.NOT_EMPTY.toString());
            errorModel.setMessage("Email can't be empty");
            errorModelList.add(errorModel);
        }

        if(null != userModel && userModel.getPassword() == null){
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(ErrorType.NOT_EMPTY.toString());
            errorModel.setMessage("Password can't be empty");
            errorModelList.add(errorModel);
        }

        return errorModelList;

    }
}

