package com.userService.user.Controller.advice;

import com.userService.user.DTO.ErrorDTO;
import com.userService.user.Exception.ResourceNotFoundException;
import com.userService.user.Utils.ErrorEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorCode(ErrorEnum.ERR_INVALID_INPUT.name());
        errorDTO.setErrorMessage("Invalid input. parameter name : ");
        errorDTO.setErrorFields(e.getBindingResult().getFieldErrors().stream().map(err -> err.getDefaultMessage()).collect(Collectors.toList()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleRequestedResourceNotFoundException(){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorCode(ErrorEnum.ERR_RESOURCE_NOT_FOUND.name());
        errorDTO.setErrorMessage("Requested resource is not available");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
    }
}
