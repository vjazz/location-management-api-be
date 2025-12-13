package com.mycompany.location_management_api.exception;

import lombok.Data;

@Data
public class ErrorModel {
    private String code;
    private String message;
}
