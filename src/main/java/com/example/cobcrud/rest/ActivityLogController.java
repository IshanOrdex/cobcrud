package com.example.cobcrud.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cobcrud.dto.ActivityLogDetailsDTO;
import com.example.cobcrud.dto.ResponseDTO;
import com.example.cobcrud.service.ActivityLogService;

@RestController
@RequestMapping("/rest/activitylog/")
public class ActivityLogController {
	
	@Autowired
	private ActivityLogService activityLogService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActivityLogController.class);
	
	@PostMapping("/assignActivity")
	public ResponseDTO assignActivity(@RequestBody ActivityLogDetailsDTO activityLogDetailsDTO) {

		ResponseDTO responseDTO = new ResponseDTO();

		LOGGER.info("In ActivityLogController -> Assign activity to user API");

		activityLogService.assignActivity(activityLogDetailsDTO);

		responseDTO.setServiceResult("Activity assigned to user successfully");
		responseDTO.setMessage("Activity assigned to user successfully");
		responseDTO.setSuccess(1);

		LOGGER.info("Exiting ActivityLogController -> Assign activity to user API");

		return responseDTO;

	}
	
	@GetMapping("/")
	public ResponseDTO activities(@RequestParam("userID") Integer userID) {

		ResponseDTO responseDTO = new ResponseDTO();

		LOGGER.info("In ActivityLogController -> Fetch Assigned activity API");

		List<ActivityLogDetailsDTO> activityDetailsDTOs =activityLogService.fetchAssignActivity(userID);

		responseDTO.setServiceResult(activityDetailsDTOs);
		responseDTO.setMessage("Fetched assigned activity successfully");
		responseDTO.setSuccess(1);

		LOGGER.info("Exiting ActivityLogController ->Fetch  Assigned activity API");

		return responseDTO;

	}
	
	@DeleteMapping("/")
	public ResponseDTO deleteActivities(@RequestParam("activityID") Integer activityID) {

		ResponseDTO responseDTO = new ResponseDTO();

		LOGGER.info("In ActivityLogController -> Delete Assigned activity API");

		activityLogService.deleteAssignActivity(activityID);

		responseDTO.setServiceResult("Deleted assigned activity successfully");
		responseDTO.setMessage("Deleted assigned activity successfully");
		responseDTO.setSuccess(1);

		LOGGER.info("Exiting ActivityLogController ->Delete  Assigned activity API");

		return responseDTO;

	}

}
