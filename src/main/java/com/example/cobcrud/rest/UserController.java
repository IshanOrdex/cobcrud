package com.example.cobcrud.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cobcrud.dto.ResponseDTO;
import com.example.cobcrud.dto.UserDetailsDTO;
import com.example.cobcrud.dto.UserLoginDTO;
import com.example.cobcrud.dto.UserRegistrationDTO;
import com.example.cobcrud.exception.DuplicateEmailException;
import com.example.cobcrud.exception.InvalidPasswordException;
import com.example.cobcrud.exception.UserNotFoundException;
import com.example.cobcrud.service.UserService;

@RestController
@RequestMapping("/rest/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@PostMapping("/registerUser")
	public ResponseDTO user(@RequestBody UserRegistrationDTO userRegistrationDTO) throws DuplicateEmailException {

		ResponseDTO responseDTO = new ResponseDTO();

		LOGGER.info("In UserController -> RegisterUser API");

		userService.registerUser(userRegistrationDTO);

		responseDTO.setServiceResult("User details registred successfully");
		responseDTO.setMessage("User details registred successfully");
		responseDTO.setSuccess(1);

		LOGGER.info("Exiting UserController -> RegisterUser API");

		return responseDTO;

	}
	
	@PutMapping("/")
	public ResponseDTO updateUser(@RequestBody UserDetailsDTO userDetailsDTO) throws UserNotFoundException {

		ResponseDTO responseDTO = new ResponseDTO();

		LOGGER.info("In UserController -> UpdateUser API");

		userService.updateUser(userDetailsDTO);

		responseDTO.setServiceResult("User details updated successfully");
		responseDTO.setMessage("User details updated successfully");
		responseDTO.setSuccess(1);

		LOGGER.info("Exiting UserController -> UpdateUser API");

		return responseDTO;

	}
	
	@DeleteMapping("/delete")
	public ResponseDTO deleteUser(@RequestParam("email") String email) {

		ResponseDTO responseDTO = new ResponseDTO();

		LOGGER.info("In UserController -> deleteUser API");

		userService.deleteUser(email);

		responseDTO.setServiceResult("User details deleted successfully");
		responseDTO.setMessage("User details deleted successfully");
		responseDTO.setSuccess(1);

		LOGGER.info("Exiting UserController -> deleteUser API");

		return responseDTO;

	}
	
	@PostMapping("/login")
	public ResponseDTO userLogin(@RequestBody UserLoginDTO userLoginDTO) throws InvalidPasswordException {

		ResponseDTO responseDTO = new ResponseDTO();

		LOGGER.info("In UserController -> LogInUser API");

		String token = userService.userLogin(userLoginDTO);

		responseDTO.setServiceResult(token);
		responseDTO.setMessage("User Logged-In successfully");
		responseDTO.setSuccess(1);

		LOGGER.info("Exiting UserController -> LogInUser API");
		return responseDTO;
	}
	
	@GetMapping("/")
	public ResponseDTO getUser(@RequestParam("email") String email) throws UserNotFoundException {

		ResponseDTO responseDTO = new ResponseDTO();

		LOGGER.info("In UserController -> getUser API");

		UserDetailsDTO userDetailsDTO=userService.fetchUser(email);

		responseDTO.setServiceResult(userDetailsDTO);
		responseDTO.setMessage("User details fetched successfully");
		responseDTO.setSuccess(1);

		LOGGER.info("Exiting UserController -> getUser API");

		return responseDTO;

	}

}
