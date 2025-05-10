package com.example.cobcrud.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.cobcrud.entity.WorkOutPlan;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface WorkOutPlanDAO extends JpaRepository<WorkOutPlan, Integer>{

	WorkOutPlan findByPlanID(Integer planID);

	@Modifying
	@Query(value = "Update WORKOUTPLAN set Active = 0 WHERE PlanID = :planID", nativeQuery = true)
	void deletePlan(Integer planID);
	
}
