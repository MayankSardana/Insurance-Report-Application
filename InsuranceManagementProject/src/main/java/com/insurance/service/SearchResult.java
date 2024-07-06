package com.insurance.service;

import java.time.LocalDate;


import lombok.Data;

@Data
public class SearchResult 
{
	String gender;
	String planName;
	String planStatus;
	LocalDate planStartDate;
	LocalDate planEndDate;
}
