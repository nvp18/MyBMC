package com.bmc.appointmentService.Exception;

public class RequestedResourceNotFoundException extends RuntimeException{

    private String errMessage;

    public RequestedResourceNotFoundException(String errMessage){
        super(errMessage);
        this.errMessage = errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}
