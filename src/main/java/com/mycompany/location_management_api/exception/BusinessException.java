package com.mycompany.location_management_api.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
public class BusinessException extends Exception {

    private List<ErrorModel> errorList;

    public BusinessException(List<ErrorModel> errorList){
        this.errorList = errorList;
    }
}