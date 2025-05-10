package com.example.cobcrud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorkOutPlanDetailsDTO {
	
	private Integer planID;
	private String name;
	private String description;
	private Integer averageCaloriesBurned;
}
