package com.accenture.lkm.exception.advice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.accenture.lkm.exception.model.ErrorModel;

/**
 * Application level global Exception handling
 * 
 * @author Training1
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	public static Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorModel genericError(HttpServletRequest request, HttpServletResponse response, Exception exception) {
		ErrorModel errModel = new ErrorModel(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(),
				exception.getLocalizedMessage());
		return errModel;
	}

}
