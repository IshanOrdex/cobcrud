package com.example.cobcrud.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cobcrud.dao.WorkOutPlanDAO;
import com.example.cobcrud.dto.WorkOutPlanDetailsDTO;
import com.example.cobcrud.entity.WorkOutPlan;
import com.example.cobcrud.service.WorkOutPlanService;

@Service
public class WorkOutPlanServiceImpl implements WorkOutPlanService{

	private static final Logger LOGGER = LoggerFactory.getLogger(WorkOutPlanServiceImpl.class);
	
	@Autowired
	private WorkOutPlanDAO workOutPlanDAO;
	
	@Override
	public void addWorkOutPlan(WorkOutPlanDetailsDTO workOutPlanDetailsDTO) {
		
		LOGGER.info("In WorkOutPlanServiceImpl -> addWorkOutPlan Method");
		
		WorkOutPlan workOutPlan = new WorkOutPlan();
		
		BeanUtils.copyProperties(workOutPlanDetailsDTO, workOutPlan);
		
		workOutPlan.setCreatedAt(new Date());
		workOutPlan.setCreatedBy("Admin");
		
		workOutPlanDAO.save(workOutPlan);
		
		LOGGER.info("Exiting from WorkOutPlanServiceImpl -> addWorkOutPlan Method");
		
	}

	@Override
	public void updateWorkOutPlan(WorkOutPlanDetailsDTO workOutPlanDetailsDTO) {
		LOGGER.info("In WorkOutPlanServiceImpl -> updateWorkOutPlan Method");

		WorkOutPlan workOutPlan = workOutPlanDAO.findByPlanID(workOutPlanDetailsDTO.getPlanID());

		BeanUtils.copyProperties(workOutPlanDetailsDTO, workOutPlan);

		workOutPlan.setModifiedAt(new Date());
		workOutPlan.setModifiedBy("Admin");

		workOutPlanDAO.save(workOutPlan);

		LOGGER.info("Exiting from WorkOutPlanServiceImpl -> updateWorkOutPlan Method");
	}

	@Override
	public void deleteWorkOutPlan(Integer planID) {
		LOGGER.info("In WorkOutPlanServiceImpl -> deleteWorkOutPlan Method");

		workOutPlanDAO.deletePlan(planID);

		LOGGER.info("Exiting WorkOutPlanServiceImpl -> deleteWorkOutPlan Method");
	}

	@Override
	public List<WorkOutPlanDetailsDTO> fetchWorkOutPlans() {

		List<WorkOutPlanDetailsDTO> workOutPlanDetailsDTOs = new ArrayList<>();

		WorkOutPlanDetailsDTO workOutPlanDetailsDTO = null;

		List<WorkOutPlan> workOutPlans = workOutPlanDAO.findAll();

		for (WorkOutPlan workOutPlan : workOutPlans) {

			workOutPlanDetailsDTO = new WorkOutPlanDetailsDTO();
			
			BeanUtils.copyProperties(workOutPlan, workOutPlanDetailsDTO);

			workOutPlanDetailsDTOs.add(workOutPlanDetailsDTO);
		}

		return workOutPlanDetailsDTOs;

	}

}
