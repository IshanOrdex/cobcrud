package com.example.cobcrud.service;

import java.util.List;

import com.example.cobcrud.dto.ActivityLogDetailsDTO;

public interface ActivityLogService {

	void assignActivity(ActivityLogDetailsDTO activityLogDetailsDTO);

	List<ActivityLogDetailsDTO> fetchAssignActivity(Integer userID);

	void deleteAssignActivity(Integer activityID);

}
