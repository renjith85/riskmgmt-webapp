package com.sample.risk.view;

import javax.validation.constraints.NotNull;

public class Risk {
	
	private String category;
	
	@NotNull
	private String riskName;
	
	private String riskDate;
	
	@NotNull
	private String riskFactor;
	
	@NotNull
	private String riskPoint;
	
	@NotNull
	private String selectedCategory;
	private java.util.Map<String,String> categories;
	
	public String getSelectedCategory() {
		return selectedCategory;
	}
	public void setSelectedCategory(String selectedCategory) {
		this.selectedCategory = selectedCategory;
	}
	
	public java.util.Map<String, String> getCategories() {
		return categories;
	}
	public void setCategories(java.util.Map<String, String> categories) {
		this.categories = categories;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getRiskName() {
		return riskName;
	}
	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}
	public String getRiskDate() {
		return riskDate;
	}
	public void setRiskDate(String riskDate) {
		this.riskDate = riskDate;
	}
	public String getRiskFactor() {
		return riskFactor;
	}
	public void setRiskFactor(String riskFactor) {
		this.riskFactor = riskFactor;
	}
	public String getRiskPoint() {
		return riskPoint;
	}
	public void setRiskPoint(String riskPoint) {
		this.riskPoint = riskPoint;
	}
	
	

}
