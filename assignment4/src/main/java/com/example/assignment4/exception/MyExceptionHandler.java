package com.example.assignment4.exception;

import com.example.assignment4.dto.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.xml.crypto.Data;
import java.util.Date;

@RestControllerAdvice
public class MyExceptionHandler {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<ErrorMessage> handleResourceNotFound(ResourceNotFoundException e){
        log.info("ResourceNotFound is handled");
        return new ResponseEntity<>(new ErrorMessage(new Date(System.currentTimeMillis()), e.getMessage()+"not found"), HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(value={Exception.class})
//    public ResponseEntity<String> handlerException(Exception e){
//        log.info("General Exception is going to be handled here");
//        return new ResponseEntity(new ErrorMessage(new Date(System.currentTimeMillis()), e.getMessage()+"not found"), HttpStatus.NOT_FOUND);
//    }

}
