package com.insurance.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Citizen {
	
	@Id
	@GeneratedValue
	Integer citizen_id;
	String gender;
	String name;
	String planName;
	String planStatus;
	LocalDate planStartDate;
	LocalDate planEndDate;
	Integer benefitAmount;
	String denialReason;
	String terminationReason;
	LocalDate terminationDate;
}
