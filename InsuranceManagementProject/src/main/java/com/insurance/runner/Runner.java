package com.insurance.runner;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.insurance.entity.Citizen;
import com.insurance.repository.InsuranceRepository;

@Component
public class Runner implements ApplicationRunner
{
	@Autowired
	private InsuranceRepository repo;

	@Override
	public void run(ApplicationArguments args) throws Exception 
	{
        repo.deleteAll();
        
        Citizen c1 = new Citizen();
        c1.setName("Mayank");
        c1.setGender("Male");
        c1.setPlanName("Cash");
        c1.setPlanStartDate(LocalDate.now());
        c1.setPlanEndDate(LocalDate.of(2024,12,10));
        c1.setPlanStatus("Approved");
        
        
        Citizen c2 = new Citizen();
        c2.setName("Neha");
        c2.setGender("Female");
        c2.setPlanName("Cash");
        c2.setDenialReason("Rent is the reason");
        c2.setPlanStatus("Denied");
        
        Citizen c3 = new Citizen();
        c3.setGender("Male");
        c3.setName("Ayush");
        c3.setPlanStatus("Terminated");
        c3.setPlanStartDate(LocalDate.of(2023, 6, 23));
        c3.setPlanEndDate(LocalDate.of(2024, 1, 23));
        c3.setPlanName("Food");
        c3.setTerminationDate(LocalDate.of(2024, 1, 23));
        c3.setTerminationReason("employeed reason");
        c3.setBenefitAmount(10000);
        
        Citizen c4 = new Citizen();
        c4.setName("Manuraj");
        c4.setPlanName("Medical");
        c4.setGender("Male");
        c4.setPlanStartDate(LocalDate.now());
        c4.setPlanEndDate(LocalDate.of(2024, 12, 24));
        c4.setPlanStatus("Approved");
        
        Citizen c5 = new Citizen();
        c5.setName("Taarak");
        c5.setBenefitAmount(50000);
        c5.setPlanEndDate(LocalDate.of(2024, 1, 1));
        c5.setPlanEndDate(LocalDate.of(2024, 2, 12));
        c5.setPlanStatus("Terminated");
        c5.setGender("Male");
        c5.setPlanName("Medical");
        c5.setTerminationDate(LocalDate.of(2024, 2, 12));
        c5.setTerminationReason("thrown out");
        
        repo.saveAll(Arrays.asList(c1 , c2 , c3 , c4 , c5));
	}

}
