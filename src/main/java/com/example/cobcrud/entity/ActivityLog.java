package com.example.cobcrud.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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

@Entity
@Table(name="ACTIVITYLOG")
public class ActivityLog {
	
	@Id
	@Column(name="ActivityID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer activityID;
	
	@Column(name="DurationInMinutes")
	private Integer durationInMinutes;
	
	@Column(name="CaloriesBurned")
    private Integer caloriesBurned;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "PlanID")
    private WorkOutPlan workoutPlan;
    
    @Column(name = "Active")
	private Byte active;

	@Column(name = "CreatedBy")
	private String createdBy;

	@Temporal(TemporalType.DATE)
	@Column(name = "CreatedAt")
	private Date createdAt;

	@Column(name = "ModifiedBy")
	private String modifiedBy;

	@Temporal(TemporalType.DATE)
	@Column(name = "ModifiedAt")
	private Date modifiedAt;
    

}
