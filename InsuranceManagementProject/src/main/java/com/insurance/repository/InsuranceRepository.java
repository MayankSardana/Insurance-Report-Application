package com.insurance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.insurance.entity.Citizen;

public interface InsuranceRepository extends JpaRepository<Citizen, Integer>
{
	  @Query("select distinct(planName) from Citizen")
      public List<String> getPlans();
	  
	  @Query("select distinct(planStatus) from Citizen")
	  public List<String> getStatus();
}
