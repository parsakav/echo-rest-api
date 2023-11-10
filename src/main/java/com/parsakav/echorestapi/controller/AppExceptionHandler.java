package com.parsakav.echorestapi.controller;


import com.parsakav.echorestapi.exceptions.UserServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AppExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(AppExceptionHandler.class);


    @ExceptionHandler({UserServiceException.class})
    public ResponseEntity<?> handleUserServiceException(UserServiceException userServiceException, WebRequest webRequest){
        logger.trace("Now handleUserServiceException is running");

        logger.debug("A user service exception occurred and we handle it");
        return new ResponseEntity<>(userServiceException.getMessage(),new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
