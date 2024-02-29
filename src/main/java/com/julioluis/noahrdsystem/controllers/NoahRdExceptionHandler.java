package com.julioluis.noahrdsystem.controllers;

import com.julioluis.noahrdsystem.dtos.ExceptionResponseDTO;
import com.julioluis.noahrdsystem.utils.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class NoahRdExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllNoManageException(Exception ex, WebRequest request) {
        ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(new Date(),ex.getMessage()
                ,request.getDescription(false));

        return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) {
        ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(new Date(),ex.getMessage()
                ,request.getDescription(false));

        return new ResponseEntity(responseDTO, HttpStatus.NOT_FOUND);
    }
}
