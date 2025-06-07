package com.hizam.subscription_manager.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalAuthenticationExceptionHandler {

    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<AppError> registrationException(Exception ex){
        return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),ex.getMessage()),HttpStatus.BAD_REQUEST);

    }@ExceptionHandler(SubscriptionException.class)
    public ResponseEntity<AppError> subscriptionExceptionException(Exception ex){
        return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),ex.getMessage()),HttpStatus.BAD_REQUEST);
    }

}
