package com.example.cobcrud.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cobcrud.dto.ResponseDTO;
import com.example.cobcrud.dto.UserRegistrationDTO;
import com.example.cobcrud.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@PostMapping("/")
	public ResponseDTO user(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {

		ResponseDTO responseDTO = new ResponseDTO();

		try {

			LOGGER.info("In UserController -> RegisterUser API");

			userService.registerUser(userRegistrationDTO);

			responseDTO.setServiceResult("User details registred successfully");
			responseDTO.setMessage("User details registred successfully");
			responseDTO.setSuccess(1);

			LOGGER.info("Exiting UserController -> RegisterUser API");

		}
		
		catch (Exception ex) {

			responseDTO.setServiceResult("Error occurred while register user details");
			responseDTO.setMessage("Error occurred while register user details");
			responseDTO.setSuccess(0);
		}

		return responseDTO;

	}

}
