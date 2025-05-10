package com.example.cobcrud.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cobcrud.dao.ActivityLogDAO;
import com.example.cobcrud.dao.UserDAO;
import com.example.cobcrud.dao.WorkOutPlanDAO;
import com.example.cobcrud.dto.ActivityLogDetailsDTO;
import com.example.cobcrud.entity.ActivityLog;
import com.example.cobcrud.entity.User;
import com.example.cobcrud.entity.WorkOutPlan;
import com.example.cobcrud.service.ActivityLogService;

@Service
public class ActivityLogServiceImpl implements ActivityLogService{

	private static final Logger LOGGER = LoggerFactory.getLogger(ActivityLogServiceImpl.class);

	@Autowired
	private ActivityLogDAO activityLogDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private WorkOutPlanDAO workOutPlanDAO;
	
	@Override
	public void assignActivity(ActivityLogDetailsDTO activityLogDetailsDTO) {

		LOGGER.info("In ActivityLogServiceImpl -> assignActivity Method");

		User user = userDAO.findByUserID(activityLogDetailsDTO.getUserID());

		WorkOutPlan workOutPlans = workOutPlanDAO.findByPlanID(activityLogDetailsDTO.getPlanID());

		ActivityLog activityLog = new ActivityLog();

		BeanUtils.copyProperties(activityLogDetailsDTO, activityLog);

		activityLog.setUser(user);

		activityLog.setCreatedAt(new Date());

		activityLog.setCreatedBy("Admin");

		activityLog.setWorkoutPlan(workOutPlans);

		int caloriesBurned = (int) (workOutPlans.getAverageCaloriesBurned()
				* (activityLogDetailsDTO.getDurationInMinutes() / 60.0));

		activityLog.setCaloriesBurned(caloriesBurned);

		activityLogDAO.save(activityLog);

		LOGGER.info("Exiting ActivityLogServiceImpl -> assignActivity Method");

	}

	@Override
	public List<ActivityLogDetailsDTO> fetchAssignActivity(Integer userID) {
		
		LOGGER.info("In ActivityLogServiceImpl -> Fetch assignActivity Method");
		
		List<ActivityLogDetailsDTO> activityLogDetailsDTOs = new ArrayList<>();
		
		List<ActivityLog> activityLogs = activityLogDAO.findByUser_UserID(userID);
		
		for(ActivityLog activityLog : activityLogs) {
			
			ActivityLogDetailsDTO activityLogDetailsDTO = new ActivityLogDetailsDTO();
			
			BeanUtils.copyProperties(activityLog, activityLogDetailsDTO);
			
			activityLogDetailsDTO.setPlanID(activityLog.getWorkoutPlan().getPlanID());
			
			activityLogDetailsDTO.setUserID(activityLog.getUser().getUserID());
			
			activityLogDetailsDTOs.add(activityLogDetailsDTO);
		}
		
		LOGGER.info("Exiting ActivityLogServiceImpl ->Fetch assignActivity Method");
		
		return activityLogDetailsDTOs;
	}

	@Override
	public void deleteAssignActivity(Integer activityID) {
		
		LOGGER.info("Entering ActivityLogServiceImpl ->Delete assignActivity Method");
		
		activityLogDAO.deleteActivity(activityID);
		
		LOGGER.info("Exiting ActivityLogServiceImpl ->Delete assignActivity Method");
	}

}
