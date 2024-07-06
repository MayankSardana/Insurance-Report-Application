package com.insurance.service;

import java.io.File;
import java.util.List;

import com.insurance.entity.Citizen;

import jakarta.servlet.http.HttpServletResponse;

public interface InsuranceService 
{
    public List<String> plansList();
    
    public List<String> plansStatus();
    
    public List<Citizen> handleSearch(SearchResult res);
        
    public boolean ExcelGenerator(HttpServletResponse res) throws Exception ;

	boolean genrateEmail(HttpServletResponse res) throws Exception;
}
