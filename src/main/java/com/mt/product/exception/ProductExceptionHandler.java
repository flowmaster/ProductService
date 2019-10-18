package com.mt.product.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice
public class ProductExceptionHandler extends ResponseEntityExceptionHandler{
	
	/**
	 *Handle for resource not found . 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(ResourceNotAvailableException.class)
	public final ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotAvailableException ex, WebRequest request) {
		return error(ex,request,HttpStatus.NO_CONTENT);
	}
	
	/**
	 *Handle for product not found . 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(ProductNotFoundException.class)
	public final ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException ex, WebRequest request) {
		return error(ex,request,HttpStatus.NOT_FOUND);
	}

	/**
	 * A generic error handle method to frame the error response
	 * @param ex
	 * @param request
	 * @param status
	 * @return
	 */
	private final ResponseEntity<ErrorResponse> error(Exception ex, WebRequest request , HttpStatus status) {
		ErrorResponse errorDetails = new ErrorResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, status);
	}
}
