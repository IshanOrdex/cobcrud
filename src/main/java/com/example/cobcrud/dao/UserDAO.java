package com.example.cobcrud.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.cobcrud.entity.User;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface UserDAO extends JpaRepository<User, Integer>{

	User findByEmail(String email);

	@Modifying
	@Query(value = "Update USER set Active = 0 WHERE Email = :email", nativeQuery = true)
	void deleteUser(@Param("email") String email);

	User findByUserID(Integer userID);

}
