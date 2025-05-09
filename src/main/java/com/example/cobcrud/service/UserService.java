package com.example.cobcrud.service;

import com.example.cobcrud.dto.UserRegistrationDTO;

import jakarta.validation.Valid;

public interface UserService {

	void registerUser(@Valid UserRegistrationDTO userRegistrationDTO);

}
