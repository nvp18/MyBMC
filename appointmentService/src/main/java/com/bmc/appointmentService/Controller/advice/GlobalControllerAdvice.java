package com.bmc.appointmentService.Controller.advice;

import com.bmc.appointmentService.DTO.ErrorDTO;
import com.bmc.appointmentService.Exception.PaymentNotMadeException;
import com.bmc.appointmentService.Exception.RequestedResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(RequestedResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleAppointmentNotFoundException(RequestedResourceNotFoundException e){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorCode("ERR_APPOINTMENT_NOT_FOUND");
        errorDTO.setErrorMessage(e.getErrMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
    }

    @ExceptionHandler(PaymentNotMadeException.class)
    public ResponseEntity<ErrorDTO> handlePaymentNotFoundException(){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorCode("ERR_PAYMENT_PENDING");
        errorDTO.setErrorMessage("Prescription cannot be issued since the payment status is pending");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }
}
