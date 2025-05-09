package com.example.cobcrud.serviceImpl;



import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cobcrud.dao.UserDAO;
import com.example.cobcrud.dto.UserRegistrationDTO;
import com.example.cobcrud.entity.User;
import com.example.cobcrud.service.UserService;

import jakarta.validation.Valid;

@Service
public class UserServiceImpl implements UserService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public void registerUser(@Valid UserRegistrationDTO userRegistrationDTO) {
		
		LOGGER.info("In UserServiceImpl -> registerUser Method");
		
		User user = new User();

		BeanUtils.copyProperties(userRegistrationDTO, user);

		user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
		
		user.setCreatedAt(new Date());
		user.setCreatedBy("Admin");

		userDAO.save(user);
		
		LOGGER.info("Exiting UserServiceImpl -> registerUser Method");
		
	}
	
	

}
