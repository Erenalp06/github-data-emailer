package com.teksen.githubdataemailer.exception;

import com.teksen.githubdataemailer.exception.response.DefaultResponse;
import com.teksen.githubdataemailer.exception.response.ExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {CustomException.class})
    public ResponseEntity<DefaultResponse> handleCustomException(CustomException e){
        DefaultResponse defaultResponse = createDefaultResponseFromException(e, e.getHttpStatusCode().value());
        return new ResponseEntity<>(defaultResponse, e.getHttpStatusCode());
    }

    private static DefaultResponse createDefaultResponseFromException(Exception e, int status) {
        String exceptionType = e.getClass().getSimpleName();
        return new DefaultResponse(status, exceptionType, e.getMessage(), new Date());
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        List<String> errors = new ArrayList<>();

        for(FieldError error : ex.getBindingResult().getFieldErrors())
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        for(ObjectError error : ex.getBindingResult().getGlobalErrors())
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());

        ExceptionResponse exceptionResponse = new ExceptionResponse(
          new Date(),
          "Validation failed",
          errors
        );

        return new ResponseEntity<>(exceptionResponse, headers, status);
    }
}
