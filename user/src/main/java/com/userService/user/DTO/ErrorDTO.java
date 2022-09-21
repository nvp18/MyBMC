package com.userService.user.DTO;

import java.util.ArrayList;
import java.util.List;

public class ErrorDTO {

    private String errorCode;

    private String errorMessage;

    private List<String> errorFields;

    public ErrorDTO(String errorCode, String errorMessage, ArrayList<String> errorFields) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorFields = errorFields;
    }

    public ErrorDTO() {
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<String> getErrorFields() {
        return errorFields;
    }

    public void setErrorFields(List<String> errorFields) {
        this.errorFields = errorFields;
    }

}
