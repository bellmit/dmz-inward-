package com.dmz.web.config;

import com.dmz.basic.Response;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author dmz
 * @date 2017/7/20
 */
@ControllerAdvice
public class GlobalExceptionHandler  {


    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Response handleIfInvalid(ConstraintViolationException e) {
        Response response = new Response();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        response.failure(convertViolation(violations));
        return response;
    }


    @ExceptionHandler(value = { Exception.class })
    @ResponseBody
    public Response handleIfInvalid(Exception e) {
        e.printStackTrace();
        Response response = new Response();
        response.failure(e.getMessage());
        return response;
    }

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Response handleIfInvalid(MethodArgumentNotValidException e) {
        Response response = new Response();
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> errors = bindingResult.getFieldErrors();
        response.failure(convertMethodArgumentInvalid(errors));
        //Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        //violations.stream().forEach(v -> logger.debug("=======" + v.getMessage()));
        return response;
    }


    private Map<String,String> convertMethodArgumentInvalid(final List<FieldError> errors) {
        Map<String, String> message = new HashMap<>();
        for (FieldError fieldError : errors) {
            message.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return message;
    }

    private Map<String, String> convertViolation(final Set<ConstraintViolation<?>> violations) {
        Map<String, String> message = new HashMap<>();
        //violations.stream().forEach(v-> message.put(v.getPropertyPath().toString().split(".")[1], v.getMessage()));
        for (ConstraintViolation violation : violations) {
            PathImpl path = (PathImpl) violation.getPropertyPath();
            message.put(path.getLeafNode().getName(), violation.getMessage());
        }
        return message;
    }
}
