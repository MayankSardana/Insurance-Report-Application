package com.insurance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.insurance.service.InsuranceService;
import com.insurance.service.SearchResult;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class InsuranceController 
{
	 @Autowired
     private InsuranceService service;
	 
	 @GetMapping("/")
	 public String LoadPage(Model model) {
		 model.addAttribute("plans", new SearchResult());
		 model.addAttribute("planList" , service.plansList());
		 model.addAttribute("statusList",service.plansStatus());
		 return "index";
	 }
	 
	
	 @PostMapping("/search")
	 public String savePage(@ModelAttribute("plans") SearchResult res , Model model)
	 {
		 model.addAttribute("tbl" , service.handleSearch(res));
		 model.addAttribute("planList" , service.plansList());
		 model.addAttribute("statusList",service.plansStatus());
		 model.addAttribute("plans", res);
		 return "index";
	 }
	 
	 
	 
	 @GetMapping("/excel")
	 public void exportExcel(HttpServletResponse res) throws Exception
	 {
		 res.setContentType("application/octect-stream");
		 res.addHeader("Content-Disposition" , "attachment;filename=plans.xls");
		 service.ExcelGenerator(res);
	 }
	 
	 @GetMapping("/attach")
	 public String attachAsMail(HttpServletResponse res , Model model) throws Exception
	 {
//		 res.setContentType("application/octect-stream");
//		 res.addHeader("Content-Disposition" , "attachment;filename=plans.xls");
		 service.genrateEmail(res);
		 model.addAttribute("plans", new SearchResult());
		 model.addAttribute("planList" , service.plansList());
		 model.addAttribute("statusList",service.plansStatus());
		 return "index";
	 }
	 
	 
}