package com.example.cobcrud.service;

import com.example.cobcrud.dto.UserDetailsDTO;
import com.example.cobcrud.dto.UserLoginDTO;
import com.example.cobcrud.dto.UserRegistrationDTO;
import com.example.cobcrud.exception.DuplicateEmailException;
import com.example.cobcrud.exception.InvalidPasswordException;
import com.example.cobcrud.exception.UserNotFoundException;

public interface UserService {

	void registerUser(UserRegistrationDTO userRegistrationDTO) throws DuplicateEmailException;

	void updateUser(UserDetailsDTO userDetailsDTO) throws UserNotFoundException;

	void deleteUser(String email);

	String userLogin(UserLoginDTO userLoginDTO) throws InvalidPasswordException;

	UserDetailsDTO fetchUser(String email);

}
