package com.insurance.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.catalina.filters.ExpiresFilter.XHttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.insurance.entity.Citizen;
import com.insurance.repository.InsuranceRepository;
import com.insurance.utitlity.EmailUtils;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;


@Service
public class InsuranceServiceImp implements InsuranceService 
{
    
	@Autowired
	private InsuranceRepository repo;
	
	@Autowired
	private EmailUtils email;
	 
	@Override
	public List<String> plansList() {
		 return repo.getPlans();
	}

	@Override
	public List<String> plansStatus() {
		return repo.getStatus();
	}

	@Override
	public List<Citizen>  handleSearch(SearchResult res) {
		Citizen c = new Citizen();
		if(null!=res.getGender() && !"".equals(res.getGender())) {
			c.setGender(res.getGender());
		}
		if(null!=res.getPlanName() && !"".equals(res.getPlanName())) {
			c.setPlanName(res.getPlanName());
		}
		if(null!=res.getPlanStatus() && !"".equals(res.getPlanStatus())) {
			c.setPlanStatus(res.getPlanStatus());
		}
		if(null!=res.getPlanStartDate() && !"".equals(res.getPlanStartDate())) {
			c.setPlanStartDate(res.getPlanStartDate());
		}
		if(null!=res.getPlanEndDate() && !"".equals(res.getPlanEndDate())) {
			c.setPlanEndDate(res.getPlanEndDate());
		}
		return repo.findAll(Example.of(c));
		 
	}

	@Override
	public boolean genrateEmail(HttpServletResponse res) throws Exception  {
		   List<Citizen> list = repo.findAll();
		   
		   Workbook book = new HSSFWorkbook();
			Sheet sheet = book.createSheet("excel");
			
			Row head = sheet.createRow(0);
			
			head.createCell(0).setCellValue("Name");
			head.createCell(1).setCellValue("Gender");
			head.createCell(2).setCellValue("Plan Name");
			head.createCell(3).setCellValue("Plan Status");
			head.createCell(3).setCellValue("Plan Start Date");
			head.createCell(4).setCellValue("Plan End Date");
			head.createCell(5).setCellValue("Plan End Date");
			head.createCell(6).setCellValue("Plan End Date");
			head.createCell(7).setCellValue("Benefit Amount");
			head.createCell(8).setCellValue("Termination Date");
			head.createCell(9).setCellValue("Termination Reason");
			
			List<Citizen> records = repo.findAll();
			int r = 1;
			for(Citizen pl:records)
			{
				Row dr = sheet.createRow(r);
				dr.createCell(0).setCellValue(pl.getName());
				dr.createCell(1).setCellValue(pl.getGender());
				dr.createCell(2).setCellValue(pl.getPlanName());
				dr.createCell(3).setCellValue(pl.getPlanStatus());
				dr.createCell(4).setCellValue(pl.getPlanStartDate() + "");
				dr.createCell(5).setCellValue(pl.getPlanEndDate() + "");
				if(null!=pl.getBenefitAmount()) {
				dr.createCell(6).setCellValue(pl.getBenefitAmount());
				}else {
					dr.createCell(6).setCellValue("N/A");
				}
				dr.createCell(7).setCellValue(pl.getTerminationDate());
				dr.createCell(8).setCellValue(pl.getTerminationReason());
				r++;
			}
			
			FileOutputStream stream = new FileOutputStream(new File("Plans.xls"));
			book.write(stream);
			stream.close();
		   File f = new File("Plans.xls");
		   email.sendEmail("Test mail subject", "<h1>Test mail body</h1>", "mayanksardana4848@gmail.com" , f);
		   f.delete();
		   
		   return true;
	}

	public boolean ExcelGenerator(HttpServletResponse res) throws Exception 
	{
		Workbook book = new HSSFWorkbook();
		Sheet sheet = book.createSheet("excel");
		
		Row head = sheet.createRow(0);
		
		head.createCell(0).setCellValue("Name");
		head.createCell(1).setCellValue("Gender");
		head.createCell(2).setCellValue("Plan Name");
		head.createCell(3).setCellValue("Plan Status");
		head.createCell(3).setCellValue("Plan Start Date");
		head.createCell(4).setCellValue("Plan End Date");
		head.createCell(5).setCellValue("Plan End Date");
		head.createCell(6).setCellValue("Plan End Date");
		head.createCell(7).setCellValue("Benefit Amount");
		head.createCell(8).setCellValue("Termination Date");
		head.createCell(9).setCellValue("Termination Reason");
		
		List<Citizen> records = repo.findAll();
		int r = 1;
		for(Citizen pl:records)
		{
			Row dr = sheet.createRow(r);
			dr.createCell(0).setCellValue(pl.getName());
			dr.createCell(1).setCellValue(pl.getGender());
			dr.createCell(2).setCellValue(pl.getPlanName());
			dr.createCell(3).setCellValue(pl.getPlanStatus());
			dr.createCell(4).setCellValue(pl.getPlanStartDate() + "");
			dr.createCell(5).setCellValue(pl.getPlanEndDate() + "");
			if(null!=pl.getBenefitAmount()) {
			dr.createCell(6).setCellValue(pl.getBenefitAmount());
			}else {
				dr.createCell(6).setCellValue("N/A");
			}
			dr.createCell(7).setCellValue(pl.getTerminationDate());
			dr.createCell(8).setCellValue(pl.getTerminationReason());
			r++;
		}
		
		
		ServletOutputStream stream = res.getOutputStream();
		book.write(stream);
		book.close();
		return true;
	}

}
