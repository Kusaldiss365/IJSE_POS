package com.pos.posapi.advisers;


import com.pos.posapi.exception.EntryDuplicateException;
import com.pos.posapi.exception.EntryNotFoundException;
import com.pos.posapi.util.StandardResponse;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class AppWideExceptionHandler {


    @ExceptionHandler(EntryDuplicateException.class)
    public ResponseEntity<StandardResponse> handleDuplicateRequestException(EntryDuplicateException e) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(409, e.getMessage(), e),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntryNotFoundException.class)
    public ResponseEntity<StandardResponse> handleNotFoundException(EntryNotFoundException e) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(404, e.getMessage(), e),
                HttpStatus.NOT_FOUND);
    }





}
