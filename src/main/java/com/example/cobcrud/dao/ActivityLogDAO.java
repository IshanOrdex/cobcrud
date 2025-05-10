package com.example.cobcrud.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.cobcrud.entity.ActivityLog;

@Repository
public interface ActivityLogDAO extends JpaRepository<ActivityLog, Integer>{

	List<ActivityLog> findByUser_UserID(Integer userID);

	@Modifying
	@Query(value = "Update ACTIVITYLOG set Active = 0 WHERE ActivityID = :activityID", nativeQuery = true)
	void deleteActivity(Integer activityID);

}
