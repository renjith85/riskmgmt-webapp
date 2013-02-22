package com.sample.risk.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sample.risk.view.Risk;

public class RiskValidator implements Validator{

		public boolean supports(Class<?> clazz) {
			//just validate the Customer instances
			return Risk.class.isAssignableFrom(clazz);
		}
	 

		public void validate(Object target, Errors errors) {
			
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "selectedCategory", "field.required","selectedCategory is required.");
			//	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "riskPoint", "field.required","Risk Points is required.");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "riskName", "field.required","Risk Description is required.");
			//	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "riskFactor" ,"field.required","Risk Factor is required.");
				
				Risk risk = (Risk)target;
				Pattern pointPattern = Pattern.compile("[\\d]*{0,2}$");
		//		Pattern factorPattern = Pattern.compile("[\\d\\.]*{0,10}$");
				Pattern riskDescriptionPattern = Pattern.compile("[\\w\\.\\s]*{0,500}");

			
					Matcher pointMatcher = pointPattern.matcher(risk.getRiskPoint());
					if(!pointMatcher.matches())
					{
						errors.rejectValue("riskPoint","Invalid value for risk point.Only numbers from 0-20 are allowed","Invalid value for risk point.Only numbers from 0-20 are allowed");
					}
					else
					{
						if(!"".equalsIgnoreCase(risk.getRiskPoint().trim()))
						{
							int val = new Integer(risk.getRiskPoint()).intValue();
							if(val > 20)
							{
								errors.rejectValue("riskPoint","Invalid value for risk point.Only numbers from 0-20 are allowed","Invalid value for risk point.Only numbers from 0-20 are allowed");
							}
						}
					}
				
//				if( ! errors.hasFieldErrors("riskFactor"))
//				{
//					Matcher factorMatcher = factorPattern.matcher(risk.getRiskFactor());
//					if(!factorMatcher.matches())
//						errors.rejectValue("riskFactor","Invalid values.Only decimal numbers are allowed","Invalid values for risk factor.Only decimal numbers are allowed");
//				}
				
				
				if( ! errors.hasFieldErrors("riskName"))
				{
					Matcher descriptionMatcher = riskDescriptionPattern.matcher(risk.getRiskName());
					if(!descriptionMatcher.matches())
						errors.rejectValue("riskName","Invalid values.Only valid characters are allowed","Invalid values for rsik description.Only alphabets,numbers and . are allowed");
				}
				
				if(risk.getSelectedCategory().equalsIgnoreCase("NONE"))
					errors.rejectValue("selectedCategory","selectedCategory is required.","Risk Type is required.");
		}

	
}
