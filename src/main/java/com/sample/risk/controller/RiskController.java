package com.sample.risk.controller;

import java.util.LinkedHashMap;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sample.risk.service.IRiskManagement;
import com.sample.risk.view.Risk;

@Controller
public class RiskController {
	

	Logger logger = Logger.getLogger("RiskControllerLogger");
	
	@Autowired
	@Qualifier("riskMgmtServiceImpl")
	IRiskManagement service;
	
	 @InitBinder
	    protected void initBinder(WebDataBinder binder) 
	 {
	        binder.setValidator(new RiskValidator());
	    }
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addRisk(@ModelAttribute("risk")Risk risk,Model model)
	{

		model.addAttribute("categories", retrieveRiskCategory());
		
		if(!model.containsAttribute("state"))
		{
			model.addAttribute("state","");
		}
		return "risk_add";
	}
	
	private java.util.Map<String,String> retrieveRiskCategory()
	{
		String riskCategoryJSON = service.getRiskCategory();
		java.util.Map<String,String> categoryList = new LinkedHashMap<String,String>();
		
		if( null != riskCategoryJSON)
		{
			try {
				JSONObject jsonObject = new JSONObject(riskCategoryJSON);
				JSONArray categories = jsonObject.getJSONArray("riskCategory");
				
				for(int i=0;i<categories.length();i++)
				{
					categoryList.put((String)categories.get(i),(String)categories.get(i));
				}
			} catch (JSONException e) {
				//swallow the exception and let an empty list flow into the UI layer
			}
		}
		
		return categoryList;
	}
	
	 @RequestMapping(value = "/add",method = RequestMethod.POST , params="addCategory")
	   public String addCategory(@RequestParam("category") String category,@ModelAttribute("risk")Risk risk, Model model) 
	 {

		 if(null == category || "".equalsIgnoreCase(category.trim()))
		 {
			 model.addAttribute("state","typeFail");
		 }
		 else
		 {
			 Pattern riskDescriptionPattern = Pattern.compile("[a-zA-Z0-9]{0,10}");
			 Matcher m = riskDescriptionPattern.matcher(category.trim());
			 if(!m.matches())
			 {
				 model.addAttribute("state","typeFail");
			 }
			 else
			 {
				if(!retrieveRiskCategory().containsKey(category.trim()))
				{ 
					 String serviceMessage = service.addRiskCategory(category.trim());
					 if(RiskMgmtUtil.isServiceSuccess(serviceMessage))
					 {
						 model.addAttribute("state","typeSuccess"); 
					 }
					 else
					 {
						 model.addAttribute("state","systemFail");
					 }
				}
				else
				{
					model.addAttribute("state","typeFail");
				}
			 }
			 
			
		 }
			return addRisk(risk,model);
	   }
	 
	 @RequestMapping(value = "/add", method = RequestMethod.POST , params="next")
	   public String addRisk(@Valid @ModelAttribute("risk")Risk risk,BindingResult result,Model model)  throws Exception{
		 
		 if(!result.hasErrors())
		 {
			 String serviceMessage  =  service.addRisk(risk.getSelectedCategory(), risk.getRiskName(), risk.getRiskPoint(), risk.getRiskFactor());
			 if(RiskMgmtUtil.isServiceSuccess(serviceMessage))
			 {
				 model.addAttribute("state","success");
				 model.addAttribute("risk", new Risk());
			 }
			 else
			 {
				 model.addAttribute("state","systemFail");
			 }
			
			 
		 }
		 else
		 {
			 model.addAttribute("state","fail");
		 }
		 
		 return addRisk(risk,model);
	   }
	

}
