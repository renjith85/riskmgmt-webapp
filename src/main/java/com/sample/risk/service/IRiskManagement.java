package com.sample.risk.service;



public interface IRiskManagement {
	
	public String getRiskCategory();
	public String addRiskCategory(String riskType);
	public String riskSummary();
	public String riskDetails(String date,String category);
	public String addRisk(String riskType,String description,String point,String factor);
}
