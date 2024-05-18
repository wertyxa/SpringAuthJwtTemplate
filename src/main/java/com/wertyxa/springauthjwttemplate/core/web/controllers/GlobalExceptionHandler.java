package com.wertyxa.springauthjwttemplate.core.web.controllers;

import com.wertyxa.springauthjwttemplate.core.dao.entity.User;
import com.wertyxa.springauthjwttemplate.core.utils.RequestUser;
import com.wertyxa.springauthjwttemplate.core.utils.ResponseWrapper;
import com.wertyxa.springauthjwttemplate.core.utils.exceptions.ErrorDetails;
import com.wertyxa.springauthjwttemplate.core.utils.exceptions.ResponseException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public final ResponseEntity<ResponseWrapper> handleResponseStatusException(ResponseStatusException ex, HttpServletRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getReason(), ex.getBody().getTitle(), "");
        return new ResponseEntity<>(new ResponseWrapper(false, errorDetails, new RequestUser(getUserFromPrincipal(request.getUserPrincipal())), request.getRequestURI()), ex.getStatusCode());
    }
    @ExceptionHandler(ResponseException.class)
    public final ResponseEntity<ResponseWrapper> handleResponseStatusException(ResponseException ex, HttpServletRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getReason(),ex.getInternalCode(), "");
        return new ResponseEntity<>(new ResponseWrapper(false, errorDetails, new RequestUser(getUserFromPrincipal(request.getUserPrincipal())), request.getRequestURI()), ex.getStatusCode());
    }

    private User getUserFromPrincipal(Principal principal) {
        if (principal == null) return User.builder().id(0L).username("Unauthorized").build();
        return (User) principal;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ResponseWrapper> handleValidationExceptions(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        BindingResult result = ex.getBindingResult();
        Map<String, String> errorsMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(result.getAllErrors())){
            for(ObjectError objectError : result.getAllErrors()){
                errorsMap.put(objectError.getCode(),objectError.getDefaultMessage());
            }
        }

        for (FieldError error : result.getFieldErrors()){
            errorsMap.put(error.getField(), error.getDefaultMessage());
        }
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Validation Error", "VALIDATION_ERROR", errorsMap);
        return new ResponseEntity<>(new ResponseWrapper(
                false,
                errorDetails,
                new RequestUser(getUserFromPrincipal(request.getUserPrincipal())),
                request.getRequestURI()),
                ex.getStatusCode());
    }
}