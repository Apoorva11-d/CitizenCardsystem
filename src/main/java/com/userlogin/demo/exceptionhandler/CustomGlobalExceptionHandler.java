package com.userlogin.demo.exceptionhandler;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.userlogin.demo.exceptionhandler.InputException;






@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler{

    // Let Spring handle the exception, we just override the status code
   /* @ExceptionHandler(UserNotFoundException.class)
    public void springHandleNotFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }*/

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleBookNotFound(UserNotFoundException u) throws IOException {
    	return new ResponseEntity<>(u.getLocalizedMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
	@ExceptionHandler(value = {InputException.class})
	public ResponseEntity<Object> handleAnyRequest(InputException ex,WebRequest request)
	{
		return new ResponseEntity<>(ex.getLocalizedMessage(),new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        //Get all errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);

        //Map<String, String> fieldErrors = ex.getBindingResult().getFieldErrors().stream().collect(
        //        Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

    }
}
