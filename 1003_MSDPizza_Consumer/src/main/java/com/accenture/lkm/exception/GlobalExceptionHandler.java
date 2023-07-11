package com.accenture.lkm.exception;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import feign.FeignException;

/**
 * Application level global Exception handling
 * 
 * @author Training1
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	public static Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(FeignException.class)
    public String handleFeignStatusException(FeignException e, HttpServletResponse response) {
        response.setStatus(e.status());
        logger.error(e.getMessage());
        return "Feign Error: " + e.getMessage();
    }
	
	@ExceptionHandler(Exception.class)
    public String handleGenericStatusException(Exception e, HttpServletResponse response) {
		logger.error(e.getMessage());
        return "Generic Error: " + e.getMessage();
    }
}
