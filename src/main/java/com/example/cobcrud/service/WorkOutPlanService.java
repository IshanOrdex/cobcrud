package com.example.cobcrud.service;

import java.util.List;

import com.example.cobcrud.dto.WorkOutPlanDetailsDTO;

public interface WorkOutPlanService {

	void addWorkOutPlan(WorkOutPlanDetailsDTO workOutPlanDetailsDTO);

	void updateWorkOutPlan(WorkOutPlanDetailsDTO workOutPlanDetailsDTO);

	void deleteWorkOutPlan(Integer planID);

	List<WorkOutPlanDetailsDTO> fetchWorkOutPlans();

}
