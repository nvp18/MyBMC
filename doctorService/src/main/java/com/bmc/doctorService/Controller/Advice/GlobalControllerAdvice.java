package com.bmc.doctorService.Controller.Advice;

import com.bmc.doctorService.DTO.ErrorDTO;
import com.bmc.doctorService.Exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorCode("ERR_INVALID_INPUT");
        errorDTO.setErrorMessage("Invalid input. Parameter name: ");
        errorDTO.setErrorFields(e.getBindingResult().getFieldErrors().stream().map(err->err.getField()).collect(Collectors.toList()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleResourceNotFoundException() {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorCode("ERR_RESOURCE_NOT_FOUND");
        errorDTO.setErrorMessage("Requested resource is not available");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
    }
}
