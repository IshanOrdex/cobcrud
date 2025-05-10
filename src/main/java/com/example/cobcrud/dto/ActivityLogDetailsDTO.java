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
public class ActivityLogDetailsDTO {
	
	private Integer activityID;
	
	private Integer durationInMinutes;
	
	private Integer caloriesBurned;

	private Integer planID;
	
	private Integer userID;
}
