package com.example.cobcrud.rest;

import java.util.List;

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
import com.example.cobcrud.dto.WorkOutPlanDetailsDTO;
import com.example.cobcrud.service.WorkOutPlanService;

@RestController
@RequestMapping("/rest/workoutplan")
public class WorkOutPlanController {
	
	@Autowired
	private WorkOutPlanService workOutPlanService;

	private static final Logger LOGGER = LoggerFactory.getLogger(WorkOutPlanController.class);
	
	@PostMapping("/")
	public ResponseDTO workOutPlan(@RequestBody WorkOutPlanDetailsDTO workOutPlanDetailsDTO) {

		ResponseDTO responseDTO = new ResponseDTO();

		LOGGER.info("In WorkOutPlanController -> add workoutplan API");

		workOutPlanService.addWorkOutPlan(workOutPlanDetailsDTO);

		responseDTO.setServiceResult("workoutplan details registred successfully");
		responseDTO.setMessage("workoutplan details registred successfully");
		responseDTO.setSuccess(1);

		LOGGER.info("Exiting WorkOutPlanController -> add workoutplan API");

		return responseDTO;

	}
	
	@PutMapping("/")
	public ResponseDTO updateWorkOutPlan(@RequestBody WorkOutPlanDetailsDTO workOutPlanDetailsDTO){

		ResponseDTO responseDTO = new ResponseDTO();

		LOGGER.info("In WorkOutPlanController -> update workoutplan API");

		workOutPlanService.updateWorkOutPlan(workOutPlanDetailsDTO);

		responseDTO.setServiceResult("workoutplan details updated successfully");
		responseDTO.setMessage("workoutplan details updated successfully");
		responseDTO.setSuccess(1);

		LOGGER.info("Exiting WorkOutPlanController -> update workoutplan API");

		return responseDTO;

	}
	
	@DeleteMapping("/")
	public ResponseDTO deleteWorkOutPlan(@RequestParam("planID") Integer planID){

		ResponseDTO responseDTO = new ResponseDTO();

		LOGGER.info("In WorkOutPlanController -> delete workoutplan API");

		workOutPlanService.deleteWorkOutPlan(planID);

		responseDTO.setServiceResult("workoutplan details deleted successfully");
		responseDTO.setMessage("workoutplan details deleted successfully");
		responseDTO.setSuccess(1);

		LOGGER.info("Exiting WorkOutPlanController -> delete workoutplan API");

		return responseDTO;

	}
	
	@GetMapping("/")
	public ResponseDTO getWorkOutPlans(){

		ResponseDTO responseDTO = new ResponseDTO();

		LOGGER.info("In WorkOutPlanController -> Get all workoutplans API");

		List<WorkOutPlanDetailsDTO> workOutPlanDetailsDTO=workOutPlanService.fetchWorkOutPlans();

		responseDTO.setServiceResult(workOutPlanDetailsDTO);
		responseDTO.setMessage("workoutplan details fetched successfully");
		responseDTO.setSuccess(1);

		LOGGER.info("Exiting WorkOutPlanController -> Get all workoutplans API");

		return responseDTO;

	}

}
