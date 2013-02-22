package com.sample.risk.controller;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sample.risk.service.IRiskManagement;
import com.sample.risk.view.Risk;
import com.sample.risk.view.RiskSummary;

@Controller
public class RiskSummaryController {
	
	Logger logger = Logger.getLogger("RiskControllerLogger");
	
	@Autowired
	@Qualifier("riskMgmtServiceImpl")
	IRiskManagement service;
	
	@RequestMapping(value = "/risks", method = RequestMethod.GET)
	public String riskSummary(Model model)
	{
		
		String summaryJSON = service.riskSummary();
		java.util.List<RiskSummary> summaryList =  new ArrayList<RiskSummary>();
		
		if(null != summaryJSON && !"".equalsIgnoreCase(summaryJSON.trim()))
		{
			try {
				JSONObject jsonObject = new JSONObject(summaryJSON);
				JSONArray summary = jsonObject.getJSONArray("summary");
				for(int i=0;i<summary.length();i++)
				{
					JSONObject summaryObject = summary.getJSONObject(i);
					RiskSummary riskSummary = new RiskSummary();
					riskSummary.setCategory(summaryObject.getString("type"));
					riskSummary.setDate(summaryObject.getString("date"));
					riskSummary.setTotal_risks(summaryObject.getString("numberOfRisks"));
					riskSummary.setTotalFactors(summaryObject.getString("totalFactor"));
					riskSummary.setTotalPoints(summaryObject.getString("totalPoints"));
					
					summaryList.add(riskSummary);
					
				}
			} catch (JSONException e) {
				
				//swallow the exception if the JSON string is not valid 
			}
		}
		
		model.addAttribute("summary", summaryList);
		
		return "risk_details";
	}
	
	@RequestMapping(value = "/risks/{date}/{category}", method = RequestMethod.GET)
	public String riskDetails(Model model,@PathVariable("date")String date,@PathVariable("category")String category)
	{
		
		String riskDetailsJSON = service.riskDetails(date,category);
		java.util.List<Risk> detailsList =   new ArrayList<Risk>();
		if(null != riskDetailsJSON && !"".equalsIgnoreCase(riskDetailsJSON.trim()))
		{
			try {
				JSONObject jsonObject = new JSONObject(riskDetailsJSON);
				JSONArray summary = jsonObject.getJSONArray("details");
				for(int i=0;i<summary.length();i++)
				{	
					JSONObject summaryObject = summary.getJSONObject(i);
					Risk riskDetail = new Risk();
					riskDetail.setRiskName(summaryObject.getString("description"));
					riskDetail.setRiskPoint(summaryObject.getString("point"));
					riskDetail.setRiskFactor(summaryObject.getString("factor"));
					detailsList.add(riskDetail);
					
				}
			} catch (JSONException e) {
				//swallow the exception if the JSON string is not valid 
			}
		}
		model.addAttribute("details", detailsList);
		model.addAttribute("riskType", category);
		model.addAttribute("riskDate", date);
		return riskSummary(model);
	}

}
