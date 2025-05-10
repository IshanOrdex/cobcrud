package com.example.cobcrud.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.cobcrud.dto.ResponseDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(DuplicateEmailException.class)
    public ResponseDTO handleDuplicateEmailException(DuplicateEmailException ex) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setSuccess(0);
        responseDTO.setMessage(ex.getMessage());
        responseDTO.setServiceResult(ex.getMessage());
        return responseDTO;
    }

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseDTO handleUserNotFoundException(UserNotFoundException ue) {
		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setSuccess(0);
		responseDTO.setMessage(ue.getMessage());
		responseDTO.setServiceResult(ue.getMessage());
		return responseDTO;
	}
	
	@ExceptionHandler(InvalidPasswordException.class)
	public ResponseDTO handleInvalidPasswordException(InvalidPasswordException ie) {
		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setSuccess(0);
		responseDTO.setMessage(ie.getMessage());
		responseDTO.setServiceResult(ie.getMessage());
		return responseDTO;
	}
	
    @ExceptionHandler(Exception.class)
    public ResponseDTO handleGenericException(Exception ex) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setSuccess(0);
        responseDTO.setMessage("An unexpected error occurred");
        responseDTO.setServiceResult("An unexpected error occurred");
        return responseDTO;
    }

}
