package com.example.cobcrud.serviceImpl;



import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cobcrud.dao.UserDAO;
import com.example.cobcrud.dto.UserDetailsDTO;
import com.example.cobcrud.dto.UserLoginDTO;
import com.example.cobcrud.dto.UserRegistrationDTO;
import com.example.cobcrud.entity.User;
import com.example.cobcrud.exception.DuplicateEmailException;
import com.example.cobcrud.exception.InvalidPasswordException;
import com.example.cobcrud.exception.UserNotFoundException;
import com.example.cobcrud.service.UserService;
import com.example.cobcrud.util.JwtUtil;

import jakarta.validation.Valid;

@Service
public class UserServiceImpl implements UserService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	public void registerUser(@Valid UserRegistrationDTO userRegistrationDTO) throws DuplicateEmailException {

		LOGGER.info("In UserServiceImpl -> registerUser Method");

		User users = userDAO.findByEmail(userRegistrationDTO.getEmail());

		if (users != null) {

			throw new DuplicateEmailException("Email already used! Please try with different one");
		}

		User user = new User();

		BeanUtils.copyProperties(userRegistrationDTO, user);

		user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));

		user.setCreatedAt(new Date());
		user.setCreatedBy("Admin");

		userDAO.save(user);

		LOGGER.info("Exiting UserServiceImpl -> registerUser Method");

	}

	@Override
	public void updateUser(UserDetailsDTO userDetailsDTO) throws UserNotFoundException {

		LOGGER.info("In UserServiceImpl -> updateUser Method");

		User users = userDAO.findByEmail(userDetailsDTO.getEmail());

		if (users == null) {

			throw new UserNotFoundException("User with this email not found");
		}

		BeanUtils.copyProperties(userDetailsDTO, users);

		users.setModifiedAt(new Date());
		users.setModifiedBy("Admin");

		userDAO.save(users);

		LOGGER.info("Exiting UserServiceImpl -> updateUser Method");
	}

	@Override
	public void deleteUser(String email) {
		
		LOGGER.info("In UserServiceImpl -> deleteUser Method");
		
		userDAO.deleteUser(email);
		
		LOGGER.info("Exiting UserServiceImpl -> deleteUser Method");
	}

	@Override
	public String userLogin(UserLoginDTO userLoginDTO) throws InvalidPasswordException {
		
		LOGGER.info("In UserServiceImpl -> loginUser Method");

		User user = userDAO.findByEmail(userLoginDTO.getEmail());

		if (user == null || !passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {

			throw new InvalidPasswordException("Invalid email or password");
		}

		LOGGER.info("Exiting UserServiceImpl -> loginUser Method");

		return jwtUtil.generateToken(user.getEmail());
	}

	@Override
	public UserDetailsDTO fetchUser(String email) {
		
		LOGGER.info("In UserServiceImpl -> fetchUser Method");
		
		UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
		
		User user = userDAO.findByEmail(email);
		
		BeanUtils.copyProperties(user, userDetailsDTO);
		
		LOGGER.info("Exiting UserServiceImpl -> fetchUser Method");
		
		return userDetailsDTO;
	}
	
	

}
